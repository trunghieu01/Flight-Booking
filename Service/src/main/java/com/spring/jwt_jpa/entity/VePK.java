package com.spring.jwt_jpa.entity;

import java.io.Serializable;
import javax.persistence.*;
import lombok.*;

@Data
@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class VePK implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String maCB;
	private String maKH;

}
