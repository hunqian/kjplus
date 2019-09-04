package com.kjplus.dto;

/**
 * 用于mobile 测量记录列表显示
 * 
 * @author songyuebin
 * 
 */
public class MblExamDto {
	// 具体随访id
	private Integer examId;
	// 被随访人
	private Integer prsnId;
	private String name;
	// 头像路径
	private String url;
	// 测量年月日
	private String day;
	// 测量时分
	private String time;
	// 测量项目名称或者描述
	private String memo;

	public MblExamDto() {
		super();
	}

	public Integer getExamId() {
		return examId;
	}

	public void setExamId(Integer examId) {
		this.examId = examId;
	}

	public Integer getPrsnId() {
		return prsnId;
	}

	public void setPrsnId(Integer prsnId) {
		this.prsnId = prsnId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
