package com.kjplus.model;

import java.io.Serializable;

public class InfoReadlogEbo implements Serializable {

	private static final long serialVersionUID = 3897603867705156798L;
	private Integer id;
	private Integer uid;
	private Integer mainId;
	private String mainType;
	private Integer readStartTime;
	private Integer readTime;
	private String flag;

	public InfoReadlogEbo() {
	}

	public InfoReadlogEbo(Integer id, Integer uid, Integer mainId, String mainType, Integer readStartTime,
			Integer readTime, String flag) {
		this.id = id;
		this.uid = uid;
		this.mainId = mainId;
		this.mainType = mainType;
		this.readStartTime = readStartTime;
		this.readTime = readTime;
		this.flag = flag;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
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

	public Integer getReadStartTime() {
		return readStartTime;
	}

	public void setReadStartTime(Integer readStartTime) {
		this.readStartTime = readStartTime;
	}

	public Integer getReadTime() {
		return readTime;
	}

	public void setReadTime(Integer readTime) {
		this.readTime = readTime;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "InfoReadlogEbo{" + "id=" + id + ", uid=" + uid + ", mainId=" + mainId + ", mainType=" + mainType
				+ ", readStartTime=" + readStartTime + ", readTime=" + readTime + ", flag='" + flag + '\'' + '}';
	}
}
