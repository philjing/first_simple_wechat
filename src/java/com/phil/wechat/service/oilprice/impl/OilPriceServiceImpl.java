package com.phil.wechat.service.oilprice.impl;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.phil.common.util.DateUtil;
import com.phil.common.util.HttpRequestUtil;
import com.phil.common.util.PhilUtil;
import com.phil.wechat.service.oilprice.OilPriceService;

@Service
public class OilPriceServiceImpl implements OilPriceService {
	
	//private static String apikey = "cd2306261560b164317de7a2c5c259a9";

	@Override
	public String priceDetect(String prov) {
		String requestUrl = "http://apis.baidu.com/showapi_open_bus/oil_price/find?prov="+PhilUtil.urlEncodeUTF8(prov);
		Map<String,String> properties = new HashMap<String,String>();
		properties.put("apikey", "cd2306261560b164317de7a2c5c259a9");	
		String jsonResult =   HttpRequestUtil.httpRequest(requestUrl, null, HttpRequestUtil.GET_METHOD, properties);
		
		JSONArray jsonArray = JSONObject.fromObject(jsonResult).getJSONObject("showapi_res_body").getJSONArray("list");
		StringBuffer buffer = new StringBuffer();
		if(jsonArray.size() > 0){
			JSONObject jsonObject = (JSONObject)jsonArray.get(0);
			buffer.append("今日").append(prov).append("油价,数据来源于国家发改委官网").append("\n");
			//日期精确到年月日时分
			buffer.append("更新时间:").append(DateUtil.dateFormat(DateUtil.Y_M_D_H_M_S_DATE, DateUtil.YMDHM_DATE, jsonObject.getString("ct"))).append("\n");
			buffer.append("0号柴油:").append(jsonObject.getString("p0")).append("元/升").append("\n");
			buffer.append("90号汽油:").append(jsonObject.getString("p90")).append("元/升").append("\n");
			buffer.append("93号汽油:").append(jsonObject.getString("p93")).append("元/升").append("\n");
			buffer.append("97号汽油:").append(jsonObject.getString("p97")).append("元/升");	
		}else{
			buffer.append("请重新输入国内31个省，直辖市的名称");
		}
		return buffer.toString();
	}

	@Override
	public String getOilPriceUsage() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("油价查询帮助").append("\n\n");
		buffer.append("回复:油价+省名或者省名+城市").append("\n");
		buffer.append("例如:油价江苏,江苏油价").append("\n");
		return buffer.toString();
	}

}
