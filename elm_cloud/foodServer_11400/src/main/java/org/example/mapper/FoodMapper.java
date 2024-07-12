package org.example.mapper;

import java.util.List;

import example.org.entity.Food;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;



@Mapper
public interface FoodMapper {

	@Select("select * from food where businessId=#{businessId} order by foodId")
	public List<Food> listFoodByBusinessId(Integer businessId);
	
	@Select("select * from food where foodId=#{foodId}")
	public Food getFoodById(Integer foodId);


    List<Food> getFoodByList(List<Integer> idList);
}
