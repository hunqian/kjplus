package com.ykisswx.dto;

import org.ybk.basic.util.DateUtil;

import com.ykisswx.constant.TypeConstant;

public class WxAgentFeeItemDto implements java.io.Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1924114243735148246L;
	private Integer id;
	private Integer fid;
	private Integer uid;
	private Double feeAmount;
	private Integer drawTime;
	private Integer drawableTime;
	private String feeType;
	private String flag;
	private String memo;
	private Integer mainId;
	private Integer createTime;
	
	// Constructors

	/** default constructor */
	public WxAgentFeeItemDto() {
	}

	/** minimal constructor */
	public WxAgentFeeItemDto(Integer fid, Integer uid, Double feeAmount,
			Integer feeTime, Integer drawableTime, String feeType, String flag) {
		this.fid = fid;
		this.uid = uid;
		this.feeAmount = feeAmount;
		this.drawTime = feeTime;
		this.drawableTime = drawableTime;
		this.feeType = feeType;
		this.flag = flag;
	}

	/** full constructor */
	//i.fid,i.uid,i.feeAmount,i.drawTime,i.drawableTime,i.feeType,i.flag,i.memo,i.mainId
	public WxAgentFeeItemDto(Integer fid, Integer uid, Double feeAmount,
			Integer feeTime, Integer drawableTime, String feeType, String flag,
			String memo, Integer mainId) {
		this.fid = fid;
		this.uid = uid;
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

	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Double getFeeAmount() {
		return this.feeAmount;
	}

	public void setFeeAmount(Double feeAmount) {
		this.feeAmount = feeAmount;
	}

	public String getDrawTime() {
		return DateUtil.formatDateTime(this.drawTime);
	}

	public void setDrawTime(Integer feeTime) {
		this.drawTime = feeTime;
	}

	public String getDrawableTime() {
		return DateUtil.formatDateTime(this.drawableTime);
	}

	public void setDrawableTime(Integer drawableTime) {
		this.drawableTime = drawableTime;
	}

	public String getFeeType() {
		String feeType = "间接";
		if(TypeConstant.FEE_DIRECT_TYPE.equals(this.feeType))
			feeType = "直接";
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	public String getFlag() {
		String flag = "无效";
		if(TypeConstant.FEE_FLAG_UNPAID.equals(this.flag))
			flag = "待付";
		else if(TypeConstant.FEE_FLAG_PAID.equals(this.flag))
			flag = "已付";
		else if(TypeConstant.FEE_FLAG_FORWARD.equals(this.flag))
			flag = "申请";
		else if(TypeConstant.FEE_FLAG_APPIDED.equals(this.flag))
			flag = "已汇款";
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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

	public Integer getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}
}
