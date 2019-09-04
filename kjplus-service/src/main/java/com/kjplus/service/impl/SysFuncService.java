package com.kjplus.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ybk.basic.util.Util;

import com.kjplus.annotation.BizType;
import com.kjplus.annotation.MethodType;
import com.kjplus.annotation.PerformanceLog;
import com.kjplus.constant.Constant;
import com.kjplus.dao.ISysFuncDao;
import com.kjplus.dao.ISysRoleDao;
import com.kjplus.dao.ISysRoleFuncDao;
import com.kjplus.dto.FuncDto;
import com.kjplus.eto.SysFuncEto;
import com.kjplus.eto.UserRoleEto;
import com.kjplus.model.SysFuncEbo;
import com.kjplus.model.SysRoleEbo;
import com.kjplus.model.SysRoleFuncEbo;
import com.kjplus.model.UserRoleEbo;
import com.kjplus.model.inner.SysRoleInnerEbo;
import com.kjplus.service.ISysFuncService;
import com.kjplus.service.IUserService;
import com.kjplus.util.DataValUtil;
import com.ybk.exception.DataException;

@Service("funcService")
public class SysFuncService implements ISysFuncService {

	@Autowired
	private ISysFuncDao funcDao;
	@Autowired
	private ISysRoleDao roleDao;
	@Autowired
	private ISysRoleFuncDao roleFuncDao;
	@Autowired
	private IUserService userService;

	private static Logger logger = Logger.getLogger(SysFuncService.class);

	public SysFuncEbo getFuncById(int funcId) throws DataException {
		if (funcId <= 0)
			return null;
		SysFuncEbo func = null;

		try {
			func = funcDao.getFuncById(funcId);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return func;
	}

	public SysFuncEbo getFuncByCode(String code) throws DataException {
		if (Util.isNull(code))
			return null;
		SysFuncEbo func = null;
		try {
			func = funcDao.getFuncByCode(code);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return func;
	}

	// 根据menu获得func
	public SysFuncEbo getFuncByMenu(String menu, String type) throws DataException {
		if (Util.isNull(menu)) {
			return null;
		}
		SysFuncEbo func = null;
		try {
			func = funcDao.getFuncByMenu(menu, type);
			if (func == null)
				func = funcDao.getFuncByMenu("/" + menu, type);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return func;
	}

	// 添加菜单
	public SysFuncEbo addFunc(SysFuncEto f) throws DataException {
		if (f == null)
			throw new DataException("[err]空菜单对象！");

		if (Util.isNull(f.getType()) || Util.isNull(f.getName()) || Util.isNull(f.getMenu()))
			throw new DataException("[err]菜单类型/名称/菜单链接都不能为空！");

		SysFuncEbo f2 = null;
		String code = f.getCode();
		if (Util.isNull(code)) {
			code = Util.genSessCode(SysFuncEto.CODE_LEN);
			f2 = getFuncByCode(code);
			while (f2 != null) {
				code = Util.genSessCode(SysFuncEto.CODE_LEN);
				f2 = getFuncByCode(code);
			}
		} else {
			f2 = getFuncByCode(code);
			if (f2 != null)
				return f2;
		}
		String path = "";
		if (f.getPid() > 0) {
			f2 = getFuncById(f.getPid());
			if (f2 == null)
				throw new DataException("[err]pid=" + f.getPid() + "不存在！");
			path = f2.getFuncPath();
		}
		SysFuncEbo func = new SysFuncEbo();
		func.setCode(code);
		func.setFlag(Constant.FLAG_YES);
		func.setMenu(f.getMenu());
		func.setName(f.getName());
		func.setPid(f.getPid());
		func.setType(f.getType());
		func.setMenuSeq(f.getMenuSeq());
		funcDao.addFunc(func);
		logger.warn("添加的数据库id为：" + func.getId());
		if (Util.isNotNull(path))
			path += func.getId() + "|";
		else
			path = "|" + func.getId() + "|";
		updateFuncPath(func.getId(), path);
		return func;
	}

	// 更新路径
	public void updateFuncPath(int funcId, String path) throws DataException {
		if (Util.isNull(path))
			return;
		if (funcId <= 0)
			throw new DataException("[err]修改菜单路径的funcId不能空，ID为:" + funcId);
		try {
			funcDao.updateFuncPath(funcId, path);
			logger.info("修改菜单路径成功，修改的ID为:" + funcId);
			logger.info("修改菜单路径成功，修改的路径为:" + path);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	// 更新flag
	public void updateFuncFlag(int funcId, String flag) throws DataException {
		if (funcId <= 0)
			return;
		if (Util.isNull(flag) || flag.length() > 1)
			return;
		funcDao.updateFuncFlag(funcId, flag);
	}

	// 获得用户的默认第一个访问页面
	public String getDefaultFunc(int uid) throws DataException {
		List<FuncDto> funcs = listUserFunc(uid);
		if (funcs.size() == 0)
			return null;
		// 目前只处理二级，应该是适用的
		for (FuncDto func : funcs) {
			if (func.getSubs().size() > 0)
				return func.getSubs().get(0).getMenu();
		}
		return null;
	}

	// 罗列菜单
	@PerformanceLog(methodType = MethodType.SELECT, bizType = BizType.PERSON)
	public List<FuncDto> listFunc(int pid, String type) throws DataException {
		SysFuncEbo func = null;
		List<SysFuncEbo> funcs = null;
		if (pid > 0)
			func = funcDao.getFuncById(pid);
		if (func != null) {
			funcs = funcDao.findlistFunc(type, func.getFuncPath());
		} else {
			funcs = funcDao.findlistFunc(type, "");
		}
		FuncDto fd = null;
		List<FuncDto> funcDtos = new ArrayList<FuncDto>();
		Hashtable<Integer, FuncDto> funcHash = new Hashtable<Integer, FuncDto>();
		for (int i = 0; i < funcs.size(); i++) {
			fd = new FuncDto();
			fd.setCode(funcs.get(i).getCode());
			fd.setFlag(funcs.get(i).getFlag());
			fd.setId(funcs.get(i).getId());
			fd.setMenu(funcs.get(i).getMenu());
			fd.setName(funcs.get(i).getName());
			if (!funcHash.containsKey(funcs.get(i).getPid())) {
				funcDtos.add(fd);
			} else {
				funcHash.get(funcs.get(i).getPid()).getSubs().add(fd);
			}
			funcHash.put(funcs.get(i).getId(), fd);
		}
		return funcDtos;
	}

	// 获得role
	public SysRoleEbo getRole(String code) throws DataException {
		if (Util.isNull(code))
			return null;
		return roleDao.getRoleByCode(code);
	}

	// 增加role
	public SysRoleEbo addRole(String type, String name) throws DataException {
		return addRole(type, null, name);
	}

	// 增加role
	public SysRoleEbo addRole(String type, String code, String name) throws DataException {

		if (Util.isNull(type) || Util.isNull(name))
			throw new DataException("[err]角色类型/名称都不能为空！");

		int CODE_LEN = 6;
		SysRoleEbo r2 = null;
		if (Util.isNull(code)) {
			code = Util.genSecID(CODE_LEN);
			r2 = getRole(code);
			while (r2 != null) {
				code = Util.genSecID(CODE_LEN);
				r2 = getRole(code);
			}
		} else {
			r2 = getRole(code);
			if (r2 != null)
				return r2;
		}
		SysRoleEbo r = new SysRoleEbo();
		r.setCode(code);
		r.setFlag(Constant.FLAG_YES);
		r.setName(name);
		r.setType(type);
		roleDao.addRole(r);
		return r;
	}

	// 列表role
	public List<SysRoleEbo> listRole(String type, String code, String name, String flag) throws DataException {

		return roleDao.listRole(type, code, name, flag, 0, Integer.MAX_VALUE);
	}

	// 列表role(用户分页)
	public List<SysRoleEbo> listRole(String type, String name, String flag, int page, int paging) throws DataException {

		return roleDao.listRole(type, null, name, flag, page, paging);
	}

	// 需要做唯一性判断
	public void addRoleFuncLine(int roleId, int funcId) throws DataException {

		SysRoleFuncEbo roleFunc = roleFuncDao.getRoleFuncByrfId(roleId, funcId);
		if (roleFunc != null)
			return;
		SysRoleFuncEbo rf = new SysRoleFuncEbo();
		rf.setRoleId(roleId);
		rf.setFuncId(funcId);
		roleFuncDao.addRoleFuncLine(rf);
	}

	// 增加菜单到role
	public void addRoleFunc(int roleid, int funcid) throws DataException {

		SysFuncEbo func = getFuncById(funcid);
		if (func == null)
			return;
		String path = func.getFuncPath();
		List<Integer> l = funcDao.listFuncByPath(path);
		Integer funcId = null;
		for (int i = 0; i < l.size(); i++) {
			funcId = (Integer) l.get(i);
			addRoleFuncLine(roleid, funcId);
		}
	}

	// 删除特定role所对应的所有funcid
	public void delAllRoleFunc(int roleId) throws DataException {
		if (roleId <= 0)
			return;
		roleFuncDao.delAllRoleFunc(roleId);
	}

	// 删除菜单到role所对应的funcid，包含自身
	public void delRoleFunc(int roleId, int funcid) throws DataException {

		SysFuncEbo func = getFuncById(funcid);
		if (func == null) {
			if (roleId < 0)
				throw new DataException("[err]无对应菜单角色的ID不能空");
			roleDao.updateRoleFlag(roleId, Constant.FLAG_NO);
		}
		String path = func.getFuncPath();
		List<Integer> l = roleFuncDao.listRoleFuncId(roleId, path);
		for (int i = 0; i < l.size(); i++) {
			roleFuncDao.delRoleFuncById(l.get(i));
		}
	}

	// 查询角色菜单对应关系
	public List<FuncDto> listRoleFunc(int roleId) throws DataException {

		List<FuncDto> funcs = new ArrayList<FuncDto>();
		List<SysFuncEbo> listFunc = funcDao.listRoleFunc(roleId);
		if (listFunc.isEmpty())
			return funcs;
		SysFuncEbo f = null;
		FuncDto fd = null;
		Hashtable<Integer, FuncDto> funcHash = new Hashtable<Integer, FuncDto>();
		for (int i = 0; i < listFunc.size(); i++) {
			f = (SysFuncEbo) listFunc.get(i);
			fd = new FuncDto();
			fd.setCode(f.getCode());
			fd.setFlag(f.getFlag());
			fd.setId(f.getId());
			fd.setMenu(f.getMenu());
			fd.setName(f.getName());
			if (!funcHash.containsKey(f.getPid())) {
				funcs.add(fd);
			} else {
				funcHash.get(f.getPid()).getSubs().add(fd);
			}
			funcHash.put(f.getId(), fd);
		}
		return funcs;
	}

	// 罗列用户对应角色对应的菜单
	@PerformanceLog(methodType = MethodType.SELECT, bizType = BizType.CMSELECT)
	public List<FuncDto> listUserFunc(int uid) throws DataException {
		String flag = "Y";
		List<SysFuncEbo> listFunc = funcDao.listUserFunc(uid, flag);
		List<FuncDto> funcs = new ArrayList<FuncDto>();
		SysFuncEbo f = null;
		FuncDto fd = null;
		Hashtable<Integer, FuncDto> funcHash = new Hashtable<Integer, FuncDto>();
		for (int i = 0; i < listFunc.size(); i++) {
			f = (SysFuncEbo) listFunc.get(i);
			if (funcHash.containsKey(f.getId()))
				continue;
			fd = new FuncDto();
			fd.setCode(f.getCode());
			fd.setFlag(f.getFlag());
			fd.setId(f.getId());
			fd.setMenu(f.getMenu());
			fd.setName(f.getName());
			if (f.getIcon() != null) {
				fd.setIcon(f.getIcon());
			} else {
				fd.setIcon("");
			}

			if (!funcHash.containsKey(f.getPid())) {
				funcs.add(fd);
			} else {
				funcHash.get(f.getPid()).getSubs().add(fd);
				funcHash.get(f.getPid()).setLeaf(Constant.FLAG_NO);
			}
			funcHash.put(f.getId(), fd);
		}
		return funcs;
	}

	// 用户所对应的全部角色，包含已定义关联角色
	public Map<Integer, List<SysRoleInnerEbo>> listRelatedUserRole(List<Integer> uids, String type) throws DataException {

		Map<Integer, List<SysRoleInnerEbo>> res = new HashMap<Integer, List<SysRoleInnerEbo>>();
		if (uids == null || uids.size() <= 0)
			return res;
		if (Util.isNull(type))
			return res;

		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < uids.size(); i++) {
			if (i != 0)
				buf.append(",");
			buf.append(uids.get(i));
		}

		List<SysRoleInnerEbo> roles = roleDao.listRelatedUserRole(buf.toString(), type);
		List<SysRoleInnerEbo> urs = null;
		for (int i = 0; i < roles.size(); i++) {
			SysRoleInnerEbo ri = roles.get(i);
			if (res.containsKey(ri.getUid())) {
				urs = res.get(ri.getUid());
			} else {
				urs = new ArrayList<SysRoleInnerEbo>();
				res.put(ri.getUid(), urs);
			}
			urs.add(ri);
		}
		return res;

	}

	// 用户所对应的 角色列表
	public List<SysRoleInnerEbo> listUserRole(int uid, int roleId, String type) throws DataException {
		List<SysRoleInnerEbo> roles = roleDao.listUserRole(uid, roleId, type);
		for (SysRoleInnerEbo role : roles) {
			if (role.getUrId() != null && role.getUrId().intValue() > 0)
				role.setUserRole(Constant.FLAG_YES);
			else
				role.setUserRole(Constant.FLAG_NO);
		}
		return roles;
	}

	// 添加角色对应
	public void addUserRole(UserRoleEto userRole) throws DataException {
		// 作空值判断
		DataValUtil.dataValidation(userRole.getClass(), userRole);
		// List<UserRoleEbo> userRoles = new ArrayList<UserRoleEbo>();
		/*
		 * UserEbo userEbo = userService.getUserById(userRole.getUid()); if
		 * (userEbo == null) { throw new DataException("系统无此用户"); }
		 */
		SysRoleEbo sysRoleEbo = roleDao.getRoleById(userRole.getRoleId());
		if (sysRoleEbo == null) {
			throw new DataException("系统无此角色");
		}
		// 查询 当前用户所有的角色对应
		List<UserRoleEbo> urs = roleDao.listUserRoleAll(userRole.getUid(), userRole.getRoleId());
		if (urs.size() > 0)
			return;
		// 删除当前用用户的所有角色对应
		// roleDao.delUserRoleAll(userRole.getUid());
		UserRoleEbo role = null;
		try {
			role = new UserRoleEbo();
			BeanUtils.copyProperties(userRole, role);
			roleDao.addUserRole(role);
		} catch (DataException e) {
			logger.error(e.getMessage());
		}
	}

	// 添加一组uid和roles对应关系
	// purgePrevious=true是否清除已有的关系
	public void addUserRoles(int uid, List<Integer> roleIds, boolean purgePrevious) throws DataException {
		if (uid <= 0)
			return;
		if (roleIds == null || roleIds.size() <= 0)
			return;
		// 删除
		if (purgePrevious)
			roleDao.delUserRoleAll(uid);
		for (Integer rid : roleIds) {
			UserRoleEto ur = new UserRoleEto();
			ur.setUid(uid);
			ur.setRoleId(rid);
			addUserRole(ur);
		}
	}
}