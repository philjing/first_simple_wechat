package com.phil.common.framework.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.CharacterEncodingFilter;

public class EXTCharacterEncodingFilter extends CharacterEncodingFilter {
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String url = requestUri.substring(contextPath.length());
        String stringurl = "/document/upload";
        if(stringurl.contains(url)){
            request.setCharacterEncoding("GB2312");
            response.setCharacterEncoding("GB2312");
        }else{
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
        }
        filterChain.doFilter(request, response);
    }
}
