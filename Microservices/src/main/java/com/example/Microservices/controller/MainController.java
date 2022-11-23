package com.example.Microservices.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/services1")
public class MainController {
	@Autowired
	RestTemplate restTemplate;

	@GetMapping
	public String hello() {
		return "Hello!";
	}
	
	@RequestMapping("/showAll")
	public String getProductList() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/showAll", HttpMethod.GET, entity, String.class).getBody();
	}
	
	@RequestMapping("/1")
	public String get1() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/1", HttpMethod.GET, entity, String.class).getBody();
	}
}
