package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@LoadBalancerClients({@LoadBalancerClient(name = "cartServer"),@LoadBalancerClient(name = "businessServer"),@LoadBalancerClient(name = "foodServer")})
public class OrderServerApplication12000 {
    public static void main(String[] args) {
        SpringApplication.run(OrderServerApplication12000.class,args);
    }
}
