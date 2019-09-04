package com.kjplus.eto;

import java.util.Date;

import org.ybk.basic.util.DateUtil;

import com.kjplus.annotation.Validation;

public class ExamMainEto {

	public static final int MAX_CODE_LEG = 16;

	private String examCode;
	private Integer examTypeId = 0;
	private Integer flpeId = 0;
	@Validation
	private Integer prsnId;
	private Integer staffId = 0;
	private Integer orgId = 0;
	private Date examDay = DateUtil.newTime();
	private Integer examTime = DateUtil.getCurTimeInSec();
	
	/** default constructor */
	public ExamMainEto() {
		super();
	}

	// Property accessors
	public String getExamCode() {
		return examCode;
	}

	public void setExamCode(String examCode) {
		this.examCode = examCode;
	}

	public Integer getExamTypeId() {
		return examTypeId;
	}

	public void setExamTypeId(Integer examTypeId) {
		this.examTypeId = examTypeId;
	}

	public Integer getFlpeId() {
		return flpeId;
	}

	public void setFlpeId(Integer flpeId) {
		this.flpeId = flpeId;
	}

	public Integer getPrsnId() {
		return prsnId;
	}

	public void setPrsnId(Integer prsnId) {
		this.prsnId = prsnId;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Date getExamDay() {
		return examDay;
	}

	public void setExamDay(Date examDay) {
		this.examDay = examDay;
	}

	public Integer getExamTime() {
		return examTime;
	}

	public void setExamTime(Integer examTime) {
		this.examTime = examTime;
	}
}
