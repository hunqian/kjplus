package com.kjplus.model.inner;

import java.io.Serializable;
import java.util.Date;

public class CalEntryInfoInnerEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -383499867958687597L;

	// 日历信息表
	private Integer infoId;
	private String infoCode;
	private String infoTitle;
	private String infoMemo;
	private Integer infoStartTime;
	private Integer infoEndTime;
	private Integer infoMaxPerson;
	private Integer infoJoinPerson;
	private String infoSourceType;
	private Integer infoTypeId;
	private String infoFlag;
	private Integer infoCreateTime;
	// 日历入口
	private Integer entryId;
	private String entryCode;
	private Integer entryMainId;
	private String entryMainType;
	private Integer entryCreateId;
	private String entryType;
	private String entryShowClass;
	private Integer entryOrgId;
	private String entryFlag;
	private Date entryCreateTime;
	private Integer entryTypeId;
	private String entryIsOpen;
	private String entryName;
	// 日历入口配置
	private String typeTitle;
	private Integer typeTimeInterval;
	private Integer typeMaxPerson;
	private String typeShowClass;
	private String typeMemo;
	// 日历使用者信息
	// 组织
	private String entryMainOrgCode;
	private String entryMainOrgName;
	// 部门或团队
	private String entryMainDeptCode;
	private String entryMainDeptName;
	// 普通用户
	private String entryMainUserMblNum;
	private String entryMainUserName;
	// 管理员
	private String entryMainAdminUserMblNum;
	private String entryMainAdminUserName;
	// 医生
	private String entryMainStaffCode;
	private String entryMainStaffName;
	// 创建人
	private String createMobileNum;
	private String createName;
	// 日历入口类型
	private String entryTypeCode;
	private String entryTypeName;
	// 日历所属组织
	private String entryOrgCode;
	private String entryOrgName;

	public CalEntryInfoInnerEbo() {
		super();
	}

	public Integer getInfoId() {
		return infoId;
	}

	public void setInfoId(Integer infoId) {
		this.infoId = infoId;
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

	public Integer getInfoMaxPerson() {
		return infoMaxPerson;
	}

	public void setInfoMaxPerson(Integer infoMaxPerson) {
		this.infoMaxPerson = infoMaxPerson;
	}

	public Integer getInfoJoinPerson() {
		return infoJoinPerson;
	}

	public void setInfoJoinPerson(Integer infoJoinPerson) {
		this.infoJoinPerson = infoJoinPerson;
	}

	public String getInfoSourceType() {
		return infoSourceType;
	}

	public void setInfoSourceType(String infoSourceType) {
		this.infoSourceType = infoSourceType;
	}

	public String getInfoFlag() {
		return infoFlag;
	}

	public void setInfoFlag(String infoFlag) {
		this.infoFlag = infoFlag;
	}

	public Integer getInfoCreateTime() {
		return infoCreateTime;
	}

	public void setInfoCreateTime(Integer infoCreateTime) {
		this.infoCreateTime = infoCreateTime;
	}

	public Integer getEntryId() {
		return entryId;
	}

	public void setEntryId(Integer entryId) {
		this.entryId = entryId;
	}

	public String getEntryCode() {
		return entryCode;
	}

	public void setEntryCode(String entryCode) {
		this.entryCode = entryCode;
	}

	public Integer getEntryMainId() {
		return entryMainId;
	}

	public void setEntryMainId(Integer entryMainId) {
		this.entryMainId = entryMainId;
	}

	public String getEntryMainType() {
		return entryMainType;
	}

	public void setEntryMainType(String entryMainType) {
		this.entryMainType = entryMainType;
	}

	public Integer getEntryCreateId() {
		return entryCreateId;
	}

	public void setEntryCreateId(Integer entryCreateId) {
		this.entryCreateId = entryCreateId;
	}

	public String getEntryType() {
		return entryType;
	}

	public void setEntryType(String entryType) {
		this.entryType = entryType;
	}

	public String getEntryShowClass() {
		return entryShowClass;
	}

	public void setEntryShowClass(String entryShowClass) {
		this.entryShowClass = entryShowClass;
	}

	public Integer getEntryOrgId() {
		return entryOrgId;
	}

	public void setEntryOrgId(Integer entryOrgId) {
		this.entryOrgId = entryOrgId;
	}

	public String getEntryFlag() {
		return entryFlag;
	}

	public void setEntryFlag(String entryFlag) {
		this.entryFlag = entryFlag;
	}

	public Date getEntryCreateTime() {
		return entryCreateTime;
	}

	public void setEntryCreateTime(Date entryCreateTime) {
		this.entryCreateTime = entryCreateTime;
	}

	public Integer getEntryTypeId() {
		return entryTypeId;
	}

	public void setEntryTypeId(Integer entryTypeId) {
		this.entryTypeId = entryTypeId;
	}

	public String getEntryIsOpen() {
		return entryIsOpen;
	}

	public void setEntryIsOpen(String entryIsOpen) {
		this.entryIsOpen = entryIsOpen;
	}

	public String getEntryName() {
		return entryName;
	}

	public void setEntryName(String entryName) {
		this.entryName = entryName;
	}

	public String getTypeTitle() {
		return typeTitle;
	}

	public void setTypeTitle(String typeTitle) {
		this.typeTitle = typeTitle;
	}

	public Integer getTypeTimeInterval() {
		return typeTimeInterval;
	}

	public void setTypeTimeInterval(Integer typeTimeInterval) {
		this.typeTimeInterval = typeTimeInterval;
	}

	public Integer getTypeMaxPerson() {
		return typeMaxPerson;
	}

	public void setTypeMaxPerson(Integer typeMaxPerson) {
		this.typeMaxPerson = typeMaxPerson;
	}

	public String getTypeShowClass() {
		return typeShowClass;
	}

	public void setTypeShowClass(String typeShowClass) {
		this.typeShowClass = typeShowClass;
	}

	public String getTypeMemo() {
		return typeMemo;
	}

	public void setTypeMemo(String typeMemo) {
		this.typeMemo = typeMemo;
	}

	public String getEntryMainOrgCode() {
		return entryMainOrgCode;
	}

	public void setEntryMainOrgCode(String entryMainOrgCode) {
		this.entryMainOrgCode = entryMainOrgCode;
	}

	public String getEntryMainOrgName() {
		return entryMainOrgName;
	}

	public void setEntryMainOrgName(String entryMainOrgName) {
		this.entryMainOrgName = entryMainOrgName;
	}

	public String getEntryMainDeptCode() {
		return entryMainDeptCode;
	}

	public void setEntryMainDeptCode(String entryMainDeptCode) {
		this.entryMainDeptCode = entryMainDeptCode;
	}

	public String getEntryMainDeptName() {
		return entryMainDeptName;
	}

	public void setEntryMainDeptName(String entryMainDeptName) {
		this.entryMainDeptName = entryMainDeptName;
	}

	public String getEntryMainUserMblNum() {
		return entryMainUserMblNum;
	}

	public void setEntryMainUserMblNum(String entryMainUserMblNum) {
		this.entryMainUserMblNum = entryMainUserMblNum;
	}

	public String getEntryMainUserName() {
		return entryMainUserName;
	}

	public void setEntryMainUserName(String entryMainUserName) {
		this.entryMainUserName = entryMainUserName;
	}

	public String getEntryMainAdminUserMblNum() {
		return entryMainAdminUserMblNum;
	}

	public void setEntryMainAdminUserMblNum(String entryMainAdminUserMblNum) {
		this.entryMainAdminUserMblNum = entryMainAdminUserMblNum;
	}

	public String getEntryMainAdminUserName() {
		return entryMainAdminUserName;
	}

	public void setEntryMainAdminUserName(String entryMainAdminUserName) {
		this.entryMainAdminUserName = entryMainAdminUserName;
	}

	public String getEntryMainStaffCode() {
		return entryMainStaffCode;
	}

	public void setEntryMainStaffCode(String entryMainStaffCode) {
		this.entryMainStaffCode = entryMainStaffCode;
	}

	public String getEntryMainStaffName() {
		return entryMainStaffName;
	}

	public void setEntryMainStaffName(String entryMainStaffName) {
		this.entryMainStaffName = entryMainStaffName;
	}

	public String getCreateMobileNum() {
		return createMobileNum;
	}

	public void setCreateMobileNum(String createMobileNum) {
		this.createMobileNum = createMobileNum;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getEntryTypeCode() {
		return entryTypeCode;
	}

	public void setEntryTypeCode(String entryTypeCode) {
		this.entryTypeCode = entryTypeCode;
	}

	public String getEntryTypeName() {
		return entryTypeName;
	}

	public void setEntryTypeName(String entryTypeName) {
		this.entryTypeName = entryTypeName;
	}

	public String getEntryOrgCode() {
		return entryOrgCode;
	}

	public void setEntryOrgCode(String entryOrgCode) {
		this.entryOrgCode = entryOrgCode;
	}

	public String getEntryOrgName() {
		return entryOrgName;
	}

	public void setEntryOrgName(String entryOrgName) {
		this.entryOrgName = entryOrgName;
	}

	public Integer getInfoTypeId() {
		return infoTypeId;
	}

	public void setInfoTypeId(Integer infoTypeId) {
		this.infoTypeId = infoTypeId;
	}

	@Override
	public String toString() {
		return "CalEntryInfoInnerEbo [infoId=" + infoId + ", infoCode=" + infoCode + ", infoTitle=" + infoTitle + ", infoMemo=" + infoMemo + ", infoStartTime="
				+ infoStartTime + ", infoEndTime=" + infoEndTime + ", infoMaxPerson=" + infoMaxPerson + ", infoJoinPerson=" + infoJoinPerson
				+ ", infoSourceType=" + infoSourceType + ", infoTypeId=" + infoTypeId + ", infoFlag=" + infoFlag + ", infoCreateTime=" + infoCreateTime
				+ ", entryId=" + entryId + ", entryCode=" + entryCode + ", entryMainId=" + entryMainId + ", entryMainType=" + entryMainType
				+ ", entryCreateId=" + entryCreateId + ", entryType=" + entryType + ", entryShowClass=" + entryShowClass + ", entryOrgId=" + entryOrgId
				+ ", entryFlag=" + entryFlag + ", entryCreateTime=" + entryCreateTime + ", entryTypeId=" + entryTypeId + ", entryIsOpen=" + entryIsOpen
				+ ", entryName=" + entryName + ", typeTitle=" + typeTitle + ", typeTimeInterval=" + typeTimeInterval + ", typeMaxPerson=" + typeMaxPerson
				+ ", typeShowClass=" + typeShowClass + ", typeMemo=" + typeMemo + ", entryMainOrgCode=" + entryMainOrgCode + ", entryMainOrgName="
				+ entryMainOrgName + ", entryMainDeptCode=" + entryMainDeptCode + ", entryMainDeptName=" + entryMainDeptName + ", entryMainUserMblNum="
				+ entryMainUserMblNum + ", entryMainUserName=" + entryMainUserName + ", entryMainAdminUserMblNum=" + entryMainAdminUserMblNum
				+ ", entryMainAdminUserName=" + entryMainAdminUserName + ", entryMainStaffCode=" + entryMainStaffCode + ", entryMainStaffName="
				+ entryMainStaffName + ", createMobileNum=" + createMobileNum + ", createName=" + createName + ", entryTypeCode=" + entryTypeCode
				+ ", entryTypeName=" + entryTypeName + ", entryOrgCode=" + entryOrgCode + ", entryOrgName=" + entryOrgName + "]";
	}

}
