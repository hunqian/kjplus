package com.kjplus.eto;

import java.util.Date;

import org.ybk.basic.util.DateUtil;

import com.kjplus.constant.Constant;

public class InfoEto {

	public static final int CODE_LEN = 32;
	private String content;
	private String infoTitle;
	private String infoDesc;
	private Integer infoCatgId;
	private Integer totalViewNum = 0;
	private Integer totalZanNum = 0;
	private Integer totalFocusNum=0;
	private String headImgUrl;
	private String iconImgUrl;
	private String flag = Constant.FLAG_YES;
	private Integer pubId = 0;
	private Integer deptId = 0;
	private Integer orgId = 0;
	private Date pubTime = DateUtil.newTime();
	private String infoType;

	public InfoEto() {
		super();
	}

	public String getInfoTitle() {
		return infoTitle;
	}

	public void setInfoTitle(String infoTitle) {
		this.infoTitle = infoTitle;
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

	public Integer getInfoCatgId() {
		return infoCatgId;
	}

	public void setInfoCatgId(Integer infoCatgId) {
		this.infoCatgId = infoCatgId;
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

	public Date getPubTime() {
		return pubTime;
	}

	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}

	public Integer getTotalFocusNum() {
		return totalFocusNum;
	}

	public void setTotalFocusNum(Integer totalFocusNum) {
		this.totalFocusNum = totalFocusNum;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
	
	
}
