package com.kjplus.eto;

public class ExamGlycemEto extends ExamMainEto{

	// 血糖记录
	private double glycemicVal = 0;
	private String measureStatus = "K";//默认空腹

	/** default constructor */
	public ExamGlycemEto() {
		super();
	}

	// Property accessors
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
