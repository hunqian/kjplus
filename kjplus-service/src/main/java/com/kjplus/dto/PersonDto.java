package com.kjplus.dto;

import java.io.Serializable;
import java.util.*;

public class PersonDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7763955788187180012L;
	private Integer prsnId;
	private String name;
	private String code;
	private String gender;
	private String idNumber;
	//date转换
	private String birthday;
	private String mobileNum;
	private String personNum;
	private String familyAddr;
	private String flag;
	private String headIconUrl;
	private Integer orgId;
	private String orgName;
	private Integer prvnId;
	private String prvnName;
	private Integer cityId;
	private String cityName;
	//date转换
	private String createTime;
	private List<TagDto> tags = new ArrayList<TagDto>();

	public PersonDto() {
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

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
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

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
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

	public List<TagDto> getTags() {
		return tags;
	}

	public void setTags(List<TagDto> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "PersonDto [prsnId=" + prsnId + ", name=" + name + ", code=" + code + ", gender=" + gender
				+ ", idNumber=" + idNumber + ", birthday=" + birthday + ", mobileNum=" + mobileNum + ", personNum="
				+ personNum + ", familyAddr=" + familyAddr + ", flag=" + flag + ", headIconUrl=" + headIconUrl
				+ ", orgId=" + orgId + ", orgName=" + orgName + ", prvnId=" + prvnId + ", prvnName=" + prvnName
				+ ", cityId=" + cityId + ", cityName=" + cityName + ", createTime=" + createTime + ", tags=" + tags
				+ "]";
	}

}
