package com.kjplus.eto;

public class UserLogEto {

	private Integer uid;
	private String userType;
	private String opType;
	private String ipAddr;

	// construct

	public UserLogEto() {
		super();
	}

	public UserLogEto(Integer uid, String userType, String opType, String ipAddr) {
		super();
		this.uid = uid;
		this.userType = userType;
		this.opType = opType;
		this.ipAddr = ipAddr;
	}

	// Property accessors
	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getOpType() {
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

}
