package com.kjplus.eto;

import org.ybk.basic.util.DateUtils;

import com.kjplus.annotation.Validation;
import com.kjplus.constant.Constant;

public class ServiceLogEto {

	public static final int CODE_LEN = 16;
	private String code;
	@Validation
	private Integer srvId;
	@Validation
	private Integer providerId;
	@Validation
	private Integer prsnId;
	private String flag = Constant.FLAG_YES;
	private Integer createTime = DateUtils.getCurTimeInSec();

	public ServiceLogEto() {
		super();
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

	public Integer getProviderId() {
		return providerId;
	}

	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}

	public Integer getPrsnId() {
		return prsnId;
	}

	public void setPrsnId(Integer prsnId) {
		this.prsnId = prsnId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Integer getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}

}
