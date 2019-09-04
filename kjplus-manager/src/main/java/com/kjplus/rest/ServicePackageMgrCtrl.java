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
import org.ybk.basic.util.DateUtil;
import org.ybk.basic.util.Util;

import com.kjplus.constant.Constant;
import com.kjplus.constant.ManagerPageFtl;
import com.kjplus.constant.ManagerUrl;
import com.kjplus.dao.IInfoDao;
import com.kjplus.dto.FuncDto;
import com.kjplus.dto.KjAdminUserDto;
import com.kjplus.eto.ServicePackageEto;
import com.kjplus.model.ServicePackageEbo;
import com.kjplus.model.SysFuncEbo;
import com.kjplus.model.SysRefValueEbo;
import com.kjplus.model.inner.ServicePackageInnerEbo;
import com.kjplus.service.IInfoService;
import com.kjplus.service.IOrgService;
import com.kjplus.service.IServiceAssignService;
import com.kjplus.service.IServicePackageService;
import com.kjplus.service.ISysBaseService;
import com.kjplus.service.ISysFuncService;
import com.ybk.exception.DataException;

@Controller
public class ServicePackageMgrCtrl {

	private static Logger logger = Logger.getLogger(ServicePackageMgrCtrl.class);
	@Autowired
	private IInfoDao infoDao;
	@Autowired
	private IInfoService infoService;
	@Autowired
	private ISysFuncService funcService;
	@Autowired
	private IOrgService orgService;
	@Autowired
	private IServiceAssignService srvAssignService;
	@Autowired
	private IServicePackageService srvPackageService;
	@Autowired
	private ISysBaseService sysBaseService;

	// 居民服务包入口 页面跳转
	@RequestMapping(ManagerUrl.LIST_SRV_PACKAGE)
	public ModelAndView srvPackageList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = mav.getModel();
		mav.setViewName(ManagerPageFtl.SRV_PACKAGE_LIST);

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
		if (userEbo == null) {
			mav.setViewName(ManagerPageFtl.LOGIN_ACE);
			map.put("result", -1);
			map.put("returnUrl", ManagerUrl.LIST_SRV_PACKAGE);
			map.put("message", "用户没有登陆!");
			return mav;
		}
		try {
			// 获取菜单数据
			SysFuncEbo func = funcService.getFuncByMenu(ManagerUrl.LIST_SRV_PACKAGE, Constant.MENU_TYPE_MANAGER);
			if (func != null)
				map.put("pmenu", func.getPid());
			else
				map.put("pmenu", -1);
			List<FuncDto> funcs = funcService.listUserFunc(userEbo.getUid());
			map.put("funcs", funcs);

			/*
			 * // 组织列表 数据筛选 List<OrgDto> listOrg = orgService.listOrg(null,
			 * null, 0, 0, false, 0, 0); map.put("listOrg", listOrg);
			 */
			// 服务周期 参照列表 数据筛选
			List<SysRefValueEbo> refVl = sysBaseService.listRefVlByRefCode("SRV_PERIOD", Constant.FLAG_YES);
			map.put("refVl", refVl);

		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return mav;
	}

	// 获取服务包列表数据
	@RequestMapping(ManagerUrl.LIST_SRV_PACKAGE_JSON)
	public @ResponseBody
	Map<String, Object> listSrvPackageJson(HttpServletRequest request, HttpServletResponse response) {

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
			String status = request.getParameter("status");
			String isDefault = request.getParameter("isDefault");
			String periodCode = request.getParameter("periodCode");
			String name = request.getParameter("name");
			int periodVal = Util.parseNumVl(request.getParameter("periodVal"), 0);
			// 起始时间 年月日 00:00:00
			String startDay = request.getParameter("startday");
			String finishDay = request.getParameter("finishday");
			// TODO
			if (Util.isNotNull(finishDay))
				finishDay = finishDay + " 23:59:59";
			List<ServicePackageInnerEbo> listSrvPackage = srvPackageService.listSrvPackageInner(name, orgId, status, isDefault, periodCode, periodVal, startDay, finishDay, page, iDisplayLength);

			List<List<Object>> aaData = new ArrayList<List<Object>>();
			List<Object> aaList = null;
			for (ServicePackageInnerEbo srv : listSrvPackage) {
				aaList = new ArrayList<Object>();
				aaList.add(srv.getId());
				aaList.add(srv.getCode());
				aaList.add(srv.getName());
				aaList.add(srv.getAlias());
				aaList.add(srv.getSrvPrice());
				aaList.add(srv.getIsDefault());
				aaList.add(srv.getStatus());
				aaList.add(srv.getPeriodTypeId());
				aaList.add(srv.getPeriodVal());
				aaList.add(srv.getPeriodName());
				aaList.add(srv.getMemo());
				aaList.add(DateUtil.formatDateTime(srv.getCreateTime()));
				aaData.add(aaList);
			}
			map.put("data", aaData);
			map.put("orgId", orgId);
			map.put("result", 1);

			int totalRec = srvPackageService.getTotalSrvPackage(name, orgId, status, isDefault, periodCode, periodVal);
			map.put("iTotalRecords", totalRec);
			map.put("iTotalDisplayRecords", totalRec);
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 获取组织服务列表修改回显数据
	@RequestMapping(ManagerUrl.GET_SRV_PACKAGE_JSON)
	public @ResponseBody
	Map<String, Object> getServCatgJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		String code = request.getParameter("code");
		try {
			ServicePackageEbo srv = srvPackageService.getSrvPackageByIdOrCode(0, code);
			if (srv == null) {
				map.put("result", -1);
				map.put("messge", "没有找到对于的信息内容的编码'" + code + "'");
				return map;
			}
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("code", Util.val(srv.getCode()));
			data.put("name", Util.val(srv.getName()));
			data.put("alias", Util.val(srv.getAlias()));
			data.put("memo", Util.val(srv.getMemo()));
			data.put("price", srv.getSrvPrice());
			data.put("defa", srv.getIsDefault());
			data.put("status", srv.getStatus());
			SysRefValueEbo refValue = sysBaseService.getRefVlById(srv.getPeriodTypeId());
			// data.put("periodTypeId", srv.getPeriodTypeId());
			data.put("periodTypeCode", Util.val(refValue.getCode()));
			data.put("periodVal", Util.val(srv.getPeriodVal()));
			map.put("data", data);
			map.put("result", 1);
			map.put("messge", "获取居民服务包数据成功！");
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 添加或修改居民服务包基本信息
	@RequestMapping(ManagerUrl.ADDORMODIFY_SRV_PACKAGE_JSON)
	public @ResponseBody
	Map<String, Object> addOrModifySrvPackage(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);

		try {

			ServicePackageEto srvPackage = new ServicePackageEto();
			String code = request.getParameter("code");
			String name = request.getParameter("name");
			String alias = request.getParameter("alias");
			String memo = request.getParameter("memo");
			// String orgCode = request.getParameter("orgCode");
			Double price = Util.parseDoubleVl(request.getParameter("price"), 0);
			// String defa = request.getParameter("defa");
			String status = request.getParameter("status");
			String periodCode = request.getParameter("periodCode");
			int periodVal = Util.parseNumVl(request.getParameter("val"), 0);
			srvPackage.setCode(code);
			srvPackage.setName(name);
			srvPackage.setAlias(alias);
			srvPackage.setMemo(memo);
			srvPackage.setOrgId(userEbo.getOrgId());
			srvPackage.setSrvPrice(price);
			// srvPackage.setIsDefault(defa);
			srvPackage.setStatus(status);
			srvPackage.setPeriodCode(periodCode);
			srvPackage.setPeriodVal(periodVal);
			if (Util.isNull(code)) {
				srvPackageService.addSrvPackage(srvPackage);
				map.put("message", "添加居民服务包成功成功！");
				map.put("result", 1);
				return map;
			} else {
				ServicePackageEbo s = srvPackageService.getSrvPackageByIdOrCode(0, code);
				if (s != null) {
					SysRefValueEbo refValue = sysBaseService.getRefVlByCode(periodCode);
					ServicePackageEbo srv = new ServicePackageEbo();
					BeanUtils.copyProperties(srvPackage, srv);
					srv.setPeriodTypeId(refValue.getId());
					srvPackageService.updateSrvPackage(srv);
					map.put("result", 1);
					map.put("message", "修改居民服务包成功！");
					return map;
				}
			}
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 修改居民默认服务包
	@RequestMapping(ManagerUrl.ADDORMODIFY_SRV_PACKAGE_ISDEFAULT)
	public @ResponseBody
	Map<String, Object> modifySrvPackageIsDefault(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		try {
			String code = request.getParameter("code");
			ServicePackageEbo srv = srvPackageService.getSrvPackageByIdOrCode(0, code);
			if (srv != null)
				srvPackageService.updateSrvPackageIsDefault(0, code);
			srv = srvPackageService.getSrvPackageByIdOrCode(0, code);
			if (Util.val(srv.getIsDefault()).equals(Constant.FLAG_YES)) {
				map.put("result", 1);
				map.put("message", "修改默认居民服务包成功！");
				return map;

			} else {
				map.put("result", -1);
				map.put("message", "修改默认服务包居民服务包失败！");
				return map;
			}
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}
	/*
	 * // 通过refvalueid获取refvalue
	 * 
	 * @RequestMapping(value = "/getorgcodebyidjson.html") public @ResponseBody
	 * Map<String, Object> getrefvaluebyid(HttpServletRequest request,
	 * HttpServletResponse response) {
	 * 
	 * Map<String, Object> map = new HashMap<String, Object>(); map.put("code",
	 * 200);
	 * 
	 * int orgid = Util.parseNumVl(request.getParameter("orgid"), 0); try {
	 * OrgEbo orgById = orgService.getOrgById(orgid); String orgCode =
	 * orgById.getCode(); map.put("orgCode", orgCode); map.put("result", 1); }
	 * catch (DataException e) { logger.error(e); map.put("result", -1);
	 * map.put("message", e.getMessage()); } return map; }
	 */
}
