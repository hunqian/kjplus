package com.kjplus.eto;

import com.kjplus.annotation.Validation;

public class TableDataLineEto {
	
	@Validation
	private Integer checkId ;
	private Integer cklnSeq = 1;
	@Validation
	private String lineCode;
	private String vlType;
	private Integer refId = 0;
	private String inputVl;

	public TableDataLineEto() {
		super();
	}

	public Integer getCheckId() {
		return checkId;
	}

	public void setCheckId(Integer checkId) {
		this.checkId = checkId;
	}

	public String getVlType() {
		return vlType;
	}

	public void setVlType(String vlType) {
		this.vlType = vlType;
	}

	public Integer getRefId() {
		return refId;
	}

	public void setRefId(Integer refId) {
		this.refId = refId;
	}

	public String getInputVl() {
		return inputVl;
	}

	public void setInputVl(String inputVl) {
		this.inputVl = inputVl;
	}

	public String getLineCode() {
		return lineCode;
	}

	public void setLineCode(String linePos) {
		this.lineCode = linePos;
	}

	public Integer getCklnSeq() {
		return cklnSeq;
	}

	public void setCklnSeq(Integer cklnSeq) {
		this.cklnSeq = cklnSeq;
	}

}
