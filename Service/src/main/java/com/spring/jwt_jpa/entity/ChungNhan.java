package com.spring.jwt_jpa.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "chungnhan")
@IdClass(ChungNhanPK.class)
public class ChungNhan implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MaNV" , nullable = false)
	private NhanVien MaNV;	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaMB", nullable = false)
    private MayBay MaMB;
	public NhanVien getMaNV() {
		return MaNV;
	}
	public void setMaNV(NhanVien maNV) {
		MaNV = maNV;
	}
	public MayBay getMaMB() {
		return MaMB;
	}
	public void setMaMB(MayBay maMB) {
		MaMB = maMB;
	}
	public ChungNhan(NhanVien maNV, MayBay maMB) {
		super();
		MaNV = maNV;
		MaMB = maMB;
	}
	public ChungNhan() {
		super();
		// TODO Auto-generated constructor stub
	}
}
