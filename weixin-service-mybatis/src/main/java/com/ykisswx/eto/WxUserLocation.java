package com.ykisswx.eto;

public class WxUserLocation implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2014733382515039354L;
	private int userId;
	private double latitude;
	private double longitude;
	private double precision;
	private int createTime;

	// Constructors

	/** default constructor */
	public WxUserLocation() {
	}

	/** minimal constructor */
	public WxUserLocation(int userId, int createTime) {
		this.userId = userId;
		this.createTime = createTime;
	}

	/** full constructor */
	public WxUserLocation(int userId, double latitude, double longitude, double precision, int createTime) {
		this.userId = userId;
		this.latitude = latitude;
		this.longitude = longitude;
		this.precision = precision;
		this.createTime = createTime;
	}

	// Property accessors
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getPrecision() {
		return this.precision;
	}

	public void setPrecision(double precision) {
		this.precision = precision;
	}

	public int getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}

}