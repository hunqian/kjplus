package com.kjplus.model;

import java.io.Serializable;

public class TableCfgLineEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1333021598461848455L;
	private Integer id;
	private Integer cfgId;
	private String code;
	private Integer pid;
	private String title;
	private Integer lingSeq;
	private Integer linePos;
	private Integer linePos2;
	private Integer refTypeId;
	private String multiRef;
	private String inputVlType;
	private String inputVlRepl;
	private String isReq;
	private String flag;
	private Double titleWidth;
	private Double contentWidth;
	private Integer typeVlId;
	//字段映射名字
	private String fieldMapName;

	public TableCfgLineEbo() {
		super();
	}

	public TableCfgLineEbo(Integer id, Integer cfgId, String code, Integer pid, String title, Integer lingSeq,
			Integer linePos,Integer linePos2, Integer refId, String multiRef, String inputVlType, String inputVlRepl, String flag,
			Double titleWidth, Double contentWidth, Integer typeVlId) {
		super();
		this.id = id;
		this.cfgId = cfgId;
		this.code = code;
		this.pid = pid;
		this.title = title;
		this.lingSeq = lingSeq;
		this.linePos = linePos;
		this.linePos2 = linePos2;
		this.refTypeId = refId;
		this.multiRef = multiRef;
		this.inputVlType = inputVlType;
		this.inputVlRepl = inputVlRepl;
		this.flag = flag;
		this.titleWidth = titleWidth;
		this.contentWidth = contentWidth;
		this.typeVlId = typeVlId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCfgId() {
		return cfgId;
	}

	public void setCfgId(Integer cfgId) {
		this.cfgId = cfgId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getLingSeq() {
		return lingSeq;
	}

	public void setLingSeq(Integer lingSeq) {
		this.lingSeq = lingSeq;
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

	public Integer getRefTypeId() {
		return refTypeId;
	}

	public void setRefTypeId(Integer refId) {
		this.refTypeId = refId;
	}

	public String getMultiRef() {
		return multiRef;
	}

	public void setMultiRef(String multiRef) {
		this.multiRef = multiRef;
	}

	public String getInputVlType() {
		return inputVlType;
	}

	public void setInputVlType(String inputVlType) {
		this.inputVlType = inputVlType;
	}

	public String getInputVlRepl() {
		return inputVlRepl;
	}

	public void setInputVlRepl(String inputVlRepl) {
		this.inputVlRepl = inputVlRepl;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Double getTitleWidth() {
		return titleWidth;
	}

	public void setTitleWidth(Double titleWidth) {
		this.titleWidth = titleWidth;
	}

	public Double getContentWidth() {
		return contentWidth;
	}

	public void setContentWidth(Double contentWidth) {
		this.contentWidth = contentWidth;
	}

	public Integer getTypeVlId() {
		return typeVlId;
	}

	public void setTypeVlId(Integer typeVlId) {
		this.typeVlId = typeVlId;
	}

	public String getIsReq() {
		return isReq;
	}

	public void setIsReq(String isReq) {
		this.isReq = isReq;
	}

	public String getFieldMapName() {
		return fieldMapName;
	}

	public void setFieldMapName(String fieldMapName) {
		this.fieldMapName = fieldMapName;
	}

}
