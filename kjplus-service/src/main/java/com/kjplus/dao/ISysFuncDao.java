package com.kjplus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kjplus.model.SysFuncEbo;
import com.ybk.exception.DataException;

/**
 * 
 * @author niuzhiwei t_sys_func dao层映射器
 */
@Repository("funcDao")
public interface ISysFuncDao {

	/**
	 * 通过funcId获取func对象
	 * 
	 * @param funcId
	 *            菜单id
	 * @return 返回 SysFuncEbo对象
	 * @throws DataException
	 */
	public SysFuncEbo getFuncById(@Param("funcId") int funcId) throws DataException;

	/**
	 * 通过menu获取func对象
	 * 
	 * @param menu
	 *            菜单地址
	 * @param type
	 *            类型 如('A')
	 * @return 返回 SysFuncEbo 对象
	 * @throws DataException
	 */
	public SysFuncEbo getFuncByMenu(@Param("menu") String menu, @Param("type") String type) throws DataException;

	/**
	 * 添加一个菜单
	 * 
	 * @param func
	 *            SysFuncEbo参数未model的ebo对象
	 * @throws DataException
	 */
	public void addFunc(SysFuncEbo func) throws DataException;

	/**
	 * 更新菜单路径
	 * 
	 * @param funcId
	 *            t_sys_func表的id
	 * @param path
	 *            菜单的路径
	 * @throws DataException
	 */
	public void updateFuncPath(@Param("funcId") int funcId, @Param("path") String path) throws DataException;

	/**
	 * 
	 * @param funcId
	 *            t_sys_func表的id
	 * @param flag
	 *            状态 Y有效/N无效
	 * @throws DataException
	 */
	public void updateFuncFlag(@Param("funcId") int funcId, @Param("flag") String flag) throws DataException;

	/**
	 * 通过code获取func对象
	 * 
	 * @param code
	 *            t_sys_func表的编号
	 * @return 返回SysFuncEbo对象
	 * @throws DataException
	 */
	public SysFuncEbo getFuncByCode(@Param("code") String code) throws DataException;

	/**
	 * 罗列菜单
	 * 
	 * @param type
	 *            类型 如('A')
	 * @param funcPath
	 *            菜单的路径
	 * @return 返回集合对象List<SysFuncEbo>
	 * @throws DataException
	 */
	public List<SysFuncEbo> findlistFunc(@Param("type") String type, @Param("funcPath") String funcPath) throws DataException;

	/**
	 * 角色和菜单的对应关系
	 * 
	 * @param roleId
	 *            角色的id
	 * @return 返回集合对象List<SysFuncEbo>
	 * @throws DataException
	 */
	public List<SysFuncEbo> listRoleFunc(@Param("roleId") int roleId) throws DataException;

	/**
	 * 
	 * @param uid
	 *            t_user表的id
	 * @return 返回List<SysFuncEbo> 列表
	 * @throws DataException
	 */
	public List<SysFuncEbo> listUserFunc(@Param("uid") int uid, @Param("flag") String flag) throws DataException;

	/**
	 * 通过菜单路劲获取对应的id
	 * 
	 * @param path
	 *            菜单路径
	 * @return 返回List<Integer>列表
	 * @throws DataException
	 */
	public List<Integer> listFuncByPath(@Param("funcPath") String funcPath) throws DataException;

}
