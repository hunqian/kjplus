package com.kjplus.dto;

/**
 * 用于mobile 服务包内容显示
 * 
 * @author songyuebin
 * 
 */
public class MblSrvPackageDto {

	private String code;
	private String name;
	private String alias;
	// TODO 是否需要从后台再次获取 详情内容较多
	private String memo;
	private String isDefault;// 是否是默认服务

	public MblSrvPackageDto() {
		super();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

}
