package com.phil.common.entity.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * 
 * @author fjing
 * @date  2016-10-10
 */
@MappedSuperclass
public class IdEntity extends BaseEntity implements Serializable {

	/**
	 * create time
	 */
	@Column(updatable = false)
	protected Date creatTime = new Date();

	/**
	 *  create user
	 */
	@Column(updatable = false)
	protected String creatUser;

	/**
	 * the last update time
	 */
	@Column(insertable = false)
	protected Date updateTime = new Date();

	/**
	 * the last update user
	 */
	@Column(insertable = false)
	protected String updateUser;

	@Version
	protected Integer version;

	/**
	 * is deleted
	 */
	protected Boolean isDelete = false;

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public String getCreatUser() {
		return creatUser;
	}

	public void setCreatUser(String creatUser) {
		this.creatUser = creatUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

}
