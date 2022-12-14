package com.spring.jwt_jpa.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.spring.jwt_jpa.entity.ChuyenBay;
import com.spring.jwt_jpa.entity.KhachHang;

public interface KhachHangRepositoty extends JpaRepository<KhachHang, String>{
	
	@Query(value = "SELECT * FROM khachhang  WHERE MaKH = ?1 and Pass = ?2", nativeQuery = true)
	KhachHang login(String maKH, String pass);

	@Query("select cb from KhachHang k, Ve v, ChuyenBay cb where k.maKH = ?1 and k.maKH = v.maKH and v.maCB = cb.maCB")
	List<ChuyenBay> findVe(String maKH);
	
	@Query("select cb from KhachHang k, Ve v, ChuyenBay cb where k.maKH = ?1 and k.maKH = v.maKH and v.maCB = cb.maCB and cb.gioDi > CURRENT_TIMESTAMP()")
	List<ChuyenBay> findVeSapDi(String maKH);
	
	@Query("select cb from KhachHang k, Ve v, ChuyenBay cb where k.maKH = ?1 and k.maKH = v.maKH and v.maCB = cb.maCB and cb.gioDi <= CURRENT_TIMESTAMP()")
	List<ChuyenBay> findVeDaDi(String maKH);
}
