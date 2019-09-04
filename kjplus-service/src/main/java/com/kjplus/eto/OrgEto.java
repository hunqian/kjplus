package com.kjplus.eto;

import java.util.Date;

import org.ybk.basic.util.DateUtil;

import com.kjplus.constant.Constant;

public class OrgEto {
	
	public static final int CODE_LEN = 8;
	private Integer pid = 0;
	private String code;
	private String name;
	private String alias;
	private Integer orgTypeId = 0;
	private Integer prvnId = 0;
	private Integer cityId = 0;
	private Integer areaId = 0;
	private String addr;
	private String headIconurl;
	private String flag = Constant.FLAG_YES;
	private Date createTime = DateUtil.newTime();

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
}
