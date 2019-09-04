package com.kjplus.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.ybk.basic.util.StringUtil;
import org.ybk.basic.util.Util;

import com.kjplus.constant.ConsolePageFtl;
import com.kjplus.constant.Constant;
import com.kjplus.dto.AdminUserDto;
import com.kjplus.dto.FuncDto;
import com.kjplus.dto.IDNameFlagDto;
import com.kjplus.dto.KjAdminUserDto;
import com.kjplus.eto.AdminUserEto;
import com.kjplus.model.AdminUserEbo;
import com.kjplus.model.SysFuncEbo;
import com.kjplus.model.inner.SysRoleInnerEbo;
import com.kjplus.service.IAdminUserService;
import com.kjplus.service.ISysFuncService;
import com.ybk.exception.DataException;

@Controller
public class AdminUserMgrCtrl {

	private static Logger logger = Logger.getLogger(AdminUserMgrCtrl.class);

	@Autowired
	private IAdminUserService adminUserService;

	@Autowired
	private ISysFuncService funcService;

	// admin列表
	@RequestMapping("/adminuserlist.html")
	public ModelAndView orgList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = mav.getModel();
		mav.setViewName("adminuser_list");

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
		if (userEbo == null) {
			mav.setViewName(ConsolePageFtl.LOGIN_ACE);
			map.put("result", -1);
			map.put("returnUrl", "/login.html");
			map.put("message", "用户没有登陆!");
			return mav;
		}

		try {
			SysFuncEbo func = funcService.getFuncByMenu("adminuserlist.html", Constant.MENU_TYPE_MANAGER);
			if (func != null)
				map.put("pmenu", func.getPid());
			else
				map.put("pmenu", -1);
			List<FuncDto> funcs = funcService.listUserFunc(userEbo.getUid());
			map.put("funcs", funcs);
			
			//TODO 重新赋值session 防止数据不同步
			AdminUserEbo adminUser =  adminUserService.getUserByUid(userEbo.getUid());
			KjAdminUserDto adminUser2 = new KjAdminUserDto();
			adminUser2.setUid(adminUser.getUid());
			adminUser2.setUserStatus(adminUser.getStatus());
			adminUser2.setUserType(adminUser.getUserType());
			adminUser2.setOrgId(adminUser.getOrgid());
			adminUser2.setNickName(adminUser.getNickName());
			adminUser2.setFaceUrl(adminUser.getFace());
			request.getSession().setAttribute(Constant.KJMGR_SESS, adminUser2);
			
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return mav;
	}

	// adminUser列表
	@RequestMapping(value = "/mgr_adminuserlistjson.html")
	public @ResponseBody
	Map<String, Object> adminUserListJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		KjAdminUserDto userEboLogin = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
		if (userEboLogin == null) {
			map.put("result", -1);
			map.put("returnUrl", "/mgr_adminuserlistjson.html");
			map.put("message", "用户没有登陆!");
			return map;
		}
		//
		int orgid = userEboLogin.getOrgId();

		int iDisplayStart = Util.parseNumVl(request.getParameter("iDisplayStart"), 0);
		int iDisplayLength = Util.parseNumVl(request.getParameter("iDisplayLength"), 10);
		int page = iDisplayStart / iDisplayLength;
		if (page <= 0)
			page = 0;

		try {
			KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
			List<List<Object>> aaData = new ArrayList<List<Object>>();
			List<AdminUserDto> aus = new ArrayList<AdminUserDto>();
			if (userEbo == null) {
				map.put("aaData", aaData);
				map.put("result", 1);
				map.put("iTotalRecords", 0);
				return map;
			}

			String userName = request.getParameter("name");
			String nickName = request.getParameter("name");
			String userType = Constant.ADMIN_USER_TYPE_GNRL;
			String status = request.getParameter("status");
			aus = adminUserService.listUser(userName, nickName, userType, status, orgid, page, iDisplayLength);
			List<Integer> uids = new ArrayList<Integer>();
			for (AdminUserDto au : aus) {
				uids.add(au.getUid());
			}

			Map<Integer, List<SysRoleInnerEbo>> roleRes = funcService.listRelatedUserRole(uids, "A");

			List<Object> auList = null;
			for (AdminUserDto au : aus) {
				auList = new ArrayList<Object>();
				auList.add(au.getUid());
				auList.add(Util.val(au.getFace()));
				auList.add(Util.val(au.getUserName()));
				auList.add(Util.val(au.getNickName()));
				auList.add(Util.val(au.getEmail()));
				auList.add(Util.val(au.getMobileNum()));
				auList.add(Util.val(au.getRegTime()));
				auList.add(Util.val(au.getStatus()));
				auList.add(Util.val(au.getUserType()));
				auList.add(Util.val(au.getEffectFrom()));
				auList.add(Util.val(au.getEffectTo()));
				auList.add(Util.val(au.getOrgId()));
				auList.add(Util.val(au.getOrgName()));
				auList.add(Util.val(au.getCreateTime()));
				if (!roleRes.containsKey(au.getUid()))
					auList.add("");
				else {
					List<SysRoleInnerEbo> roles = roleRes.get(au.getUid());
					StringBuffer buf = new StringBuffer();
					for (int i = 0; i < roles.size(); i++) {
						if (i != 0)
							buf.append(",");
						buf.append(roles.get(i).getName());
					}
					auList.add(buf.toString());
				}
				aaData.add(auList);
			}
			int total = adminUserService.getTotalUser(userName, nickName, userType, status, orgid);
			map.put("aaData", aaData);
			map.put("result", 1);
			map.put("iTotalRecords", total);
			map.put("iTotalDisplayRecords", total);
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 查询管理员信息获取
	@RequestMapping(value = "/mgr_getadminuserjson.html")
	public @ResponseBody
	Map<String, Object> getAdminUserJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);
		// 模糊查询
		String nickName = request.getParameter("nickName");
		String userName = request.getParameter("username");
		String status = request.getParameter("status");
		// 默认组织-1,全部组织信息
		int orgid = Util.parseNumVl(request.getParameter("orgid"), 0);
		int page = 0;
		int paging = 10;
		List<List<Object>> aaData = new ArrayList<List<Object>>();
		List<AdminUserDto> aus = new ArrayList<AdminUserDto>();
		try {
			aus = adminUserService.listUser(userName, nickName, null, status, orgid, page, paging);
			if (aus.size() == 0) {
				map.put("result", -1);
				map.put("messge", "没有查询到相关人员");
				return map;
			}
			List<Object> auList = null;
			for (AdminUserDto au : aus) {
				auList = new ArrayList<Object>();
				auList.add(au.getUid());
				auList.add(Util.val(au.getUserName()));
				auList.add(Util.val(au.getEmail()));
				auList.add(Util.val(au.getMobileNum()));
				auList.add(Util.val(au.getRegTime()));
				auList.add(Util.val(au.getStatus()));
				auList.add(Util.val(au.getUserType()));
				auList.add(Util.val(au.getFace()));
				auList.add(Util.val(au.getEffectFrom()));
				auList.add(Util.val(au.getEffectTo()));
				auList.add(Util.val(au.getNickName()));
				auList.add(Util.val(au.getOrgId()));
				auList.add(Util.val(au.getOrgName()));
				auList.add(Util.val(au.getCreateTime()));
				aaData.add(auList);
			}
			int total = adminUserService.getTotalUser(null, nickName, null, status, orgid);
			map.put("aaData", aaData);
			map.put("result", 1);
			map.put("iTotalRecords", total);
			map.put("iTotalDisplayRecords", total);
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 角色列表
	@RequestMapping(value = "/mgr_getadminuserolesjson.html")
	public @ResponseBody
	Map<String, Object> getAdminUserRolesJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		int uid = Util.parseNumVl(request.getParameter("uid"), 0);

		try {
			KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
			if (userEbo == null) {
				map.put("result", -1);
				map.put("message", "用户没有登陆!");
				return map;
			}

			// int uid, int roleId
			List<SysRoleInnerEbo> userRoles = funcService.listUserRole(uid, 0, "A");
			List<IDNameFlagDto> urs = new ArrayList<IDNameFlagDto>();
			for (SysRoleInnerEbo r : userRoles) {
				IDNameFlagDto ur = new IDNameFlagDto();
				ur.setId(r.getId());
				ur.setName(r.getName());
				if (r.getUid() != null && r.getUid().intValue() > 0)
					ur.setFlag(Constant.FLAG_YES);
				else
					ur.setFlag(Constant.FLAG_NO);
				urs.add(ur);
			}
			map.put("data", urs);
			map.put("result", 1);
			map.put("message", "获得用户角色成功!");
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// adminUser列表
	@RequestMapping(value = "/mgr_defadminuserolesjson.html")
	public @ResponseBody
	Map<String, Object> defAdminUserRolesJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		int uid = Util.parseNumVl(request.getParameter("uid"), 0);
		String roleIdStr = request.getParameter("roleids");

		try {
			KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
			if (userEbo == null) {
				map.put("result", -1);
				map.put("message", "用户没有登陆!");
				return map;
			}
			if (uid <= 0) {
				map.put("result", -1);
				map.put("message", "用户没有指定!");
				return map;
			}

			List<Integer> roleIds = new ArrayList<Integer>();
			List<String> rids = Util.str2Array(roleIdStr);
			for (String r : rids) {
				roleIds.add(Util.parseNumVl(r, 0));
			}
			funcService.addUserRoles(uid, roleIds, true);
			map.put("result", 1);
			map.put("message", "保存用户角色成功!");
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// adminUsermenu列表
	@RequestMapping(value = "/mgr_adminusermenujson.html")
	public @ResponseBody
	Map<String, Object> adminUserMenuJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		try {
			KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
			if (userEbo == null) {
				map.put("result", 1);
				map.put("message", "用户没有登录!");
				return map;
			}

			int uid = Util.parseNumVl(request.getParameter("uid"), 0);
			List<FuncDto> funcs = funcService.listUserFunc(uid);
			map.put("data", funcs);
			map.put("result", 1);
			map.put("message", "获得用户菜单成功!");
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// adminuser添加，注册
	@RequestMapping(value = "/mgr_addadminuser.html")
	public @ResponseBody
	Map<String, Object> addAdminuser(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		try {
			KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
			if (userEbo == null) {
				map.put("result", 1);
				map.put("message", "用户没有登录!");
				return map;
			}

			int orgId = userEbo.getOrgId();
			String userName = request.getParameter("username");
			String passWord = request.getParameter("password");
			String email = request.getParameter("email");
			String mobileNum = request.getParameter("mobilenum");
			String face = request.getParameter("face");
			String nickName = request.getParameter("nickname");

			if (Util.isNull(userName) && Util.isNull(mobileNum)) {
				map.put("result", 1);
				map.put("message", "用户名和手机号不能同时为空!");
				return map;
			}
			if (Util.isNull(passWord)) {
				map.put("result", 1);
				map.put("message", "密码不能为空");
				return map;
			}
			// 查询是否存在此管理员
			AdminUserEbo u1 = adminUserService.getUserByUnameMobile(userName, null);
			if (u1 != null) {
				map.put("result", 1);
				map.put("message", "[err]该用户已经存在：userName =" + userName);
				return map;
			}
			// 手机号格式验证 具体格式
			if (mobileNum.length() != 11) {
				map.put("result", 1);
				map.put("message", "[err]手机号不符合要求且不能为空!");
				return map;
			}
			AdminUserEbo u2 = adminUserService.getUserByUnameMobile(null, mobileNum);
			if (u2 != null) {
				map.put("result", 1);
				map.put("message", "[err]该用户已经存在：mobile =" + mobileNum);
				return map;
			}
			AdminUserEto adminUser = new AdminUserEto();
			adminUser.setUserName(userName);
			adminUser.setPassWord(passWord);
			adminUser.setEmail(email);
			adminUser.setMobileNum(mobileNum);
			adminUser.setFace(face);
			adminUser.setNickName(nickName);
			adminUser.setOrgId(orgId);
			adminUserService.register(adminUser);
			map.put("result", 1);
			map.put("message", "添加管理员成功");
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// adminuser添加，注册
	@RequestMapping(value = "/mgr_updateadminuser.html")
	public @ResponseBody
	Map<String, Object> updateAdminUser(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		try {
			KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
			if (userEbo == null) {
				map.put("result", 1);
				map.put("message", "用户没有登录!");
				return map;
			}

			String userName = request.getParameter("username");
			String passWord = request.getParameter("password");
			String email = request.getParameter("email");
			String mobileNum = request.getParameter("mobilenum");
			String face = request.getParameter("face");
			String status = request.getParameter("status");
			String nickName = request.getParameter("nickname");

			AdminUserEbo admin = adminUserService.getUserByUnameMobile(userName, null);
			if (admin == null) {
				map.put("result", 1);
				map.put("message", "该用户不存在!");
				return map;
			}
			if (Util.isNotNull(passWord))
				passWord = StringUtil.md5(passWord);
			if (Util.isNotNull(email)) {
				if (!email.contains("@")) {
					map.put("result", 1);
					map.put("message", "[err]邮箱格式不对!");
					return map;
				}
			}

			if (Util.isNotNull(mobileNum)) {
				// 手机号格式验证 具体格式
				if (mobileNum.length() != 11) {
					map.put("result", 1);
					map.put("message", "手机号不符合要求且不能为空!");
					return map;
				}
				if (!mobileNum.equals(admin.getMobileNum())) {
					// 查询是否存在此管理员
					AdminUserEbo u2 = adminUserService.getUserByUnameMobile(null, mobileNum);
					if (u2 != null) {
						map.put("result", 1);
						map.put("message", "该用户已经存在：mobileNum =" + mobileNum);
						return map;
					}
				}
			}
			if (status.equals("Y")) {
				status = Constant.FLAG_YES;
			} else if (status.equals("N")) {
				status = Constant.FLAG_NO;
			} else {
				map.put("result", 1);
				map.put("message", "该用户状态格式有问题");
				return map;
			}
			AdminUserEbo adminUserEbo = new AdminUserEbo();
			adminUserEbo.setUserName(userName);
			adminUserEbo.setPassWord(passWord);
			adminUserEbo.setEmail(email);
			adminUserEbo.setMobileNum(mobileNum);
			adminUserEbo.setStatus(status);
			adminUserEbo.setFace(face);
			adminUserEbo.setNickName(nickName);
			adminUserService.updateAdminUser(adminUserEbo);
			map.put("result", 1);
			map.put("message", "修改管理员成功");
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

}
