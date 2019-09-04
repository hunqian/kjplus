package com.kjplus.dto;

public class ExamBloodDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4380316448085559943L;
	//examMain表信息
	private Integer mainId;
	private String examCode;
	private Integer examTypeId;
	//examBlood信息
	private double shrinkPress;
	private double diastolePress;
	private Integer heartRate;

	/** default constructor */
	public ExamBloodDto() {
		super();
	}

	public Integer getMainId() {
		return mainId;
	}

	public void setMainId(Integer mainId) {
		this.mainId = mainId;
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

}
