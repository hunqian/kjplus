package com.kjplus.model;

import java.io.Serializable;

public class UserRoleEbo implements Serializable {

    private static final long serialVersionUID = -8111285306804772496L;
    private Integer id;
    private Integer uid;
    private Integer roleId;

    public UserRoleEbo() {
    }

    public UserRoleEbo(Integer id, Integer uid, Integer roleId) {
        this.id = id;
        this.uid = uid;
        this.roleId = roleId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "UserRoleEbo{" +
                "id=" + id +
                ", uid=" + uid +
                ", roleId=" + roleId +
                '}';
    }
}
