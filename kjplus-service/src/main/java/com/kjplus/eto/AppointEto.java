package com.kjplus.eto;

import com.kjplus.annotation.Validation;
import com.kjplus.constant.Constant;
import com.mq.util.DateUtil;

public class AppointEto {

	public static final int CODE_LEN = 32;

	private String code;
	private Integer calInfoId;// 当calId为空时,代表用户主动预约医生，默认对应到医生的默认工作服务日历
	@Validation
	private Integer orgId;
	@Validation
	private Integer prsnId;
	private String status = Constant.FLAG_YES;
	@Validation
	private Integer mainId;
	@Validation
	private String mainType;
	private Integer appTypeId;//具体预约参照 eg：乙肝疫苗
	private String appMemo;// 如果mainCode为空则以info表memo为准
							// 当主动预约时，此处需要预约具体内容的描述（不可空）
	private Integer createTime = DateUtil.getCurTimeInSec();
	// 当主动预约时，需要填的数据
	private Integer appointTime;// 预约时间
	private String appointSourceType = "A";// 日历信息来源 主动预约
	private String appointTitle;// 预约题目（大致事项）

	public AppointEto() {
		super();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getCalInfoId() {
		return calInfoId;
	}

	public void setCalInfoId(Integer calInfoId) {
		this.calInfoId = calInfoId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getPrsnId() {
		return prsnId;
	}

	public void setPrsnId(Integer prsnId) {
		this.prsnId = prsnId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getMainId() {
		return mainId;
	}

	public void setMainId(Integer mainId) {
		this.mainId = mainId;
	}

	public String getMainType() {
		return mainType;
	}

	public void setMainType(String mainType) {
		this.mainType = mainType;
	}

	public String getAppMemo() {
		return appMemo;
	}

	public void setAppMemo(String appMemo) {
		this.appMemo = appMemo;
	}

	public Integer getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}

	public Integer getAppointTime() {
		return appointTime;
	}

	public void setAppointTime(Integer appointTime) {
		this.appointTime = appointTime;
	}

	public String getAppointSourceType() {
		return appointSourceType;
	}

	public void setAppointSourceType(String appointSourceType) {
		this.appointSourceType = appointSourceType;
	}

	public String getAppointTitle() {
		return appointTitle;
	}

	public void setAppointTitle(String appointTitle) {
		this.appointTitle = appointTitle;
	}

	public Integer getAppTypeId() {
		return appTypeId;
	}

	public void setAppTypeId(Integer appTypeId) {
		this.appTypeId = appTypeId;
	}

}
