package com.kjplus.dto;

import java.util.ArrayList;
import java.util.List;

import com.kjplus.model.CalendarInfoEbo;

/**
 * TAdminUser entity. @author MyEclipse Persistence Tools
 */

public class ActivityEnrolInnerDto {

	private Integer uid;
	private String userType;
	private String status;
	private Integer enrolTime;
	List<CalendarInfoEbo> listCalInfo = new ArrayList<CalendarInfoEbo>();
	
	//Constructors
	public ActivityEnrolInnerDto() {
		super();
	}

	public ActivityEnrolInnerDto(Integer uid, String userType, String status, Integer enrolTime,
			List<CalendarInfoEbo> listCalInfo) {
		super(); 
		this.uid = uid;
		this.userType = userType;
		this.status = status;
		this.enrolTime = enrolTime;
		this.listCalInfo = listCalInfo;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getEnrolTime() {
		return enrolTime;
	}

	public void setEnrolTime(Integer enrolTime) {
		this.enrolTime = enrolTime;
	}

	public List<CalendarInfoEbo> getListCalInfo() {
		return listCalInfo;
	}

	public void setListCalInfo(List<CalendarInfoEbo> listCalInfo) {
		this.listCalInfo = listCalInfo;
	}

	@Override
	public String toString() {
		return "ActivityEnrolInnerDto [uid=" + uid + ", userType=" + userType + ", status=" + status + ", enrolTime="
				+ enrolTime + ", listCalInfo=" + listCalInfo + "]";
	}
	
}