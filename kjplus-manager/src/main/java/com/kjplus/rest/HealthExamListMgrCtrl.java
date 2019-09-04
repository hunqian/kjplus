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
import com.kjplus.dto.FuncDto;
import com.kjplus.dto.HealthExamDto;
import com.kjplus.dto.KjAdminUserDto;
import com.kjplus.dto.StaffDto;
import com.kjplus.model.DocumentInfoEbo;
import com.kjplus.model.HealthExamEbo;
import com.kjplus.model.SysFuncEbo;
import com.kjplus.qto.HealthExamQto;
import com.kjplus.service.IDocInfoService;
import com.kjplus.service.IHealthExamService;
import com.kjplus.service.IStaffService;
import com.kjplus.service.ISysFuncService;
import com.ybk.exception.DataException;

@Controller
public class HealthExamListMgrCtrl {

	private static Logger logger = Logger.getLogger(HealthExamListMgrCtrl.class);
	@Autowired
	private ISysFuncService funcService;
	@Autowired
	private IStaffService staffService;
	@Autowired
	private IDocInfoService docInfoService;
	@Autowired
	private IHealthExamService healthExamService;

	// 资讯列表
	@RequestMapping("/healthexamlist.html")
	public ModelAndView healthExamList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = mav.getModel();
		mav.setViewName("healthexam_list");

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
		if (userEbo == null) {
			mav.setViewName(ConsolePageFtl.LOGIN_ACE);
			map.put("result", -1);
			map.put("returnUrl", "/healthexamlist.html");
			map.put("message", "用户没有登陆!");
			return mav;
		}

		try {
			SysFuncEbo func = funcService.getFuncByMenu("healthexamlist.html", Constant.MENU_TYPE_MANAGER);
			if (func != null)
				map.put("pmenu", func.getPid());
			else
				map.put("pmenu", -1);
			List<FuncDto> funcs = funcService.listUserFunc(userEbo.getUid());
			map.put("funcs", funcs);

			List<DocumentInfoEbo> listDocInfo = docInfoService.listDocInfo(userEbo.getOrgId(), null, null);
			map.put("listDocInfo", listDocInfo);

			List<StaffDto> listStaffDto = staffService.listStaffDto(userEbo.getOrgId(), 0, null, null, null, -1, 0);
			map.put("listStaffDto", listStaffDto);

		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return mav;
	}

	@RequestMapping(value = "/mgr_healthexamlistjson.html")
	public @ResponseBody
	Map<String, Object> healthExamListJson(HttpServletRequest request, HttpServletResponse response) {

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
			HealthExamQto healthExamQto = new HealthExamQto();

			int prsnId = Util.parseNumVl(request.getParameter("prsnId"), 0);
			int staffId = Util.parseNumVl(request.getParameter("staffId"), 0);
			String flag = request.getParameter("flag");
			String startTime = request.getParameter("startDay");
			String endTime = request.getParameter("finishDay");

			healthExamQto.setPrsnId(prsnId);
			healthExamQto.setStaffId(staffId);
			healthExamQto.setOrgId(orgId);
			healthExamQto.setStartTime(startTime);
			healthExamQto.setEndTime(endTime);
			healthExamQto.setFlag(flag);

			List<HealthExamDto> listHealthExam = healthExamService.listHealthExam(healthExamQto, page, iDisplayLength);
			List<List<Object>> aaData = new ArrayList<List<Object>>();
			List<Object> aaList = null;
			for (HealthExamDto health : listHealthExam) {
				aaList = new ArrayList<Object>();
				aaList.add(health.getId());
				aaList.add(Util.val(health.getCode()));
				aaList.add(health.getPrsnId());
				aaList.add(Util.val(health.getPrsnName()));
				aaList.add(health.getStaffId());
				aaList.add(Util.val(health.getStaffName()));
				aaList.add(health.getOrgId());
				aaList.add(Util.val(health.getOrgName()));
				aaList.add(health.getTbcfgId());
				aaList.add(health.getTemperature());
				aaList.add(health.getRespiratoryFrequency());
				aaList.add(health.getPulseRate());
				aaList.add(health.getLeftHightBlood());
				aaList.add(health.getLeftLowBlood());
				aaList.add(health.getRightHightBlood());
				aaList.add(health.getRightLowBlood());
				aaList.add(health.getHeight());
				aaList.add(health.getWeight());
				aaList.add(health.getTheWaist());
				aaList.add(health.getBodyMassIndex());
				aaList.add(Util.val(health.getFlag()));
				aaList.add(DateUtil.formatDateTime(health.getCreateTime()));
				aaList.add(Util.val(health.getHealthMemo()));
				aaData.add(aaList);
			}
			map.put("data", aaData);
			map.put("result", 1);
			int totalRec = healthExamService.getTotalHealthExam(healthExamQto);
			map.put("iTotalRecords", totalRec);
			map.put("iTotalDisplayRecords", totalRec);

		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	@RequestMapping(value = "/mgr_gethealthexamjson.html")
	public @ResponseBody
	Map<String, Object> gethealthExamJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		String healthCode = request.getParameter("healthCode");
		try {
			HealthExamEbo health = healthExamService.getHealthExamByIdOrCode(0, healthCode);
			if (health == null) {
				map.put("result", -1);
				map.put("messge", "没有找到对于的信息内容的编码'" + healthCode + "'");
				return map;
			}
			Map<String, Object> data = new HashMap<String, Object>();

			data.put("prsnId", health.getPrsnId());
			data.put("staffId", health.getStaffId());
			data.put("temperature", health.getTemperature());
			data.put("respiratoryFrequency", health.getRespiratoryFrequency());
			data.put("pulseRate", health.getPulseRate());
			data.put("leftHightBlood", health.getLeftHightBlood());
			data.put("leftLowBlood", health.getLeftLowBlood());
			data.put("rightHightBlood", health.getRightHightBlood());
			data.put("rightLowBlood", health.getRightLowBlood());
			data.put("height", health.getHeight());
			data.put("weight", health.getWeight());
			data.put("theWaist", health.getTheWaist());
			data.put("bodyMassIndex", health.getBodyMassIndex());
			data.put("flag", health.getFlag());
			data.put("healthMemo", health.getHealthMemo());

			map.put("data", data);
			map.put("result", 1);

		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

}
