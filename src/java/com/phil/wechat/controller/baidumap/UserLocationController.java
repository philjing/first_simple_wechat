package com.phil.wechat.controller.baidumap;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.phil.wechat.model.baidumap.BaiduPlace;
import com.phil.wechat.model.baidumap.UserLocation;
import com.phil.wechat.model.message.resp.Article;
import com.phil.wechat.model.message.resp.NewsMessage;
import com.phil.wechat.service.baidumap.UserLocationService;
import com.phil.wechat.util.MessageUtil;

/**
 * 基于百度地图API的周边搜索
 * @author fjing
 */
@Controller
public class UserLocationController {
	
	@Autowired
	private UserLocationService userLocationService;
	
	/**
	 * 
	 * @param fromUserName
	 * @param keyWord  周边搜索的关键词
	 * @return 
	 * @throws Exception 
	 */
	public String xzdadasda(String toUserName, String fromUserName, String keyWord) throws Exception{
		
		String respContent = null;	
		// 获取用户最后一次发送的地理位置  
	    UserLocation location = userLocationService.getUserLastLocation(fromUserName);
		//根据转换后（纠偏）的坐标搜索周边POI
		List<BaiduPlace> placeList = userLocationService.searchPlace(keyWord, location.getBd09Lng(), location.getBd09Lat());
		if(null == placeList || 0 ==placeList.size()){
			respContent = String.format("/难过，您发送的位置附近未搜索到“%s”信息！", keyWord);
		} else {
			 List<Article> articleList = userLocationService.makeArticleList(placeList, location.getBd09Lng(), location.getBd09Lat());
			  // 回复图文消息  
			 NewsMessage newsMessage = new NewsMessage();
             newsMessage.setToUserName(fromUserName);
             newsMessage.setFromUserName(toUserName);
             newsMessage.setCreateTime(new Date().getTime());
             newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
             newsMessage.setArticles(articleList);
             newsMessage.setArticleCount(articleList.size());
             return MessageUtil.newsMessageToXml(newsMessage);
		}
		return respContent;
	}
	
	
	/**
	 * if (content.startsWith("附近")) {  
    String keyWord = content.replaceAll("附近", "").trim();  
    // 获取用户最后一次发送的地理位置  
    UserLocation location = userLocationDao.getLastLocation(fromUserName);  
    // 未获取到  
    if (null == location) {  
        respContent = getLocationUsage();  
    } else {  
        // 根据转换后（纠偏）的坐标搜索周边POI  
        List<BaiduPlace> placeList = BaiduMapUtil.searchPlace(keyWord, location.getBd09Lng(),  
                        location.getBd09Lat());  
        // 未搜索到POI  
        if (null == placeList || 0 == placeList.size()) {  
            respContent = String.format("/难过，您发送的位置附近未搜索到“%s”信息！", keyWord);  
        } else {  
            articleList = BaiduMapUtil.makeArticleList(placeList, location.getBd09Lng(),  
                            location.getBd09Lat());  
            // 回复图文消息  
            newsMessage = new NewsMessage();  
            newsMessage.setToUserName(fromUserName);  
            newsMessage.setFromUserName(toUserName);  
            newsMessage.setCreateTime(new Date().getTime());  
            newsMessage.setMsgType(MessageUtil.MESSAGE_TYPE_NEWS);  
            newsMessage.setArticles(articleList);  
            newsMessage.setArticleCount(articleList.size());  
            return MessageUtil.messageToXml(newsMessage);  
        }  
    }  
	 */

}
