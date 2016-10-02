package com.phil.wechat.model.message.resp;

import java.util.List;

/**
 * 图文消息
 * @author fjing
 *
 */
public class NewsMessage extends BaseRespMessage {

	//图片消息个数，限制在10条以内
	private int ArticleCount;
	
	private List<Article> Articles;

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public List<Article> getArticles() {
		return Articles;
	}

	public void setArticles(List<Article> articles) {
		Articles = articles;
	}
	
}
