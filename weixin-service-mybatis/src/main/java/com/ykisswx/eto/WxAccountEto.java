package com.ykisswx.eto;

import com.ykisswx.annotation.Validation;

/**
 * TWxAccount entity. @author MyEclipse Persistence Tools
 */

public class WxAccountEto {

	//微信公众号code长度
	public static int MAX_ACCCODE_LEN = 32;
	//sesCode长度
	public static int MAX_SEECODE_LEN = 32;
	//token长度
	public static int MAX_TOKEN_LEN = 20;
	
	// Fields
	private Integer orgid;
	@Validation
	private String account;
	@Validation
	private String name;
	private String introduction;
	@Validation
	private String appid;
	@Validation
	private String appsecret;
	private String url;
	private String token;
	private String encoaseskeyding;
	private Integer grade = 1;
	private String type;
	//Secure安全/Text明文/Compatible兼容
	private String mode = "T";
	private String webDomain;

	// Constructors

	/** default constructor */
	public WxAccountEto() {
	}

	// Property accessors

	public Integer getOrgid() {
		return this.orgid;
	}

	public void setOrgid(Integer orgid) {
		this.orgid = orgid;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduction() {
		return this.introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getAppid() {
		return this.appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAppsecret() {
		return this.appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEncoaseskeyding() {
		return this.encoaseskeyding;
	}

	public void setEncoaseskeyding(String encoaseskeyding) {
		this.encoaseskeyding = encoaseskeyding;
	}

	public Integer getGrade() {
		return this.grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getWebDomain() {
		return webDomain;
	}

	public void setWebDomain(String webDomain) {
		this.webDomain = webDomain;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

}