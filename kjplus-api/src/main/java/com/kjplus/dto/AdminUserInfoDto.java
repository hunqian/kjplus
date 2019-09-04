package com.kjplus.dto;

import java.io.Serializable;

//token对应 系统中用户各功能关系
public class AdminUserInfoDto implements Serializable{


    private static final long serialVersionUID = 4140368473237558454L;
    //系统中为用户分配的token
    private String openToken;
    //用户在系统中的管理账户id
    private Integer adUid;
    //用戶的医生id 编号
    private Integer staffId;
    private String staffCode;

    //用戶的所属的部门id 编号
    private Integer deptId;
    private String deptCode;
    //用戶的所属的组织
    private Integer orgId;
    private Integer orgCode;

    //超时设置  存入token数据库的时间
    private Integer timeOut;

    public AdminUserInfoDto(Integer adUid, Integer staffId, Integer deptId, Integer orgId) {
        this.adUid = adUid;
        this.staffId = staffId;
        this.deptId = deptId;
        this.orgId = orgId;
    }

    public AdminUserInfoDto(Integer adUid, Integer orgId) {
        this.adUid = adUid;
        this.orgId = orgId;
    }

    public AdminUserInfoDto(Integer adUid) {
        this.adUid = adUid;
    }

    public AdminUserInfoDto() {
    }

    public AdminUserInfoDto(String openToken, Integer adUid, Integer staffId, String staffCode, Integer deptId, String deptCode, Integer orgId, Integer orgCode) {
        this.openToken = openToken;
        this.adUid = adUid;
        this.staffId = staffId;
        this.staffCode = staffCode;
        this.deptId = deptId;
        this.deptCode = deptCode;
        this.orgId = orgId;
        this.orgCode = orgCode;
    }

    public String getOpenToken() {
        return openToken;
    }

    public void setOpenToken(String openToken) {
        this.openToken = openToken;
    }

    public Integer getAdUid() {
        return adUid;
    }

    public void setAdUid(Integer adUid) {
        this.adUid = adUid;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(Integer orgCode) {
        this.orgCode = orgCode;
    }

    public Integer getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Integer timeOut) {
        this.timeOut = timeOut;
    }

    @Override
    public String toString() {
        return "AdminUserInfoDto{" +
                "openToken='" + openToken + '\'' +
                ", adUid=" + adUid +
                ", staffId=" + staffId +
                ", staffCode='" + staffCode + '\'' +
                ", deptId=" + deptId +
                ", deptCode=" + deptCode +
                ", orgId=" + orgId +
                ", orgCode=" + orgCode +
                ", timeOut=" + timeOut +
                '}';
    }
}

