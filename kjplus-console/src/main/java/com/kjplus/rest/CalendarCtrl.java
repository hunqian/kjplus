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
import org.ybk.basic.util.DateUtil;
import org.ybk.basic.util.Util;

import com.kjplus.constant.ConsolePageFtl;
import com.kjplus.constant.Constant;
import com.kjplus.dto.CalendarEntryDto;
import com.kjplus.dto.FuncDto;
import com.kjplus.dto.IDNameDto;
import com.kjplus.dto.KjAdminUserDto;
import com.kjplus.dto.OrgDto;
import com.kjplus.model.SysFuncEbo;
import com.kjplus.service.*;
import com.ybk.exception.DataException;
import com.ykisswx.constant.WxConstant;

@Controller
public class CalendarCtrl {

	private static Logger logger = Logger.getLogger(CalendarCtrl.class);
	
	@Autowired
	private ICalendarMainService calMainService; 
	@Autowired
	private ISysFuncService funcService;
	@Autowired
	private IOrgService orgService;
	
	// admin列表
	@RequestMapping("/calendarlist.html")
	public ModelAndView orgList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = mav.getModel();
		mav.setViewName("calendar_list");

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJCONSOLE_SESS);
		if (userEbo == null) {
			mav.setViewName(ConsolePageFtl.LOGIN_ACE);
			map.put("result", -1);
			map.put("returnUrl", "/adminuserlist.html");
			map.put("message", "用户没有登陆!");
			return mav;
		}
		
		try {
			SysFuncEbo func = funcService.getFuncByMenu("adminuserlist.html", Constant.MENU_TYPE_CONSOLE);
			if (func != null)
				map.put("pmenu", func.getPid());
			else
				map.put("pmenu", -1);
			List<FuncDto> funcs = funcService.listUserFunc(userEbo.getUid());
			
			//添加时，组织列表
			List<OrgDto> orgs = orgService.listOrg(null, WxConstant.FLAG_YES, 0, 0, false, 0, 10);
			List<IDNameDto> idns = new ArrayList<IDNameDto>();
			for (OrgDto org : orgs) {
				IDNameDto idn = new IDNameDto();
				idn.setId(org.getId());
				idn.setCode(org.getCode());
				idn.setName(org.getName());
				idns.add(idn);
			}
			map.put("orglist", idns);
			
			map.put("funcs", funcs);
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return mav;
	}

	// 日历列表
	@RequestMapping(value = "/calendarlistjson.html")
	public @ResponseBody
	Map<String, Object> adminUserListJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		int iDisplayStart = Util.parseNumVl(request.getParameter("iDisplayStart"), 0);
		int iDisplayLength = Util.parseNumVl(request.getParameter("iDisplayLength"), 10);
		int page = iDisplayStart / iDisplayLength;
		if (page <= 0)
			page = 0;

		try {
			
			String createType = request.getParameter("createtype");
			int mainId =Util.parseNumVl( request.getParameter("mainId"),0);
			int orgId = Util.parseNumVl(request.getParameter("orgId"),0);
			String mainType = request.getParameter("mainType");
			int startTime = Util.parseNumVl(request.getParameter("startTime"),0);
			int endTime = Util.parseNumVl(request.getParameter("endTime"),0);
			String entryFlag = request.getParameter("entryFlag");
			String infoFlag = request.getParameter("infoFlag");
			String entryCode = request.getParameter("entryCode");
			String infoCode = request.getParameter("infoCode");
			String srvCode = request.getParameter("srvCode");
			String srvFlag = request.getParameter("srvFlag");
			String configType = request.getParameter("configType");
			int dayEndTime = Util.parseNumVl(request.getParameter("dayEndTime"),0);
			
		/*	List<CalendarEntryDto> entryList= calMainService.listEntryInfo(createType, mainId, orgId, mainType, startTime, endTime, entryFlag
					, infoFlag, entryCode, infoCode, srvCode, srvFlag, configType, dayEndTime, page, iDisplayLength);*/
			List<CalendarEntryDto> entryList= new ArrayList<CalendarEntryDto>();
			List<List<Object>> aaData = new ArrayList<List<Object>>();
			List<Object> auList = null;
			for (CalendarEntryDto en : entryList) {
				auList = new ArrayList<Object>();
				auList.add(en.getEntryId());
				auList.add(Util.val(en.getEntryCode()));
				auList.add(Util.val(en.getEntryMainId()));
				/*auList.add(Util.val(en.getEntryMainCode()));
				auList.add(Util.val(en.getEntryMainName()));*/
				auList.add(Util.val(""));
				auList.add(Util.val(""));
				auList.add(Util.val(en.getEntryMainType()));
				auList.add(Util.val(en.getEntryName()));
				auList.add(Util.val(en.getEntryTypeId()));
				auList.add(Util.val(en.getEntryTypeCode()));
				auList.add(Util.val(en.getEntryTypeName()));
				auList.add(Util.val(en.getEntryOrgId()));
				auList.add(Util.val(en.getEntryOrgCode()));
				auList.add(Util.val(en.getEntryOrgName()));
				auList.add(Util.val(en.getEntryFlag()));
				auList.add(Util.val(DateUtil.formatDate(en.getEntryCreateTime())));
				aaData.add(auList);				
			}
			int total = entryList.size();
			map.put("aaData", aaData);
			map.put("result", 1);
			map.put("iTotalRecords", total);
			map.put("iTotalDisplayRecords", total);
		/*} catch (DataException e) {*/
		} catch (Exception e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}
}

