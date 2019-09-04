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
import com.kjplus.dto.IDNameDto;
import com.kjplus.dto.KjAdminUserDto;
import com.kjplus.dto.OrgDto;
import com.kjplus.dto.SysRegionsDto;
import com.kjplus.eto.OrgEto;
import com.kjplus.model.OrgEbo;
import com.kjplus.model.SysFuncEbo;
import com.kjplus.model.SysRefValueEbo;
import com.kjplus.service.*;
import com.mq.util.DateUtil;
import com.ybk.exception.DataException;

@Controller
public class OrgCtrl {

	private static Logger logger = Logger.getLogger(OrgCtrl.class);
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
	
	// 组织列表
	@RequestMapping("/orglist.html")
	public ModelAndView orgList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = mav.getModel();
		mav.setViewName("org_list");
		
		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJCONSOLE_SESS);
		if (userEbo == null) {
			mav.setViewName(ConsolePageFtl.LOGIN_ACE);
			map.put("result", -1);
			map.put("returnUrl", "/orglist.html");
			map.put("message", "用户没有登陆!");
			return mav;
		}
		
		try{
			SysFuncEbo func = funcService.getFuncByMenu("orglist.html", Constant.MENU_TYPE_CONSOLE);
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

	@RequestMapping(value = "/mgr_orglistjson.html")
	public @ResponseBody
	Map<String, Object> mgrOrgListJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		int iDisplayStart = Util.parseNumVl(request.getParameter("iDisplayStart"), 0);
		int iDisplayLength = Util.parseNumVl(request.getParameter("iDisplayLength"), 10);
		int page = iDisplayStart / iDisplayLength;
		if (page <= 0)
			page = 0;

		try {
			String flag = request.getParameter("status");
			String orgName = request.getParameter("orgname");
			int prvnId = Util.parseNumVl(request.getParameter("prvnid"), 0);
			int cityId = Util.parseNumVl(request.getParameter("cityid"), 0);
			List<OrgDto> orgs = orgService.listOrg(orgName, flag, cityId, prvnId, false, page, iDisplayLength);
			List<List<Object>> aaData = new ArrayList<List<Object>>();
			List<Object> aaList = null;
			for (OrgDto org : orgs) {
				aaList = new ArrayList<Object>();
				aaList.add(org.getId());
				aaList.add(org.getCode());
				aaList.add(org.getName());
				aaList.add(org.getAlias());
				aaList.add(org.getPname());
				aaList.add(org.getAreaName());
				aaList.add(org.getCityName());
				aaList.add(org.getPrvnName());
				aaList.add(org.getAddr());
				aaList.add(org.getFlag());
				aaList.add(DateUtil.formatDateTime(org.getCreateTime()));
				aaData.add(aaList);
			}
			int totalRec = orgService.getTotalOrg(orgName, flag, cityId, prvnId, false);
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

	// 添加修改组织
	@RequestMapping(value = "/mgr_addormodifyorgjson.html")
	public @ResponseBody
	Map<String, Object> addOrModifyOrgJson(HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);
		try {
			String orgCode = request.getParameter("orgcode");
			String orgName = request.getParameter("orgname");
			String orgAlias = request.getParameter("orgalias");
			String orgAddr = request.getParameter("orgaddr");
			int pid = Util.parseNumVl(request.getParameter("pid"), 0);
			int prvnId = Util.parseNumVl(request.getParameter("prvnid"), 0);
			int cityId = Util.parseNumVl(request.getParameter("cityid"), 0);
			int areaId = Util.parseNumVl(request.getParameter("areaid"), 0);
			int orgTypeId = Util.parseNumVl(request.getParameter("orgtypeid"), 365);
			OrgEto org = new OrgEto();
			org.setName(orgName);
			org.setAlias(orgAlias);
			org.setAddr(orgAddr);
			org.setOrgTypeId(orgTypeId);
			org.setPrvnId(prvnId);
			org.setCityId(cityId);
			org.setAreaId(areaId);
			org.setPid(pid);
			if(Util.isNull(orgCode))
				orgService.addOrg(org);
			else{
				OrgEbo o2 = orgDao.getOrgByCode(orgCode);
				if(o2 != null)
					orgService.updateOrg(o2.getId(), org);
			}
			map.put("result", 1);
			map.put("message", "添加组织成功!");
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 获得组织
	@RequestMapping(value = "/mgr_getyorgjson.html")
	public @ResponseBody
	Map<String, Object> getOrgJson(HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);
		try {
			String orgCode = request.getParameter("orgcode");
			OrgEbo o2 = orgDao.getOrgByCode(orgCode);
			if(o2 != null){
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("code", o2.getCode());
				data.put("name", o2.getName());
				data.put("alias", o2.getAlias());
				data.put("addr", o2.getAddr());
				data.put("areaid", o2.getAreaId());
				data.put("cityid", o2.getCityId());
				data.put("prvnid", o2.getPrvnId());
				data.put("orgtypeid", o2.getOrgTypeId());
				data.put("headiconurl", Util.val(o2.getHeadIconurl()));
				map.put("data", data);
			}else{
				map.put("result", 0);
				map.put("message", "没有找到对应组织信息!");
			}
			map.put("result", 1);
			map.put("message", "添加组织成功!");
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}
	
	//ajax进行org列表筛选
	@RequestMapping(value = "/orglistjson.html")
	public @ResponseBody
	Map<String, Object> orgListJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		int maxrec = Util.parseNumVl(request.getParameter("maxrec"), 10);
		try {
			String flag = null;
			String orgName = request.getParameter("orgname");
			int prvnId = Util.parseNumVl(request.getParameter("prvnid"), 0);
			int cityId = Util.parseNumVl(request.getParameter("cityid"), 0);
			List<OrgDto> orgs = orgService.listOrg(orgName, flag, cityId, prvnId, false, 0, maxrec);
			List<IDNameDto> idns = new ArrayList<IDNameDto>();
			for (OrgDto org : orgs) {
				IDNameDto idn = new IDNameDto();
				idn.setId(org.getId());
				idn.setCode(org.getCode());
				idn.setName(org.getName());
				idns.add(idn);
			}
			map.put("data", idns);
			map.put("result", 1);
			map.put("message", "筛选组织成功！");
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}
	
		// 通过orgId获取组织
		@RequestMapping(value = "/getorgbyidjson.html")
		public @ResponseBody
		Map<String, Object> getorgbyid(HttpServletRequest request, HttpServletResponse response) {

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", 200);

			int orgId = Util.parseNumVl(request.getParameter("orgId"), 0);
			try {
				OrgEbo orgById = orgService.getOrgById(orgId);
				if (orgById != null) {
					String orgCode = orgById.getCode();
					map.put("orgCode", orgCode);
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
