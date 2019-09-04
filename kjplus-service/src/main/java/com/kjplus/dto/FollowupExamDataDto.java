package com.kjplus.dto;

public class FollowupExamDataDto implements java.io.Serializable {

	/**
	 * 用于随访的血糖、血压数据存储
	 * 
	 */
	private static final long serialVersionUID = 741149023735604978L;
	//examMain表信息
	private Integer id;
	private String examCode;
	private Integer examTypeId;
	private Integer flpeId;
	
	//examBlood信息
	private double shrinkPress;
	private double diastolePress;
	private Integer heartRate;
	
	//ExamGlycemic信息
	private double glycemicVal;
	private String measureStatus;
	
	/** default constructor */
	public FollowupExamDataDto() {
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

}
