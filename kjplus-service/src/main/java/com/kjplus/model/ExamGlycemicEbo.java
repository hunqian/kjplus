package com.kjplus.model;

public class ExamGlycemicEbo implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5389173846707147817L;
	private Integer id;
	private Integer exmainId;
	private double glycemicVal;
	private String measureStatus;
	
	/** default constructor */
	public ExamGlycemicEbo() {
		super();
	}

	// Property accessors
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getExmainId() {
		return exmainId;
	}

	public void setExmainId(Integer exmainId) {
		this.exmainId = exmainId;
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

	@Override
	public String toString() {
		return "ExamGlycemicEbo [id=" + id + ", exmainId=" + exmainId + ", glycemicVal=" + glycemicVal
				+ ", measureStatus=" + measureStatus + "]";
	}
}