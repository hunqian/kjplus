package com.kjplus.model.inner;

import java.io.Serializable;
import java.util.Date;


public class StaffInnerEbo  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2737634983035625344L;
	private Integer id;
	private String code;
	private String staffCode;
	private String name;
	private String sex;
	private String birthday;
	private String mobileNum;
	private Integer typeId;
	private String typeName;
	private Integer deptId;
	private String deptName;
	private Integer orgId;
	private String orgName;
	private String idCard;
	private String StaffDeptName;
	private Date regDate;
	private String flag;
	private String status;
	private String headIconUrl;
	private Date createTime;
	private String stafMemo;
	private Integer tagId;
	private Integer mainId;
	private String mainType;
	private Integer refValId;
	private String refValName;
	
	public StaffInnerEbo() {
		super();
	}

	public StaffInnerEbo(Integer id, String code, String staffCode, String name, String sex, String birthday,
			Integer typeId, String typeName, Integer deptId, String deptName, Integer orgId, String orgName,
			String idCard, String staffDeptName, Date regDate, String flag, String status, String headIconUrl,
			Date createTime, String stafMemo, Integer tagId, Integer mainId, String mainType, Integer refValId,
			String refValName) {
		super();
		this.id = id;
		this.code = code;
		this.staffCode = staffCode;
		this.name = name;
		this.sex = sex;
		this.birthday = birthday;
		this.typeId = typeId;
		this.typeName = typeName;
		this.deptId = deptId;
		this.deptName = deptName;
		this.orgId = orgId;
		this.orgName = orgName;
		this.idCard = idCard;
		StaffDeptName = staffDeptName;
		this.regDate = regDate;
		this.flag = flag;
		this.status = status;
		this.headIconUrl = headIconUrl;
		this.createTime = createTime;
		this.stafMemo = stafMemo;
		this.tagId = tagId;
		this.mainId = mainId;
		this.mainType = mainType;
		this.refValId = refValId;
		this.refValName = refValName;
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
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
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

	public String getStaffDeptName() {
		return StaffDeptName;
	}

	public void setStaffDeptName(String staffDeptName) {
		StaffDeptName = staffDeptName;
	}

	public String getStafMemo() {
		return stafMemo;
	}

	public void setStafMemo(String stafMemo) {
		this.stafMemo = stafMemo;
	}

	public Integer getTagId() {
		return tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}

	public Integer getMainId() {
		return mainId;
	}

	public void setMainId(Integer mainId) {
		this.mainId = mainId;
	}

	public String getMainType() {
		return mainType;
	}

	public void setMainType(String mainType) {
		this.mainType = mainType;
	}

	public Integer getRefValId() {
		return refValId;
	}

	public void setRefValId(Integer refValId) {
		this.refValId = refValId;
	}

	public String getRefValName() {
		return refValName;
	}

	public void setRefValName(String refValName) {
		this.refValName = refValName;
	}

	@Override
	public String toString() {
		return "StaffInnerEbo [id=" + id + ", code=" + code + ", staffCode=" + staffCode + ", name=" + name + ", sex="
				+ sex + ", birthday=" + birthday + ", mobileNum=" + mobileNum +", typeId=" + typeId + ", typeName=" + typeName + ", deptId="
				+ deptId + ", deptName=" + deptName + ", orgId=" + orgId + ", orgName=" + orgName + ", idCard="
				+ idCard + ", StaffDeptName=" + StaffDeptName + ", regDate=" + regDate + ", flag=" + flag + ", status="
				+ status + ", headIconUrl=" + headIconUrl + ", createTime=" + createTime + ", stafMemo=" + stafMemo
				+ ", tagId=" + tagId + ", mainId=" + mainId + ", mainType=" + mainType + ", refValId=" + refValId
				+ ", refValName=" + refValName + "]";
	}

}
