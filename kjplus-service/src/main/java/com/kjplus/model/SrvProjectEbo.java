package com.kjplus.model;

import java.io.Serializable;

public class SrvProjectEbo implements Serializable {

    private static final long serialVersionUID = 770983635824629775L;


    private Integer id;
    private String code;
    private String name;
    private Integer typeId;
    private String flag;


    public SrvProjectEbo() {
    }

    public SrvProjectEbo(Integer id, String code, String name, Integer typeId, String flag) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.typeId = typeId;
        this.flag = flag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "SrvProjectEbo{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", typeId=" + typeId +
                ", flag='" + flag + '\'' +
                '}';
    }
}
