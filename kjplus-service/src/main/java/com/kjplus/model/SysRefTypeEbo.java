package com.kjplus.model;

/**
 * TSysRefType entity. @author MyEclipse Persistence Tools
 */

public class SysRefTypeEbo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -6696770129956939201L;
	private Integer id;
	private String refCode;
	private String refName;
	private String refType;
	private String refMemo;
	private String flag;

	// Constructors

	/** default constructor */
	public SysRefTypeEbo() {
	}

	/** minimal constructor */
	public SysRefTypeEbo(String refCode, String refName, String refType, String flag) {
		this.refCode = refCode;
		this.refName = refName;
		this.refType = refType;
		this.flag = flag;
	}

	/** full constructor */
	public SysRefTypeEbo(String refCode, String refName, String refType, String refMemo, String flag) {
		this.refCode = refCode;
		this.refName = refName;
		this.refType = refType;
		this.refMemo = refMemo;
		this.flag = flag;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRefCode() {
		return this.refCode;
	}

	public void setRefCode(String refCode) {
		this.refCode = refCode;
	}

	public String getRefName() {
		return this.refName;
	}

	public void setRefName(String refName) {
		this.refName = refName;
	}

	public String getRefType() {
		return this.refType;
	}

	public void setRefType(String refType) {
		this.refType = refType;
	}

	public String getRefMemo() {
		return this.refMemo;
	}

	public void setRefMemo(String refMemo) {
		this.refMemo = refMemo;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "SysRefTypeEbo [id=" + id + ", refCode=" + refCode + ", refName=" + refName + ", refType=" + refType
				+ ", refMemo=" + refMemo + ", flag=" + flag + "]";
	}

}