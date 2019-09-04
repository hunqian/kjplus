package com.kjplus.model;

import java.io.Serializable;

public class CalendarEventTypeEbo implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7232058286631622330L;
	private Integer id;
	private Integer defId;
	private String title;
	private Integer timeInterval;
	private Integer maxPerson;
	private String showClass;
	private String memo;
	
	public CalendarEventTypeEbo() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDefId() {
		return defId;
	}

	public void setDefId(Integer defId) {
		this.defId = defId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getMaxPerson() {
		return maxPerson;
	}

	public void setMaxPerson(Integer maxPerson) {
		this.maxPerson = maxPerson;
	}

	public String getShowClass() {
		return showClass;
	}

	public void setShowClass(String showClass) {
		this.showClass = showClass;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getTimeInterval() {
		return timeInterval;
	}

	public void setTimeInterval(Integer timeInterval) {
		this.timeInterval = timeInterval;
	}

}
