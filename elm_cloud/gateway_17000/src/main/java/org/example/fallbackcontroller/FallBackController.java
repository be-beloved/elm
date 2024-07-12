package org.example.fallbackcontroller;


import example.org.result.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackController {
    @RequestMapping("/fallback")
    public Result<String> cartFallBack(){
        String msg="服务繁忙中，稍后再试...";
        return Result.error(msg,2);
    }
}
