package com.spring.jwt_jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.jwt_jpa.entity.ChungNhan;
import com.spring.jwt_jpa.repository.ChungNhanRepository;

@Service
public class ChungNhanServiceImpl implements ChungNhanService{
	@Autowired
    private ChungNhanRepository chungNhanRepository;

	@Override
	public void insertChungNhan(List<ChungNhan> dsChungNhan) {
		// TODO Auto-generated method stub
		chungNhanRepository.saveAll(dsChungNhan);
	}
}

