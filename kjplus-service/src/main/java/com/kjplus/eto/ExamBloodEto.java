package com.kjplus.eto;

public class ExamBloodEto extends ExamMainEto{

	// 血压记录 TODO 默认值设定
	private double shrinkPress = 0;
	private double diastolePress = 0;
	private Integer heartRate = 0;

	/** default constructor */
	public ExamBloodEto() {
		super();
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
