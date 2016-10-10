package com.phil.common.entity.module;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.phil.common.entity.base.IdEntity;
import com.phil.common.entity.role.Role;

/**
 * 角色模块
 * @author fjing
 * @date  2016-10-13
 */
@Entity
@Table(name="sys_role_module")
public class RoleModule extends IdEntity{

	/**
	 * 角色ID
	 */
	@Column(name="role_id")
	private String roleId;
  
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="role_id",updatable=false, insertable=false)
	private Role role;
	
	/**
	 * 模块ID
	 */
	@Column(name="module_id")
	private String moduleId;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="module_id",updatable=false, insertable=false)
	private Module module;
	
	/**
	 * 排序
	 */
	private Integer position;

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

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}
 
}