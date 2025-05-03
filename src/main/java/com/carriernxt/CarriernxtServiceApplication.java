package com.carriernxt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.carriernxt.repository")
@EntityScan(basePackages = "com.carriernxt.model")
public class CarriernxtServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarriernxtServiceApplication.class, args);
	}

} 