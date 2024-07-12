package org.example.filter;

import example.org.properties.JwtProperties;
import example.org.utils.JwtUtil;
import jakarta.annotation.Resource;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Component

public class AuthFilter implements GlobalFilter {
    @Resource
    private JwtProperties jwtProperties;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getPath().toString();
        if ("/UserController/getUserByIdByPass".equals(path) || "/UserController/saveUser".equals(path)) {
            return chain.filter(exchange);
        }

        Map<String,Object>claims=new HashMap<>();
        try {
            String token=exchange.getRequest().getHeaders().getFirst(jwtProperties.getUserTokenName());
           claims= JwtUtil.parseJWT(jwtProperties.getUserSecretKey(),token);
        }catch (Exception e){
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
//        Integer userId= (Integer) claims.get("userId");
//        if (userId==null){
//            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//            return exchange.getResponse().setComplete();
//        }
//        执行过滤链的下一步骤
        return chain.filter(exchange);
    }
}
