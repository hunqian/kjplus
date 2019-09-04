package com.kjplus.model.inner;

public class CfgHeadsInnerEbo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3214962087347372042L;

	private int cfgId;
	private String cfgName;
	private int rvId;
	private String rvName;

	public CfgHeadsInnerEbo() {
		super();
	}

	public CfgHeadsInnerEbo(int cfgId, String cfgName, int rvId, String rvName) {
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
		return "CfgHeadsInnerEbo [cfgId=" + cfgId + ", cfgName=" + cfgName + ", rvId=" + rvId + ", rvName=" + rvName + "]";
	}
	
}
