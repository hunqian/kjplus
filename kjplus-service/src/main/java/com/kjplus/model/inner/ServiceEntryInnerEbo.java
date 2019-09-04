package com.kjplus.model.inner;

import java.io.Serializable;
import java.util.Date;

public class ServiceEntryInnerEbo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 476114914811671852L;
	private Integer id;
	private Integer pid;
	private String srvType;
	private String srvCode;
	private String srvName;
	private String srvMemo;
	private String flag;
	private Date createTime;
	// 服务类型
	private String srvTypeCode;
	private String srvTypeName;
	// 组织信息
	private String orgCode;
	private String orgName;
	// 配置
	private Double acceptorPoint;
	private Double providerPoint;

	public ServiceEntryInnerEbo() {
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

	public String getSrvMemo() {
		return srvMemo;
	}

	public void setSrvMemo(String srvMemo) {
		this.srvMemo = srvMemo;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getSrvTypeCode() {
		return srvTypeCode;
	}

	public void setSrvTypeCode(String srvTypeCode) {
		this.srvTypeCode = srvTypeCode;
	}

	public String getSrvTypeName() {
		return srvTypeName;
	}

	public void setSrvTypeName(String srvTypeName) {
		this.srvTypeName = srvTypeName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
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

	@Override
	public String toString() {
		return "ServiceEntryInnerEbo [id=" + id + ", pid=" + pid + ", srvType=" + srvType + ", srvCode=" + srvCode
				+ ", srvName=" + srvName + ", srvMemo=" + srvMemo + ", flag=" + flag + ", createTime=" + createTime
				+ ", srvTypeCode=" + srvTypeCode + ", srvTypeName=" + srvTypeName + ", orgCode=" + orgCode
				+ ", orgName=" + orgName + ", acceptorPoint=" + acceptorPoint + ", providerPoint=" + providerPoint
				+ "]";
	}

}
