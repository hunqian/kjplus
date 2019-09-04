package com.kjplus.service;

import java.util.List;

import com.kjplus.dto.DepartmentDto;
import com.kjplus.eto.DepartmentEto;
import com.kjplus.model.DepartmentEbo;
import com.ybk.exception.DataException;

public interface IDeptService {

	// 通过code获取部门
	public DepartmentEbo getDepartmentByCode(String code) throws DataException;

	// 通过deptId获取部门
	public DepartmentEbo getDepartmentById(int deptId) throws DataException;

	// 添加部门
	public DepartmentEbo addDepartment(DepartmentEto dept) throws DataException;

	// 通过部门名称查询部门列表
	public List<DepartmentDto> listDepartmentDto(String deptName,String deptType, int orgid,String flag,int page,int paging) throws DataException;
	
	//查询部门总数
	public int getTotalDepartment(String deptName,int orgid,String flag) throws DataException;
	
	//修改部门信息
	public void updateDept(DepartmentEbo departmentEbo) throws DataException;
	
}
