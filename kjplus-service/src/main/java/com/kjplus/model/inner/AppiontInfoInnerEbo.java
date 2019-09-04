package com.kjplus.model.inner;

import java.io.Serializable;

/**
 * 
 * @author niuzhiwei 预约信息表
 */
public class AppiontInfoInnerEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8885161726272521191L;
	// user表
	private Integer uid;
	private String userName;
	private String userMobile;

	// 居民表
	private String personCode;
	private String personName;
	private String personType;
	private String personIdCard;

	// 预约表
	private String appCode;
	private String appTypeCode;
	private String appTypeName;
	private String appStatus;
	private Integer appStartTime;
	private Integer appendTime;
	// 日历信息表
	private String infoCode;
	private String infoTitle;
	private String infoMemo;
	private Integer infoStartTime;
	private Integer infoEndTime;
	private String infoFlag;

	public AppiontInfoInnerEbo() {
		super();
	}

	public AppiontInfoInnerEbo(Integer uid, String userName, String userMobile, String personCode, String personName,
			String personType, String personIdCard, String appCode, String appTypeCode, String appTypeName,
			String appStatus, Integer appStartTime, Integer appendTime, String infoCode, String infoTitle,
			String infoMemo, Integer infoStartTime, Integer infoEndTime, String infoFlag) {
		super();
		this.uid = uid;
		this.userName = userName;
		this.userMobile = userMobile;
		this.personCode = personCode;
		this.personName = personName;
		this.personType = personType;
		this.personIdCard = personIdCard;
		this.appCode = appCode;
		this.appTypeCode = appTypeCode;
		this.appTypeName = appTypeName;
		this.appStatus = appStatus;
		this.appStartTime = appStartTime;
		this.appendTime = appendTime;
		this.infoCode = infoCode;
		this.infoTitle = infoTitle;
		this.infoMemo = infoMemo;
		this.infoStartTime = infoStartTime;
		this.infoEndTime = infoEndTime;
		this.infoFlag = infoFlag;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getPersonCode() {
		return personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPersonType() {
		return personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}

	public String getPersonIdCard() {
		return personIdCard;
	}

	public void setPersonIdCard(String personIdCard) {
		this.personIdCard = personIdCard;
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public String getAppTypeCode() {
		return appTypeCode;
	}

	public void setAppTypeCode(String appTypeCode) {
		this.appTypeCode = appTypeCode;
	}

	public String getAppTypeName() {
		return appTypeName;
	}

	public void setAppTypeName(String appTypeName) {
		this.appTypeName = appTypeName;
	}

	public String getAppStatus() {
		return appStatus;
	}

	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}

	public Integer getAppStartTime() {
		return appStartTime;
	}

	public void setAppStartTime(Integer appStartTime) {
		this.appStartTime = appStartTime;
	}

	public Integer getAppendTime() {
		return appendTime;
	}

	public void setAppendTime(Integer appendTime) {
		this.appendTime = appendTime;
	}

	public String getInfoCode() {
		return infoCode;
	}

	public void setInfoCode(String infoCode) {
		this.infoCode = infoCode;
	}

	public String getInfoTitle() {
		return infoTitle;
	}

	public void setInfoTitle(String infoTitle) {
		this.infoTitle = infoTitle;
	}

	public String getInfoMemo() {
		return infoMemo;
	}

	public void setInfoMemo(String infoMemo) {
		this.infoMemo = infoMemo;
	}

	public Integer getInfoStartTime() {
		return infoStartTime;
	}

	public void setInfoStartTime(Integer infoStartTime) {
		this.infoStartTime = infoStartTime;
	}

	public Integer getInfoEndTime() {
		return infoEndTime;
	}

	public void setInfoEndTime(Integer infoEndTime) {
		this.infoEndTime = infoEndTime;
	}

	public String getInfoFlag() {
		return infoFlag;
	}

	public void setInfoFlag(String infoFlag) {
		this.infoFlag = infoFlag;
	}

	@Override
	public String toString() {
		return "AppiontInfoInnerEbo [uid=" + uid + ", userName=" + userName + ", userMobile=" + userMobile
				+ ", personCode=" + personCode + ", personName=" + personName + ", personType=" + personType
				+ ", personIdCard=" + personIdCard + ", appCode=" + appCode + ", appTypeCode=" + appTypeCode
				+ ", appTypeName=" + appTypeName + ", appStatus=" + appStatus + ", appStartTime=" + appStartTime
				+ ", appendTime=" + appendTime + ", infoCode=" + infoCode + ", infoTitle=" + infoTitle + ", infoMemo="
				+ infoMemo + ", infoStartTime=" + infoStartTime + ", infoEndTime=" + infoEndTime + ", infoFlag="
				+ infoFlag + "]";
	}

}
