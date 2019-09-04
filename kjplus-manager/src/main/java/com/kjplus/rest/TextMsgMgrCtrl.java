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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.ybk.basic.util.DateUtil;
import org.ybk.basic.util.Util;

import com.kjplus.constant.ConsolePageFtl;
import com.kjplus.constant.Constant;
import com.kjplus.dao.IInfoDao;
import com.kjplus.dto.FuncDto;
import com.kjplus.dto.KjAdminUserDto;
import com.kjplus.model.OrgEbo;
import com.kjplus.model.SysFuncEbo;
import com.kjplus.service.*;
import com.ybk.exception.DataException;
import com.ykisswx.dto.WxTextMsgDto;
import com.ykisswx.service.IWxTextMsgService;

@Controller
public class TextMsgMgrCtrl {

	private static Logger logger = Logger.getLogger(TextMsgMgrCtrl.class);
	@Autowired
	private IInfoDao infoDao;
	@Autowired
	private IInfoService infoService;
	@Autowired
	private ISysFuncService funcService;
	@Autowired
	private IDeptService deptService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IOrgService orgService;
	@Autowired
	private IWxTextMsgService wxTextMsgService;
	
	// 文本消息
	@RequestMapping("/wxtextmsglist.html")
	public ModelAndView wxTestMsgList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = mav.getModel();
		mav.setViewName("wxtextmsg_list");
		
		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
		if (userEbo == null) {
			mav.setViewName(ConsolePageFtl.LOGIN_ACE);
			map.put("result", -1);
			map.put("returnUrl", "/wxtextmsglist.html");
			map.put("message", "用户没有登陆!");
			return mav;
		}

		try {
			SysFuncEbo func = funcService.getFuncByMenu("wxtextmsglist.html", Constant.MENU_TYPE_MANAGER);
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

	@RequestMapping(value = "/mgr_textmsglistjson.html")
	public @ResponseBody
	Map<String, Object> textMsgListJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		int iDisplayStart = Util.parseNumVl(request.getParameter("iDisplayStart"), 0);
		int iDisplayLength = Util.parseNumVl(request.getParameter("iDisplayLength"), 10);
		int page = iDisplayStart / iDisplayLength;
		if (page <= 0)
			page = 0;
		
		try {
			int accid = Util.parseNumVl(request.getParameter("accid"), 0);
			int userid = Util.parseNumVl(request.getParameter("userid"), 0);
			String msg = request.getParameter("msg");
			String queryday = request.getParameter("queryday");
			if(Util.isNotNull(queryday)){
				Date rtDate = null;
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				try {
					rtDate = dateFormat.parse(queryday);
				} catch (ParseException pe) {
				}
				if(rtDate != null)
				queryday = DateUtil.formatDate(rtDate);
				
			}
			List<WxTextMsgDto> listTextMsg = wxTextMsgService.listTextMsg(accid, userid, msg, queryday, page, iDisplayLength);
			List<List<Object>> aaData = new ArrayList<List<Object>>();
			List<Object> aaList = null;
			for (WxTextMsgDto text : listTextMsg) {
				aaList = new ArrayList<Object>();
				aaList.add(text.getId());
				aaList.add(text.getUserId());
				aaList.add(text.getNickName());
				aaList.add(text.getAccId());
				aaList.add(text.getAccount());
				aaList.add(text.getTextMsg());
				aaList.add(DateUtil.formatDateTime(text.getCreateTime()));
				aaData.add(aaList);
			}
			int totalRec = wxTextMsgService.getTotalTextMsg(accid, userid, msg, queryday);
			map.put("data", aaData);
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
