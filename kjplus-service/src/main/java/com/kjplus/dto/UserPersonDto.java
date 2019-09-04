package com.kjplus.dto;

import java.util.ArrayList;
import java.util.List;

public class UserPersonDto {
    private Integer uid;
    private String userName;
    //该用户的签约信息
    private List<PersonServiceDto> prsnServices=new ArrayList<PersonServiceDto>();

    public UserPersonDto() {
    }

    public UserPersonDto(Integer uid, String userName, List<PersonServiceDto> prsnServices) {
        this.uid = uid;
        this.userName = userName;
        this.prsnServices = prsnServices;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<PersonServiceDto> getPrsnServices() {
        return prsnServices;
    }

    public void setPrsnServices(List<PersonServiceDto> prsnServices) {
        this.prsnServices = prsnServices;
    }

    @Override
    public String toString() {
        return "UserPersonDto{" +
                "uid=" + uid +
                ", userName='" + userName + '\'' +
                ", prsnServices=" + prsnServices +
                '}';
    }
}
