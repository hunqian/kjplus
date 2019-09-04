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
import org.ybk.basic.util.Util;

import com.kjplus.constant.ConsolePageFtl;
import com.kjplus.constant.Constant;
import com.kjplus.dao.IOrgDao;
import com.kjplus.dto.DepartmentDto;
import com.kjplus.dto.FuncDto;
import com.kjplus.dto.KjAdminUserDto;
import com.kjplus.dto.SysRegionsDto;
import com.kjplus.eto.DepartmentEto;
import com.kjplus.model.DepartmentEbo;
import com.kjplus.model.SysFuncEbo;
import com.kjplus.model.SysRefValueEbo;
import com.kjplus.service.IDeptService;
import com.kjplus.service.IOrgService;
import com.kjplus.service.ISysBaseService;
import com.kjplus.service.ISysFuncService;
import com.kjplus.service.ISysRegionsService;
import com.ybk.exception.DataException;

@Controller
public class DeptMgrCtrl {

	private static Logger logger = Logger.getLogger(DeptMgrCtrl.class);
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
		// mav.setViewName("dept_list");
		mav.setViewName("dept_list");
		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
		if (userEbo == null) {
			mav.setViewName(ConsolePageFtl.LOGIN_ACE);
			map.put("result", -1);
			map.put("returnUrl", "/deptlist.html");
			map.put("message", "用户没有登陆!");
			return mav;
		}

		try {
			SysFuncEbo func = funcService.getFuncByMenu("deptlist.html", Constant.MENU_TYPE_MANAGER);
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

			List<DepartmentDto> listDepartmentDto = deptService.listDepartmentDto(null, null, userEbo.getOrgId(), null, 0, 10);
			map.put("listDepartmentDto", listDepartmentDto);
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
			KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
			int orgId = userEbo.getOrgId();
			String type = request.getParameter("type");
			String deptName = request.getParameter("deptname");
			String flag = request.getParameter("status");
			List<DepartmentDto> listDepartmentDto = deptService.listDepartmentDto(deptName, type, orgId, flag, page, iDisplayLength);
			List<List<Object>> aaData = new ArrayList<List<Object>>();
			List<Object> aaList = null;
			for (DepartmentDto d : listDepartmentDto) {
				aaList = new ArrayList<Object>();
				aaList.add(d.getId());
				aaList.add(d.getCode());
				aaList.add(d.getName());
				aaList.add(d.getPid());
				aaList.add(d.getpName());
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

	@RequestMapping(value = "/mgr_getdeptjson.html")
	public @ResponseBody
	Map<String, Object> getDeptJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		String code = request.getParameter("code");
		try {
			DepartmentEbo dept = deptService.getDepartmentByCode(code);
			if (dept == null) {
				map.put("result", -1);
				map.put("messge", "没有找到对于的信息内容的编码'" + code + "'");
				return map;
			}
			Map<String, Object> data = new HashMap<String, Object>();

			data.put("pid", dept.getPid());
			data.put("deptName", dept.getName());
			data.put("deptType", dept.getDeptType());
			data.put("flag", dept.getFlag());
			data.put("memo", dept.getMemo());

			map.put("data", data);
			map.put("result", 1);

		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 添加修改咨询列表
	@RequestMapping(value = "/mgr_addormodifydeptjson.html")
	public @ResponseBody
	Map<String, Object> adddeptlistjson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		try {
			DepartmentEto dept = new DepartmentEto();
			String code = request.getParameter("code");
			int pid = Util.parseNumVl(request.getParameter("pid"), 0);
			String deptName = request.getParameter("deptName");
			String deptType = request.getParameter("deptType");
			String flag = request.getParameter("flag");
			KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
			int orgId = userEbo.getOrgId();
			String memo = request.getParameter("memo");
			dept.setPid(pid);
			dept.setName(deptName);
			dept.setOrgId(orgId);
			dept.setDeptType(deptType);
			dept.setFlag(flag);
			dept.setMemo(memo);
			if (Util.isNull(code)) {
				deptService.addDepartment(dept);
				map.put("message", "添加info成功！");
			} else {
				DepartmentEbo d = deptService.getDepartmentByCode(code);
				d.setPid(pid);
				d.setName(deptName);
				d.setDeptType(deptType);
				d.setFlag(flag);
				d.setMemo(memo);
				d.setOrgId(orgId);
				if (d != null) {
					deptService.updateDept(d);
					map.put("message", "修改部门信息功！");
				}
			}
			map.put("result", 1);
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

}
