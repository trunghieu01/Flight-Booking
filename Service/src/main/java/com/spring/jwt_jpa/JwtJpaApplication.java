package com.spring.jwt_jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching 
public class JwtJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtJpaApplication.class, args);
		System.out.println("Finished");
	}

}