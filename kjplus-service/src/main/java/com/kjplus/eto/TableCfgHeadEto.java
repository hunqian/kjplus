package com.kjplus.eto;

import com.kjplus.annotation.Validation;

public class TableCfgHeadEto {

	public static final int CODE_LEN = 8;
	// refValue表id
	@Validation
	private Integer typeId;
	private String code = "";
	@Validation
	private String name;

	public TableCfgHeadEto() {
		super();
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer type) {
		this.typeId = type;
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

}
