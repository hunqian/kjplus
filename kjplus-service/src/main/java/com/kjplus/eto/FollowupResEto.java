package com.kjplus.eto;

import com.kjplus.annotation.Validation;

public class FollowupResEto {

	@Validation
	private String code;
	private Integer resId;
	private String resMemo;
	private Integer resStaffId;

	// Constructors

	/** default constructor */
	public FollowupResEto() {
		super();
	}

	// Property accessors
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getResId() {
		return resId;
	}

	public void setResId(Integer resId) {
		this.resId = resId;
	}

	public String getResMemo() {
		return resMemo;
	}

	public void setResMemo(String resMemo) {
		this.resMemo = resMemo;
	}

	public Integer getResStaffId() {
		return resStaffId;
	}

	public void setResStaffId(Integer resStaffId) {
		this.resStaffId = resStaffId;
	}

}
