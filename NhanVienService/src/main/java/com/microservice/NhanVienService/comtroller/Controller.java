package com.microservice.NhanVienService.comtroller;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.microservice.NhanVienService.service.NhanVienService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@RestController
@RequestMapping("/nhanvien")
public class Controller {
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	NhanVienService nhanVienService;

	@GetMapping
	public String hello() {
		String html = "";
		html += "<ul>";
		html += " <li><a href='/nhanvien/showAll'> Tất cả nhân viên.</a></li>";
		html += " <li><a href='/nhanvien/1'>1. Tìm các nhân viên có lương nhỏ hơn 10,000.</a></li>";
		html += " <li><a href='/nhanvien/2'>2. Cho biết tổng số lương phải trả cho các nhân viên.</a></li>";
		html += " <li><a href='/nhanvien/3'>3. Cho biết mã số của các phi công lái máy báy Boeing.</a></li>";
		html += " <li><a href='/nhanvien/4'>4. Cho biết các nhân viên có thể lái máy bay có mã số 747.</a></li>";
		html += " <li><a href='/nhanvien/5'>5. Cho biết mã số của các phi công vừa lái được Boeing vừa lái được Airbus.</a></li>";
		html += " <li><a href='/nhanvien/6'>6. Cho biết tên của các phi công lái máy bay Boeing.</a></li>";
		html += " <li><a href='/nhanvien/7'>7. Cho biết mã số của các phi công chỉ lái được 3 loại máy bay</a></li>";
		html += " <li><a href='/nhanvien/8'>8. Với mỗi phi công có thể lái nhiều hơn 3 loại máy bay, cho biết mã số phi công và tầm bay lớn nhất của các loại máy bay mà phi công đó có thể lái.</a></li>";
		html += " <li><a href='/nhanvien/9'>9. Với mỗi phi công cho biết mã số phi công và tổng số loại máy bay mà phi công đó có thể lái.</a></li>";
		html += " <li><a href='/nhanvien/10'>10.	Tìm các nhân viên không phải là phi công.</a></li>";
		html += " <li><a href='/nhanvien/11'>11.	Cho biết mã số của các nhân viên có lương cao nhất.</a></li>";
		html += " <li><a href='/nhanvien/12'>12.	Cho biết tổng số lương phải trả cho các phi công.</a></li>";
		html += "</ul>";
		return html;
	}

	int attempt = 1;

	@GetMapping("/getAll")
	@CircuitBreaker(name = "userService", fallbackMethod = "getAllAvailableProducts")
//	@Retry(name = "userService", fallbackMethod = "getAllAvailableProducts")
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
				return nhanVienService.getAll();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		});
	}

	@RequestMapping("/1")
	@CircuitBreaker(name = "userService", fallbackMethod = "getFallBack")
	@TimeLimiter(name = "userService")
	public CompletableFuture<String> get1() {
		return CompletableFuture.supplyAsync(nhanVienService::get1);
	}

	@RequestMapping("/2")
	public String get2() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/8", HttpMethod.GET, entity, String.class).getBody();
	}

	@RequestMapping("/3")
	public String get3() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/9", HttpMethod.GET, entity, String.class).getBody();
	}

	@RequestMapping("/4")
	public String get4() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/10", HttpMethod.GET, entity, String.class).getBody();
	}

	@RequestMapping("/5")
	public String get5() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/12", HttpMethod.GET, entity, String.class).getBody();
	}

	@RequestMapping("/6")
	public String get6() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/15", HttpMethod.GET, entity, String.class).getBody();
	}

	@RequestMapping("/7")
	public String get7() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/22", HttpMethod.GET, entity, String.class).getBody();
	}

	@RequestMapping("/8")
	public String get8() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/23", HttpMethod.GET, entity, String.class).getBody();
	}

	@RequestMapping("/9")
	public String get9() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/24", HttpMethod.GET, entity, String.class).getBody();
	}

	@RequestMapping("/10")
	public String get10() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/25", HttpMethod.GET, entity, String.class).getBody();
	}

	@RequestMapping("/11")
	public String get11() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/26", HttpMethod.GET, entity, String.class).getBody();
	}

	@RequestMapping("/12")
	public String get12() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/27", HttpMethod.GET, entity, String.class).getBody();
	}

	@RequestMapping("/add")
	@Retry(name = "userService", fallbackMethod = "getAllAvailableProducts")
	public String get13() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8080/addnhanvien", HttpMethod.GET, entity, String.class)
				.getBody();
	}

	@RequestMapping("/delete/{manv}")
	@Retry(name = "userService", fallbackMethod = "getAllAvailableProducts")
	public String get14(@PathVariable String manv) {
		System.out.println(manv);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate
				.exchange("http://localhost:8080/deletenhanvien/" + manv, HttpMethod.GET, entity, String.class)
				.getBody();
	}

	@RequestMapping("/update/{manv}")
	@Retry(name = "userService", fallbackMethod = "getAllAvailableProducts")
	public String get15(@PathVariable String manv) {
		System.out.println(manv);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate
				.exchange("http://localhost:8080/updatenhanvien/" + manv, HttpMethod.GET, entity, String.class)
				.getBody();
	}
}
