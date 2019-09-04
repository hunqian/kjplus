package com.kjplus.eto;


import java.util.Date;

import org.ybk.basic.util.DateUtil;

import com.kjplus.annotation.Validation;
import com.kjplus.constant.Constant;

public class PersonEto {

	public static final int CODE_LEN=16;
	@Validation
	private String name;
	private String code;
	private String gender;
	private String idNumber;
	private Date birthday;
	private String mobileNum;
	private String personNum;
	private String familyAddr;
	private String flag = Constant.FLAG_YES;
	private String headIconUrl;
	private Integer orgId;
	private Date createTime = DateUtil.newTime();
	
	public PersonEto() {
		super();
	}

	public PersonEto(String name, String code, String gender, String idNumber, Date birthday, String mobileNum,
			String personNum, String familyAddr, String flag, String headIconUrl, Integer orgId, Date createTime) {
		super();
		this.name = name;
		this.code = code;
		this.gender = gender;
		this.idNumber = idNumber;
		this.birthday = birthday;
		this.mobileNum = mobileNum;
		this.personNum = personNum;
		this.familyAddr = familyAddr;
		this.flag = flag;
		this.headIconUrl = headIconUrl;
		this.orgId = orgId;
		this.createTime = createTime;
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

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "PersonEto [name=" + name + ", code=" + code + ", gender=" + gender + ", idNumber=" + idNumber
				+ ", birthday=" + birthday + ", mobileNum=" + mobileNum + ", personNum=" + personNum + ", familyAddr="
				+ familyAddr + ", flag=" + flag + ", headIconUrl=" + headIconUrl + ", orgId=" + orgId + ", createTime="
				+ createTime + "]";
	}
	

}
