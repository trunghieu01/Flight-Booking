package com.spring.jwt_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.jwt_jpa.entity.ChungNhan;
@Repository
public interface ChungNhanRepository extends JpaRepository<ChungNhan, String>{
}
