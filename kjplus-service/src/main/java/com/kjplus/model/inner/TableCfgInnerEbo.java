package com.kjplus.model.inner;

import java.io.Serializable;
import java.util.Date;

public class TableCfgInnerEbo implements Serializable {

	/**
	 * 
	 */
	// head
	private static final long serialVersionUID = -4026831095654298380L;
	private Integer cfgId;
	private String cfgCode;
	private String cfgName;
	private Integer cfgTypeId;
	private String cfgTypeName;
	private String cfgFlag;
	private Date cfgCreateTime;

	// line
	private Integer lineId;
	private String lineCode;
	private Integer linePid;
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
	//字段映射名字
	private String fieldMapName;

	// refVl
	private Integer refVlId;
	private String refVlCode;
	private String refVl;
	private String extVlType;
	private String refVlName;
	
	//refType
	private Integer refTypeId;
	private String refTypeCode;
	private String refTypeName;

	public TableCfgInnerEbo() {
		super();
	}

	public Integer getCfgId() {
		return cfgId;
	}

	public void setCfgId(Integer cfgId) {
		this.cfgId = cfgId;
	}

	public String getCfgCode() {
		return cfgCode;
	}

	public void setCfgCode(String cfgCode) {
		this.cfgCode = cfgCode;
	}

	public String getCfgName() {
		return cfgName;
	}

	public void setCfgName(String cfgName) {
		this.cfgName = cfgName;
	}

	public Integer getCfgTypeId() {
		return cfgTypeId;
	}

	public void setCfgTypeId(Integer cfgTypeId) {
		this.cfgTypeId = cfgTypeId;
	}

	public String getCfgTypeName() {
		return cfgTypeName;
	}

	public void setCfgTypeName(String cfgTypeName) {
		this.cfgTypeName = cfgTypeName;
	}

	public String getCfgFlag() {
		return cfgFlag;
	}

	public void setCfgFlag(String cfgFlag) {
		this.cfgFlag = cfgFlag;
	}

	public Date getCfgCreateTime() {
		return cfgCreateTime;
	}

	public void setCfgCreateTime(Date cfgCreateTime) {
		this.cfgCreateTime = cfgCreateTime;
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

	public Integer getLinePid() {
		return linePid;
	}

	public void setLinePid(Integer linePid) {
		this.linePid = linePid;
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
	public String getIsReq() {
		return isReq;
	}

	public void setIsReq(String isReq) {
		this.isReq = isReq;
	}

	public Integer getRefVlId() {
		return refVlId;
	}

	public void setRefVlId(Integer refVlId) {
		this.refVlId = refVlId;
	}

	public String getRefVlCode() {
		return refVlCode;
	}

	public void setRefVlCode(String refVlCode) {
		this.refVlCode = refVlCode;
	}

	public String getRefVl() {
		return refVl;
	}

	public void setRefVl(String refVl) {
		this.refVl = refVl;
	}

	public String getExtVlType() {
		return extVlType;
	}

	public void setExtVlType(String extVlType) {
		this.extVlType = extVlType;
	}

	public String getRefVlName() {
		return refVlName;
	}

	public void setRefVlName(String refVlName) {
		this.refVlName = refVlName;
	}

	public Integer getRefTypeId() {
		return refTypeId;
	}

	public void setRefTypeId(Integer refTypeId) {
		this.refTypeId = refTypeId;
	}

	public String getRefTypeCode() {
		return refTypeCode;
	}

	public void setRefTypeCode(String refTypeCode) {
		this.refTypeCode = refTypeCode;
	}

	public String getRefTypeName() {
		return refTypeName;
	}

	public void setRefTypeName(String refTypeName) {
		this.refTypeName = refTypeName;
	}
	public String getLineTypeVlName() {
		return lineTypeVlName;
	}

	public void setLineTypeVlName(String lineTypeVlName) {
		this.lineTypeVlName = lineTypeVlName;
	}

	public String getFieldMapName() {
		return fieldMapName;
	}

	public void setFieldMapName(String fieldMapName) {
		this.fieldMapName = fieldMapName;
	}

}
