package com.kjplus.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2933509162725726422L;
	private Integer id;
	private String code;
	private String name;
	private Integer pid;
	private String pName;
	private String deptType;
	private String flag;
	private Integer orgId;
	private String orgName;
	private String memo;
	// 子部门
	public List<DepartmentDto> subs = new ArrayList<DepartmentDto>();
	public List<StaffDto> staffs = new ArrayList<StaffDto>();

	public DepartmentDto() {
		super();
	}

	public DepartmentDto(Integer id, String code, String name, Integer pid, String pName, String deptType, String flag, Integer orgId, String orgName, String memo, List<DepartmentDto> subs,
			List<StaffDto> staffs) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.pid = pid;
		this.pName = pName;
		this.deptType = deptType;
		this.flag = flag;
		this.orgId = orgId;
		this.orgName = orgName;
		this.memo = memo;
		this.subs = subs;
		this.staffs = staffs;
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

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
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

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public List<DepartmentDto> getSubs() {
		return subs;
	}

	public void setSubs(List<DepartmentDto> subs) {
		this.subs = subs;
	}

	public List<StaffDto> getStaffs() {
		return staffs;
	}

	public void setStaffs(List<StaffDto> staffs) {
		this.staffs = staffs;
	}

}
