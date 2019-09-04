package com.kjplus.dto;

/**
 * 用于mobile 预约服务时的页面显示
 * 
 * @author songyuebin
 * 
 */
public class MblCalInfoDto {

	private String calInfoCode;
	private String calInfoTitle;
	private String day;// TODO　服务仅在一天　
	private String startTime;// 开始时间
	private String endTime;// 结束时间
	private Integer joinPerson;// 已经预约人数
	private String status;// 当预约人数达到最大值时，不可继续预约 N,否则为Y,可以继续预约

	private String staffName;// 医生名
	private String deptName;// 部门名

	public MblCalInfoDto() {
		super();
	}

	public String getCalInfoCode() {
		return calInfoCode;
	}

	public void setCalInfoCode(String calInfoCode) {
		this.calInfoCode = calInfoCode;
	}

	public String getCalInfoTitle() {
		return calInfoTitle;
	}

	public void setCalInfoTitle(String calInfoTitle) {
		this.calInfoTitle = calInfoTitle;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getJoinPerson() {
		return joinPerson;
	}

	public void setJoinPerson(Integer joinPerson) {
		this.joinPerson = joinPerson;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

}
