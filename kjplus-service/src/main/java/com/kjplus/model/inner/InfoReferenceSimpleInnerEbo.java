package com.kjplus.model.inner;

import java.io.Serializable;

public class InfoReferenceSimpleInnerEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6386745577578956774L;
	// 引用表
	private Integer id;
	private String refCode;
	private Integer infoId;
	private Integer infoTypeId;
	private Integer viewNum;
	private Integer zanNum;
	private Integer orgId;
	private String orgName;
	private Integer deptId;
	private String flag;

	public InfoReferenceSimpleInnerEbo() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getInfoId() {
		return infoId;
	}

	public void setInfoId(Integer infoId) {
		this.infoId = infoId;
	}

	public Integer getInfoTypeId() {
		return infoTypeId;
	}

	public void setInfoTypeId(Integer infoTypeId) {
		this.infoTypeId = infoTypeId;
	}

	public Integer getViewNum() {
		return viewNum;
	}

	public void setViewNum(Integer viewNum) {
		this.viewNum = viewNum;
	}

	public Integer getZanNum() {
		return zanNum;
	}

	public void setZanNum(Integer zanNum) {
		this.zanNum = zanNum;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getRefCode() {
		return refCode;
	}

	public void setRefCode(String refCode) {
		this.refCode = refCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@Override
	public String toString() {
		return "InfoReferenceSimpleInnerEbo [id=" + id + ", refCode=" + refCode + ", infoId=" + infoId
				+ ", infoTypeId=" + infoTypeId + ", viewNum=" + viewNum + ", zanNum=" + zanNum + ", orgId=" + orgId
				+ ", orgName=" + orgName + ", deptId=" + deptId + ", flag=" + flag + "]";
	}

}
