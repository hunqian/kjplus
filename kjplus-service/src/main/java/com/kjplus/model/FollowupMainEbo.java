package com.kjplus.model;

import java.util.Date;

public class FollowupMainEbo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7407612162117361477L;
	private Integer id;
	private String code;
	private String selfCode;
	private Integer prsnId;
	private Integer stafffId;
	private Integer orgId;
	private Integer tbcfgId;
	private String flpeMiscType;
	private Integer flpeTypeId;
	private Integer flpeTime;
	private Integer prsnTypeId;
	private Date flpeDay;
	private Integer resId;
	private String resMemo;
	private Integer resStaffId;

	// Constructors

	/** default constructor */
	public FollowupMainEbo() {
		super();
	}

	// Property accessors
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

	public String getSelfCode() {
		return selfCode;
	}

	public void setSelfCode(String selfCode) {
		this.selfCode = selfCode;
	}

	public Integer getPrsnId() {
		return prsnId;
	}

	public void setPrsnId(Integer prsnId) {
		this.prsnId = prsnId;
	}

	public Integer getStafffId() {
		return stafffId;
	}

	public void setStafffId(Integer stafffId) {
		this.stafffId = stafffId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getTbcfgId() {
		return tbcfgId;
	}

	public void setTbcfgId(Integer tbcfgId) {
		this.tbcfgId = tbcfgId;
	}

	public String getFlpeMiscType() {
		return flpeMiscType;
	}

	public void setFlpeMiscType(String flpeMiscType) {
		this.flpeMiscType = flpeMiscType;
	}

	public Integer getFlpeTypeId() {
		return flpeTypeId;
	}

	public void setFlpeTypeId(Integer flpeTypeId) {
		this.flpeTypeId = flpeTypeId;
	}

	public Integer getFlpeTime() {
		return flpeTime;
	}

	public void setFlpeTime(Integer flpeTime) {
		this.flpeTime = flpeTime;
	}

	public Date getFlpeDay() {
		return flpeDay;
	}

	public void setFlpeDay(Date flpeDay) {
		this.flpeDay = flpeDay;
	}

	public Integer getResId() {
		return resId;
	}

	public void setResId(Integer resId) {
		this.resId = resId;
	}

	public String getResMemo() {
		return resMemo;
	}

	public void setResMemo(String resMemo) {
		this.resMemo = resMemo;
	}

	public Integer getResStaffId() {
		return resStaffId;
	}

	public void setResStaffId(Integer resStaffId) {
		this.resStaffId = resStaffId;
	}

	public Integer getPrsnTypeId() {
		return prsnTypeId;
	}

	public void setPrsnTypeId(Integer prsnTypeId) {
		this.prsnTypeId = prsnTypeId;
	}

	@Override
	public String toString() {
		return "FollowupMainEbo [id=" + id + ", code=" + code + ", selfCode=" + selfCode + ", prsnId=" + prsnId + ", stafffId=" + stafffId + ", orgId=" + orgId + ", tbcfgId=" + tbcfgId
				+ ", flpeMiscType=" + flpeMiscType + ", flpeTypeId=" + flpeTypeId + ", flpeTime=" + flpeTime + ", flpeDay=" + flpeDay + ", resId=" + resId + ", resMemo=" + resMemo + ", resStaffId="
				+ resStaffId + "]";
	}

}
