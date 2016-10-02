package com.phil.wechat.service.custommessage.impl;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.phil.wechat.service.custommessage.CustomMessageSerivce;

@Service
public class CustomMessageSerivceImpl implements CustomMessageSerivce {
	
	private static final Logger logger = Logger.getLogger(CustomMessageSerivceImpl.class);

	@Override
	public boolean sendCustomMessage(String accessToken, String jsonMsg) {
		
		boolean result = false;
		
		//
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
		
		requestUrl.replace("ACCESS_TOKEN", accessToken);
		
		JSONObject jsonObject = null;
		
		if(null != jsonObject){
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if(0 == errorCode){
				result = true;
				logger.info("客服消息发送成功: "+errorMsg);
			}else{
				logger.info("客服消息发送失败,错误代码 :"+errorCode+errorMsg);
			}
		}
		
		return false;
	}

}
