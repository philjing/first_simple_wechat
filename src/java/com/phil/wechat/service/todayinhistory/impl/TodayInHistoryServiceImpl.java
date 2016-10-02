package com.phil.wechat.service.todayinhistory.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.phil.common.util.DateUtil;
import com.phil.common.util.HttpRequestUtil;
import com.phil.wechat.service.todayinhistory.TodayInHistoryService;

/**
 * 历史上的今天查询服务
 * @author fjing
 */
@Service
public class TodayInHistoryServiceImpl implements TodayInHistoryService{
	
	 /** 
     * 从html中抽取出历史上的今天信息 
     *  
     * @param html 
     * @return 
     */  
	public String extractToString(String html){
		StringBuffer buffer = null;
		//日期标签：区分是昨天还是今天
		String dateTag =DateUtil.getMonthDay(0);
		Pattern p = Pattern.compile("(.*)(<ul class=\"list clearfix\">)(.*?)(</ul>)(.*)");
		Matcher m = p.matcher(html);
		if(m.matches()){
			buffer = new StringBuffer();
			if(m.group(3).contains(DateUtil.getMonthDay(-1))){
				dateTag = DateUtil.getMonthDay(-1);
			}
			//拼装标题
			buffer.append("≡≡ ").append("历史上的").append(dateTag).append(" ≡≡").append("\n\n");
			
			//抽取需要的数据
			for(String info : m.group(3).split("                                        ")){
				info = info.replace(dateTag, "").replaceAll("</?[^>]+>", "").trim(); 
				//在每行末尾追加2个换行符
				if(!"".equals(info)){
					buffer.append(info).append("\n");
				}
			}
		}
		return (null == buffer) ? null : buffer.substring(0,buffer.lastIndexOf("\n"));
	}
	
	 /** 
     * 从html中抽取出历史上的今天信息
     *  
     * @param html 
     * @return 
     */
	@Override
	public List<String> extractToList(String html) {
		List<String> list =null;
		//日期标签：区分是昨天还是今天
		String dateTag =DateUtil.getMonthDay(0);
		Pattern p = Pattern.compile("(.*)(<ul class=\"list clearfix\">)(.*?)(</ul>)(.*)");
		Matcher m = p.matcher(html);
		if(m.matches()){
			list = new ArrayList<String>();
			if(m.group(3).contains(DateUtil.getMonthDay(-1))){
				dateTag = DateUtil.getMonthDay(-1);
			}
			//拼装标题		
			//buffer.append("≡≡ ").append("历史上的").append(dateTag).append(" ≡≡").append("\n\n");
			
			//抽取需要的数据
			for(String info : m.group(3).split("                                        ")){
				info = info.replace(dateTag, "").replaceAll("</?[^>]+>", "").trim(); 
				//在每行末尾追加2个换行符
				if(!"".equals(info)){
					list.add(info);
				}
			}
		}	
		return list;		
	}
	
    /** 
     * 封装历史上的今天查询方法，供外部调用 
     *  
     * @return 
     */  
    public String getTodayInHistoryInfo() {  
        // 获取网页源代码  
        String html = HttpRequestUtil.httpRequestHtml("http://www.lssdjt.com", HttpRequestUtil.GET_METHOD);
        // 从网页中抽取信息  
        String result = extractToString(html);
        return result;
    }
    
    public List<String> getTodayInHistoryInfoList() {
        // 获取网页源代码  
        String html = HttpRequestUtil.httpRequestHtml("http://www.lssdjt.com", HttpRequestUtil.GET_METHOD);
        // 从网页中抽取信息  
        List<String> result = extractToList(html);
        return result;
    }
    
    /** 
     * 通过main在本地测试 
     *  
     * @param args 
     * @throws UnsupportedEncodingException 
     */  
    public static void main(String[] args) throws UnsupportedEncodingException {  
        String info = new TodayInHistoryServiceImpl().getTodayInHistoryInfo();
        System.out.println(info);
        System.out.println(info.getBytes().length);
        System.out.println(info.length());
    }
    


}
