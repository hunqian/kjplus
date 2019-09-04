package com.kjplus.model;

import java.io.Serializable;

public class SysRoleEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1915968289424175968L;
	private Integer id;
	private String type;
	private String code;
	private String name;
	private String flag;

	public SysRoleEbo() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "SysRoleEbo [id=" + id + ", type=" + type + ", code=" + code + ", name=" + name + ", flag=" + flag + "]";
	}

}
