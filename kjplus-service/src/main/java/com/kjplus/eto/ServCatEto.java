package com.kjplus.eto;

import java.util.Date;

import org.ybk.basic.util.DateUtil;

import com.kjplus.annotation.Validation;
import com.kjplus.constant.Constant;

public class ServCatEto {

	public static final int CODE_LEN = 8;
	private String code;
	@Validation
	private String name;
	private String memo;
	@Validation
	private String orgCode;
	private Double srvPrice = 0.0;// 服务价格默认0.0
	@Validation
	private String isDefault = Constant.FLAG_NO;//默认添加服务不是默认服务
	@Validation
	private String status = Constant.FLAG_YES;
	private Date createTime = DateUtil.newTime();
	@Validation
	private String periodCode;
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

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	
	
}
