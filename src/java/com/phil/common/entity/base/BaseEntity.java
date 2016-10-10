package com.phil.common.entity.base;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * @author fjing
 * @date  2016-10-13
 */
@MappedSuperclass
public class BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1854851533745515987L;
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="id",nullable=false,length=32)  
	protected String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
	
	
	

}
