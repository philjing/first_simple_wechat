package com.phil.common.entity.user;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 内部用户  可以进入后台控制系统
 * @author fjing
 * @date  2016-10-13
 */
@Entity
@Table(name="internal_user")
public class InternalUser extends IdUser {
	
	
	
}
