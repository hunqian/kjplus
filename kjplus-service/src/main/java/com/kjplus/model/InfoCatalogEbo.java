package com.kjplus.model;

import java.io.Serializable;

public class InfoCatalogEbo implements Serializable {

	private static final long serialVersionUID = 3760011590443878549L;

	private Integer id;
	private Integer pid;
	private String code;
	private String name;
	private Integer infoTypeId;
	private Integer orgId;
	private String flag;

	public InfoCatalogEbo() {
	}

	public InfoCatalogEbo(Integer id, String code, String name, Integer infoTypeId, Integer orgId, String flag) {
		this.id = id;
		this.code = code;
		this.name = name;
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
	
}
