package com.phil.wechat.controller.oauth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.phil.wechat.model.token.Oauth2Token;
import com.phil.wechat.model.user.SNSUserInfo;
import com.phil.wechat.service.oauth.OAuthService;

/**
 * 授权后的回调请求处理
 * @author fjing
 * @date  2016-10-12
 */
@Controller
@RequestMapping("/oauth")
public class OAuthController {
	
	private static final Logger logger = Logger.getLogger(OAuthController.class);
	
	@Autowired
	private OAuthService oAuthService;
	
	@RequestMapping(method = { RequestMethod.GET })
	public String processGet(HttpServletRequest request, HttpServletResponse response, String url) throws Exception{	
		request.setCharacterEncoding("gb2312");
		response.setCharacterEncoding("gb2312");	
		//用户同意授权后可以获得code
		String code = request.getParameter("code");
		logger.info("用户同意授权后的code: "+code);
		
		//用户同意授权
		if(!"authdeny".equals(code)){
			//获取网页授权access_token
			Oauth2Token oauth2Token = oAuthService.getOauth2AccessToken("appId", "appSecret", code);
			//获取用户信息
			SNSUserInfo suser = oAuthService.getSNSUserInfo(oauth2Token.getAccessToken(), oauth2Token.getOpenId());
			request.setAttribute("suser", suser);
		}
		//
		return "forward:/"+url; //转发到指定的url
	}
}
