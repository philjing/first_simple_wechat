package com.phil.wechat.model.weather;

import java.util.List;

/**
 * 今天天气 基类
 * 
 * @author fjing
 * 
 */
public class WeatherToday extends Weather {
	private String curTemp;// 当前温度
	private String aqi;// pm值
	private List<WeatherIndex> index;  //指标列表

	public String getCurTemp() {
		return curTemp;
	}

	public void setCurTemp(String curTemp) {
		this.curTemp = curTemp;
	}

	public String getAqi() {
		return aqi;
	}

	public void setAqi(String aqi) {
		this.aqi = aqi;
	}

	public List<WeatherIndex> getIndex() {
		return index;
	}

	public void setIndex(List<WeatherIndex> index) {
		this.index = index;
	}
}
