package com.kjplus.eto;

import java.util.Date;

import com.kjplus.annotation.Validation;
import com.kjplus.constant.Constant;
import com.mq.util.DateUtil;

public class ServiceEntryEto {

	public static final int CODE_LEN = 8;
	// 父编号，可为空
	private Integer pid = 0;
	// S服务类/M其它
	@Validation
	private String srvType = "S";
	// 编号
	private String srvCode;
	// 服务名称
	@Validation
	private String srvName;
	// 参照类型编号
	@Validation
	private Integer srvTypeId;
	private String flag = Constant.FLAG_YES;
	private String srvMemo;
	//操作类型 NEW新建/UPD更新/OTH其他(默认）
	private String opType="OTH";
	private Date CreateTime = DateUtil.newTime();// 创建时间
	private Integer orgId = 0;

	private Double acceptorPoint;
	private Double providerPoint;

	public String getSrvType() {
		return srvType;
	}

	public void setSrvType(String srvType) {
		this.srvType = srvType;
	}

	public String getSrvCode() {
		return srvCode;
	}

	public void setSrvCode(String srvCode) {
		this.srvCode = srvCode;
	}

	public String getSrvName() {
		return srvName;
	}

	public void setSrvName(String srvName) {
		this.srvName = srvName;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getSrvMemo() {
		return srvMemo;
	}

	public void setSrvMemo(String srvMemo) {
		this.srvMemo = srvMemo;
	}

	public String getOpType() {
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
	}

	public Date getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(Date createTime) {
		CreateTime = createTime;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getSrvTypeId() {
		return srvTypeId;
	}

	public void setSrvTypeId(Integer srvTypeId) {
		this.srvTypeId = srvTypeId;
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

}
