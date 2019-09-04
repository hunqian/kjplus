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
import org.ybk.basic.util.DateUtil;
import org.ybk.basic.util.Util;

import com.kjplus.constant.ConsolePageFtl;
import com.kjplus.constant.Constant;
import com.kjplus.constant.ManagerPageFtl;
import com.kjplus.constant.ManagerUrl;
import com.kjplus.dto.FuncDto;
import com.kjplus.dto.KjAdminUserDto;
import com.kjplus.model.AppointEbo;
import com.kjplus.model.SysFuncEbo;
import com.kjplus.model.inner.AppointInfoInnerEbo;
import com.kjplus.service.IAppointService;
import com.kjplus.service.ISysFuncService;
import com.ybk.exception.DataException;

@Controller
public class AppointmentMgrCtrl {

	private static Logger logger = Logger.getLogger(AppointmentMgrCtrl.class);
	@Autowired
	private ISysFuncService funcService;
	@Autowired
	private IAppointService appointService;

	// 预约页面跳转
	@RequestMapping(ManagerUrl.LIST_APPOINEMENT)
	public ModelAndView listAppointment(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = mav.getModel();
		mav.setViewName(ManagerPageFtl.APPOINTMENT_LIST);

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
		if (userEbo == null) {
			mav.setViewName(ConsolePageFtl.LOGIN_ACE);
			map.put("result", -1);
			map.put("returnUrl", ManagerUrl.LIST_APPOINEMENT);
			map.put("message", "用户没有登陆!");
			return mav;
		}
		try {
			SysFuncEbo func = funcService.getFuncByMenu(ManagerUrl.LIST_APPOINEMENT, Constant.MENU_TYPE_MANAGER);
			if (func != null)
				map.put("pmenu", func.getPid());
			else
				map.put("pmenu", "-1");
			List<FuncDto> funcs = funcService.listUserFunc(userEbo.getUid());
			map.put("funcs", funcs);

		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return mav;
	}

	// 预约页面数据
	@RequestMapping(ManagerUrl.LIST_APPOINEMENT_JSON)
	public @ResponseBody
	Map<String, Object> listAppointmentJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);

		int iDisplayStart = Util.parseNumVl(request.getParameter("iDisplayStart"), 0);
		int iDisplayLength = Util.parseNumVl(request.getParameter("iDisplayLength"), 10);
		int page = iDisplayStart / iDisplayLength;
		if (page <= 0)
			page = 0;

		try {
			int orgId = userEbo.getOrgId();
			// 预约医生的模糊查询
			String staffName = request.getParameter("staffName");
			// 预约状态 状态，Y有效/C确认/D拒绝/R撤销
			String status = request.getParameter("status");
			Integer calId = 0;
			Integer prsnId = 0;
			Integer mainId = 0;
			String mainType = "";
			Integer startTime = 0;
			Integer endTime = 0;
			List<AppointInfoInnerEbo> listAppoint = appointService.listAppointInfo(calId, prsnId, orgId, mainId, mainType, staffName, startTime, endTime, status, 0,0, page, iDisplayLength);
			List<List<Object>> aaData = new ArrayList<List<Object>>();
			List<Object> aaList = null;
			for (AppointInfoInnerEbo a : listAppoint) {
				aaList = new ArrayList<Object>();
				aaList.add(a.getId());
				aaList.add(a.getCode());
				aaList.add(a.getPrsnName());// 预约人
				aaList.add(Util.val(a.getBkStaffName()));// 被预约医生名
				aaList.add(Util.val(a.getBkDeptName()));// 被预约部门
				aaList.add(a.getInfoTitle());// 预约标题
				aaList.add(Util.val(a.getInfoMemo()));// 预约项目描述
				aaList.add(DateUtil.getSecondToDate(a.getInfoStartTime()));// 预约项目开始时间
				aaList.add(DateUtil.getSecondToDate(a.getInfoEndTime()));// 预约项目结束时间
				aaList.add(a.getStatus());// 预约状态
				aaData.add(aaList);
			}
			map.put("data", aaData);
			map.put("result", 1);
			// TODO 数目
			int totalRec = appointService.getTotalAppoint(calId, prsnId, orgId, mainId, mainType, staffName, startTime, endTime, status, 0,0);
			map.put("aaData", aaData);
			map.put("iTotalRecords", totalRec);
			map.put("iTotalDisplayRecords", totalRec);
			map.put("message", "获取预约列表成功！");
		} catch (Exception e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 预约页面数据
	@RequestMapping(ManagerUrl.GET_APPOINEMENT_JSON)
	public @ResponseBody
	Map<String, Object> getAppointmentJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		try {
			Integer id = Util.parseNumVl(request.getParameter("id"), 0);

			AppointEbo appoint = appointService.getAppointByIdOrCode(id, null);

			Map<String, Object> data = new HashMap<String, Object>();
			data.put("id", appoint.getId());
			data.put("code", appoint.getCode());
			data.put("status", appoint.getStatus());
			data.put("memo", appoint.getAppMemo());
			map.put("data", data);
			map.put("result", 1);
			map.put("aaData", data);
			map.put("message", "获取预约数据成功！");
		} catch (Exception e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 预约页面数据 TODO 添加暂定
	@RequestMapping(ManagerUrl.ADDORMODIFY_APPOINT_JSON)
	public @ResponseBody
	Map<String, Object> addOrModifyAppointJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		try {
			AppointEbo appointEbo = new AppointEbo();
			Integer id = Util.parseNumVl(request.getParameter("id"), 0);
			appointEbo.setId(id);
			;
			String status = request.getParameter("status");
			appointEbo.setStatus(status);
			String memo = request.getParameter("memo");
			appointEbo.setAppMemo(memo);
			appointService.updateAppointEbo(appointEbo);
			map.put("result", 1);
			map.put("message", "修改预约数据成功！");
		} catch (Exception e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 修改预约状态
	@RequestMapping(ManagerUrl.MODIFY_APPOINT_STATUS)
	public @ResponseBody
	Map<String, Object> modifyAppointStatusJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		try {
			Integer id = Util.parseNumVl(request.getParameter("id"), 0);
			String status = request.getParameter("status");
			String memo = request.getParameter("memo");
			appointService.updateAppointStatus(id, null, status, memo);
			map.put("result", 1);
			map.put("message", "修改预约状态成功！");
		} catch (Exception e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

}
