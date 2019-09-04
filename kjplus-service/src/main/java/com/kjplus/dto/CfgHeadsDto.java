package com.kjplus.dto;

import java.io.Serializable;

public class CfgHeadsDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2412725781902191612L;

	private int cfgId;
	private String cfgName;
	private int rvId;
	private String rvName;

	public CfgHeadsDto() {
		super();
	}

	public CfgHeadsDto(int cfgId, String cfgName, int rvId, String rvName) {
		super();
		this.cfgId = cfgId;
		this.cfgName = cfgName;
		this.rvId = rvId;
		this.rvName = rvName;
	}

	public int getCfgId() {
		return cfgId;
	}

	public void setCfgId(int cfgId) {
		this.cfgId = cfgId;
	}

	public String getCfgName() {
		return cfgName;
	}

	public void setCfgName(String cfgName) {
		this.cfgName = cfgName;
	}

	public int getRvId() {
		return rvId;
	}

	public void setRvId(int rvId) {
		this.rvId = rvId;
	}

	public String getRvName() {
		return rvName;
	}

	public void setRvName(String rvName) {
		this.rvName = rvName;
	}

	@Override
	public String toString() {
		return "CfgHeadsDto [cfgId=" + cfgId + ", cfgName=" + cfgName + ", rvId=" + rvId + ", rvName=" + rvName + "]";
	}

}
