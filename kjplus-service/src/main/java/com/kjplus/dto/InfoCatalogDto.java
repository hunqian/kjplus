package com.kjplus.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.ybk.basic.util.Util;

public class InfoCatalogDto implements Serializable {

	private static final long serialVersionUID = 3760011590443878549L;

	private Integer id;
	private Integer pid;
	private String code;
	private String name;
	private String pname;
	private Integer infoTypeId;
	private String infoTypeName = "";
	private Integer orgId = 0;
	private String orgName = "";
	private String flag;
	private List<InfoCatalogDto> subs = new ArrayList<InfoCatalogDto>();

	public InfoCatalogDto() {
	}

	public InfoCatalogDto(Integer id, Integer pid, String code, String name,String pname, Integer infoTypeId, Integer orgId, String flag) {
		this.id = id;
		this.pid = pid;
		this.code = code;
		this.name = name;
		this.pname = pname;
		this.infoTypeId = infoTypeId;
		this.orgId = orgId;
		this.flag = flag;
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

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Integer getInfoTypeId() {
		return infoTypeId;
	}

	public void setInfoTypeId(Integer infoTypeId) {
		this.infoTypeId = infoTypeId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getInfoTypeName() {
		return infoTypeName;
	}

	public void setInfoTypeName(String infoTypeName) {
		this.infoTypeName = infoTypeName;
	}

	public String getOrgName() {
		if(Util.isNull(orgName))
			return "平台";
		else
			return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public List<InfoCatalogDto> getSubs() {
		return subs;
	}

	public void setSubs(List<InfoCatalogDto> subs) {
		this.subs = subs;
	}
}
