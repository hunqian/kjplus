package com.kjplus.model;

import java.io.Serializable;

public class StaffDeptEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7495853038046745044L;
	private Integer id;
	private Integer staffId;
	private Integer deptId;

	public StaffDeptEbo() {
		super();
	}

	public StaffDeptEbo(Integer id, Integer staffId, Integer deptId) {
		super();
		this.id = id;
		this.staffId = staffId;
		this.deptId = deptId;
	}

	public StaffDeptEbo(Integer staffId, Integer deptId) {
		super();
		this.staffId = staffId;
		this.deptId = deptId;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}