package com.kjplus.model;

import java.util.Date;

public class ExamMainEbo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4380316448085559943L;
	private Integer id;
	private String examCode;
	private Integer examTypeId;
	private Integer flpeId;
	private Integer prsnId;
	private Integer staffId;
	private Integer orgId;
	private Date examDay;
	private Integer examTime;

	/** default constructor */
	public ExamMainEbo() {
		super();
	}

	// Property accessors
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	@Override
	public String toString() {
		return "ExamMainEbo [id=" + id + ", examCode=" + examCode + ", examTypeId=" + examTypeId + ", flpeId=" + flpeId
				+ ", prsnId=" + prsnId + ", staffId=" + staffId + ", orgId=" + orgId + ", examDay=" + examDay
				+ ", examTime=" + examTime + "]";
	}

}
