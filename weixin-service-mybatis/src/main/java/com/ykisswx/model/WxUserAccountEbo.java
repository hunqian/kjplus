package com.ykisswx.model;

import java.util.Date;

/**
 * TWxUserAccount entity. @author MyEclipse Persistence Tools
 */

public class WxUserAccountEbo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8650625126816596235L;
	private Integer id;
	private Integer userId;
	private Integer accid;
	private String flag;
	private Date createTime;
	private String unionid;

	// Constructors

	/** default constructor */
	public WxUserAccountEbo() {
	}

	/** minimal constructor */
	public WxUserAccountEbo(Integer userId, Integer accid, Date createTime) {
		this.userId = userId;
		this.accid = accid;
		this.createTime = createTime;
	}

	/** full constructor */
	public WxUserAccountEbo(Integer userId, Integer accid, String flag, Date createTime) {
		this.userId = userId;
		this.accid = accid;
		this.flag = flag;
		this.createTime = createTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getAccid() {
		return this.accid;
	}

	public void setAccid(Integer accid) {
		this.accid = accid;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUnionid() {
		return this.unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
}