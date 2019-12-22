package com.ubaidsample.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages={"com.ubaidsample.springboot"})
public class SpringBootRestApiApp {

	public static void main(String[] args) {
		
		SpringApplication.run(SpringBootRestApiApp.class, args);
	}
}