package com.phil.wechat.model.weather;

/**
 * 天气 基类
 * @author fjing
 * 
 */
public class Weather {
	private String date;// 日期
	private String week; // 星期
	private String fengxiang;// 风向
	private String fengli; // 风力
	private String hightemp;// 最高温度
	private String lowtemp;// 最低温度
	private String type;// 天气状态

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getFengxiang() {
		return fengxiang;
	}

	public void setFengxiang(String fengxiang) {
		this.fengxiang = fengxiang;
	}

	public String getFengli() {
		return fengli;
	}

	public void setFengli(String fengli) {
		this.fengli = fengli;
	}

	public String getHightemp() {
		return hightemp;
	}

	public void setHightemp(String hightemp) {
		this.hightemp = hightemp;
	}

	public String getLowtemp() {
		return lowtemp;
	}

	public void setLowtemp(String lowtemp) {
		this.lowtemp = lowtemp;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
