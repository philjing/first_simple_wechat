package com.phil.common.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.phil.common.entity.base.IdEntity;
import com.phil.common.entity.role.Role;

/**
 * 用户角色
 * 
 * @author fjing
 * @date 2016-10-13
 */
@Entity
@Table(name = "internal_user_role")
public class InternalUserRole extends IdEntity {

	/**
	 * 内部用户ID
	 */
	@Column(name = "internal_user_id")
	private String internalUserId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "internal_user_id", insertable = false, updatable = false)
	private InternalUser internalUser;

	/**
	 * 角色Id
	 */
	@Column(name = "role_id")
	private String roleId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", insertable = false, updatable = false)
	private Role role;

	public String getInternalUserId() {
		return internalUserId;
	}

	public void setInternalUserId(String internalUserId) {
		this.internalUserId = internalUserId;
	}

	public InternalUser getInternalUser() {
		return internalUser;
	}

	public void setInternalUser(InternalUser internalUser) {
		this.internalUser = internalUser;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
