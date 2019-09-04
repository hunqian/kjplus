package com.kjplus.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServAsgnDto {

	private Integer id; // 签约记录ID
	private String code; // 签约记录Code
	private Integer personId;// 居民ID
	private String personName;// 居民名字
	private String personMobile;// 具名手机号
	private Integer stafId;// 医生id
	private String stafCode;// 医生编号
	private String stafName;// 医生名称
	private String stafType;// 医生类别
	private Integer orgId;// 组织id
	private String orgName;// 组织名称
	private Integer deptId;// 科室id
	private String deptType;// 科室类型
	private String deptName;// 科室名称
	private Integer reqTime;// 请求时间
	private String status;// 申请状态
	private Integer proofAmount;
	private String memo;
	private Integer operTime;// 操作时间

	// 服务包具体信息
	private Date beginDay;// 服务开始时间
	private Date endDay;// 服务结束时间
	private Integer srvId;// 服务id
	private String srvCode;// 服务编号
	private String srvName;// 服务名称
	private String srvAlias;// 服务简称
	private Double srvPrice;// 服务价格

	private List<ServAsgnPackageDto> listPackage = new ArrayList<ServAsgnPackageDto>();

	/*
	 * // 签约凭证 private List<FileRepoInnerEbo> listFile = new
	 * ArrayList<FileRepoInnerEbo>();
	 */

	public ServAsgnDto() {
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

	public List<ServAsgnPackageDto> getListPackage() {
		return listPackage;
	}

	public void setListPackage(List<ServAsgnPackageDto> listPackage) {
		this.listPackage = listPackage;
	}

	public Integer getProofAmount() {
		return proofAmount;
	}

	public void setProofAmount(Integer proofAmount) {
		this.proofAmount = proofAmount;
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

	public String getSrvAlias() {
		return srvAlias;
	}

	public void setSrvAlias(String srvAlias) {
		this.srvAlias = srvAlias;
	}

	public Double getSrvPrice() {
		return srvPrice;
	}

	public void setSrvPrice(Double srvPrice) {
		this.srvPrice = srvPrice;
	}

	@Override
	public String toString() {
		return "ServAsgnDto [personId=" + personId + ", personName="
				+ personName + ", personMobile=" + personMobile + ", stafId="
				+ stafId + ", stafCode=" + stafCode + ", stafName=" + stafName
				+ ", stafType=" + stafType + ", orgId=" + orgId + ", orgName="
				+ orgName + ", deptId=" + deptId + ", deptType=" + deptType
				+ ", deptName=" + deptName + ", reqTime=" + reqTime
				+ ", status=" + status + ", proofAmount=" + proofAmount
				+ ", memo=" + memo + ", operTime=" + operTime + ", beginDay="
				+ beginDay + ", endDay=" + endDay + ", srvId=" + srvId
				+ ", srvCode=" + srvCode + ", srvName=" + srvName
				+ ", srvAlias=" + srvAlias + ", srvPrice=" + srvPrice
				+ ", listPackage=" + listPackage + "]";
	}

}
