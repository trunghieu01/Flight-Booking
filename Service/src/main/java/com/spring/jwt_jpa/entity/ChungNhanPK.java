package com.spring.jwt_jpa.entity;

import javax.persistence.Embeddable;

import lombok.Data;

import java.io.Serializable;
@Data
@Embeddable
public class ChungNhanPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String MaNV;
	private int MaMB;

	public ChungNhanPK() {
	}

	public String getMaNV() {
		return MaNV;
	}

	public void setMaNV(String maNV) {
		MaNV = maNV;
	}

	public int getMaMB() {
		return MaMB;
	}

	public void setMaMB(int maMB) {
		MaMB = maMB;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
}