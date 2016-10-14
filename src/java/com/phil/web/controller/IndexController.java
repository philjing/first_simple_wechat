package com.phil.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author fjing
 * @date  2016-10-26
 */
@Controller
@RequestMapping("/index")
public class IndexController {
	
	@RequestMapping("/index")
	public String index(){
		
		return "";	
	}
	
	

}
