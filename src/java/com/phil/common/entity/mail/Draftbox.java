package com.phil.common.entity.mail;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 草稿箱
 * @author fjing
 * @date  2016-10-13
 */
@Entity
@Table(name="mail_draftbox")
public class Draftbox extends Mailbox {

}
