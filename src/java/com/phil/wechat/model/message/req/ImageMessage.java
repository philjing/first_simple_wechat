package com.phil.wechat.model.message.req;


/**
 * 图片消息
 * @author fjing
 *
 */
public class ImageMessage extends BaseReqMessage {
	
	//图片链接
	private String PicUrl;

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

}
