package com.kjplus.eto;

import com.kjplus.annotation.Validation;

public class UserRoleEto  {

    @Validation
    private Integer uid;
    @Validation
    private Integer roleId;

    public UserRoleEto() {
    }

    public UserRoleEto( Integer uid, Integer roleId) {

        this.uid = uid;
        this.roleId = roleId;
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

}

