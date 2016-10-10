package com.phil.common.entity.upload;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.phil.common.entity.base.IdEntity;

/**
 * 附件表
 * 
 * @author fjing
 * @date 2016-10-14
 */
@Entity
@Table(name = "web_attach")
public class Attach extends IdEntity {

	/**
	 * 文件名
	 */
	private String fileName;
	/**
	 * 文件路径
	 */
	private String path;
	/**
	 * 文件类别,文章附件?图片附件?
	 */
	private String category;
	/**
	 * 文件类型,后缀
	 */
	private String ext;
	/**
	 * 用户ID,当前操作用户的ID
	 */
	private String userId;
	/**
	 * 描述
	 */
	private String remark;
	
	/**
	 * 所属的className
	 */
	private String ownerClassName;
	
	/**
	 * 文章id,用户id
	 */
	private String ownerId;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOwnerClassName() {
		return ownerClassName;
	}

	public void setOwnerClassName(String ownerClassName) {
		this.ownerClassName = ownerClassName;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	
}
