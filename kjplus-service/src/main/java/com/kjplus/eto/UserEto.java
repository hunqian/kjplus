package com.kjplus.eto;

import java.util.*;

import com.kjplus.constant.Constant;
import com.mq.util.DateUtil;

public class UserEto {

	// Fields

	private String nickName;
	// 如果userName为空，则为用户随机分配一个
	private String userName;
	private String passWord;
	private String email;
	// 当前电话号码可以为空
	private String mobileNum;
	private String mobileFlag = Constant.FLAG_YES;
	// 用户类型，U普通用户/D医生用户
	private String userType = "U";
	private Date regTime;
	private String status = Constant.FLAG_YES;
	private String face;
	private Date effectFrom = DateUtil.curDateOffset(0);
	private Date effectTo;
	// 来源标志,W微信/U自注册
	private String sourceFlag = "U";
	private Integer orgId = 0;

	// Constructors

	/** default constructor */
	public UserEto() {
	}

	// Property accessors
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

	public String getSourceFlag() {
		return this.sourceFlag;
	}

	public void setSourceFlag(String sourceFlag) {
		this.sourceFlag = sourceFlag;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

}