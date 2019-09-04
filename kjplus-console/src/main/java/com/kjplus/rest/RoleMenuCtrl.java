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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.ybk.basic.util.Util;

import com.kjplus.constant.ConsolePageFtl;
import com.kjplus.constant.Constant;
import com.kjplus.dao.IOrgDao;
import com.kjplus.dto.FuncDto;
import com.kjplus.dto.KjAdminUserDto;
import com.kjplus.model.SysFuncEbo;
import com.kjplus.model.SysRoleEbo;
import com.kjplus.service.*;
import com.ybk.exception.DataException;

@Controller
public class RoleMenuCtrl {

	private static Logger logger = Logger.getLogger(RoleMenuCtrl.class);
	@Autowired
	private IOrgDao orgDao;
	@Autowired
	private IOrgService orgService;
	@Autowired
	private ISysFuncService funcService;
	@Autowired
	private ISysRegionsService regionsService;
	@Autowired
	private ISysBaseService baseService;
	@Autowired
	private IAdminUserService adminUserService;
	
	// roleMenu
	@RequestMapping("/rolemenulist.html")
	public ModelAndView roleMenuList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = mav.getModel();
		mav.setViewName("role_menu");
		
		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJCONSOLE_SESS);
		if (userEbo == null) {
			mav.setViewName(ConsolePageFtl.LOGIN_ACE);
			map.put("result", -1);
			map.put("returnUrl", "/userlist.html");
			map.put("message", "用户没有登陆!");
			return mav;
		}
		
		try{
			SysFuncEbo func = funcService.getFuncByMenu("rolemenulist.html", Constant.MENU_TYPE_CONSOLE);
			if (func != null)
				map.put("pmenu", func.getPid());
			else
				map.put("pmenu", -1);
			List<FuncDto> funcs = funcService.listUserFunc(userEbo.getUid());
			map.put("funcs", funcs);
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return mav;
	}

	@RequestMapping(value = "/rolelistjson.html")
	public @ResponseBody
	Map<String, Object> userListJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		int iDisplayStart = Util.parseNumVl(request.getParameter("iDisplayStart"), 0);
		int iDisplayLength = Util.parseNumVl(request.getParameter("iDisplayLength"), 10);
		int page = iDisplayStart / iDisplayLength;
		if (page <= 0)
			page = 0;

		try {
			List<SysRoleEbo> roleList = funcService.listRole(null, null, null, Constant.FLAG_YES);
			List<List<Object>> aaData = new ArrayList<List<Object>>();
			List<Object> aaList = null;
			for (SysRoleEbo u : roleList) {
				aaList = new ArrayList<Object>();
				aaList.add(u.getId());
				aaList.add(u.getCode());
				aaList.add(u.getName());
				aaList.add(u.getType());
				aaData.add(aaList);
			}
			map.put("aaData", aaData);
			map.put("result", 1);
			map.put("iTotalRecords", roleList.size());
			map.put("iTotalDisplayRecords", roleList.size());
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// adminUser列表
	@RequestMapping(value = "/rolemenujson.html")
	public @ResponseBody
	Map<String, Object> roleMenuJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		try {
			KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJCONSOLE_SESS);
			if (userEbo == null) {
				map.put("result", 1);
				map.put("message", "用户没有登录!");
				return map;
			}
			
			int roleid = Util.parseNumVl(request.getParameter("roleid"), 0);
			List<FuncDto> funcs = funcService.listRoleFunc(roleid);
			map.put("data", funcs);
			map.put("result", 1);
			map.put("message", "获得角色菜单成功!");
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}
	
}