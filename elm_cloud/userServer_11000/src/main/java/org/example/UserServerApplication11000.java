package org.example;


import example.org.properties.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@EnableConfigurationProperties(JwtProperties.class)
@SpringBootApplication
public class UserServerApplication11000 {
    public static void main(String[] args) {
        SpringApplication.run(UserServerApplication11000.class,args);
    }
}
