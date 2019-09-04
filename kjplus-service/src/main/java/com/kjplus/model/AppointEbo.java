package com.kjplus.model;

import java.io.Serializable;

public class AppointEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5083913624436868481L;
	private Integer id;
	private String code;
	private Integer calInfoId;
	private Integer prsnId;
	private Integer orgId;
	private Integer mainId;
	private String mainType;
	private String status;
	private Integer startTime;
	private Integer endTime;
	private Integer appTypeId;
	private String appMemo;
	private Integer createTime;

	public AppointEbo() {

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

	public Integer getPrsnId() {
		return prsnId;
	}

	public void setPrsnId(Integer prsnId) {
		this.prsnId = prsnId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getStartTime() {
		return startTime;
	}

	public void setStartTime(Integer startTime) {
		this.startTime = startTime;
	}

	public Integer getEndTime() {
		return endTime;
	}

	public void setEndTime(Integer endTime) {
		this.endTime = endTime;
	}

	public String getAppMemo() {
		return appMemo;
	}

	public void setAppMemo(String appMemo) {
		this.appMemo = appMemo;
	}

	public Integer getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}

	public Integer getCalInfoId() {
		return calInfoId;
	}

	public void setCalInfoId(Integer calInfoId) {
		this.calInfoId = calInfoId;
	}

	public Integer getAppTypeId() {
		return appTypeId;
	}

	public void setAppTypeId(Integer appTypeId) {
		this.appTypeId = appTypeId;
	}

	@Override
	public String toString() {
		return "AppointEbo [id=" + id + ", code=" + code + ", calInfoId=" + calInfoId + ", prsnId=" + prsnId + ", orgId=" + orgId + ", mainId=" + mainId
				+ ", mainType=" + mainType + ", status=" + status + ", startTime=" + startTime + ", endTime=" + endTime + ", appTypeId=" + appTypeId
				+ ", appMemo=" + appMemo + ", createTime=" + createTime + "]";
	}

}
