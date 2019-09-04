package com.kjplus.model;

import java.util.Date;

/**
 * TAdminUser entity. @author MyEclipse Persistence Tools
 */

public class AdminUserEbo implements java.io.Serializable {

	private static final long serialVersionUID = -59958582993029706L;
	// Fields
	private Integer uid;
	private String userName;
	private String passWord;
	private String email;
	private String mobileNum;
	private Date regTime;
	private String status;
	private String userType;
	private String face;
	private Date effectFrom;
	private Date effectTo;
	private String nickName;
	private Integer orgId;
	private Date createTime;

	// Constructors

	/** default constructor */
	public AdminUserEbo() {
	}

  	// Property accessors

	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return this.passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNum() {
		return this.mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public Date getRegTime() {
		return this.regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFace() {
		return this.face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public Date getEffectFrom() {
		return this.effectFrom;
	}

	public void setEffectFrom(Date effectFrom) {
		this.effectFrom = effectFrom;
	}

	public Date getEffectTo() {
		return this.effectTo;
	}

	public void setEffectTo(Date effectTo) {
		this.effectTo = effectTo;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getOrgid() {
		return this.orgId;
	}

	public void setOrgid(Integer orgId) {
		this.orgId = orgId;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}