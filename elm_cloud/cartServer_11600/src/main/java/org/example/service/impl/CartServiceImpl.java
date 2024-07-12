package org.example.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import example.org.entity.Business;
import example.org.entity.Cart;
import example.org.entity.Food;
import example.org.exception.CircuitBreakerException;
import example.org.result.Result;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.feign.BusinessServer;
import org.example.feign.FoodServer;
import org.example.mapper.CartMapper;
import org.example.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class CartServiceImpl implements CartService {
	
	@Resource
	private CartMapper cartMapper;

	@Resource
	private BusinessServer businessServer;

	@Resource
	private FoodServer foodServer;

	/**
	 * 获取购物车列表
	 * @param cart
	 * @return
	 */
	@Override
	@CircuitBreaker(name = "backendA",fallbackMethod = "getFoodListByIdOrgetBusinessByIdList")
	public List<Cart> listCart(Cart cart) {
		List<Cart>cartList=cartMapper.listCart(cart);
		if(cartList.isEmpty()){
			return cartList;
		}

		List<Integer> foodIdList = cartList.stream()
				.map(Cart::getFoodId)
				.collect(Collectors.toList());

		List<Integer>businessIdList=cartList.stream().
				map(Cart::getBusinessId).
				collect(Collectors.toList());


		//远程调用食品
		Result<List<Food>>listResultFood=foodServer.getFoodListById(foodIdList);
		//远程调用商家
		Result<List<Business>>listResultBusiness=businessServer.getByIdList(businessIdList);

		List<Food>foodList=listResultFood.getData();
		List<Business>businessList=listResultBusiness.getData();

		// 将 foodList 转换为 Map<foodId, Food>
		Map<Integer, Food> foodMap = foodList.stream()
				.collect(Collectors.toMap(Food::getFoodId, food -> food));

		// 将 businessList 转换为 Map<businessId, Business>
		Map<Integer, Business> businessMap = businessList.stream()
				.collect(Collectors.toMap(Business::getBusinessId, business -> business));

		// 遍历 cartList 并赋值 food 和 business
		cartList.forEach(cart1 -> {
			int foodId = cart1.getFoodId();
			int businessId = cart1.getBusinessId();

			if (foodMap.containsKey(foodId)) {
				cart1.setFood(foodMap.get(foodId));
			}

			if (businessMap.containsKey(businessId)) {
				cart1.setBusiness(businessMap.get(businessId));
			}
		});

		return cartList;
	}
	public List<Cart>getFoodListByIdOrgetBusinessByIdList(Cart cart,Throwable e){
		log.info("服务熔断：{}",e.getMessage());
		throw new CircuitBreakerException("服务繁忙中，稍后再试...");

	}


	@Override
	public int saveCart(Cart cart) {
		return cartMapper.saveCart(cart);
	}
	
	@Override
	public int updateCart(Cart cart) {
		return cartMapper.updateCart(cart);
	}
	
	@Override
	public int removeCart(Cart cart) {
		return cartMapper.removeCart(cart);
	}
}
