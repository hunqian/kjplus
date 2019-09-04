package com.ykisswx.dto;

public class WxToken {

	private String appid;
	private String secret;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public WxToken(String appid, String secret) {
		super();
		this.appid = appid;
		this.secret = secret;
	}

	public WxToken() {
		super();
	}
}
