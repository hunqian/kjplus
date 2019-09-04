package com.kjplus.model;

public class CodeRepoEbo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -1794762614139995118L;
	private Integer id;
	private String code;
	private String biztype;
	private String submaintype;
	private Integer submainid;

	// Constructors

	/** default constructor */
	public CodeRepoEbo() {
		super();
	}

	// Property accessors

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getBiztype() {
		return biztype;
	}

	public void setBiztype(String biztype) {
		this.biztype = biztype;
	}

	public String getSubmaintype() {
		return submaintype;
	}

	public void setSubmaintype(String submaintype) {
		this.submaintype = submaintype;
	}

	public Integer getSubmainid() {
		return submainid;
	}

	public void setSubmainid(Integer submainid) {
		this.submainid = submainid;
	}

	@Override
	public String toString() {
		return "CodeRepoEbo [id=" + id + ", code=" + code + ", biztype=" + biztype + ", submaintype=" + submaintype
				+ ", submainid=" + submainid + "]";
	}

}
