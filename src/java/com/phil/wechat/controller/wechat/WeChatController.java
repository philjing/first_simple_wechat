package com.phil.wechat.controller.wechat;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.phil.common.util.PhilUtil;
import com.phil.wechat.model.message.resp.MusicMessage;
import com.phil.wechat.model.message.resp.NewsMessage;
import com.phil.wechat.model.message.resp.TextMessage;
import com.phil.wechat.model.weather.WeatherData;
import com.phil.wechat.service.baiduweather.BaiduWeatherService;
import com.phil.wechat.service.face.FaceService;
import com.phil.wechat.service.oilprice.OilPriceService;
import com.phil.wechat.util.MessageUtil;
import com.phil.wechat.util.SignUtil;

@Controller
@RequestMapping("/wechat")
public class WeChatController {
	
	
    private static Logger logger = Logger.getLogger(WeChatController.class);
    
    @Autowired
    private BaiduWeatherService baiduWeatherService;
    
    @Autowired
    private FaceService faceService;
    
    @Autowired
    private OilPriceService oilPriceService;
    
    /**
     * 校验信息是否是从微信服务器发过来的。 
     */
    @RequestMapping(method = { RequestMethod.GET })
    public void processGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //微信加密签名
        String signature = request.getParameter("signature");
        //时间戳
        String timestamp = request.getParameter("timestamp");
        //随机数
        String nonce = request.getParameter("nonce");
        //随机字符串
        String echostr = request.getParameter("echostr");
        logger.info("echostr: "+echostr);
        PrintWriter out = response.getWriter();
        //通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败 
        if(SignUtil.checkSignature(signature, timestamp, nonce)){
            out.print(echostr);
        }
        out.close();
        out = null;
    }
    
    /** 
     * 微信消息的处理 
     * @param request 
     * @param out 
     * @throws IOException 
     */ 
    @RequestMapping(method = { RequestMethod.POST })
    public void processPost(HttpServletRequest request, HttpServletResponse response)  throws IOException{
        //调用核心业务类接收消息、处理消息
        String respMessage = processRequest(request);
        logger.info("respMessage=" + respMessage);
        // 响应消息
        PrintWriter out = response.getWriter();
        out.print(respMessage);
        out.close();
    }
    
    /**
	 * 处理微信发来的请求
	 * @param request
	 * @return
	 */
    private String processRequest(HttpServletRequest request){	
    	// 返回给微信服务器的xml消息
    	String respXml = "";
    	
		try {
			// 默认返回的文本消息内容
			String respContent = ""; // "请求处理异常，请稍后尝试！";
			// xml请求解析
			Map<String, String> requestMap;
			requestMap = MessageUtil.parseXml(request);
			// 发送方的账号
			String fromUserName = requestMap.get("FromUserName");
			// 公众账号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			
			// 默认回复文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);
			
			//回复图文消息
			NewsMessage newsMessage = null;
			
			MusicMessage musicMessage = null;
			
			// 文本消息
			if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)){
				// 接收到的信息
				String content = requestMap.get("Content").trim();
				String content_ = null;
				
				// 天气处理, 首尾出现的话进行判断
				if (content.startsWith("天气") || content.startsWith("tianqi") || content.endsWith("天气") || content.endsWith("tianqi")) {
					respContent = baiduWeatherService.getWeatherUsage(); // 天气帮助的菜单
					if(content.contains("天气预报") || content.contains("tianqiyubao")){
						content_ = content.replaceAll("天气", "").replaceAll("tianqi", "").replaceAll("yubao", "").replaceAll("预报", "");
						respContent = baiduWeatherService.getForecastWeatherInfo(baiduWeatherService.getCityWeather(PhilUtil.urlEncodeUTF8(content_)));
					} else {
						content_ = content.replaceAll("天气", "").replaceAll("tianqi", "");
						if(StringUtils.isNotEmpty(content_)){ //还有城市名的话
							WeatherData wd = baiduWeatherService.getCityWeather(PhilUtil.urlEncodeUTF8(content_));
							respContent = baiduWeatherService.getTodayWeatherInfo(wd);
						}
					}	
				}//天气处理结束
				
				//油价处理
				if (content.startsWith("油价") || content.startsWith("youjia") || content.endsWith("油价") || content.endsWith("youjia")) {
					respContent = oilPriceService.getOilPriceUsage(); // 查看油价的菜单		
					content_ = content.replaceAll("油价", "").replaceAll("youjia", "");				
					if(StringUtils.isNotEmpty(content_)){ //不为空的话就可以查询了
						respContent = oilPriceService.priceDetect(content_);
					}
				}//油价处理结束
						
				
				
			}//文本消息处理结束
			
			// 图片消息
			else if ((msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE))){
				// 取得图片地址
				String picUrl = requestMap.get("PicUrl");
				respContent = faceService.detect(picUrl);	
			}//图片消息处理结束
			
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {		    
		        //用户发送的经纬度
				String lng = requestMap.get("Location_Y");
			    String lat = requestMap.get("Location_X");
			    //坐标转换后的经纬度
			    String bd09Lng = null;
			    String bd09Lat = null;
			    //接口转换坐标
			    /*UserLocation userLocation = BaiduMapUtil.convertCoord(lng, lat);
			    if(null != userLocation){   	
			        bd09Lat = userLocation.getBd09Lat();
			        bd09Lng = userLocation.getBd09Lng();
			    }
			    //保存用户地理位置
			    MySQLUtil.saveUserLocation(request, fromUserName, lng, lat, bd09Lng, bd09Lat);
			    StringBuffer buffer = new StringBuffer();
			    buffer.append("成功接收到您的位置").append("\n");			    
				respContent = buffer.toString();//"您发送的是地理位置消息！";
                */		    
		    }						
			
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息！";
			}
			// 音频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是音频消息！";
			}
			
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = "欢迎关注我";
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					String eventKey = requestMap.get("EventKey");
					if (eventKey.equals("11")) {
						respContent = baiduWeatherService.getWeatherUsage();
					} else if (eventKey.equals("12")) {
						respContent = "即将实现";
					} else if (eventKey.equals("13")) {
						//respContent = BaiduMapService.getLocationUsage();
					} else if (eventKey.equals("14")) {
						respContent = "即将实现";   //电影排行榜菜单项被点击
					} else if (eventKey.equals("21")) {
						//respContent = NewsService.getNewsUsage();   //新闻查询的帮助菜单
					} else if (eventKey.equals("22")) {
						//respContent = JokeService.getJokeInfo();
					} else if (eventKey.equals("23")) {
						//respContent = TodayInHistoryService.getTodayInHistoryInfo();
					} else if (eventKey.equals("24")) {
						//respContent = KuwoMusicService.getMusicUsage();
					} else if (eventKey.equals("25")) {
						//respContent = "即将实现";
					} else if (eventKey.equals("31")) {
						respContent = "即将实现";
					} else if (eventKey.equals("32")) {
						//respContent = FaceService.getFaceUsage(); 
					} else if (eventKey.equals("33")) {
						respContent = "保留菜单";
					}
				}
			}
			
			// 有回复文本的内容
			if (respContent != "") {
				textMessage.setContent(respContent);
				respXml = MessageUtil.textMessageToXml(textMessage);
			}
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
    	return respXml;
    }
}
