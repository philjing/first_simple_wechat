package com.phil.wechat.model.message.resp;

/**
 * 文本消息
 * @author fjing
 *
 */
public class TextMessage extends BaseRespMessage {
	
	//回复的消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

}
