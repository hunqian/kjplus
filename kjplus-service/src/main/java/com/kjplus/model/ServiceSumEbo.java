package com.kjplus.model;

import java.io.Serializable;

public class ServiceSumEbo implements Serializable {

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

	public ServiceSumEbo() {
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

	@Override
	public String toString() {
		return "ServiceSumEbo [id=" + id + ", orgId=" + orgId + ", bodyId=" + bodyId + ", bodyType=" + bodyType
				+ ", totalEarnPoint=" + totalEarnPoint + ", totalConsumePoint=" + totalConsumePoint + ", totalPoint="
				+ totalPoint + "]";
	}

}
