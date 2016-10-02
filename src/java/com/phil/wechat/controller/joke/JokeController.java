package com.phil.wechat.controller.joke;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.phil.wechat.service.joke.JokeService;

@Controller
@RequestMapping("/joke")
public class JokeController {
    
    @Autowired
    private JokeService jokeService;
    
    @RequestMapping(method = { RequestMethod.GET,RequestMethod.POST })
    public String joke(HttpServletRequest request){
        
        String url = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
        url += "/joke";
        
        String result = jokeService.searchJokes();
        result += "<a href=\""+ url +"\">随机一次";   
        request.setAttribute("result", result);
        return "joke/joke";
        
    }
    
    

}
