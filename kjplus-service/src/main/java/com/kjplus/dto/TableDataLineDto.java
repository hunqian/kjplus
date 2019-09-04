package com.kjplus.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.kjplus.dto.DataLineDto;

public class TableDataLineDto implements Serializable {

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
	private List<DataLineDto> lines = new ArrayList<DataLineDto>();
	
	public TableDataLineDto() {
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

	public List<DataLineDto> getLines() {
		return lines;
	}

	public void setLines(List<DataLineDto> subRefs) {
		this.lines = subRefs;
	}
}
