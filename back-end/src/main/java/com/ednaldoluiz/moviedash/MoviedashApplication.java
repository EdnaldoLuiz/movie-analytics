package com.ednaldoluiz.moviedash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class MoviedashApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviedashApplication.class, args);
	}
}
