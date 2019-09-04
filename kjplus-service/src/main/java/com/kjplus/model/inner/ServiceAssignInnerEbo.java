package com.kjplus.model.inner;

import java.io.Serializable;
import java.util.Date;

public class ServiceAssignInnerEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4047253369392441757L;
	private Integer id;
	private String code;
	private Integer personId;
	private String personName;
	private String personMobile;
	private Integer stafId;
	private String stafCode;
	private String stafName;
	private String stafType;
	private Integer orgId;
	private String orgName;
	private Integer deptId;
	private String deptType;
	private String deptName;
	private Integer srvId;
	private String srvCode;
	private String srvName;
	private String srvAlias;
	private Double srvPrice;
	private Integer reqTime;// 请求时间
	private String status;// 申请状态
	private Integer proofAmount;
	private String memo;// 审核结果
	private Integer operTime;// 操作时间
	private Date beginDay;//  服务开始时间
	private Date endDay;// 服务结束时间

	public ServiceAssignInnerEbo() {
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

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPersonMobile() {
		return personMobile;
	}

	public void setPersonMobile(String personMobile) {
		this.personMobile = personMobile;
	}

	public Integer getStafId() {
		return stafId;
	}

	public void setStafId(Integer stafId) {
		this.stafId = stafId;
	}

	public String getStafCode() {
		return stafCode;
	}

	public void setStafCode(String stafCode) {
		this.stafCode = stafCode;
	}

	public String getStafName() {
		return stafName;
	}

	public void setStafName(String stafName) {
		this.stafName = stafName;
	}

	public String getStafType() {
		return stafType;
	}

	public void setStafType(String stafType) {
		this.stafType = stafType;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getDeptType() {
		return deptType;
	}

	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Integer getSrvId() {
		return srvId;
	}

	public void setSrvId(Integer srvId) {
		this.srvId = srvId;
	}

	public String getSrvCode() {
		return srvCode;
	}

	public void setSrvCode(String srvCode) {
		this.srvCode = srvCode;
	}

	public String getSrvName() {
		return srvName;
	}

	public void setSrvName(String srvName) {
		this.srvName = srvName;
	}

	public Double getSrvPrice() {
		return srvPrice;
	}

	public void setSrvPrice(Double srvPrice) {
		this.srvPrice = srvPrice;
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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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

	public String getSrvAlias() {
		return srvAlias;
	}

	public void setSrvAlias(String srvAlias) {
		this.srvAlias = srvAlias;
	}

	public Integer getProofAmount() {
		return proofAmount;
	}

	public void setProofAmount(Integer proofAmount) {
		this.proofAmount = proofAmount;
	}

	@Override
	public String toString() {
		return "ServiceAssignInnerEbo [id=" + id + ", code=" + code + ", personId=" + personId + ", personName=" + personName + ", personMobile="
				+ personMobile + ", stafId=" + stafId + ", stafCode=" + stafCode + ", stafName=" + stafName + ", stafType=" + stafType + ", orgId=" + orgId
				+ ", orgName=" + orgName + ", deptId=" + deptId + ", deptType=" + deptType + ", deptName=" + deptName + ", srvId=" + srvId + ", srvCode="
				+ srvCode + ", srvName=" + srvName + ", srvAlias=" + srvAlias + ", srvPrice=" + srvPrice + ", reqTime=" + reqTime + ", status=" + status
				+ ", proofAmount=" + proofAmount + ", memo=" + memo + ", operTime=" + operTime + ", beginDay=" + beginDay + ", endDay=" + endDay + "]";
	}

}
