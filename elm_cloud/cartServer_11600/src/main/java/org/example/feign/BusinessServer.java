package org.example.feign;

import example.org.entity.Business;
import example.org.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("businessServer")
public interface BusinessServer {
    @GetMapping("/BusinessController//getByIdList")
    public Result<List<Business>> getByIdList(@RequestParam(name = "idList") List<Integer>idList);
}
