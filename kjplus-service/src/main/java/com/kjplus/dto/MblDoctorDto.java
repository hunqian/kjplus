package com.kjplus.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于mobile TODO 签约家庭医生列表
 * 
 * @author songyuebin
 * 
 */
public class MblDoctorDto {
	// 医生code
	private String staffCode;
	// 医生name
	private String staffName;
	// 头像路径
	private String url;
	private String deptCode;
	private String deptName;// 实体部门名
	private String typeName;// 医生类型名

	private String gDeptCode;// 团队code
	private String gDeptName;// 团队名称

	private List<MblDoctorDto> listDoc = new ArrayList<MblDoctorDto>();

	public MblDoctorDto() {
		super();
	}

	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getgDeptCode() {
		return gDeptCode;
	}

	public void setgDeptCode(String gDeptCode) {
		this.gDeptCode = gDeptCode;
	}

	public String getgDeptName() {
		return gDeptName;
	}

	public void setgDeptName(String gDeptName) {
		this.gDeptName = gDeptName;
	}

	public List<MblDoctorDto> getListDoc() {
		return listDoc;
	}

	public void setListDoc(List<MblDoctorDto> listDoc) {
		this.listDoc = listDoc;
	}

}
