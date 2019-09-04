package com.kjplus.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SysRefVlDto implements Serializable {

	private static final long serialVersionUID = -2698118698878845530L;

	// ref
	private Integer refVlId;
	private String refVlCode;
	private String refVl;
	private List<Integer> exts = new ArrayList<Integer>();
	private String refVlName;

	public SysRefVlDto() {
		super();
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

	public String getRefVlName() {
		return refVlName;
	}

	public void setRefVlName(String refVlName) {
		this.refVlName = refVlName;
	}

	public List<Integer> getExts() {
		return exts;
	}

	public void setExts(List<Integer> extVlTypes) {
		this.exts = extVlTypes;
	}
	
}
