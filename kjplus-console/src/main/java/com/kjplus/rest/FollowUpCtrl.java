package com.kjplus.rest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
//import org.ybk.basic.image.ImageBasePpUtil;
import org.ybk.basic.util.DateUtil;
import org.ybk.basic.util.Util;

import com.kjplus.constant.ConsolePageFtl;
import com.kjplus.constant.Constant;
import com.kjplus.dto.FollowupMainDto;
import com.kjplus.dto.FuncDto;
import com.kjplus.dto.KjAdminUserDto;
import com.kjplus.model.SysFuncEbo;
import com.kjplus.qto.FollowupMainQto;
import com.kjplus.service.IFollowupService;
import com.kjplus.service.ISysFuncService;
import com.ybk.exception.DataException;

@Controller
public class FollowUpCtrl {

	private static Logger logger = Logger.getLogger(FollowUpCtrl.class);
	@Autowired
	private ISysFuncService funcService;
	@Autowired
	private IFollowupService followupService;

	// 随访记录列表
	@RequestMapping("/followuplist.html")
	public ModelAndView createDocument(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = mav.getModel();
		mav.setViewName("followup_list");

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJCONSOLE_SESS);
		if (userEbo == null) {
			mav.setViewName(ConsolePageFtl.LOGIN_ACE);
			map.put("result", -1);
			map.put("returnUrl", "/followuplist.html");
			map.put("message", "用户没有登陆!");
			return mav;
		}

		try {
			SysFuncEbo func = funcService.getFuncByMenu("followuplist.html", Constant.MENU_TYPE_CONSOLE);
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

	@RequestMapping(value = "/followuplistjson.html")
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
			FollowupMainQto followupMainQto = new FollowupMainQto();
			int prsnId = Util.parseNumVl(request.getParameter("prsnId"), 0);
			int staffId = Util.parseNumVl(request.getParameter("staffId"), 0);
			int orgId = Util.parseNumVl(request.getParameter("orgId"), 0);
			String flpeMiscType = request.getParameter("flpeMiscType");
			int flpeTypeId = Util.parseNumVl(request.getParameter("flpeTypeId"), 0);
			String startDay = request.getParameter("startDay");
			String finishDay = request.getParameter("finishDay");
			if (Util.isNotNull(startDay)) {
				Date rtDate = null;
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				try {
					rtDate = dateFormat.parse(startDay);
				} catch (ParseException pe) {
				}
				if (rtDate != null)
					startDay = DateUtil.formatDate(rtDate);
			}
			if (Util.isNotNull(finishDay)) {
				Date rtDate = null;
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				try {
					rtDate = dateFormat.parse(finishDay);
				} catch (ParseException pe) {
				}
				if (rtDate != null)
					finishDay = DateUtil.formatDate(rtDate);
			}
			followupMainQto.setPrsnId(prsnId);
			followupMainQto.setStaffId(staffId);
			followupMainQto.setOrgId(orgId);
			followupMainQto.setFlpeMiscType(flpeMiscType);
			followupMainQto.setFlpeTypeId(flpeTypeId);
			followupMainQto.setStartTime(DateUtil.parseTimeStrInSec(startDay));
			followupMainQto.setFinishTime(DateUtil.parseTimeStrInSec(finishDay));
			List<FollowupMainDto> listFollowupMainDto = followupService.listFollowupMainDto(followupMainQto, page, iDisplayLength);
			List<List<Object>> aaData = new ArrayList<List<Object>>();
			List<Object> aaList = null;
			for (FollowupMainDto followup : listFollowupMainDto) {
				aaList = new ArrayList<Object>();
				aaList.add(followup.getId());
				aaList.add(Util.val(followup.getCode()));
				aaList.add(Util.val(followup.getSelfCode()));
				aaList.add(followup.getPrsnId());
				aaList.add(Util.val(followup.getPrsnName()));
				aaList.add(followup.getStafffId());
				aaList.add(Util.val(followup.getStaffName()));
				aaList.add(followup.getOrgId());
				aaList.add(Util.val(followup.getOrgName()));
				aaList.add(followup.getTbcfgId());
				aaList.add(Util.val(followup.getFlpeMiscType()));
				aaList.add(followup.getFlpeTypeId());
				aaList.add(followup.getInspectRefName());
				aaList.add(DateUtil.formatDateTime(followup.getFlpeTime()));
				aaList.add(DateUtil.formatDateTime(followup.getFlpeDay()));
				aaList.add(followup.getResId());
				aaList.add(followup.getResRefName());
				aaList.add(Util.val(followup.getResMemo()));
				aaList.add(followup.getResStaffId());
				aaList.add(Util.val(followup.getResStaffName()));

				aaData.add(aaList);
			}
			map.put("data", aaData);
			map.put("result", 1);
			int totalFollowupMain = followupService.getTotalFollowupMain(followupMainQto);
			map.put("iTotalRecords", totalFollowupMain);
			map.put("iTotalDisplayRecords", totalFollowupMain);
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

}
