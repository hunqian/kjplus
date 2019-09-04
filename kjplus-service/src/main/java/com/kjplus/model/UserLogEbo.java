package com.kjplus.model;

public class UserLogEbo implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 439008522246895421L;
	private Integer id;
	private Integer uid;
	private String userType;
	private String opType;
	private String ipAddr;
	private Integer opTime;

	// construct
	public UserLogEbo() {
		super();
	}

	public UserLogEbo(Integer uid, String userType, String opType, String ipAddr, Integer opTime) {
		super();
		this.uid = uid;
		this.userType = userType;
		this.opType = opType;
		this.ipAddr = ipAddr;
		this.opTime = opTime;
	}

	// Property accessors

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public Integer getopTime() {
		return opTime;
	}

	public void setopTime(Integer opTime) {
		this.opTime = opTime;
	}

}
