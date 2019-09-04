package com.kjplus.dto;

/**
 * 用于mobile预约接种时的页面显示
 * 
 * @author songyuebin
 * 
 */
public class MblAppointVccDto {

	private Integer calInfoId;// 日历信息Id
	private String calInfoCode;// 日历信息code
	private String startDay;// 开始年月日
	private String startTime;// 开始 时分
	private String finishDay;// 結束年月日
	private String finishTime;// 結束时分
	private Integer joinPerson;// 已预约人数
	private Integer maxPerson;// 最多可预约人数
	private String status;// 目前预约状态（人数是否已经预约满）Y可预约/N不可预约
	private Integer infoTypeId;// 日历信息类型

	public MblAppointVccDto() {
		super();
	}

	public Integer getCalInfoId() {
		return calInfoId;
	}

	public void setCalInfoId(Integer calInfoId) {
		this.calInfoId = calInfoId;
	}

	public String getCalInfoCode() {
		return calInfoCode;
	}

	public void setCalInfoCode(String calInfoCode) {
		this.calInfoCode = calInfoCode;
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

	public String getFinishDay() {
		return finishDay;
	}

	public void setFinishDay(String finishDay) {
		this.finishDay = finishDay;
	}

	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

	public Integer getJoinPerson() {
		return joinPerson;
	}

	public void setJoinPerson(Integer joinPerson) {
		this.joinPerson = joinPerson;
	}

	public Integer getMaxPerson() {
		return maxPerson;
	}

	public void setMaxPerson(Integer maxPerson) {
		this.maxPerson = maxPerson;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getInfoTypeId() {
		return infoTypeId;
	}

	public void setInfoTypeId(Integer infoTypeId) {
		this.infoTypeId = infoTypeId;
	}

	@Override
	public String toString() {
		return "MblAppointAccDto [calInfoId=" + calInfoId + ", calInfoCode=" + calInfoCode + ", startDay=" + startDay + ", startTime=" + startTime
				+ ", finishDay=" + finishDay + ", finishTime=" + finishTime + ", joinPerson=" + joinPerson + ", maxPerson=" + maxPerson + ", status=" + status
				+ ", infoTypeId=" + infoTypeId + "]";
	}

}
