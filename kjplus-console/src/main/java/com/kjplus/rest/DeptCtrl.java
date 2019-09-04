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
import com.kjplus.dto.DepartmentDto;
import com.kjplus.dto.FuncDto;
import com.kjplus.dto.KjAdminUserDto;
import com.kjplus.dto.SysRegionsDto;
import com.kjplus.model.DepartmentEbo;
import com.kjplus.model.SysFuncEbo;
import com.kjplus.model.SysRefValueEbo;
import com.kjplus.service.*;
import com.ybk.exception.DataException;

@Controller
public class DeptCtrl {

	private static Logger logger = Logger.getLogger(DeptCtrl.class);
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
	private IDeptService deptService;
	
	// 部门列表
	@RequestMapping("/deptlist.html")
	public ModelAndView deptList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = mav.getModel();
		mav.setViewName("dept_list");
		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJCONSOLE_SESS);
		if (userEbo == null) {
			mav.setViewName(ConsolePageFtl.LOGIN_ACE);
			map.put("result", -1);
			map.put("returnUrl", "/deptlist.html");
			map.put("message", "用户没有登陆!");
			return mav;
		}
		
		try{
			SysFuncEbo func = funcService.getFuncByMenu("deptlist.html", Constant.MENU_TYPE_CONSOLE);
			if (func != null)
				map.put("pmenu", func.getPid());
			else
				map.put("pmenu", -1);
			List<FuncDto> funcs = funcService.listUserFunc(userEbo.getUid());
			map.put("funcs", funcs);
			
			List<SysRegionsDto> prvnList = regionsService.listRegions(0);
			map.put("prvnList", prvnList);
			
			List<SysRefValueEbo> orgTypeList = baseService.listRefVlByRefCode("ORG_TYPE", Constant.FLAG_YES);
			map.put("orgTypeList", orgTypeList);
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return mav;
	}

	@RequestMapping(value = "/mgr_deptlistjson.html")
	public @ResponseBody
	Map<String, Object> deptListJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		int iDisplayStart = Util.parseNumVl(request.getParameter("iDisplayStart"), 0);
		int iDisplayLength = Util.parseNumVl(request.getParameter("iDisplayLength"), 10);
		int page = iDisplayStart / iDisplayLength;
		if (page <= 0)
			page = 0;

		try {
			int orgId = Util.parseNumVl(request.getParameter("orgId"), Integer.MAX_VALUE);
			String deptName = request.getParameter("deptname");
			String flag = request.getParameter("status");
			String deptType = request.getParameter("deptType");
			List<DepartmentDto> listDepartmentDto = deptService.listDepartmentDto(deptName,deptType, orgId,flag,page,iDisplayLength);
			List<List<Object>> aaData = new ArrayList<List<Object>>();
			List<Object> aaList = null;		
			for (DepartmentDto d : listDepartmentDto) {
				aaList = new ArrayList<Object>();
				aaList.add(d.getId());
				aaList.add(d.getPid());
				aaList.add(d.getCode());
				aaList.add(d.getName());
				aaList.add(d.getDeptType());
				aaList.add(d.getFlag());
				aaList.add(d.getOrgId());
				aaList.add(d.getOrgName());
				aaList.add(d.getMemo());
				aaData.add(aaList);
			}
			
			int totalRec = deptService.getTotalDepartment(deptName, orgId, flag);
			map.put("aaData", aaData);
			map.put("result", 1);
			map.put("iTotalRecords", totalRec);
			map.put("iTotalDisplayRecords", totalRec);
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}
	
	// 通过deptId获取部门
	@RequestMapping(value = "/getdeptbyidjson.html")
	public @ResponseBody
	Map<String, Object> getorgbyid(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		int deptId = Util.parseNumVl(request.getParameter("deptId"), 0);
		try {
			DepartmentEbo departmentById = deptService.getDepartmentById(deptId);
			if (departmentById != null) {
				String deptCode = departmentById.getCode();
				map.put("deptCode", deptCode);
				map.put("result", 1);
			}else {
				map.put("result", -1);
				map.put("message", "系统没有该组织");
			}
			
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}
	
}
