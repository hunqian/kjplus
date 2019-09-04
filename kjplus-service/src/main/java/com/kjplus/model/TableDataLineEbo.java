package com.kjplus.model;

import java.io.Serializable;

public class TableDataLineEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2497362437310477311L;
	private Integer id;
	private Integer checkId;
	private Integer cfgLineId;
	private String lineCode;
	private String vlType;
	private Integer refId;
	private String inputVl;
	private Integer cklnSeq;

	public TableDataLineEbo() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCheckId() {
		return checkId;
	}

	public void setCheckId(Integer checkId) {
		this.checkId = checkId;
	}

	public Integer getCfgLineId() {
		return cfgLineId;
	}

	public void setCfgLineId(Integer cfgLineId) {
		this.cfgLineId = cfgLineId;
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

	public Integer getCklnSeq() {
		return cklnSeq;
	}

	public void setCklnSeq(Integer cklnSeq) {
		this.cklnSeq = cklnSeq;
	}

	public String getLineCode() {
		return lineCode;
	}

	public void setLineCode(String lineCode) {
		this.lineCode = lineCode;
	}

}
