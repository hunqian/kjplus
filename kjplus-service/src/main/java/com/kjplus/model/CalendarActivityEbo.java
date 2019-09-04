package com.kjplus.model;

import java.io.Serializable;

public class CalendarActivityEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4404966739830486794L;
	private Integer id;
	private Integer calInfoId;
	private Integer mainId;
	private String mainType;
	
	public CalendarActivityEbo() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCalInfoId() {
		return calInfoId;
	}

	public void setCalInfoId(Integer calInfoId) {
		this.calInfoId = calInfoId;
	}

	public Integer getMainId() {
		return mainId;
	}

	public void setMainId(Integer mainId) {
		this.mainId = mainId;
	}

	public String getMainType() {
		return mainType;
	}

	public void setMainType(String mainType) {
		this.mainType = mainType;
	}

}
