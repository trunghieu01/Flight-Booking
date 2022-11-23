package com.spring.jwt_jpa.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.jwt_jpa.entity.MayBay;
import com.spring.jwt_jpa.repository.MayBayRepository;

@Service
public class MayBayServiceImpl implements MayBayService {

	@Autowired
    private MayBayRepository mayBayRepository;

	@Override
	public List<MayBay> findByTamBay() {
		// TODO Auto-generated method stub
		return mayBayRepository.findByTambay(10000);
	}

	@Override
	public String countLoaiMB() {
		// TODO Auto-generated method stub
		return "Số loại máy báy Boeing: " + mayBayRepository.countLoaiMB("Boeing");
	}

	@Override
	public List<String> findByHo() {
		// TODO Auto-generated method stub
		return mayBayRepository.findByHo("Nguyen%");
	}

	@Override
	public List<String> findLoaiMayBay() {
		// TODO Auto-generated method stub
		return mayBayRepository.findLoaiMayBay("VN280");
	}

	@Override
	public List<Map<String, Object>> findMayBay() {
		// TODO Auto-generated method stub
		return mayBayRepository.findMayBay();
	}

	@Override
	public void insertMayBay(List<MayBay> dsMayBay) {
		// TODO Auto-generated method stub
		
	}
}