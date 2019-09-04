package com.kjplus.dao;

import java.util.List;

import com.kjplus.model.inner.DeptStaffInnerEbo;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kjplus.dto.StaffDto;
import com.kjplus.model.*;
import com.ybk.exception.DataException;

@Repository("staffDao")
public interface IStaffDao {
	/**
	 * 通过编号 查询staff对象
	 * 
	 * @param code
	 *            编号
	 * @return 返回StaffEbo对象
	 * @throws DataException
	 * 
	 */
	public StaffEbo getStaffByCode(@Param("code") String code) throws DataException;

	/**
	 * 通过组织id 查询staff对象
	 * 
	 * @param staffId
	 *            编号
	 * @return 返回StaffEbo对象
	 * @throws DataException
	 */
	public StaffEbo getStaffById(@Param("staffId") int staffId) throws DataException;

	/**
	 * 添加组织
	 * 
	 * @param staff
	 *            传入StaffEbo对象
	 * @throws DataException
	 */
	public void addStaff(StaffEbo staff) throws DataException;

	/**
	 * 添加员工组织对应
	 * 
	 * @param sd
	 *            传入StaffDeptEbo对象
	 * @throws DataException
	 */
	public void addStaffDept(StaffDeptEbo sd) throws DataException;

	/**
	 * 添加组织
	 * 
	 * @param staffName
	 *            部门名称
	 * @param orgId
	 *            组织id
	 * @param deptId
	 *            部门id
	 * @param typeId
	 *            类型id
	 * @throws DataException
	 */
	public List<StaffDto> listStaffDto(@Param("staffName") String staffName, @Param("orgId") int orgId, @Param("deptId") int deptId, @Param("typeId") int typeId)
			throws DataException;

	/**
	 * 获得staff和dept的对应关系
	 * 
	 * @param deptId
	 *            部门id
	 * @param staffId
	 *            职工id
	 * @throws DataException
	 */
	public List<StaffDeptEbo> listStaffDeptMapEbo(@Param("staffId") int staffId, @Param("deptId") int deptId) throws DataException;

	/**
	 * TODO 将数据全部查出后处理 ，建议将部门与团队的获取方式分开处理 获得staff和dept的对应关系
	 * 
	 * @param orgId
	 *            部门id
	 * @throws DataException
	 */
	public List<DeptStaffInnerEbo> listStaffDeptDto(@Param("orgId") int orgId, @Param("deptId") int deptId, @Param("staffName") String staffName,
			@Param("flag") String flag, @Param("status") String status, @Param("page") int page, @Param("paging") int paging) throws DataException;

	/**
	 * 获得staff总数
	 * 
	 * @param 组织ID
	 *            部门ID
	 * @return 返回staff总数
	 * @throws DataException
	 */
	public int getTotalStaffDto(@Param("orgId") int orgId, @Param("deptId") int deptId,@Param("flag") String flag, @Param("status") String status) throws DataException;

	// 更新
	// public void updateStaff(@Param("staffId") int staffId,StaffEbo
	// staff)throws DataException;
	public void updateStaff(StaffEbo staff) throws DataException;

}
