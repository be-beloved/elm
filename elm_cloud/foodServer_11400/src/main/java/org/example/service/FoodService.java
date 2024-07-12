package org.example.service;

import example.org.entity.Food;

import java.util.List;

public interface FoodService {

	public List<Food> listFoodByBusinessId(Integer businessId);

	Food getByFoodId(Integer foodId);

    List<Food> getByIdList(List<Integer> idList);
}
