package com.ykisswx.model;

/**
 * TWxMsgtmplContent entity. @author MyEclipse Persistence Tools
 */

public class WxMsgtmplContentEbo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6979674769038727765L;
	private Integer id;
	private String code;
	private String content;
	private String demoSample;

	// Constructors

	/** default constructor */
	public WxMsgtmplContentEbo() {
	}

	/** minimal constructor */
	public WxMsgtmplContentEbo(String code, String content) {
		this.code = code;
		this.content = content;
	}

	/** full constructor */
	public WxMsgtmplContentEbo(String code, String content, String demoSample) {
		this.code = code;
		this.content = content;
		this.demoSample = demoSample;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDemoSample() {
		return this.demoSample;
	}

	public void setDemoSample(String demoSample) {
		this.demoSample = demoSample;
	}

}