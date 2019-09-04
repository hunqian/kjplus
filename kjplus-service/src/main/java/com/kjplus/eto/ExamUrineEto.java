package com.kjplus.eto;

import com.kjplus.annotation.Validation;

public class ExamUrineEto extends ExamMainEto {

	/**
	 * 
	 */
	@Validation
	private Integer urineTypeId;
	private String urineVl;

	/** default constructor */
	public ExamUrineEto() {
		super();
	}

	// Property accessors
	public Integer getUrineTypeId() {
		return urineTypeId;
	}

	public void setUrineTypeId(Integer urineTypeId) {
		this.urineTypeId = urineTypeId;
	}

	public String getUrineVl() {
		return urineVl;
	}

	public void setUrineVl(String urineVl) {
		this.urineVl = urineVl;
	}

}
