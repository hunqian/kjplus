package com.kjplus.model;

import java.io.Serializable;

public class InfoContentEbo implements Serializable {

	private static final long serialVersionUID = 5808786801806417679L;
	private Integer id;
	private Integer infoId;
	private Integer infoSeq;
	private String infoContent;

	public InfoContentEbo() {
		super();
	}

	public InfoContentEbo(Integer id, Integer infoId, Integer infoSeq, String infoContent) {
		super();
		this.id = id;
		this.infoId = infoId;
		this.infoSeq = infoSeq;
		this.infoContent = infoContent;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getInfoId() {
		return infoId;
	}

	public void setInfoId(Integer infoId) {
		this.infoId = infoId;
	}

	public Integer getInfoSeq() {
		return infoSeq;
	}

	public void setInfoSeq(Integer infoSeq) {
		this.infoSeq = infoSeq;
	}

	public String getInfoContent() {
		return infoContent;
	}

	public void setInfoContent(String infoContent) {
		this.infoContent = infoContent;
	}

}
