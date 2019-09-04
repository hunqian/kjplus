package com.kjplus.qto;

public class FollowupMainQto {

	// 用于在查询时参数太多
	// Fields
	// 用于单独具体某个人随访记录
	private int prsnId = 0;
	// 用于查询多人的随访记录时(eg:mobile中随访记录)
	private String prsnIdStr;
	private int staffId = 0;
	private int orgId = 0;
	private int tbcfgId = 0;
	// 随访类型,F正规随访/M随机随访，如果是F，tccfgid大于0
	private String flpeMiscType;
	// 类型，高血压B/糖尿病D/新生儿N等，对应到参照
	private int flpeTypeId = 0;
	private int startTime;
	private int finishTime;
	// lineData = true 返回随访表具体列数据 ，false 不返回列数据　
	private boolean lineData = false;

	// Constructors

	/** default constructor */
	public FollowupMainQto() {
		super();
	}

	// Property accessors
	public int getPrsnId() {
		return prsnId;
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

	public int getTbcfgId() {
		return tbcfgId;
	}

	public void setTbcfgId(int tbcfgId) {
		this.tbcfgId = tbcfgId;
	}

	public String getFlpeMiscType() {
		return flpeMiscType;
	}

	public void setFlpeMiscType(String flpeMiscType) {
		this.flpeMiscType = flpeMiscType;
	}

	public int getFlpeTypeId() {
		return flpeTypeId;
	}

	public void setFlpeTypeId(int flpeTypeId) {
		this.flpeTypeId = flpeTypeId;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(int finishTime) {
		this.finishTime = finishTime;
	}

	public boolean isLineData() {
		return lineData;
	}

	public void setLineData(boolean lineData) {
		this.lineData = lineData;
	}

}
