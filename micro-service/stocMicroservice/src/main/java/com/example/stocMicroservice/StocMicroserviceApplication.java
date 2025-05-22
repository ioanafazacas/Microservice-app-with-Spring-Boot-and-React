package com.example.stocMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class StocMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StocMicroserviceApplication.class, args);
	}

}
