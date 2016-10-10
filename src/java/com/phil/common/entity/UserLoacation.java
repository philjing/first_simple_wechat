package com.phil.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.phil.common.entity.base.IdEntity;

/**
 * 用户地理位置
 * 
 * @author fjing
 */
@Entity
@Table(name="user_location")
public class UserLoacation extends IdEntity {

	/**
	 * 用户的微信ID
	 */
	private String openId;

	/**
	 * 经度
	 */
	private String lng;
	/**
	 * 纬度
	 */
	private String lat;
	/**
	 * 百度地图经度
	 */
	private String bd09Lng;
	/**
	 * 百度地图纬度
	 */
	private String bd09Lat;
	
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getBd09Lng() {
		return bd09Lng;
	}
	public void setBd09Lng(String bd09Lng) {
		this.bd09Lng = bd09Lng;
	}
	public String getBd09Lat() {
		return bd09Lat;
	}
	public void setBd09Lat(String bd09Lat) {
		this.bd09Lat = bd09Lat;
	}
	
}
