package org.example.feigh;

import example.org.entity.Food;
import example.org.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "foodServer")
public interface FoodServer {
    @GetMapping("/FoodController/getByFoodId")
    public Result<Food> getByFoodId(@RequestParam(name = "foodId")Integer foodId);

    @GetMapping("/FoodController/getFoodListById")
    public Result<List<Food>>getFoodListById(@RequestParam(name = "idList") List<Integer>idList);
}
