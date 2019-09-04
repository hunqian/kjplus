package com.kjplus.model.inner;

import java.io.Serializable;
import java.util.Date;

public class CalInfoInnerEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -383499867958687597L;

	// 日历主表
	private Integer entryId;
	private String entryCode;
	private Integer entryMainId;
	private String entryMainCode;
	private String entryMainName;
	private String cOrgName;
	private String cDeptName;
	private String cStaffName;
	private String entryMainType;
	private String entryName;
	private Integer entryCreateId;
	private Integer entryTypeId;
	private String entryTypeCode;
	private String entryTypeName;
	private Integer entryOrgId;
	private String entryOrgCode;
	private String entryOrgName;
	private String entryFlag;
	private Date entryCreateTime;
	private String entryIsOpen;
	
	// 日历信息表
	private Integer infoId;
	private String infoCode;
	private String infoTitle;
	private String infoMemo;
	private Integer infoStartTime;
	private Integer infoEndTime;
	private Integer infoMaxPerson;
	private Integer infoJoinPerson;
	private String infoFlag;
	private Integer infoCreateTime;
	private String infoShowClass;
	
	//关联的activity
	private Integer actMainId;
	private String actMainType;

	public CalInfoInnerEbo() {
		super();
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

	public String getEntryMainCode() {
		return entryMainCode;
	}

	public void setEntryMainCode(String entryMainCode) {
		this.entryMainCode = entryMainCode;
	}

	public String getEntryMainName() {
		return entryMainName;
	}

	public void setEntryMainName(String entryMainName) {
		this.entryMainName = entryMainName;
	}

	public String getEntryMainType() {
		return entryMainType;
	}

	public void setEntryMainType(String entryMainType) {
		this.entryMainType = entryMainType;
	}

	public String getEntryName() {
		return entryName;
	}

	public void setEntryName(String entryName) {
		this.entryName = entryName;
	}

	public Integer getEntryCreateId() {
		return entryCreateId;
	}

	public void setEntryCreateId(Integer entryCreateId) {
		this.entryCreateId = entryCreateId;
	}

	public Integer getEntryTypeId() {
		return entryTypeId;
	}

	public void setEntryTypeId(Integer entryTypeId) {
		this.entryTypeId = entryTypeId;
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

	public Integer getEntryOrgId() {
		return entryOrgId;
	}

	public void setEntryOrgId(Integer entryOrgId) {
		this.entryOrgId = entryOrgId;
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

	public String getEntryIsOpen() {
		return entryIsOpen;
	}

	public void setEntryIsOpen(String entryIsOpen) {
		this.entryIsOpen = entryIsOpen;
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
	
	public String getInfoShowClass() {
		return infoShowClass;
	}

	public void setInfoShowClass(String infoShowClass) {
		this.infoShowClass = infoShowClass;
	}

	public Integer getActMainId() {
		return actMainId;
	}

	public void setActMainId(Integer actMainId) {
		this.actMainId = actMainId;
	}

	public String getActMainType() {
		return actMainType;
	}

	public void setActMainType(String actMainType) {
		this.actMainType = actMainType;
	}

	public String getcOrgName() {
		return cOrgName;
	}

	public void setcOrgName(String cOrgName) {
		this.cOrgName = cOrgName;
	}

	public String getcDeptName() {
		return cDeptName;
	}

	public void setcDeptName(String cDeptName) {
		this.cDeptName = cDeptName;
	}

	public String getcStaffName() {
		return cStaffName;
	}

	public void setcStaffName(String cStaffName) {
		this.cStaffName = cStaffName;
	}

	public Integer getInfoId() {
		return infoId;
	}

	public void setInfoId(Integer infoId) {
		this.infoId = infoId;
	}

}
