package com.phil.wechat.model.joke;

/**
 * 笑话返回的Result类
 * 
 * @author fjing
 * 
 */
public class JokeResult {
	private String showapi_res_code;
	private String showapi_res_error;
	private JokeBody showapi_res_body;

	public String getShowapi_res_code() {
		return showapi_res_code;
	}

	public void setShowapi_res_code(String showapi_res_code) {
		this.showapi_res_code = showapi_res_code;
	}

	public String getShowapi_res_error() {
		return showapi_res_error;
	}

	public void setShowapi_res_error(String showapi_res_error) {
		this.showapi_res_error = showapi_res_error;
	}

	public JokeBody getShowapi_res_body() {
		return showapi_res_body;
	}

	public void setShowapi_res_body(JokeBody showapi_res_body) {
		this.showapi_res_body = showapi_res_body;
	}

}
