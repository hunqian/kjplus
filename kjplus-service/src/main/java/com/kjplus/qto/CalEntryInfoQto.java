package com.kjplus.qto;

import org.ybk.basic.util.DateUtil;

import com.kjplus.constant.Constant;

public class CalEntryInfoQto {

	private int mainId; // 主体id
	private String mainType; // 主体类型 //某人的日历
	private int orgId;
	private int entryId;// 日历入口编号
	private int infoId;// 日历信息表编号
	private String infoTitle;// 日历信息标题模糊查询
	private String entryName;// 日历入口名字模糊查询
	private String infoSourceType;// 日历信息标题模糊查询
	// 查询某个时间端的活动 开始时间大于startTime ，结束时间小于endTime
	private int startTime = 0;// 活动等开始时间
	private int endTime = 0;// 活动等结束时间
	private int validTime = DateUtil.getCurDayBeginInSec();// 活动有效时间（默认为当前时间）
	private boolean isValid = true;// 是否获取有效的日历信息（预约和活动）
	// 具体某一天的活动
	private String dayStr;
	// 查询某月的
	private int year;// 年
	private int month;// 月
	private String entryFlag = Constant.FLAG_YES;// 日历主表是否有效
	private String infoFlag = Constant.FLAG_YES;// 日历信息表是否有效

	// 日历信息类型
	private int infoTypeId;// 日历信息的类型 参照对应数据汇总

	public CalEntryInfoQto() {
		super();
	}

	public int getMainId() {
		return mainId;
	}

	public void setMainId(int mainId) {
		this.mainId = mainId;
	}

	public String getMainType() {
		return mainType;
	}

	public void setMainType(String mainType) {
		this.mainType = mainType;
	}

	public int getOrgId() {
		return orgId;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	public int getEntryId() {
		return entryId;
	}

	public void setEntryId(int entryId) {
		this.entryId = entryId;
	}

	public int getInfoId() {
		return infoId;
	}

	public void setInfoId(int infoId) {
		this.infoId = infoId;
	}

	public String getInfoTitle() {
		return infoTitle;
	}

	public void setInfoTitle(String infoTitle) {
		this.infoTitle = infoTitle;
	}

	public String getEntryName() {
		return entryName;
	}

	public void setEntryName(String entryName) {
		this.entryName = entryName;
	}

	public String getInfoSourceType() {
		return infoSourceType;
	}

	public void setInfoSourceType(String infoSourceType) {
		this.infoSourceType = infoSourceType;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	public String getDayStr() {
		return dayStr;
	}

	public void setDayStr(String dayStr) {
		this.dayStr = dayStr;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public String getEntryFlag() {
		return entryFlag;
	}

	public void setEntryFlag(String entryFlag) {
		this.entryFlag = entryFlag;
	}

	public String getInfoFlag() {
		return infoFlag;
	}

	public void setInfoFlag(String infoFlag) {
		this.infoFlag = infoFlag;
	}

	public int getInfoTypeId() {
		return infoTypeId;
	}

	public void setInfoTypeId(int infoTypeId) {
		this.infoTypeId = infoTypeId;
	}

	public int getValidTime() {
		return validTime;
	}

	public void setValidTime(int validTime) {
		this.validTime = validTime;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

}
