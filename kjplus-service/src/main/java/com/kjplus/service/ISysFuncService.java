package com.kjplus.service;

import java.util.List;
import java.util.Map;

import com.kjplus.eto.UserRoleEto;
import com.kjplus.model.*;
import com.kjplus.dto.FuncDto;
import com.kjplus.eto.SysFuncEto;
import com.kjplus.model.inner.SysRoleInnerEbo;
import com.ybk.exception.DataException;

public interface ISysFuncService {

	// 获得对象
	public SysFuncEbo getFuncById(int funcId) throws DataException;

	// 通过code获取function
	public SysFuncEbo getFuncByCode(String code) throws DataException;

	// 通过menu获取function
	public SysFuncEbo getFuncByMenu(String menu, String type) throws DataException;

	// 添加func
	public SysFuncEbo addFunc(SysFuncEto f) throws DataException;

	// 获得用户的默认第一个访问页面
	public String getDefaultFunc(int uid) throws DataException;

	// 更新路径
	public void updateFuncPath(int funcId, String path) throws DataException;

	// 更新flag
	public void updateFuncFlag(int funcId, String flag) throws DataException;

	// 罗列菜单
	public List<FuncDto> listFunc(int pid, String type) throws DataException;

	// 通过code获取 role
	public SysRoleEbo getRole(String code) throws DataException;

	// 罗列角色对应的菜单
	public List<FuncDto> listRoleFunc(int roleId) throws DataException;

	// 罗列用户对应的角色对应的菜单
	public List<FuncDto> listUserFunc(int uid) throws DataException;

	// 需要做唯一性判断
	public void addRoleFuncLine(int roleId, int funcId) throws DataException;

	// 增加菜单到role
	public void addRoleFunc(int roleid, int funcid) throws DataException;

	// 删除特定role所对应的所有funcid
	public void delAllRoleFunc(int roleId) throws DataException;

	// 删除菜单到role所对应的funcid，包含自身
	public void delRoleFunc(int roleId, int funcid) throws DataException;

	// 角色里列表（分页）
	public List<SysRoleEbo> listRole(String type, String name, String flag, int page, int paging) throws DataException;

	// 列表role
	public List<SysRoleEbo> listRole(String type, String code, String name, String flag) throws DataException;

	//用户所对应的全部角色，包含已定义和未定义的关联角色
	public List<SysRoleInnerEbo> listUserRole(int uid, int roleId, String type)throws DataException;

	//用户所对应的全部角色，包含已定义关联角色
	public Map<Integer,List<SysRoleInnerEbo>> listRelatedUserRole(List<Integer> uids, String type)throws DataException ;

	//添加一个用户和角色的对应关系
	public void addUserRole(UserRoleEto userRole)throws DataException;

	//添加一组uid和roles对应关系
	//purgePrevious=true是否清除已有的关系
	public void addUserRoles(int uid, List<Integer> roleIds, boolean purgePrevious)throws DataException;
}
