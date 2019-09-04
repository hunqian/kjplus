package com.kjplus.eto;

import com.kjplus.annotation.Validation;
import com.kjplus.constant.Constant;

/**
 * TSysRefType entity. @author MyEclipse Persistence Tools
 */

public class SysRefTypeEto {

	// Fields
	public static final int TYPE_CODE_LEN = 8;
	public static final int VALUE_CODE_LEN = 2;

	private String refCode;
	@Validation
	private String refName;
	private String refType;
	private String refMemo;
	private String flag = Constant.FLAG_YES;

	// Constructors

	/** default constructor */
	public SysRefTypeEto() {
	}

	/** minimal constructor */
	public SysRefTypeEto(String refCode, String refName, String refType,
			String flag) {
		this.refCode = refCode;
		this.refName = refName;
		this.refType = refType;
		this.flag = flag;
	}

	/** full constructor */
	public SysRefTypeEto(String refCode, String refName, String refType,
			String refMemo, String flag) {
		this.refCode = refCode;
		this.refName = refName;
		this.refType = refType;
		this.refMemo = refMemo;
		this.flag = flag;
	}

	// Property accessors
	public String getRefCode() {
		return this.refCode;
	}

	public void setRefCode(String refCode) {
		this.refCode = refCode;
	}

	public String getRefName() {
		return this.refName;
	}

	public void setRefName(String refName) {
		this.refName = refName;
	}

	public String getRefType() {
		return this.refType;
	}

	public void setRefType(String refType) {
		this.refType = refType;
	}

	public String getRefMemo() {
		return this.refMemo;
	}

	public void setRefMemo(String refMemo) {
		this.refMemo = refMemo;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
}