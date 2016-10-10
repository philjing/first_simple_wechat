package com.phil.common.entity.mail;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 已删除邮件
 * @author fjing
 * @date  2016-10-13
 */
@Entity
@Table(name="mail_trashbox")
public class Trashbox extends Mailbox {

}
