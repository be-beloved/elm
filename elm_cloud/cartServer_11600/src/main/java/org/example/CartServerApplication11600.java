package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@LoadBalancerClients({@LoadBalancerClient(name = "foodServer"),@LoadBalancerClient(name = "businessServer")})
public class CartServerApplication11600 {
    public static void main(String[] args) {
        SpringApplication.run(CartServerApplication11600.class,args);
    }
}
