package com.spring.jwt_jpa.entity;

import java.io.Serializable;
import javax.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ve")
@IdClass(VePK.class)
public class Ve implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MaCB" , nullable = false)
	private ChuyenBay maCB;
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MaKH" , nullable = false)
	private KhachHang maKH;
}
