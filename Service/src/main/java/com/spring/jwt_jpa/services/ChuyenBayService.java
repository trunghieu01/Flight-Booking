package com.spring.jwt_jpa.services;

import java.util.List;
import java.util.Map;

import com.spring.jwt_jpa.entity.ChuyenBay;

public interface ChuyenBayService {
	void insertChuyenBay(List<ChuyenBay>dsChuyenBay);
	List<ChuyenBay> updateOneChuyenBay(String macb);
	String deleteOneChuyenBay(String macb);
	List<ChuyenBay> findAll();

	List<ChuyenBay> findByGaDen();

	List<ChuyenBay> findByDuongbay();

	List<ChuyenBay> findBySG_BMT();

	String countChuyenBay();

	List<ChuyenBay> findChuyenBay();

	List<ChuyenBay> findDiVe();

	List<Map<String, Object>> countGadi();

	List<Map<String, Object>> sumChiPhi();

	List<ChuyenBay> findByGiodi();

	List<Map<String, Object>> countChuyenBayTruoc12h();

	List<ChuyenBay> findChuyenBayBoeing();
}
