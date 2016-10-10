package com.phil.common.entity.mail;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.phil.common.entity.base.IdEntity;
import com.phil.common.entity.user.InternalUser;

/**
 * 邮件基类
 * @author fjing
 * @date  2016-10-13
 */
@MappedSuperclass
public class Mailbox extends IdEntity{
	/**
	 * 收件人地址
	 */
	private String toAddress;
	
	/**
	 * 抄送人地址
	 */
	private String ccAddress;
	
	/**
	 * 邮件主题
	 */
	private String subject;
	
	/**
	 * 邮件内容
	 */
	private String content;
	
	/**
	 * 是否是
	 */
	private Boolean startarget;
	
	/**
	 * 标签
	 */
	
	
	/**
	 * 类别
	 */

	
	/**
     * 内部用户ID
     */
    @Column(name = "internal_user_id")
    private String internalUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "internal_user_id", insertable = false, updatable = false)
    private InternalUser user;
	
	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getCcAddress() {
		return ccAddress;
	}

	public void setCcAddress(String ccAddress) {
		this.ccAddress = ccAddress;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getStartarget() {
		return startarget;
	}

	public void setStartarget(Boolean startarget) {
		this.startarget = startarget;
	}

	public String getInternalUserId() {
		return internalUserId;
	}

	public void setInternalUserId(String internalUserId) {
		this.internalUserId = internalUserId;
	}

	public InternalUser getUser() {
		return user;
	}

	public void setUser(InternalUser user) {
		this.user = user;
	}
}
