package com.kjplus.eto;

import com.kjplus.annotation.Validation;

public class ServiceSumEto {

	@Validation
	private Integer orgId;
	@Validation
	private Integer bodyId;
	@Validation
	private String bodyType;
	// 总赚取积分
	private Double totalEarnPoint = 0.0;
	// 总消费积分
	private Double totalConsumePoint = 0.0;
	// 剩余积分
	private Double totalPoint = 0.0;

	public ServiceSumEto() {
		super();
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
		return "ServiceSumEto [orgId=" + orgId + ", bodyId=" + bodyId + ", bodyType=" + bodyType + ", totalEarnPoint="
				+ totalEarnPoint + ", totalConsumePoint=" + totalConsumePoint + ", totalPoint=" + totalPoint + "]";
	}

}
