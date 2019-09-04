package com.kjplus.model;

public class DocumentOplogEbo {

	private Integer id;
	private Integer prsnId;
	private Integer opType;
	private Integer orgId;
	private Integer opTime;
	private Integer opSeq;
	private Integer upPrsn;
	private String memo;
	private String flag;

	public DocumentOplogEbo() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPrsnId() {
		return prsnId;
	}

	public void setPrsnId(Integer prsnId) {
		this.prsnId = prsnId;
	}

	public Integer getOpType() {
		return opType;
	}

	public void setOpType(Integer opType) {
		this.opType = opType;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getOpTime() {
		return opTime;
	}

	public void setOpTime(Integer opTime) {
		this.opTime = opTime;
	}

	public Integer getOpSeq() {
		return opSeq;
	}

	public void setOpSeq(Integer opSeq) {
		this.opSeq = opSeq;
	}

	public Integer getUpPrsn() {
		return upPrsn;
	}

	public void setUpPrsn(Integer upPrsn) {
		this.upPrsn = upPrsn;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "DocumentOplogEbo{" + "id=" + id + ", prsnId=" + prsnId + ", opType=" + opType + ", orgId=" + orgId
				+ ", opTime=" + opTime + ", opSeq=" + opSeq + ", upPrsn=" + upPrsn + ", memo='" + memo + '\''
				+ ", flag='" + flag + '\'' + '}';
	}
}
