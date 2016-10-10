package com.phil.common.entity.module;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import com.phil.common.entity.base.IdEntity;

/**
 * 模块，菜单
 * @author fjing
 * @date  2016-10-13
 */
@Entity
@Table(name="sys_module")
public class Module extends IdEntity{

	/**
	 * UUID
	 */
	private static final long serialVersionUID = 2204047722672141781L;

	private static final String SQL_CHILD_COUNT = "select COUNT(*) from sys_module d where d.parent_id is null";
	
	/** 是否为顶层 */
	@Formula("(case when (" + SQL_CHILD_COUNT + ") = 0  then 0 else 1 end)")
    private Boolean isRoot;
	
	/** 模块名 */
	private String name;
	
	/** 排序*/
	private Integer position=0;
	
	/** 模块编码 */
	@Column(nullable=false,unique=true)
	private String code;
	
	/** 是否功能*/
	private Boolean ifFunc=false;
	
	/** 图片名*/
	private String picUrl;
	
	/** 描述*/
	private String description;
	
	/** URL*/
	private String funcUrl;
	
	/** 信息框URL*/
	private String frameUrl;
	
	/** 是否可用*/
	private Boolean ifActive=true;
	
	/** 开发者名称*/
	private String devName;
	
	@Column(name="parent_id")
	private String parentId;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parent_id",updatable=false,insertable=false)
	private Module parent;
	
	// 是否为系统模块 0 否 1是
	private Integer isSys;

	public Boolean getIsRoot(){
		return isRoot;
	}

	public void setIsRoot(Boolean isRoot){
		this.isRoot = isRoot;
	}

	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}

	public String getCode(){
		return code;
	}

	public void setCode(String code){
		this.code = code;
	}
	
	public String getDescription(){
		return description;
	}

	public void setDescription(String description){
		this.description = description;
	}
	
	public Integer getPosition(){
		return position;
	}

	public void setPosition(Integer position){
		this.position = position;
	}
	
	public Boolean getIfActive(){
		return ifActive;
	}

	public void setIfActive(Boolean ifActive){
		this.ifActive = ifActive;
	}

	public Boolean getIfFunc(){
		return ifFunc;
	}

	public void setIfFunc(Boolean ifFunc){
		this.ifFunc = ifFunc;
	}

	public String getFuncUrl(){
		return funcUrl;
	}

	public void setFuncUrl(String funcUrl){
		this.funcUrl = funcUrl;
	}

	public String getDevName(){
		return devName;
	}

	public void setDevName(String devName){
		this.devName = devName;
	}

	public String getParentId(){
		return parentId;
	}

	public void setParentId(String parentId){
		this.parentId = parentId;
	}

	public Module getParent(){
		return parent;
	}

	public void setParent(Module parent){
		this.parent = parent;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getFrameUrl() {
		return frameUrl;
	}

	public void setFrameUrl(String frameUrl) {
		this.frameUrl = frameUrl;
	}

	public Integer getIsSys() {
		return isSys;
	}

	public void setIsSys(Integer isSys) {
		this.isSys = isSys;
	}
	
}
