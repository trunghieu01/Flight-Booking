package com.spring.jwt_jpa.entity;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "maybay")
public class MayBay implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "mamb")
	private int maMB;

	@Column(name = "loai")
	private String loai;

	@Column(name = "tambay")
	private int tamBay;

	public int getMaMB() {
		return maMB;
	}

	public void setMaMB(int maMB) {
		this.maMB = maMB;
	}

	public String getLoai() {
		return loai;
	}

	public void setLoai(String loai) {
		this.loai = loai;
	}

	public int getTamBay() {
		return tamBay;
	}

	public void setTamBay(int tamBay) {
		this.tamBay = tamBay;
	}

	public MayBay(int maMB, String loai, int tamBay) {
		super();
		this.maMB = maMB;
		this.loai = loai;
		this.tamBay = tamBay;
	}

	public MayBay() {
		// TODO Auto-generated constructor stub
	}

	public MayBay(int maMB) {
		super();
		this.maMB = maMB;
	}

}
