package com.kjplus.model.inner;

import java.io.Serializable;

public class PersonOplogInnerEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3723738720736233414L;
	private Integer id;
	private Integer opSeq;
	private Integer prsnId;
	private String opTypeId;
	private Integer uid;
	private Integer orgid;
	private String flag;
	private String memo;
	private Integer opTime;

	// 档案信息
	private String diCode;
	private String prsnName;

	// 参照
	private String rvCode;
	private String rvName;

	// 管理员
	private String auUserName;
	private String auNickName;

	// 组织
	private String oCode;
	private String oName;
	private String oAlias;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOpSeq() {
		return opSeq;
	}

	public void setOpSeq(Integer opSeq) {
		this.opSeq = opSeq;
	}

	public Integer getPrsnId() {
		return prsnId;
	}

	public void setPrsnId(Integer prsnId) {
		this.prsnId = prsnId;
	}

	public String getOpTypeId() {
		return opTypeId;
	}

	public void setOpTypeId(String opTypeId) {
		this.opTypeId = opTypeId;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getOrgid() {
		return orgid;
	}

	public void setOrgid(Integer orgid) {
		this.orgid = orgid;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getOpTime() {
		return opTime;
	}

	public void setOpTime(Integer opTime) {
		this.opTime = opTime;
	}

	public String getDiCode() {
		return diCode;
	}

	public void setDiCode(String diCode) {
		this.diCode = diCode;
	}

	public String getPrsnName() {
		return prsnName;
	}

	public void setPrsnName(String prsnName) {
		this.prsnName = prsnName;
	}

	public String getRvCode() {
		return rvCode;
	}

	public void setRvCode(String rvCode) {
		this.rvCode = rvCode;
	}

	public String getRvName() {
		return rvName;
	}

	public void setRvName(String rvName) {
		this.rvName = rvName;
	}

	public String getAuUserName() {
		return auUserName;
	}

	public void setAuUserName(String auUserName) {
		this.auUserName = auUserName;
	}

	public String getAuNickName() {
		return auNickName;
	}

	public void setAuNickName(String auNickName) {
		this.auNickName = auNickName;
	}

	public String getoCode() {
		return oCode;
	}

	public void setoCode(String oCode) {
		this.oCode = oCode;
	}

	public String getoName() {
		return oName;
	}

	public void setoName(String oName) {
		this.oName = oName;
	}

	public String getoAlias() {
		return oAlias;
	}

	public void setoAlias(String oAlias) {
		this.oAlias = oAlias;
	}

	@Override
	public String toString() {
		return "PersonOplogInnerEbo [id=" + id + ", opSeq=" + opSeq + ", prsnId=" + prsnId + ", opTypeId=" + opTypeId + ", uid=" + uid + ", orgid=" + orgid
				+ ", flag=" + flag + ", memo=" + memo + ", opTime=" + opTime + ", diCode=" + diCode + ", prsnName=" + prsnName + ", rvCode=" + rvCode
				+ ", rvName=" + rvName + ", auUserName=" + auUserName + ", auNickName=" + auNickName + ", oCode=" + oCode + ", oName=" + oName + ", oAlias="
				+ oAlias + "]";
	}

}
