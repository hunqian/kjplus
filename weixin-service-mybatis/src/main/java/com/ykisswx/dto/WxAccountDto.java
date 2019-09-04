package com.ykisswx.dto;

import java.io.Serializable;

public class WxAccountDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6249608229242539324L;
	private Integer accid;
	private Integer orgid;
	//名称
	private String account;
	//别名
	private String name;
	private String introduction;
	//wx保留
	private String appid;
	//wx保留
	private String appsecret;
	private String url;
	//wx保留
	private String token;
	//wx保留
	private String encoaseskeyding;
	//private Integer grade;
	//B订阅号S服务号
	private String type;
	private String mode;
	//用于识别从wx平台回来的微信公共号请求推送
	private String sessCode;
	//只是为了标识对应微信公共号的后台服务域名
	private String webDomain;
	//和accid一一对应，可以作为accid的对照使用
	private String accCode;

	public WxAccountDto(Integer accid, String name, String introduction, String url, String token,
			String encoaseskeyding, String mode) {
		super();
		this.accid = accid;
		this.name = name;
		this.introduction = introduction;
		this.url = url;
		this.token = token;
		this.encoaseskeyding = encoaseskeyding;
		this.mode = mode;
	}

	public WxAccountDto(Integer accid, String name) {
		super();
		this.accid = accid;
		this.name = name;
	}

	public WxAccountDto() {
		super();
	}

	public Integer getAccid() {
		return accid;
	}

	public void setAccid(Integer accid) {
		this.accid = accid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEncoaseskeyding() {
		return encoaseskeyding;
	}

	public void setEncoaseskeyding(String encoaseskeyding) {
		this.encoaseskeyding = encoaseskeyding;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAppsecret() {
		return appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}

	public String getSessCode() {
		return sessCode;
	}

	public void setSessCode(String sessCode) {
		this.sessCode = sessCode;
	}

	public String getWebDomain() {
		return webDomain;
	}

	public void setWebDomain(String webDomain) {
		this.webDomain = webDomain;
	}

	public String getAccCode() {
		return accCode;
	}

	public void setAccCode(String accCode) {
		this.accCode = accCode;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getOrgid() {
		return orgid;
	}

	public void setOrgid(Integer orgid) {
		this.orgid = orgid;
	}

}
