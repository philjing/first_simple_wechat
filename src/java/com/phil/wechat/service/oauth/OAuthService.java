package com.phil.wechat.service.oauth;

import com.phil.wechat.model.token.Oauth2Token;
import com.phil.wechat.model.user.SNSUserInfo;

/**
 * 
 * @author fjing
 * @date  2016-10-12
 */
public interface OAuthService {
	
	/**
	 * 获取网页授权凭证
	 * @param appId 公众账号的唯一标识
	 * @param appSecret 公众账号的密钥
	 * @param code
	 * @return
	 */
	public Oauth2Token getOauth2AccessToken(String appId, String appSecret, String code);
	
	/**
	 * 刷新网页授权验证
	 * @param appId  公众账号的唯一标识
	 * @param refreshToken
	 * @return
	 */
	public Oauth2Token refreshOauth2AccessToken(String appId, String refreshToken);
		
	/**
	 * 通过网页授权获取用户信息
	 * @param oauth2Token
	 * @return
	 */
	public SNSUserInfo getSNSUserInfo(String accessToken, String openId);
}
