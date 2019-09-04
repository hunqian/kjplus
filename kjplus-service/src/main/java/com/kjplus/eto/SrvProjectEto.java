package com.kjplus.eto;

import com.kjplus.annotation.Validation;
import com.kjplus.constant.Constant;

public class SrvProjectEto {

    public static final int CODE_LEN=8;

    private String code;
    @Validation
    private String name;
    @Validation
    private String typeCode;
    private String flag= Constant.FLAG_YES;

    public SrvProjectEto() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
