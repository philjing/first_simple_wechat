package com.phil.wechat.model.message.req;

/**
 * 地理位置消息
 * @author fjing
 *
 */
public class LocationMessage extends BaseReqMessage {
	
	//地理位置纬度
	private String Location_X;
	//地理位置经度
	private String Location_Y;
	//地图缩放大小
	private String Scale;
	//地理位置消息
	private String Labe;
	
	public String getLocation_X() {
		return Location_X;
	}
	public void setLocation_X(String location_X) {
		Location_X = location_X;
	}
	public String getLocation_Y() {
		return Location_Y;
	}
	public void setLocation_Y(String location_Y) {
		Location_Y = location_Y;
	}
	public String getScale() {
		return Scale;
	}
	public void setScale(String scale) {
		Scale = scale;
	}
	public String getLabe() {
		return Labe;
	}
	public void setLabe(String labe) {
		Labe = labe;
	}
}
