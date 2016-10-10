package com.phil.common.entity.upload;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.phil.common.entity.base.IdEntity;

/**
 * 附件操作历史表
 * 
 * @author fjing
 * @date 2016-10-14
 */
@Entity
@Table(name = "web_attach_history")
public class AttachHistory extends IdEntity {

	/**
	 * 附件的ID
	 */
	@Column(name = "attach_id")
	private String attachId;

	/**
	 * 关联attach表
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "attach_id", updatable = false, insertable = false)
	private Attach attach;

	/**
	 * 操作类型,删除,修改
	 */
	private String operation;

	/**
	 * 来源，文章，用户
	 */
	private String source;

	public String getAttachId() {
		return attachId;
	}

	public void setAttachId(String attachId) {
		this.attachId = attachId;
	}

	public Attach getAttach() {
		return attach;
	}

	public void setAttach(Attach attach) {
		this.attach = attach;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
