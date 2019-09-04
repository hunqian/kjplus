package com.kjplus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kjplus.model.SysRoleFuncEbo;
import com.ybk.exception.DataException;

@Repository("roleFuncDao")
public interface ISysRoleFuncDao {

	/**
	 * 通过角色id 菜单id 查询对应
	 * 
	 * @param roleId
	 *            角色id
	 * @param funcId
	 *            菜单id
	 * @return 返回SysRoleFuncEbo对象
	 * @throws DataException
	 */
	public SysRoleFuncEbo getRoleFuncByrfId(@Param("roleId") int roleId, @Param("funcId") int funcId)
			throws DataException;

	/**
	 * 添加角色菜单对应关系
	 * 
	 * @param rf
	 *            传入SysRoleFuncEbo 对象
	 * @throws DataException
	 */
	public void addRoleFuncLine(SysRoleFuncEbo rf) throws DataException;

	/**
	 * 删除角色id 对应的所有菜单
	 * 
	 * @param roleId
	 *            角色id
	 * @throws DataException
	 */
	public void delAllRoleFunc(@Param("roleId") int roleId) throws DataException;

	/**
	 * 通过id 删除 角色菜单对应关系
	 * 
	 * @param id
	 *            t_sys_role_func表的id
	 * @throws DataException
	 */
	public void delRoleFuncById(@Param("id") int id) throws DataException;

	/**
	 * 通过角色id 菜单路径 获取对应的 关联表id
	 * 
	 * @param roleId
	 *            角色id
	 * @param path
	 *            菜单路径
	 * @return
	 * @throws DataException
	 */
	public List<Integer> listRoleFuncId(@Param("roleId") int roleId, @Param("path") String path) throws DataException;
}
