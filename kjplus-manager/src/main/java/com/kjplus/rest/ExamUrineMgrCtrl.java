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
import com.kjplus.dto.ExamUrineDto;
import com.kjplus.dto.FuncDto;
import com.kjplus.dto.KjAdminUserDto;
import com.kjplus.model.ExamMainEbo;
import com.kjplus.model.SysFuncEbo;
import com.kjplus.qto.ExamQto;
import com.kjplus.service.IExamService;
import com.kjplus.service.ISysFuncService;
import com.ybk.exception.DataException;

@Controller
public class ExamUrineMgrCtrl {

	private static Logger logger = Logger.getLogger(ExamUrineMgrCtrl.class);
	@Autowired
	private ISysFuncService funcService;
	@Autowired
	private IExamService examService;

	// 尿液检查列表
	@RequestMapping("/examurinelist.html")
	public ModelAndView createDocument(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = mav.getModel();
		mav.setViewName("examurine_list");

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
		if (userEbo == null) {
			mav.setViewName(ConsolePageFtl.LOGIN_ACE);
			map.put("result", -1);
			map.put("returnUrl", "/examurinelist.html");
			map.put("message", "用户没有登陆!");
			return mav;
		}

		try {
			SysFuncEbo func = funcService.getFuncByMenu("examurinelist.html", Constant.MENU_TYPE_MANAGER);
			if (func != null)
				map.put("pmenu", func.getPid());
			else
				map.put("pmenu", -1);
			List<FuncDto> funcs = funcService.listUserFunc(userEbo.getUid());
			map.put("funcs", funcs);
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return mav;
	}

	@RequestMapping(value = "/mgr_examurinelistjson.html")
	public @ResponseBody
	Map<String, Object> urineListJson(HttpServletRequest request, HttpServletResponse response) {

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
			String refTypeCode = Constant.TP_EAXM_URINE;
			examQto.setExamTypeCode(refTypeCode);
			// 默认传回“2017-12-1 12:20:30"
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
				/*
				 * aaList.add(exam.getOrgId());
				 * aaList.add(Util.val(exam.getOrgName()));
				 */aaList.add(DateUtil.formatDateTime(exam.getExamTime()));
				aaList.add(Util.val(exam.getExamDay()));
				aaList.add(Util.val(exam.getDigest()));
				aaData.add(aaList);
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

	// 详细尿检数据展示
	@RequestMapping(value = "/mgr_examurinedetailsjson.html")
	public @ResponseBody
	Map<String, Object> urineDetailsJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		int exmainId = Util.parseNumVl(request.getParameter("exmainId"), 0);

		try {
			ExamMainEbo main = examService.getExamMainEboByIdOrCode(exmainId, null);
			if (main == null) {
				map.put("data", null);
				map.put("result", 1);
				return map;
			}
			ExamQto examQto = new ExamQto();
			examQto.setExmainId(exmainId);
			List<ExamMainSimpleDto> simples = examService.listExamMainInnerEbo(examQto, 0, -1);
			Object ex = simples.get(0).getExamObjDto();
			List<ExamUrineDto> listUrine = null;
			if (ex instanceof List<?>) {
				listUrine = (List<ExamUrineDto>) ex;
			}
			List<List<Object>> aaData = new ArrayList<List<Object>>();
			List<Object> aaList = null;
			for (ExamUrineDto urine : listUrine) {
				aaList = new ArrayList<Object>();
				aaList.add(Util.val(urine.getUrineTypeId()));
				aaList.add(Util.val(urine.getUrineTypeName()));
				aaList.add(Util.val(urine.getUrineVl()));
				aaData.add(aaList);
			}
			map.put("data", aaData);
			map.put("result", 1);
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

}
