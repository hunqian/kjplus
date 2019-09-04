package com.kjplus.model;

import java.io.Serializable;

public class ZanHisEbo implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1827031090429674224L;
	private Integer id;
	private Integer zanId;
	private Integer uid;
	private Integer zanTime;
	private String ipAddr;
	
	public ZanHisEbo() {
		super();
	}

	public ZanHisEbo(Integer id, Integer zanId, Integer uid, Integer zanTime, String ipAddr) {
		super();
		this.id = id;
		this.zanId = zanId;
		this.uid = uid;
		this.zanTime = zanTime;
		this.ipAddr = ipAddr;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getZanId() {
		return zanId;
	}

	public void setZanId(Integer zanId) {
		this.zanId = zanId;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getZanTime() {
		return zanTime;
	}

	public void setZanTime(Integer zanTime) {
		this.zanTime = zanTime;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	@Override
	public String toString() {
		return "ZanHisEbo [id=" + id + ", zanId=" + zanId + ", uid=" + uid + ", zanTime=" + zanTime + ", ipAddr="
				+ ipAddr + "]";
	}
	
}
