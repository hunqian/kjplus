package com.kjplus.model.inner;

import java.io.Serializable;
import java.util.Date;

public class ServiceConfigInnerEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6905779594929131188L;
	// 服务配置
	private Integer cfgId;
	private Integer srvId;
	private Integer mainId;
	private String mainType;
	private Double acceptorPoint;
	private Double providerPoint;
	private Integer orgId;
	// 总服务包
	private Integer pid;
	private String srvType;
	private String srvCode;
	private String srvName;
	private String srvFlag;
	private String srvMemo;
	private Date srvCreateTime;
	private Integer srvOrgId;
	// 服务类型
	private String srvTypeCode;
	private String srvTypeName;
	// 服务组织
	private String orgCode;
	private String orgName;
	// 日历信息
	private String calInfoCode;
	private Integer calEntryId;
	private String calTitle;
	private String calMemo;
	private Integer calStartTime;
	private Integer calEndTime;
	private Integer maxPerson;
	private Integer joinPerson;
	private String sourceType;
	private String calFlag;
	private Integer calCreateTime;

	public ServiceConfigInnerEbo() {
		super();
	}

	public Integer getCfgId() {
		return cfgId;
	}

	public void setCfgId(Integer cfgId) {
		this.cfgId = cfgId;
	}

	public Integer getSrvId() {
		return srvId;
	}

	public void setSrvId(Integer srvId) {
		this.srvId = srvId;
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

	public String getSrvFlag() {
		return srvFlag;
	}

	public void setSrvFlag(String srvFlag) {
		this.srvFlag = srvFlag;
	}

	public String getSrvMemo() {
		return srvMemo;
	}

	public void setSrvMemo(String srvMemo) {
		this.srvMemo = srvMemo;
	}

	public Date getSrvCreateTime() {
		return srvCreateTime;
	}

	public void setSrvCreateTime(Date srvCreateTime) {
		this.srvCreateTime = srvCreateTime;
	}

	public Integer getSrvOrgId() {
		return srvOrgId;
	}

	public void setSrvOrgId(Integer srvOrgId) {
		this.srvOrgId = srvOrgId;
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

	public String getCalInfoCode() {
		return calInfoCode;
	}

	public void setCalInfoCode(String calInfoCode) {
		this.calInfoCode = calInfoCode;
	}

	public Integer getCalEntryId() {
		return calEntryId;
	}

	public void setCalEntryId(Integer calEntryId) {
		this.calEntryId = calEntryId;
	}

	public String getCalTitle() {
		return calTitle;
	}

	public void setCalTitle(String calTitle) {
		this.calTitle = calTitle;
	}

	public String getCalMemo() {
		return calMemo;
	}

	public void setCalMemo(String calMemo) {
		this.calMemo = calMemo;
	}

	public Integer getCalStartTime() {
		return calStartTime;
	}

	public void setCalStartTime(Integer calStartTime) {
		this.calStartTime = calStartTime;
	}

	public Integer getCalEndTime() {
		return calEndTime;
	}

	public void setCalEndTime(Integer calEndTime) {
		this.calEndTime = calEndTime;
	}

	public Integer getMaxPerson() {
		return maxPerson;
	}

	public void setMaxPerson(Integer maxPerson) {
		this.maxPerson = maxPerson;
	}

	public Integer getJoinPerson() {
		return joinPerson;
	}

	public void setJoinPerson(Integer joinPerson) {
		this.joinPerson = joinPerson;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getCalFlag() {
		return calFlag;
	}

	public void setCalFlag(String calFlag) {
		this.calFlag = calFlag;
	}

	public Integer getCalCreateTime() {
		return calCreateTime;
	}

	public void setCalCreateTime(Integer calCreateTime) {
		this.calCreateTime = calCreateTime;
	}

}
