package com.spring.jwt_jpa.services;

import java.util.List;
import java.util.Map;

import com.spring.jwt_jpa.entity.NhanVien;

public interface NhanVienService {
	String deleteOnePhiCong(String manv);

	List<NhanVien> updateOnePhiCong(String manv);

	void insertNhanVien(List<NhanVien> dsNhanVien);

	List<NhanVien> findByLuong();

	String sumLuong();

	List<NhanVien> findNhanVienByLoaiMayBay();

	List<NhanVien> findNhanVienByMa();

	List<NhanVien> findPhiCong();

	List<String> findTenPhiCong();

	List<String> findMaNhanVienLai3Loai();

	List<Map<String, Object>> findPhiCongLai3LoaiMBVaTamBayMax();

	List<Map<String, Object>> findPhiCongvaSoMB();

	List<NhanVien> findNhanVien();

	String findMaxLuong();

	String sumLuongPhiCong();
}
