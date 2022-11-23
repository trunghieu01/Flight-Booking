package com.spring.jwt_jpa.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "nhanvien")
public class NhanVien implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="manv")
	private String maNV;
	
	@Column(name="ten")
	private String ten;
	
	@Column(name="luong")
	private int luong;
	
	public String getMaNV() {
		return maNV;
	}
	public void setManV(String manv) {
		this.maNV = manv;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public int getLuong() {
		return luong;
	}
	public void setLuong(int luong) {
		this.luong = luong;
	}
	public NhanVien(String manv, String ten, int luong) {
		super();
		this.maNV = manv;
		this.ten = ten;
		this.luong = luong;
	}
	public NhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NhanVien(String maNV) {
		super();
		this.maNV = maNV;
	}
	
	
}
