package com.phil.wechat.model.news;

import java.util.List;

import com.phil.wechat.model.message.resp.Article;

/**
 * 新闻JSON组装的类
 * 
 * @author fjing
 * 
 */
public class NewsResult {

	private String code;
	private String msg;
	private List<Article> newslist; //新闻列表

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<Article> getNewslist() {
		return newslist;
	}

	public void setNewslist(List<Article> newslist) {
		this.newslist = newslist;
	}

}
