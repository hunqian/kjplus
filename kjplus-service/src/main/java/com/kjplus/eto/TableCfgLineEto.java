package com.kjplus.eto;

import com.kjplus.annotation.Validation;
import com.kjplus.constant.Constant;

public class TableCfgLineEto {

	public static final int CODE_LEN = 8;
	@Validation
	private Integer cfgId;
	private String code;
	private String plinePos = "";
	private Integer pid = 0;
	@Validation
	private String title;
	@Validation
	private Integer lingSeq = 0;
	@Validation
	private Integer linePos = -1;
	@Validation
	private Integer linePos2 = -1;
	private int refTypeId = 0;
	@Validation
	private String multiRef = Constant.FLAG_NO;
	private String inputVlType;
	private String inputVlRepl;
	@Validation
	private String siReq = Constant.FLAG_YES;
	private String flag = Constant.FLAG_YES;
	@Validation
	private Double titleWidth = 20.0;
	@Validation
	private Double contentWidth = 30.0;
	private String typeVl;

	public TableCfgLineEto() {
		super();
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

	public String getTypeVl() {
		return typeVl;
	}

	public void setTypeVl(String typeVl) {
		this.typeVl = typeVl;
	}

	public String getPlinePos() {
		return plinePos;
	}

	public void setPlinePos(String plinePos) {
		this.plinePos = plinePos;
	}

	public String getSiReq() {
		return siReq;
	}

	public void setSiReq(String siReq) {
		this.siReq = siReq;
	}

	public void setRefTypeId(int refTypeId) {
		this.refTypeId = refTypeId;
	}
}