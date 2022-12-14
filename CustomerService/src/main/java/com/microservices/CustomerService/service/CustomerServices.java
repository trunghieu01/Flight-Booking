package com.microservices.CustomerService.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerServices {

	@Autowired
	RestTemplate restTemplate;
	int attempt = 1;

	public String display() throws Exception {
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

	public String getAll() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		String rs = restTemplate.exchange("http://localhost:8080/showAll", HttpMethod.GET, entity, String.class)
				.getBody();
		if (rs.isEmpty()) {
			throw new Exception("Next");
		}
		return rs;
	}
	
	public String addCustomer(String maKH, String pass, String ten) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/customer/new?maKH"+maKH+"&&pass="+pass+"&&ten="+ten, HttpMethod.GET, entity, String.class)
				.getBody();
	}
	
	public String updateCustomer(String maKH, String pass, String ten) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/customer/update?maKH="+maKH+"&&pass="+pass+"&&ten="+ten, HttpMethod.GET, entity, String.class)
				.getBody();
	}
	
	public String deleteCustomer(String maKH) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/customer/delete?maKH="+maKH, HttpMethod.GET, entity, String.class)
				.getBody();
	}
	
	public String ticket(String maKH) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/ticket/"+maKH, HttpMethod.GET, entity, String.class)
				.getBody();
	}
	
	public String ticketUpcomming(String maKH) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/ticket/upcomming/"+maKH, HttpMethod.GET, entity, String.class)
				.getBody();
	}
	
	public String ticketCompleted(String maKH) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/ticket/completed/"+maKH, HttpMethod.GET, entity, String.class)
				.getBody();
	}
	
	public String ticketBooking(String maKH, String maCB) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/ticket/booking?maCB="+maCB+"&&maKH="+maKH, HttpMethod.GET, entity, String.class)
				.getBody();
	}
	
	public String ticketCancel(String maKH, String maCB) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/ticket/cancel?maCB="+maCB+"&&maKH="+maKH, HttpMethod.GET, entity, String.class)
				.getBody();
	}
	
	public String login(String maKH, String maCB) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/login?maCB="+maCB+"&&maKH="+maKH, HttpMethod.GET, entity, String.class)
				.getBody();
	}
}
