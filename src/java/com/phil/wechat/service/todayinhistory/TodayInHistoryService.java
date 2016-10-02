package com.phil.wechat.service.todayinhistory;

import java.util.List;


/**
 * 历史上的今天查询服务
 * @author fjing
 *
 */
public interface TodayInHistoryService {
	
	 /** 
     * 从html中抽取出历史上的今天信息 
     *  
     * @param html 
     * @return 
     */  
	public String extractToString(String html);
	
	 /** 
     * 从html中抽取出历史上的今天信息 
     *  
     * @param html 
     * @return 
     */ 
	public List<String> extractToList(String html);

    /** 
     * 封装历史上的今天查询方法，供外部调用 
     *  
     * @return 
     */  
    public String getTodayInHistoryInfo();

    public List<String> getTodayInHistoryInfoList();
}
