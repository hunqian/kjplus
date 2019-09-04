package com.kjplus.model.inner;

import java.io.Serializable;

public class ZanInfoInnerEbo  implements Serializable{


    private static final long serialVersionUID = 3323406224022334080L;
    private  Integer zanId;
    private Integer  zanUid;
    private  Integer infoId;
    private  String  infoCode;
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

    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    public String getinfoCode() {
        return infoCode;
    }

    public void setinfoCode(String infoCode) {
        this.infoCode = infoCode;
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
        return "ZanInfoInnerEbo{" +
                "zanId=" + zanId +
                ", zanUid=" + zanUid +
                ", infoId=" + infoId +
                ", infoCode='" + infoCode + '\'' +
                ", mainType='" + mainType + '\'' +
                ", zanStatus='" + zanStatus + '\'' +
                '}';
    }
}
