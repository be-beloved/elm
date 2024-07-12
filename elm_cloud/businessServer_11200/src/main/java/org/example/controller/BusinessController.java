package org.example.controller;

import example.org.dto.PageQueryDTO;
import example.org.entity.Business;
import example.org.result.PageResult;
import example.org.result.Result;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.service.BusinessService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/BusinessController")
@Slf4j
public class BusinessController {

    @Resource
    private BusinessService businessService;

    /**
     * 根据类型查询商家
     * @param orderTypeId
     * @return
     */
    @GetMapping("/listBusinessByOrderTypeId")
    public Result<List<Business>> listBusinessByOrderTypeId(@RequestParam(name = "orderTypeId") Integer orderTypeId){
        log.info("根据类型查询商家：{}",orderTypeId);
        List<Business>businessList= businessService.listBusinessByOrderTypeId( orderTypeId);
        return Result.success(businessList);
    }

    /**
     * 根据id查询商家
     * @param businessId
     * @return
     */
    @GetMapping("/getBusinessById")
    public Result<Business> getBusinessById(@RequestParam(name = "businessId") Integer businessId){
        log.info("根据id查询商家：{}",businessId);
        Business business= businessService.getBusinessById(businessId);
        return Result.success(business);
    }

    /**
     * 分页查询商家
     */
    @GetMapping("/getBusinessByPageQuery")
    public Result<PageResult>getBusinessByPageQuery( PageQueryDTO pageQueryDTO){
        log.info("分页查询商家{}",pageQueryDTO);
        PageResult pageResult=businessService.pageQuery(pageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 通过id列表查询商家
     * @param idList
     * @return
     */
    @GetMapping("/getByIdList")
    public Result<List<Business>>getByIdList(@RequestParam(name = "idList") List<Integer>idList){
        log.info("通过id列表查询商家：{}",idList);
        List<Business>businessList=businessService.getByIdList(idList);
        return Result.success(businessList);
    }
}

