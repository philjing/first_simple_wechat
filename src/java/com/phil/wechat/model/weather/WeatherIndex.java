package com.phil.wechat.model.weather;

/**
 * 天气 指标
 * @author fjing
 *
 */
public class WeatherIndex {	
	private String name;//指数指标1名称
	private String code;//指标编码  
	private String index; //等级
	private String details;//描述
	private String otherName;//其它信息
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getOtherName() {
		return otherName;
	}
	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}
}
