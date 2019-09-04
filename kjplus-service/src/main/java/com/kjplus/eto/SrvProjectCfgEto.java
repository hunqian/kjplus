package com.kjplus.eto;

import com.kjplus.annotation.Validation;
import com.kjplus.constant.Constant;


public class SrvProjectCfgEto {

    @Validation
    private String prjCode;
    @Validation
    private Integer tagId;
    @Validation
    private String orgCode;
    private String isNorm = Constant.FLAG_YES;

    public SrvProjectCfgEto() {
    }

    public String getPrjCode() {
        return prjCode;
    }

    public void setPrjCode(String prjCode) {
        this.prjCode = prjCode;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getIsNorm() {
        return isNorm;
    }

    public void setIsNorm(String isNorm) {
        this.isNorm = isNorm;
    }
}
