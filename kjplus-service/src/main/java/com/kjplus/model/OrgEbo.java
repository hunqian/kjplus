package com.kjplus.model;

import java.io.Serializable;
import java.util.Date;

public class OrgEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2954525928356739951L;
	private Integer id;
	private Integer pid;
	private String code;
	private String name;
	private String alias;
	private Integer orgTypeId;
	private Integer prvnId;
	private Integer cityId;
	private Integer areaId;
	private String addr;
	private String headIconurl;
	private String flag;
	private Date createTime;

	public OrgEbo() {
		super();
	}

	public OrgEbo(Integer id, Integer pid, String code, String name, String alias, Integer orgTypeId, Integer prvnId,
			Integer cityId, Integer areaId, String addr, String headIconurl, String flag, Date createTime) {
		super();
		this.id = id;
		this.pid = pid;
		this.code = code;
		this.name = name;
		this.alias = alias;
		this.orgTypeId = orgTypeId;
		this.prvnId = prvnId;
		this.cityId = cityId;
		this.areaId = areaId;
		this.addr = addr;
		this.headIconurl = headIconurl;
		this.flag = flag;
		this.createTime = createTime;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Integer getOrgTypeId() {
		return orgTypeId;
	}

	public void setOrgTypeId(Integer orgTypeId) {
		this.orgTypeId = orgTypeId;
	}

	public Integer getPrvnId() {
		return prvnId;
	}

	public void setPrvnId(Integer prvnId) {
		this.prvnId = prvnId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getHeadIconurl() {
		return headIconurl;
	}

	public void setHeadIconurl(String headIconurl) {
		this.headIconurl = headIconurl;
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

	@Override
	public String toString() {
		return "OrgEbo [id=" + id + ", pid=" + pid + ", code=" + code + ", name=" + name + ", alias=" + alias
				+ ", orgTypeId=" + orgTypeId + ", prvnId=" + prvnId + ", cityId=" + cityId + ", areaId=" + areaId
				+ ", addr=" + addr + ", headIconurl=" + headIconurl + ", flag=" + flag + ", createTime=" + createTime
				+ "]";
	}

}
