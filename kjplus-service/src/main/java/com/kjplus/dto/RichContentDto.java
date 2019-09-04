package com.kjplus.dto;

import java.util.Date;

/**
 * TAdminUser entity. @author MyEclipse Persistence Tools
 */

public class RichContentDto implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2061270096906132409L;
	private Integer id;
	private String code;
	private Integer mainId;
	private String mainTypeCode;
	private String mainTypeName;
	private int orgid;
	private String orgName;
	private int deptid;
	private String deptName;
	private int staffid;
	private String staffName;
	private String title;
	private String showPic1;
	private String showPic2;
	private String showPic3;
	private String showPic4;
	private String showPic5;
	private String content;
	private Date createTime;
	
	
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
	public Integer getMainId() {
		return mainId;
	}
	public void setMainId(Integer mainId) {
		this.mainId = mainId;
	}
	public String getMainTypeCode() {
		return mainTypeCode;
	}
	public void setMainTypeCode(String mainTypeCode) {
		this.mainTypeCode = mainTypeCode;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getShowPic1() {
		return showPic1;
	}
	public void setShowPic1(String showPic1) {
		this.showPic1 = showPic1;
	}
	public String getShowPic2() {
		return showPic2;
	}
	public void setShowPic2(String showPic2) {
		this.showPic2 = showPic2;
	}
	public String getShowPic3() {
		return showPic3;
	}
	public void setShowPic3(String showPic3) {
		this.showPic3 = showPic3;
	}
	public String getShowPic4() {
		return showPic4;
	}
	public void setShowPic4(String showPic4) {
		this.showPic4 = showPic4;
	}
	public String getShowPic5() {
		return showPic5;
	}
	public void setShowPic5(String showPic5) {
		this.showPic5 = showPic5;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getMainTypeName() {
		return mainTypeName;
	}
	public void setMainTypeName(String mainTypeName) {
		this.mainTypeName = mainTypeName;
	}
	public int getOrgid() {
		return orgid;
	}
	public void setOrgid(int orgid) {
		this.orgid = orgid;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public int getDeptid() {
		return deptid;
	}
	public void setDeptid(int deptid) {
		this.deptid = deptid;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public int getStaffid() {
		return staffid;
	}
	public void setStaffid(int staffid) {
		this.staffid = staffid;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	@Override
	public String toString() {
		return "NorContentDto [ID=" + id + ", mainId=" + mainId
				+ ", mainTypeCode=" + mainTypeCode + ", title=" + title
				+ ", showPic1=" + showPic1 + ", showPic2=" + showPic2
				+ ", showPic3=" + showPic3 + ", showPic4=" + showPic4
				+ ", showPic5=" + showPic5 + ", content=" + content
				+ ", createTime=" + createTime + "]";
	}
	

}