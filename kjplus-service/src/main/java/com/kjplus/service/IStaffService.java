package com.kjplus.service;

import java.util.List;

import com.kjplus.dto.DeptStaffDto;
import com.kjplus.dto.StaffDto;
import com.kjplus.eto.StaffEto;
import com.kjplus.model.StaffDeptEbo;
import com.kjplus.model.StaffEbo;
import com.ybk.exception.DataException;

public interface IStaffService {

	// 通过code获取staff
	public StaffEbo getStaffByCode(String code) throws DataException;

	// 通过satffId获取staff
	public StaffEbo getStaffById(int staffId) throws DataException;

	// 添加staff
	public StaffEbo addStaff(StaffEto staff) throws DataException;

	// 通过组织ID，部门ID查询staff列表
	public List<StaffDto> listStaffDto(int orgId, int deptId,String staffName,String flag,String status,int page,int paging) throws DataException;

	// 获得orgid下所有的,部门id部门列表,以及该部门所对应的staff列表
	// fetchDeptHiarch:是否返回层级树状部门
	// omitZeroStaff：是否忽略无staff部门
	public List<DeptStaffDto> listDeptStaffDto(int orgId, String deptType, boolean fetchDeptHiarch,
			boolean omitZeroStaff) throws DataException;

	// 通过staff名称,部门id，类型id，组织id查询部门列表
	public int getTotalStaffDto(int orgId, int deptId,String flag,String status) throws DataException;

	// 更新
	//public void updateStaff(int staffId,StaffEbo staff)throws DataException;
	public void updateStaff(StaffEbo staff)throws DataException;

	/**
	 * 获得医生和团队的对应关系
	 * 
	 * @param deptId
	 *            部门id
	 * @param staffId
	 *            职工id
	 * @throws DataException
	 */
	public List<StaffDeptEbo> listStaffDeptMapEbo(int staffId, int deptId) throws DataException;
	
}
