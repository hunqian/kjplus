package com.kjplus.dto;

/**
 * 用于mobile 我的家庭成员显示 ，家庭医生
 * 
 * @author songyuebin
 * 
 */
public class MblMyFamilyDto {
	// 档案用户Code
	private String prsnCode;
	// 头像路径
	private String url;
	private String name;// 用户名
	private String relationTypeName;// 与用户关系
	private Integer age;// 年龄
	private String staffName;// 签约医生姓名
	private String srvPackageName;// 签约服务包名
	private String srvPackageAlias;// 签约服务包简称
	private String idNumber;// 身份证号
	private String sex;// 性别
	// 用于家庭医生显示
	private String srvStatus;// 签约状态

	public MblMyFamilyDto() {
		super();
	}

	public String getPrsnCode() {
		return prsnCode;
	}

	public void setPrsnCode(String prsnCode) {
		this.prsnCode = prsnCode;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRelationTypeName() {
		return relationTypeName;
	}

	public void setRelationTypeName(String relationTypeName) {
		this.relationTypeName = relationTypeName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getSrvPackageName() {
		return srvPackageName;
	}

	public void setSrvPackageName(String srvPackageName) {
		this.srvPackageName = srvPackageName;
	}

	public String getSrvPackageAlias() {
		return srvPackageAlias;
	}

	public void setSrvPackageAlias(String srvPackageAlias) {
		this.srvPackageAlias = srvPackageAlias;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSrvStatus() {
		return srvStatus;
	}

	public void setSrvStatus(String srvStatus) {
		this.srvStatus = srvStatus;
	}

}
