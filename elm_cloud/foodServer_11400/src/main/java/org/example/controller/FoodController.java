package org.example.controller;

import example.org.entity.Food;
import example.org.result.Result;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.service.FoodService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/FoodController")
@Slf4j
public class FoodController {

    @Resource
    private FoodService foodService;

    /**
     * 通过商家id获取食品列表
     * @param businessId
     * @return
     */
    @GetMapping("/listFoodByBusinessId")
    public Result<List<Food>> listFoodByBusinessId(@RequestParam(name = "businessId")Integer businessId){
        log.info("通过商家id获取食物:{}",businessId);
        List<Food>foodList= foodService.listFoodByBusinessId(businessId);
        return Result.success(foodList);
    }

    /**
     * 通过食物id获取食物
     * @param foodId
     * @return
     */
    @GetMapping("/getByFoodId")
    public Result<Food>getByFoodId(@RequestParam(name = "foodId")Integer foodId){
        log.info("通过食品id获取食品：{}",foodId);
        Food food=foodService.getByFoodId(foodId);
        return Result.success(food);
    }

    /**
     * 通过id列表获取食品
     * @param idList
     * @return
     */
    @GetMapping("/getFoodListById")
    public Result<List<Food>>getFoodListById(@RequestParam(name = "idList") List<Integer>idList){
        log.info("通过id列表获取食物：{}",idList);
        List<Food>foodList=foodService.getByIdList(idList);
        return Result.success(foodList);
    }
}