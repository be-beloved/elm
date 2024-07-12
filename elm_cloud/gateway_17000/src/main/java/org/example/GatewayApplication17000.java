package org.example;

import example.org.properties.JwtProperties;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties.class)
public class GatewayApplication17000 {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication17000.class,args);
    }
}
