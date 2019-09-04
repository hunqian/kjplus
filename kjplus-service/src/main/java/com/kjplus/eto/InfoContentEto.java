package com.kjplus.eto;

public class InfoContentEto {

	private Integer infoId;
	private Integer infoSeq;
	private String infoContent;
	
	public InfoContentEto() {
		super();
	}
	public InfoContentEto(Integer infoId, Integer infoSeq, String infoContent) {
		super();
		this.infoId = infoId;
		this.infoSeq = infoSeq;
		this.infoContent = infoContent;
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
	@Override
	public String toString() {
		return "InfoContentEto [infoId=" + infoId + ", infoSeq=" + infoSeq + ", infoContent=" + infoContent + "]";
	}
	
}
