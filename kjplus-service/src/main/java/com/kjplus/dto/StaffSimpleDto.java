package com.kjplus.dto;

import java.io.Serializable;

public class StaffSimpleDto implements Serializable {

	private static final long serialVersionUID = -7325307996197037297L;
	private String stafName;
	private Integer stafId;
	private Integer stafType;
	private String stafCode;
	private String headIconUrl;
	private String stafsex;
	// 对应实际部门
	private Integer realDeptId;
	private String realDeptCode;
	private String realDept;

	public String getStafName() {
		return stafName;
	}

	public void setStafName(String stafName) {
		this.stafName = stafName;
	}

	public Integer getStafType() {
		return stafType;
	}

	public void setStafType(Integer stafType) {
		this.stafType = stafType;
	}

	public String getStafCode() {
		return stafCode;
	}

	public void setStafCode(String stafCode) {
		this.stafCode = stafCode;
	}

	public String getHeadIconUrl() {
		return headIconUrl;
	}

	public void setHeadIconUrl(String headIconUrl) {
		this.headIconUrl = headIconUrl;
	}

	public String getStafsex() {
		return stafsex;
	}

	public void setStafsex(String stafsex) {
		this.stafsex = stafsex;
	}

	public Integer getStafId() {
		return stafId;
	}

	public void setStafId(Integer stafId) {
		this.stafId = stafId;
	}

	public String getRealDept() {
		return realDept;
	}

	public void setRealDept(String realDept) {
		this.realDept = realDept;
	}

	public Integer getRealDeptId() {
		return realDeptId;
	}

	public void setRealDeptId(Integer realDeptId) {
		this.realDeptId = realDeptId;
	}

	public String getRealDeptCode() {
		return realDeptCode;
	}

	public void setRealDeptCode(String realDeptCode) {
		this.realDeptCode = realDeptCode;
	}

	@Override
	public String toString() {
		return "StaffSimpleDto [stafName=" + stafName + ", stafId=" + stafId + ", stafType=" + stafType + ", stafCode="
				+ stafCode + ", headIconUrl=" + headIconUrl + ", stafsex=" + stafsex + ", realDeptId=" + realDeptId
				+ ", realDeptCode=" + realDeptCode + ", realDept=" + realDept + "]";
	}

}
