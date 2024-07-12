package org.example.handler;

import example.org.exception.BaseException;
import example.org.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex){
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    /**
     * 处理SQL异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(SQLIntegrityConstraintViolationException ex){
//        log.error("异常信息：{}", ex.getMessage());
//        return Result.error(ex.getMessage());
        if(ex.getMessage().contains("Duplicate entry")){
            String msg="手机号已经被注册已存在";
            return Result.error(msg);
        }else {
            return Result.error("未知异常");
        }
    }
}
