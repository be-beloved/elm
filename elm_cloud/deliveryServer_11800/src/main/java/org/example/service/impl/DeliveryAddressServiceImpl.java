package org.example.service.impl;

import java.util.List;

import example.org.entity.DeliveryAddress;
import jakarta.annotation.Resource;
import org.example.mapper.DeliveryAddressMapper;
import org.example.service.DeliveryAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class DeliveryAddressServiceImpl implements DeliveryAddressService {
	
	@Resource
	private DeliveryAddressMapper deliveryAddressMapper;
	
	@Override
	public List<DeliveryAddress> listDeliveryAddressByUserId(String userId) {
		return deliveryAddressMapper.listDeliveryAddressByUserId(userId);
	}
	
	@Override
	public DeliveryAddress getDeliveryAddressById(Integer daId) {
		return deliveryAddressMapper.getDeliveryAddressById(daId);
	}
	
	@Override
	public int saveDeliveryAddress(DeliveryAddress deliveryAddress) {
		return deliveryAddressMapper.saveDeliveryAddress(deliveryAddress);
	}
	
	@Override
	public int updateDeliveryAddress(DeliveryAddress deliveryAddress) {
		return deliveryAddressMapper.updateDeliveryAddress(deliveryAddress);
	}
	
	@Override
	public int removeDeliveryAddress(Integer daId) {
		return deliveryAddressMapper.removeDeliveryAddress(daId);
	}
}
