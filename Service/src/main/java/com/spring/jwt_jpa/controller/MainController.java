package com.spring.jwt_jpa.controller;

import java.sql.*;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.*;
import org.springframework.cache.annotation.*;
import org.springframework.web.bind.annotation.*;
import com.spring.jwt_jpa.entity.*;
import com.spring.jwt_jpa.repository.*;
import com.spring.jwt_jpa.services.*;

@RestController
@RequestMapping("/")
public class MainController {
	@Autowired
	private VeRepository veRepository;
	@Autowired
	private KhachHangRepositoty khachHangRepositoty;
	@Autowired
	private ChungNhanRepository chungNhanRepository;
	@Autowired
	private ChuyenBayRepository chuyenBayRepository;
	@Autowired
	private ChuyenBayService chuyenBayService;
	@Autowired
	private MayBayRepository mayBayRepository;
	@Autowired
	private MayBayService mayBayService;
	@Autowired
	private NhanVienRepository nhanVienRepository;
	@Autowired
	private NhanVienService nhanVienService;

	@ResponseBody
	@GetMapping("/")
	public String home() {
		String html = "";
		html += "<ul>";
		html += " <li><a href='/showAll'>Show All Chuyến bay</a></li>";
		html += " <li><a href='/maybay'>Show All Máy bay</a></li>";
		html += " <li><a href='/nhanvien'>Show All Nhân viên</a></li>";
		html += " <li><a href='/1'>1. Cho biết các chuyến bay đi Đà Lạt (DAD).</a></li>";
		html += " <li><a href='/2'>2. Cho biết các loại máy bay có tầm bay lớn hơn 10,000km.</a></li>";
		html += " <li><a href='/3'>3. Tìm các nhân viên có lương nhỏ hơn 10,000.</a></li>";
		html += " <li><a href='/4'>4. Cho biết các chuyến bay có độ dài đường bay nhỏ hơn 10.000km và lớn hơn 8.000km.</a></li>";
		html += " <li><a href='/5'>5. Cho biết các chuyến bay xuất phát từ Sài Gòn (SGN) đi Ban Mê Thuộc (BMV).</a></li>";
		html += " <li><a href='/6'>6. Có bao nhiêu chuyến bay xuất phát từ Sài Gòn (SGN).</a></li>";
		html += " <li><a href='/7'>7. Có bao nhiêu loại máy báy Boeing.</a></li>";
		html += " <li><a href='/8'>8. Cho biết tổng số lương phải trả cho các nhân viên.</a></li>";
		html += " <li><a href='/9'>9. Cho biết mã số của các phi công lái máy báy Boeing.</a></li>";
		html += " <li><a href='/10'>10. Cho biết các nhân viên có thể lái máy bay có mã số 747.</a></li>";
		html += " <li><a href='/11'>11.	Cho biết mã số của các loại máy bay mà nhân viên có họ Nguyễn có thể lái.</a></li>";
		html += " <li><a href='/12'>12.	Cho biết mã số của các phi công vừa lái được Boeing vừa lái được Airbus.</a></li>";
		html += " <li><a href='/13'>13.	Cho biết các loại máy bay có thể thực hiện chuyến bay VN280.</a></li>";
		html += " <li><a href='/14'>14.	Cho biết các chuyến bay có thể ñược thực hiện bởi máy bay Airbus A320.</a></li>";
		html += " <li><a href='/15'>15. Cho biết tên của các phi công lái máy bay Boeing.</a></li>";
		html += " <li><a href='/16'>16.	Với mỗi loại máy bay có phi công lái cho biết mã số, loại máy báy và tổng số phi công có thể lái loại máy bay đó.</a></li>";
		html += " <li><a href='/17'>17.	Giả sử một hành khách muốn đi thẳng từ ga A đến ga B rồi quay trở về ga A. Cho biết các đường bay nào có thể đáp ứng yêu cầu này.</a></li>";
		html += " <li><a href='/18'>18. Với mỗi ga có chuyến bay xuất phát từ đó cho biết có bao nhiêu chuyến bay khởi hành từ ga đó.</a></li>";
		html += " <li><a href='/19'>19.	Với mỗi ga có chuyến  bay xuất phát từ đó cho biết tổng chi phí phải trả cho phi công lái các chuyến bay khởi hành từ ga đó.</a></li>";
		html += " <li><a href='/20'>20.	Cho biết danh sách các chuyến bay có thể khởi hành trước 12:00</a></li>";
		html += " <li><a href='/21'>21. Với mỗi địa điểm xuất phát cho biết có bao nhiêu chuyến bay có thể khởi hành trước 12:00.</a></li>";
		html += " <li><a href='/22'>22.	Cho biết mã số của các phi công chỉ lái được 3 loại máy bay</a></li>";
		html += " <li><a href='/23'>23.	Với mỗi phi công có thể lái nhiều hơn 3 loại máy bay, cho biết mã số phi công và tầm bay lớn nhất của các loại máy bay mà phi công đó có thể lái.</a></li>";
		html += " <li><a href='/24'>24.	Với mỗi phi công cho biết mã số phi công và tổng số loại máy bay mà phi công đó có thể lái.</a></li>";
		html += " <li><a href='/25'>25.	Tìm các nhân viên không phải là phi công.</a></li>";
		html += " <li><a href='/26'>26.	Cho biết mã số của các nhân viên có lương cao nhất.</a></li>";
		html += " <li><a href='/27'>27.	Cho biết tổng số lương phải trả cho các phi công.</a></li>";
		html += " <li><a href='/28'>28.	Tìm các chuyến bay có thể được thực hiện bởi tất cả các loại máy bay Boeing.</a></li>";
		html += "</ul>";
		return html;
	}

	@GetMapping("/showAll")
//	@Cacheable(value = "ChuyenBay")
	public List<ChuyenBay> showAll() {
		return chuyenBayRepository.findAll();
	}

	@GetMapping("/maybay")
	@Cacheable(value = "MayBay")
	public List<MayBay> mayBay() {
		return mayBayRepository.findAll();
	}

	@GetMapping("/nhanvien")
	@Cacheable(value = "NhanVien")
	public List<NhanVien> nhanVien() {
		return nhanVienRepository.findAll();
	}

	@ResponseBody
	@RequestMapping("/1")
	public List<ChuyenBay> showGaDen() {
		return chuyenBayService.findByGaDen();
	}

	@ResponseBody
	@RequestMapping("/2")
	public List<MayBay> showTamBay() {
		return mayBayService.findByTamBay();
	}

	@ResponseBody
	@RequestMapping("/3")
	public List<NhanVien> showLuong() {
		return nhanVienService.findByLuong();
	}

	@ResponseBody
	@RequestMapping("/4")
	public List<ChuyenBay> showDuongBay() {
		return chuyenBayService.findByDuongbay();
	}

	@ResponseBody
	@RequestMapping("/5")
	public List<ChuyenBay> showChuyenBaySG_BMT() {
		return chuyenBayService.findBySG_BMT();
	}

	@ResponseBody
	@RequestMapping("/6")
	public String countChuyenBay() {
		return chuyenBayService.countChuyenBay();
	}

	@ResponseBody
	@RequestMapping("/7")
	public String countMayBay() {
		return mayBayService.countLoaiMB();
	}

	@ResponseBody
	@RequestMapping("/8")
	public String sumLuong() {
		return nhanVienService.sumLuong();
	}

	@ResponseBody
	@RequestMapping("/9")
	public List<NhanVien> findPhiCong() {
		return nhanVienService.findNhanVienByLoaiMayBay();
	}

	@ResponseBody
	@RequestMapping("/10")
	public List<NhanVien> findPhiCong1() {
		return nhanVienService.findNhanVienByMa();
	}

	@ResponseBody
	@RequestMapping("/11")
	public List<String> findMayBay() {
		return mayBayService.findByHo();
	}

	@ResponseBody
	@RequestMapping("/12")
	public List<NhanVien> findPhiCong2() {
		return nhanVienService.findPhiCong();
	}

	@ResponseBody
	@RequestMapping("/13")
	public List<String> findMayBay1() {
		return mayBayService.findLoaiMayBay();
	}

	@ResponseBody
	@RequestMapping("/14")
	public List<ChuyenBay> findMayBay2() {
		return chuyenBayService.findChuyenBay();
	}

	@ResponseBody
	@RequestMapping("/15")
	public List<String> findPhiCong3() {
		return nhanVienService.findTenPhiCong();
	}

	@ResponseBody
	@RequestMapping("/16")
	public List<Map<String, Object>> findMayBay3() {
		return mayBayService.findMayBay();
	}

	@ResponseBody
	@RequestMapping("/17")
	public List<ChuyenBay> findDuongBay() {
		return chuyenBayService.findDiVe();
	}

	@ResponseBody
	@RequestMapping("/18")
	public List<Map<String, Object>> findChuyenBay() {
		return chuyenBayService.countGadi();
	}

	@ResponseBody
	@RequestMapping("/19")
	public List<Map<String, Object>> findChiPhi() {
		return chuyenBayService.sumChiPhi();
	}

	@ResponseBody
	@RequestMapping("/20")
	public List<ChuyenBay> findChuyenBay1() {
		return chuyenBayService.findByGiodi();
	}

	@ResponseBody
	@RequestMapping("/21")
	public List<Map<String, Object>> findChuyenBay2() {
		return chuyenBayService.countChuyenBayTruoc12h();
	}

	@ResponseBody
	@RequestMapping("/22")
	public List<String> findPhiCong4() {
		return nhanVienService.findMaNhanVienLai3Loai();
	}

	@ResponseBody
	@RequestMapping("/23")
	public List<Map<String, Object>> findPhiCong_TamBay() {
		return nhanVienService.findPhiCongLai3LoaiMBVaTamBayMax();
	}

	@ResponseBody
	@RequestMapping("/24")
	public List<Map<String, Object>> findPhiCong5() {
		return nhanVienService.findPhiCongvaSoMB();
	}

	@ResponseBody
	@RequestMapping("/25")
	public List<NhanVien> findNhanVien() {
		return nhanVienService.findNhanVien();
	}

	@ResponseBody
	@RequestMapping("/26")
	public String findNhanVien1() {
		return nhanVienService.findMaxLuong();
	}

	@ResponseBody
	@RequestMapping("/27")
	public String findSumLuong() {
		return nhanVienService.sumLuongPhiCong();
	}

	@ResponseBody
	@RequestMapping("/28")
	public List<ChuyenBay> findChuyenBay3() {
		return chuyenBayService.findChuyenBayBoeing();
	}

	@ResponseBody
	@RequestMapping("/addchuyenbay")
	public ChuyenBay insertChuyenBay() {
		ChuyenBay cb = new ChuyenBay("VN742", "HAN", "PXU", 395, Timestamp.valueOf("2023-01-12 10:30:00"), Timestamp.valueOf("2023-01-12 12:30:00"),
				125);
		chuyenBayRepository.save(cb);
		return cb;
	}

	@ResponseBody
	@RequestMapping("/deletechuyenbay/{macb}")
	public String deleteOneChuyenBay(@PathVariable String macb) {
		return chuyenBayService.deleteOneChuyenBay(macb);
	}

	@ResponseBody
	@RequestMapping("/updatechuyenbay/{macb}")
	public List<ChuyenBay> updateOneChuyenBay(@PathVariable String macb) {
		return chuyenBayService.updateOneChuyenBay(macb);
	}

	@ResponseBody
	@RequestMapping("/addphicong")
	public NhanVien insertPhiCong() {
		NhanVien nv = new NhanVien("310454123", "Ta Van Dau", 212156);
		nhanVienRepository.save(nv);
		return nv;
	}

	@ResponseBody
	@RequestMapping("/deletenhanvien/{manv}")
	public String deleteOnePhiCong(@PathVariable String manv) {
		return nhanVienService.deleteOnePhiCong(manv);
	}

	@ResponseBody
	@RequestMapping("/updatenhanvien/{manv}")
	public List<NhanVien> updatePhiCong(@PathVariable String manv) {
		return nhanVienService.updateOnePhiCong(manv);
	}

	@ResponseBody
	@RequestMapping("/addchungnhan")
	public ChungNhan addChungNhan() {
		ChungNhan cn = new ChungNhan(new NhanVien("310454123"), new MayBay(154));
		chungNhanRepository.save(cn);
		return cn;
	}

	@ResponseBody
	@RequestMapping("/deletechungnhan")
	public void removeChungNhan() {
		ChungNhan cn = new ChungNhan(new NhanVien("310454123"), new MayBay(154));
		chungNhanRepository.delete(cn);
	}

	@ResponseBody
	@RequestMapping("/customer")
	public List<KhachHang> getAllCustomer() {
		return khachHangRepositoty.findAll();
	}

	@ResponseBody
	@RequestMapping("/login")
	public String login(@RequestParam String maKH, String pass) {
		String k = khachHangRepositoty.findById(maKH).map(KhachHang::getPass).orElse(null);
		if (k.equalsIgnoreCase(pass))
			return "Successful!";
		return "Unsuccessful!";
	}

	@ResponseBody
	@RequestMapping("/customer/new")
	public KhachHang createCustomer(@RequestParam String maKH, String pass, String ten) {
		KhachHang k = new KhachHang(maKH, pass, ten);
		khachHangRepositoty.save(k);
		return k;
	}

	@ResponseBody
	@RequestMapping("/customer/update")
	@Cacheable(value = "KhachHang", key = "#maKH")
	public KhachHang updateCustomer(@RequestParam String maKH, String pass, String ten) {
		KhachHang k = new KhachHang(maKH, pass, ten);
		khachHangRepositoty.save(k);
		return k;
	}

	@ResponseBody
	@RequestMapping("/customer/delete")
	@CacheEvict(value = "KhachHang", key = "#maKH")
	public String removeCustomer(@RequestParam String maKH) {
		khachHangRepositoty.deleteById(maKH);
		return "Deleted";
	}

	@ResponseBody
	@RequestMapping("/ticket/{id}")
	public List<ChuyenBay> getVe(@PathVariable String id) {
		return khachHangRepositoty.findVe(id);
	}

	@ResponseBody
	@RequestMapping("/ticket/upcomming/{id}")
	public List<ChuyenBay> getVeSapDi(@PathVariable String id) {
		return khachHangRepositoty.findVeSapDi(id);
	}

	@ResponseBody
	@RequestMapping("/ticket/completed/{id}")
	public List<ChuyenBay> getVeDaDi(@PathVariable String id) {
		return khachHangRepositoty.findVeDaDi(id);
	}

	@ResponseBody
	@GetMapping("/ticket/booking")
	public Ve addTicket(@RequestParam String maCB, String maKH) {
		Ve v = new Ve(new ChuyenBay(maCB), new KhachHang(maKH));
		veRepository.save(v);
		return v;
	}

//	@ResponseBody
//	@RequestMapping(value = "/ticket/cancel", method = RequestMethod.POST)
//	public String cancelTicket(@RequestBody Map<String, String> s) {
//		veRepository.delete(new Ve(new ChuyenBay(s.get("maCB")), new KhachHang(s.get("maKH"))));
//		return "Cancelled";
//	}

	@ResponseBody
	@GetMapping("/ticket/cancel")
	public String cancelTicket(@RequestParam String maCB, String maKH) {
		veRepository.delete(new Ve(new ChuyenBay(maCB), new KhachHang(maKH)));
		return "Cancelled";
	}
}
