package com.kjplus.dto;

import java.io.Serializable;
import java.util.Date;

public class ServCatDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2292204028580149595L;
	private Integer id;
	private String code;
	private String name;
	private String memo;
	private Integer orgId;
	private String orgName;
	private Double srvPrice;
	private String isDefault;
	private String status;
	private Date createTime;
	private Integer periodTypeId;
	private String periodName;// 服务周期类型
	private Integer periodVal;

	public ServCatDto() {
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

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
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

	public String getPeriodName() {
		return periodName;
	}

	public void setPeriodName(String periodName) {
		this.periodName = periodName;
	}

	public Integer getPeriodVal() {
		return periodVal;
	}

	public void setPeriodVal(Integer periodVal) {
		this.periodVal = periodVal;
	}

	public Integer getPeriodTypeId() {
		return periodTypeId;
	}

	public void setPeriodTypeId(Integer periodTypeId) {
		this.periodTypeId = periodTypeId;
	}

	@Override
	public String toString() {
		return "ServCatDto [id=" + id + ", code=" + code + ", name=" + name + ", memo=" + memo + ", orgId=" + orgId
				+ ", orgName=" + orgName + ", srvPrice=" + srvPrice + ", isDefault=" + isDefault + ", status=" + status
				+ ", createTime=" + createTime + ", periodTypeId=" + periodTypeId + ", periodName=" + periodName
				+ ", periodVal=" + periodVal + "]";
	}

}
