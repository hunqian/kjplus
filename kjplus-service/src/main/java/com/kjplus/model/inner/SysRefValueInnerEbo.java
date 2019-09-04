package com.kjplus.model.inner;

/**
 * TSysRefValue entity. @author MyEclipse Persistence Tools
 */

public class SysRefValueInnerEbo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5985410422622557276L;
	private Integer id;
	private Integer refId;
	private String refCode;
	private String refName;
	private String refType;
	private String refMemo;
	private String code;
	private String refVl;
	private String name;
	private String extVlType;
	private String flag;

	// Constructors

	/** default constructor */
	public SysRefValueInnerEbo() {
	}

	/** full constructor */
	public SysRefValueInnerEbo(Integer refId, String code, String refVl, String name) {
		this.refId = refId;
		this.code = code;
		this.refVl = refVl;
		this.name = name;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRefId() {
		return this.refId;
	}

	public void setRefId(Integer refId) {
		this.refId = refId;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRefVl() {
		return this.refVl;
	}

	public void setRefVl(String refVl) {
		this.refVl = refVl;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getExtVlType() {
		return extVlType;
	}

	public void setExtVlType(String extVlType) {
		this.extVlType = extVlType;
	}

	public String getRefCode() {
		return refCode;
	}

	public void setRefCode(String refCode) {
		this.refCode = refCode;
	}

	public String getRefName() {
		return refName;
	}

	public void setRefName(String refName) {
		this.refName = refName;
	}

	public String getRefType() {
		return refType;
	}

	public void setRefType(String refType) {
		this.refType = refType;
	}

	public String getRefMemo() {
		return refMemo;
	}

	public void setRefMemo(String refMemo) {
		this.refMemo = refMemo;
	}

}