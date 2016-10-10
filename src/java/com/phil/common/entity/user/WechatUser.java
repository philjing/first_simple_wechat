package com.phil.common.entity.user;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 微信用户
 * @author fjing
 * @date  2016-10-13
 */
@Entity
@Table(name="wechat_user")
public class WechatUser extends IdUser {

}
