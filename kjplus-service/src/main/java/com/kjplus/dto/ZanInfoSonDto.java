package com.kjplus.dto;

import java.io.Serializable;

public class ZanInfoSonDto implements Serializable{

    private static final long serialVersionUID = -7258566754611863574L;
    private  Integer zanId;
    private Integer  zanUid;
    private  String  mainType;
    //无点赞赋值N/否则Y
    private  String zanStatus;

    public Integer getZanId() {
        return zanId;
    }

    public void setZanId(Integer zanId) {
        this.zanId = zanId;
    }

    public Integer getZanUid() {
        return zanUid;
    }

    public void setZanUid(Integer zanUid) {
        this.zanUid = zanUid;
    }

    public String getMainType() {
        return mainType;
    }

    public void setMainType(String mainType) {
        this.mainType = mainType;
    }

    public String getZanStatus() {
        return zanStatus;
    }

    public void setZanStatus(String zanStatus) {
        this.zanStatus = zanStatus;
    }

    @Override
    public String toString() {
        return "ZanInfoSonDto{" +
                "zanId=" + zanId +
                ", zanUid=" + zanUid +
                ", mainType='" + mainType + '\'' +
                ", zanStatus='" + zanStatus + '\'' +
                '}';
    }
}
