package com.kjplus.model;

import java.io.Serializable;

public class PersonOplogEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5999737002871551729L;
	private Integer id;
	private Integer opSeq;
	private Integer prsnId;
	private Integer opTypeId;
	private Integer uid;
	private Integer orgid;
	private String flag;
	private String memo;
	private Integer opTime;

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

	public Integer getOpTypeId() {
		return opTypeId;
	}

	public void setOpTypeId(Integer opTypeId) {
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

}
