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
@Table(name = "external_user_role")
public class ExternalUserRole extends IdEntity {

	/**
	 * 内部用户ID
	 */
	@Column(name = "external_user_id")
	private String externalUserId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "external_user_id", insertable = false, updatable = false)
	private ExternalUser externalUser;

	/**
	 * 角色Id
	 */
	@Column(name = "role_id")
	private String roleId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", insertable = false, updatable = false)
	private Role role;

	public String getExternalUserId() {
		return externalUserId;
	}

	public void setExternalUserId(String externalUserId) {
		this.externalUserId = externalUserId;
	}

	public ExternalUser getExternalUser() {
		return externalUser;
	}

	public void setExternalUser(ExternalUser externalUser) {
		this.externalUser = externalUser;
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
