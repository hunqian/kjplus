package com.kjplus.dto;

import java.util.ArrayList;
import java.util.List;

public class ServiceSumDto {

	/**
	 * 
	 */
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

	// 积分详情
	private List<ServiceListDto> listLt = new ArrayList<ServiceListDto>();

	public ServiceSumDto() {
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

	public List<ServiceListDto> getListLt() {
		return listLt;
	}

	public void setListLt(List<ServiceListDto> listLt) {
		this.listLt = listLt;
	}

	@Override
	public String toString() {
		return "ServiceSumDto [id=" + id + ", orgId=" + orgId + ", bodyId=" + bodyId + ", bodyType=" + bodyType
				+ ", totalEarnPoint=" + totalEarnPoint + ", totalConsumePoint=" + totalConsumePoint + ", totalPoint="
				+ totalPoint + ", listLt=" + listLt + "]";
	}

}
