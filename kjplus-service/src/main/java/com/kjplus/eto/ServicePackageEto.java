package com.kjplus.eto;

import java.util.Date;

import org.ybk.basic.util.DateUtil;

import com.kjplus.annotation.Validation;
import com.kjplus.constant.Constant;

public class ServicePackageEto {

	public static final int CODE_LEN = 8;
	private String code;
	@Validation
	private String name;
	private String alias;
	private String memo;
	@Validation
	private Integer orgId;
	private Double srvPrice = 0.0;// 服务价格默认0.0
	@Validation
	private String isDefault = Constant.FLAG_NO;// 默认添加服务不是默认服务
	@Validation
	private String status = Constant.FLAG_YES;
	private Date createTime = DateUtil.newTime();
	@Validation
	private String periodCode = "PERIOD_TYPE_MONTHS"; // 默认月
	@Validation
	private Integer periodVal = 0;

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

	public Integer getPeriodVal() {
		return periodVal;
	}

	public String getPeriodCode() {
		return periodCode;
	}

	public void setPeriodCode(String periodCode) {
		this.periodCode = periodCode;
	}

	public void setPeriodVal(Integer periodVal) {
		this.periodVal = periodVal;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

}
