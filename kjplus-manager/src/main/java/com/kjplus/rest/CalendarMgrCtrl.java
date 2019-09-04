package com.kjplus.rest;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.ybk.basic.util.DateUtil;
import org.ybk.basic.util.Util;

import com.alibaba.fastjson.JSONArray;
import com.kjplus.constant.*;
import com.kjplus.dao.ICalendarMainDao;
import com.kjplus.dto.*;
import com.kjplus.eto.CalendarEntryEto;
import com.kjplus.eto.CalendarInfoEto;
import com.kjplus.eto.InfoEto;
import com.kjplus.model.*;
import com.kjplus.qto.CalEntryInfoQto;
import com.kjplus.service.*;
import com.ybk.exception.DataException;

@Controller
public class CalendarMgrCtrl {

	private static Logger logger = Logger.getLogger(CalendarMgrCtrl.class);
	@Autowired
	private ITableService tableService;
	@Autowired
	private IStaffService staffService;
	@Autowired
	private ISysFuncService funcService;
	@Autowired
	private ICalendarMainService calMainService;
	@Autowired
	private IUserMapService userMapService;
	@Autowired
	private ICalendarMainDao calMainDao;
	@Autowired
	private IInfoService infoService;
	@Autowired
	private ISysBaseService sysBaseService;
	
	// 我的日历
	@RequestMapping("mycals.html")
	public ModelAndView createDocument(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = mav.getModel();
		mav.setViewName("mycal");

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
		if (userEbo == null) {
			mav.setViewName(ConsolePageFtl.LOGIN_ACE);
			map.put("result", -1);
			map.put("returnUrl", "/mycals.html");
			map.put("message", "用户没有登陆!");
			return mav;
		}

		try {
			SysFuncEbo func = funcService.getFuncByMenu("departmentlist.html", Constant.MENU_TYPE_MANAGER);
			if (func != null)
				map.put("pmenu", func.getPid());
			else
				map.put("pmenu", -1);
			List<FuncDto> funcs = funcService.listUserFunc(userEbo.getUid());
			map.put("funcs", funcs);

			List<CalendarEntryEbo> entrys = calMainService.listCalEntry(-1, null, userEbo.getUid(), userEbo.getOrgId(),
					null);
			map.put("entrys", entrys); 			
			List<SysRefValueEbo> entryType = sysBaseService.listRefVlByRefCode("RT_CAL_TYPE", "Y");
			for(CalendarEntryEbo entry : entrys){
				if("A".equals(entry.getEntryType())){
					for(int i = entryType.size() -1 ;i > 0 ; i-- ){
						if("活动".equals(entryType.get(i).getName())){
							entryType.remove(i); 
						}
					}
				}
			}
			map.put("entrytypes", entryType);
			
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return mav;
	}
	
	//获取日历信息
	@RequestMapping(value = "/mgr_calinfosjson.html")
	public @ResponseBody
	Map<String, Object> calInfosJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
		if (userEbo == null) {
			map.put("result", -1);
			map.put("message", "用户没有登陆!");
			return map;
		}

		try {
			// 逗号分割
			String calIds = request.getParameter("calids");
			int calId = 0;
			String entryFlag = request.getParameter("entryflag");
			if (Util.isNull(entryFlag))
				entryFlag = Constant.FLAG_YES;
			String infoFlag = request.getParameter("infoflag");
			if (Util.isNull(infoFlag))
				infoFlag = Constant.FLAG_YES;
			String frmDay = request.getParameter("startday");
			String endDay = request.getParameter("endday");
			List<Map<String, Object>> calDatas = new ArrayList<Map<String, Object>>();
			List<String> calList = Util.str2Array(calIds);
			for (String calidStr : calList) {
				calId = Util.parseNumVl(calidStr, 0);
				CalEntryInfoQto calEntryInfoQto = new CalEntryInfoQto();
				calEntryInfoQto.setEntryId(calId);
				calEntryInfoQto.setEntryFlag(entryFlag);
				calEntryInfoQto.setInfoFlag(infoFlag);
				calEntryInfoQto.setStartTime(DateUtil.parseTimeStrInSec(frmDay));
				calEntryInfoQto.setEndTime(DateUtil.parseTimeStrInSec(endDay));
				List<CalendarEntryDto> listEntry = calMainService.listEntryInfo(calEntryInfoQto, 0, -1);
				//listEntry只有一条总记录
				List<CalendarInfoDto> cals = listEntry.get(0).getListCalInfo(); 
				CalendarEntryDto calEntry = listEntry.get(0);
				for (CalendarInfoDto ci : cals) {
					Map<String, Object> caline = new HashMap<String, Object>();
					caline.put("id", ci.getInfoId());
					caline.put("defid", calEntry.getEntryId());
					caline.put("code", ci.getInfoCode());
					caline.put("title", ci.getInfoTitle());
					caline.put("memo", ci.getInfoMemo());
					caline.put("start", DateUtil.formatDateTime(ci.getInfoStartTime()));
					caline.put("end", DateUtil.formatDateTime(ci.getInfoEndTime()));
					caline.put("maxperson", ci.getInfoMaxPerson());
					caline.put("showclass", calEntry.getEntryShowClass());
					calDatas.add(caline);
				}
			}
			map.put("data", calDatas);
			map.put("result", 1);
		} catch (DataException e) {
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	@RequestMapping(value = "/mgr_getcalinfojson.html")
	public @ResponseBody
	Map<String, Object> getCalInfoJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		try {
			int id = Util.parseNumVl(request.getParameter("infoid"), 0);
			CalendarInfoEbo calInfo = calMainService.getCalInfoByIdOrCode(id, null);
			map.put("data", calInfo);
			map.put("result", 1);
		} catch (DataException e) {
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	@RequestMapping(value = "/mgr_addcalentryson.html")
	public @ResponseBody
	Map<String, Object> addCalEntryJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
		if (userEbo == null) {
			map.put("result", -1);
			map.put("message", "用户没有登陆!");
			return map;
		}
		
		String name = request.getParameter("name");
		int caltypeid = Util.parseNumVl(request.getParameter("caltypeid"), 0);
		
		try {
			int staffId = userMapService.getStaffIdByAdminUserId(userEbo.getUid());
			CalendarEntryEto calEntry = new CalendarEntryEto();
			calEntry.setCalTypeId(caltypeid);
			
			calEntry.setMainId(staffId);
			calEntry.setMainType(Constant.CREATE_TYPE_STAFF);
			calEntry.setName(name);
			calEntry.setCreateId(userEbo.getUid());
			calEntry.setOrgId(userEbo.getOrgId());
			CalendarEntryEbo entry = calMainService.addCalEntry(calEntry, false);
			map.put("data", entry.getId());
			map.put("result", 1);
			map.put("message", "添加日历类型成功!");
		} catch (DataException e) {
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	@RequestMapping(value = "/mgr_listcalentrytypeson.html")
	public @ResponseBody
	Map<String, Object> listCalEntryTypeJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
		if (userEbo == null) {
			map.put("result", -1);
			map.put("message", "用户没有登陆!");
			return map;
		}

		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> actDatas = new  ArrayList<Map<String, Object>>();
		String defidStr = request.getParameter("defids");
		if (Util.isNull(defidStr)) {
			map.put("data", datas);
			map.put("result", 1);
			map.put("message", "空参数!");
			return map;
		}

		try {
			List<String> defIdList = Util.str2Array(defidStr);
			List<Integer> defIds = new ArrayList<Integer>();
			int defid = 0;
			actDatas = new ArrayList<Map<String, Object>>();
			for (String s : defIdList) {
				defid = com.mq.util.Util.parseNumVl(s, 0);
				if (defid == 0)
					continue;
				
				CalendarEntryEbo entry = calMainService.getCalEntryByIdOrCode(defid, null);
				if("A".equals(entry.getEntryType())){
					List<InfoDto> listInfo= infoService.listInfo(null, 0, null,null, userEbo.getOrgId(), null, false, "A", 0, 100);
					for (InfoDto infoDto : listInfo) {
						Map<String, Object> m = new HashMap<String, Object>();
						m.put("typeid", infoDto.getId());
						m.put("defid", defid);
						m.put("title", infoDto.getInfoTitle());
						m.put("memo", Util.val(infoDto.getInfoDesc()));
						m.put("maxPerson", 100);
						m.put("showClass", entry.getShowClass());
						m.put("timeInterval", 120);
						actDatas.add(m);
					}
				}else{
					defIds.add(defid);
				}
			}
			datas = new ArrayList<Map<String, Object>>();
			List<CalendarEventTypeEbo> listCalEventType = calMainService.listCalEventType(defIds);
			for (CalendarEventTypeEbo ci : listCalEventType) {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("typeid", ci.getId());
				m.put("defid", ci.getDefId());
				m.put("title", ci.getTitle());
				m.put("memo", Util.val(ci.getMemo()));
				m.put("maxPerson", ci.getMaxPerson());
				m.put("showClass", ci.getShowClass());
				m.put("timeInterval", ci.getTimeInterval());
				datas.add(m);
			}
			map.put("data", datas);
			map.put("actdata", actDatas);
			map.put("result", 1);
			map.put("message", "罗列日历类型成功!");
		} catch (DataException e) {
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	@RequestMapping(value = "/mgr_addcalentrytypejson.html")
	public @ResponseBody
	Map<String, Object> addCalEntryTypeJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
		if (userEbo == null) {
			map.put("result", -1);
			map.put("message", "用户没有登陆!");
			return map;
		}

		String title = request.getParameter("title");
		String memo = request.getParameter("memo");
		String showClass = request.getParameter("showclass");
		int defid = Util.parseNumVl(request.getParameter("defid"), 0);
		int maxPerson = Util.parseNumVl(request.getParameter("maxperson"), 0);
		int timeInterval = Util.parseNumVl(request.getParameter("timeinterval"), 60);
		try {

			CalendarEventTypeEbo calEventType = new CalendarEventTypeEbo();
			calEventType.setDefId(defid);
			calEventType.setTitle(title);
			calEventType.setTimeInterval(timeInterval);
			calEventType.setMaxPerson(maxPerson);
			calEventType.setShowClass(showClass);
			calEventType.setMemo(memo);
			calMainDao.addCalEventType(calEventType);
			//map.put("data", entry.getId());
			map.put("result", 1);
			map.put("message", "添加日历事件类型成功!");
		} catch (DataException e) {
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	@RequestMapping(value = "/mgr_addormodifycalinfojson.html")
	public @ResponseBody
	Map<String, Object> addOrModifyCalInfoJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		String calinfojson = request.getParameter("calinfojson");

		if (Util.isNull(calinfojson)) {
			map.put("result", -1);
			map.put("message", "空日历行对象!");
			return map;
		}

		int defid = 0;
		String memo = null;
		String title = null;
		String startTime = null;
		String endTime = null;
		int startTimeInSec = 0;
		int endTimeInSec = 0;
		int maxPerson = 0;
		int timeInterval = 0;
		int infoid = 0;
		String trace = null;

		try {
			System.out.println(calinfojson);
			JSONArray ciArr = JSONArray.parseArray(calinfojson);
			System.out.println("转换后" + ciArr);
			if (ciArr == null || ciArr.size() == 0) {
				map.put("result", -1);
				map.put("message", "空日历行获得日历数据非法!");
				return map;
			}

			List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < ciArr.size(); i++) {
				infoid = Util.parseNumVl(ciArr.getJSONObject(i).getString("infoid"), 0);
				memo = ciArr.getJSONObject(i).getString("memo");
				title = ciArr.getJSONObject(i).getString("title");
				trace = ciArr.getJSONObject(i).getString("trace");
				startTime = ciArr.getJSONObject(i).getString("starttime");
				if (Util.isNotNull(startTime) && startTime.length() > 19) {
					startTime = startTime.substring(0, 19);
					startTime = Util.replace(startTime, "T", " ");
				}

				endTime = ciArr.getJSONObject(i).getString("endtime");
				if (Util.isNotNull(endTime) && endTime.length() > 19) {
					endTime = endTime.substring(0, 19);
					endTime = Util.replace(endTime, "T", " ");
				}
				timeInterval = Util.parseNumVl(ciArr.getJSONObject(i).getString("timeinterval"), 0);

				if (Util.isNull(startTime) || "null".equals(startTime))
					continue;
				startTimeInSec = DateUtil.parseTimeStrInSec(startTime);
				// timeInterval分钟
				if (Util.isNull(endTime) || "null".equals(endTime))
					endTimeInSec = startTimeInSec + timeInterval * 60;
				else {
					endTimeInSec = DateUtil.parseTimeStrInSec(endTime);
				}

				maxPerson = Util.parseNumVl(ciArr.getJSONObject(i).getString("maxperson"), 0);

				Map<String, Object> res = new HashMap<String, Object>();

				if (infoid == 0) {
					defid = Util.parseNumVl(ciArr.getJSONObject(i).getString("defid"), 0);
					if (defid == 0)
						continue;
					CalendarInfoEto calInfo = new CalendarInfoEto();
					calInfo.setCalEntryId(defid);
					calInfo.setCalMemo(memo);
					calInfo.setCalTitle(title);
					calInfo.setStartTime(startTimeInSec);
					calInfo.setEndTime(endTimeInSec);
					calInfo.setMaxPerson(maxPerson);
					CalendarInfoEbo ci = calMainService.addCalInfo(calInfo);
					res.put("trace", trace);
					res.put("id", ci.getId());
					res.put("oper", "ADD");
				} else {
					if ("D".equalsIgnoreCase(ciArr.getJSONObject(i).getString("modify"))) {
						calMainService.delCalInfo(infoid);
						res.put("trace", trace);
						res.put("id", infoid);
						res.put("oper", "DEL");
					} else {
						String flag = ciArr.getJSONObject(i).getString("flag");
						calMainService.updateCalInfo(infoid, null, title, memo, maxPerson, -1, flag, startTimeInSec,
								endTimeInSec);
						res.put("trace", trace);
						res.put("id", infoid);
						res.put("oper", "MODIFY");
					}
				}
				resList.add(res);
			}
			map.put("data", resList);
			map.put("result", 1);
			map.put("message", "修改日历事件成功!");
		} catch (Exception e) {
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	@RequestMapping(value = "/mgr_updatecalentryinfojson.html")
	public @ResponseBody
	Map<String, Object> updateCalEntryInfoJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		int id = Util.parseNumVl(request.getParameter("infoid"), 0);
		// String code = request.getParameter("code");
		String title = request.getParameter("title");
		String memo = request.getParameter("memo");
		int maxPerson = Util.parseNumVl(request.getParameter("maxperson"), 0);
		int joinPerson = Util.parseNumVl(request.getParameter("joinPerson"), 0);
		String flag = request.getParameter("flag");
		int startTime = Util.parseNumVl(request.getParameter("startTime"), 0);
		int endTime = Util.parseNumVl(request.getParameter("endTime"), 0);

		try {
			calMainService.updateCalInfo(id, null, title, memo, maxPerson, joinPerson, flag, startTime, endTime);
			map.put("result", 1);
			map.put("message", "修改日历事件成功!");
		} catch (DataException e) {
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	@RequestMapping(value = "/mgr_addactivityinfojson.html")
	public @ResponseBody
	Map<String, Object> addActivityInfoJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
		if (userEbo == null) {
			map.put("result", -1);
			map.put("returnUrl", "/mycals.html");
			map.put("message", "用户没有登陆!");
		}
		
		String title = request.getParameter("title");
		String memo = request.getParameter("memo");

		try {
			InfoEto info = new InfoEto(); 
			info.setInfoTitle(title);
			info.setInfoDesc(memo);
			info.setFlag("Y");
			info.setOrgId(userEbo.getOrgId());
			info.setPubTime(DateUtil.newTime());
			info.setInfoType("A");
			
			infoService.addInfo(info);
			map.put("result", 1);
			map.put("message", "修改日历事件成功!");
		} catch (DataException e) {
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	
}
