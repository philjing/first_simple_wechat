package com.phil.wechat.service.baidumap;

import java.util.List;

import com.phil.wechat.model.baidumap.BaiduPlace;
import com.phil.wechat.model.baidumap.UserLocation;
import com.phil.wechat.model.message.resp.Article;

public interface UserLocationService {
	
	/**
	 * 保存用户地理位置
	 * @param userLocation
	 * @return
	 */
	 public int saveUserLocation(UserLocation userLocation);
	 
	 /**
	  * 获取用户最后一次发送的地理位置 
	  * @param openId
	  * @return
	  */
	 public UserLocation getUserLastLocation(String openId);
	 
	 /**
	  * 圆形区域检索 ,根据百度地图返回的流解析出地址信息 
	  * @param query 检索关键词 
	  * @param lng   经度 
	  * @param lat   纬度 
	  * @return
	  * @throws Exception
	  */
	 public List<BaiduPlace> searchPlace(String query, String lng, String lat) throws Exception;
	 
	 /**
	  * 根据Place组装图文列表 
	  * @param placeList
	  * @param bd09Lng  经度 
	  * @param bd09Lat  纬度 
	  * @return
	  */
	 public List<Article> makeArticleList(List<BaiduPlace> placeList, String bd09Lng, String bd09Lat);
	 
	 /**
	  * 将微信定位的坐标转换成百度坐标（GCJ-02 -> Baidu） 
	  * @param lng  经度 
	  * @param lat  纬度 
	  * @return
	  */
	 public UserLocation convertCoord(String lng, String lat);
	 
}
