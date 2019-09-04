package com.kjplus.dto;

import java.io.Serializable;

public class DocumentOplogDto implements Serializable {


    private static final long serialVersionUID = 8694427067944735175L;
    //被修改人
    private  Integer id;
    private  Integer prsnId;
    private  String prsnCode;
    private  String prsnName;

    //类型去参照
    private  Integer opTypeId;
    private  String opTypeCode;
    private  String opTypeName;

    //组织
    private  Integer orgId;
    private  String orgCode;
    private  String orgName;

    //修改人
    private  Integer upPrsn;
    private  String upPrsnName;
    private  String upPrsnMobile;

    //修改时间 修改次数 修改信息 是否有效
    private  Integer opTime;
    private  Integer opSeq;
    private  String  memo;
    private  String  flag;

    public DocumentOplogDto() {
    }

    public DocumentOplogDto(Integer id, Integer prsnId, String prsnCode, String prsnName, Integer opTypeId, String opTypeCode, String opTypeName, Integer orgId, String orgCode, String orgName, Integer upPrsn, String upPrsnName, String upPrsnMobile, Integer opTime, Integer opSeq, String memo, String flag) {
        this.id = id;
        this.prsnId = prsnId;
        this.prsnCode = prsnCode;
        this.prsnName = prsnName;
        this.opTypeId = opTypeId;
        this.opTypeCode = opTypeCode;
        this.opTypeName = opTypeName;
        this.orgId = orgId;
        this.orgCode = orgCode;
        this.orgName = orgName;
        this.upPrsn = upPrsn;
        this.upPrsnName = upPrsnName;
        this.upPrsnMobile = upPrsnMobile;
        this.opTime = opTime;
        this.opSeq = opSeq;
        this.memo = memo;
        this.flag = flag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrsnId() {
        return prsnId;
    }

    public void setPrsnId(Integer prsnId) {
        this.prsnId = prsnId;
    }

    public String getPrsnCode() {
        return prsnCode;
    }

    public void setPrsnCode(String prsnCode) {
        this.prsnCode = prsnCode;
    }

    public String getPrsnName() {
        return prsnName;
    }

    public void setPrsnName(String prsnName) {
        this.prsnName = prsnName;
    }

    public Integer getOpTypeId() {
        return opTypeId;
    }

    public void setOpTypeId(Integer opTypeId) {
        this.opTypeId = opTypeId;
    }

    public String getOpTypeCode() {
        return opTypeCode;
    }

    public void setOpTypeCode(String opTypeCode) {
        this.opTypeCode = opTypeCode;
    }

    public String getOpTypeName() {
        return opTypeName;
    }

    public void setOpTypeName(String opTypeName) {
        this.opTypeName = opTypeName;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Integer getUpPrsn() {
        return upPrsn;
    }

    public void setUpPrsn(Integer upPrsn) {
        this.upPrsn = upPrsn;
    }

    public String getUpPrsnName() {
        return upPrsnName;
    }

    public void setUpPrsnName(String upPrsnName) {
        this.upPrsnName = upPrsnName;
    }

    public String getUpPrsnMobile() {
        return upPrsnMobile;
    }

    public void setUpPrsnMobile(String upPrsnMobile) {
        this.upPrsnMobile = upPrsnMobile;
    }

    public Integer getOpTime() {
        return opTime;
    }

    public void setOpTime(Integer opTime) {
        this.opTime = opTime;
    }

    public Integer getOpSeq() {
        return opSeq;
    }

    public void setOpSeq(Integer opSeq) {
        this.opSeq = opSeq;
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

    @Override
    public String toString() {
        return "DocumentOplogDto{" +
                "id=" + id +
                ", prsnId=" + prsnId +
                ", prsnCode='" + prsnCode + '\'' +
                ", prsnName='" + prsnName + '\'' +
                ", opTypeId=" + opTypeId +
                ", opTypeCode='" + opTypeCode + '\'' +
                ", opTypeName='" + opTypeName + '\'' +
                ", orgId=" + orgId +
                ", orgCode='" + orgCode + '\'' +
                ", orgName='" + orgName + '\'' +
                ", upPrsn=" + upPrsn +
                ", upPrsnName='" + upPrsnName + '\'' +
                ", upPrsnMobile='" + upPrsnMobile + '\'' +
                ", opTime=" + opTime +
                ", opSeq=" + opSeq +
                ", memo='" + memo + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }
}

