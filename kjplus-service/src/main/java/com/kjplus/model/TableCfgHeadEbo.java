package com.kjplus.model;

import java.io.Serializable;
import java.util.Date;

public class TableCfgHeadEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4026831095654298380L;
	private Integer id;
	private String code;
	private String name;
	private Integer typeId = 0;
	private String dataType;
	private int prsnTypeId;
	private String flag;
	private Date createTime;

	public TableCfgHeadEbo() {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getPrsnTypeId() {
		return prsnTypeId;
	}

	public void setPrsnTypeId(int prsnTypeId) {
		this.prsnTypeId = prsnTypeId;
	}

	@Override
	public String toString() {
		return "TableCfgHeadEbo [id=" + id + ", code=" + code + ", name=" + name + ", typeId=" + typeId + ", dataType=" + dataType + ", flag=" + flag + ", createTime=" + createTime + "]";
	}

}
