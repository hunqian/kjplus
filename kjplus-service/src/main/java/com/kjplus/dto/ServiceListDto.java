package com.kjplus.dto;

public class ServiceListDto {

	// 积分详情
	private Integer mainId;
	private String mainType;
	private Integer createTime;
	private Double servicePoint;
	// 积分记录状态 I增加/D减少
	private String serviceStatus;
	// 积分描述
	private String memo;
	private String flag;

	public ServiceListDto() {
		super();
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

	public Integer getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}

	public Double getServicePoint() {
		return servicePoint;
	}

	public void setServicePoint(Double servicePoint) {
		this.servicePoint = servicePoint;
	}

	public String getServiceStatus() {
		return serviceStatus;
	}

	public void setServiceStatus(String serviceStatus) {
		this.serviceStatus = serviceStatus;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "ServiceListDto [mainId=" + mainId + ", mainType=" + mainType + ", createTime=" + createTime
				+ ", servicePoint=" + servicePoint + ", serviceStatus=" + serviceStatus + ", memo=" + memo + ", flag="
				+ flag + "]";
	}

}
