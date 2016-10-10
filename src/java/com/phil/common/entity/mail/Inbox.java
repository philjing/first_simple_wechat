package com.phil.common.entity.mail;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.phil.common.entity.user.InternalUser;

/**
 * 收件箱
 * @author fjing
 * @date  2016-10-13
 */
@Entity
@Table(name="mail_inbox")
public class Inbox extends Mailbox {
    
    

}
