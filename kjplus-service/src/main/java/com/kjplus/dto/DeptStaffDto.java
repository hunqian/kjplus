package com.kjplus.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 科室下的人员
 */
public class DeptStaffDto implements Serializable{

    private static final long serialVersionUID = -4799051244540490330L;
    private Integer deptId;
    private String deptName;
    private String deptCode;
    private String deptType;
    private String deptMemo;
    private List<StaffSimpleDto> stafs = new ArrayList<StaffSimpleDto>();

    public DeptStaffDto() {
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptType() {
        return deptType;
    }

    public void setDeptType(String deptType) {
        this.deptType = deptType;
    }

    public String getDeptMemo() {
        return deptMemo;
    }

    public void setDeptMemo(String deptMemo) {
        this.deptMemo = deptMemo;
    }

    public List<StaffSimpleDto> getStafs() {
        return stafs;
    }

    public void setStafs(List<StaffSimpleDto> stafs) {
        this.stafs = stafs;
    }

    @Override
    public String toString() {
        return "DeptStaffDto{" +
                "deptId=" + deptId +
                ", deptName='" + deptName + '\'' +
                ", deptCode='" + deptCode + '\'' +
                ", deptType='" + deptType + '\'' +
                ", deptMemo='" + deptMemo + '\'' +
                ", stafs=" + stafs +
                '}';
    }
}
