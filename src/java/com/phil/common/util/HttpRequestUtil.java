package com.phil.common.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;

public class HttpRequestUtil {    
    
    private static Logger logger = Logger.getLogger(HttpRequestUtil.class);
    
    public static final String GET_METHOD = "GET";
    
    public static final String POST_METHOD = "POST";
    
    /** 
     * 
     * 发送http请求取得返回的输入流 
     * @param requestUrl 请求地址 
     * @return InputStream
     */  
    public static InputStream httpRequestInputStream(String requestUrl) {  
        InputStream inputStream = null;  
        try {  
            URL url = new URL(requestUrl);  
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();  
            httpUrlConn.setDoInput(true);  
            httpUrlConn.setRequestMethod("GET");  
            httpUrlConn.connect();  
            // 获得返回的输入流  
            inputStream = httpUrlConn.getInputStream();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        return inputStream;
    }

    /** 
     * 
     * 发起http请求返回结果
     * @param requestUrl 请求地址 
     * @return params
     * @return method
     * @return properties
     */  
    public static String httpRequest(String httpUrl, Map<String,String> params, String requestMethod, Map<String,String> properties) {
        BufferedReader reader = null;
        StringBuffer result = new StringBuffer();
        //拼接参数请求
        StringBuffer buffer = new StringBuffer(httpUrl);
        if(params!=null && !params.isEmpty()){
            buffer.append("?");
            for(Map.Entry<String,String> param : params.entrySet()){
                buffer.append(param.getKey() + "=" + param.getValue());
                buffer.append("&");
            }
        }
        logger.info("request Url "+buffer.toString());
        try {
            URL url = new URL(buffer.toString());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();           
            //请求方式,get或者post
            connection.setRequestMethod(requestMethod==null||requestMethod==""?"GET":requestMethod.toUpperCase()); //默认get
            //表头的参数填充
            if(properties!=null && !properties.isEmpty()){
                for(Map.Entry<String,String> property : properties.entrySet()){
                    connection.setRequestProperty(property.getKey(),property.getValue());
                }
            }
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                result.append(strRead);
                result.append("\r\n");
            }
            reader.close();
            is.close();
            connection.disconnect(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }
    
    /**
     * 发起http get请求获取网页源代码
     * 
     * @param requestUrl 
     * @return 
     */
    public static String httpRequestHtml(String requestUrl, String requestMethod){
        
        StringBuffer buffer = null;       
        try {
            //建立连接
            URL url = new URL(requestUrl);
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
            httpUrlConn.setDoInput(true);
            httpUrlConn.setRequestMethod(requestMethod==null||requestMethod==""?"GET":requestMethod.toUpperCase());//默认是GET
            //获取输入流
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            
            //读取返回结果
            buffer = new StringBuffer();
            String str = null;
            while((str = bufferedReader.readLine()) != null){
                buffer.append(str);
            }         
            //释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            httpUrlConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }
    
    /**
     * 
     * @param requestUrl  请求地址
     * @param requestMethod   请求方式
     * @param str 提交的数据
     * @return
     */
    public static JSONObject httpRequestJSONObject(String requestUrl, String requestMethod, String outputStr){
        JSONObject jsonObject = null;
        try {
        	//创建SSLContext对象
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL","SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            //从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
            conn.setSSLSocketFactory(ssf);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            //设置请求方式
            conn.setRequestMethod(requestMethod);
            
            if(null != outputStr){
                OutputStream outputStream = conn.getOutputStream();
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
            
            //从输入流读取返回内容
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while((str = bufferedReader.readLine())!= null){
                buffer.append(str);
            }
            
            //释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream=null;
            conn.disconnect();
            jsonObject = JSONObject.fromObject(buffer.toString());        
        } catch (Exception e) {
            //e.printStackTrace();
        	logger.info("请求异常: "+e.getMessage()); 
			//throw e;
        }        
        return jsonObject;  
    }       

}
