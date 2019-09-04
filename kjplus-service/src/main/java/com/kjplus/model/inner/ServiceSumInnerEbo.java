package com.kjplus.model.inner;

import java.io.Serializable;

public class ServiceSumInnerEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1444153790421575145L;
	private Integer id;
	private Integer orgId;
	private Integer bodyId;
	private String bodyType;
	// 总赚取积分
	private Double totalEarnPoint;
	// 总消费积分
	private Double totalConsumePoint;
	// 剩余积分
	private Double totalPoint;
	
	//积分详情
	private Integer mainId;
	private String mainType;
	private Integer createTime;
	private Double servicePoint;
	// 积分记录状态 I增加/D减少
	private String serviceStatus;
	// 积分描述
	private String memo;
	private String flag;

	public ServiceSumInnerEbo() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getBodyId() {
		return bodyId;
	}

	public void setBodyId(Integer bodyId) {
		this.bodyId = bodyId;
	}

	public String getBodyType() {
		return bodyType;
	}

	public void setBodyType(String bodyType) {
		this.bodyType = bodyType;
	}

	public Double getTotalEarnPoint() {
		return totalEarnPoint;
	}

	public void setTotalEarnPoint(Double totalEarnPoint) {
		this.totalEarnPoint = totalEarnPoint;
	}

	public Double getTotalConsumePoint() {
		return totalConsumePoint;
	}

	public void setTotalConsumePoint(Double totalConsumePoint) {
		this.totalConsumePoint = totalConsumePoint;
	}

	public Double getTotalPoint() {
		return totalPoint;
	}

	public void setTotalPoint(Double totalPoint) {
		this.totalPoint = totalPoint;
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

}
