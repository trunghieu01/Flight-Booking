package com.spring.jwt_jpa.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import com.spring.jwt_jpa.entity.NhanVien;
import com.spring.jwt_jpa.repository.NhanVienRepository;

@Service
public class NhanVienServiceImpl implements NhanVienService {

	@Autowired
	private NhanVienRepository nhanVienRepository;

	@Override
	public void insertNhanVien(List<NhanVien> dsNhanVien) {
		// TODO Auto-generated method stub
		nhanVienRepository.saveAll(dsNhanVien);
	}

	@Override
	public List<NhanVien> findByLuong() {
		// TODO Auto-generated method stub
		return nhanVienRepository.findByLuong(10000);
	}

	@Override
	public String sumLuong() {
		// TODO Auto-generated method stub
		return "Tổng số lương phải trả cho các nhân viên: " + nhanVienRepository.sumLuong();
	}

	@Override
	public List<NhanVien> findNhanVienByLoaiMayBay() {
		// TODO Auto-generated method stub
		return nhanVienRepository.findNhanVienByLoaiMayBay("Boeing%");
	}

	@Override
	public List<NhanVien> findNhanVienByMa() {
		// TODO Auto-generated method stub
		return nhanVienRepository.findNhanVienByMa(747);
	}

	@Override
	public List<NhanVien> findPhiCong() {
		// TODO Auto-generated method stub
		return nhanVienRepository.findPhiCong("Boeing%", "Airbus%");
	}

	@Override
	public List<String> findTenPhiCong() {
		// TODO Auto-generated method stub
		return nhanVienRepository.findTenPhiCong("Boeing%");
	}

	@Override
	public List<String> findMaNhanVienLai3Loai() {
		// TODO Auto-generated method stub
		return nhanVienRepository.findMaNhanVienLai3Loai();
	}

	@Override
	public List<Map<String, Object>> findPhiCongLai3LoaiMBVaTamBayMax() {
		// TODO Auto-generated method stub
		return nhanVienRepository.findPhiCongLai3LoaiMBVaTamBayMax();
	}

	@Override
	public List<Map<String, Object>> findPhiCongvaSoMB() {
		// TODO Auto-generated method stub
		return nhanVienRepository.findPhiCongvaSoMB();
	}

	@Override
	public List<NhanVien> findNhanVien() {
		// TODO Auto-generated method stub
		return nhanVienRepository.findNhanVien();
	}

	@Override
	public String findMaxLuong() {
		// TODO Auto-generated method stub
		return "Mã nhân viên có lương cao nhất: " + nhanVienRepository.findMaxLuong();
	}

	@Override
	public String sumLuongPhiCong() {
		// TODO Auto-generated method stub
		return "Tổng số lương phải trả cho các phi công: " + nhanVienRepository.sumLuongPhiCong();
	}

	@Override
	@CachePut(value = "NhanVien", key="#manv")
	public String deleteOnePhiCong(String manv) {
		nhanVienRepository.deleteById(manv);
		return "Successd";
	}

	@Override
	@CachePut(value = "NhanVien", key="#manv")
	public List<NhanVien> updateOnePhiCong(String manv) {
		NhanVien nv = new NhanVien(manv, "Ta Van Dau", 212156);
		nhanVienRepository.save(nv);
		return nhanVienRepository.findByMaNV(manv);
	}

}
