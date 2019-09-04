package com.kjplus.model;

import java.io.Serializable;
import java.util.Date;

public class ServicePackageEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -470370027731185820L;
	private Integer id;
	private String code;
	private String name;
	private String alias;
	private String memo;
	private Integer orgId;
	private Double srvPrice;
	private String isDefault;
	private String status;
	private Date createTime;
	private Integer periodTypeId;
	private Integer periodVal;

	public ServicePackageEbo() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Double getSrvPrice() {
		return srvPrice;
	}

	public void setSrvPrice(Double srvPrice) {
		this.srvPrice = srvPrice;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getPeriodTypeId() {
		return periodTypeId;
	}

	public void setPeriodTypeId(Integer periodTypeId) {
		this.periodTypeId = periodTypeId;
	}

	public Integer getPeriodVal() {
		return periodVal;
	}

	public void setPeriodVal(Integer periodVal) {
		this.periodVal = periodVal;
	}

	@Override
	public String toString() {
		return "ServicePackageEbo [id=" + id + ", code=" + code + ", name=" + name + ", alias=" + alias + ", memo="
				+ memo + ", orgId=" + orgId + ", srvPrice=" + srvPrice + ", isDefault=" + isDefault + ", status="
				+ status + ", createTime=" + createTime + ", periodTypeId=" + periodTypeId + ", periodVal=" + periodVal
				+ "]";
	}

}
