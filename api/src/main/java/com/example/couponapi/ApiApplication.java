package com.example.couponapi;

import com.example.couponcore.CoreConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(CoreConfiguration.class)
@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "application-core,application-api");
        SpringApplication.run(ApiApplication.class, args);
    }

}
