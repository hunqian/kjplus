package com.kjplus.model;

import java.util.*;

/**
 * TUser entity. @author MyEclipse Persistence Tools
 */

public class UserEbo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3585851363109955256L;
	private Integer uid;
	private String nickName;
	private String userName;
	private String passWord;
	private String email;
	private String mobileNum;
	private String mobileFlag;
	private String userType;
	private Date regTime;
	private String status;
	private String face;
	private Date effectFrom;
	private Date effectTo;
	private String sourceFlag;
	private Date createTime;
	private Integer orgId;
	private String orgName;
	
	// Constructors

	/** default constructor */
	public UserEbo() {
	}

	/** minimal constructor */
	public UserEbo(String userName, String passWord, String mobileNum, String mobileFlag, String userType,
			Date regTime, String status, Date effectFrom, String sourceFlag, Date createTime) {
		this.userName = userName;
		this.passWord = passWord;
		this.mobileNum = mobileNum;
		this.mobileFlag = mobileFlag;
		this.userType = userType;
		this.regTime = regTime;
		this.status = status;
		this.effectFrom = effectFrom;
		this.sourceFlag = sourceFlag;
		this.createTime = createTime;
	}

	/** full constructor */
	public UserEbo(String nickName, String userName, String passWord, String email, String mobileNum,
			String mobileFlag, String userType, Date regTime, String status, String face, Date effectFrom,
			Date effectTo, String sourceFlag, Date createTime) {
		this.nickName = nickName;
		this.userName = userName;
		this.passWord = passWord;
		this.email = email;
		this.mobileNum = mobileNum;
		this.mobileFlag = mobileFlag;
		this.userType = userType;
		this.regTime = regTime;
		this.status = status;
		this.face = face;
		this.effectFrom = effectFrom;
		this.effectTo = effectTo;
		this.sourceFlag = sourceFlag;
		this.createTime = createTime;
	}

	// Property accessors

	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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

	public String getMobileFlag() {
		return this.mobileFlag;
	}

	public void setMobileFlag(String mobileFlag) {
		this.mobileFlag = mobileFlag;
	}

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
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
		return effectFrom;
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

	public String getSourceFlag() {
		return this.sourceFlag;
	}

	public void setSourceFlag(String sourceFlag) {
		this.sourceFlag = sourceFlag;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	@Override
	public String toString() {
		return "UserEbo [uid=" + uid + ", nickName=" + nickName + ", userName="
				+ userName + ", passWord=" + passWord + ", email=" + email
				+ ", mobileNum=" + mobileNum + ", mobileFlag=" + mobileFlag
				+ ", userType=" + userType + ", regTime=" + regTime
				+ ", status=" + status + ", face=" + face + ", effectFrom="
				+ effectFrom + ", effectTo=" + effectTo + ", sourceFlag="
				+ sourceFlag + ", createTime=" + createTime + ", orgId="
				+ orgId + ", orgName=" + orgName + "]";
	}

	

}