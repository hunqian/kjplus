package com.kjplus.model.inner;

import java.io.Serializable;

//用户对应的role列表
public class SysRoleInnerEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1915968289424175968L;
	private Integer id;
	private String type;
	private String code;
	private String name;
	private String flag;
	private Integer urId;
	private Integer uid;
	//是否定义了和user的对应关系
	private String userRole;

	public SysRoleInnerEbo() {
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

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public Integer getUrId() {
		return urId;
	}

	public void setUrId(Integer urId) {
		this.urId = urId;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	@Override
	public String toString() {
		return "SysRoleInnerEbo{" +
				"id=" + id +
				", type='" + type + '\'' +
				", code='" + code + '\'' +
				", name='" + name + '\'' +
				", flag='" + flag + '\'' +
				", urId=" + urId +
				", userRole='" + userRole + '\'' +
				'}';
	}
}
