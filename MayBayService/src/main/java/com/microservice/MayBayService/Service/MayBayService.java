package com.microservice.MayBayService.Service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MayBayService {
	@Autowired
	RestTemplate restTemplate;

	int attempt = 1;

	public String displayOrders() throws Exception {
		System.out.println("retry method called " + attempt);
		attempt++;
		if (attempt == 1 || attempt == 2 || attempt == 3 || attempt == 4 || attempt == 5 || attempt == 6) {
			throw new Exception("Next");
		}
		return "Hi: " + attempt;
	}

	public String getAllAvailableProducts(Exception e) {
		attempt = 1;
		return "Failled";
	}

	public String getAll() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		String rs = restTemplate.exchange("http://localhost:8080/maybay", HttpMethod.GET, entity, String.class)
				.getBody();
		if (rs.isEmpty())
			throw new Exception("Failled");
		return rs;
	}

	public String get1() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/2", HttpMethod.GET, entity, String.class).getBody();
	}

	public String get2() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/7", HttpMethod.GET, entity, String.class).getBody();
	}

	public String get3() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/11", HttpMethod.GET, entity, String.class).getBody();
	}

	public String get4() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/13", HttpMethod.GET, entity, String.class).getBody();
	}

	public String get5() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/16", HttpMethod.GET, entity, String.class).getBody();
	}
}
