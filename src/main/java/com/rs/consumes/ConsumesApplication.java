package com.rs.consumes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ConsumesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumesApplication.class, args);
	}

}
