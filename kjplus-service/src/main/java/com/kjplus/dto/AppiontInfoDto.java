package com.kjplus.dto;

import java.io.Serializable;

public class AppiontInfoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 454185192495250800L;
	// 预约表
	private String appCode;
	private String appTypeCode;
	private String appTypeName;
	private String appStatus;
	private Integer appStartTime;
	private String startDay;
	private String startTime;
	private Integer appendTime;
	// 日历信息表
	private String infoCode;
	private String infoTitle;
	private String infoMemo;
	private Integer infoStartTime;
	private Integer infoEndTime;
	private String infoFlag;

	public AppiontInfoDto() {
		super();
	}

	public AppiontInfoDto(String appCode, String appTypeCode, String appTypeName, String appStatus,
			Integer appStartTime, Integer appendTime, String infoCode, String infoTitle, String infoMemo,
			Integer infoStartTime, Integer infoEndTime, String infoFlag) {
		super();
		this.appCode = appCode;
		this.appTypeCode = appTypeCode;
		this.appTypeName = appTypeName;
		this.appStatus = appStatus;
		this.appStartTime = appStartTime;
		this.appendTime = appendTime;
		this.infoCode = infoCode;
		this.infoTitle = infoTitle;
		this.infoMemo = infoMemo;
		this.infoStartTime = infoStartTime;
		this.infoEndTime = infoEndTime;
		this.infoFlag = infoFlag;
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public String getAppTypeCode() {
		return appTypeCode;
	}

	public void setAppTypeCode(String appTypeCode) {
		this.appTypeCode = appTypeCode;
	}

	public String getAppTypeName() {
		return appTypeName;
	}

	public void setAppTypeName(String appTypeName) {
		this.appTypeName = appTypeName;
	}

	public String getAppStatus() {
		return appStatus;
	}

	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}

	public Integer getAppStartTime() {
		return appStartTime;
	}

	public void setAppStartTime(Integer appStartTime) {
		this.appStartTime = appStartTime;
	}

	public Integer getAppendTime() {
		return appendTime;
	}

	public void setAppendTime(Integer appendTime) {
		this.appendTime = appendTime;
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

	public String getStartDay() {
		return startDay;
	}

	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@Override
	public String toString() {
		return "AppiontInfoDto [appCode=" + appCode + ", appTypeCode=" + appTypeCode + ", appTypeName=" + appTypeName
				+ ", appStatus=" + appStatus + ", appStartTime=" + appStartTime + ", startDay=" + startDay
				+ ", startTime=" + startTime + ", appendTime=" + appendTime + ", infoCode=" + infoCode + ", infoTitle="
				+ infoTitle + ", infoMemo=" + infoMemo + ", infoStartTime=" + infoStartTime + ", infoEndTime="
				+ infoEndTime + ", infoFlag=" + infoFlag + "]";
	}

}
