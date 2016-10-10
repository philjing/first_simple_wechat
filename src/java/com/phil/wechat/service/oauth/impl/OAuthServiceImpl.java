package com.phil.wechat.service.oauth.impl;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.phil.common.util.HttpRequestUtil;
import com.phil.wechat.model.token.Oauth2Token;
import com.phil.wechat.model.user.SNSUserInfo;
import com.phil.wechat.service.oauth.OAuthService;

/**
 * 
 * @author fjing
 * @date  2016-10-12
 */
@Service
public class OAuthServiceImpl implements OAuthService {
	
	private static final Logger logger = Logger.getLogger(OAuthServiceImpl.class);

	/**
	 * 获取网页授权凭证
	 * @param appId 公众账号的唯一标识
	 * @param appSecret 公众账号的密钥
	 * @param code
	 * @return
	 */
	@Override
	public Oauth2Token getOauth2AccessToken(String appId, String appSecret, String code) {
		Oauth2Token ot = null;
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		requestUrl = requestUrl.replace("APPID", appId);
		requestUrl = requestUrl.replace("SECRET", appSecret);
		requestUrl = requestUrl.replace("CODE", code);
		//获取网页授权凭证
		JSONObject jsonObject = HttpRequestUtil.httpRequestJSONObject(requestUrl, HttpRequestUtil.GET_METHOD, null);
		if(null != jsonObject){
			try {
				ot = new Oauth2Token();
				ot.setAccessToken(jsonObject.getString("access_token"));
				ot.setExpiresIn(jsonObject.getInt("expires_in"));
				ot.setRefreshToken(jsonObject.getString("refresh_token"));
				ot.setOpenId(jsonObject.getString("openid"));
				ot.setScope(jsonObject.getString("scope"));
			} catch (Exception e) {
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				logger.info("获取网页授权凭证失败,错误码:"+errorCode+",错误提示:"+errorMsg);
			}
		}
		return ot;
	}

	/**
	 * 刷新网页授权验证
	 * @param appId  公众账号的唯一标识
	 * @param refreshToken
	 * @return
	 */
	@Override
	public Oauth2Token refreshOauth2AccessToken(String appId, String refreshToken) {	
		Oauth2Token ot = null;
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
		requestUrl = requestUrl.replace("appId", appId);
		requestUrl = requestUrl.replace("REFRESH_TOKEN", refreshToken);
		//刷新网页授权凭证
		JSONObject jsonObject = HttpRequestUtil.httpRequestJSONObject(requestUrl, HttpRequestUtil.GET_METHOD, null);
		if(null != jsonObject){
			try {
				ot = new Oauth2Token();
				ot.setAccessToken(jsonObject.getString("access_token"));
				ot.setExpiresIn(jsonObject.getInt("expires_in"));
				ot.setRefreshToken(jsonObject.getString("refresh_token"));
				ot.setOpenId(jsonObject.getString("openid"));
				ot.setScope(jsonObject.getString("scope"));
			} catch (Exception e) {
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				logger.info("刷新网页授权凭证失败,错误码:"+errorCode+",错误提示:"+errorMsg);
			}
		}
		return ot;
	}

	/**
	 * 通过网页授权获取用户信息
	 * @param oauth2Token
	 * @return
	 */
	@Override
	public SNSUserInfo getSNSUserInfo(String accessToken, String openId) {
		SNSUserInfo suser = null;
		String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);		
		requestUrl = requestUrl.replace("OPENID", openId);
		//通过网页授权获取用户信息
		String  result = HttpRequestUtil.httpRequest(requestUrl, null, HttpRequestUtil.GET_METHOD, null);
		if(null != result){
			try {
				suser = new Gson().fromJson(result, SNSUserInfo.class);
			} catch (JsonSyntaxException e) {
				logger.info("transfer exception");
				throw e;
			} 
		}
		/*if(null != jsonObject){
			try {
				suser = new SNSUserInfo();
				suser.setOpenId(jsonObject.getString("openid"));
				suser.setNickname(jsonObject.getString("nickname"));
				suser.setSex(jsonObject.getInt("sex"));
				suser.setCountry(jsonObject.getString("country"));
				suser.setProvince(jsonObject.getString("province"));
				suser.setCity(jsonObject.getString("city"));
				suser.setHeadImgUrl(jsonObject.getString("headimgurl"));
				suser.setPrivilegeList(JSONArray.toCollection(jsonObject.getJSONArray("privilege")));
				
			} catch (Exception e) {
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				logger.info("刷新网页授权凭证失败,错误码:"+errorCode+",错误提示:"+errorMsg);
			}
		}*/
		return suser;
	}
}
