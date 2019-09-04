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
import com.kjplus.dto.SysRegionsDto;
import com.kjplus.model.SysFuncEbo;
import com.kjplus.model.SysRefTypeEbo;
import com.kjplus.model.SysRefValueEbo;
import com.kjplus.service.*;
import com.ybk.exception.DataException;

@Controller
public class RefCtrl {

	private static Logger logger = Logger.getLogger(RefCtrl.class);
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
	@RequestMapping("/reflist.html")
	public ModelAndView orgList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = mav.getModel();
		mav.setViewName("ref_list");
		
		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJCONSOLE_SESS);
		if (userEbo == null) {
			mav.setViewName(ConsolePageFtl.LOGIN_ACE);
			map.put("result", -1);
			map.put("returnUrl", "/reflist.html");
			map.put("message", "用户没有登陆!");
			return mav;
		}
		
		try{
			SysFuncEbo func = funcService.getFuncByMenu("reflist.html", Constant.MENU_TYPE_CONSOLE);
			if (func != null)
				map.put("pmenu", func.getPid());
			else
				map.put("pmenu", -1);
			List<FuncDto> funcs = funcService.listUserFunc(userEbo.getUid());
			map.put("funcs", funcs);
			
			List<SysRegionsDto> prvnList = regionsService.listRegions(0);
			map.put("prvnList", prvnList);
			
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return mav;
	}

	@RequestMapping(value = "/mgr_reftypelistjson.html")
	public @ResponseBody
	Map<String, Object> reftypeListJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		int iDisplayStart = Util.parseNumVl(request.getParameter("iDisplayStart"), 0);
		int iDisplayLength = Util.parseNumVl(request.getParameter("iDisplayLength"), 10);
		int page = iDisplayStart / iDisplayLength;
		if (page <= 0)
			page = 0;

		try {
			String refcode = request.getParameter("refcode");
			String refname  = request.getParameter("refname");
			String flag = request.getParameter("flag");
			if(Util.isNotNull(flag)){
				flag = flag.toUpperCase().substring(0,1);
			}
			List<SysRefTypeEbo> listRefType = baseService.listRefType(refcode, refname, flag, page, iDisplayLength);
			List<List<Object>> aaData = new ArrayList<List<Object>>();
			List<Object> aaList = null;
			for (SysRefTypeEbo ref : listRefType) {
				aaList = new ArrayList<Object>();
				aaList.add(ref.getId());
				aaList.add(ref.getRefCode());
				aaList.add(ref.getRefName());
				aaList.add(ref.getRefType());
				aaList.add(ref.getRefMemo());
				aaList.add(ref.getFlag());
				aaData.add(aaList);
			}
			int totalRec = baseService.getTotalRefType(refcode, refname, flag);
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
	
	@RequestMapping(value = "/mgr_refvaluelistjson.html")
	public @ResponseBody
	Map<String, Object> refvalueListJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		try {
			String flag = request.getParameter("flag");
			if(Util.isNotNull(flag)){
				flag = flag.toUpperCase().substring(0,1);
			}
			String refcode = request.getParameter("refcode");
			List<SysRefValueEbo> listRefVls = baseService.listRefVlByRefCode(refcode, flag);
			List<List<Object>> aaData = new ArrayList<List<Object>>();
			List<Object> aaList = null;
			for (SysRefValueEbo ref : listRefVls) {
				aaList = new ArrayList<Object>();
				aaList.add(ref.getId());
				aaList.add(ref.getCode());
				aaList.add(ref.getRefVl());
				aaList.add(ref.getName());
				aaList.add(ref.getFlag());
				aaData.add(aaList);
			}
			map.put("aaData", aaData);
			map.put("result", 1);
			map.put("iTotalRecords", listRefVls.size());
			map.put("iTotalDisplayRecords", listRefVls.size());
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}
	
	@RequestMapping(value = "/refvaluelist.html")
	public @ResponseBody
	Map<String, Object> refvaluelist(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		try {
			int refid =Util.parseNumVl( request.getParameter("refid"),0);
			List<SysRefValueEbo> listRefVls = baseService.getRefVlByRefId(refid,null);
			List<List<Object>> aaData = new ArrayList<List<Object>>();
			List<Object> aaList = null;
			for (SysRefValueEbo ref : listRefVls) {
				aaList = new ArrayList<Object>();
				aaList.add(ref.getId());
				aaList.add(ref.getCode());
				aaList.add(ref.getRefVl());
				aaList.add(ref.getName());
				aaList.add(ref.getFlag());
				aaData.add(aaList);
			}
			map.put("aaData", aaData);
			map.put("result", 1);
			map.put("iTotalRecords", listRefVls.size());
			map.put("iTotalDisplayRecords", listRefVls.size());
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}
	// 通过refvalueid获取refvalue
		@RequestMapping(value = "/getrefvaluebyidjson.html")
		public @ResponseBody
		Map<String, Object> getrefvaluebyid(HttpServletRequest request, HttpServletResponse response) {

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", 200);

			int refid = Util.parseNumVl(request.getParameter("refid"), 0);
			try {
				SysRefValueEbo refVlById = baseService.getRefVlById(refid);
				String refCode = refVlById.getCode();
				map.put("refCode", refCode);
				map.put("result", 1);
			} catch (DataException e) {
				logger.error(e);
				map.put("result", -1);
				map.put("message", e.getMessage());
			}
			return map;
		}
}
