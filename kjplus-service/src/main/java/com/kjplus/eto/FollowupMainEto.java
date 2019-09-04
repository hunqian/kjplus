package com.kjplus.eto;

import java.util.Date;

import org.ybk.basic.util.DateUtil;

public class FollowupMainEto {

	// Fields
	public static final int MAX_CODE_LEN = 16;
	public static final int MAX_SELFCODE_LEN = 10;

	private String code;
	private String selfCode;
	private Integer prsnId;
	private Integer staffId=0;//默认自测
	private Integer orgId = 0;
	private Integer tbcfgId=0;//默认自测
	private String flpeMiscType = "M";//默认非正规;
	private Integer flpeTypeId=0;
	private Integer flpeTime = DateUtil.getCurTimeInSec();//默认当前时间;
	private Date flpeDay = new Date();//默认当前时间;
	
	// Constructors

	/** default constructor */
	public FollowupMainEto() {
		super();
	}

	// Property accessors
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
		return staffId;
	}

	public void setStafffId(Integer stafffId) {
		this.staffId = stafffId;
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

}
