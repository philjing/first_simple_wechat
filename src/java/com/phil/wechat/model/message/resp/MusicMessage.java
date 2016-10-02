package com.phil.wechat.model.message.resp;

/**
 * 音乐消息
 * @author fjing
 *
 */
public class MusicMessage extends BaseRespMessage {
	
	private Music music;

	public Music getMusic() {
		return music;
	}

	public void setMusic(Music music) {
		this.music = music;
	}
}
