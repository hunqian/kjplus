package com.kjplus.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ZanInfoParentDto implements Serializable{

    private static final long serialVersionUID = -2027184618713894681L;
    private  Integer infoId;
    private  String  infoCode;
    private List<ZanInfoSonDto> zanItem=new ArrayList<ZanInfoSonDto>();

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

    public List<ZanInfoSonDto> getZanItem() {
        return zanItem;
    }

    public void setZanItem(List<ZanInfoSonDto> zanItem) {
        this.zanItem = zanItem;
    }

    @Override
    public String toString() {
        return "ZanInfoParentDto{" +
                "infoId=" + infoId +
                ", infoCode='" + infoCode + '\'' +
                ", zanItem=" + zanItem +
                '}';
    }
}
