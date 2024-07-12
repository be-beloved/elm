package org.example.mapper;

import example.org.entity.OrderDetailet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderDetailMapper {


    int saveOrderDetailetBatch(List<OrderDetailet> list);

    @Select("select * from orderdetailet where orderId=#{orderId}")
    public List<OrderDetailet> listOrderDetailetByOrderId(Integer orderId);


    List<OrderDetailet> findByOrderIdList(List<Integer> orderIdList);
}
