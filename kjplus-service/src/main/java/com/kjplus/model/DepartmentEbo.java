package com.kjplus.model;

import java.io.Serializable;

public class DepartmentEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7140484513627129605L;
	private Integer id;
	private Integer pid;
	private String code;
	private String name;
	private String deptType;
	private String flag;
	private Integer orgId;
	private String memo;

	public DepartmentEbo() {
		super();
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

	public String getDeptType() {
		return deptType;
	}

	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public String toString() {
		return "DepartmentEbo [id=" + id + ", pid=" + pid + ", cdoe=" + code + ", name=" + name + ", deptType="
				+ deptType + ", flag=" + flag + ", orgId=" + orgId + ", memo=" + memo + "]";
	}

}
