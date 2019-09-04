package com.kjplus.model;

/**
 * TSysRefValue entity. @author MyEclipse Persistence Tools
 */

public class SysRefValueEbo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5985410422622557276L;
	private Integer id;
	private Integer refId;
	private String code;
	private String refVl;
	private String name;
	private String extVlType;
	private String flag;

	// Constructors

	/** default constructor */
	public SysRefValueEbo() {
	}

	/** full constructor */
	public SysRefValueEbo(Integer refId, String code, String refVl, String name) {
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

	@Override
	public String toString() {
		return "SysRefValueEbo [id=" + id + ", refId=" + refId + ", code=" + code + ", refVl=" + refVl + ", name="
				+ name + ", flag=" + flag + "]";
	}

	public String getExtVlType() {
		return extVlType;
	}

	public void setExtVlType(String extVlType) {
		this.extVlType = extVlType;
	}

}