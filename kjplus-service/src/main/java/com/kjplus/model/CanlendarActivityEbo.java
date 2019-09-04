package com.kjplus.model;

import java.io.Serializable;

public class CanlendarActivityEbo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8909401045052470448L;
	private Integer id;
	private Integer calInfoId;
	private Integer mainId;
	private String mainType;

	public CanlendarActivityEbo() {
		super();
	}

	public CanlendarActivityEbo(Integer id, Integer calInfoId, Integer mainId, String mainType) {
		super();
		this.id = id;
		this.calInfoId = calInfoId;
		this.mainId = mainId;
		this.mainType = mainType;
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

	@Override
	public String toString() {
		return "CanlendarActivityEbo [id=" + id + ", calInfoId=" + calInfoId + ", mainId=" + mainId + ", mainType="
				+ mainType + "]";
	}

}
