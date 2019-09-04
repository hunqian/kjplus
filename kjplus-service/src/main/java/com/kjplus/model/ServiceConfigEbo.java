package com.kjplus.model;

import java.io.Serializable;

public class ServiceConfigEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6905779594929131188L;
	private Integer id;
	private Integer srvId;
	private Double acceptorPoint;
	private Double providerPoint;
	private Integer orgId;

	public ServiceConfigEbo() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "ServiceConfigEbo [id=" + id + ", srvId=" + srvId + ", acceptorPoint=" + acceptorPoint
				+ ", providerPoint=" + providerPoint + ", orgId=" + orgId + "]";
	}

}
