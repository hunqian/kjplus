package com.kjplus.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StaffDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2737634983035625344L;
	private Integer id;
	private String code;
	private Integer stafId;
	private String stafCode;
	private String stafStafCode;
	private String stafName;
	private String stafSex;
	private String birthday;
	private String mobileNum;
	private Integer typeId;
	private String typeName;
	private Integer deptId;// 所属部门id
	private String deptName;// 所属部门
	private Integer orgId;
	private String orgName;
	private String idCard;
	private Date regDate;
	private String flag;
	private String status;
	private String headIconUrl;
	private String memo;
	private Date createTime;
	// 所属团队
	private List<DeptSimpleDto> depts = new ArrayList<DeptSimpleDto>();
	// 医生个人标签
	private List<TagDto> tags = new ArrayList<TagDto>();

	public StaffDto() {
		super();
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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<DeptSimpleDto> getDepts() {
		return depts;
	}

	public void setDepts(List<DeptSimpleDto> depts) {
		this.depts = depts;
	}

	public List<TagDto> getTags() {
		return tags;
	}

	public void setTags(List<TagDto> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "StaffDto [id=" + id + ", code=" + code + ", stafId=" + stafId + ", stafCode=" + stafCode + ", stafStafCode=" + stafStafCode + ", stafName="
				+ stafName + ", stafSex=" + stafSex + ", birthday=" + birthday + ", mobileNum=" + mobileNum + ", typeId=" + typeId + ", typeName=" + typeName
				+ ", deptId=" + deptId + ", deptName=" + deptName + ", orgId=" + orgId + ", orgName=" + orgName + ", idCard=" + idCard + ", regDate=" + regDate
				+ ", flag=" + flag + ", status=" + status + ", headIconUrl=" + headIconUrl + ", memo=" + memo + ", createTime=" + createTime + ", depts="
				+ depts + ", tags=" + tags + "]";
	}

}
