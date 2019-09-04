package com.kjplus.eto;

import com.kjplus.annotation.Validation;
import com.kjplus.constant.Constant;
import com.mq.util.DateUtil;

public class PersonOplogEto {

	private Integer opSeq = 1;// 操作次数
	@Validation
	private Integer prsnId;// 档案人id
	private String opTypeCode = Constant.RV_DOC_UPDATE;// 操作类型，默认档案修改
	private Integer uid = 0;// 操作人 可为空
	private Integer orgid;// 组织 为空时，用户所在组织
	private String flag = Constant.FLAG_YES;
	private String memo;// 备注
	private Integer opTime = DateUtil.getCurTimeInSec();// 操作时间，默认当前

	public Integer getOpSeq() {
		return opSeq;
	}

	public void setOpSeq(Integer opSeq) {
		this.opSeq = opSeq;
	}

	public Integer getPrsnId() {
		return prsnId;
	}

	public void setPrsnId(Integer prsnId) {
		this.prsnId = prsnId;
	}

	public String getOpTypeCode() {
		return opTypeCode;
	}

	public void setOpTypeCode(String opTypeCode) {
		this.opTypeCode = opTypeCode;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getOrgid() {
		return orgid;
	}

	public void setOrgid(Integer orgid) {
		this.orgid = orgid;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getOpTime() {
		return opTime;
	}

	public void setOpTime(Integer opTime) {
		this.opTime = opTime;
	}

}
