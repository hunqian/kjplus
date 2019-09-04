package com.kjplus.eto;

import com.kjplus.constant.Constant;

public class DepartmentEto {

	public static final int CODE_LEN = 8;
	private String code;
	private Integer pid = 0;
	private String name;
	//默认部门为实体团队
	private String deptType = Constant.DEPT_TYPE_REAL;
	private Integer orgId;
	private String memo;
	private String flag = Constant.FLAG_YES;

	
	public DepartmentEto() {
		super();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
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

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
