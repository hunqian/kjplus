package com.kjplus.dto;

/**
 * 用于mobile 我的积分显示
 * 
 * @author songyuebin
 * 
 */
public class MblScoreDto {

	private String memo;// 积分来源
	private String day;// 日期
	private Double point;// 数目

	public MblScoreDto() {
		super();
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public Double getPoint() {
		return point;
	}

	public void setPoint(Double point) {
		this.point = point;
	}

}
