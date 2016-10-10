package com.phil.common.entity.user;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.phil.common.entity.base.IdEntity;

/**
 * 
 * @author fjing
 * @date  2016-10-13
 */
@MappedSuperclass
public class IdUser extends IdEntity{
	
	/**
	 * 昵称(唯一的),昵称为空显示用户名
	 */
	@Column(nullable=false,unique=true)
	private String nickName;
	
	/**
	 * 用户名,登录名(不可更改)
	 */
	@Column(nullable=false,unique=true)
	private String userName;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 邮件地址
	 */
	@Column(nullable=false,unique=true)
	private String email;
	
	/**
	 * 手机号
	 */
	private String mobile;
	
	/**
	 * 账号是否锁定
	 */
	private Boolean isDisabled;
	
	/**
	 * 登陆失败次数
	 */
	private Integer loginFailedCount;
	
	/**
	 * 登陆IP地址
	 */
	private String ip;

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Boolean getIsDisabled() {
		return isDisabled;
	}

	public void setIsDisabled(Boolean isDisabled) {
		this.isDisabled = isDisabled;
	}

	public Integer getLoginFailedCount() {
		return loginFailedCount;
	}

	public void setLoginFailedCount(Integer loginFailedCount) {
		this.loginFailedCount = loginFailedCount;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
}
