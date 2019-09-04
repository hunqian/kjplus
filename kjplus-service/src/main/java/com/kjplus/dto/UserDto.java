package com.kjplus.dto;

public class UserDto {
	private int uid;
	private int orgid;
	private String orgName;
	private String nickName;
	private String userName;
	private String email;
	private String mobileNum;
	private String mobileFlag;
	private String userType;
	private String regTime;
	private String status;
	private String face;
	private String effectFrom;
	private String effectTo;
	private String sourceFlag;
	private String createTime;

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getOrgid() {
		return orgid;
	}

	public void setOrgid(int orgid) {
		this.orgid = orgid;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public String getMobileFlag() {
		return mobileFlag;
	}

	public void setMobileFlag(String mobileFlag) {
		this.mobileFlag = mobileFlag;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public String getSourceFlag() {
		return sourceFlag;
	}

	public void setSourceFlag(String sourceFlag) {
		this.sourceFlag = sourceFlag;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRegTime() {
		return regTime;
	}

	public void setRegTime(String regTime) {
		this.regTime = regTime;
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

	@Override
	public String toString() {
		return "UserDto [uid=" + uid + ", orgid=" + orgid + ", orgName=" + orgName + ", nickName=" + nickName
				+ ", userName=" + userName + ", email=" + email + ", mobileNum=" + mobileNum + ", mobileFlag="
				+ mobileFlag + ", userType=" + userType + ", regTime=" + regTime + ", status=" + status + ", face="
				+ face + ", effectFrom=" + effectFrom + ", effectTo=" + effectTo + ", sourceFlag=" + sourceFlag
				+ ", createTime=" + createTime + "]";
	}

}
