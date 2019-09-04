package com.kjplus.eto;

import java.util.Date;

import org.ybk.basic.util.DateUtil;

import com.kjplus.constant.Constant;

//个人建档信息
public class DocInfoEto {

	public static final int MAX_PRSN_CODE_LEN = 8;
	public static final int MAX_CODE_LEN = 12;

	private String prsnCode;
	private String prsnName;
	private String prsnSex;
	private String idNumber;
	// private Integer uid;
	// private String userNickName;

	private String birthday;
	private String mobileNum;
	private String personNum;
	private String workUnit;
	private String bloodVl;
	private String nationVl;
	private String contactName;
	private String contactNum;
	private String familyAddr;
	private String houseAddr;
	private Integer orgId;
	private Integer creatorId;
	private String createDay = DateUtil.currDay();
	private String headIconUrl;
	private Date lastUpdateTime;

	private String status = Constant.FLAG_YES;// 默认已审核

	public DocInfoEto() {
	}

	public String getPrsnCode() {
		return prsnCode;
	}

	public void setPrsnCode(String prsnCode) {
		this.prsnCode = prsnCode;
	}

	public String getPrsnName() {
		return prsnName;
	}

	public void setPrsnName(String prsnName) {
		this.prsnName = prsnName;
	}

	public String getPrsnSex() {
		return prsnSex;
	}

	public void setPrsnSex(String prsnSex) {
		this.prsnSex = prsnSex;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
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

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	public String getBloodVl() {
		return bloodVl;
	}

	public void setBloodVl(String bloodVl) {
		this.bloodVl = bloodVl;
	}

	public String getNationVl() {
		return nationVl;
	}

	public void setNationVl(String nationVl) {
		this.nationVl = nationVl;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactNum() {
		return contactNum;
	}

	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}

	public String getFamilyAddr() {
		return familyAddr;
	}

	public void setFamilyAddr(String familyAddr) {
		this.familyAddr = familyAddr;
	}

	public String getHouseAddr() {
		return houseAddr;
	}

	public void setHouseAddr(String houseAddr) {
		this.houseAddr = houseAddr;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getCreateDay() {
		return createDay;
	}

	public void setCreateDay(String createDay) {
		this.createDay = createDay;
	}

	public String getHeadIconUrl() {
		return headIconUrl;
	}

	public void setHeadIconUrl(String headIconUrl) {
		this.headIconUrl = headIconUrl;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}