package com.phil.wechat.model.message.req;

/**
 * 文本消息 
 * @author fjing
 *
 */
public class TextMessage extends BaseReqMessage {
	
	//消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

}

	