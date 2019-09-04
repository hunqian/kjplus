package com.kjplus.model.inner;

import java.io.Serializable;

public class ZanStatusInnerEbo implements Serializable {

	private static final long serialVersionUID = 3323406224022334080L;
	//zanId是对于主ID
	private Integer zanId;
	private Integer zhId;
	private Integer zhUid;
	private Integer zhTime;
	private String ipAddr;
	private Integer mainId;
	private String mainType;

	public Integer getZanId() {
		return zanId;
	}

	public void setZanId(Integer zanId) {
		this.zanId = zanId;
	}

	public String getMainType() {
		return mainType;
	}

	public void setMainType(String mainType) {
		this.mainType = mainType;
	}

	public Integer getZhId() {
		return zhId;
	}

	public void setZhId(Integer zhId) {
		this.zhId = zhId;
	}

	public Integer getZhUid() {
		return zhUid;
	}

	public void setZhUid(Integer zhUid) {
		this.zhUid = zhUid;
	}

	public Integer getZhTime() {
		return zhTime;
	}

	public void setZhTime(Integer zhTime) {
		this.zhTime = zhTime;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public Integer getMainId() {
		return mainId;
	}

	public void setMainId(Integer mainId) {
		this.mainId = mainId;
	}
	
}
