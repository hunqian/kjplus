package com.kjplus.dto;

/**
 * TAdminUser entity. @author MyEclipse Persistence Tools
 */

public class AdminUserDto implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2061270096906132409L;
	private Integer uid;
	private String userName;
	private String email;
	private String mobileNum;
	private String regTime;
	private String status;
	private String userType;
	private String face;
	private String effectFrom;
	private String effectTo;
	private String nickName;
	private Integer orgId;
	private String orgName;
	private String createTime;

	// Constructors

	/** default constructor */
	public AdminUserDto() {
	}

	/** minimal constructor */
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

	public String getRegTime() {
		return regTime;
	}

	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public String getEffectFrom() {
		return effectFrom;
	}

	public void setEffectFrom(String effectFrom) {
		this.effectFrom = effectFrom;
	}

	public String getEffectTo() {
		return effectTo;
	}

	public void setEffectTo(String effectTo) {
		this.effectTo = effectTo;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}