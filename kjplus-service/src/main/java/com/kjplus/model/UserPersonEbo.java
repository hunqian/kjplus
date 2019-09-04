package com.kjplus.model;

import java.util.Date;

/**
 * TAdminUser entity. @author MyEclipse Persistence Tools
 */

public class UserPersonEbo implements java.io.Serializable {

	private static final long serialVersionUID = -59958582993029706L;
	// Fields
	private Integer id;
	private Integer uid;
	private String userType;
	private Integer prsnId;
	private Integer relationTypeId;
	private Date createTime;

	/** default constructor */
	public UserPersonEbo() {
	}

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

	public Integer getPrsnId() {
		return prsnId;
	}

	public void setPrsnId(Integer prsnId) {
		this.prsnId = prsnId;
	}

	public Integer getRelationTypeId() {
		return relationTypeId;
	}

	public void setRelationTypeId(Integer relationTypeId) {
		this.relationTypeId = relationTypeId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}