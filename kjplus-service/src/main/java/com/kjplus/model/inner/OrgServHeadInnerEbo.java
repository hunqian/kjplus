package com.kjplus.model.inner;

import java.io.Serializable;

public class OrgServHeadInnerEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6607130838180149979L;
	private Integer orgId;
	private String orgName;
	private String orgCode;
	private Integer seq;
	private String title;
	private String contentBody;
	private String flag;

	public OrgServHeadInnerEbo() {
		super();
	}

	public OrgServHeadInnerEbo(Integer orgId, String orgName, String orgCode, Integer seq, String title,
			String contentBody, String flag) {
		super();
		this.orgId = orgId;
		this.orgName = orgName;
		this.orgCode = orgCode;
		this.seq = seq;
		this.title = title;
		this.contentBody = contentBody;
		this.flag = flag;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
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

	@Override
	public String toString() {
		return "OrgServHeadInnerEbo [orgId=" + orgId + ", orgName=" + orgName + ", orgCode=" + orgCode + ", seq=" + seq
				+ ", title=" + title + ", contentBody=" + contentBody + ", flag=" + flag + "]";
	}

}
