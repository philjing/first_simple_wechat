package com.phil.wechat.service.aibangbus;

import java.util.List;

import com.phil.wechat.model.aibang.BusLine;
import com.phil.wechat.model.aibang.StatsLine;
import com.phil.wechat.model.aibang.TransferLine;

public interface AiBangBusService {
    
    /**
     * 公交线路查询，该接口根据关键字查询匹配的线路
     * @author fjing
     * @param city
     * @param q
     * @return
     */
    public List<BusLine> searchBusLines(String city, String q) throws Exception ;
    
    /**
     * 公交换乘查询，该接口根据起点和终点信息查询公交换乘方案
     * @author fjing
     * @param city
     * @param start_addr
     * @param end_addr
     * @return
     */
    public List<TransferLine> searchBusTrasferLines(String city, String start_addr ,String end_addr) throws Exception ;
    
    /**
     * 
     * 公交站点查询，该接口根据关键字查询匹配的站点
     * @author fjing
     * @param city
     * @param q
     * @return
     */
    public List<StatsLine> searchBusLineStats(String city, String q ) throws Exception ;
    
    

}
