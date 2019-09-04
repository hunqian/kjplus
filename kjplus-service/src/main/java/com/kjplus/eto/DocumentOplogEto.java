package com.kjplus.eto;

import com.kjplus.annotation.Validation;
import com.kjplus.constant.Constant;
import com.mq.util.DateUtil;

public class DocumentOplogEto {

    @Validation
    private  Integer prsnId;
    @Validation
    private  String opTypeCode;
    @Validation
    private  String orgCode;
    private  Integer opTime= DateUtil.getCurTimeInSec();
    @Validation
    private  Integer upPrsn;
    //修改信息描述
    private  String  memo;
    private  String  flag= Constant.FLAG_YES;

    public DocumentOplogEto() {
    }

    public Integer getPrsnId() {
		return prsnId;
	}

	public void setPrsnId(Integer prsnId) {
		this.prsnId = prsnId;
	}

	public String getOpTypeCode() {
        return opTypeCode;
    }

    public void setOpTypeCode(String opTypeCode) {
        this.opTypeCode = opTypeCode;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public Integer getOpTime() {
        return opTime;
    }

    public void setOpTime(Integer opTime) {
        this.opTime = opTime;
    }

    public Integer getUpPrsn() {
        return upPrsn;
    }

    public void setUpPrsn(Integer upPrsn) {
        this.upPrsn = upPrsn;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
