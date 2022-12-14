package com.spring.jwt_jpa.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.spring.jwt_jpa.entity.ChuyenBay;
import com.spring.jwt_jpa.repository.ChuyenBayRepository;

@Service
public class ChuyenBayServiceImpl implements ChuyenBayService {

	@Autowired
	private ChuyenBayRepository chuyenBayRepository;
	
	@Override
	@Cacheable(value = "ChuyenBay")
	public List<ChuyenBay> findAll() {
		// TODO Auto-generated method stub
		return chuyenBayRepository.findAll();
	}

	@Override
	public List<ChuyenBay> findByGaDen() {
		// TODO Auto-generated method stub
		return chuyenBayRepository.findByGaDen("DAD");
	}

	@Override
	public List<ChuyenBay> findByDuongbay() {
		// TODO Auto-generated method stub
		return chuyenBayRepository.findByDuongbay(8000, 10000);
	}

	@Override
	public List<ChuyenBay> findBySG_BMT() {
		return chuyenBayRepository.findBySG_BMT("SGN", "BMV");
	}

	@Override
	public String countChuyenBay() {
		// TODO Auto-generated method stub
		String rs = "Số chuyến bay xuất phát từ Sài Gòn (SGN): ";
		return rs+ chuyenBayRepository.countChuyenBay("SGN");
	}

	@Override
	public List<ChuyenBay> findChuyenBay() {
		// TODO Auto-generated method stub
		return chuyenBayRepository.findChuyenBay("Airbus A320");
	}

	@Override
	public List<ChuyenBay> findDiVe() {
		// TODO Auto-generated method stub
		return chuyenBayRepository.findDiVe();
	}

	@Override
	public List<Map<String, Object>> countGadi() {
		// TODO Auto-generated method stub 
		return chuyenBayRepository.countGadi();
	}

	@Override
	public List<Map<String, Object>> sumChiPhi() {
		// TODO Auto-generated method stub
		return chuyenBayRepository.sumChiPhi();
	}

	@Override
	public List<ChuyenBay> findByGiodi() {
		// TODO Auto-generated method stub
		return chuyenBayRepository.findByGiodi(Timestamp.valueOf("2023-01-12 10:30:00"));
	}

	@Override
	public List<Map<String, Object>> countChuyenBayTruoc12h() {
		return chuyenBayRepository.countChuyenBayTruoc12h();
	}

	@Override
	public List<ChuyenBay> findChuyenBayBoeing() {
		// TODO Auto-generated method stub 
		return chuyenBayRepository.findChuyenBayBoeing("Boeing%");
	}

	@Override
	public void insertChuyenBay(List<ChuyenBay> dsChuyenBay) {
		// TODO Auto-generated method stub
		chuyenBayRepository.saveAll(dsChuyenBay);
	}

	@Override
	@CachePut(value = "ChuyenBay", key="#macb")
	public List<ChuyenBay> updateOneChuyenBay(String macb) {
		ChuyenBay cb = new ChuyenBay(macb, "HAN", "PXU", 395, Timestamp.valueOf("2023-01-12 10:30:00"), Timestamp.valueOf("2023-01-12 10:30:00"),123);
		chuyenBayRepository.save(cb);
		return chuyenBayRepository.findByMaCB(macb);
	}

	@Override
	@CacheEvict(value = "ChuyenBay", key="#macb")
	public String deleteOneChuyenBay(String macb) {
		chuyenBayRepository.deleteById(macb);
		return "Finish!";
	}

}
