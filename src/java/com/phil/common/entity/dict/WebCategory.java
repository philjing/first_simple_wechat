package com.phil.common.entity.dict;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.phil.common.entity.base.IdEntity;

/**
 * 类别
 * @author fjing
 * @date  2016-10-13
 */
@Entity
@Table(name = "web_category")
public class WebCategory extends IdEntity {
	
	/**
	 * 编码
	 */
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	

}
