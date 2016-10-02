package com.phil.wechat.service.baiduweather;

import com.phil.wechat.model.weather.WeatherData;

public interface BaiduWeatherService {
    
    /**
     * 获取WeatherData
     * @param word
     * @return
     */
    public WeatherData getCityWeather(String city);
    
    /**
     * 组装今日天气的信息
     */
    public String getTodayWeatherInfo(WeatherData weatherData);

    /**
     * 组装未来天气的信息
     */
    public String getForecastWeatherInfo(WeatherData weatherData);
    
    /**
     * 天气帮助菜单
     */
    public String getWeatherUsage();
}
