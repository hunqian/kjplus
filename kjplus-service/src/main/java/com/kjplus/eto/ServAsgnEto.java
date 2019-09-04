package com.kjplus.eto;

import java.util.Date;

import org.ybk.basic.util.DateUtil;

import com.kjplus.annotation.Validation;
import com.kjplus.constant.Constant;

public class ServAsgnEto {

	public static final int CODE_LEN = 12;
	private String code;
	private Integer prsnId;
	private String prsnCode;// 档案编号
	private Integer staffId;
	private String stafCode;// 医生编号
	private String srvCode;// 服务编号
	@Validation
	private String deptCode;
	private Integer reqTime = DateUtil.getCurTimeInSec();
	private String status = Constant.SRV_ASSIGN_STATUS_APPLY;// 默认申请状态
	private Integer operTime = 0;
	@Validation
	private Date beginDay = DateUtil.newTime();// 默认为当前时间
	@Validation
	private Date endDay = DateUtil.parseDateStr(DateUtil.currDayOffset(365));// 默认签约时间为一年
	private String memo;

	public ServAsgnEto() {
		super();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getPrsnId() {
		return prsnId;
	}

	public void setPrsnId(Integer prsnId) {
		this.prsnId = prsnId;
	}

	public String getPrsnCode() {
		return prsnCode;
	}

	public void setPrsnCode(String prsnCode) {
		this.prsnCode = prsnCode;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public String getStafCode() {
		return stafCode;
	}

	public void setStafCode(String stafCode) {
		this.stafCode = stafCode;
	}

	public String getSrvCode() {
		return srvCode;
	}

	public void setSrvCode(String srvCode) {
		this.srvCode = srvCode;
	}

	public Integer getReqTime() {
		return reqTime;
	}

	public void setReqTime(Integer reqTime) {
		this.reqTime = reqTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getOperTime() {
		return operTime;
	}

	public void setOperTime(Integer operTime) {
		this.operTime = operTime;
	}

	public Date getBeginDay() {
		return beginDay;
	}

	public void setBeginDay(Date beginDay) {
		this.beginDay = beginDay;
	}

	public Date getEndDay() {
		return endDay;
	}

	public void setEndDay(Date endDay) {
		this.endDay = endDay;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public String toString() {
		return "ServAsgnEto [code=" + code + ", prsnId=" + prsnId + ", prsnCode=" + prsnCode + ", staffId=" + staffId
				+ ", stafCode=" + stafCode + ", srvCode=" + srvCode + ", deptCode=" + deptCode + ", reqTime=" + reqTime
				+ ", status=" + status + ", operTime=" + operTime + ", beginDay=" + beginDay + ", memo=" + memo + "]";
	}

}
