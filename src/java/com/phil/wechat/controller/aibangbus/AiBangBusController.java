package com.phil.wechat.controller.aibangbus;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.phil.wechat.model.aibang.BusLine;
import com.phil.wechat.model.aibang.StatsLine;
import com.phil.wechat.model.aibang.TransferLine;
import com.phil.wechat.service.aibangbus.AiBangBusService;

@Controller
@RequestMapping("/aibangbus")
public class AiBangBusController {
	
	private static Logger logger = Logger.getLogger(AiBangBusController.class);
	
	@Autowired
    private AiBangBusService aiBangBusService;
	
	/**
	 * 查找路线的首页
	 * @return
	 */
	@RequestMapping("/buslines")
	public String busLines(HttpServletRequest request){	
		return "aibangbus/busline";		
	}
	
	/**
	 * 
	 * 查找路线的结果
	 * @author fjing
	 * @param request
	 * @param city
	 * @param line
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/searchbusline")
    public String searchBusLine(HttpServletRequest request ,String city, String line) throws Exception{      
        logger.info("城市 : "+city+", 线路 :"+line);        
        List<BusLine> bls = aiBangBusService.searchBusLines(city, line);
        request.setAttribute("bls", bls);
        return "aibangbus/buslinesResult";
    }
	
	/**
     * 公交换乘查询的首页
     * @return
     */
    @RequestMapping("/transfer")
    public String transfer(HttpServletRequest request){ 
        return "aibangbus/transfer";        
    }
    
    /**
     *
     *  公交换乘查询的结果
     * @author fjing
     * @param request
     * @param city
     * @param start_addr
     * @param end_addr
     * @return
     * @throws Exception
     */
    @RequestMapping("/transferLine")
    public String transferLine(HttpServletRequest request ,String city, String start_addr, String end_addr) throws Exception{      
        logger.info("城市 : "+city+", 起点 :"+start_addr+",终点："+end_addr);
        List<TransferLine> tls = aiBangBusService.searchBusTrasferLines(city, start_addr, end_addr);
        request.setAttribute("tls", tls);
        return "aibangbus/transferResult";
    }
    
    /**
     * 公交站点查询的首页
     * @return
     */
    @RequestMapping("/stats")
    public String stats(HttpServletRequest request){ 
        return "aibangbus/stats";        
    }
    
    /**
     * 
     * 公交站点查询的结果
     * @author fjing
     * @param request
     * @param city
     * @param location
     * @return
     * @throws Exception
     */
    @RequestMapping("/statsLine")
    public String statsLine(HttpServletRequest request ,String city, String location) throws Exception{      
        logger.info("城市 : "+city+", 地点 :"+location);        
        List<StatsLine> sls = aiBangBusService.searchBusLineStats(city, location);
        request.setAttribute("sls", sls);
        return "aibangbus/statsResult";
    }
}
