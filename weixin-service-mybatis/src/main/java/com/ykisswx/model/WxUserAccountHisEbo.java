package com.ykisswx.model;

/**
 * TWxUserAccountMember entity. @author MyEclipse Persistence Tools
 */

public class WxUserAccountHisEbo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8645551980012242897L;
	private Integer id;
	private Integer userId;
	private Integer accId;
	private Integer opseq;
	private String state;
	private Integer optime;

	// Constructors

	/** default constructor */
	public WxUserAccountHisEbo() {
	}

	/** full constructor */
	public WxUserAccountHisEbo(Integer userId, Integer accId, Integer opseq,String state, Integer optime) {
		this.userId = userId;
		this.accId = accId;
		this.opseq = opseq;
		this.state = state;
		this.optime = optime;
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

	public Integer getAccId() {
		return this.accId;
	}

	public void setAccId(Integer accId) {
		this.accId = accId;
	}

	public Integer getOpseq() {
		return this.opseq;
	}

	public void setOpseq(Integer opseq) {
		this.opseq = opseq;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getOptime() {
		return this.optime;
	}

	public void setOptime(Integer optime) {
		this.optime = optime;
	}

}