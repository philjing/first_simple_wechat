package com.phil.wechat.controller.wechat;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.phil.wechat.util.SignUtil;

@Controller
@RequestMapping("/wechat")
public class WeChatController {
    
    private static Logger logger = Logger.getLogger(WeChatController.class);
    
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
     // 调用核心业务类接收消息、处理消息
        String respMessage = "";//CoreService.processRequest(request);
        logger.info("respMessage=" + respMessage);
        // 响应消息
        PrintWriter out = response.getWriter();
        out.print(respMessage);
        out.close();
    }

}
