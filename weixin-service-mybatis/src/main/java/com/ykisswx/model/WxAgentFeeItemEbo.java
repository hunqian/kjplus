package com.ykisswx.model;

import org.ybk.basic.util.Util;

/**
 * TAgencyFeeItem entity. @author MyEclipse Persistence Tools
 */

public class WxAgentFeeItemEbo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7441850101249353482L;
	private Integer id;
	private Integer fid;
	private Integer mid;
	private Double feeAmount;
	private Integer drawTime;
	private Integer drawableTime;
	private String feeType;
	private String flag;
	private String memo;
	private Integer mainId;
	private Integer subId;
	private Integer createTime;
	
	// Constructors

	/** default constructor */
	public WxAgentFeeItemEbo() {
	}

	/** minimal constructor */
	public WxAgentFeeItemEbo(Integer fid, Integer mid, Double feeAmount,
			Integer feeTime, Integer drawableTime, String feeType, String flag) {
		this.fid = fid;
		this.mid = mid;
		this.feeAmount = feeAmount;
		this.drawTime = feeTime;
		this.drawableTime = drawableTime;
		this.feeType = feeType;
		this.flag = flag;
	}

	/** full constructor */
	public WxAgentFeeItemEbo(Integer fid, Integer mid, Double feeAmount,
			Integer feeTime, Integer drawableTime, String feeType, String flag,
			String memo, Integer mainId) {
		this.fid = fid;
		this.mid = mid;
		this.feeAmount = feeAmount;
		this.drawTime = feeTime;
		this.drawableTime = drawableTime;
		this.feeType = feeType;
		this.flag = flag;
		this.memo = memo;
		this.mainId = mainId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFid() {
		return this.fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public Integer getMid() {
		return this.mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public Double getFeeAmount() {
		return this.feeAmount;
	}

	public void setFeeAmount(Double feeAmount) {
		this.feeAmount = feeAmount;
	}

	public Integer getDrawTime() {
		return this.drawTime;
	}

	public void setDrawTime(Integer feeTime) {
		this.drawTime = feeTime;
	}

	public Integer getDrawableTime() {
		return this.drawableTime;
	}

	public void setDrawableTime(Integer drawableTime) {
		this.drawableTime = drawableTime;
	}

	public String getFeeType() {
		return this.feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getMemo() {
		if(Util.isNull(memo))
			return "暂无备注";
		else
			return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getMainId() {
		return this.mainId;
	}

	public void setMainId(Integer mainId) {
		this.mainId = mainId;
	}

	public Integer getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}

	public Integer getSubId() {
		return subId;
	}

	public void setSubId(Integer subId) {
		this.subId = subId;
	}

}