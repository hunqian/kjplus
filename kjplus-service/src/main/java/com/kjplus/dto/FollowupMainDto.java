package com.kjplus.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FollowupMainDto implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7407612162117361477L;
	// 随访信息
	private Integer id;
	private String code;
	private String selfCode;
	private Integer prsnId;
	private Integer stafffId;
	private Integer orgId;
	private Integer tbcfgId;
	private String flpeMiscType;
	private Integer flpeTypeId;
	private Integer flpeTime;
	private Date flpeDay;
	private Integer resId;
	private String resMemo;
	private Integer resStaffId;
	// 档案信息
	private String prsnName;
	// 医生信息
	private String staffName;
	private String resStaffName;
	// 组织信息
	private String orgName;
	// 表格信息
	private String tabCfgName;
	private Integer tabDataId;
	// 参照信息
	private String resRefName;
	private String inspectRefName;
	// 表格具体数据
	private List<TableDataLineDto> listDataLine = new ArrayList<TableDataLineDto>();

	// 检查
	private List<Object> examList = new ArrayList<Object>();

	// Constructors

	/** default constructor */
	public FollowupMainDto() {
		super();
	}

	// Property accessors
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

	public String getSelfCode() {
		return selfCode;
	}

	public void setSelfCode(String selfCode) {
		this.selfCode = selfCode;
	}

	public Integer getPrsnId() {
		return prsnId;
	}

	public void setPrsnId(Integer prsnId) {
		this.prsnId = prsnId;
	}

	public Integer getStafffId() {
		return stafffId;
	}

	public void setStafffId(Integer stafffId) {
		this.stafffId = stafffId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getTbcfgId() {
		return tbcfgId;
	}

	public void setTbcfgId(Integer tbcfgId) {
		this.tbcfgId = tbcfgId;
	}

	public String getFlpeMiscType() {
		return flpeMiscType;
	}

	public void setFlpeMiscType(String flpeMiscType) {
		this.flpeMiscType = flpeMiscType;
	}

	public Integer getFlpeTypeId() {
		return flpeTypeId;
	}

	public void setFlpeTypeId(Integer flpeTypeId) {
		this.flpeTypeId = flpeTypeId;
	}

	public Integer getFlpeTime() {
		return flpeTime;
	}

	public void setFlpeTime(Integer flpeTime) {
		this.flpeTime = flpeTime;
	}

	public Date getFlpeDay() {
		return flpeDay;
	}

	public void setFlpeDay(Date flpeDay) {
		this.flpeDay = flpeDay;
	}

	public Integer getResId() {
		return resId;
	}

	public void setResId(Integer resId) {
		this.resId = resId;
	}

	public String getResMemo() {
		return resMemo;
	}

	public void setResMemo(String resMemo) {
		this.resMemo = resMemo;
	}

	public Integer getResStaffId() {
		return resStaffId;
	}

	public void setResStaffId(Integer resStaffId) {
		this.resStaffId = resStaffId;
	}

	public String getPrsnName() {
		return prsnName;
	}

	public void setPrsnName(String prsnName) {
		this.prsnName = prsnName;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getResStaffName() {
		return resStaffName;
	}

	public void setResStaffName(String resStaffName) {
		this.resStaffName = resStaffName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getTabCfgName() {
		return tabCfgName;
	}

	public void setTabCfgName(String tabCfgName) {
		this.tabCfgName = tabCfgName;
	}

	public Integer getTabDataId() {
		return tabDataId;
	}

	public void setTabDataId(Integer tabDataId) {
		this.tabDataId = tabDataId;
	}

	public String getResRefName() {
		return resRefName;
	}

	public void setResRefName(String resRefName) {
		this.resRefName = resRefName;
	}

	public List<TableDataLineDto> getListDataLine() {
		return listDataLine;
	}

	public void setListDataLine(List<TableDataLineDto> listDataLine) {
		this.listDataLine = listDataLine;
	}

	public List<Object> getExamList() {
		return examList;
	}

	public void setExamList(List<Object> examList) {
		this.examList = examList;
	}

	public String getInspectRefName() {
		return inspectRefName;
	}

	public void setInspectRefName(String inspectRefName) {
		this.inspectRefName = inspectRefName;
	}

	@Override
	public String toString() {
		return "FollowupMainDto [id=" + id + ", code=" + code + ", selfCode=" + selfCode + ", prsnId=" + prsnId + ", stafffId=" + stafffId + ", orgId="
				+ orgId + ", tbcfgId=" + tbcfgId + ", flpeMiscType=" + flpeMiscType + ", flpeTypeId=" + flpeTypeId + ", flpeTime=" + flpeTime
				+ ", flpeDay=" + flpeDay + ", resId=" + resId + ", resMemo=" + resMemo + ", resStaffId=" + resStaffId + ", prsnName=" + prsnName
				+ ", staffName=" + staffName + ", resStaffName=" + resStaffName + ", orgName=" + orgName + ", tabCfgName=" + tabCfgName + ", tabDataId="
				+ tabDataId + ", resRefName=" + resRefName + ", inspectRefName=" + inspectRefName + ", listDataLine=" + listDataLine + ", examList="
				+ examList + "]";
	}

}
