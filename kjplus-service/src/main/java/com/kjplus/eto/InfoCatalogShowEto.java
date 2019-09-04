package com.kjplus.eto;

import com.kjplus.constant.Constant;

public class InfoCatalogShowEto {

    private Integer clickNum=0;
    private String showCode;
    private Integer catgId;
    private Integer showPos=1;
    private String showPicUrl;
    private String showHint;
    private String showClickUrl;
    private String flag= Constant.FLAG_YES;

    public InfoCatalogShowEto() {
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
}
