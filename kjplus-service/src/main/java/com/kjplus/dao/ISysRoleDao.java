package com.kjplus.dao;

import java.util.List;

import com.kjplus.model.UserRoleEbo;
import com.kjplus.model.inner.SysRoleInnerEbo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kjplus.model.SysRoleEbo;
import com.ybk.exception.DataException;

@Repository("roleDao")
public interface ISysRoleDao {

	/**
	 * 通过code查询
	 * 
	 * @param code
	 *            t_sys_role 的code 编号
	 * @return 返回SysRoleEbo对象
	 * @throws DataException
	 */
	public SysRoleEbo getRoleByCode(@Param("prjCode") String code) throws DataException;

	/**
	 *
	 * @param id
	 * @return
	 * @throws DataException
	 */
	public SysRoleEbo getRoleById(@Param("id") int id) throws DataException;

	/**
	 * 添加角色
	 * 
	 * @param role
	 *            SysRoleEbo 对象
	 * @return
	 * @throws DataException
	 */
	public void addRole(SysRoleEbo role) throws DataException;

	/**
	 * 角色列表
	 * 
	 * @param type
	 *            类型
	 * @param code
	 *            编号
	 * @param name
	 *            名称
	 * @param flag
	 *            是否有效
	 * @param page
	 *            起始页
	 * @param paging
	 *            结束页
	 * 
	 * @return 返回List<SysRoleEbo>列表
	 * 
	 * @throws DataException
	 */
	public List<SysRoleEbo> listRole(@Param("type") String type, @Param("code") String code,
			@Param("name") String name, @Param("flag") String flag, @Param("page") int page, @Param("paging") int paging)
			throws DataException;

	/**
	 * 修改角色flag状态
	 * 
	 * @param flag
	 *            状态是否有效 Y有效/N无效
	 * @throws DataException
	 */
	public void updateRoleFlag(@Param("roleId") int roleId, @Param("prjFlag") String flag) throws DataException;


	/**
	 * 罗列当前用户所对应的角色
	 *
	 * @param uid
	 * 		用户id
	 * @param roleId
	 * 		角色id
	 * @return
	 * @throws DataException
	 */
	public List<SysRoleInnerEbo> listUserRole(@Param("uid") int uid,@Param("roleId") int roleId,@Param("type") String type)throws DataException;

	/**
	 * 罗列当前用户所已经对应的角色
	 *
	 * @param uid
	 * 		用户id
	 * @param roleId
	 * 		角色id
	 * @return
	 * @throws DataException
	 */
	public List<SysRoleInnerEbo> listRelatedUserRole(@Param("uids") String uids,@Param("type") String type)throws DataException;

	/**
	 *查询当前用户的所有角色对应
	 *
	 * @param uid
	 * 用户id
	 * @param roleId
	 * 角色id
	 * @return
	 * @throws DataException
	 */
	public List<UserRoleEbo> listUserRoleAll(@Param("uid") int uid,@Param("roleId") int roleId)throws DataException;


	/**
	 * 添加用户角色对应关系
	 *
	 * @param userRole
	 * 	传入UserRoleEbo对象
	 * @throws DataException
	 */
	public void  addUserRole(UserRoleEbo userRole)throws DataException;

	/**
	 * 添加用户角色全部对应关系
	 *
	 * @param userRole
	 * 	传入UserRoleEbo对象
	 * @throws DataException
	 */
	public void  addUserRoleAll(List<UserRoleEbo> userRole)throws DataException;


	/**
	 *删除用户所对应的所有角色
	 * @param uid
	 * 		用户id
	 * @throws DataException
	 */
	public void delUserRoleAll(@Param("uid")int uid)throws DataException;

}
