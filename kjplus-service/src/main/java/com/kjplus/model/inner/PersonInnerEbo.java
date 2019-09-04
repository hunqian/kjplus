package com.kjplus.model.inner;

import java.io.Serializable;
import java.util.Date;

public class PersonInnerEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3814884491581602662L;
	
	//p.id prsn_id,p.name name,p.code code,p.gender gender,p.id_number id_number
	private Integer prsnId;
	private String name;
	private String code;
	private String gender;
	private String idNumber;

	//,o.prvn_id,pr.local_name prvn_name,o.city_id,cr.local_name city_name,o.id org_id,o.name org_name
	private Integer cityId;
	private String cityName;
	private Integer prvnId;
	private String prvnName;
	private Integer orgId;
	private String orgName;
	//,p.birthday birthday,p.mobile_num,p.person_num,p.family_addr
	private Date birthday;
	private String mobileNum;
	private String personNum;
	private String familyAddr;
	//,p.flag,p.head_icon_url,p.create_time
	private String flag;
	private String headIconUrl;
	private Date createTime;	

	public PersonInnerEbo() {
		super();
	}

	public Integer getPrsnId() {
		return prsnId;
	}

	public void setPrsnId(Integer prsnId) {
		this.prsnId = prsnId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getPrvnId() {
		return prvnId;
	}

	public void setPrvnId(Integer prvnId) {
		this.prvnId = prvnId;
	}

	public String getPrvnName() {
		return prvnName;
	}

	public void setPrvnName(String prvnName) {
		this.prvnName = prvnName;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public String getPersonNum() {
		return personNum;
	}

	public void setPersonNum(String personNum) {
		this.personNum = personNum;
	}

	public String getFamilyAddr() {
		return familyAddr;
	}

	public void setFamilyAddr(String familyAddr) {
		this.familyAddr = familyAddr;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getHeadIconUrl() {
		return headIconUrl;
	}

	public void setHeadIconUrl(String headIconUrl) {
		this.headIconUrl = headIconUrl;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
