package com.example.Microservices.Service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChuyenBayService {
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

	public String getAllAvailableProducts(Exception e) {
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

	public String get1() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/1", HttpMethod.GET, entity, String.class).getBody();
	}

	public String get2() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/4", HttpMethod.GET, entity, String.class).getBody();
	}

	public String get3() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/5", HttpMethod.GET, entity, String.class).getBody();
	}

	public String get4() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/6", HttpMethod.GET, entity, String.class).getBody();
	}

	public String get5() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/14", HttpMethod.GET, entity, String.class).getBody();
	}

	public String get6() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/17", HttpMethod.GET, entity, String.class).getBody();
	}

	public String get7() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/18", HttpMethod.GET, entity, String.class).getBody();
	}

	public String get8() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/19", HttpMethod.GET, entity, String.class).getBody();
	}

	public String get9() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/20", HttpMethod.GET, entity, String.class).getBody();
	}

	public String get10() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/21", HttpMethod.GET, entity, String.class).getBody();
	}

	public String get11() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/28", HttpMethod.GET, entity, String.class).getBody();
	}

	public String get12() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/addchuyenbay", HttpMethod.GET, entity, String.class)
				.getBody();
	}

	public String get13(String macb) {
		System.out.println(macb);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate
				.exchange("http://localhost:8080/deletechuyenbay/" + macb, HttpMethod.GET, entity, String.class)
				.getBody();
	}

	public String get14(String macb) {
		System.out.println(macb);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate
				.exchange("http://localhost:8080/updatechuyenbay/" + macb, HttpMethod.GET, entity, String.class)
				.getBody();
	}
}
