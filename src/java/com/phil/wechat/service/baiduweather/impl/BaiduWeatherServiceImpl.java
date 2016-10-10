package com.phil.wechat.service.baiduweather.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.phil.common.util.HttpRequestUtil;
import com.phil.common.util.PhilUtil;
import com.phil.wechat.model.weather.WeatherData;
import com.phil.wechat.model.weather.WeatherForecast;
import com.phil.wechat.model.weather.WeatherResult;
import com.phil.wechat.model.weather.WeatherToday;
import com.phil.wechat.service.baiduweather.BaiduWeatherService;

/**
 * 百度天气查询
 * @author fjing
 *
 */
@Service
public class BaiduWeatherServiceImpl implements BaiduWeatherService {
    
    private static Logger logger = Logger.getLogger(BaiduWeatherServiceImpl.class);

    @Override
    public WeatherData getCityWeather(String city) {   
        //http://apis.baidu.com/apistore/weatherservice/recentweathers?cityname={cityname}
        String httpUrl = System.getProperty("baiduweather.url");//"http://apis.baidu.com/apistore/weatherservice/recentweathers?";
        Map<String,String> params = new HashMap<String,String>();
        params.put("cityname", PhilUtil.urlEncodeUTF8(city));
        
        Map<String,String> properties = new HashMap<String,String>();
        properties.put("apikey", System.getProperty("baiduweather.apikey"));       
        String jsonResult = HttpRequestUtil.httpRequest(httpUrl, params, HttpRequestUtil.GET_METHOD, properties);
        logger.info("jsonResult "+jsonResult);
        WeatherData wd = null;
        try {
            WeatherResult wr = new Gson().fromJson(jsonResult, WeatherResult.class);
            wd = wr.getRetData();
        } catch (Exception e) {
            logger.info("exception "+e.getMessage());
        }
        return wd;
    }

    /**
     * 组装今日天气的信息
     */
    @Override
    public String getTodayWeatherInfo(WeatherData weatherData) {
        if (weatherData == null) {
            return "没有查询到该城市的天气信息";
        }
        WeatherToday weatherToday = null;
        weatherToday = weatherData.getToday();
        StringBuffer buffer = new StringBuffer();
        buffer.append(weatherData.getCity()).append(weatherToday.getDate()).append("天气情况").append("\n");
        buffer.append("日期:").append(weatherToday.getWeek()).append("\n");
        buffer.append("当前温度:").append(weatherToday.getCurTemp()).append("\n");
        buffer.append("pm值:").append(weatherToday.getAqi()).append("\n");
        buffer.append("风向:").append(weatherToday.getFengxiang()).append("\n");
        buffer.append("风力:").append(weatherToday.getFengli()).append("\n");
        buffer.append("最高温度:").append(weatherToday.getHightemp()).append("\n");
        buffer.append("最低温度:").append(weatherToday.getLowtemp()).append("\n");
        buffer.append("天气状态:").append(weatherToday.getType()).append("\n");
        return buffer.toString();
    }
    
    /**
     * 组装未来天气的信息
     */
    @Override
    public String getForecastWeatherInfo(WeatherData weatherData) {
        List<WeatherForecast> forecastList = weatherData.getForecast(); 
        StringBuffer buffer = new StringBuffer();
        buffer.append("未来三天天气预报").append("\n");
        for(int i = 0 ;i < forecastList.size() -1 ; i ++){
            WeatherForecast wf = forecastList.get(i);
            buffer.append("日期:").append(wf.getDate()).append("\n");
            buffer.append("星期:").append(wf.getWeek()).append("\n");
            buffer.append("风向:").append(wf.getFengxiang()).append("\n");
            buffer.append("风力:").append(wf.getFengli()).append("\n");
            buffer.append("最高温度:").append(wf.getHightemp()).append("\n");
            buffer.append("最低温度:").append(wf.getLowtemp()).append("\n");
            buffer.append("天气状态：").append(wf.getType()).append("\n");
        }
        return buffer.toString();
    }

    @Override
    public String getWeatherUsage() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("天气查询帮助").append("\n\n");
        buffer.append("回复:天气+城市或者城市+天气").append("\n");
        buffer.append("例如:天气苏州,苏州天气").append("\n\n");
        buffer.append("回复:天气预报+城市或者城市+天气预报,就可以预知未来三天天气情况咯").append("\n");
        buffer.append("友情提醒:城市只能是中文哦,快试试吧^_^");
        return buffer.toString();
    }

}
