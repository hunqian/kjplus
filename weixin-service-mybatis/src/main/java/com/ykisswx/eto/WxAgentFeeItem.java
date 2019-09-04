package com.ykisswx.eto;

/**
 * TAgencyFeeItem entity. @author MyEclipse Persistence Tools
 */

public class WxAgentFeeItem implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6831604015829358541L;
	// Fields
	private Integer mid;
	private Double feeAmount;
	private Integer drawableTime;
	private String feeType;
	private String memo;
	private Integer mainId;
	private Integer subId;

	// Constructors

	/** default constructor */
	public WxAgentFeeItem() {
	}

	/** minimal constructor */
	public WxAgentFeeItem(Integer fid, Integer mid, Double feeAmount,
			Integer feeTime, Integer drawableTime, String feeType, String flag) {
		this.mid = mid;
		this.feeAmount = feeAmount;
		this.drawableTime = drawableTime;
		this.feeType = feeType;
	}

	/** full constructor */
	public WxAgentFeeItem(Integer fid, Integer mid, Double feeAmount,
			Integer feeTime, Integer drawableTime, String feeType, String flag,
			String memo, Integer mainId) {
		this.mid = mid;
		this.feeAmount = feeAmount;
		this.drawableTime = drawableTime;
		this.feeType = feeType;
		this.memo = memo;
		this.mainId = mainId;
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

	public String getMemo() {
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

	public Integer getSubId() {
		return subId;
	}

	public void setSubId(Integer subId) {
		this.subId = subId;
	}

}