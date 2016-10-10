package com.phil.common.entity.user;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 外部用户
 * @author fjing
 * @date  2016-10-13
 */
@Entity
@Table(name="external_user")
public class ExternalUser extends IdUser {
	
}
