package com.ykisswx.model;

/**
 * TWxMsgtmplLib entity. @author MyEclipse Persistence Tools
 */

public class WxMsgtmplLibEbo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -6432678264954860007L;
	private Integer id;
	private String code;
	private String title;
	private String mainIndustry;
	private String subIndustry;
	private Integer useNum;

	// Constructors

	/** default constructor */
	public WxMsgtmplLibEbo() {
	}

	/** full constructor */
	public WxMsgtmplLibEbo(String code, String title, String mainIndustry, String subIndustry, Integer useNum) {
		this.code = code;
		this.title = title;
		this.mainIndustry = mainIndustry;
		this.subIndustry = subIndustry;
		this.useNum = useNum;
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

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMainIndustry() {
		return this.mainIndustry;
	}

	public void setMainIndustry(String mainIndustry) {
		this.mainIndustry = mainIndustry;
	}

	public String getSubIndustry() {
		return this.subIndustry;
	}

	public void setSubIndustry(String subIndustry) {
		this.subIndustry = subIndustry;
	}

	public Integer getUseNum() {
		return this.useNum;
	}

	public void setUseNum(Integer useNum) {
		this.useNum = useNum;
	}

}