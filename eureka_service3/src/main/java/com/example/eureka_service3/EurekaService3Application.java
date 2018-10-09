package com.example.eureka_service3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EurekaService3Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaService3Application.class, args);
    }
}
