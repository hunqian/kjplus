package com.kjplus.model;

import java.io.Serializable;

public class ServiceLogEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2170757310351686667L;
	private Integer id;
	private String code;
	private Integer srvId;
	private Integer providerId;
	private Integer prsnId;
	private String flag;
	private Integer createTime;

	public ServiceLogEbo() {
		super();
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

	public Integer getSrvId() {
		return srvId;
	}

	public void setSrvId(Integer srvId) {
		this.srvId = srvId;
	}

	public Integer getProviderId() {
		return providerId;
	}

	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}

	public Integer getPrsnId() {
		return prsnId;
	}

	public void setPrsnId(Integer prsnId) {
		this.prsnId = prsnId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Integer getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "ServiceLogEbo [id=" + id + ", code=" + code + ", srvId=" + srvId + ", providerId=" + providerId
				+ ", prsnId=" + prsnId + ", flag=" + flag + ", createTime=" + createTime + "]";
	}

}
