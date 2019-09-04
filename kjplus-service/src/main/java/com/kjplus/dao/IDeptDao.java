package com.kjplus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kjplus.dto.DepartmentDto;
import com.kjplus.model.DepartmentEbo;
import com.ybk.exception.DataException;

@Repository("deptDao")
public interface IDeptDao {

	/**
	 * 通过编号查询部门对象
	 * 
	 * @param code
	 *            编号
	 * @return 返回DepartmentEbo对象
	 * @throws DataException
	 */
	public DepartmentEbo getDepartmentByCode(@Param("code") String code) throws DataException;

	/**
	 * 通过部门id查询部门对象
	 *
	 * @param deptId
	 *            部门id
	 * @return 返回DepartmentEbo对象
	 * @throws DataException
	 */
	public DepartmentEbo getDepartmentById(@Param("deptId") int deptId) throws DataException;

	/**
	 * 添加部门
	 *
	 * @param DepartmentEbo
	 *            传入DepartmentEbo对象
	 * @throws DataException
	 */
	public void addDepartment(DepartmentEbo dept) throws DataException;

	/**
	 * 查询部门
	 * 
	 * @param deptName
	 *            部门名称
	 * @return 返回List<DepartmentDto>
	 * @throws DataException
	 */
	public List<DepartmentDto> listDepartmentDto(@Param("deptName") String deptName, @Param("deptType") String deptType, @Param("orgid") int orgid, @Param("flag") String flag,@Param("page") int page,@Param("paging") int paging )
			throws DataException;
	/**
	 * 查询部门总数
	 * @param deptName
	 * 			部门名称
	 * @param orgid
	 * 			组织ID
	 * @return int
	 */
	public int getTotalDept(@Param("deptName") String deptName, @Param("orgid") int orgid, @Param("flag") String flag) throws DataException;

	/**
	 * 修改部门信息
	 * @param departmentEbo
	 * @throws DataException
	 */
	public void updateDepartment(DepartmentEbo departmentEbo) throws DataException;



}
