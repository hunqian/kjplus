package com.kjplus.dto;

import java.io.Serializable;

public class InfoContentDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 465070459384964665L;
	private Integer infoId;
	private String infoContent = "";

	public InfoContentDto() {
		super();
	}

	public InfoContentDto(Integer infoSeq, String infoContent) {
		super();
		this.infoId = infoSeq;
		this.infoContent = infoContent;
	}

	public Integer getInfoId() {
		return infoId;
	}

	public void setInfoId(Integer infoId) {
		this.infoId = infoId;
	}

	public String getInfoContent() {
		return infoContent;
	}

	public void setInfoContent(String infoContent) {
		this.infoContent = infoContent;
	}

	@Override
	public String toString() {
		return "InfoContentDto [infoSeq=" + infoId + ", infoContent=" + infoContent + "]";
	}

}
