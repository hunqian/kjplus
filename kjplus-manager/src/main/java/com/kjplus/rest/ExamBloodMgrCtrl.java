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
import com.kjplus.dto.ExamMainSimpleDto;
import com.kjplus.dto.FuncDto;
import com.kjplus.dto.KjAdminUserDto;
import com.kjplus.dto.StaffDto;
import com.kjplus.eto.ExamBloodEto;
import com.kjplus.model.DocumentInfoEbo;
import com.kjplus.model.ExamBloodEbo;
import com.kjplus.model.ExamMainEbo;
import com.kjplus.model.SysFuncEbo;
import com.kjplus.qto.ExamQto;
import com.kjplus.service.IDocInfoService;
import com.kjplus.service.IExamService;
import com.kjplus.service.IStaffService;
import com.kjplus.service.ISysFuncService;
import com.ybk.exception.DataException;

@Controller
public class ExamBloodMgrCtrl {

	private static Logger logger = Logger.getLogger(ExamBloodMgrCtrl.class);
	@Autowired
	private ISysFuncService funcService;
	@Autowired
	private IExamService examService;
	@Autowired
	private IStaffService staffService;
	@Autowired
	private IDocInfoService docInfoService;

	// 测量血压列表
	@RequestMapping("/exambloodlist.html")
	public ModelAndView createDocument(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = mav.getModel();
		mav.setViewName("examblood_list");

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
		if (userEbo == null) {
			mav.setViewName(ConsolePageFtl.LOGIN_ACE);
			map.put("result", -1);
			map.put("returnUrl", "/exambloodlist.html");
			map.put("message", "用户没有登陆!");
			return mav;
		}
		try {
			SysFuncEbo func = funcService.getFuncByMenu("exambloodlist.html", Constant.MENU_TYPE_MANAGER);
			if (func != null)
				map.put("pmenu", func.getPid());
			else
				map.put("pmenu", -1);
			List<FuncDto> funcs = funcService.listUserFunc(userEbo.getUid());
			map.put("funcs", funcs);

			List<StaffDto> listStaffDto = staffService.listStaffDto(userEbo.getOrgId(), 0, null, null, null, -1, -1);
			map.put("listStaffDto", listStaffDto);

			List<DocumentInfoEbo> listDocInfo = docInfoService.listDocInfo(userEbo.getOrgId(), null, null);
			map.put("listDocInfo", listDocInfo);
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return mav;
	}

	@RequestMapping(value = "/mgr_exambloodlistjson.html")
	public @ResponseBody
	Map<String, Object> infoListJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		int iDisplayStart = Util.parseNumVl(request.getParameter("iDisplayStart"), 0);
		int iDisplayLength = Util.parseNumVl(request.getParameter("iDisplayLength"), 10);
		int page = iDisplayStart / iDisplayLength;
		if (page <= 0)
			page = 0;

		try {
			KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
			ExamQto examQto = new ExamQto();
			int prsnId = Util.parseNumVl(request.getParameter("prsnId"), 0);
			examQto.setPrsnId(prsnId);
			int staffId = Util.parseNumVl(request.getParameter("staffId"), 0);
			examQto.setStaffId(staffId);
			int orgId = userEbo.getOrgId();
			examQto.setOrgId(orgId);
			String refTypeCode = Constant.TP_EAXM_BLOOD;
			examQto.setExamTypeCode(refTypeCode);
			String startDay = request.getParameter("startDay");
			/*
			 * if (Util.isNotNull(startDay)) { Date rtDate = null; DateFormat
			 * dateFormat = new SimpleDateFormat("MM/dd/yyyy"); try { rtDate =
			 * dateFormat.parse(startDay); } catch (ParseException pe) { } if
			 * (rtDate != null) startDay = DateUtil.formatDate(rtDate); }
			 */
			examQto.setFirstTime(startDay);
			String finishDay = request.getParameter("finishDay");
			/*
			 * if (Util.isNotNull(finishDay)) { Date rtDate = null; DateFormat
			 * dateFormat = new SimpleDateFormat("MM/dd/yyyy"); try { rtDate =
			 * dateFormat.parse(finishDay); } catch (ParseException pe) { } if
			 * (rtDate != null) finishDay = DateUtil.formatDate(rtDate); }
			 */
			examQto.setLastTime(finishDay);
			List<ExamMainSimpleDto> listExamMainInnerEbo = examService.listExamMainInnerEbo(examQto, page, iDisplayLength);
			List<List<Object>> aaData = new ArrayList<List<Object>>();
			List<Object> aaList = null;
			for (ExamMainSimpleDto exam : listExamMainInnerEbo) {

				ExamBloodEbo examBlood = examService.getExamBloodEboByIdOrMainId(0, exam.getId());
				if (examBlood != null) {
					aaList = new ArrayList<Object>();
					aaList.add(exam.getId());
					aaList.add(Util.val(exam.getExamCode()));
					aaList.add(exam.getExamTypeId());
					aaList.add(Util.val(exam.getRefCode()));
					aaList.add(Util.val(exam.getRefTypeName()));
					aaList.add(exam.getFlpeId());
					aaList.add(exam.getPrsnId());
					aaList.add(Util.val(exam.getPrsnName()));
					aaList.add(exam.getStaffId());
					aaList.add(Util.val(exam.getStaffName()));
					aaList.add(examBlood.getShrinkPress());
					aaList.add(examBlood.getDiastolePress());
					aaList.add(examBlood.getHeartRate());
					aaList.add(DateUtil.formatDateTime(exam.getExamTime()));
					aaList.add(Util.val(exam.getExamDay()));
					aaList.add(Util.val(exam.getDigest()));
					aaData.add(aaList);
				}
			}
			map.put("data", aaData);
			map.put("result", 1);
			int totalFollowupMain = examService.gettotalExamMain(refTypeCode, prsnId, staffId, orgId, startDay, finishDay);
			map.put("iTotalRecords", totalFollowupMain);
			map.put("iTotalDisplayRecords", totalFollowupMain);
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	@RequestMapping(value = "/mgr_getyexambloodjson.html")
	public @ResponseBody
	Map<String, Object> getInfoJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		String examCode = request.getParameter("examCode");
		try {

			if (examCode == null) {
				map.put("result", -1);
				map.put("messge", "没有找到对于的血压测量内容的Id'" + examCode + "'");
				return map;
			}
			Map<String, Object> data = new HashMap<String, Object>();

			ExamMainEbo examMain = examService.getExamMainEboByIdOrCode(0, examCode);
			ExamBloodEbo exam = examService.getExamBloodEboByIdOrMainId(0, examMain.getId());

			data.put("shrinkpress", exam.getShrinkPress());
			data.put("diastolepress", exam.getDiastolePress());
			data.put("heartrate", exam.getHeartRate());
			data.put("prsnid", examMain.getPrsnId());
			data.put("staffid", examMain.getStaffId());

			map.put("data", data);
			map.put("result", 1);

		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 添加修改测量血压
	@RequestMapping(value = "/mgr_addormodifyexambloodjson.html")
	public @ResponseBody
	Map<String, Object> addormodifyexambloodjson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		try {

			ExamBloodEto examBloodEto = new ExamBloodEto();
			String examCode = request.getParameter("examCode");
			KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
			int orgId = userEbo.getOrgId();
			int prsnId = Util.parseNumVl(request.getParameter("prsnid"), 0);
			int staffId = Util.parseNumVl(request.getParameter("staffid"), 0);
			double shrinkPress = Util.parseDoubleVl(request.getParameter("shrinkPress"), 0.0);
			double diastolePress = Util.parseDoubleVl(request.getParameter("diastolePress"), 0.0);
			int heartRate = Util.parseNumVl(request.getParameter("heartRate"), 0);
			examBloodEto.setExamTypeId(1083);
			examBloodEto.setFlpeId(0);
			examBloodEto.setHeartRate(heartRate);
			examBloodEto.setShrinkPress(shrinkPress);
			examBloodEto.setDiastolePress(diastolePress);
			examBloodEto.setPrsnId(prsnId);
			examBloodEto.setStaffId(staffId);
			examBloodEto.setOrgId(orgId);
			if (Util.isNull(examCode)) {
				examService.addExamBloodEbo(examBloodEto);
				map.put("message", "添加测量记录成功！");
			}
			map.put("result", 1);
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

}
