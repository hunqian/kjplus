package com.kjplus.model.inner;

import java.io.Serializable;

/**
 * 
 * @author songyuebin 预约信息列表
 */
public class AppointInfoInnerEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8885161726272521191L;
	// 预约表信息
	private Integer id;
	private String code;
	private Integer calInfoId;
	private Integer prsnId;
	private Integer orgId;
	private Integer mainId;
	private String mainType;
	private String status;
	private Integer startTime;
	private Integer endTime;
	private Integer appTypeId;
	private String appMemo;
	private Integer createTime;

	// 预约信息
	private String infoCode;
	private String infoTitle;
	private String infoMemo;
	private Integer infoStartTime;
	private Integer infoEndTime;
	private String infoFlag;
	private Integer maxPerson;
	private Integer joinPerson;

	// 预约人信息
	private String prsnCode;
	private String prsnName;

	// 预约组织信息
	private String orgCode;
	private String orgName;
	private String orgAlias;
	// 被预约人信息
	// 医生
	private String bkStaffCode;
	private String bkStaffName;
	// 部门
	private String bkDeptCode;
	private String bkDeptName;
	// 组织
	private String bkOrgCode;
	private String bkOrgName;
	private String bkOrgAlias;

	public AppointInfoInnerEbo() {
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

	public Integer getCalInfoId() {
		return calInfoId;
	}

	public void setCalInfoId(Integer calInfoId) {
		this.calInfoId = calInfoId;
	}

	public Integer getPrsnId() {
		return prsnId;
	}

	public void setPrsnId(Integer prsnId) {
		this.prsnId = prsnId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getMainId() {
		return mainId;
	}

	public void setMainId(Integer mainId) {
		this.mainId = mainId;
	}

	public String getMainType() {
		return mainType;
	}

	public void setMainType(String mainType) {
		this.mainType = mainType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getStartTime() {
		return startTime;
	}

	public void setStartTime(Integer startTime) {
		this.startTime = startTime;
	}

	public Integer getEndTime() {
		return endTime;
	}

	public void setEndTime(Integer endTime) {
		this.endTime = endTime;
	}

	public String getAppMemo() {
		return appMemo;
	}

	public void setAppMemo(String appMemo) {
		this.appMemo = appMemo;
	}

	public Integer getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}

	public String getInfoCode() {
		return infoCode;
	}

	public void setInfoCode(String infoCode) {
		this.infoCode = infoCode;
	}

	public String getInfoTitle() {
		return infoTitle;
	}

	public void setInfoTitle(String infoTitle) {
		this.infoTitle = infoTitle;
	}

	public String getInfoMemo() {
		return infoMemo;
	}

	public void setInfoMemo(String infoMemo) {
		this.infoMemo = infoMemo;
	}

	public Integer getInfoStartTime() {
		return infoStartTime;
	}

	public void setInfoStartTime(Integer infoStartTime) {
		this.infoStartTime = infoStartTime;
	}

	public Integer getInfoEndTime() {
		return infoEndTime;
	}

	public void setInfoEndTime(Integer infoEndTime) {
		this.infoEndTime = infoEndTime;
	}

	public String getInfoFlag() {
		return infoFlag;
	}

	public void setInfoFlag(String infoFlag) {
		this.infoFlag = infoFlag;
	}

	public Integer getMaxPerson() {
		return maxPerson;
	}

	public void setMaxPerson(Integer maxPerson) {
		this.maxPerson = maxPerson;
	}

	public Integer getJoinPerson() {
		return joinPerson;
	}

	public void setJoinPerson(Integer joinPerson) {
		this.joinPerson = joinPerson;
	}

	public String getPrsnCode() {
		return prsnCode;
	}

	public void setPrsnCode(String prsnCode) {
		this.prsnCode = prsnCode;
	}

	public String getPrsnName() {
		return prsnName;
	}

	public void setPrsnName(String prsnName) {
		this.prsnName = prsnName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgAlias() {
		return orgAlias;
	}

	public void setOrgAlias(String orgAlias) {
		this.orgAlias = orgAlias;
	}

	public String getBkStaffCode() {
		return bkStaffCode;
	}

	public void setBkStaffCode(String bkStaffCode) {
		this.bkStaffCode = bkStaffCode;
	}

	public String getBkStaffName() {
		return bkStaffName;
	}

	public void setBkStaffName(String bkStaffName) {
		this.bkStaffName = bkStaffName;
	}

	public String getBkDeptCode() {
		return bkDeptCode;
	}

	public void setBkDeptCode(String bkDeptCode) {
		this.bkDeptCode = bkDeptCode;
	}

	public String getBkDeptName() {
		return bkDeptName;
	}

	public void setBkDeptName(String bkDeptName) {
		this.bkDeptName = bkDeptName;
	}

	public String getBkOrgCode() {
		return bkOrgCode;
	}

	public void setBkOrgCode(String bkOrgCode) {
		this.bkOrgCode = bkOrgCode;
	}

	public String getBkOrgName() {
		return bkOrgName;
	}

	public void setBkOrgName(String bkOrgName) {
		this.bkOrgName = bkOrgName;
	}

	public String getBkOrgAlias() {
		return bkOrgAlias;
	}

	public void setBkOrgAlias(String bkOrgAlias) {
		this.bkOrgAlias = bkOrgAlias;
	}

	public Integer getAppTypeId() {
		return appTypeId;
	}

	public void setAppTypeId(Integer appTypeId) {
		this.appTypeId = appTypeId;
	}

	@Override
	public String toString() {
		return "AppointInfoInnerEbo [id=" + id + ", code=" + code + ", calInfoId=" + calInfoId + ", prsnId=" + prsnId + ", orgId=" + orgId + ", mainId="
				+ mainId + ", mainType=" + mainType + ", status=" + status + ", startTime=" + startTime + ", endTime=" + endTime + ", appTypeId=" + appTypeId
				+ ", appMemo=" + appMemo + ", createTime=" + createTime + ", infoCode=" + infoCode + ", infoTitle=" + infoTitle + ", infoMemo=" + infoMemo
				+ ", infoStartTime=" + infoStartTime + ", infoEndTime=" + infoEndTime + ", infoFlag=" + infoFlag + ", maxPerson=" + maxPerson + ", joinPerson="
				+ joinPerson + ", prsnCode=" + prsnCode + ", prsnName=" + prsnName + ", orgCode=" + orgCode + ", orgName=" + orgName + ", orgAlias=" + orgAlias
				+ ", bkStaffCode=" + bkStaffCode + ", bkStaffName=" + bkStaffName + ", bkDeptCode=" + bkDeptCode + ", bkDeptName=" + bkDeptName
				+ ", bkOrgCode=" + bkOrgCode + ", bkOrgName=" + bkOrgName + ", bkOrgAlias=" + bkOrgAlias + "]";
	}

}
