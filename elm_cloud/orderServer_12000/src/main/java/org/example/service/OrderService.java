package org.example.service;

import example.org.entity.Orders;

import java.util.List;

public interface OrderService {
    public int createOrders(Orders orders);
    public Orders getOrdersById(Integer orderId);
    public List<Orders> listOrdersByUserId(String userId);

    void payOrder(Integer orderId);
}
