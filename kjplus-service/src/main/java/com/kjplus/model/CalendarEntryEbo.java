package com.kjplus.model;

import java.io.Serializable;
import java.util.Date;

public class CalendarEntryEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4404966739830486794L;
	private Integer id;
	private String code;
	private Integer mainId;
	private String mainType;
	private String name;
	private Integer createId;
	private Integer calTypeId;
	private Integer orgId;
	private String entryType;
	private String isDefault;
	private String showClass;
	private String flag;
	private Date createTime;
	private String isOpen;

	public CalendarEntryEbo() {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
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

	public String getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}

	@Override
	public String toString() {
		return "CalendarEntryEbo [id=" + id + ", code=" + code + ", mainId=" + mainId + ", mainType=" + mainType
				+ ", name=" + name + ", createId=" + createId + ", calTypeId=" + calTypeId + ", orgId=" + orgId
				+ ", entryType=" + entryType + ", showClass=" + showClass + ", flag=" + flag + ", createTime="
				+ createTime + ", isOpen=" + isOpen + "]";
	}

}
