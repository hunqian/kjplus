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
import com.kjplus.constant.ManagerPageFtl;
import com.kjplus.constant.ManagerUrl;
import com.kjplus.dao.IOrgDao;
import com.kjplus.dto.FuncDto;
import com.kjplus.dto.KjAdminUserDto;
import com.kjplus.dto.ServiceEntryDto;
import com.kjplus.eto.ServiceConfigEto;
import com.kjplus.eto.ServiceEntryEto;
import com.kjplus.model.ServiceConfigEbo;
import com.kjplus.model.ServiceEntryEbo;
import com.kjplus.model.SysFuncEbo;
import com.kjplus.model.SysRefValueEbo;
import com.kjplus.service.IOrgService;
import com.kjplus.service.IServiceEntryService;
import com.kjplus.service.ISysBaseService;
import com.kjplus.service.ISysFuncService;
import com.kjplus.service.ISysRegionsService;
import com.mq.util.DateUtil;
import com.ybk.exception.DataException;

@Controller
public class ServiceEntryMgrCtrl {

	private static Logger logger = Logger.getLogger(ServiceEntryMgrCtrl.class);
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

	// 组织服务列表页面跳转
	@RequestMapping(ManagerUrl.LIST_SRV_ENTRY)
	public ModelAndView orgList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = mav.getModel();
		mav.setViewName(ManagerPageFtl.SRV_ENTRY_LIST);

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
		if (userEbo == null) {
			mav.setViewName(ConsolePageFtl.LOGIN_ACE);
			map.put("result", -1);
			map.put("returnUrl", ManagerUrl.LIST_SRV_ENTRY);
			map.put("message", "用户没有登陆!");
			return mav;
		}

		try {
			SysFuncEbo func = funcService.getFuncByMenu(ManagerUrl.LIST_SRV_ENTRY, Constant.MENU_TYPE_MANAGER);
			if (func != null)
				map.put("pmenu", func.getPid());
			else
				map.put("pmenu", -1);
			List<FuncDto> funcs = funcService.listUserFunc(userEbo.getUid());
			map.put("funcs", funcs);

			// 服务类型 对应到汇总的参照数据
			List<SysRefValueEbo> refVl = baseService.listRefVlByRefCode("RT_SERVICE", Constant.FLAG_YES);
			map.put("refVl", refVl);

		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return mav;
	}

	// 列表组织服务
	@RequestMapping(value = "/mgr_srventrylistjson.html")
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
			KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
			int orgId = userEbo.getOrgId();
			String flag = request.getParameter("status");
			String name = request.getParameter("name");
			String type = request.getParameter("type");
			int typeId = Util.parseNumVl(request.getParameter("typeId"), 0);
			List<Integer> typeIds = new ArrayList<Integer>();
			if (typeId != 0)
				typeIds.add(typeId);
			String opType = request.getParameter("opType");
			List<ServiceEntryDto> listServiceEntry = srvEntryService.listServiceEntry(orgId, typeIds, type, opType, name, flag, page, iDisplayLength, false, true);
			List<List<Object>> aaData = new ArrayList<List<Object>>();
			List<Object> aaList = null;
			for (ServiceEntryDto srv : listServiceEntry) {
				aaList = new ArrayList<Object>();
				aaList.add(srv.getId());
				aaList.add(srv.getPid());
				aaList.add(srv.getSrvType());
				aaList.add(srv.getSrvCode());
				aaList.add(srv.getSrvName());
				aaList.add(srv.getSrvTypeCode());
				aaList.add(srv.getSrvTypeName());
				aaList.add(srv.getFlag());
				aaList.add(srv.getSrvMemo());
				aaList.add(srv.getAcceptorPoint());
				aaList.add(srv.getProviderPoint());
				aaList.add(DateUtil.formatDateTime(srv.getCreateTime()));
				aaList.add(srv.getOrgCode());
				aaList.add(srv.getOrgName());
				aaData.add(aaList);
			}
			int totalRec = srvEntryService.getTotalEntry(orgId, typeIds, type, opType, name, flag);
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

	// 添加修改组织服务包
	@RequestMapping(value = "/mgr_addormodifyserventryjson.html")
	public @ResponseBody
	Map<String, Object> addOrModifyServEntryJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);
		try {
			KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
			String code = request.getParameter("code");
			// 服务名
			String serventryname = request.getParameter("serventryname");
			// 服务类别，S服务类/M其它
			String serventrytype = request.getParameter("serventrytype");
			// 对应参照id，与t_table_cfg_head中的type_id统一 (代表与table对应)，也可不对应
			int refid = Util.parseNumVl(request.getParameter("refid"), 0);
			// 状态
			String status = request.getParameter("status");
			int orgid = userEbo.getOrgId();
			int pid = Util.parseNumVl(request.getParameter("pid"), 0);
			String serventrymemo = request.getParameter("serventrymemo");

			Double acceptorpoint = Util.parseDoubleVl(request.getParameter("acceptorpoint"), 0.0);
			Double providerpoint = Util.parseDoubleVl(request.getParameter("providerpoint"), 0.0);

			ServiceEntryEto srvEntry = new ServiceEntryEto();
			srvEntry.setPid(pid);
			srvEntry.setSrvName(serventryname);
			srvEntry.setSrvType(serventrytype);
			srvEntry.setSrvTypeId(refid);
			srvEntry.setFlag(status);
			srvEntry.setOrgId(orgid);
			srvEntry.setSrvMemo(serventrymemo);
			srvEntry.setAcceptorPoint(acceptorpoint);
			srvEntry.setProviderPoint(providerpoint);

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

					ServiceConfigEto srvConfig = new ServiceConfigEto();
					srvConfig.setSrvId(srvEntryByIdOrCode.getId());
					srvConfig.setOrgId(srvEntryByIdOrCode.getOrgId());
					srvConfig.setAcceptorPoint(acceptorpoint);
					srvConfig.setProviderPoint(providerpoint);
					srvEntryService.addSrvConfig(srvConfig);
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
	@RequestMapping(value = "/mgr_getservEntryjson.html")
	public @ResponseBody
	Map<String, Object> getEntryJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);
		try {
			String code = request.getParameter("code");
			ServiceEntryEbo s = srvEntryService.getSrvEntryByIdOrCode(-1, code);
			List<ServiceConfigEbo> confs = new ArrayList<ServiceConfigEbo>();
			if (s != null)
				confs = srvEntryService.listSrvConfigEbo(s.getId(), s.getOrgId());
			if (s != null) {
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("srvCode", s.getSrvCode());
				data.put("srvName", s.getSrvName());
				data.put("srvType", s.getSrvType());
				data.put("srvTypeId", s.getSrvTypeId());
				data.put("status", s.getFlag());
				data.put("srvMemo", s.getSrvMemo());
				if (confs.size() > 0) {
					data.put("acceptorpoint", confs.get(0).getAcceptorPoint());
					data.put("providerpoint", confs.get(0).getProviderPoint());
				}
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

}
