package com.phil.common.entity.dict;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 类别
 * @author fjing
 * @date  2016-10-13
 */
@Entity
@Table(name = "web_category")
public class WebCategory {
	
	/**
	 * 编码
	 */
	private String code;

}
