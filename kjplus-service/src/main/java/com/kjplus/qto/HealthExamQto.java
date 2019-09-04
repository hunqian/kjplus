package com.kjplus.qto;

public class HealthExamQto {

	private int prsnId;
	private int staffId;
	private int orgId;
	private String flag;
	private String startTime;// 起始时间
	private String endTime;// 结束时间

	public int getPrsnId() {
		return prsnId;
	}

	public void setPrsnId(int prsnId) {
		this.prsnId = prsnId;
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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
