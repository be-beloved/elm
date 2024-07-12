package org.example.mapper;

import example.org.entity.Orders;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {
    @Insert("insert into orders(userId,businessId,orderDate,orderTotal,daId,orderState) values(#{userId},#{businessId},#{orderDate},#{orderTotal},#{daId},0)")
    @Options(useGeneratedKeys=true,keyProperty="orderId",keyColumn="orderId")
    public int saveOrders(Orders orders);

    @Select("select * from orders where orderId=#{orderId}")
    public Orders getOrdersById(Integer orderId);

    @Select("select * from orders where userId=#{userId} order by orderId ")
    public List<Orders> listOrdersByUserId(String userId);

    @Update("UPDATE orders SET orderState=1 WHERE orderId=#{orderId}")
    void payOrder(Integer orderId);
}
