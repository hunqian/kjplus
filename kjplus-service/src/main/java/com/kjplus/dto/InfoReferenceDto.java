package com.kjplus.dto;

public class InfoReferenceDto {

	private Integer id;
	private String refCode;
	private Integer infoId;
	private Integer infoTypeId;
	private String infoTypeName;
	private Integer viewNum;
	private Integer zanNum;
	private Integer focusNum;
	private Integer orgId;
	private String orgName;
	private Integer deptId;
	private String deptName;
	private String flag;
	private String infoFlag;
	private String infoTitle;
	private String headImgUrl;
	private String iconImgUrl;
	//我的关注
	private boolean mf = false;
	//我的点赞
	private boolean mz = false;
	
	public InfoReferenceDto() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getInfoId() {
		return infoId;
	}

	public void setInfoId(Integer infoId) {
		this.infoId = infoId;
	}

	public Integer getInfoTypeId() {
		return infoTypeId;
	}

	public void setInfoTypeId(Integer infoTypeId) {
		this.infoTypeId = infoTypeId;
	}

	public String getInfoTypeName() {
		return infoTypeName;
	}

	public void setInfoTypeName(String infoTypeName) {
		this.infoTypeName = infoTypeName;
	}

	public Integer getViewNum() {
		return viewNum;
	}

	public void setViewNum(Integer viewNum) {
		this.viewNum = viewNum;
	}

	public Integer getZanNum() {
		return zanNum;
	}

	public void setZanNum(Integer zanNum) {
		this.zanNum = zanNum;
	}

	public Integer getFocusNum() {
		return focusNum;
	}

	public void setFocusNum(Integer focusNum) {
		this.focusNum = focusNum;
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

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getInfoTitle() {
		return infoTitle;
	}

	public void setInfoTitle(String infoTitle) {
		this.infoTitle = infoTitle;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public String getIconImgUrl() {
		return iconImgUrl;
	}

	public void setIconImgUrl(String iconImgUrl) {
		this.iconImgUrl = iconImgUrl;
	}

	public String getRefCode() {
		return refCode;
	}

	public void setRefCode(String refCode) {
		this.refCode = refCode;
	}

	public String getInfoFlag() {
		return infoFlag;
	}

	public void setInfoFlag(String infoFlag) {
		this.infoFlag = infoFlag;
	}

	public boolean isMf() {
		return mf;
	}

	public void setMf(boolean mf) {
		this.mf = mf;
	}

	public boolean isMz() {
		return mz;
	}

	public void setMz(boolean mz) {
		this.mz = mz;
	}

	@Override
	public String toString() {
		return "InfoReferenceDto [id=" + id + ", refCode=" + refCode + ", infoId=" + infoId + ", infoTypeId="
				+ infoTypeId + ", infoTypeName=" + infoTypeName + ", viewNum=" + viewNum + ", zanNum=" + zanNum
				+ ", orgId=" + orgId + ", orgName=" + orgName + ", deptId=" + deptId + ", deptName=" + deptName
				+ ", flag=" + flag + ", infoFlag=" + infoFlag + ", infoTitle=" + infoTitle + ", headImgUrl="
				+ headImgUrl + ", iconImgUrl=" + iconImgUrl + "]";
	}

}
