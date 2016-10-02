package com.phil.wechat.model.weather;

import java.util.List;

/**
 * 天气
 * @author fjing
 * 
 */
public class WeatherData {
	private String city; // 城市
	private String cityid; // 城市编码
	private WeatherToday today;
	private List<WeatherForecast> forecast;// 未来四天天气情况
	private List<WeatherHistory> history; // 之前两天天气情况

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCityid() {
		return cityid;
	}

	public void setCityid(String cityid) {
		this.cityid = cityid;
	}

	public WeatherToday getToday() {
		return today;
	}

	public void setToday(WeatherToday today) {
		this.today = today;
	}

	public List<WeatherForecast> getForecast() {
		return forecast;
	}

	public void setForecast(List<WeatherForecast> forecast) {
		this.forecast = forecast;
	}

	public List<WeatherHistory> getHistory() {
		return history;
	}

	public void setHistory(List<WeatherHistory> history) {
		this.history = history;
	}

}
