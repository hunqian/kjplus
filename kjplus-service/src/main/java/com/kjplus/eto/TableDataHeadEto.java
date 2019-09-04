package com.kjplus.eto;

import java.util.Date;

import org.ybk.basic.util.DateUtil;

import com.kjplus.annotation.Validation;

public class TableDataHeadEto {

	public static final int CODE_LEN = 16;
	public static final int SHEET_CODE_LEN = 8;

	@Validation
	private Integer cfgId;
	private String code;
	private String sheetCode;
	@Validation
	private Integer uid;
	private Integer staffId;
	@Validation
	private Integer orgId;
	private Double total = 0.0;
	@Validation
	private Date sheetDay;
	@Validation
	private Integer beginTime;
	private Integer endTime = DateUtil.getCurTimeInSec();// 定义储存时间为结束时间
	//
	private Integer mainId = 0;
	private String mainType = "";

	public TableDataHeadEto() {
		super();
	}

	public Integer getCfgId() {
		return cfgId;
	}

	public void setCfgId(Integer cfgId) {
		this.cfgId = cfgId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSheetCode() {
		return sheetCode;
	}

	public void setSheetCode(String sheetCode) {
		this.sheetCode = sheetCode;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Date getSheetDay() {
		return sheetDay;
	}

	public void setSheetDay(Date sheetDay) {
		this.sheetDay = sheetDay;
	}

	public Integer getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Integer beginTime) {
		this.beginTime = beginTime;
	}

	public Integer getEndTime() {
		return endTime;
	}

	public void setEndTime(Integer endTime) {
		this.endTime = endTime;
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

}
