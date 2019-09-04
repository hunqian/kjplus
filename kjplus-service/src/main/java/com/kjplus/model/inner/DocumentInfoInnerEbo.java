package com.kjplus.model.inner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kjplus.dto.ServAsgnDto;
import com.kjplus.dto.TagDto;

//个人建档信息
public class DocumentInfoInnerEbo implements Serializable {

	private static final long serialVersionUID = -4653926549209701772L;

	private Integer prsnId;
	private String code;
	private String prsnCode;
	private String prsnName;
	private String prsnSex;
	private String idNumber;
	private Integer uid;
	private String unickName;

	private Date birthday;
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
	private String orgName;
	private String creatorName;
	// private String contactDoctor;
	private Integer creatorId;
	// private String doctorName;
	private String status;
	private String flag;
	private Date createTime;
	private Date createDay;
	private String headIconUrl;

	/*
	 * // 签约记录 private Integer staffId; private Integer srvId; private String
	 * srvName; private String srvAlias; private String srvMemo;
	 */

	private List<ServAsgnDto> srvAssigns = new ArrayList<ServAsgnDto>();
	private List<TagDto> tags = new ArrayList<TagDto>();

	public DocumentInfoInnerEbo() {
	}

	public Integer getPrsnId() {
		return prsnId;
	}

	public void setPrsnId(Integer prsnId) {
		this.prsnId = prsnId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateDay() {
		return createDay;
	}

	public void setCreateDay(Date createDay) {
		this.createDay = createDay;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUnickName() {
		return unickName;
	}

	public void setUnickName(String unickName) {
		this.unickName = unickName;
	}

	public List<TagDto> getTags() {
		return tags;
	}

	public void setTags(List<TagDto> tags) {
		this.tags = tags;
	}

	public String getHeadIconUrl() {
		return headIconUrl;
	}

	public void setHeadIconUrl(String headIconUrl) {
		this.headIconUrl = headIconUrl;
	}

	public List<ServAsgnDto> getSrvAssigns() {
		return srvAssigns;
	}

	public void setSrvAssigns(List<ServAsgnDto> srvAssigns) {
		this.srvAssigns = srvAssigns;
	}

	@Override
	public String toString() {
		return "DocumentInfoInnerEbo [prsnId=" + prsnId + ", code=" + code
				+ ", prsnCode=" + prsnCode + ", prsnName=" + prsnName
				+ ", prsnSex=" + prsnSex + ", idNumber=" + idNumber + ", uid="
				+ uid + ", unickName=" + unickName + ", birthday=" + birthday
				+ ", mobileNum=" + mobileNum + ", personNum=" + personNum
				+ ", workUnit=" + workUnit + ", bloodVl=" + bloodVl
				+ ", nationVl=" + nationVl + ", contactName=" + contactName
				+ ", contactNum=" + contactNum + ", familyAddr=" + familyAddr
				+ ", houseAddr=" + houseAddr + ", orgId=" + orgId
				+ ", orgName=" + orgName + ", creatorName=" + creatorName
				+ ", creatorId=" + creatorId + ", status=" + status + ", flag="
				+ flag + ", createTime=" + createTime + ", createDay="
				+ createDay + ", headIconUrl=" + headIconUrl + ", srvAssigns="
				+ srvAssigns + ", tags=" + tags + "]";
	}

}