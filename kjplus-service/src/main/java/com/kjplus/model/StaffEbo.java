package com.kjplus.model;

import java.io.Serializable;
import java.util.Date;

public class StaffEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7495853038046745044L;
	private Integer id;
	private String code;
	private String staffCode;
	private String name;
	private String sex;
	private String birthday;
	private String mobileNum;
	private Integer typeId;
	private Integer deptId;
	private Integer orgId;
	private String idCard;
	private Date regDate;
	private String flag;
	private String status;
	private String headIconUrl;
	private Date createTime;
	private String memo;

	public StaffEbo() {
		super();
	}

	public StaffEbo(Integer id, String code, String staffCode, String name, String sex, String birthday,
			Integer typeId, Integer deptId, Integer orgId, String idCard, Date regDate, String flag, String status,
			String headIconUrl, Date createTime, String memo) {
		super();
		this.id = id;
		this.code = code;
		this.staffCode = staffCode;
		this.name = name;
		this.sex = sex;
		this.birthday = birthday;
		this.typeId = typeId;
		this.deptId = deptId;
		this.orgId = orgId;
		this.idCard = idCard;
		this.regDate = regDate;
		this.flag = flag;
		this.status = status;
		this.headIconUrl = headIconUrl;
		this.createTime = createTime;
		this.memo = memo;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
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
	
	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public String toString() {
		return "StaffEbo [id=" + id + ", code=" + code + ", staffCode="
				+ staffCode + ", name=" + name + ", sex=" + sex + ", birthday="
				+ birthday + ", mobileNum=" + mobileNum + ", typeId=" + typeId + ", deptId=" + deptId
				+ ", orgId=" + orgId + ", idCard=" + idCard + ", regDate="
				+ regDate + ", flag=" + flag + ", status=" + status
				+ ", headIconUrl=" + headIconUrl + ", createTime=" + createTime
				+ ", memo=" + memo + "]";
	}

	

}