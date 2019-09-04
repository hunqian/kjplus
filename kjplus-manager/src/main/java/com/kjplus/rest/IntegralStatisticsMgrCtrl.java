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
import com.kjplus.constant.ManagerUrl;
import com.kjplus.dto.FuncDto;
import com.kjplus.dto.KjAdminUserDto;
import com.kjplus.dto.ServiceSumDto;
import com.kjplus.model.AdminUserEbo;
import com.kjplus.model.ServiceEntryEbo;
import com.kjplus.model.ServiceListEbo;
import com.kjplus.model.SysFuncEbo;
import com.kjplus.model.UserEbo;
import com.kjplus.service.IAdminUserService;
import com.kjplus.service.IExamService;
import com.kjplus.service.IServiceEntryService;
import com.kjplus.service.IServiceSumService;
import com.kjplus.service.ISysFuncService;
import com.kjplus.service.IUserService;
import com.ybk.exception.DataException;

//import org.ybk.basic.image.ImageBasePpUtil;

@Controller
public class IntegralStatisticsMgrCtrl {

	private static Logger logger = Logger.getLogger(IntegralStatisticsMgrCtrl.class);
	@Autowired
	private ISysFuncService funcService;
	@Autowired
	private IExamService examService;
	@Autowired
	private IServiceSumService serviceSumService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IAdminUserService adminUserService;
	@Autowired
	private IServiceEntryService serviceEntryService;

	// 积分统计
	@RequestMapping(ManagerUrl.INTEGRALSTATISTICS)
	public ModelAndView createDocument(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = mav.getModel();
		mav.setViewName("integralstatistic_list");

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
		if (userEbo == null) {
			mav.setViewName(ConsolePageFtl.LOGIN_ACE);
			map.put("result", -1);
			map.put("returnUrl", "/integralstatistics.html");
			map.put("message", "用户没有登陆!");
			return mav;
		}
		try {
			SysFuncEbo func = funcService.getFuncByMenu("integralstatistics.html", Constant.MENU_TYPE_MANAGER);
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

	// 积分统计 总数据 页面数据
	@RequestMapping(ManagerUrl.LIST_INTEGRALSTATISTICS_JSON)
	public @ResponseBody
	Map<String, Object> listintegraljson(HttpServletRequest request, HttpServletResponse response) {

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
			String bodyType = request.getParameter("bodyType");
			List<ServiceSumDto> listSum = serviceSumService.listSrvSumDto(orgId, bodyType, page, iDisplayLength);

			List<List<Object>> aaData = new ArrayList<List<Object>>();
			List<Object> aaList = null;

			for (ServiceSumDto sum : listSum) {
				aaList = new ArrayList<Object>();
				aaList.add(Util.val(sum.getId()));
				String bodyTypeName = "";// 用户类型
				String bodyName = "";// 用户名
				// 积分用户类型及姓名
				String bodyType2 = Util.val(sum.getBodyType());
				if (Util.isNotNull(bodyType2) && "U".equals(bodyType2)) {// 普通用户
					bodyTypeName = "用户";
					UserEbo user = userService.getUserById(Util.val(sum.getBodyId()));
					if (user != null) {
						bodyName = user.getUserName();
					}
				} else if (Util.isNotNull(bodyType2) && "A".equals(bodyType2)) {// 普通用户
					bodyTypeName = "医生";
					AdminUserEbo admin = adminUserService.getUserByUid(Util.val(sum.getBodyId()));
					if (admin != null) {
						bodyName = admin.getUserName();
					}
				}
				aaList.add(bodyTypeName);
				aaList.add(bodyName);
				aaList.add(sum.getTotalEarnPoint());// 总赚取积分
				aaList.add(sum.getTotalConsumePoint());// 总消费积分
				aaList.add(sum.getTotalPoint());// 剩余积分
				aaData.add(aaList);
			}
			map.put("data", aaData);
			map.put("result", 1);
			// TODO 总数
			int totalRec = aaData.size();
			map.put("iTotalRecords", totalRec);
			map.put("iTotalDisplayRecords", totalRec);
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 积分详情 总数据 页面数据
	@RequestMapping(ManagerUrl.LIST_INTEGRALSTATISTICSLIST_JSON)
	public @ResponseBody
	Map<String, Object> listIntegralListJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		int iDisplayStart = Util.parseNumVl(request.getParameter("iDisplayStart"), 0);
		int iDisplayLength = Util.parseNumVl(request.getParameter("iDisplayLength"), 10);
		int page = iDisplayStart / iDisplayLength;
		if (page <= 0)
			page = 0;

		try {

			List<ServiceListEbo> listSrvList = serviceSumService.listSrvList(0, 0, null, 0, 0, Constant.FLAG_YES, page, iDisplayLength);

			List<List<Object>> aaData = new ArrayList<List<Object>>();
			List<Object> aaList = null;

			for (ServiceListEbo srvList : listSrvList) {
				aaList = new ArrayList<Object>();
				aaList.add(Util.val(srvList.getId()));
				aaList.add(Util.val(srvList.getSumId()));
				String srvListName = ""; // 积分使用原因
				String mainType = srvList.getMainType();
				if (Util.isNotNull(mainType) && "S".equals(mainType)) {// 服务
					ServiceEntryEbo srvEntry = serviceEntryService.getSrvEntryByIdOrCode(srvList.getMainId(), null);
					if (srvEntry != null)
						srvListName = srvEntry.getSrvName();
				}
				aaList.add(srvListName);
				aaList.add(DateUtil.formatDate(srvList.getCreateTime()));// 积分操纵时间
				aaList.add(srvList.getServicePoint());
				aaList.add(Util.val(srvList.getFlag()));
				aaList.add(srvList.getMemo());
				aaData.add(aaList);
			}
			map.put("data", aaData);
			map.put("result", 1);
			// TODO 总数
			int totalRec = aaData.size();
			map.put("iTotalRecords", totalRec);
			map.put("iTotalDisplayRecords", totalRec);
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 积分详情 总数据 页面数据
	@RequestMapping(ManagerUrl.GET_INTEGRALSTATISTICSLIST_JSON)
	public @ResponseBody
	Map<String, Object> getIntegralListJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		int iDisplayStart = Util.parseNumVl(request.getParameter("iDisplayStart"), 0);
		int iDisplayLength = Util.parseNumVl(request.getParameter("iDisplayLength"), 10);
		int page = iDisplayStart / iDisplayLength;
		if (page <= 0)
			page = 0;

		try {
			int sumId = Util.parseNumVl(request.getParameter("sumId"), 0);

			List<ServiceListEbo> listSrvList = serviceSumService.listSrvList(sumId, 0, null, 0, 0, null, page, iDisplayLength);

			List<List<Object>> aaData = new ArrayList<List<Object>>();
			List<Object> aaList = null;

			for (ServiceListEbo srvList : listSrvList) {
				aaList = new ArrayList<Object>();
				aaList.add(Util.val(srvList.getId()));
				aaList.add(Util.val(srvList.getSumId()));
				String srvListName = ""; // 积分使用原因
				String mainType = srvList.getMainType();
				if (Util.isNotNull(mainType) && "S".equals(mainType)) {// 服务
					ServiceEntryEbo srvEntry = serviceEntryService.getSrvEntryByIdOrCode(srvList.getMainId(), null);
					if (srvEntry != null)
						srvListName = srvEntry.getSrvName();
				}
				aaList.add(srvListName);
				aaList.add(DateUtil.formatTime(srvList.getCreateTime()));// 积分操纵时间
				aaList.add(srvList.getServicePoint());
				aaList.add(srvList.getFlag());
				aaList.add(srvList.getMemo());
				aaData.add(aaList);
			}
			map.put("data", aaData);
			map.put("result", 1);
			// TODO 总数
			int totalRec = aaData.size();
			map.put("iTotalRecords", totalRec);
			map.put("iTotalDisplayRecords", totalRec);
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

}
