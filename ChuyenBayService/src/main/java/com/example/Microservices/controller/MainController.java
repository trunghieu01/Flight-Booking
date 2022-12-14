package com.example.Microservices.controller;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.Microservices.Service.ChuyenBayService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@RestController
@RequestMapping("/chuyenbay")
public class MainController {
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	ChuyenBayService bayService;

	@GetMapping
	public String hello() {
		String html = "";
		html += "<ul>";
		html += " <li><a href='/chuyenbay/showAll'> Tất cả chuyến bay</a></li>";
		html += " <li><a href='/chuyenbay/1'>1. Cho biết các chuyến bay đi Đà Lạt (DAD).</a></li>";
		html += " <li><a href='/chuyenbay/2'>2. Cho biết các chuyến bay có độ dài đường bay nhỏ hơn 10.000km và lớn hơn 8.000km.</a></li>";
		html += " <li><a href='/chuyenbay/3'>3. Cho biết các chuyến bay xuất phát từ Sài Gòn (SGN) đi Ban Mê Thuộc (BMV).</a></li>";
		html += " <li><a href='/chuyenbay/4'>4. Có bao nhiêu chuyến bay xuất phát từ Sài Gòn (SGN).</a></li>";
		html += " <li><a href='/chuyenbay/5'>5. Cho biết các chuyến bay có thể ñược thực hiện bởi máy bay Airbus A320.</a></li>";
		html += " <li><a href='/chuyenbay/6'>6. Giả sử một hành khách muốn đi thẳng từ ga A đến ga B rồi quay trở về ga A. Cho biết các đường bay nào có thể đáp ứng yêu cầu này.</a></li>";
		html += " <li><a href='/chuyenbay/7'>7. Với mỗi ga có chuyến bay xuất phát từ đó cho biết có bao nhiêu chuyến bay khởi hành từ ga đó.</a></li>";
		html += " <li><a href='/chuyenbay/8'>8. Với mỗi ga có chuyến  bay xuất phát từ đó cho biết tổng chi phí phải trả cho phi công lái các chuyến bay khởi hành từ ga đó.</a></li>";
		html += " <li><a href='/chuyenbay/9'>9. Cho biết danh sách các chuyến bay có thể khởi hành trước 12:00</a></li>";
		html += " <li><a href='/chuyenbay/10'>10. Với mỗi địa điểm xuất phát cho biết có bao nhiêu chuyến bay có thể khởi hành trước 12:00.</a></li>";
		html += " <li><a href='/chuyenbay/11'>11.	Tìm các chuyến bay có thể được thực hiện bởi tất cả các loại máy bay Boeing.</a></li>";
		html += " <li><a href='/chuyenbay/add'>11. Thêm 1 chuyến bay.</a></li>";
		html += "</ul>";
		return html;
	}

	int attempt = 1;

	@GetMapping("/test")
//	@CircuitBreaker(name = "uerService", fallbackMethod = "getAllAvailableProducts")
	@Retry(name = "userService", fallbackMethod = "getAllAvailableProducts")
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
	
	public CompletableFuture<String> getFallBack(Exception e) {
		return CompletableFuture.supplyAsync(() -> {
			try {
				return fallBack(e);
			} catch (Throwable e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return null;
		});
	}

	@RequestMapping("/showAll")
	@CircuitBreaker(name = "userService1", fallbackMethod = "getFallBack")
	@TimeLimiter(name = "userService")
	public CompletableFuture<String> getAll(Exception e) {
		return CompletableFuture.supplyAsync(() -> {
			try {
				return bayService.getAll();
			} catch (Throwable e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return null;
		});
	}

	@RequestMapping("/1")
	@CircuitBreaker(name = "userService1", fallbackMethod = "getFallBack")
	@TimeLimiter(name = "userService")
	public CompletableFuture<String> get1(Exception e) {
		return CompletableFuture.supplyAsync(() -> {
			try {
				return bayService.get1();
			} catch (Throwable e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return null;
		});
	}

	@RequestMapping("/2")
	public String get2() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/4", HttpMethod.GET, entity, String.class).getBody();
	}

	@RequestMapping("/3")
	public String get3() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/5", HttpMethod.GET, entity, String.class).getBody();
	}

	@RequestMapping("/4")
	public String get4() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/6", HttpMethod.GET, entity, String.class).getBody();
	}

	@RequestMapping("/5")
	public String get5() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/14", HttpMethod.GET, entity, String.class).getBody();
	}

	@RequestMapping("/6")
	public String get6() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/17", HttpMethod.GET, entity, String.class).getBody();
	}

	@RequestMapping("/7")
	public String get7() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/18", HttpMethod.GET, entity, String.class).getBody();
	}

	@RequestMapping("/8")
	public String get8() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/19", HttpMethod.GET, entity, String.class).getBody();
	}

	@RequestMapping("/9")
	public String get9() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/20", HttpMethod.GET, entity, String.class).getBody();
	}

	@RequestMapping("/10")
	public String get10() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/21", HttpMethod.GET, entity, String.class).getBody();
	}

	@RequestMapping("/11")
	public String get11() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/28", HttpMethod.GET, entity, String.class).getBody();
	}

	@RequestMapping("/add")
	@Retry(name = "userService", fallbackMethod = "fallBack")
	public String get12() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/addchuyenbay", HttpMethod.GET, entity, String.class)
				.getBody();
	}

	@RequestMapping("/delete/{macb}")
	@Retry(name = "userService", fallbackMethod = "fallBack")
	public String get13(@PathVariable String macb) {
		System.out.println(macb);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate
				.exchange("http://localhost:8080/deletechuyenbay/" + macb, HttpMethod.GET, entity, String.class)
				.getBody();
	}

	@RequestMapping("/update/{macb}")
	@Retry(name = "userService", fallbackMethod = "fallBack")
	public String get14(@PathVariable String macb) {
		System.out.println(macb);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate
				.exchange("http://localhost:8080/updatechuyenbay/" + macb, HttpMethod.GET, entity, String.class)
				.getBody();
	}
}
