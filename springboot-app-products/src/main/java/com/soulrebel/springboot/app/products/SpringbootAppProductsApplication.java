package com.soulrebel.springboot.app.products;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SpringbootAppProductsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAppProductsApplication.class, args);
    }

}
