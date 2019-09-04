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
import com.kjplus.dto.FuncDto;
import com.kjplus.dto.InfoCatalogDto;
import com.kjplus.dto.KjAdminUserDto;
import com.kjplus.dto.OrgDto;
import com.kjplus.eto.InfoCatalogEto;
import com.kjplus.model.InfoCatalogEbo;
import com.kjplus.model.SysFuncEbo;
import com.kjplus.model.SysRefValueEbo;
import com.kjplus.service.IInfoService;
import com.kjplus.service.IOrgService;
import com.kjplus.service.ISysBaseService;
import com.kjplus.service.ISysFuncService;
import com.kjplus.service.IUserService;
import com.ybk.exception.DataException;

@Controller
public class InfoCatalogCtrl {

	private static Logger logger = Logger.getLogger(InfoCatalogCtrl.class);
	@Autowired
	private IInfoService infoService;
	@Autowired
	private ISysFuncService funcService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IOrgService orgService;
	@Autowired
	private ISysBaseService baseService;

	// infocatalog列表
	@RequestMapping("/infocataloglist.html")
	public ModelAndView createDocument(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = mav.getModel();
		mav.setViewName("infocatalog_list");

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJCONSOLE_SESS);
		if (userEbo == null) {
			mav.setViewName(ConsolePageFtl.LOGIN_ACE);
			map.put("result", -1);
			map.put("returnUrl", "/login.html");
			map.put("message", "用户没有登陆!");
			return mav;
		}

		try {
			SysFuncEbo func = funcService.getFuncByMenu("/infocataloglist.html", Constant.MENU_TYPE_CONSOLE);
			if (func != null)
				map.put("pmenu", func.getPid());
			else
				map.put("pmenu", -1);
			List<FuncDto> funcs = funcService.listUserFunc(userEbo.getUid());
			map.put("funcs", funcs);

			List<OrgDto> orgs = orgService.listOrg(null, null, 0, 0, false, 0, 10);
			map.put("orgs", orgs);

			List<SysRefValueEbo> refVls = baseService.listRefVlByRefCode("STD_INFO_TYPE", Constant.FLAG_YES);
			map.put("refVls", refVls);
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return mav;
	}

	// infocatalog列表信息
	@RequestMapping(value = "/infocataloglistjson.html")
	public @ResponseBody
	Map<String, Object> infoCatalogListJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		int iDisplayStart = Util.parseNumVl(request.getParameter("iDisplayStart"), 0);
		int iDisplayLength = Util.parseNumVl(request.getParameter("iDisplayLength"), 10);
		int page = iDisplayStart / iDisplayLength;
		if (page <= 0)
			page = 0;

		try {
			int orgid = Util.parseNumVl(request.getParameter("orgid"), -1);
			int pid = Util.parseNumVl(request.getParameter("pid"), -1);
			String name = request.getParameter("name");
			String flag = request.getParameter("flag");
			List<InfoCatalogDto> catgs = infoService.listCatalog(orgid, pid, name, flag);
			List<Object> auList = null;
			List<List<Object>> aaData = new ArrayList<List<Object>>();
			for (InfoCatalogDto ct : catgs) {
				auList = new ArrayList<Object>();
				auList.add(ct.getId());
				auList.add(Util.val(ct.getCode()));
				auList.add(Util.val(ct.getName()));
				auList.add(Util.val(ct.getPid()));
				auList.add(Util.val(ct.getPname()));
				auList.add(Util.val(ct.getInfoTypeId()));
				auList.add(Util.val(ct.getInfoTypeName()));
				auList.add(Util.val(ct.getOrgId()));
				auList.add(Util.val(ct.getOrgName()));
				auList.add(Util.val(ct.getFlag()));
				aaData.add(auList);
			}
			int total = infoService.getTotalCatalogByOrg(orgid, pid, name, flag);
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

	// infocatalog列表信息
	@RequestMapping(value = "/getinfocatalogjson.html")
	public @ResponseBody
	Map<String, Object> getinfocatalogjson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		try {
			String code = request.getParameter("code");
			InfoCatalogEbo cat = infoService.getCatalogByIdOrCode(0, code);
			if (cat == null) {
				map.put("data", new HashMap<String, String>());
				map.put("result", -1);
				map.put("message", "该infocatalog不存在");
				return map;
			}
			map.put("data", cat);
			map.put("result", 1);
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// infocatalog列表信息t添加
	@RequestMapping(value = "/addinfocatalogjson.html")
	public @ResponseBody
	Map<String, Object> addInfoCatalogJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		try {
			int orgid = Util.parseNumVl(request.getParameter("orgid"), 0);
			/*OrgEbo orgEbo = orgService.getOrgById(orgid);
			if (orgEbo == null) {
				map.put("result", -1);
				map.put("message", "该组织不存在");
				return map;
			}*/
			int pid = Util.parseNumVl(request.getParameter("pid"), 0);
			if (pid != 0) {
				InfoCatalogEbo infoCatalogEbo = infoService.getCatalogByIdOrCode(pid, null);
				if (infoCatalogEbo == null) {
					map.put("result", -1);
					map.put("message", "该catalog父类不存在，pid=" + pid);
					return map;
				}
			}
			String name = request.getParameter("name");
			int typeId = Util.parseNumVl(request.getParameter("typeid"), 0);
			InfoCatalogEto catg = new InfoCatalogEto();
			catg.setName(name);
			catg.setPid(pid);
			catg.setOrgId(orgid);
			catg.setInfoTypeId(typeId);
			infoService.addCatalog(catg);
			map.put("result", 1);
			map.put("message", "添加infocatalog成功");
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// infocatalog列表信息t添加
	@RequestMapping(value = "/updateinfocatalogjson.html")
	public @ResponseBody
	Map<String, Object> updateInfoCatalogJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		try {
			String code = request.getParameter("code");
			if (code == null) {
				map.put("result", -1);
				map.put("message", "code不能为空");
				return map;
			}
			String name = request.getParameter("name");
			int infoTypeId = Util.parseNumVl(request.getParameter("infotypeid"), 0);
			String flag = request.getParameter("flag");
			;
			InfoCatalogEbo in = new InfoCatalogEbo();
			in.setCode(code);
			in.setName(name);
			in.setFlag(flag);
			in.setInfoTypeId(infoTypeId);
			infoService.updateInfoCatalog(in);

			map.put("result", 1);
			map.put("message", "修改infocatalog成功");
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}
}
