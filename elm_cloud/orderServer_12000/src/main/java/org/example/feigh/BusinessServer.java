package org.example.feigh;

import example.org.entity.Business;
import example.org.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "businessServer")
public interface BusinessServer {
    @GetMapping("/BusinessController/getBusinessById")
    public Result<Business> getBusinessById(@RequestParam(name = "businessId") Integer businessId);

    @GetMapping("/BusinessController/getByIdList")
    public Result<List<Business>>getByIdList(@RequestParam(name = "idList") List<Integer>idList);
}
