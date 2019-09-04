package com.kjplus.eto;

import org.ybk.basic.util.DateUtil;

import com.kjplus.annotation.Validation;
import com.kjplus.constant.Constant;

public class ServiceListEto {

	@Validation
	private Integer sumId;
	@Validation
	private Integer opId;
	private Integer mainId;
	private String mainType;
	private Integer createTime = DateUtil.getCurTimeInSec();
	private Double servicePoint = 0.0;
	// 积分记录状态 I增加/D减少
	private String serviceStatus;
	// 操作人信息
	private String operName;
	// 积分描述
	private String memo;
	private String flag = Constant.FLAG_YES;

	public ServiceListEto() {
		super();
	}

	public Integer getSumId() {
		return sumId;
	}

	public void setSumId(Integer sumId) {
		this.sumId = sumId;
	}

	public Integer getOpId() {
		return opId;
	}

	public void setOpId(Integer opId) {
		this.opId = opId;
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

	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

}
