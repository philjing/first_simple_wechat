package com.phil.wechat.service.custommessage;

public interface CustomMessageSerivce {
	
	public boolean sendCustomMessage(String accessToken, String jsonMsg);

}
