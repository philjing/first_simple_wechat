package com.phil.wechat.model.weather;

/**
 * 历史天气 基类
 * @author fjing
 *
 */
public class WeatherHistory extends Weather{
	
	private String aqi;  //pm值

	public String getAqi() {
		return aqi;
	}

	public void setAqi(String aqi) {
		this.aqi = aqi;
	}
}
