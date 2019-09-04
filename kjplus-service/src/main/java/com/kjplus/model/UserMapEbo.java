package com.kjplus.model;

/**
 * TAdminUser entity. @author songyuebin
 */

public class UserMapEbo implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = -8718649528748451604L;
	private Integer id;
	private Integer mainId;
	private String  mainType;
	private Integer uid;
	private String userType;
	
	// Constructors
	public UserMapEbo() {
		super();
	}

	// Property accessors
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMainId() {
		return mainId;
	}

	public void setMainId(Integer mainId) {
		this.mainId = mainId;
	}

	public String getMainType() {
		return mainType;
	}

	public void setMainType(String mainType) {
		this.mainType = mainType;
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