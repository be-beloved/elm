package org.example.feigh;

import example.org.entity.Cart;
import example.org.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "cartServer")
public interface CartServer {
    @GetMapping("/CartController/getcartList")
    Result<List<Cart>> listCart(@RequestParam("userId") String userId, @RequestParam("businessId") Integer businessId);

    @DeleteMapping("/CartController/cart")
    public Result removeCart(@RequestParam("userId") Integer userId, @RequestParam(value = "businessId",required = false) Integer businessId);
}
