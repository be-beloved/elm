package org.example.feign;

import example.org.entity.Food;
import example.org.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("foodServer")
public interface FoodServer {
    @GetMapping("/FoodController/getFoodListById")
    public Result<List<Food>> getFoodListById(@RequestParam(name = "idList") List<Integer>idList);
}
