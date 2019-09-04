package com.kjplus.model;

import java.io.Serializable;

public class SysRoleFuncEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7425650500375269655L;
	private Integer id;
	private Integer roleId;
	private Integer funcId;

	public SysRoleFuncEbo() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getFuncId() {
		return funcId;
	}

	public void setFuncId(Integer funcId) {
		this.funcId = funcId;
	}

	@Override
	public String toString() {
		return "SysRoleFuncEbo [id=" + id + ", roleId=" + roleId + ", funcId=" + funcId + "]";
	}

}
