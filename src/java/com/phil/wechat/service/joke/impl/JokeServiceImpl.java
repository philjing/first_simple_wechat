package com.phil.wechat.service.joke.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.phil.common.util.HttpRequestUtil;
import com.phil.wechat.model.joke.JokeContent;
import com.phil.wechat.model.joke.JokeResult;
import com.phil.wechat.service.joke.JokeService;

@Service
public class JokeServiceImpl implements JokeService {
    
    private static Logger logger = Logger.getLogger(JokeServiceImpl.class);
    
    private static String apikey = "cd2306261560b164317de7a2c5c259a9";//System.getProperty("joke.api_key");

    @Override
    public String searchJokes() {
        
        String requestUrl = "http://apis.baidu.com/showapi_open_bus/showapi_joke/joke_text?";//System.getProperty("joke.requestUrl");
        
        Map<String,String> params = new HashMap<String,String>();
        int temp = (int) ( Math.random()* 88  + 1);
        params.put("page", String.valueOf(temp));
        logger.info("joke url "+requestUrl);
        
        Map<String,String> properties = new HashMap<String,String>();
        properties.put("apikey", apikey);     
       String jsonResult = HttpRequestUtil.httpRequest(requestUrl, params, null, properties);   
       //空指针处理
       JokeResult jokeResult = new Gson().fromJson(jsonResult,JokeResult.class); 
       List<JokeContent> contentlist = jokeResult.getShowapi_res_body().getContentlist();
       StringBuffer buffer = new StringBuffer();
       //显示五则笑话
       for(int i = 0 ;i < 4 ; i ++){
           JokeContent jc = contentlist.get(i);
           buffer.append((i+1)+"."+jc.getText().replaceAll("<p></p>","").replaceAll("</p><p>","").replaceAll("<p>","").replaceAll("</p>","").replaceAll("</br>","")).append("\n");
       }
        return buffer.toString();
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        String joke = new JokeServiceImpl().searchJokes();
        System.out.println(joke);
    }

}
