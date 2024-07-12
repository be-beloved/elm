package org.example.controller;

import example.org.entity.Orders;
import example.org.result.Result;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/OrderController")
@Slf4j
public class OrderController {
    @Resource
    private OrderService ordersService;


    @PostMapping("/createOrders")
    public Result createOrders(@RequestBody Orders orders) throws Exception{
        log.info("创建订单：{}",orders);
        int orderId=ordersService.createOrders(orders);
        return Result.success(orderId);
    }

    @GetMapping("/getOrdersById")
    public Result<Orders> getOrdersById(@RequestParam("orderId")Integer orderId) throws Exception{
        log.info("通过订单id获取订单：{}",orderId);
        Orders orders1= ordersService.getOrdersById(orderId);
        return Result.success(orders1);
    }

    @PutMapping("/payOrder")
    public Result payOrder(@RequestParam("orderId")Integer orderId){
        log.info("支付订单：{}",orderId);
        ordersService.payOrder(orderId);
        return Result.success();
    }

    @GetMapping("/listOrdersByUserId")
    public Result<List<Orders>> listOrdersByUserId(@RequestParam("userId")Integer userId) throws Exception{
        log.info("通过用户id获取订单:{}",userId);
        List<Orders>ordersList= ordersService.listOrdersByUserId(String.valueOf(userId));
        return Result.success(ordersList);
    }
}
