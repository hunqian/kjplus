package com.ykisswx.model;

/**
 * TWxAccount entity. @author MyEclipse Persistence Tools
 */

public class WxAccountEbo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3337396594173138197L;
	// Fields
	private Integer id;
	private Integer orgid;
	private String account;
	private String name;
	private String introduction;
	private String msgServiceName;
	private String msgService;
	private Integer msgLeft;
	private String msgSignature;
	private String appid;
	private String appsecret;
	private String url;
	private String token;
	private String encoaseskeyding;
	private Integer grade;
	private String type;
	private String code;
	private String photo;
	private String sessCode;
	private String webDomain;
	private String accCode;
	private String accKey;
	private Integer accLevel;
	private String mode;
	

	// Constructors

	/** default constructor */
	public WxAccountEbo() {
	}

	/** minimal constructor */
	public WxAccountEbo(String account, String name, String appid, String appsecret, String token, String code) {
		this.account = account;
		this.name = name;
		this.appid = appid;
		this.appsecret = appsecret;
		this.token = token;
		this.code = code;
	}

	/** full constructor */
	public WxAccountEbo(Integer orgid, String account, String name, String introduction, String msgServiceName,
			String msgService, Integer msgLeft, String msgSignature, String appid, String appsecret, String url,
			String token, String encoaseskeyding, Integer grade, String type, String code, String photo,
			String sessCode, String mode) {
		this.orgid = orgid;
		this.account = account;
		this.name = name;
		this.introduction = introduction;
		this.msgServiceName = msgServiceName;
		this.msgService = msgService;
		this.msgLeft = msgLeft;
		this.msgSignature = msgSignature;
		this.appid = appid;
		this.appsecret = appsecret;
		this.url = url;
		this.token = token;
		this.encoaseskeyding = encoaseskeyding;
		this.grade = grade;
		this.type = type;
		this.code = code;
		this.photo = photo;
		this.sessCode = sessCode;
		this.mode = mode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrgid() {
		return orgid;
	}

	public void setOrgid(Integer orgid) {
		this.orgid = orgid;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
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

	public String getMsgServiceName() {
		return msgServiceName;
	}

	public void setMsgServiceName(String msgServiceName) {
		this.msgServiceName = msgServiceName;
	}

	public String getMsgService() {
		return msgService;
	}

	public void setMsgService(String msgService) {
		this.msgService = msgService;
	}

	public Integer getMsgLeft() {
		return msgLeft;
	}

	public void setMsgLeft(Integer msgLeft) {
		this.msgLeft = msgLeft;
	}

	public String getMsgSignature() {
		return msgSignature;
	}

	public void setMsgSignature(String msgSignature) {
		this.msgSignature = msgSignature;
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

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getAccCode() {
		return accCode;
	}

	public void setAccCode(String accCode) {
		this.accCode = accCode;
	}

	public String getAccKey() {
		return accKey;
	}

	public void setAccKey(String accKey) {
		this.accKey = accKey;
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

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public Integer getAccLevel() {
		return accLevel;
	}

	public void setAccLevel(Integer accLevel) {
		this.accLevel = accLevel;
	}
}