package com.kjplus.dto;

import java.util.ArrayList;
import java.util.List;

public class TableCfgLineDto {

	// line
	private Integer lineId;
	private String lineCode;
	private String lineTitle;
	private Integer lineSeq;
	private Integer linePos;
	private Integer linePos2;
	private Integer lineRefTypeId;
	private String lineRefTypeName;
	private String lineMultiRef;
	private String lineInputVlType;
	private String lineInputVlRepl;
	private String lineFlag;
	private Double lineTitleWidth;
	private Double lineContentWidth;
	private Integer lineTypeVlId;
	private String lineTypeVlName;
	private String isReq;
	private List<TableCfgLineDto> subs = new ArrayList<TableCfgLineDto>();
	private List<SysRefVlDto> refVls = new ArrayList<SysRefVlDto>();

	public TableCfgLineDto() {
		super();
	}

	public Integer getLineId() {
		return lineId;
	}

	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}

	public String getLineCode() {
		return lineCode;
	}

	public void setLineCode(String lineCode) {
		this.lineCode = lineCode;
	}

	public String getLineTitle() {
		return lineTitle;
	}

	public void setLineTitle(String lineTitle) {
		this.lineTitle = lineTitle;
	}

	public Integer getLineSeq() {
		return lineSeq;
	}

	public void setLineSeq(Integer lineSeq) {
		this.lineSeq = lineSeq;
	}

	public Integer getLinePos() {
		return linePos;
	}

	public void setLinePos(Integer linePos) {
		this.linePos = linePos;
	}

	public Integer getLinePos2() {
		return linePos2;
	}

	public void setLinePos2(Integer linePos2) {
		this.linePos2 = linePos2;
	}	
	
	public Integer getLineRefTypeId() {
		return lineRefTypeId;
	}

	public void setLineRefTypeId(Integer lineRefTypeId) {
		this.lineRefTypeId = lineRefTypeId;
	}
	public String getLineRefTypeName() {
		return lineRefTypeName;
	}

	public void setLineRefTypeName(String lineRefTypeName) {
		this.lineRefTypeName = lineRefTypeName;
	}

	public String getLineMultiRef() {
		return lineMultiRef;
	}

	public void setLineMultiRef(String lineMultiRef) {
		this.lineMultiRef = lineMultiRef;
	}

	public String getLineInputVlType() {
		return lineInputVlType;
	}

	public void setLineInputVlType(String lineInputVlType) {
		this.lineInputVlType = lineInputVlType;
	}

	public String getLineInputVlRepl() {
		return lineInputVlRepl;
	}

	public void setLineInputVlRepl(String lineInputVlRepl) {
		this.lineInputVlRepl = lineInputVlRepl;
	}

	public String getLineFlag() {
		return lineFlag;
	}

	public void setLineFlag(String lineFlag) {
		this.lineFlag = lineFlag;
	}

	public Double getLineTitleWidth() {
		return lineTitleWidth;
	}

	public void setLineTitleWidth(Double lineTitleWidth) {
		this.lineTitleWidth = lineTitleWidth;
	}

	public Double getLineContentWidth() {
		return lineContentWidth;
	}

	public void setLineContentWidth(Double lineContentWidth) {
		this.lineContentWidth = lineContentWidth;
	}

	public Integer getLineTypeVlId() {
		return lineTypeVlId;
	}
	public void setLineTypeVlId(Integer lineTypeVlId) {
		this.lineTypeVlId = lineTypeVlId;
	}

	public String getLineTypeVlName() {
		return lineTypeVlName;
	}

	public void setLineTypeVlName(String lineTypeVlName) {
		this.lineTypeVlName = lineTypeVlName;
	}

	public List<TableCfgLineDto> getSubs() {
		return subs;
	}

	public void setSubs(List<TableCfgLineDto> subs) {
		this.subs = subs;
	}

	public List<SysRefVlDto> getRefVls() {
		return refVls;
	}

	public void setRefVls(List<SysRefVlDto> refVls) {
		this.refVls = refVls;
	}

	public String getIsReq() {
		return isReq;
	}

	public void setIsReq(String isReq) {
		this.isReq = isReq;
	}

	@Override
	public String toString() {
		return "TableCfgLineDto [lineId=" + lineId + ", lineCode=" + lineCode + ", lineTitle=" + lineTitle
				+ ", lineSeq=" + lineSeq + ", linePos=" + linePos + ",, linePos2=" + linePos2 + " lineRefTypeId=" + lineRefTypeId
				+ ", lineMultiRef=" + lineMultiRef + ", lineInputVlType=" + lineInputVlType + ", lineInputVlRepl="
				+ lineInputVlRepl + ", lineFlag=" + lineFlag + ", lineTitleWidth=" + lineTitleWidth
				+ ", lineContentWidth=" + lineContentWidth + ", lineTypeVl=" + lineTypeVlId + ", isReq=" + isReq
				+ ", subs=" + subs + ", refVls=" + refVls + "]";
	}

}
