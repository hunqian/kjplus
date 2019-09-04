package com.kjplus.model;

/**
 * TAdminUser entity. @author songyuebin
 */

public class AdminUserMapEbo implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = -8718649528748451604L;
	private Integer id;
	private Integer staffId;
	private Integer uid;
	private String userType;

	// Constructors
	
	public AdminUserMapEbo() {
		super();
	}

  	// Property accessors
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
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

}