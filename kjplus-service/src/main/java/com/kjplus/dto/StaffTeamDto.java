package com.kjplus.dto;

import java.io.Serializable;

public class StaffTeamDto  implements Serializable{

    private static final long serialVersionUID = 4314812288675845790L;
    private String teamName;
    private String teamMemo;
    private String staffName;
    private String deptId;
    private String typeName;

    public StaffTeamDto() {
        super();
    }
    
    public StaffTeamDto(String teamName, String teamMemo, String staffName, String deptId, String typeName) {
		super();
		this.teamName = teamName;
		this.teamMemo = teamMemo;
		this.staffName = staffName;
		this.deptId = deptId;
		this.typeName = typeName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamMemo() {
        return teamMemo;
    }

    public void setTeamMemo(String teamMemo) {
        this.teamMemo = teamMemo;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }
}
