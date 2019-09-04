package com.kjplus.eto;

import com.kjplus.annotation.Validation;
import com.kjplus.constant.Constant;

public class ServiceConfigEto {

	private Integer srvId;

	private Double acceptorPoint = Constant.DEFAULT_POINT;
	private Double providerPoint = Constant.DEFAULT_POINT;
	@Validation
	private Integer orgId = 0;

	public ServiceConfigEto() {
		super();
	}

	public Integer getSrvId() {
		return srvId;
	}

	public void setSrvId(Integer srvId) {
		this.srvId = srvId;
	}

	public Double getAcceptorPoint() {
		return acceptorPoint;
	}

	public void setAcceptorPoint(Double acceptorPoint) {
		this.acceptorPoint = acceptorPoint;
	}

	public Double getProviderPoint() {
		return providerPoint;
	}

	public void setProviderPoint(Double providerPoint) {
		this.providerPoint = providerPoint;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

}
