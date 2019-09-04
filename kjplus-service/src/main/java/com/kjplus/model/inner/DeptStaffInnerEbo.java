package com.kjplus.model.inner;

import java.io.Serializable;
import java.util.Date;

public class DeptStaffInnerEbo implements Serializable {

	private static final long serialVersionUID = -5046986383710691868L;

	// 实体部门
	private Integer deptId;
	private String deptName;
	private String deptCode;
	private String deptType;
	private String deptMemo;

	// 团体
	private Integer gDeptId;
	private String gDeptName;
	private String gDeptCode;
	private String gDeptType;
	private String gDeptMemo;

	private Integer stafId;
	private String stafCode;
	private String stafStafCode;
	private String stafName;
	private String mobileNum;
	private String stafSex;
	private String birthday;
	private String idCard;
	private String flag;
	private String status;
	private Date regDate;
	private Date createTime;
	private Integer orgId;
	private String orgName;
	private Integer stafTypeId;
	private String stafTypeVl;
	private String headIconUrl;
	private String stafMemo;

	public DeptStaffInnerEbo() {
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

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptType() {
		return deptType;
	}

	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}

	public String getDeptMemo() {
		return deptMemo;
	}

	public void setDeptMemo(String deptMemo) {
		this.deptMemo = deptMemo;
	}

	public Integer getgDeptId() {
		return gDeptId;
	}

	public void setgDeptId(Integer gDeptId) {
		this.gDeptId = gDeptId;
	}

	public String getgDeptName() {
		return gDeptName;
	}

	public void setgDeptName(String gDeptName) {
		this.gDeptName = gDeptName;
	}

	public String getgDeptCode() {
		return gDeptCode;
	}

	public void setgDeptCode(String gDeptCode) {
		this.gDeptCode = gDeptCode;
	}

	public String getgDeptType() {
		return gDeptType;
	}

	public void setgDeptType(String gDeptType) {
		this.gDeptType = gDeptType;
	}

	public String getgDeptMemo() {
		return gDeptMemo;
	}

	public void setgDeptMemo(String gDeptMemo) {
		this.gDeptMemo = gDeptMemo;
	}

	public Integer getStafId() {
		return stafId;
	}

	public void setStafId(Integer stafId) {
		this.stafId = stafId;
	}

	public String getStafCode() {
		return stafCode;
	}

	public void setStafCode(String stafCode) {
		this.stafCode = stafCode;
	}

	public String getStafStafCode() {
		return stafStafCode;
	}

	public void setStafStafCode(String stafStafCode) {
		this.stafStafCode = stafStafCode;
	}

	public String getStafName() {
		return stafName;
	}

	public void setStafName(String stafName) {
		this.stafName = stafName;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public String getStafSex() {
		return stafSex;
	}

	public void setStafSex(String stafSex) {
		this.stafSex = stafSex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
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

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	public Integer getStafTypeId() {
		return stafTypeId;
	}

	public void setStafTypeId(Integer stafTypeId) {
		this.stafTypeId = stafTypeId;
	}

	public String getStafTypeVl() {
		return stafTypeVl;
	}

	public void setStafTypeVl(String stafTypeVl) {
		this.stafTypeVl = stafTypeVl;
	}

	public String getHeadIconUrl() {
		return headIconUrl;
	}

	public void setHeadIconUrl(String headIconUrl) {
		this.headIconUrl = headIconUrl;
	}

	public String getStafMemo() {
		return stafMemo;
	}

	public void setStafMemo(String stafMemo) {
		this.stafMemo = stafMemo;
	}

	@Override
	public String toString() {
		return "DeptStaffInnerEbo [deptId=" + deptId + ", deptName=" + deptName + ", deptCode=" + deptCode + ", deptType=" + deptType + ", deptMemo="
				+ deptMemo + ", gDeptId=" + gDeptId + ", gDeptName=" + gDeptName + ", gDeptCode=" + gDeptCode + ", gDeptType=" + gDeptType + ", gDeptMemo="
				+ gDeptMemo + ", stafId=" + stafId + ", stafCode=" + stafCode + ", stafStafCode=" + stafStafCode + ", stafName=" + stafName + ", mobileNum="
				+ mobileNum + ", stafSex=" + stafSex + ", birthday=" + birthday + ", idCard=" + idCard + ", flag=" + flag + ", status=" + status + ", regDate="
				+ regDate + ", createTime=" + createTime + ", orgId=" + orgId + ", orgName=" + orgName + ", stafTypeId=" + stafTypeId + ", stafTypeVl="
				+ stafTypeVl + ", headIconUrl=" + headIconUrl + ", stafMemo=" + stafMemo + "]";
	}

}
