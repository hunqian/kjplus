package com.kjplus.model;

import java.io.Serializable;
import java.util.Date;

public class ServAsgnEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5083748499343387718L;
	private Integer id;
	private String code;
	private Integer prsnId;
	private Integer stafId;
	private Integer deptId;
	private Integer srvId;
	private Integer reqTime;
	private String status;
	private Integer operTime;
	private Date beginDay;
	private Date endDay;
	private Integer proofAmount;
	private String memo;

	public ServAsgnEbo() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getPrsnId() {
		return prsnId;
	}

	public void setPrsnId(Integer prsnId) {
		this.prsnId = prsnId;
	}

	public Integer getStafId() {
		return stafId;
	}

	public void setStafId(Integer stafId) {
		this.stafId = stafId;
	}

	public Integer getSrvId() {
		return srvId;
	}

	public void setSrvId(Integer srvId) {
		this.srvId = srvId;
	}

	public Integer getReqTime() {
		return reqTime;
	}

	public void setReqTime(Integer reqTime) {
		this.reqTime = reqTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getOperTime() {
		return operTime;
	}

	public void setOperTime(Integer operTime) {
		this.operTime = operTime;
	}

	public Date getBeginDay() {
		return beginDay;
	}

	public void setBeginDay(Date beginDay) {
		this.beginDay = beginDay;
	}

	public Date getEndDay() {
		return endDay;
	}

	public void setEndDay(Date endDay) {
		this.endDay = endDay;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public Integer getProofAmount() {
		return proofAmount;
	}

	public void setProofAmount(Integer proofAmount) {
		this.proofAmount = proofAmount;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public String toString() {
		return "ServAsgnEbo [id=" + id + ", code=" + code + ", prsnId=" + prsnId + ", stafId=" + stafId + ", deptId=" + deptId + ", srvId=" + srvId
				+ ", reqTime=" + reqTime + ", status=" + status + ", operTime=" + operTime + ", beginDay=" + beginDay + ", endDay=" + endDay + ", proofAmount="
				+ proofAmount + ", memo=" + memo + "]";
	}

}
