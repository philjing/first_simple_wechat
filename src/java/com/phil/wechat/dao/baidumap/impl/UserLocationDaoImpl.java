package com.phil.wechat.dao.baidumap.impl;

import org.springframework.stereotype.Repository;

import com.phil.common.dao.impl.CommonDaoImpl;
import com.phil.wechat.dao.baidumap.UserLocationDao;
import com.phil.wechat.model.baidumap.UserLocation;

@Repository
public class UserLocationDaoImpl extends CommonDaoImpl implements UserLocationDao {
	
	/**
	 * 保存用户地理位置 
	 */
	@Override
	public int saveUserLocation(String sql, UserLocation userLocation) {
				
		super.save(userLocation);
		
		return 1;
	}

	/**
	 * 获取用户最后一次发送的地理位置 
	 */
	@Override
	public UserLocation getUserLastLocation(String sql, String openId) {
		
		
		return null;
	}

}
