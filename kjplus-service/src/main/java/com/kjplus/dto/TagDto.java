package com.kjplus.dto;

import java.io.Serializable;

import com.kjplus.constant.Constant;

public class TagDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1818535268951980682L;
	private Integer id = 0;
	private Integer refValId;
	private String refValName;
	private String checked = Constant.FLAG_YES;
	
	public TagDto() {
		super();
	}

	public TagDto(Integer id, Integer refValId, String refValName) {
		super();
		this.id = id;
		this.refValId = refValId;
		this.refValName = refValName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	
	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}
	
}
