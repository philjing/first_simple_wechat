package com.phil.wechat.service.aibangbus.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;

import com.phil.common.framework.constant.SystemConstant;
import com.phil.common.util.HttpRequestUtil;
import com.phil.common.util.PhilUtil;
import com.phil.wechat.model.aibang.BusLine;
import com.phil.wechat.model.aibang.StatsLine;
import com.phil.wechat.model.aibang.TransferLine;
import com.phil.wechat.service.aibangbus.AiBangBusService;

/**
 * 爱帮公交
 * @author fjing
 *
 */
@Service
public class AiBangBusServiceImpl implements AiBangBusService {

    private static Logger logger = Logger.getLogger(AiBangBusServiceImpl.class);
    
    public static String api_key = "cd2306261560b164317de7a2c5c259a9";//System.getProperty("aibang.api_key");

    @SuppressWarnings("unchecked")
	@Override
    public List<BusLine> searchBusLines(String city, String q) throws Exception {
        List<BusLine> list = null;
        String requestUrl = System.getProperty("aibang.linesUrl");
        
        // with_xys int 是 是否包含坐标点信息 默认为0，不包含各个站点和路线的坐标信息，如果为1，则包含
        // 对城市、关键字进行URL编码
        requestUrl = requestUrl.replace("{app_key}", api_key).replace("{city}",PhilUtil.urlEncodeUTF8(city)).replace("{q}", PhilUtil.urlEncodeUTF8(q)).replaceAll("\\+", "%20");
        logger.info("LinesRequestUrl " + requestUrl);
        // 查询并获取返回结果
        InputStream inputStream = HttpRequestUtil.httpRequestInputStream(requestUrl);
        try {
            // 使用dom4j解析xml字符串   
            SAXReader reader = new SAXReader();  
            Document document = reader.read(inputStream);  
            // 得到xml根元素  
            Element root = document.getRootElement();
            String result_num = root.element("result_num").getText();
            if(!"0".equals(result_num)){
                list = new ArrayList<BusLine>();
                Element lines = root.element("lines");
                List<Element> line = lines.elements("line");
                for(Element e:line){
                    BusLine bl = new BusLine();
                    bl.setName(e.element("name").getText());
                    bl.setInfo(e.element("info").getText());
                    bl.setStats(e.element("stats").getText());
                    list.add(bl);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(inputStream!=null){
                inputStream.close();
            }
        }
        return list;
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<TransferLine> searchBusTrasferLines(String city, String start_addr, String end_addr) throws Exception {
        
        String requestUrl = System.getProperty("aibang.transferUrl");       
        List<TransferLine> list = null;
        
        //对城市、关键字进行URL编码  
        requestUrl = requestUrl.replace("{app_key}",api_key).replace("{city}", PhilUtil.urlEncodeUTF8(city)).replace("{start_addr}", PhilUtil.urlEncodeUTF8(start_addr)).replace("{end_addr}", PhilUtil.urlEncodeUTF8(end_addr)).replaceAll("\\+", "%20").replace("{count}", SystemConstant.TRSANFER_LINE_COUNT);  
        logger.info("TransferRequestUrl "+requestUrl);
        // 查询并获取返回结果  
        InputStream inputStream = HttpRequestUtil.httpRequestInputStream(requestUrl);        
        try {
            // 使用dom4j解析xml字符串   
            SAXReader reader = new SAXReader();  
            Document document = reader.read(inputStream);  
            // 得到xml根元素  
            Element root = document.getRootElement();
            String result_num = root.element("result_num").getText();
            if(!"0".equals(result_num)){
                list = new ArrayList<TransferLine>();
                Element buses = root.element("buses");
                List<Element> bus = buses.elements("bus");         
                for(Element e :bus){         
                    TransferLine tl = new TransferLine();
                    tl.setDist(e.element("dist").getText()+"米");
                    tl.setTime(e.element("time").getText()+"分钟");
                    Element segment = e.element("segments").element("segment");
                    tl.setStart_stat(segment.element("start_stat").getText());
                    tl.setEnd_stat(segment.element("end_stat").getText());
                    tl.setLine_name(segment.element("line_name").getText());
                    tl.setStats(segment.element("stats").getText());
                    tl.setLine_dist(segment.element("line_dist").getText()+"米");
                    tl.setFoot_dist(segment.element("foot_dist").getText()+"米 ");
                    list.add(tl);
                }
            } 
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(inputStream!=null){
                inputStream.close();
            }
        }
        return list;
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<StatsLine> searchBusLineStats(String city, String q) throws Exception {
        
        String requestUrl = System.getProperty("aibang.statsUrl");
        List<StatsLine> list = null;
        //对城市、关键字进行URL编码  
        requestUrl = requestUrl.replace("{app_key}",api_key).replace("{city}", PhilUtil.urlEncodeUTF8(city)).replace("{q}", PhilUtil.urlEncodeUTF8(q)).replaceAll("\\+", "%20");    
        logger.info("TransferRequestUrl "+requestUrl);
        // 查询并获取返回结果  
        InputStream inputStream = HttpRequestUtil.httpRequestInputStream(requestUrl);
        try {
            // 使用dom4j解析xml字符串   
            SAXReader reader = new SAXReader();  
            Document document = reader.read(inputStream);  
            // 得到xml根元素  
            Element root = document.getRootElement();
            String result_num = root.element("result_num").getText();
            if(!"0".equals(result_num)){
                list = new ArrayList<StatsLine>();
                Element stats = root.element("stats");
                List<Element> stat = stats.elements("stat");  
                for(Element e:stat){
                    StatsLine sl = new StatsLine();
                    sl.setName(e.element("name").getText());
                    sl.setLine_names(e.element("line_names").getText());
                    sl.setXy(e.element("xy").getText());
                    list.add(sl);
                }    
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(inputStream!=null){
                inputStream.close();
            }
        }   
        return list;
    }

}
