package com.ykisswx.model;

/**
 * TWxAccount entity. @author MyEclipse Persistence Tools
 */

public class WxMsgtmplEbo {

	// Fields
	private Integer id;
	private String code;
	private String title;
	private String mainIndustry;
	private String subIndustry;
	private Integer useNum;
	private String content;
	private String demoSample;


	// Constructors
	
	public WxMsgtmplEbo(Integer id, String code, String title, String mainIndustry, String subIndustry, Integer useNum,
			String content, String demoSample) {
		super();
		this.id = id;
		this.code = code;
		this.title = title;
		this.mainIndustry = mainIndustry;
		this.subIndustry = subIndustry;
		this.useNum = useNum;
		this.content = content;
		this.demoSample = demoSample;
	}
	public WxMsgtmplEbo() {
		super();
	}
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMainIndustry() {
		return mainIndustry;
	}
	public void setMainIndustry(String mainIndustry) {
		this.mainIndustry = mainIndustry;
	}
	public String getSubIndustry() {
		return subIndustry;
	}
	public void setSubIndustry(String subIndustry) {
		this.subIndustry = subIndustry;
	}
	public Integer getUseNum() {
		return useNum;
	}
	public void setUseNum(Integer useNum) {
		this.useNum = useNum;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDemoSample() {
		return demoSample;
	}
	public void setDemoSample(String demoSample) {
		this.demoSample = demoSample;
	}


}