package com.phil.wechat.dao.baidumap;

import com.phil.wechat.model.baidumap.UserLocation;

public interface UserLocationDao {
	
	/**
	 * 保存用户地理位置 
	 * @param sql
	 * @param userLocation
	 * @return
	 */
	public int saveUserLocation(String sql, UserLocation userLocation);

	/**
	 * 获取用户最后一次发送的地理位置 
	 */
	public UserLocation getUserLastLocation(String sql, String openId);

}
