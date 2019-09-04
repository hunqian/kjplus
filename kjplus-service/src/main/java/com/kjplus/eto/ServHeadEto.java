package com.kjplus.eto;

import java.io.Serializable;

import com.kjplus.annotation.Validation;
import com.kjplus.constant.Constant;

public class ServHeadEto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8001689746472966148L;
	@Validation
	private String orgCode;
	@Validation
	private Integer seq = 1;
	@Validation
	private String title;
	@Validation
	private String contentBody;
	private String flag = Constant.FLAG_YES;

	public ServHeadEto() {
		super();
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContentBody() {
		return contentBody;
	}

	public void setContentBody(String contentBody) {
		this.contentBody = contentBody;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
