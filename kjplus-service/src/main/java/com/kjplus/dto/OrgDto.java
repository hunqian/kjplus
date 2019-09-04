package com.kjplus.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrgDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2954525928356739951L;
	private Integer id;
	private String code;
	private String name;
	private String pname;
	private String alias;
	private Integer orgTypeId;
	private String orgTypeName;
	private Integer prvnId;
	private String prvnName;
	private Integer cityId;
	private String cityName;
	private Integer areaId;
	private String areaName;
	private String addr;
	private String headIconurl;
	private String flag;
	private Date createTime;
	private List<OrgDto> subs = new ArrayList<OrgDto>();

	public OrgDto() {
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

	public String getOrgTypeName() {
		return orgTypeName;
	}

	public void setOrgTypeName(String orgTypeName) {
		this.orgTypeName = orgTypeName;
	}

	public String getPrvnName() {
		return prvnName;
	}

	public void setPrvnName(String prvnName) {
		this.prvnName = prvnName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public List<OrgDto> getSubs() {
		return subs;
	}

	public void setSubs(List<OrgDto> subs) {
		this.subs = subs;
	}

}
