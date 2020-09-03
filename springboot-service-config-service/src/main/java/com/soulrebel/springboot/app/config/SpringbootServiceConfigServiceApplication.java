package com.soulrebel.springboot.app.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class SpringbootServiceConfigServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServiceConfigServiceApplication.class, args);
	}

}
