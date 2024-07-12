package org.example.controller;

import example.org.entity.DeliveryAddress;
import example.org.result.Result;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.service.DeliveryAddressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/DeliveryAddressController")
@Slf4j
public class DeliveryAddressController {
    @Resource
    private DeliveryAddressService deliveryAddressService;

    /**
     * 通过用户id获取地址列表
     * @param deliveryAddress
     * @return
     * @throws Exception
     */
    @GetMapping("/listDeliveryAddressByUserId")
    public Result<List<DeliveryAddress>> listDeliveryAddressByUserId(DeliveryAddress deliveryAddress) throws Exception{
        log.info("通过用户id获取地址：{}",deliveryAddress);
        List<DeliveryAddress>deliveryAddressList =deliveryAddressService.listDeliveryAddressByUserId(deliveryAddress.getUserId());
        return Result.success(deliveryAddressList);
    }

    /**
     * 通过Id获取地址
     * @param deliveryAddress
     * @return
     * @throws Exception
     */
    @GetMapping("/Address")
    public Result<DeliveryAddress> getDeliveryAddressById(DeliveryAddress deliveryAddress) throws Exception{
        log.info("通过id获取地址：{}",deliveryAddress);
        DeliveryAddress deliveryAddress1= deliveryAddressService.getDeliveryAddressById(deliveryAddress.getDaId());
        return  Result.success(deliveryAddress1);
    }

    /**
     * 新增地址
     * @param deliveryAddress
     * @return
     * @throws Exception
     */
    @PostMapping("/Address")
    public Result saveDeliveryAddress(@RequestBody DeliveryAddress deliveryAddress) throws Exception{
        log.info("增加地址：{}",deliveryAddress);
         deliveryAddressService.saveDeliveryAddress(deliveryAddress);
         return Result.success();
    }

    /**
     * 修改地址
     * @param deliveryAddress
     * @return
     * @throws Exception
     */
    @PutMapping("/Address")
    public Result updateDeliveryAddress(@RequestBody DeliveryAddress deliveryAddress) throws Exception{
        log.info("修改地址：{}",deliveryAddress);
         deliveryAddressService.updateDeliveryAddress(deliveryAddress);
         return Result.success();
    }

    /**
     * 删除地址
     * @param deliveryAddress
     * @return
     * @throws Exception
     */
    @DeleteMapping("/Address")
    public Result removeDeliveryAddress(DeliveryAddress deliveryAddress) throws Exception{
        log.info("删除地址：{}",deliveryAddress);
      deliveryAddressService.removeDeliveryAddress(deliveryAddress.getDaId());
      return Result.success();
    }
}
