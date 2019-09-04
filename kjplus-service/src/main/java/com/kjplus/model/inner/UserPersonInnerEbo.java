package com.kjplus.model.inner;

import java.io.Serializable;
import java.util.Date;

public class UserPersonInnerEbo implements Serializable {

	private static final long serialVersionUID = 1013765161177106477L;
	private Integer uid; // 用户uid
	private String userName;// 用户名称

	private Integer personId;// 居民id
	private String personName;// 居民名称
	private String personCode;// 居民编号
	private String personType;// 居民类型
	private String headIconUrl;// 头像
	private String idNumber;// 身份证号
	private String sex;// 性别
	private Date birthday;// 生日
	// 签约信息
	private Integer srvOrgId;// 服务组织id
	private String srvOrgName;// 服务名称
	private String srvStatus;// 服务状态
	private Integer stafId;// 签约医生id
	private String stafName;// 签约医生名称
	private String srvName;// 签约服务名称
	private String srvAlias;// 签约服务简称
	// 关系
	private Integer relationTypeId;
	private String relationTypeCode;
	private String relationTypeName;

	public UserPersonInnerEbo() {
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getHeadIconUrl() {
		return headIconUrl;
	}

	public void setHeadIconUrl(String headIconUrl) {
		this.headIconUrl = headIconUrl;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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

	public String getSrvAlias() {
		return srvAlias;
	}

	public void setSrvAlias(String srvAlias) {
		this.srvAlias = srvAlias;
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

	@Override
	public String toString() {
		return "UserPersonInnerEbo [uid=" + uid + ", userName=" + userName + ", personId=" + personId + ", personName="
				+ personName + ", personCode=" + personCode + ", personType=" + personType + ", srvOrgId=" + srvOrgId
				+ ", srvOrgName=" + srvOrgName + ", srvStatus=" + srvStatus + ", stafId=" + stafId + ", stafName="
				+ stafName + ", srvName=" + srvName + ", relationTypeId=" + relationTypeId + ", relationTypeCode="
				+ relationTypeCode + ", relationTypeName=" + relationTypeName + "]";
	}

}
