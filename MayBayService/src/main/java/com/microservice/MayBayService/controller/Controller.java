package com.microservice.MayBayService.controller;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.microservice.MayBayService.Service.MayBayService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@RestController
@RequestMapping("/maybay")
public class Controller {
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	MayBayService mayBayService;
	
	@GetMapping
	public String hello() {
		String html = "";
		html += "<ul>";
		html += " <li><a href='/maybay/showAll'> Tất cả máy bay.</a></li>";
		html += " <li><a href='/maybay/1'>1. Cho biết các loại máy bay có tầm bay lớn hơn 10,000km.</a></li>";
		html += " <li><a href='/maybay/2'>2. Có bao nhiêu loại máy báy Boeing.</a></li>";
		html += " <li><a href='/maybay/3'>3. Cho biết mã số của các loại máy bay mà nhân viên có họ Nguyễn có thể lái.</a></li>";
		html += " <li><a href='/maybay/4'>4. Cho biết các loại máy bay có thể thực hiện chuyến bay VN280.</a></li>";
		html += " <li><a href='/maybay/5'>5. Với mỗi loại máy bay có phi công lái cho biết mã số, loại máy báy và tổng số phi công có thể lái loại máy bay đó.</a></li>";
		html += "</ul>";
		return html;
	}
	
	int attempt = 1;
	@GetMapping("/getAll")
	@Retry(name = "userService", fallbackMethod = "getAllAvailableProducts")
	public String displayOrders() throws Exception {
		System.out.println("retry method called " + attempt);
		attempt++;
		if(attempt ==1 || attempt ==2 || attempt ==3 || attempt ==4 || attempt ==5 || attempt ==6) {
			throw new Exception("Next");}
		return "Hi: "+ attempt;
	}
	
	public String getAllAvailableProducts(Exception e) {
		attempt = 1;
		return "Failled";
	}
	
	public CompletableFuture<String> getFallBack(Exception e) {
		return CompletableFuture.supplyAsync(() -> {
			try {
				return mayBayService.getAll();
			} catch (Throwable e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return null;
		});
	}
	
	@RequestMapping("/showAll")
	@CircuitBreaker(name ="userService",fallbackMethod = "getFallBack")
	@TimeLimiter(name ="userService")
	public CompletableFuture<String> getAll() throws Exception {
		return CompletableFuture.supplyAsync(() -> {
			try {
				return mayBayService.getAll();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		});
	}
	
	@RequestMapping("/1")
	@CircuitBreaker(name ="userService",fallbackMethod = "getFallBack")
	@TimeLimiter(name ="userService")
	public CompletableFuture<String> get1() {
		return CompletableFuture.supplyAsync(() -> {
			try {
				return mayBayService.getAll();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		});
	}
	
	@RequestMapping("/2")
	@Retry(name = "userService", fallbackMethod = "getAllAvailableProducts")
	public String get2() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/7", HttpMethod.GET, entity, String.class).getBody();
	}
	
	@RequestMapping("/3")
	public String get3() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/11", HttpMethod.GET, entity, String.class).getBody();
	}
	
	@RequestMapping("/4")
	public String get4() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/13", HttpMethod.GET, entity, String.class).getBody();
	}
	
	@RequestMapping("/5")
	public String get5() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/16", HttpMethod.GET, entity, String.class).getBody();
	}
}
