package com.phil.wechat.model.customer;

import com.phil.wechat.model.message.resp.TextMessage;

public class CustomerTextMessage extends CustomerMessage{
	
	private TextMessage text;

	public TextMessage getText() {
		return text;
	}

	public void setText(TextMessage text) {
		this.text = text;
	}

}
