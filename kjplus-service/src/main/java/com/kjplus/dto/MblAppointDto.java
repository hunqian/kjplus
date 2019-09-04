package com.kjplus.dto;

/**
 * 用于mobile预约服务时的页面显示
 * 
 * @author songyuebin
 * 
 */
public class MblAppointDto {
	private Integer prsnId;
	private String prsnCode;
	private String name;// 用户名
	private String relationTypeName;// 关系
	private String day;// 年月日
	private String time;// 时分
	private String memo;// 描述（所有预约活动的简述,名称）
	private String status;// 预约状态
	private String appointCode;// 预约记录

	public MblAppointDto() {
		super();
	}

	public Integer getPrsnId() {
		return prsnId;
	}

	public void setPrsnId(Integer prsnId) {
		this.prsnId = prsnId;
	}

	public String getPrsnCode() {
		return prsnCode;
	}

	public void setPrsnCode(String prsnCode) {
		this.prsnCode = prsnCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRelationTypeName() {
		return relationTypeName;
	}

	public void setRelationTypeName(String relationTypeName) {
		this.relationTypeName = relationTypeName;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAppointCode() {
		return appointCode;
	}

	public void setAppointCode(String appointCode) {
		this.appointCode = appointCode;
	}

}
