package com.kjplus.model.inner;

import java.io.Serializable;

public class TableDataLineInnerEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2497362437310477311L;
	private Integer id;
	private Integer checkId;
	private Integer cfgLineId;
	private String lineCode;
	private String vlType;
	private Integer refTypeId;
	private String multiRef;
	private Integer refId;
	private String refVl;
	private String inputVl;
	private Integer cklnSeq;
	
	public TableDataLineInnerEbo() {
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

	public String getLineCode() {
		return lineCode;
	}

	public void setLineCode(String lineCode) {
		this.lineCode = lineCode;
	}

	public String getVlType() {
		return vlType;
	}

	public void setVlType(String vlType) {
		this.vlType = vlType;
	}

	public Integer getRefTypeId() {
		return refTypeId;
	}

	public void setRefTypeId(Integer refTypeId) {
		this.refTypeId = refTypeId;
	}

	public String getMultiRef() {
		return multiRef;
	}

	public void setMultiRef(String multiRef) {
		this.multiRef = multiRef;
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

}
