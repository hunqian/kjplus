package com.kjplus.model;

import java.io.Serializable;

public class CalendarInfoEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7801660221583196648L;
	private Integer id;
	private String code;
	private Integer calId;
	private String calTitle;
	private String calMemo;
	private Integer startTime;
	private Integer endTime;
	private Integer maxPerson;
	private Integer joinPerson;
	private String sourceType;
	private Integer typeId;
	private String flag;
	private Integer createTime;

	public CalendarInfoEbo() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getCalId() {
		return calId;
	}

	public void setCalId(Integer calId) {
		this.calId = calId;
	}

	public String getCalTitle() {
		return calTitle;
	}

	public void setCalTitle(String calTitle) {
		this.calTitle = calTitle;
	}

	public String getCalMemo() {
		return calMemo;
	}

	public void setCalMemo(String claMemo) {
		this.calMemo = claMemo;
	}

	public Integer getStartTime() {
		return startTime;
	}

	public void setStartTime(Integer startTime) {
		this.startTime = startTime;
	}

	public Integer getEndTime() {
		return endTime;
	}

	public void setEndTime(Integer endTime) {
		this.endTime = endTime;
	}

	public Integer getMaxPerson() {
		return maxPerson;
	}

	public void setMaxPerson(Integer maxPerson) {
		this.maxPerson = maxPerson;
	}

	public Integer getJoinPerson() {
		return joinPerson;
	}

	public void setJoinPerson(Integer joinPerson) {
		this.joinPerson = joinPerson;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Integer getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	@Override
	public String toString() {
		return "CalendarInfoEbo [id=" + id + ", code=" + code + ", calId=" + calId + ", calTitle=" + calTitle
				+ ", calMemo=" + calMemo + ", startTime=" + startTime + ", endTime=" + endTime + ", maxPerson="
				+ maxPerson + ", joinPerson=" + joinPerson + ", sourceType=" + sourceType + ", typeId=" + typeId
				+ ", flag=" + flag + ", createTime=" + createTime + "]";
	}

}
