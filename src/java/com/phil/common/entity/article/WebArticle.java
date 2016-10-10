package com.phil.common.entity.article;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.phil.common.entity.base.IdEntity;
import com.phil.common.entity.dict.WebCategory;

/**
 * 文章表
 * @author fjing
 * @date  2016-10-14
 */
@Entity
@Table(name = "web_article")
public class WebArticle extends IdEntity {
	
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 文章类别Id
	 */
	@Column(name = "category_id")
	private String categoryId;
	
	/**
	 * 关联category
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", updatable = false, insertable = false)
	private WebCategory category;
	
	/**
	 * 文章内容
	 */
	private String content;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public WebCategory getCategory() {
		return category;
	}

	public void setCategory(WebCategory category) {
		this.category = category;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
