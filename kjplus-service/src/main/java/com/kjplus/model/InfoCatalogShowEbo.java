package com.kjplus.model;

import java.io.Serializable;

public class InfoCatalogShowEbo implements Serializable {

    private static final long serialVersionUID = 1155344463005916788L;
    private Integer id;
    private Integer clickNum;
    private String showCode;
    private Integer catgId;
    private Integer showPos;
    private String showPicUrl;
    private String showHint;
    private String showClickUrl;
    private String flag;

    public InfoCatalogShowEbo() {
    }

    public InfoCatalogShowEbo(Integer id, Integer clickNum, String showCode, Integer catgId, Integer showPos, String showPicUrl, String showHint, String showClickUrl, String flag) {
        this.id = id;
        this.clickNum = clickNum;
        this.showCode = showCode;
        this.catgId = catgId;
        this.showPos = showPos;
        this.showPicUrl = showPicUrl;
        this.showHint = showHint;
        this.showClickUrl = showClickUrl;
        this.flag = flag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClickNum() {
        return clickNum;
    }

    public void setClickNum(Integer clickNum) {
        this.clickNum = clickNum;
    }

    public String getShowCode() {
        return showCode;
    }

    public void setShowCode(String showCode) {
        this.showCode = showCode;
    }

    public Integer getCatgId() {
        return catgId;
    }

    public void setCatgId(Integer catgId) {
        this.catgId = catgId;
    }

    public Integer getShowPos() {
        return showPos;
    }

    public void setShowPos(Integer showPos) {
        this.showPos = showPos;
    }

    public String getShowPicUrl() {
        return showPicUrl;
    }

    public void setShowPicUrl(String showPicUrl) {
        this.showPicUrl = showPicUrl;
    }

    public String getShowHint() {
        return showHint;
    }

    public void setShowHint(String showHint) {
        this.showHint = showHint;
    }

    public String getShowClickUrl() {
        return showClickUrl;
    }

    public void setShowClickUrl(String showClickUrl) {
        this.showClickUrl = showClickUrl;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "InfoCatalogShowEbo{" +
                "id=" + id +
                ", clickNum=" + clickNum +
                ", showCode='" + showCode + '\'' +
                ", catgId=" + catgId +
                ", showPos=" + showPos +
                ", showPicUrl='" + showPicUrl + '\'' +
                ", showHint='" + showHint + '\'' +
                ", showClickUrl='" + showClickUrl + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }
}
