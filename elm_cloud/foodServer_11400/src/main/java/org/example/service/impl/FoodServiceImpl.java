package org.example.service.impl;

import java.util.List;

import example.org.entity.Food;
import jakarta.annotation.Resource;
import org.example.mapper.FoodMapper;
import org.example.service.FoodService;
import org.springframework.stereotype.Service;

@Service
public class FoodServiceImpl implements FoodService {
	
	@Resource
	private FoodMapper foodMapper;

	@Override
	public List<Food> listFoodByBusinessId(Integer businessId) {
		return foodMapper.listFoodByBusinessId(businessId);
	}

	@Override
	public Food getByFoodId(Integer foodId) {
		return foodMapper.getFoodById(foodId);
	}

	@Override
	public List<Food> getByIdList(List<Integer> idList) {
		return foodMapper.getFoodByList(idList);
	}
}
