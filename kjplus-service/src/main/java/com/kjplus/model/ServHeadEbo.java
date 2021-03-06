package com.kjplus.model;

import java.io.Serializable;

public class ServHeadEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5556916679932871591L;
	private Integer id;
	private Integer orgId;
	private Integer seq;
	private String title;
	private String contentBody;
	private String flag;
	
	public ServHeadEbo() {
		super();
	}

	public ServHeadEbo(Integer id, Integer orgId, Integer seq, String title, String contentBody, String flag) {
		super();
		this.id = id;
		this.orgId = orgId;
		this.seq = seq;
		this.title = title;
		this.contentBody = contentBody;
		this.flag = flag;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
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
		return "ServHeadEbo [id=" + id + ", orgId=" + orgId + ", seq=" + seq + ", title=" + title + ", contentBody="
				+ contentBody + ", flag=" + flag + "]";
	}
	
	
}
