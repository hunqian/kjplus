package com.ykisswx.model;

/**
 * TWxMember entity. @author MyEclipse Persistence Tools
 */

public class WxMemberEbo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 4364297606947852358L;
	private Integer mid;
	private String userName;
	private String showName;
	private String nickName;
	private String userEmail;
	private String userMobile;
	private String mobileCountryCode;
	private String password;
	private String flag;
	private Integer regTime;
	private String userType;

	// Constructors

	/** default constructor */
	public WxMemberEbo() {
	}

	/** minimal constructor */
	public WxMemberEbo(String userName, String nickName, String userMobile, String password, Integer regTime,
			String userType) {
		this.userName = userName;
		this.nickName = nickName;
		this.userMobile = userMobile;
		this.password = password;
		this.regTime = regTime;
		this.userType = userType;
	}

	/** full constructor */
	public WxMemberEbo(String userName, String nickName, String userEmail, String userMobile, String mobileCountryCode,
			String password, Integer regTime, String userType) {
		this.userName = userName;
		this.nickName = nickName;
		this.userEmail = userEmail;
		this.userMobile = userMobile;
		this.mobileCountryCode = mobileCountryCode;
		this.password = password;
		this.regTime = regTime;
		this.userType = userType;
	}

	// Property accessors

	public Integer getMid() {
		return this.mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserMobile() {
		return this.userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getMobileCountryCode() {
		return this.mobileCountryCode;
	}

	public void setMobileCountryCode(String mobileCountryCode) {
		this.mobileCountryCode = mobileCountryCode;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getRegTime() {
		return this.regTime;
	}

	public void setRegTime(Integer regTime) {
		this.regTime = regTime;
	}

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

}