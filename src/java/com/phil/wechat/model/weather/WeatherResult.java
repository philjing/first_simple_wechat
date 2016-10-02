package com.phil.wechat.model.weather;

/**
 * 天气结果(JSON转java对象)
 * @author fjing
 *
 */
public class WeatherResult {
	
	private String errNum;
	
	private String errMsg;
	
	private WeatherData retData;

	public String getErrNum() {
		return errNum;
	}

	public void setErrNum(String errNum) {
		this.errNum = errNum;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public WeatherData getRetData() {
		return retData;
	}

	public void setRetData(WeatherData retData) {
		this.retData = retData;
	}
}
