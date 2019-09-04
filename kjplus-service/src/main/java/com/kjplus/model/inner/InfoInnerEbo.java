package com.kjplus.model.inner;

import java.io.Serializable;
import java.util.Date;

public class InfoInnerEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8728553555958932800L;
	//咨询主表
	private Integer id;
	private String refCode;
	private String infoCode;
	private String infoTitle;
	private String infoDesc;
	private Integer infoTypeId;
	private String infoTypeName;
	private Integer infoCatgId;
	private String infoCatgName;
	
	private Integer totalViewNum;
	private Integer totalZanNum;
	private Integer totalFocusNum;
	private String headImgUrl;
	private String iconImgUrl;
	private String flag;
	private String infoFlag;
	private Integer pubId;
	private String pubName;
	private Integer deptId;
	private String deptName;
	private Integer orgId;
	private String orgName;
	private Date pubTime;
	private String infoType;
	
	private Integer viewNum = 0;
	private Integer zanNum = 0;
	private Integer focusNum = 0;
	
	public InfoInnerEbo() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInfoCode() {
		return infoCode;
	}

	public void setInfoCode(String infoCode) {
		this.infoCode = infoCode;
	}

	public String getInfoTitle() {
		return infoTitle;
	}

	public void setInfoTitle(String infoTitle) {
		this.infoTitle = infoTitle;
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

	public Integer getTotalViewNum() {
		return totalViewNum;
	}

	public void setTotalViewNum(Integer totalViewNum) {
		this.totalViewNum = totalViewNum;
	}

	public Integer getTotalZanNum() {
		return totalZanNum;
	}

	public void setTotalZanNum(Integer totalZanNum) {
		this.totalZanNum = totalZanNum;
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

	public Integer getTotalFocusNum() {
		return totalFocusNum;
	}

	public void setTotalFocusNum(Integer totalFocusNum) {
		this.totalFocusNum = totalFocusNum;
	}

	public void setIconImgUrl(String iconImgUrl) {
		this.iconImgUrl = iconImgUrl;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Integer getPubId() {
		return pubId;
	}

	public void setPubId(Integer pubId) {
		this.pubId = pubId;
	}

	public String getPubName() {
		return pubName;
	}

	public void setPubName(String pubName) {
		this.pubName = pubName;
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

	public Date getPubTime() {
		return pubTime;
	}

	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}

	public String getInfoFlag() {
		return infoFlag;
	}

	public void setInfoFlag(String infoFlag) {
		this.infoFlag = infoFlag;
	}

	public String getRefCode() {
		return refCode;
	}

	public void setRefCode(String refCode) {
		this.refCode = refCode;
	}

	public Integer getInfoCatgId() {
		return infoCatgId;
	}

	public void setInfoCatgId(Integer infoCatgId) {
		this.infoCatgId = infoCatgId;
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

	
	public String getInfoCatgName() {
		return infoCatgName;
	}

	public void setInfoCatgName(String infoCatgName) {
		this.infoCatgName = infoCatgName;
	}

	public String getInfoDesc() {
		return infoDesc;
	}

	public void setInfoDesc(String infoDesc) {
		this.infoDesc = infoDesc;
	}

	public String getInfoType() {
		return infoType;
	}

	public void setInfoType(String infoType) {
		this.infoType = infoType;
	}

	@Override
	public String toString() {
		return "InfoInnerEbo [id=" + id + ", refCode=" + refCode
				+ ", infoCode=" + infoCode + ", infoTitle=" + infoTitle
				+ ", infoDesc=" + infoDesc + ", infoTypeId=" + infoTypeId
				+ ", infoTypeName=" + infoTypeName + ", infoCatgId="
				+ infoCatgId + ", infoCatgName=" + infoCatgName
				+ ", totalViewNum=" + totalViewNum + ", totalZanNum="
				+ totalZanNum + ", totalFocusNum=" + totalFocusNum
				+ ", headImgUrl=" + headImgUrl + ", iconImgUrl=" + iconImgUrl
				+ ", flag=" + flag + ", infoFlag=" + infoFlag + ", pubId="
				+ pubId + ", pubName=" + pubName + ", deptId=" + deptId
				+ ", deptName=" + deptName + ", orgId=" + orgId + ", orgName="
				+ orgName + ", pubTime=" + pubTime + ", infoType=" + infoType
				+ ", viewNum=" + viewNum + ", zanNum=" + zanNum + ", focusNum="
				+ focusNum + "]";
	}

	

}
