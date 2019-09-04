package com.kjplus.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.ybk.basic.util.Util;

import com.kjplus.constant.ConsolePageFtl;
import com.kjplus.constant.Constant;
import com.kjplus.dao.IOrgDao;
import com.kjplus.dto.FuncDto;
import com.kjplus.dto.KjAdminUserDto;
import com.kjplus.dto.ServiceEntryDto;
import com.kjplus.dto.OrgDto;
import com.kjplus.eto.ServiceConfigEto;
import com.kjplus.eto.ServiceEntryEto;
import com.kjplus.model.ServiceConfigEbo;
import com.kjplus.model.ServiceEntryEbo;
import com.kjplus.model.SysFuncEbo;
import com.kjplus.model.SysRefValueEbo;
import com.kjplus.model.inner.ServiceConfigInnerEbo;
import com.kjplus.service.IOrgService;
import com.kjplus.service.IServiceEntryService;
import com.kjplus.service.ISysBaseService;
import com.kjplus.service.ISysFuncService;
import com.kjplus.service.ISysRegionsService;
import com.mq.util.DateUtil;
import com.ybk.exception.DataException;

@Controller
public class ServerCtrl {

	private static Logger logger = Logger.getLogger(ServerCtrl.class);
	@Autowired
	private IOrgDao orgDao;
	@Autowired
	private IOrgService orgService;
	@Autowired
	private IServiceEntryService srvEntryService;
	@Autowired
	private ISysFuncService funcService;
	@Autowired
	private ISysRegionsService regionsService;
	@Autowired
	private ISysBaseService baseService;

	// 组织列表
	@RequestMapping("/serverlist.html")
	public ModelAndView orgList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = mav.getModel();
		mav.setViewName("server_list");

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJCONSOLE_SESS);
		if (userEbo == null) {
			mav.setViewName(ConsolePageFtl.LOGIN_ACE);
			map.put("result", -1);
			map.put("returnUrl", "/serverlist.html");
			map.put("message", "用户没有登陆!");
			return mav;
		}

		try {
			SysFuncEbo func = funcService.getFuncByMenu("serverlist.html", Constant.MENU_TYPE_CONSOLE);
			if (func != null)
				map.put("pmenu", func.getPid());
			else
				map.put("pmenu", -1);
			List<FuncDto> funcs = funcService.listUserFunc(userEbo.getUid());
			map.put("funcs", funcs);

			List<OrgDto> listOrg = orgService.listOrg(null, null, 0, 0, false, 0, 0);
			map.put("listOrg", listOrg);

			List<SysRefValueEbo> refVl = baseService.getRefVlByRefId(43, null);
			map.put("refVl", refVl);

			// List<MainSrvCatalogDto> listServiceEntry =
			// srvEntryService.listServiceEntry(null, null, -1, null, null, 0,
			// 10);
			//List<ServiceEntryDto> listServiceEntry = srvEntryService.listServiceEntry(-1, 0, null, null, null, Constant.FLAG_YES, 0, 10, false, true);
			//map.put("listServiceEntry", listServiceEntry);

		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return mav;
	}

	@RequestMapping(value = "/mgr_serventrylistjson.html")
	public @ResponseBody
	Map<String, Object> mgrServEntryListJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		int iDisplayStart = Util.parseNumVl(request.getParameter("iDisplayStart"), 0);
		int iDisplayLength = Util.parseNumVl(request.getParameter("iDisplayLength"), 10);
		int page = iDisplayStart / iDisplayLength;
		if (page <= 0)
			page = 0;

		try {
			int orgId = Util.parseNumVl(request.getParameter("orgId"), -1);
			String flag = request.getParameter("status");
			String name = request.getParameter("name");
			String type = request.getParameter("type");
			int typeId = Util.parseNumVl(request.getParameter("typeId"), 0);
			String opType = request.getParameter("opType");
			// List<MainSrvCatalogDto> listServiceEntry =
			// srvEntryService.listServiceEntry(name,type,orgId, srvCode,
			// flag,page,iDisplayLength);
			//List<ServiceEntryDto> listServiceEntry = srvEntryService.listServiceEntry(orgId, typeId, type, opType, name, flag, page, iDisplayLength, false, true);
			List<ServiceEntryDto> listServiceEntry = new ArrayList<ServiceEntryDto>();
			List<List<Object>> aaData = new ArrayList<List<Object>>();
			List<Object> aaList = null;
			for (ServiceEntryDto server : listServiceEntry) {
				aaList = new ArrayList<Object>();
				aaList.add(server.getId());
				aaList.add(server.getSrvType());
				aaList.add(server.getSrvCode());
				aaList.add(server.getSrvName());
				aaList.add(server.getSrvTypeCode());
				aaList.add(server.getSrvTypeName());
				aaList.add(server.getFlag());
				aaList.add(server.getSrvMemo());
				aaList.add(DateUtil.formatDateTime(server.getCreateTime()));
				aaList.add(server.getOrgCode());
				aaList.add(server.getOrgName());
				aaData.add(aaList);
			}
			int totalRec = 0;//srvEntryService.getTotalEntry(orgId, typeId, type, opType, name, flag);
			map.put("aaData", aaData);
			map.put("result", 1);
			map.put("iTotalRecords", totalRec);
			map.put("iTotalDisplayRecords", totalRec);
		} catch (Exception e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 获取服务配置列表
	@RequestMapping(value = "/mgr_serverconfiglistjson.html")
	public @ResponseBody
	Map<String, Object> mgrServerConfigJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		int iDisplayStart = Util.parseNumVl(request.getParameter("iDisplayStart"), 0);
		int iDisplayLength = Util.parseNumVl(request.getParameter("iDisplayLength"), 10);
		int page = iDisplayStart / iDisplayLength;
		if (page <= 0)
			page = 0;

		try {
			int orgId = Util.parseNumVl(request.getParameter("orgId"), -1);
			int srvId = Util.parseNumVl(request.getParameter("srvId"), Integer.MAX_VALUE);
			//List<ServiceConfigInnerEbo> listSrvCfg = srvEntryService.listSrvCfg(orgId, srvId, page, iDisplayLength);
			List<ServiceConfigInnerEbo> listSrvCfg = new ArrayList<ServiceConfigInnerEbo>();
			List<List<Object>> aaData = new ArrayList<List<Object>>();
			List<Object> aaList = null;
			for (ServiceConfigInnerEbo config : listSrvCfg) {
				aaList = new ArrayList<Object>();
				aaList.add(1);
				//aaList.add(config.getId());
				aaList.add(config.getSrvId());
				aaList.add(config.getSrvName());
				aaList.add(config.getSrvType());
				aaList.add(config.getSrvCode());
				aaList.add(config.getSrvMemo());
				aaList.add(2);
				//aaList.add(config.getSrvTypeId());
				aaList.add(config.getSrvTypeName());
				aaList.add(config.getAcceptorPoint());
				aaList.add(config.getProviderPoint());
				aaList.add(config.getOrgId());
				aaList.add(config.getOrgName());
				aaList.add(DateUtil.formatDateTime(config.getCalCreateTime()));
				aaData.add(aaList);
			}
			// int totalRec = srvEntryService.getTotalConfig(orgId, srvId);
			map.put("aaData", aaData);
			// map.put("result", totalRec);
			map.put("iTotalRecords", 1);
			// map.put("iTotalDisplayRecords", totalRec);
		} catch (Exception e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 添加修改组织
	@RequestMapping(value = "/mgr_addormodifyserventryjson.html")
	public @ResponseBody
	Map<String, Object> addOrModifyServEntryJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);
		try {
			String code = request.getParameter("code");
			String serventryname = request.getParameter("serventryname");
			String serventrytype = request.getParameter("serventrytype");
			int refid = Util.parseNumVl(request.getParameter("refid"), 0);
			String status = request.getParameter("status");
			int orgid = Util.parseNumVl(request.getParameter("orgid"), -1);
			int pid = Util.parseNumVl(request.getParameter("pid"), 0);
			String serventrymemo = request.getParameter("serventrymemo");

			ServiceEntryEto srvEntry = new ServiceEntryEto();
			srvEntry.setPid(pid);
			srvEntry.setSrvName(serventryname);
			srvEntry.setSrvType(serventrytype);
			srvEntry.setSrvTypeId(refid);
			srvEntry.setFlag(status);
			srvEntry.setOrgId(orgid);
			srvEntry.setSrvMemo(serventrymemo);
			if (Util.isNull(code)) {
				srvEntryService.addSrvEntry(srvEntry);
				map.put("result", 1);
				map.put("message", "添加服务成功!");
			} else {
				ServiceEntryEbo srvEntryByIdOrCode = srvEntryService.getSrvEntryByIdOrCode(-1, code);
				if (srvEntryByIdOrCode != null) {
					srvEntryByIdOrCode.setPid(pid);
					srvEntryByIdOrCode.setSrvName(serventryname);
					srvEntryByIdOrCode.setSrvType(serventrytype);
					srvEntryByIdOrCode.setSrvTypeId(refid);
					srvEntryByIdOrCode.setFlag(status);
					srvEntryByIdOrCode.setOrgId(orgid);
					srvEntryByIdOrCode.setSrvMemo(serventrymemo);
					srvEntryService.updateSrvEntry(srvEntryByIdOrCode);
					map.put("result", 1);
					map.put("message", "修改服务成功!");
				}
			}

		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 获取服务主窗口回显数据
	@RequestMapping(value = "/mgr_getyservEntryjson.html")
	public @ResponseBody
	Map<String, Object> getEntryJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);
		try {
			String code = request.getParameter("code");
			ServiceEntryEbo s = srvEntryService.getSrvEntryByIdOrCode(-1, code);
			if (s != null) {
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("srvCode", s.getSrvCode());
				data.put("srvName", s.getSrvName());
				data.put("srvType", s.getSrvType());
				data.put("srvTypeId", s.getSrvTypeId());
				data.put("status", s.getFlag());
				data.put("orgid", s.getOrgId());
				data.put("srvMemo", s.getSrvMemo());
				map.put("data", data);
			} else {
				map.put("result", 0);
				map.put("message", "没有找到对应服务!");
			}
			map.put("result", 1);
			map.put("message", "获取数据成功!");
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 获取服务配置回显数据
	@RequestMapping(value = "/mgr_getyservConfigjson.html")
	public @ResponseBody
	Map<String, Object> getServConfigJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);
		try {
			int id = Util.parseNumVl(request.getParameter("id"), 0);
			ServiceConfigEbo s = srvEntryService.getSrvConfigById(id);
			if (s != null) {
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("acceptorpoint", s.getAcceptorPoint());
				data.put("providerpoint", s.getProviderPoint());
				map.put("data", data);
			} else {
				map.put("result", 0);
				map.put("message", "没有找到对应服务!");
			}
			map.put("result", 1);
			map.put("message", "获取数据成功!");
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 添加修改组织配置
	@RequestMapping(value = "/mgr_addormodifyservconfigjson.html")
	public @ResponseBody
	Map<String, Object> addOrModifyServConfigJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);
		try {
			int id = Util.parseNumVl(request.getParameter("id"), 0);
			int srvId = Util.parseNumVl(request.getParameter("srvId"), 0);
			int orgId = Util.parseNumVl(request.getParameter("orgId"), 0);
			Double acceptorpoint = Util.parseDoubleVl(request.getParameter("acceptorpoint"), 0.0);
			Double providerpoint = Util.parseDoubleVl(request.getParameter("providerpoint"), 0.0);

			ServiceConfigEto srvConfig = new ServiceConfigEto();
			srvConfig.setSrvId(srvId);
			srvConfig.setOrgId(orgId);
			srvConfig.setAcceptorPoint(acceptorpoint);
			srvConfig.setProviderPoint(providerpoint);

			if (id == 0) {
				srvEntryService.addSrvConfig(srvConfig);
				map.put("result", 1);
				map.put("message", "添加配置成功！！！");
			} else {
				ServiceConfigEbo srvConfigById = srvEntryService.getSrvConfigById(id);
				srvConfigById.setAcceptorPoint(acceptorpoint);
				srvConfigById.setProviderPoint(providerpoint);
				BeanUtils.copyProperties(srvConfigById, srvConfig);
				srvEntryService.addSrvConfig(srvConfig);
				map.put("result", 1);
				map.put("message", "修改配置成功！！！");
			}

		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 获取组织ID
	@RequestMapping(value = "/getorgidjson.html")
	public @ResponseBody
	Map<String, Object> getOrgIdJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);
		try {
			int srvId = Util.parseNumVl(request.getParameter("srvId"), 0);
			ServiceEntryEbo s = srvEntryService.getSrvEntryByIdOrCode(srvId, null);
			if (s != null) {
				map.put("orgid", s.getOrgId());
			} else {
				map.put("result", 0);
				map.put("message", "没有找到对应服务!");
			}
			map.put("result", 1);
			map.put("message", "获取数据成功!");
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}
}
