package com.kjplus.eto;

import com.kjplus.constant.Constant;

public class InfoReferenceEto {

	public static final int CODE_LEN = 32;
	private Integer infoId;
	private String refCode;
	private Integer InfoCatgId;
	private Integer viewNum = 0;
	private Integer zanNum = 0;
	private Integer focusNum=0;
	private Integer deptId;
	private Integer orgId;
	private String flag = Constant.FLAG_YES;

	public InfoReferenceEto() {
		super();
	}

	public Integer getInfoId() {
		return infoId;
	}

	public void setInfoId(Integer infoId) {
		this.infoId = infoId;
	}

	public Integer getInfoCatgId() {
		return InfoCatgId;
	}

	public void setInfoCatgId(Integer infoCatgId) {
		this.InfoCatgId = infoCatgId;
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

	public Integer getFocusNum() {
		return focusNum;
	}

	public void setFocusNum(Integer focusNum) {
		this.focusNum = focusNum;
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
		return "InfoReferenceEto [infoId=" + infoId + ", refCode=" + refCode + ", infoCatgId=" + InfoCatgId
				+ ", viewNum=" + viewNum + ", zanNum=" + zanNum + ", deptId=" + deptId + ", orgId=" + orgId + ", flag="
				+ flag + "]";
	}
}
