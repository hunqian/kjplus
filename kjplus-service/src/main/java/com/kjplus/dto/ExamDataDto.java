package com.kjplus.dto;

public class ExamDataDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1330940178318874922L;
	// 血压信息
	private double shrinkPress;
	private double diastolePress;
	private Integer heartRate;
	// 血糖信息
	private double glycemicVal;
	private String measureStatus;

	/** default constructor */
	public ExamDataDto() {
		super();
	}

	public double getDiastolePress() {
		return diastolePress;
	}

	public void setDiastolePress(double diastolePress) {
		this.diastolePress = diastolePress;
	}

	public double getShrinkPress() {
		return shrinkPress;
	}

	public void setShrinkPress(double shrinkPress) {
		this.shrinkPress = shrinkPress;
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
