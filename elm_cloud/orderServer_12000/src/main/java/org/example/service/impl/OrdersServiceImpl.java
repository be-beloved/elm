package org.example.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import example.org.entity.*;
import example.org.exception.CircuitBreakerException;
import example.org.result.Result;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.feigh.BusinessServer;
import org.example.feigh.CartServer;
import org.example.feigh.FoodServer;
import org.example.mapper.OrderDetailMapper;
import org.example.mapper.OrderMapper;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class OrdersServiceImpl implements OrderService {
	
	@Resource
	private CartServer cartServer;

	@Resource
	private BusinessServer businessServer;

	@Resource
	private FoodServer foodServer;

	@Resource
	private OrderMapper ordersMapper;
	@Resource
	private OrderDetailMapper orderDetailetMapper;

	@Override
	@Transactional
	@CircuitBreaker(name = "backendA",fallbackMethod = "listCartOrremoveCartDown")
	public int createOrders(Orders orders) {
		//1、查询当前用户购物车中当前商家的所有食品
		// 1. 查询当前用户购物车中当前商家的所有食品
		String userId = orders.getUserId();
		Integer businessId = orders.getBusinessId();
		// 远程调用cart查询
		Result<List<Cart>> result = cartServer.listCart(userId, businessId);
		List<Cart> cartList = result.getData();
		
		//2、创建订单（返回生成的订单编号）
		orders.setOrderDate(String.valueOf(LocalDateTime.now()));
		ordersMapper.saveOrders(orders);
		int orderId = orders.getOrderId();
		
		//3、批量添加订单明细
		List<OrderDetailet> list = new ArrayList<>();
		for(Cart c : cartList) {
			OrderDetailet od = new OrderDetailet();
			od.setOrderId(orderId);
			od.setFoodId(c.getFoodId());
			od.setQuantity(c.getQuantity());
			list.add(od);
		}
		//批量插入
		orderDetailetMapper.saveOrderDetailetBatch(list);
		
		//4、从购物车表中删除相关食品信息
		//远程调用cart删除
		cartServer.removeCart(Integer.valueOf(userId),businessId);
		
		return orderId;
	}
	public int listCartOrremoveCartDown(Orders orders,Throwable e){
		log.info("创建订单触发熔断：{}",e.getMessage());
		throw new CircuitBreakerException("服务繁忙中，稍后再试...");
	}
	@Override
	@CircuitBreaker(name = "backendA",fallbackMethod = "getBusinessByIdOrgetFoodListByIdDown")
	public Orders getOrdersById(Integer orderId) {

		Orders orders = ordersMapper.getOrdersById(orderId);
		//远程调用business查询business
		Result<Business> businessResult = businessServer.getBusinessById(orders.getBusinessId());
		Business business = businessResult.getData();
		orders.setBusiness(business);


		List<OrderDetailet> orderDetailetList = orderDetailetMapper.listOrderDetailetByOrderId(orderId);
		//提取食品id
		List<Integer> foodIds = orderDetailetList.stream()
				.map(OrderDetailet::getFoodId)
				.toList();
		//远程调用food获取食品列表
		Result<List<Food>> listResult = foodServer.getFoodListById(foodIds);
		List<Food> foodList = listResult.getData();

		//将获取到的食品列表匹配订单细节
		for (OrderDetailet orderDetailet : orderDetailetList) {
			for (Food food : foodList) {
				if (food.getFoodId().equals(orderDetailet.getFoodId())) {
					orderDetailet.setFood(food);
					break;
				}
			}
		}
		orders.setList(orderDetailetList);

		return orders;
	}
	public Orders getBusinessByIdOrgetFoodListByIdDown(Integer orderId,Throwable e){
		log.info("根据id查询订单触发熔断：{}",e.getMessage());
		throw new CircuitBreakerException("服务繁忙中，稍后再试...");
	}
	@Override
	@CircuitBreaker(name = "backendA",fallbackMethod = "listOrdersByUserIdDown")
	public List<Orders> listOrdersByUserId(String userId){
		List<Orders>ordersList=ordersMapper.listOrdersByUserId(userId);
		//获取businessId
		List<Integer>idList=ordersList.stream().map(Orders::getBusinessId).toList();
		//远程调用business获取商家列表
		Result<List<Business>>listResult=businessServer.getByIdList(idList);
		List<Business>businessList=listResult.getData();
		//商家赋值
		for (Orders orders : ordersList) {
			for (Business business : businessList) {
				if (orders.getBusinessId().equals(business.getBusinessId())){
					orders.setBusiness(business);
					break;
				}
			}
		}

		//获取订单id
		List<Integer>orderIdList=ordersList.stream().map(Orders::getOrderId).toList();
		//通过id批量查询订单细节
		List<OrderDetailet>orderDetailetList=orderDetailetMapper.findByOrderIdList(orderIdList);

		//提取食品id
		List<Integer> foodIds = orderDetailetList.stream()
				.map(OrderDetailet::getFoodId)
				.toList();
		//远程调用food获取食品列表
		Result<List<Food>> listResult1 = foodServer.getFoodListById(foodIds);
		List<Food> foodList = listResult1.getData();

		//将获取到的食品列表匹配订单细节
		for (OrderDetailet orderDetailet : orderDetailetList) {
			for (Food food : foodList) {
				if (food.getFoodId().equals(orderDetailet.getFoodId())) {
					orderDetailet.setFood(food);
					break;
				}
			}
		}

		//将订单细节添加到对应的订单
		for (Orders orders : ordersList) {
			List<OrderDetailet> orderDetailsForOrder = new ArrayList<>();
			for (OrderDetailet orderDetail : orderDetailetList) {
				if (orders.getOrderId().equals(orderDetail.getOrderId())) {
					orderDetailsForOrder.add(orderDetail);
				}
			}
			orders.setList(orderDetailsForOrder);
		}
		return ordersList;
	}

	public List<Orders> listOrdersByUserIdDown(String userId,Throwable e){
		log.info("根据id查询订单触发熔断：{}",e.getMessage());
		throw new CircuitBreakerException("服务繁忙中，稍后再试...");
	}
	@Override
	public void payOrder(Integer orderId) {
		ordersMapper.payOrder(orderId);
	}
}
