package com.kjplus.eto;

import java.util.Date;

import org.ybk.basic.util.DateUtil;

import com.kjplus.annotation.Validation;
import com.kjplus.constant.Constant;

public class AdminUserEto {
	
	public static final int MAX_USERNAME_LEN = 12;
	
	private String userName;
	private String passWord;
	private String email = "";
	private String mobileNum;
	private Date regTime = DateUtil.newTime();
	private String status = Constant.FLAG_YES;
	//admin用户类型,A管理员/G普通用户
	private String userType = Constant.ADMIN_USER_TYPE_GNRL; 
	private String face;
	private Date effectFrom = DateUtil.newTime();
	private Date effectTo;
	private String nickName;
	private Integer orgId;
	@Validation
	private Date createTime = DateUtil.newTime();
	
	// Constructors
	
	public AdminUserEto() {
		super();
	}
	
	public AdminUserEto(String userName, String passWord, String email, String mobileNum, Date regTime, String status,
			String userType, String face, Date effectFrom, Date effectTo, String nickName, Integer orgId,
			Date createTime) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.email = email;
		this.mobileNum = mobileNum;
		this.regTime = regTime;
		this.status = status;
		this.userType = userType;
		this.face = face;
		this.effectFrom = effectFrom;
		this.effectTo = effectTo;
		this.nickName = nickName;
		this.orgId = orgId;
		this.createTime = createTime;
	}

	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
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

	public Date getEffectFrom() {
		return effectFrom;
	}

	public void setEffectFrom(Date effectFrom) {
		this.effectFrom = effectFrom;
	}

	public Date getEffectTo() {
		return effectTo;
	}

	public void setEffectTo(Date effectTo) {
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	// Property accessors


}