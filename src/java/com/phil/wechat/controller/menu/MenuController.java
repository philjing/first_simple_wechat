package com.phil.wechat.controller.menu;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.phil.common.util.HttpRequestUtil;
import com.phil.wechat.model.menu.Button;
import com.phil.wechat.model.menu.CommonButton;
import com.phil.wechat.model.menu.ComplexButton;
import com.phil.wechat.model.menu.Menu;
import com.phil.wechat.model.menu.ViewButton;
import com.phil.wechat.model.token.AccessToken;

/**
 * 菜单的创建
 * @author fjing
 * @date   2016-10-11
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
	
	
	private static final Logger logger = Logger.getLogger(MenuController.class);
	
	  // 第三方用户唯一凭证  
    private static String appId = "wx9fc2f6abeaeb52a0"; // wxd0552c95eda4ab24

    // 第三方用户唯一凭证密钥  
    private static String appSecret = "f8515649b5e2dd3f64dacb000560dba8";      //51ca21e7bacc8f80c5e4325a4b52310d
	
	/**
	 * 创建菜单
	 * @param request
	 */
	@RequestMapping("/create")
	public void create(HttpServletRequest request){	
		AccessToken accessToken = new AccessToken();//WeiXinUtil.getAccessToken(appId, appSecret);
		
		String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";	
		int result = 1;
		//拼装创建菜单的url
		String url = menu_create_url.replace("ACCESS_TOKEN", "aa");    //accessToken.getToken()
		//将菜单对象转换为json字符串
		String jsonMenu = JSONObject.fromObject(getMenu()).toString();
		//调用接口创建对象
		JSONObject jsonObject = null;
		try {
			jsonObject = HttpRequestUtil.httpRequestJSONObject(url, HttpRequestUtil.POST_METHOD, jsonMenu);
		} catch (Exception e) {
			logger.info("连接超时！");
			throw e;
		}
		
		if(null != jsonObject){
			if(0 != jsonObject.getInt("errcode")){
				result = jsonObject.getInt("errcode");
			}
		}
		if (0 == result){
			logger.info("菜单创建成功！");  
		}    
        else {
        	logger.info("菜单创建失败，错误码：" + result); 
        }
	}
	
	/**
	 * 删除菜单
	 * @param request
	 */
	public void delete(HttpServletRequest request){
		
		AccessToken accessToken = new AccessToken();//WeiXinUtil.getAccessToken(appId, appSecret);
		
		String menu_delete_url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
		int result = 1;
		//拼装创建菜单的url
		String url = menu_delete_url.replace("ACCESS_TOKEN", "aa");    //accessToken.getToken()
		//将菜单对象转换为json字符串
		String jsonMenu = JSONObject.fromObject(getMenu()).toString();
		//调用接口创建对象
		JSONObject jsonObject = null;
		try {
			jsonObject = HttpRequestUtil.httpRequestJSONObject(url, HttpRequestUtil.GET_METHOD, jsonMenu);
		} catch (Exception e) {
			logger.info("连接超时！");
			throw e;
		}
		
		if(null != jsonObject){
			if(0 != jsonObject.getInt("errcode")){
				result = jsonObject.getInt("errcode");
			}
		}	
		if (0 == result){
			logger.info("菜单删除成功！");  
		}    
        else {
        	logger.info("菜单删除失败，错误码：" + result); 
        }
	}
	
	/** 
     * 组装菜单数据 
     *  
     * @return 
     */  
    private static Menu getMenu() {  
        CommonButton btn11 = new CommonButton();  
        btn11.setName("天气预报");  
        btn11.setType("click");  
        btn11.setKey("11");  
  
        CommonButton btn12 = new CommonButton();  
        btn12.setName("公交查询");  
        btn12.setType("click");  
        btn12.setKey("12"); 
  
        CommonButton btn13 = new CommonButton();  
        btn13.setName("周边搜索");  
        btn13.setType("click");  
        btn13.setKey("13");
        
        CommonButton btn14 = new CommonButton();  
        btn14.setName("电影排行榜");  
        btn14.setType("click");  
        btn14.setKey("14");
        
        CommonButton btn15 = new CommonButton();  
        btn15.setName("保留菜单");  
        btn15.setType("click"); 
        btn15.setKey("15");
        
        CommonButton btn21 = new CommonButton();  
        btn21.setName("新闻点击");  
        btn21.setType("click");  
        btn21.setKey("21");  
  
        CommonButton btn22 = new CommonButton();  
        btn22.setName("幽默笑话");
        btn22.setType("click");  
        btn22.setKey("22");
  
        CommonButton btn23 = new CommonButton();  
        btn23.setName("历史上的今天");  
        btn23.setType("click");  
        btn23.setKey("23");
  
        CommonButton btn24 = new CommonButton();  
        btn24.setName("在线点歌");  
        btn24.setType("click");  
        btn24.setKey("24");
  
        CommonButton btn25 = new CommonButton();  
        btn25.setName("聊天唠嗑");  
        btn25.setType("click");  
        btn25.setKey("25");
  
        ViewButton btn31 = new ViewButton();  
        btn31.setName("菜单");  
        btn31.setType("view");
        btn31.setUrl("http://liebesy.com");
  
        CommonButton btn32 = new CommonButton();  
        btn32.setName("人脸识别");  
        btn32.setType("click");
        btn32.setKey("32");
        
        CommonButton btn33 = new CommonButton();  
        btn33.setName("保留菜单");  
        btn33.setType("click");  
        btn33.setKey("33");
        
        ViewButton btn34 = new ViewButton();  
        btn34.setName("LOL战斗力");  
        btn34.setType("view");
        btn34.setUrl("http://lol.766.com/gamer");
        
        ViewButton btn35 = new ViewButton();  
        btn35.setName("博客地址");  
        btn35.setType("view");
        btn35.setUrl("http://liebesy.com");
        
        ComplexButton mainBtn1 = new ComplexButton();  
        mainBtn1.setName("生活助手");  
        mainBtn1.setSub_button(new Button[] { btn11, btn12, btn13, btn14, btn15 });  
  
        ComplexButton mainBtn2 = new ComplexButton();  
        mainBtn2.setName("休闲驿站");  
        mainBtn2.setSub_button(new CommonButton[] { btn21, btn22, btn23, btn24, btn25 });  
  
        ComplexButton mainBtn3 = new ComplexButton();  
        mainBtn3.setName("更多");  
        mainBtn3.setSub_button(new Button[] { btn31, btn32, btn33, btn34, btn35});
        /** 
         *  
         * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br> 
         * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br> 
         * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 }); 
         */  
        Menu menu = new Menu();  
        menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });  
        
        return menu;  
    }
}
