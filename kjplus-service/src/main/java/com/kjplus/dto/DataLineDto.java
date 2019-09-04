package com.kjplus.dto;

public class DataLineDto {

	private Integer refId;
	private String refVl;
	private String inputVl;
	private Integer cklnSeq;
	
	public DataLineDto() {
		super();
	}

	public DataLineDto(Integer refId, String refVl, String inputVl, Integer cklnSeq) {
		super();
		this.refId = refId;
		this.refVl = refVl;
		this.inputVl = inputVl;
		this.cklnSeq = cklnSeq;
	}

	public Integer getRefId() {
		return refId;
	}

	public void setRefId(Integer refId) {
		this.refId = refId;
	}

	public String getRefVl() {
		return refVl;
	}

	public void setRefVl(String refVl) {
		this.refVl = refVl;
	}

	public String getInputVl() {
		return inputVl;
	}

	public void setInputVl(String inputVl) {
		this.inputVl = inputVl;
	}

	public Integer getCklnSeq() {
		return cklnSeq;
	}

	public void setCklnSeq(Integer cklnSeq) {
		this.cklnSeq = cklnSeq;
	}

	@Override
	public String toString() {
		return "DataRefDto [refId=" + refId + ", refVl=" + refVl + ", inputVl=" + inputVl + ", cklnSeq=" + cklnSeq
				+ "]";
	}

}
