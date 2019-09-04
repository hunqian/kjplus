package com.kjplus.dto;

public class DeptSimpleDto {

	private Integer id;
	private String code;
	private String name;
	private String deptType;
	private String memo;

	public DeptSimpleDto() {
		super();
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

	public String getDeptType() {
		return deptType;
	}

	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public String toString() {
		return "DeptSimpleDto [code=" + code + ", name=" + name + ", deptType=" + deptType + ", memo=" + memo + "]";
	}

}
