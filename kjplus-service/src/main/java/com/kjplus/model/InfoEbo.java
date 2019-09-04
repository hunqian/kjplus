package com.kjplus.model;

import java.io.Serializable;
import java.util.Date;

public class InfoEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6084727123108754884L;
	private Integer id;
	private String infoCode;
	private String infoTitle;
	private String infoDesc;
	private Integer infoCatgId;
	private Integer totalViewNum;
	private Integer totalZanNum;
	private Integer totalFocusNum;
	private String headImgUrl;
	private String iconImgUrl;
	private String flag;
	private Integer pubId = 0;
	private Integer deptId = 0;
	private Integer orgId = 0;
	private Date pubTime = null;
	private String infoType;

	public InfoEbo() {
		super();
	}

	public InfoEbo(Integer id, String infoCode, String infoTitle, Integer infoCataId, Integer deptId, Integer orgId,
			String flag, Integer totalViewNum, Integer totalZanNum, Date pubTime, Integer pubId, String headImgUrl,
			String iconImgUrl,String infoType) {
		super();
		this.id = id;
		this.infoCode = infoCode;
		this.infoTitle = infoTitle;
		this.infoCatgId = infoCataId;
		this.deptId = deptId;
		this.orgId = orgId;
		this.flag = flag;
		this.totalViewNum = totalViewNum;
		this.totalZanNum = totalZanNum;
		this.pubTime = pubTime;
		this.pubId = pubId;
		this.headImgUrl = headImgUrl;
		this.iconImgUrl = iconImgUrl;
		this.infoType = infoType;
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

	public Integer getInfoCatgId() {
		return infoCatgId;
	}

	public void setInfoCatgId(Integer infoCatgId) {
		this.infoCatgId = infoCatgId;
	}

	public Integer getTotalFocusNum() {
		return totalFocusNum;
	}

	public void setTotalFocusNum(Integer totalFocusNum) {
		this.totalFocusNum = totalFocusNum;
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

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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

	public Date getPubTime() {
		return pubTime;
	}

	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}

	public Integer getPubId() {
		return pubId;
	}

	public void setPubId(Integer pubId) {
		this.pubId = pubId;
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
		return "InfoEbo [id=" + id + ", infoCode=" + infoCode + ", infoTitle="
				+ infoTitle + ", infoDesc=" + infoDesc + ", infoCatgId="
				+ infoCatgId + ", totalViewNum=" + totalViewNum
				+ ", totalZanNum=" + totalZanNum + ", totalFocusNum="
				+ totalFocusNum + ", headImgUrl=" + headImgUrl
				+ ", iconImgUrl=" + iconImgUrl + ", flag=" + flag + ", pubId="
				+ pubId + ", deptId=" + deptId + ", orgId=" + orgId
				+ ", pubTime=" + pubTime + ", infoType=" + infoType + "]";
	}

	

}
