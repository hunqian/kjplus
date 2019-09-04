package com.kjplus.rest;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kjplus.constant.*;
import com.kjplus.dto.*;
import com.kjplus.eto.AppointEto;
import com.kjplus.model.AppointEbo;
import com.kjplus.model.CalendarEntryEbo;
import com.kjplus.model.CalendarInfoEbo;
import com.kjplus.model.DepartmentEbo;
import com.kjplus.model.DocumentInfoEbo;
import com.kjplus.model.StaffEbo;
import com.kjplus.model.SysRefValueEbo;
import com.kjplus.model.UserPersonEbo;
import com.kjplus.model.inner.AppointInfoInnerEbo;
import com.kjplus.qto.CalEntryInfoQto;
import com.kjplus.service.*;
import com.mq.util.DateUtil;
import com.mq.util.Util;
import com.ybk.exception.DataException;

@Controller
public class AppointCtrl {

	private static Logger logger = Logger.getLogger(FamilyDoctorCtrl.class);
	
	@Autowired
	private IServiceEntryService srvEntryServie;
	@Autowired
	private ICalendarMainService calMainService;
	@Autowired
	private IAppointService appiontService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IUserMapService userMapService;
	@Autowired
	private ISysBaseService baseService;
	@Autowired
	private IDocInfoService docInfoService;
	@Autowired
	private IStaffService staffService;
	@Autowired
	private IDeptService deptService;
	@Autowired
	private ISysBaseService sysBaseService;

	// 预约服务页面	显示所有的预约记录
	@RequestMapping(value = ConstantUrlWx.WX_APPOINT_SERVICE)
	public ModelAndView appointService(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(ConstantPageFtl.WX_APPOINT_SERVICE);
		Map<String, Object> map = mav.getModelMap();
		map.put("code", 200);
		UserSess ui = (UserSess) request.getSession().getAttribute(Constant.KJMB_SESS);
		if (ui == null) {
			return new ModelAndView("redirect:" + ConstantUrlWx.WX_MBLOGIN + "?returnUrl=" + ConstantUrlWx.WX_APPOINT_SERVICE);
		}
		try {

			List<UserPersonEbo> listPrsn = userService.listUserPersonMap(ui.getUid(), Constant.USER_TYPE_UNIVERSIAL, 0);
			List<Integer> prsnIds = new ArrayList<Integer>();
			for (UserPersonEbo p : listPrsn) {
				prsnIds.add(p.getPrsnId());
			}
			// 获取与该用户有关系的用户的预约信息 只显示预约成功的
			List<AppointInfoInnerEbo> userInfoList = appiontService.listAppointInfo(0, prsnIds, ui.getOrgId(), 0, null, null, 0, 0,
					Constant.APPOINT_STATUS_CONFIRM, 0, 0, 0, 10);
			// mobile数据传输
			List<MblAppointDto> appInfoList = new ArrayList<MblAppointDto>();

			for (AppointInfoInnerEbo userInfo : userInfoList) {
				MblAppointDto m = new MblAppointDto();
				m.setPrsnId(userInfo.getPrsnId());
				m.setPrsnCode(userInfo.getPrsnCode());
				m.setName(userInfo.getPrsnName());// 用户名
				String relationTypeName = "";
				List<UserPersonEbo> listMap = userService.listUserPersonMap(ui.getUid(), "U", userInfo.getPrsnId());
				if (listMap.size() > 0) {
					int relationTypeId = listMap.get(0).getRelationTypeId();
					SysRefValueEbo rv = baseService.getRefVlById(relationTypeId);
					if (rv != null)
						relationTypeName = rv.getName();
				}
				m.setRelationTypeName(relationTypeName);// 关系
				String startTime = DateUtil.formatTime(userInfo.getInfoStartTime());// 活动开始时间
				String day = startTime.substring(0, 10);
				String time = startTime.substring(11, 16);
				m.setDay(day);// 预约天
				m.setTime(time);// 预约具体时间
				m.setMemo(userInfo.getInfoTitle());// 预约项目名称
				m.setStatus(userInfo.getStatus());// 预约状态
				m.setAppointCode(userInfo.getCode());// 预约记录code
				appInfoList.add(m);
			}
			map.put("appInfoList", appInfoList);
			map.put("result", 1);
			map.put("message", "预约列表加载成功！");
		} catch (Exception e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return mav;
	}

	// 取消预约
	@RequestMapping("/editappointstatus.html")
	public @ResponseBody
	Map<String, Object> editAppointStatus(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);
		String appointCode = request.getParameter("appointCode");
		try {
			if (Util.isNull(appointCode)) {
				map.put("result", -1);
				map.put("message", "请选定预约记录！");
				return map;
			}
			appiontService.updateAppointStatus(0, appointCode, Constant.APPOINT_STATUS_REVOKE, null);
			map.put("result", 1);
			map.put("message", "取消预约成功！");
		} catch (Exception e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 预约接种
	@RequestMapping(value = ConstantUrlWx.WX_VACCINATION)
	public ModelAndView vaccination(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(ConstantPageFtl.WX_VACCINATION);
		Map<String, Object> map = mav.getModelMap();
		map.put("code", 200);

		UserSess ui = (UserSess) request.getSession().getAttribute(Constant.KJMB_SESS);
		if (ui == null) {
			return new ModelAndView("redirect:" + ConstantUrlWx.WX_MBLOGIN + "?returnUrl=" + ConstantUrlWx.WX_VACCINATION);
		}
		int uid = ui.getUid();
		int orgId = ui.getOrgId();	
		
		//变更预约
		String cancelAppointCode =request.getParameter("cancelAppointCode");
		map.put("cancelAppointCode", cancelAppointCode);
		try {
			// 获取与之关联的用户信息
			List<UserPersonDto> userPersons = userService.listUserPerson(uid, 0, orgId, Constant.FLAG_YES, "");
			List<IDNameDto> ps = new ArrayList<IDNameDto>();
			for (UserPersonDto up : userPersons) {
				List<PersonServiceDto> personList = up.getPrsnServices();
				for (PersonServiceDto p : personList) {
					IDNameDto n = new IDNameDto();
					n.setId(p.getPersonId());
					n.setName(p.getPersonName());
					n.setCode(p.getPersonCode());
					ps.add(n);
				}
			}
			map.put("persons", ps);

			// 参照中所有的接种项目
			List<SysRefValueEbo> refVls = sysBaseService.listRefVlByRefPreCode(null, Constant.RV_APPOINT_IMMUNITY, Constant.FLAG_YES);
			List<IDNameDto> vacs = new ArrayList<IDNameDto>();
			for (SysRefValueEbo refVl : refVls) {
				IDNameDto IDName = new IDNameDto();
				IDName.setId(refVl.getId());
				IDName.setCode(refVl.getCode());
				IDName.setName(refVl.getName());
				vacs.add(IDName);
			}
			// 接种项目 后期具体的预约种类
			map.put("vacs", vacs);

			map.put("result", 1);
			map.put("message", "预约列表加载成功！");
		} catch (Exception e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return mav;
	}

	// 预约接种信息展示
	@RequestMapping(value = "/vaccination_dailylist.html")
	public @ResponseBody
	Map<String, Object> vaccination_dailylist(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		UserSess ui = (UserSess) request.getSession().getAttribute(Constant.KJMB_SESS);
		int orgId = ui.getOrgId();
		// String date = request.getParameter("date");
		try {
			String date = request.getParameter("datestr");
			int startTime = 0;
			int endTime = 0;
			if (Util.isNotNull(date)) {
				String startDay = date + " 00:00:00";
				startTime = DateUtil.parseTimeStrInSec(startDay);
				String endDay = date + " 23:59:59";
				endTime = DateUtil.parseTimeStrInSec(endDay);
			}
			// String typeCode = request.getParameter("typeCode");
			// int infoTypeId = 0;
			/*
			 * SysRefValueEbo refVl = baseService.getRefVlByCode(typeCode);
			 * if(refVl != null) infoTypeId = refVl.getId();
			 */
			CalEntryInfoQto infoQto = new CalEntryInfoQto();
			// infoQto.setInfoTypeId(infoTypeId);
			infoQto.setStartTime(startTime);// 设置预约项目结束时间大于当前时间
			infoQto.setEndTime(endTime);
			infoQto.setOrgId(orgId);
			// 接种项目可预约信息展示
			List<CalendarEntryDto> listCalEntry = calMainService.listEntryInfo(infoQto, 0, -1);
			List<MblAppointVccDto> listVcc = new ArrayList<MblAppointVccDto>();
			for (CalendarEntryDto calEntry : listCalEntry) {
				List<CalendarInfoDto> listCalInfo = calEntry.getListCalInfo();
				for (CalendarInfoDto info : listCalInfo) {
					MblAppointVccDto vcc = new MblAppointVccDto();
					int joinPerson = info.getInfoJoinPerson();
					int maxPerson = info.getInfoMaxPerson();
					vcc.setCalInfoId(info.getInfoId());
					vcc.setCalInfoCode(info.getInfoCode());
					vcc.setJoinPerson(joinPerson);
					vcc.setMaxPerson(maxPerson);
					// 日历信息 开始时间
					String startTime2 = DateUtil.formatTime(info.getInfoStartTime());
					vcc.setStartDay(startTime2.substring(0, 10));
					vcc.setStartTime(startTime2.substring(11, 16));
					// 日历信息 结束时间
					String finishTime = DateUtil.formatTime(info.getInfoEndTime());
					vcc.setFinishDay(finishTime.substring(0, 10));
					vcc.setFinishTime(finishTime.substring(11, 16));
					if (joinPerson >= maxPerson)
						vcc.setStatus(Constant.FLAG_NO);// 不可报名
					else
						vcc.setStatus(Constant.FLAG_YES);// 可报名
					vcc.setInfoTypeId(info.getInfoTypeId());
					listVcc.add(vcc);
				}
			}

			map.put("vacList", listVcc);// 该组织预约项目
			map.put("result", 1);
			map.put("message", "预约列表加载成功！");
		} catch (Exception e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 添加预约接种
	@RequestMapping(value = "/vaccination_appoint.html")
	public @ResponseBody
	Map<String, Object> vaccination_appoint(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);
		
		UserSess ui = (UserSess) request.getSession().getAttribute(Constant.KJMB_SESS);
		
		String personCode = request.getParameter("personCode");//预约人
		String calInfoCode = request.getParameter("calInfoCode");//预约日历code
		String appTypeCode = request.getParameter("appTypeCode");//预约具体参照code
		String cancelAppointCode = request.getParameter("cancelAppointCode");//用于变更预约
		
		try {
			//日历信息
			CalendarInfoEbo calInfo = calMainService.getCalInfoByIdOrCode(0, calInfoCode);
			if(calInfo == null){
				map.put("result", -1);
				map.put("message", "请选择预约项目！");
				return map;
			}
			//默认预约对象时所在组织
			int mainId = ui.getOrgId();
			String mainType = Constant.APPIONT_TYPE_ORG;
			//日历入口信息
			CalendarEntryEbo calEntry = calMainService.getCalEntryByIdOrCode(calInfo.getCalId(), null);
			if(calEntry != null){
				mainId = calEntry.getMainId();
				mainType = calEntry.getMainType();
			}
			userService.listUserPersonMap(ui.getUid(), "U", 0);
			int prsnId = userService.getSelfPrsnIdByUserId(ui.getUid());
			DocumentInfoEbo doc = docInfoService.getDocinfoByIdOrCode(0, personCode);
			if(doc != null)
				prsnId = doc.getPrsnId();
			int appTypeId = 0;
			SysRefValueEbo refVl = baseService.getRefVlByCode(appTypeCode);
			if(refVl != null)
				appTypeId = refVl.getId();
			
			//修改预约记录
			if(Util.isNotNull(cancelAppointCode)){
				AppointEbo appointEbo = new AppointEbo();
				appointEbo.setCode(cancelAppointCode);
				if(Util.isNotNull(personCode))
					appointEbo.setPrsnId(prsnId);
				appointEbo.setAppTypeId(appTypeId);
				appointEbo.setCalInfoId(calInfo.getId());
				appiontService.updateAppointEbo(appointEbo);
				//appiontService.updateAppointStatus(0, cancelAppointCode, Constant.APPOINT_STATUS_REVOKE, null);	
				map.put("result", 1);
				map.put("message", "预约成功！");
				return map;
			}
			
			//calInfoId mainId	mainType
			AppointEto appoint = new AppointEto();
			appoint.setCalInfoId(calInfo.getId());//预约日历信息ID
			appoint.setPrsnId(prsnId);//预约人
			appoint.setMainId(mainId);//被预约对象ID
			appoint.setMainType(mainType);//被预约对象类型
			appoint.setOrgId(ui.getOrgId());
			appoint.setAppTypeId(appTypeId);//具体预约信息
			appoint.setStatus(Constant.APPOINT_STATUS_CONFIRM);//默认预约接种无需审核直接确认
			appiontService.addAppoint(appoint);
			map.put("appiont", appoint);
			map.put("result", 1);
			map.put("message", "预约成功！");
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", "预约失败");
		}
		return map;
	}

	// 预约诊疗
	@RequestMapping(value = ConstantUrlWx.WX_DIAGNOSE)
	public ModelAndView diagnose(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(ConstantPageFtl.WX_DIAGNOSE);
		Map<String, Object> map = mav.getModelMap();

		UserSess ui = (UserSess) request.getSession().getAttribute(Constant.KJMB_SESS);
		if (ui == null) {
			return new ModelAndView("redirect:" + ConstantUrlWx.WX_MBLOGIN + "?returnUrl=" + ConstantUrlWx.WX_DIAGNOSE);
		}

		// String prsnCode = request.getParameter("prsnCode");
		String staffCode = request.getParameter("staffCode");
		try {

			List<UserPersonEbo> listPrsn = userService.listUserPersonMap(ui.getUid(), Constant.USER_TYPE_UNIVERSIAL, 0);
			// 页面用户切换
			List<IDNameDto> prsns = new ArrayList<IDNameDto>();
			for (UserPersonEbo up : listPrsn) {
				IDNameDto id = new IDNameDto();
				id.setId(up.getPrsnId());
				String prsnCode2 = "";
				String prsnName = "";
				if (up.getPrsnId() > 0) {
					DocumentInfoEbo doc = docInfoService.getDocinfoByIdOrCode(up.getPrsnId(), null);
					if (doc != null) {
						prsnCode2 = doc.getPrsnCode();
						prsnName = doc.getPrsnName();
					}
				}
				id.setCode(prsnCode2);
				id.setName(prsnName);
				prsns.add(id);
			}

			// 获取该组织的有预约的医生信息 TODO 还是显示该组织的所有医生
			CalEntryInfoQto calEntryInfoQto = new CalEntryInfoQto();
			calEntryInfoQto.setOrgId(ui.getOrgId());

			List<CalendarEntryDto> listEntryInfo = calMainService.listEntryInfo(calEntryInfoQto, 0, -1);
			List<IDNameDto> staffs = new ArrayList<IDNameDto>();
			for (CalendarEntryDto cal : listEntryInfo) {
				if (cal.getEntryMainType().equals(Constant.CREATE_TYPE_STAFF)) {// 只针对医生的服务进行筛选
					IDNameDto id = new IDNameDto();
					id.setId(cal.getEntryMainId());
					id.setCode(cal.getEntryMainStaffCode());
					id.setName(cal.getEntryMainStaffName());
					staffs.add(id);
				}
			}

			// 获取当天的活动 不进行医生和用户筛选(仅限数据初始化时)
			CalEntryInfoQto calInfoQto = new CalEntryInfoQto();
			calEntryInfoQto.setOrgId(ui.getOrgId());
			StaffEbo staff = staffService.getStaffByCode(staffCode);
			if (staff != null) {
				calInfoQto.setMainId(staff.getId());
				calInfoQto.setMainType(Constant.CREATE_TYPE_STAFF);
			}
			List<CalendarEntryDto> listInfo = calMainService.listEntryInfo(calInfoQto, 0, -1);
			List<MblCalEntryDto> infos = new ArrayList<MblCalEntryDto>();

			for (CalendarEntryDto info : listInfo) {// 查出数据均为医生可预约信息
				MblCalEntryDto calEntry = new MblCalEntryDto();
				String staffName = null;
				String deptName = null;
				// TODO 优化
				if (Util.isNotNull(info.getEntryMainStaffCode())) {
					StaffEbo staff2 = staffService.getStaffByCode(info.getEntryMainStaffCode());
					if (staff2 != null) {
						staffName = staff2.getName();
						DepartmentEbo dept = deptService.getDepartmentById(staff2.getDeptId());
						if (dept != null)
							deptName = dept.getName();
					}
				}
				calEntry.setStaffName(staffName);
				calEntry.setDeptName(deptName);
				// TODO 按天的时间划分？？
				for (CalendarInfoDto in : info.getListCalInfo()) {
					MblCalEntryDto cal = new MblCalEntryDto();
					cal.setStaffName(staffName);
					cal.setDeptName(deptName);
					cal.setCalInfoCode(in.getInfoCode());
					cal.setCalInfoTitle(in.getInfoTitle());
					Integer joinPerson = in.getInfoJoinPerson();
					cal.setJoinPerson(joinPerson);
					// TODO 默认预约的项目在同一天
					String startDay = DateUtil.formatTime(in.getInfoStartTime());
					String endDay = DateUtil.formatTime(in.getInfoEndTime());
					String day = startDay.substring(0, 10);
					String startTime = startDay.substring(11, 16);
					String endTime = endDay.substring(11, 16);
					cal.setDay(day);
					cal.setStartTime(startTime);
					cal.setEndTime(endTime);
					Integer maxPerson = Util.val(in.getInfoMaxPerson());
					String status = Constant.FLAG_YES;// 默认可预约状态
					if (maxPerson != null && maxPerson.intValue() > 0 && joinPerson >= maxPerson) {// 预约人数到达上限
						status = Constant.FLAG_NO;
					}
					cal.setStatus(status);
					infos.add(cal);
				}

			}
			map.put("prsns", prsns);// 用戶
			map.put("staffs", staffs);// TODO 部门与医生 级联查
			map.put("infos", infos);// 预约信息 //TODO 一个医生下对应多条信息
			map.put("result", 1);
			map.put("message", "预约列表加载成功！");
		} catch (Exception e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}

		return mav;
	}

	// 预约体检
	@RequestMapping(value = ConstantUrlWx.WX_CHECK)
	public ModelAndView check(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(ConstantPageFtl.WX_CHECK);
		Map<String, Object> map = mav.getModelMap();
		map.put("code", 200);
		@SuppressWarnings("unused")
		int uid = 0;
		UserSess ui = (UserSess) request.getSession().getAttribute(Constant.KJMB_SESS);
		if (ui != null)
			uid = ui.getUid();
		
		return mav;
	}

}
