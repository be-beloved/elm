package example.org;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication15000 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication15000.class,args);
    }
}
