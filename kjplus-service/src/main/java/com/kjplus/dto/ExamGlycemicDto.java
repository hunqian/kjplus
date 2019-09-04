package com.kjplus.dto;

public class ExamGlycemicDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4380316448085559943L;
	//examMain表信息
	private Integer mainId;
	private String examCode;
	private Integer examTypeId;
	
	//ExamGlycemic信息
	private double glycemicVal;
	private String measureStatus;
	
	/** default constructor */
	public ExamGlycemicDto() {
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
