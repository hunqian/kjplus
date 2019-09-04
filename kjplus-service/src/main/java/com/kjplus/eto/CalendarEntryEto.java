package com.kjplus.eto;

import java.util.Date;

import org.ybk.basic.util.DateUtil;

import com.kjplus.annotation.Validation;
import com.kjplus.constant.Constant;

public class CalendarEntryEto {

	public static final int CODE_LEN = 8;

	private String code;
	@Validation
	private Integer mainId;
	@Validation
	private String mainType;
	private String name = "";
	@Validation
	private Integer createId;
	@Validation
	private Integer calTypeId;
	private String entryType = Constant.ENTRY_TYPE_WORK; // 默认日历类型为工作日历
	private String isDefault = Constant.FLAG_YES;
	private String showClass;
	@Validation
	private Integer orgId;
	private String flag = Constant.FLAG_YES;
	private String isOpen = Constant.FLAG_YES;
	private Date createTime = DateUtil.newTime();

	public CalendarEntryEto() {
		super();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/*
	 * public String getMainCode() { return mainCode; }
	 * 
	 * public void setMainCode(String mainCode) { this.mainCode = mainCode; }
	 */

	public Integer getMainId() {
		return mainId;
	}

	public void setMainId(Integer uid) {
		this.mainId = uid;
	}

	public String getMainType() {
		return mainType;
	}

	public void setMainType(String mainType) {
		this.mainType = mainType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEntryType() {
		return entryType;
	}

	public void setEntryType(String entryType) {
		this.entryType = entryType;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public String getShowClass() {
		return showClass;
	}

	public void setShowClass(String showClass) {
		this.showClass = showClass;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCreateId() {
		return createId;
	}

	public void setCreateId(Integer createId) {
		this.createId = createId;
	}

	public Integer getCalTypeId() {
		return calTypeId;
	}

	public void setCalTypeId(Integer calTypeId) {
		this.calTypeId = calTypeId;
	}

	public String getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}

}
