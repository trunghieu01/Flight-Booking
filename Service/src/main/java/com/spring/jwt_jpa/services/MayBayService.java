package com.spring.jwt_jpa.services;

import java.util.List;
import java.util.Map;

import com.spring.jwt_jpa.entity.MayBay;

public interface MayBayService {
	void insertMayBay(List<MayBay> dsMayBay);
	
	List<MayBay> findByTamBay();

	String countLoaiMB();

	List<String> findByHo();

	List<String> findLoaiMayBay();

	List<Map<String, Object>> findMayBay();
}