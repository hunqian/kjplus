package com.kjplus.dto;

public class ServAsgnPackageDto {

	private Integer id; //签约记录ID
	private String code; //签约记录Code
	private Integer srvId;// 服务id
	private String srvCode;// 服务编号
	private String srvName;// 服务名称
	private String srvAlias;// 服务简称
	private Double srvPrice;// 服务价格

	public ServAsgnPackageDto() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getSrvId() {
		return srvId;
	}

	public void setSrvId(Integer srvId) {
		this.srvId = srvId;
	}

	public String getSrvCode() {
		return srvCode;
	}

	public void setSrvCode(String srvCode) {
		this.srvCode = srvCode;
	}

	public String getSrvName() {
		return srvName;
	}

	public void setSrvName(String srvName) {
		this.srvName = srvName;
	}

	public String getSrvAlias() {
		return srvAlias;
	}

	public void setSrvAlias(String srvAlias) {
		this.srvAlias = srvAlias;
	}

	public Double getSrvPrice() {
		return srvPrice;
	}

	public void setSrvPrice(Double srvPrice) {
		this.srvPrice = srvPrice;
	}

	@Override
	public String toString() {
		return "ServAsgnPackageDto [id=" + id + ", code=" + code + ", srvId=" + srvId + ", srvCode=" + srvCode + ", srvName=" + srvName + ", srvAlias="
				+ srvAlias + ", srvPrice=" + srvPrice + "]";
	}

}
