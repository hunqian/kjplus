package com.ykisswx.eto;

import java.util.Date;

import org.ybk.basic.util.DateUtil;

import com.ykisswx.constant.WxConstant;

/**
 * TWxMsgtmplUse entity. @author MyEclipse Persistence Tools
 */

public class WxMsgtmplUseEto  {

	// Fields

	private Integer accId;
	private Integer tmplId;
	private String flag = WxConstant.FLAG_YES;
	private Integer createTime = DateUtil.getCurTimeInSec();
	
	public WxMsgtmplUseEto(Integer accId, Integer tmplId, String flag, Integer createTime) {
		super();
		this.accId = accId;
		this.tmplId = tmplId;
		this.flag = flag;
		this.createTime = createTime;
	}
	
	public WxMsgtmplUseEto() {
		super();
	}

	public Integer getAccId() {
		return accId;
	}
	public void setAccId(Integer accId) {
		this.accId = accId;
	}
	public Integer getTmplId() {
		return tmplId;
	}
	public void setTmplId(Integer tmplId) {
		this.tmplId = tmplId;
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



	// Constructors

}