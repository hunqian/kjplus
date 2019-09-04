package com.kjplus.dto;

public class CalendarInfoDto {

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

	public CalendarInfoDto() {
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

	public Integer getInfoTypeId() {
		return infoTypeId;
	}

	public void setInfoTypeId(Integer infoTypeId) {
		this.infoTypeId = infoTypeId;
	}

	@Override
	public String toString() {
		return "CalendarInfoDto [infoId=" + infoId + ", infoCode=" + infoCode + ", infoTitle=" + infoTitle + ", infoMemo=" + infoMemo + ", infoStartTime="
				+ infoStartTime + ", infoEndTime=" + infoEndTime + ", infoMaxPerson=" + infoMaxPerson + ", infoJoinPerson=" + infoJoinPerson
				+ ", infoSourceType=" + infoSourceType + ", infoTypeId=" + infoTypeId + ", infoFlag=" + infoFlag + ", infoCreateTime=" + infoCreateTime + "]";
	}

}
