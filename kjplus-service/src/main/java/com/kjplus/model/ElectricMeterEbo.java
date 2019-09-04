package com.kjplus.model;

/**
 * TAdminUser entity. @author MyEclipse Persistence Tools
 */

public class ElectricMeterEbo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2052665979954122009L;
	private Integer id;
	private String meterCode;
	private String province;
	private String city;
	private String area;
	private String addr;
	private String longitude;
	private String latitude;
	// Constructors

	/** default constructor */
	public ElectricMeterEbo() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getMeterCode() {
		return meterCode;
	}

	public void setMeterCode(String meterCode) {
		this.meterCode = meterCode;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

}