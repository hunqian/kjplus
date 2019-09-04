package com.kjplus.dto;

public class ExamUrineDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4380316448085559943L;
	// examMain表信息
	private Integer mainId;
	private String examCode;
	private Integer examTypeId;

	// ExamUrine信息
	private int urineTypeId;
	private String urineTypeCode;
	private String urineTypeName;
	private String urineVl;
	
	/** default constructor */
	public ExamUrineDto() {
		super();
	}

	public Integer getMainId() {
		return mainId;
	}

	public void setMainId(Integer mainId) {
		this.mainId = mainId;
	}

	public String getExamCode() {
		return examCode;
	}

	public void setExamCode(String examCode) {
		this.examCode = examCode;
	}

	public Integer getExamTypeId() {
		return examTypeId;
	}

	public void setExamTypeId(Integer examTypeId) {
		this.examTypeId = examTypeId;
	}

	public int getUrineTypeId() {
		return urineTypeId;
	}

	public void setUrineTypeId(int urineTypeId) {
		this.urineTypeId = urineTypeId;
	}

	public String getUrineTypeCode() {
		return urineTypeCode;
	}

	public void setUrineTypeCode(String urineTypeCode) {
		this.urineTypeCode = urineTypeCode;
	}

	public String getUrineTypeName() {
		return urineTypeName;
	}

	public void setUrineTypeName(String urineTypeName) {
		this.urineTypeName = urineTypeName;
	}

	public String getUrineVl() {
		return urineVl;
	}

	public void setUrineVl(String urineVl) {
		this.urineVl = urineVl;
	}

}
