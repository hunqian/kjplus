package com.kjplus.model.inner;

import java.io.Serializable;

public class InfoCatalogInnerEbo implements Serializable {

	private static final long serialVersionUID = 3760011590443878549L;

	private Integer id;
	private Integer pid;
	private String code;
	private String name;
	private String pname;
	private Integer infoTypeId;
	private String infoTypeName;
	private Integer orgId;
	private String orgName;
	private String flag;

	public InfoCatalogInnerEbo() {
		super();
	}

	public InfoCatalogInnerEbo(Integer id, Integer pid, String code, String name, String pname, Integer infoTypeId,
			String infoTypeName, Integer orgId, String orgName, String flag) {
		super();
		this.id = id;
		this.pid = pid;
		this.code = code;
		this.name = name;
		this.pname = pname;
		this.infoTypeId = infoTypeId;
		this.infoTypeName = infoTypeName;
		this.orgId = orgId;
		this.orgName = orgName;
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

	public String getInfoTypeName() {
		return infoTypeName;
	}

	public void setInfoTypeName(String infoTypeName) {
		this.infoTypeName = infoTypeName;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
