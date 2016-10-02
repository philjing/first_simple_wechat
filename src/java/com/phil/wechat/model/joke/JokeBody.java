package com.phil.wechat.model.joke;

import java.util.List;

/**
 * 
 * @author fjing
 * 
 */
public class JokeBody {

	private String allNum;
	private String allPages;
	private String currentPage;
	private String maxResult;
	private List<JokeContent> contentlist;

	public String getAllNum() {
		return allNum;
	}

	public void setAllNum(String allNum) {
		this.allNum = allNum;
	}

	public String getAllPages() {
		return allPages;
	}

	public void setAllPages(String allPages) {
		this.allPages = allPages;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(String maxResult) {
		this.maxResult = maxResult;
	}

	public List<JokeContent> getContentlist() {
		return contentlist;
	}

	public void setContentlist(List<JokeContent> contentlist) {
		this.contentlist = contentlist;
	}

}
