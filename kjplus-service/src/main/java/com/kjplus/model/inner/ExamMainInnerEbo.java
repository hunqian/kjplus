package com.kjplus.model.inner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kjplus.dto.ExamUrineDto;

public class ExamMainInnerEbo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4380316448085559943L;
	// examMain表信息
	private Integer id;
	private String examCode;
	private Integer examTypeId;
	private String examTypeCode;
	private Integer flpeId;
	private Integer prsnId;
	private Integer staffId;
	private Integer orgId;
	private Date examDay;
	private Integer examTime;

	// examBlood信息
	private double shrinkPress;
	private double diastolePress;
	private Integer heartRate;

	// ExamGlycemic信息
	private double glycemicVal;
	private String measureStatus;

	// ExamUrine信息
	private int urineTypeId;
	private String urineTypeCode;
	private String urineTypeName;
	private String urineVl;
	private List<ExamUrineDto> listUrine = new ArrayList<ExamUrineDto>();

	// TODO followup信息

	// 参照信息
	private String refTypeName;

	// 档案信息
	private String prsnName;

	// staff信息
	private String staffName;

	// 组织信息
	private String orgName;

	/** default constructor */
	public ExamMainInnerEbo() {
		super();
	}

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

	public double getShrinkPress() {
		return shrinkPress;
	}

	public void setShrinkPress(double shrinkPress) {
		this.shrinkPress = shrinkPress;
	}

	public double getDiastolePress() {
		return diastolePress;
	}

	public void setDiastolePress(double diastolePress) {
		this.diastolePress = diastolePress;
	}

	public Integer getHeartRate() {
		return heartRate;
	}

	public void setHeartRate(Integer heartRate) {
		this.heartRate = heartRate;
	}

	public double getGlycemicVal() {
		return glycemicVal;
	}

	public void setGlycemicVal(double glycemicVal) {
		this.glycemicVal = glycemicVal;
	}

	public String getMeasureStatus() {
		return measureStatus;
	}

	public void setMeasureStatus(String measureStatus) {
		this.measureStatus = measureStatus;
	}

	public String getRefTypeName() {
		return refTypeName;
	}

	public void setRefTypeName(String refTypeName) {
		this.refTypeName = refTypeName;
	}

	public String getPrsnName() {
		return prsnName;
	}

	public void setPrsnName(String prsnName) {
		this.prsnName = prsnName;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getExamTypeCode() {
		return examTypeCode;
	}

	public void setExamTypeCode(String examTypeCode) {
		this.examTypeCode = examTypeCode;
	}

	public int getUrineTypeId() {
		return urineTypeId;
	}

	public void setUrineTypeId(int urineTypeId) {
		this.urineTypeId = urineTypeId;
	}

	public String getUrineTypeCode() {
		return urineTypeCode;
	}

	public void setUrineTypeCode(String urineTypeCode) {
		this.urineTypeCode = urineTypeCode;
	}

	public String getUrineTypeName() {
		return urineTypeName;
	}

	public void setUrineTypeName(String urineTypeName) {
		this.urineTypeName = urineTypeName;
	}

	public String getUrineVl() {
		return urineVl;
	}

	public void setUrineVl(String urineVl) {
		this.urineVl = urineVl;
	}

	public List<ExamUrineDto> getListUrine() {
		return listUrine;
	}

	public void setListUrine(List<ExamUrineDto> listUrine) {
		this.listUrine = listUrine;
	}

}
