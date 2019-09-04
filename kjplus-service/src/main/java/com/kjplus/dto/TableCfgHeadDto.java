package com.kjplus.dto;

import java.util.Date;

public class TableCfgHeadDto {

	private Integer cfgId;
	private String cfgCode;
	private String cfgName;
	private Integer cfgTypeId;
	private String cfgTypeName;
	private String cfgFlag;
	private Date cfgCreateTime;
	
	public TableCfgHeadDto() {
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

	@Override
	public String toString() {
		return "TableCfgHeadDto [cfgId=" + cfgId + ", cfgCode=" + cfgCode + ", cfgName=" + cfgName + ", cfgTypeId="
				+ cfgTypeId + ", cfgTypeName=" + cfgTypeName + ", cfgFlag=" + cfgFlag + ", cfgCreateTime="
				+ cfgCreateTime + "]";
	}
	
	
	
}
