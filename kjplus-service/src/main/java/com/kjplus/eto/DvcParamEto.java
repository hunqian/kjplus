package com.kjplus.eto;

import org.ybk.basic.util.DateUtil;

import com.kjplus.annotation.Validation;

/**
 * TAdminUser entity. @author MyEclipse Persistence Tools
 */

public class DvcParamEto  {

	// Fields
	@Validation
	private String deviceType;
	@Validation
	private String paramType;
	@Validation
	private String deviceParam;
	private String flag = "Y";
	private Integer createTime = DateUtil.getCurTimeInSec();
	
	// Constructors
	
	public DvcParamEto() {
		super();
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getParamType() {
		return paramType;
	}

	public void setParamType(String paramType) {
		this.paramType = paramType;
	}

	public String getDeviceParam() {
		return deviceParam;
	}

	public void setDeviceParam(String deviceParam) {
		this.deviceParam = deviceParam;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Integer getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "DvcParamEto [deviceType=" + deviceType + ", paramType=" + paramType + ", deviceParam=" + deviceParam
				+ ", flag=" + flag + ", createTime=" + createTime + "]";
	}

	
}