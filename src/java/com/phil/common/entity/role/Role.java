package com.phil.common.entity.role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import com.phil.common.entity.base.IdEntity;

/**
 * 角色
 * @author fjing
 * @date  2016-10-13
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "sys_role")
public class Role extends IdEntity {

    private static final String SQL_CHILD_COUNT = "select COUNT(*) from sys_role d where d.parent_id = id";

    /** 是否为顶层 */
    @Formula("(case when (" + SQL_CHILD_COUNT + ") = 0  then 0 else 1 end)")
    private Boolean isRoot;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色编码
     */
    private String code;

    /**
     * 备注
     */
    private String remark;

    /**
     * 父ID
     */
    @Column(name = "parent_id")
    private String parentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", updatable = false, insertable = false)
    private Role parent;

    @Column(updatable = false)
    private String rolePrefix;

    /**
     * 排序
     */
    private Integer position = 0;

    /** 是否系统角色 0 否 1是 */
    @Column(name = "is_sys")
    private Boolean isSys = Boolean.FALSE;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Role getParent() {
        return parent;
    }

    public void setParent(Role parent) {
        this.parent = parent;
    }

    public String getRolePrefix() {
        return rolePrefix;
    }

    public void setRolePrefix(String rolePrefix) {
        this.rolePrefix = rolePrefix;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Boolean getIsRoot() {
        return isRoot;
    }

    public void setIsRoot(Boolean isRoot) {
        this.isRoot = isRoot;
    }

    public Boolean getIsSys() {
        return isSys;
    }

    public void setIsSys(Boolean isSys) {
        this.isSys = isSys;
    }

}
