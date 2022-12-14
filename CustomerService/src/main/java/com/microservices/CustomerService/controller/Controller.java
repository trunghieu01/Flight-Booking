package com.microservices.CustomerService.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.CustomerService.service.CustomerServices;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@RestController
@RequestMapping("/khachhang")
public class Controller {
	@Autowired
	private CustomerServices customerService;
	
	int attempt = 1;

	@GetMapping("/getAll")
	@CircuitBreaker(name = "userService", fallbackMethod = "fallBack")
//	@Retry(name = "userService", fallbackMethod = "getAllAvailableProducts")
	public String displayOrders() throws Exception {
		System.out.println("retry method called " + attempt);
		attempt++;
		if (attempt == 1 || attempt == 2 || attempt == 3 || attempt == 4 || attempt == 5 || attempt == 6) {
			throw new Exception("Next");
		}
		return "Hi: " + attempt;
	}

	public String fallBack(Exception e) {
		attempt = 1;
		return "Failled";
	}

	public CompletableFuture<String> getFallBack(Exception e) {
		return CompletableFuture.supplyAsync(() -> {
			try {
				return "Failled";
			} catch (Throwable e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return null;
		});
	}

	@RequestMapping("/showAll")
	@CircuitBreaker(name = "userService", fallbackMethod = "getFallBack")
	@TimeLimiter(name = "userService")
	public CompletableFuture<String> getAll() throws Exception {
		return CompletableFuture.supplyAsync(() -> {
			try {
				return customerService.getAll();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		});
	}

	@RequestMapping("/new")
	@Retry(name = "userService", fallbackMethod = "fallBack")
	public String add(@RequestParam String maKH, String pass, String ten) {
		return customerService.addCustomer(maKH, pass, ten);
	}
	
	@RequestMapping("/delete")
	@Retry(name = "userService", fallbackMethod = "fallBack")
	public String remove(@RequestParam String maKH) {
		return customerService.deleteCustomer(maKH);
	}

	@RequestMapping("/update")
	@Retry(name = "userService", fallbackMethod = "fallBack")
	public String update(@RequestParam String maKH, String pass, String ten) {
		return customerService.updateCustomer(maKH, pass, ten);
	}
	
	@RequestMapping("/ticket/{maKH}")
	@Retry(name = "userService", fallbackMethod = "fallBack")
	public String ticket(@PathVariable String maKH) {
		return customerService.ticket(maKH);
	}
	
	@RequestMapping("/ticket/upcomming/{maKH}")
	@Retry(name = "userService", fallbackMethod = "fallBack")
	public String ticketUpcomming(@PathVariable String maKH) {
		return customerService.ticketUpcomming(maKH);
	}
	
	@RequestMapping("/ticket/completed/{maKH}")
	@Retry(name = "userService", fallbackMethod = "fallBack")
	public String ticketComplete(@PathVariable String maKH) {
		return customerService.ticketCompleted(maKH);
	}
	
	@RequestMapping("/booking")
	@Retry(name = "userService", fallbackMethod = "fallBack")
	public String booking(@RequestParam String maKH, String maCB) {
		return customerService.ticketBooking(maKH, maCB);
	}
	
	@RequestMapping("/cancel")
	@Retry(name = "userService", fallbackMethod = "fallBack")
	public String cancel(@RequestParam String maKH, String maCB) {
		return customerService.ticketCancel(maKH, maCB);
	}
}
