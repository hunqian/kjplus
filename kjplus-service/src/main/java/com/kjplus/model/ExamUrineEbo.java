package com.kjplus.model;

public class ExamUrineEbo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2734510342185534228L;
	private Integer id;
	private Integer exmainId;
	private Integer urineTypeId;
	private String urineVl;

	/** default constructor */
	public ExamUrineEbo() {
		super();
	}

	// Property accessors
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getExmainId() {
		return exmainId;
	}

	public void setExmainId(Integer exmainId) {
		this.exmainId = exmainId;
	}

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

	@Override
	public String toString() {
		return "ExamUrineEbo [id=" + id + ", exmainId=" + exmainId + ", urineTypeId=" + urineTypeId + ", urineVl=" + urineVl + "]";
	}

}
