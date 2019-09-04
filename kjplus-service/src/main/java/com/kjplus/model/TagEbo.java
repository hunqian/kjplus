package com.kjplus.model;

import java.io.Serializable;

public class TagEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3654781271715496001L;
	private Integer id;
	private Integer mainId;
	private String mainType;
	private Integer refValId;
	private String refValCode;
	private String refValName;
	
	public TagEbo() {
		super();
	}

	public TagEbo(Integer id, Integer mainId, String mainType, Integer refValId) {
		super();
		this.id = id;
		this.mainId = mainId;
		this.mainType = mainType;
		this.refValId = refValId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getRefValId() {
		return refValId;
	}

	public void setRefValId(Integer refValId) {
		this.refValId = refValId;
	}

	public String getRefValName() {
		return refValName;
	}

	public void setRefValName(String refValName) {
		this.refValName = refValName;
	}

	public String getRefValCode() {
		return refValCode;
	}

	public void setRefValCode(String refValCode) {
		this.refValCode = refValCode;
	}

	@Override
	public String toString() {
		return "TagEbo [id=" + id + ", mainId=" + mainId + ", mainType=" + mainType + ", refValId=" + refValId + "]";
	}
	
	
}
