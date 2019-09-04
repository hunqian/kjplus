package com.kjplus.model;

import java.io.Serializable;
import java.util.Date;

public class TableDataHeadEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 841046025098225545L;
	private Integer id;
	private Integer cfgId;
	private String code;
	private String sheetCode;
	private Integer uid;
	private Integer mainId;
	private String mainType;
	private Integer staffId;
	private Integer orgId;
	private Double total;
	private Date sheetDay;
	private Integer beginTime;
	private Integer endTime;

	public TableDataHeadEbo() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

}
