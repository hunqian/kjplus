package com.kjplus.qto;

public class ExamQto {

	//主表id
	private int exmainId;
	// 测量类型
	private int examTypeId;
	private String examTypeCode;
	// 个人id
	private int prsnId;
	// 查询多人，以逗号间隔（eg:21,22,23）
	private String prsnIdStr;
	private int staffId;
	private int orgId;

	// 测量时间段
	private String firstTime;
	private String lastTime;

	// Constructors

	/** default constructor */
	public ExamQto() {
		super();
	}

	// Property accessors

	public int getPrsnId() {
		return prsnId;
	}

	public int getExamTypeId() {
		return examTypeId;
	}

	public void setExamTypeId(int examTypeId) {
		this.examTypeId = examTypeId;
	}

	public String getExamTypeCode() {
		return examTypeCode;
	}

	public void setExamTypeCode(String examTypeCode) {
		this.examTypeCode = examTypeCode;
	}

	public void setPrsnId(int prsnId) {
		this.prsnId = prsnId;
	}

	public String getPrsnIdStr() {
		return prsnIdStr;
	}

	public void setPrsnIdStr(String prsnIdStr) {
		this.prsnIdStr = prsnIdStr;
	}

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public int getOrgId() {
		return orgId;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	public String getFirstTime() {
		return firstTime;
	}

	public void setFirstTime(String firstTime) {
		this.firstTime = firstTime;
	}

	public String getLastTime() {
		return lastTime;
	}

	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}

	public int getExmainId() {
		return exmainId;
	}

	public void setExmainId(int exmainId) {
		this.exmainId = exmainId;
	}

}
