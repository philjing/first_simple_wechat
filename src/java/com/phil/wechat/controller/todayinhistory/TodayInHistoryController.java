package com.phil.wechat.controller.todayinhistory;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.phil.wechat.service.todayinhistory.TodayInHistoryService;

@Controller
@RequestMapping("/todayinhistory")
public class TodayInHistoryController {
	
	//private static Logger logger = Logger.getLogger(TodayInHistoryController.class);
	
	@Autowired
    private TodayInHistoryService todayInHistoryService;
	
	@RequestMapping("/view")
	public String view(HttpServletRequest request){
		List<String> list = todayInHistoryService.getTodayInHistoryInfoList();
		request.setAttribute("list", list);
		return "todayinhistory/todayinhistory";
	}

}
