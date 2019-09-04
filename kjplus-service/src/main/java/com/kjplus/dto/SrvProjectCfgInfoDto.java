package com.kjplus.dto;

import java.io.Serializable;

//服务项目配置信息
public class SrvProjectCfgInfoDto implements Serializable{

    private static final long serialVersionUID = 5375704393179053905L;
    private Integer id;
    private String isNorm;

    //标签表字段
    private Integer tagId;
    private String tagName;

    //组织表字段
    private Integer orgId;
    private String orgCode;
    private String orgName;

    //服务项目表字段
    private Integer prjId;
    private String prjCode;
    private String prjName;
    private String prjTypeName;
    private String prjFlag;

    public SrvProjectCfgInfoDto() {
    }

    public SrvProjectCfgInfoDto(Integer id, String isNorm, Integer tagId, String tagName, Integer orgId, String orgCode, String orgName, Integer prjId, String prjCode, String prjName, String prjTypeName, String prjFlag) {
        this.id = id;
        this.isNorm = isNorm;
        this.tagId = tagId;
        this.tagName = tagName;
        this.orgId = orgId;
        this.orgCode = orgCode;
        this.orgName = orgName;
        this.prjId = prjId;
        this.prjCode = prjCode;
        this.prjName = prjName;
        this.prjTypeName= prjTypeName;
        this.prjFlag = prjFlag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsNorm() {
        return isNorm;
    }

    public void setIsNorm(String isNorm) {
        this.isNorm = isNorm;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
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

    public Integer getPrjId() {
        return prjId;
    }

    public void setPrjId(Integer prjId) {
        this.prjId = prjId;
    }

    public String getPrjCode() {
        return prjCode;
    }

    public void setPrjCode(String prjCode) {
        this.prjCode = prjCode;
    }

    public String getName() {
        return prjName;
    }

    public void setName(String prjName) {
        this.prjName = prjName;
    }

    public String getPrjTypeName() {
        return prjTypeName;
    }

    public void setPrjTypeName(String prjTypeName) {
        this.prjTypeName = prjTypeName;
    }

    public String getPrjFlag() {
        return prjFlag;
    }

    public void setPrjFlag(String prjFlag) {
        this.prjFlag = prjFlag;
    }

    @Override
    public String toString() {
        return "SrvProjectCfgInfoDto{" +
                "id=" + id +
                ", isNorm='" + isNorm + '\'' +
                ", tagId=" + tagId +
                ", tagName='" + tagName + '\'' +
                ", orgId=" + orgId +
                ", orgCode='" + orgCode + '\'' +
                ", orgName='" + orgName + '\'' +
                ", prjId=" + prjId +
                ", prjCode='" + prjCode + '\'' +
                ", name='" + prjName + '\'' +
                ", prjTypeName='" + prjTypeName + '\'' +
                ", prjFlag='" + prjFlag + '\'' +
                '}';
    }
}

