package com.spring.jwt_jpa.entity;

import java.io.Serializable;
import java.sql.*;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "chuyenbay")
public class ChuyenBay implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "maCB", columnDefinition = "varchar(5)")
	private String maCB;

	@Column(name = "gadi", columnDefinition = "varchar(50)")
	private String gaDi;

	@Column(name = "gaden", columnDefinition = "varchar(50)")
	private String gaDen;

	@Column(name = "dodai")
	private int doDai;

	@Column(name = "giodi")
	private Timestamp gioDi;

	@Column(name = "gioden")
	private Timestamp gioDen;

	@Column(name = "chiphi")
	private int chiPhi;

	public String getMaCB() {
		return maCB;
	}

	public void setMaCB(String maCB) {
		this.maCB = maCB;
	}

	public String getGaDi() {
		return gaDi;
	}

	public void setGaDi(String gaDi) {
		this.gaDi = gaDi;
	}

	public String getGaDen() {
		return gaDen;
	}

	public void setGaDen(String gaDen) {
		this.gaDen = gaDen;
	}

	public int getDoDai() {
		return doDai;
	}

	public void setDoDai(int doDai) {
		this.doDai = doDai;
	}

	public Timestamp getGioDi() {
		return gioDi;
	}

	public void setGioDi(Timestamp gioDi) {
		this.gioDi = gioDi;
	}

	public Timestamp getGioDen() {
		return gioDen;
	}

	public void setGioDen(Timestamp gioDen) {
		this.gioDen = gioDen;
	}

	public int getChiPhi() {
		return chiPhi;
	}

	public void setChiPhi(int chiPhi) {
		this.chiPhi = chiPhi;
	}

	public ChuyenBay() {
	}

	public ChuyenBay(String maCB) {
		this.maCB = maCB;
	}

	public ChuyenBay(String macb, String gaDi, String gaDen, int doDai, Timestamp gioDi, Timestamp gioDen, int chiPhi) {
		this.maCB = macb;
		this.gaDi = gaDi;
		this.gaDen = gaDen;
		this.doDai = doDai;
		this.gioDi = gioDi;
		this.gioDen = gioDen;
		this.chiPhi = chiPhi;
	}
}
