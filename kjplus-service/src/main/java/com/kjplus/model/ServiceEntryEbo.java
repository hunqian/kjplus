package com.kjplus.model;

import java.io.Serializable;
import java.util.Date;

public class ServiceEntryEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3518089522350438120L;
	private Integer id;
	private Integer pid;
	private String srvType;
	private String srvCode;
	private String srvName;
	private Integer srvTypeId;
	private String flag;
	private String srvMemo;
	private String opType;
	private Date createTime;
	private Integer orgId;

	public ServiceEntryEbo() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
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

	public Integer getSrvTypeId() {
		return srvTypeId;
	}

	public void setSrvTypeId(Integer srvTypeId) {
		this.srvTypeId = srvTypeId;
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

	public String getOpType() {
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
	}

	public void setSrvMemo(String srvMemo) {
		this.srvMemo = srvMemo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getSrvType() {
		return srvType;
	}

	public void setSrvType(String srvType) {
		this.srvType = srvType;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	@Override
	public String toString() {
		return "ServiceEntryEbo [id=" + id + ", pid=" + pid + ", srvType=" + srvType + ", srvCode=" + srvCode
				+ ", srvName=" + srvName + ", srvTypeId=" + srvTypeId + ", flag=" + flag + ", srvMemo=" + srvMemo
				+ ", opType=" + opType + ", createTime=" + createTime + ", orgId=" + orgId + "]";
	}

}
