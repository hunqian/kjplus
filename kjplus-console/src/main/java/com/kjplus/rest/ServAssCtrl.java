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
import com.kjplus.dto.KjAdminUserDto;
import com.kjplus.dto.ServAsgnDto;
import com.kjplus.eto.SrvAsgnEto;
import com.kjplus.model.DepartmentEbo;
import com.kjplus.model.DocumentInfoEbo;
import com.kjplus.model.ServAsgnEbo;
import com.kjplus.model.ServicePackageEbo;
import com.kjplus.model.StaffEbo;
import com.kjplus.model.SysFuncEbo;
import com.kjplus.service.IDeptService;
import com.kjplus.service.IDocInfoService;
import com.kjplus.service.IOrgService;
import com.kjplus.service.IServiceAssignService;
import com.kjplus.service.IStaffService;
import com.kjplus.service.ISysFuncService;
import com.ybk.exception.DataException;

@Controller
public class ServAssCtrl {

	private static Logger logger = Logger.getLogger(ServAssCtrl.class);
	@Autowired
	private ISysFuncService funcService;
	@Autowired
	private IDeptService deptService;
	@Autowired
	private IOrgService orgService;
	@Autowired
	private IServiceAssignService srvAssignService;
	@Autowired
	private IStaffService staffService;
	@Autowired
	private IDocInfoService docInfoService;

	// 签约列表
	@RequestMapping("/servasslist.html")
	public ModelAndView createDocument(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = mav.getModel();
		mav.setViewName("servass_list");

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJCONSOLE_SESS);
		if (userEbo == null) {
			mav.setViewName(ConsolePageFtl.LOGIN_ACE);
			map.put("result", -1);
			map.put("returnUrl", "/servasslist.html");
			map.put("message", "用户没有登陆!");
			return mav;
		}

		try {
			SysFuncEbo func = funcService.getFuncByMenu("servasslist.html", Constant.MENU_TYPE_CONSOLE);
			if (func != null)
				map.put("pmenu", func.getPid());
			else
				map.put("pmenu", -1);
			List<FuncDto> funcs = funcService.listUserFunc(userEbo.getUid());
			map.put("funcs", funcs);

			List<DocumentInfoEbo> doc = docInfoService.listDocInfo(0, null, null);
			map.put("doc", doc);

		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return mav;
	}

	@RequestMapping(value = "/mgr_servasslistjson.html")
	public @ResponseBody
	Map<String, Object> servAssListJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		int iDisplayStart = Util.parseNumVl(request.getParameter("iDisplayStart"), 0);
		int iDisplayLength = Util.parseNumVl(request.getParameter("iDisplayLength"), 10);
		int page = iDisplayStart / iDisplayLength;
		if (page <= 0)
			page = 0;

		try {
			int personId = Util.parseNumVl(request.getParameter("personId"), 0);
			int staffId = Util.parseNumVl(request.getParameter("staffId"), 0);
			int orgId = Util.parseNumVl(request.getParameter("orgId"), 0);
			String personName = request.getParameter("name");
			String staffName = request.getParameter("staffName");
			String staffCode = request.getParameter("staffCode");
			if (Util.isNotNull(staffCode)) {
				StaffEbo staff = staffService.getStaffByCode(staffCode);
				if (staff != null)
					staffId = staff.getId();
			}
			String isDefault = request.getParameter("isDefault");
			String status = request.getParameter("status");
			List<ServAsgnDto> servAss = srvAssignService.listAsgnDto(personName, personId, staffName, staffId, isDefault, orgId, status, true, false, page,
					iDisplayLength);
			List<List<Object>> aaData = new ArrayList<List<Object>>();
			List<Object> aaList = null;
			for (ServAsgnDto ser : servAss) {
				aaList = new ArrayList<Object>();
				aaList.add(ser.getId());
				aaList.add(ser.getPersonId());
				aaList.add(ser.getPersonName());
				aaList.add(Util.val(ser.getPersonMobile()));
				aaList.add(Util.val(ser.getStafId()));
				aaList.add(ser.getStafCode());
				aaList.add(ser.getStafName());
				aaList.add(ser.getStafType());
				aaList.add(ser.getOrgId());
				aaList.add(ser.getOrgName());
				aaList.add(ser.getDeptId());
				aaList.add(ser.getDeptType());
				aaList.add(ser.getDeptName());
				aaList.add(ser.getSrvId());
				aaList.add(ser.getSrvCode());
				aaList.add(ser.getSrvName());
				aaList.add(ser.getSrvPrice());
				aaList.add(DateUtil.formatDateTime(ser.getReqTime()));
				aaList.add(ser.getStatus());
				aaList.add(ser.getMemo());
				aaList.add(ser.getOperTime());
				aaList.add(DateUtil.formatDateTime(ser.getBeginDay()));
				aaList.add(DateUtil.formatDateTime(ser.getEndDay()));
				aaData.add(aaList);
			}
			map.put("data", aaData);
			map.put("result", 1);

			int totalRec = srvAssignService.getTotalServAss(personName, personId, staffName, staffId, isDefault, orgId, status, true, false);
			map.put("iTotalRecords", totalRec);
			map.put("iTotalDisplayRecords", totalRec);
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	@RequestMapping(value = "/getservassjson.html")
	public @ResponseBody
	Map<String, Object> getServAssJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		int servAsgnId = Util.parseNumVl(request.getParameter("servAsgnId"), 0);
		try {
			ServAsgnEbo ser = srvAssignService.getServAssByIdOrCode(servAsgnId, null);
			if (ser == null) {
				map.put("result", -1);
				map.put("messge", "没有找到对应的签约记录,ID是：'" + servAsgnId + "'");
				return map;
			}
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("prsnId", ser.getPrsnId());
			data.put("srvId", ser.getSrvId());
			data.put("deptId", ser.getDeptId());
			data.put("stafId", ser.getStafId());
			data.put("status", ser.getStatus());
			data.put("operTime", ser.getOperTime());
			data.put("memo", Util.val(ser.getMemo()));
			map.put("data", data);
			map.put("result", 1);

		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 添加修改签约记录
	@RequestMapping(value = "/addormodifyservassjson.html")
	public @ResponseBody
	Map<String, Object> addServAsslistjson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		try {
			SrvAsgnEto servAsgnEto = new SrvAsgnEto();
			int servAsgnId = Util.parseNumVl(request.getParameter("servAsgnId"), 0);
			int prsnId = Util.parseNumVl(request.getParameter("prsnId"), 0);
			int srvId = Util.parseNumVl(request.getParameter("srvId"), 0);
			int deptId = Util.parseNumVl(request.getParameter("deptId"), 0);
			int staffId = Util.parseNumVl(request.getParameter("stafId"), 0);
			int operTime = Util.parseNumVl(request.getParameter("operTime"), 0);
			// 根据服务ID获取Code
			// ServicePackageEbo servCatByCodeOrId =
			// srvAssignService.getServCatByCodeOrId(srvId, null);
			ServicePackageEbo servCatByCodeOrId = null;
			// 根据部门ID获取Code
			DepartmentEbo departmentById = deptService.getDepartmentById(deptId);
			String deptCode = departmentById.getCode();
			// 根据医生ID获取Code
			StaffEbo staffById = staffService.getStaffById(staffId);
			String stafCode = staffById.getCode();

			servAsgnEto.setPrsnId(prsnId);
			// servAsgnEto.setSrvCode(srvCode);
			servAsgnEto.setDeptCode(deptCode);
			servAsgnEto.setStafCode(stafCode);

			if (servAsgnId == 0) {
				srvAssignService.addServAsgn(servAsgnEto);
				map.put("message", "添加签约成功！");
			} else {
				ServAsgnEbo s = srvAssignService.getServAssByIdOrCode(servAsgnId, null);
				s.setPrsnId(prsnId);
				s.setSrvId(srvId);
				s.setDeptId(deptId);
				s.setStafId(staffId);
				s.setOperTime(operTime);
				if (s != null) {
					srvAssignService.updateServAsgn(s);
					map.put("message", "修改签约成功！");
				}
			}
			map.put("result", 1);
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 通过id获取居民信息、组织ID
	@RequestMapping(value = "/getprsnbyidjson.html")
	public @ResponseBody
	Map<String, Object> getprsnbyid(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		int prsnid = Util.parseNumVl(request.getParameter("prsnid"), 0);
		try {
			DocumentInfoEbo doc = docInfoService.getDocinfoByIdOrCode(prsnid, null);
			int orgid = doc.getOrgId();
			map.put("orgid", orgid);
			map.put("result", 1);
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 审核
	@RequestMapping(value = "/editstatusjson.html")
	public @ResponseBody
	Map<String, Object> editstatusjson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		try {
			int servAsgnId = Util.parseNumVl(request.getParameter("servAsgnId"), 0);
			String code = request.getParameter("code");
			String status = request.getParameter("status");
			String memo = request.getParameter("memo");

			if (servAsgnId != 0) {
				ServAsgnEbo s = srvAssignService.getServAssByIdOrCode(servAsgnId, null);
				if (s != null) {
					srvAssignService.updateServAsgnStatus(servAsgnId, code, status, memo);
					map.put("message", "审核完成！");
				}
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
