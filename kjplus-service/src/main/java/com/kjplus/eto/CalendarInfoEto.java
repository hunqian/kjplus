package com.kjplus.eto;

import org.ybk.basic.util.DateUtil;

import com.kjplus.annotation.Validation;
import com.kjplus.constant.Constant;

public class CalendarInfoEto {

	public static final int CODE_LEN = 32;
	private String claInfoCode = null;// 日历信息表的编号
	@Validation
	private Integer calEntryId;// 日历主表的编号
	private String calTitle;
	private String calMemo;
	private Integer startTime;
	private Integer endTime;
	private Integer maxPerson = 0;
	private Integer joinPerson = 0;// 实际加入人数默认创建为0
	private String flag = Constant.FLAG_YES;
	private Integer createTime = DateUtil.getCurTimeInSec();
	private String sourceType = "E";// 默认数据来源是医生从日历入口中添加的

	public CalendarInfoEto() {
		super();
	}

	public Integer getCalEntryId() {
		return calEntryId;
	}

	public void setCalEntryId(Integer calEntryId) {
		this.calEntryId = calEntryId;
	}

	public String getClaInfoCode() {
		return claInfoCode;
	}

	public void setClaInfoCode(String claInfoCode) {
		this.claInfoCode = claInfoCode;
	}

	public String getCalTitle() {
		return calTitle;
	}

	public void setCalTitle(String calTitle) {
		this.calTitle = calTitle;
	}

	public String getCalMemo() {
		return calMemo;
	}

	public void setCalMemo(String calMemo) {
		this.calMemo = calMemo;
	}

	public Integer getStartTime() {
		return startTime;
	}

	public void setStartTime(Integer startTime) {
		this.startTime = startTime;
	}

	public Integer getEndTime() {
		return endTime;
	}

	public void setEndTime(Integer endTime) {
		this.endTime = endTime;
	}

	public Integer getMaxPerson() {
		return maxPerson;
	}

	public void setMaxPerson(Integer maxPerson) {
		this.maxPerson = maxPerson;
	}

	public Integer getJoinPerson() {
		return joinPerson;
	}

	public void setJoinPerson(Integer joinPerson) {
		this.joinPerson = joinPerson;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Integer getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

}
