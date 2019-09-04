package com.kjplus.model;

import java.io.Serializable;

public class SrvProjectCfgEbo implements Serializable{

    private static final long serialVersionUID = 2560596006444090887L;
    private Integer id;
    private Integer prjId;
    private Integer tagId;
    private Integer orgId;
    private String isNorm;

    public SrvProjectCfgEbo() {
    }

    public SrvProjectCfgEbo(Integer id, Integer prjId, Integer tagId, Integer orgId, String isNorm) {
        this.id = id;
        this.prjId = prjId;
        this.tagId = tagId;
        this.orgId = orgId;
        this.isNorm = isNorm;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrjId() {
        return prjId;
    }

    public void setPrjId(Integer prjId) {
        this.prjId = prjId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getIsNorm() {
        return isNorm;
    }

    public void setIsNorm(String isNorm) {
        this.isNorm = isNorm;
    }

    @Override
    public String toString() {
        return "SrvProjectCfgEto{" +
                "id=" + id +
                ", prjId=" + prjId +
                ", tagId=" + tagId +
                ", orgId=" + orgId +
                ", isNorm='" + isNorm + '\'' +
                '}';
    }
}
