package com.kjplus.dto;

public class ExamMainSimpleDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4380316448085559943L;
	// examMain表信息
	private Integer id;
	private String examCode;
	private Integer examTypeId;
	// 参照信息
	private String refCode;
	private String refTypeName;
	private Integer flpeId;
	private Integer prsnId;
	private Integer staffId;
	private Integer orgId;
	private String examDay;
	private Integer examTime;

	// 检查对象DTO,可能是ExamBloodSimpleDto,ExamGlycemicSimpleDto,List<ExamUrineDto>
	private Object examObjDto;

	// 显示摘要
	private String digest;
	// 档案信息
	private String prsnName;
	// staff信息
	private String staffName;

	// 组织信息
	private String orgName;

	/** default constructor */
	public ExamMainSimpleDto() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getExamCode() {
		return examCode;
	}

	public void setExamCode(String examCode) {
		this.examCode = examCode;
	}

	public String getRefCode() {
		return refCode;
	}

	public void setRefCode(String refCode) {
		this.refCode = refCode;
	}

	public Integer getExamTypeId() {
		return examTypeId;
	}

	public void setExamTypeId(Integer examTypeId) {
		this.examTypeId = examTypeId;
	}

	public String getRefTypeName() {
		return refTypeName;
	}

	public void setRefTypeName(String refTypeName) {
		this.refTypeName = refTypeName;
	}

	public Integer getFlpeId() {
		return flpeId;
	}

	public void setFlpeId(Integer flpeId) {
		this.flpeId = flpeId;
	}

	public Integer getPrsnId() {
		return prsnId;
	}

	public void setPrsnId(Integer prsnId) {
		this.prsnId = prsnId;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getExamDay() {
		return examDay;
	}

	public void setExamDay(String examDay) {
		this.examDay = examDay;
	}

	public Integer getExamTime() {
		return examTime;
	}

	public void setExamTime(Integer examTime) {
		this.examTime = examTime;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public String getPrsnName() {
		return prsnName;
	}

	public void setPrsnName(String prsnName) {
		this.prsnName = prsnName;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Object getExamObjDto() {
		return examObjDto;
	}

	public void setExamObjDto(Object examObjDto) {
		this.examObjDto = examObjDto;
	}

	@Override
	public String toString() {
		return "ExamMainSimpleDto [id=" + id + ", examCode=" + examCode + ", examTypeId=" + examTypeId + ", refCode=" + refCode + ", refTypeName="
				+ refTypeName + ", flpeId=" + flpeId + ", prsnId=" + prsnId + ", staffId=" + staffId + ", orgId=" + orgId + ", examDay=" + examDay
				+ ", examTime=" + examTime + ", examObjDto=" + examObjDto + ", digest=" + digest + ", prsnName=" + prsnName + ", staffName=" + staffName
				+ ", orgName=" + orgName + "]";
	}

}
