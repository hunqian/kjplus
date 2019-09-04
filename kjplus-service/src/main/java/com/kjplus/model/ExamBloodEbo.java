package com.kjplus.model;

public class ExamBloodEbo  implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7008818879154437575L;
	private Integer id;
	private Integer exmainId;
	private double shrinkPress;
	private double diastolePress;
	private Integer heartRate;
	
	/** default constructor */
	public ExamBloodEbo() {
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

	@Override
	public String toString() {
		return "ExamBloodEbo [id=" + id + ", exmainId=" + exmainId + ", shrinkPress=" + shrinkPress
				+ ", diastolePress=" + diastolePress + ", heartRate=" + heartRate + "]";
	}

}
