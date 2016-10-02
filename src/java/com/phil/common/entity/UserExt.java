package com.phil.common.entity;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class UserExt {
	
	@Column(name="user_id")
	private String userId;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id",updatable=false,insertable=false)
	private User user;
	
	

}
