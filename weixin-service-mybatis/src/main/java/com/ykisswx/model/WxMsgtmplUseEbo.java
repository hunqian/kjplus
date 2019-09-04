package com.ykisswx.model;

/**
 * TWxMsgtmplUse entity. @author MyEclipse Persistence Tools
 */

public class WxMsgtmplUseEbo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4707964569765590328L;
	private Integer id;
	private Integer accId;
	private String tmplCode;
	private String tmplId;
	private String flag;
	private Integer createTime;

	// Constructors

	/** default constructor */
	public WxMsgtmplUseEbo() {
	}

	/** full constructor */
	public WxMsgtmplUseEbo(Integer accId, String tmplCode, String tmplId, String flag, Integer createTime) {
		this.accId = accId;
		this.tmplCode = tmplCode;
		this.tmplId = tmplId;
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

	public Integer getAccId() {
		return this.accId;
	}

	public void setAccId(Integer accId) {
		this.accId = accId;
	}

	public String getTmplCode() {
		return this.tmplCode;
	}

	public void setTmplCode(String tmplCode) {
		this.tmplCode = tmplCode;
	}

	public String getTmplId() {
		return this.tmplId;
	}

	public void setTmplId(String tmplId) {
		this.tmplId = tmplId;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Integer getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}

}