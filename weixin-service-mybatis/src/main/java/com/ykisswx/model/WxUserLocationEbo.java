package com.ykisswx.model;

/**
 * TWxUserPosition entity. @author MyEclipse Persistence Tools
 */

public class WxUserLocationEbo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2014733382515039354L;
	private Integer id;
	private Integer userId;
	private Double latitude;
	private Double longitude;
	private Double precision;
	private Integer createTime;

	// Constructors

	/** default constructor */
	public WxUserLocationEbo() {
	}

	/** minimal constructor */
	public WxUserLocationEbo(Integer userId, Integer createTime) {
		this.userId = userId;
		this.createTime = createTime;
	}

	/** full constructor */
	public WxUserLocationEbo(Integer userId, Double latitude, Double longitude,
			Double precision, Integer createTime) {
		this.userId = userId;
		this.latitude = latitude;
		this.longitude = longitude;
		this.precision = precision;
		this.createTime = createTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getPrecision() {
		return this.precision;
	}

	public void setPrecision(Double precision) {
		this.precision = precision;
	}

	public Integer getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}

}