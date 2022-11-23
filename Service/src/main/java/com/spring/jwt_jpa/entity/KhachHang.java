package com.spring.jwt_jpa.entity;

import java.io.Serializable;

import javax.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "khachhang")
public class KhachHang implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="maKH", columnDefinition = "varchar(16)")
	private String maKH;
	
    @Column(name="pass", columnDefinition = "varchar(50)")
	private String pass;
    
    @Column(name="tenKH", columnDefinition = "varchar(50)")
	private String tenKH;
    
    public KhachHang(String maKH) {
    	this.maKH = maKH;
    }
}
