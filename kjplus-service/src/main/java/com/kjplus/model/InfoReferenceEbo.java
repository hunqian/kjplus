package com.kjplus.model;

import java.io.Serializable;

public class InfoReferenceEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -387289796878333032L;
	private Integer id;
	private String refCode;
	private Integer infoId;
	private Integer infoCatgId;
	private Integer viewNum;
	private Integer zanNum;
	private Integer focusNum;
	private Integer deptId;
	private Integer orgId;
	private String flag;

	public InfoReferenceEbo() {
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

	public Integer getInfoCatgId() {
		return infoCatgId;
	}

	public void setInfoCatgId(Integer infoCatgId) {
		this.infoCatgId = infoCatgId;
	}

	public Integer getFocusNum() {
		return focusNum;
	}

	public void setFocusNum(Integer focusNum) {
		this.focusNum = focusNum;
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

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
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

	@Override
	public String toString() {
		return "InfoReferenceEbo [id=" + id + ", refCode=" + refCode + ", infoId=" + infoId + ", infoCatgId="
				+ infoCatgId + ", viewNum=" + viewNum + ", zanNum=" + zanNum + ", deptId=" + deptId + ", orgId=" + orgId
				+ ", flag=" + flag + "]";
	}

}
