package com.kjplus.dto;

import java.io.Serializable;
import java.util.Date;

public class PersonServiceDto implements Serializable {

	private static final long serialVersionUID = 2845846755855579537L;

	private Integer personId;
	private String personName;
	private String personCode;
	private String personType;
	private String headIconUrl;// 头像
	private String idNumber;// 身份证号
	private String sex;// 性别
	private Date birthday;// 生日
	// 签约信息
	private Integer srvOrgId;
	private String srvOrgName;
	private String srvStatus;
	private Integer stafId;
	private String stafName;
	private String srvName;
	private String srvAlias;// 签约服务简称

	// 关系
	private Integer relationTypeId;
	private String relationTypeCode;
	private String relationTypeName;

	public PersonServiceDto() {
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPersonType() {
		return personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}

	public Integer getSrvOrgId() {
		return srvOrgId;
	}

	public void setSrvOrgId(Integer srvOrgId) {
		this.srvOrgId = srvOrgId;
	}

	public String getSrvOrgName() {
		return srvOrgName;
	}

	public void setSrvOrgName(String srvOrgName) {
		this.srvOrgName = srvOrgName;
	}

	public String getSrvStatus() {
		return srvStatus;
	}

	public void setSrvStatus(String srvStatus) {
		this.srvStatus = srvStatus;
	}

	public Integer getStafId() {
		return stafId;
	}

	public void setStafId(Integer stafId) {
		this.stafId = stafId;
	}

	public String getStafName() {
		return stafName;
	}

	public void setStafName(String stafName) {
		this.stafName = stafName;
	}

	public String getSrvName() {
		return srvName;
	}

	public void setSrvName(String srvName) {
		this.srvName = srvName;
	}

	public String getPersonCode() {
		return personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	public Integer getRelationTypeId() {
		return relationTypeId;
	}

	public void setRelationTypeId(Integer relationTypeId) {
		this.relationTypeId = relationTypeId;
	}

	public String getRelationTypeCode() {
		return relationTypeCode;
	}

	public void setRelationTypeCode(String relationTypeCode) {
		this.relationTypeCode = relationTypeCode;
	}

	public String getRelationTypeName() {
		return relationTypeName;
	}

	public void setRelationTypeName(String relationTypeName) {
		this.relationTypeName = relationTypeName;
	}

	public String getHeadIconUrl() {
		return headIconUrl;
	}

	public void setHeadIconUrl(String headIconUrl) {
		this.headIconUrl = headIconUrl;
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getSrvAlias() {
		return srvAlias;
	}

	public void setSrvAlias(String srvAlias) {
		this.srvAlias = srvAlias;
	}

	@Override
	public String toString() {
		return "PersonServiceDto [personId=" + personId + ", personName=" + personName + ", personCode=" + personCode
				+ ", personType=" + personType + ", headIconUrl=" + headIconUrl + ", idNumber=" + idNumber + ", sex="
				+ sex + ", birthday=" + birthday + ", srvOrgId=" + srvOrgId + ", srvOrgName=" + srvOrgName
				+ ", srvStatus=" + srvStatus + ", stafId=" + stafId + ", stafName=" + stafName + ", srvName=" + srvName
				+ ", srvAlias=" + srvAlias + ", relationTypeId=" + relationTypeId + ", relationTypeCode="
				+ relationTypeCode + ", relationTypeName=" + relationTypeName + "]";
	}

}
