package com.kjplus.model;

import java.io.Serializable;

public class ServiceListEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -193281620876105179L;
	private Integer id;
	private Integer sumId;
	private Integer mainId;
	private String mainType;
	private Integer createTime;
	private Double servicePoint;
	// 积分记录状态 I增加/D减少
	private String serviceStatus;
	// 操作用戶id,name(格式eg:1,张三)
	private String operName;
	// 积分描述
	private String memo;
	private String flag;

	public ServiceListEbo() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSumId() {
		return sumId;
	}

	public void setSumId(Integer sumId) {
		this.sumId = sumId;
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

	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	@Override
	public String toString() {
		return "ServiceListEbo [id=" + id + ", sumId=" + sumId + ", mainId=" + mainId + ", mainType=" + mainType + ", createTime=" + createTime
				+ ", servicePoint=" + servicePoint + ", serviceStatus=" + serviceStatus + ", operName=" + operName + ", memo=" + memo + ", flag=" + flag + "]";
	}

}
