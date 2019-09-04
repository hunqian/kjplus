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
import com.kjplus.dto.DepartmentDto;
import com.kjplus.dto.FuncDto;
import com.kjplus.dto.KjAdminUserDto;
import com.kjplus.dto.ServAsgnDto;
import com.kjplus.dto.StaffDto;
import com.kjplus.eto.SrvAsgnEto;
import com.kjplus.model.DepartmentEbo;
import com.kjplus.model.DocumentInfoEbo;
import com.kjplus.model.ServAsgnEbo;
import com.kjplus.model.ServicePackageEbo;
import com.kjplus.model.StaffEbo;
import com.kjplus.model.SysFuncEbo;
import com.kjplus.model.inner.ServicePackageInnerEbo;
import com.kjplus.service.IAdminUserService;
import com.kjplus.service.IDeptService;
import com.kjplus.service.IDocInfoService;
import com.kjplus.service.IOrgService;
import com.kjplus.service.IServiceAssignService;
import com.kjplus.service.IServicePackageService;
import com.kjplus.service.IStaffService;
import com.kjplus.service.ISysFuncService;
import com.kjplus.service.IUserMapService;
import com.ybk.exception.DataException;

@Controller
public class ServiceAssignMgrCtrl {

	private static Logger logger = Logger.getLogger(ServiceAssignMgrCtrl.class);
	@Autowired
	private ISysFuncService funcService;
	@Autowired
	private IDeptService deptService;
	@Autowired
	private IOrgService orgService;
	@Autowired
	private IServiceAssignService srvAssignService;
	@Autowired
	private IServicePackageService srvPackageService;
	@Autowired
	private IStaffService staffService;
	@Autowired
	private IDocInfoService docInfoService;
	@Autowired
	private IAdminUserService adminUserService;
	@Autowired
	private IUserMapService userMapService;

	// 已签约 页面跳转
	@RequestMapping(ManagerUrl.LIST_SRV_ASSIGN)
	public ModelAndView listSrvAss(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = mav.getModel();
		mav.setViewName(ManagerPageFtl.SRV_ASSIGN_LIST);

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
		if (userEbo == null) {
			mav.setViewName(ConsolePageFtl.LOGIN_ACE);
			map.put("result", -1);
			map.put("returnUrl", ManagerUrl.LIST_SRV_ASSIGN);
			map.put("message", "用户没有登陆!");
			return mav;
		}

		try {
			// 查看菜单是否存在
			SysFuncEbo func = funcService.getFuncByMenu(ManagerUrl.LIST_SRV_ASSIGN, Constant.MENU_TYPE_MANAGER);
			if (func != null)
				map.put("pmenu", func.getPid());
			else
				map.put("pmenu", -1);
			// 该管理员菜单列表
			List<FuncDto> funcs = funcService.listUserFunc(userEbo.getUid());
			map.put("funcs", funcs);

			// 该组织居民档案筛选
			List<DocumentInfoEbo> doc = docInfoService.listDocInfo(userEbo.getOrgId(), null, null);
			map.put("doc", doc);

			// 居民服务包列表
			List<ServicePackageInnerEbo> listSrvPackage = srvPackageService.listSrvPackageInner(null, userEbo.getOrgId(), Constant.FLAG_YES, null, null, 0, null, null, 0, -1);
			map.put("listSrvPackage", listSrvPackage);

			// 对应团队列表
			List<DepartmentDto> listDept = deptService.listDepartmentDto(null, Constant.DEPT_TYPE_GRUOP, userEbo.getOrgId(), Constant.FLAG_YES, 0, -1);
			map.put("listDept", listDept);

			// 对应医生列表
			List<StaffDto> listStaff = staffService.listStaffDto(userEbo.getOrgId(), 0, null, null, null, 0, -1);
			map.put("listStaff", listStaff);

		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return mav;
	}

	// 已签约页面数据 注意：用户每个包分别罗列
	@RequestMapping(ManagerUrl.LIST_SRV_ASSIGN_JSON)
	public @ResponseBody
	Map<String, Object> listServAssJson(HttpServletRequest request, HttpServletResponse response) {

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

			// 签约人筛选
			int prsnId = Util.parseNumVl(request.getParameter("personId"), 0);

			String prsnName = request.getParameter("name");
			String staffName = request.getParameter("name");
			// 签约医生筛选
			int staffId = Util.parseNumVl(request.getParameter("staffId"), 0);
			String staffCode = request.getParameter("staffCode");
			if (Util.isNotNullStr(staffCode)) {
				StaffEbo staff = staffService.getStaffByCode(staffCode);
				if (staff != null)
					staffId = staff.getId();
			}
			// TODO 查询是否默认服务
			String isDefault = request.getParameter("isDefault");
			// 签约状态 状态，S申请/R驳回/Y确认
			String status = request.getParameter("status");
			// 获取用户最新的签约信息
			List<ServAsgnDto> servAss = srvAssignService.listAsgnDto(prsnName, prsnId, staffName, staffId, isDefault, orgId, status, true, false, page, iDisplayLength);
			List<List<Object>> aaData = new ArrayList<List<Object>>();
			List<Object> aaList = null;

			for (ServAsgnDto ser : servAss) {
				aaList = new ArrayList<Object>();
				/*
				 * StringBuffer codes = new StringBuffer(); StringBuffer
				 * packageAlias = new StringBuffer(); List<ServAsgnPackageDto>
				 * listPackage = ser.getListPackage(); int length =
				 * listPackage.size(); for (int i = 0; i < length; i++) { if (i
				 * != 0) { codes.append(","); packageAlias.append(","); }
				 * codes.append(listPackage.get(i).getCode());
				 * packageAlias.append(listPackage.get(i).getSrvAlias()); }
				 * aaList.add(Util.val(codes.toString()));
				 * aaList.add(Util.val(packageAlias.toString()));
				 */
				aaList.add(Util.val(ser.getId()));
				aaList.add(Util.val(ser.getCode()));
				aaList.add(Util.val(ser.getPersonName()));
				aaList.add(Util.val(ser.getPersonMobile()));
				aaList.add(Util.val(ser.getStafId()));
				aaList.add(Util.val(ser.getStafName()));
				aaList.add(Util.val(ser.getStafType()));
				aaList.add(Util.val(ser.getDeptId()));
				aaList.add(Util.val(ser.getDeptName()));
				aaList.add(DateUtil.formatDateTime(ser.getBeginDay()));
				aaList.add(DateUtil.formatDateTime(ser.getEndDay()));
				aaList.add(DateUtil.formatDateTime(ser.getReqTime()));
				// 居民服务包
				aaList.add(Util.val(ser.getSrvName()));
				aaList.add(Util.val(ser.getSrvAlias()));
				aaList.add(ser.getSrvPrice());
				aaList.add(Util.val(ser.getStatus()));
				aaList.add(Util.val(ser.getMemo()));
				aaList.add(DateUtil.formatTime(ser.getOperTime()));
				aaData.add(aaList);
			}
			map.put("data", aaData);
			map.put("result", 1);
			int totalRec = srvAssignService.getTotalServAss(prsnName, prsnId, staffName, staffId, isDefault, orgId, status, true, false);
			map.put("iTotalRecords", totalRec);
			map.put("iTotalDisplayRecords", totalRec);
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 用于签约信息数据回显 修改信息时，只能一个个修改
	@RequestMapping(ManagerUrl.GET_SRV_ASSIGN_JSON)
	public @ResponseBody
	Map<String, Object> getServAssJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		Integer id = Util.parseNumVl(request.getParameter("id"), 0);
		try {
			ServAsgnEbo ser = srvAssignService.getServAssByIdOrCode(id, null);
			if (ser == null) {
				map.put("result", -1);
				map.put("messge", "没有找到对应的签约记录!");
				return map;
			}
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("prsnId", Util.val(ser.getPrsnId()));
			data.put("srvId", Util.val(ser.getSrvId()));
			data.put("deptId", Util.val(ser.getDeptId()));
			data.put("stafId", Util.val(ser.getStafId()));
			data.put("status", Util.val(ser.getStatus()));
			data.put("beginDay", DateUtil.formatDate(ser.getBeginDay()));
			data.put("endDay", DateUtil.formatDate(ser.getEndDay()));
			data.put("operTime", Util.val(ser.getOperTime()));
			data.put("memo", Util.val(ser.getMemo()));
			map.put("data", data);
			map.put("result", 1);
			map.put("messge", "获取签约数据成功!");
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 添加签约基本信息 可多选包
	@RequestMapping(ManagerUrl.ADD_SRV_ASSIGN_JSON)
	public @ResponseBody
	Map<String, Object> addServAsslistjson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);

		try {
			SrvAsgnEto servAsgnEto = new SrvAsgnEto();
			int prsnId = Util.parseNumVl(request.getParameter("prsnId"), 0);
			String srvIdStr = request.getParameter("srvIdStr");
			int deptId = Util.parseNumVl(request.getParameter("deptId"), 0);
			int staffId = userMapService.getStaffIdByAdminUserId(userEbo.getUid());
			// int staffId = Util.parseNumVl(request.getParameter("stafId"), 0);
			String status = request.getParameter("status");
			String endDay = request.getParameter("endDay");
			String memo = request.getParameter("memo");

			List<String> listPackageIds = Util.str2Array(srvIdStr);
			List<String> listPackageCode = new ArrayList<String>();
			for (String s : listPackageIds) {
				ServicePackageEbo srvPackage = srvPackageService.getSrvPackageByIdOrCode(Util.parseNumVl(s, 0), null);
				if (srvPackage == null) {
					map.put("result", -1);
					map.put("message", "该服务包不存在，code = " + s);
					return map;
				}
				listPackageCode.add(srvPackage.getCode());
			}
			servAsgnEto.setListPackageCode(listPackageCode);
			// 根据部门id获取code
			DepartmentEbo departmentById = deptService.getDepartmentById(deptId);
			String deptCode = "";
			if (departmentById != null)
				deptCode = departmentById.getCode();
			// 根据医生id获取code
			StaffEbo staffById = staffService.getStaffById(staffId);
			String stafCode = "";
			if (Util.isNull(stafCode) && staffById != null)
				stafCode = staffById.getCode();
			servAsgnEto.setPrsnId(prsnId);
			servAsgnEto.setDeptCode(deptCode);
			servAsgnEto.setStafCode(stafCode);
			servAsgnEto.setOperTime(DateUtil.getCurDayBeginInSec());
			servAsgnEto.setStatus(status);
			servAsgnEto.setEndDay(DateUtil.parseDateStr(endDay));
			servAsgnEto.setMemo(memo);

			srvAssignService.addServAsgn(servAsgnEto);
			map.put("message", "添加签约成功！");
			map.put("result", 1);
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 修改签约基本信息 只能一个个进行修改
	@RequestMapping(ManagerUrl.MODIFY_SRV_ASSIGN_JSON)
	public @ResponseBody
	Map<String, Object> modefyServAsslistjson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		try {
			Integer id = Util.parseNumVl(request.getParameter("servAsgnId"), 0);
			Integer srvId = Util.parseNumVl(request.getParameter("srvId"), 0);
			String endDay = request.getParameter("endDay");
			String status = request.getParameter("status");
			String memo = request.getParameter("memo");
			ServAsgnEbo s = srvAssignService.getServAssByIdOrCode(id, null);
			s.setSrvId(srvId);
			s.setOperTime(DateUtil.getCurDayBeginInSec());
			s.setStatus(status);
			s.setEndDay(DateUtil.parseDateStr(endDay));
			s.setMemo(memo);
			if (s != null) {
				srvAssignService.updateServAsgn(s);
				map.put("message", "修改签约成功！");
			}
			map.put("result", 1);
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 审核 签约状态
	@RequestMapping(ManagerUrl.EDIT_SRV_ASSIGN_JSON)
	public @ResponseBody
	Map<String, Object> editstatusjson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		try {
			Integer id = Util.parseNumVl(request.getParameter("servAsgnId"), 0);
			String status = request.getParameter("status");
			String memo = request.getParameter("memo");
			srvAssignService.updateServAsgnStatus(id, null, status, memo);
			map.put("message", "审核完成！");
			map.put("result", 1);
			return map;
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 待审核 页面跳转
	@RequestMapping(ManagerUrl.LIST_SRV_ASSIGN_AUDIT)
	public ModelAndView listSrvAssAudit(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = mav.getModel();
		mav.setViewName(ManagerPageFtl.SRV_ASSIGN_LIST_AUDIT);

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
		if (userEbo == null) {
			mav.setViewName(ConsolePageFtl.LOGIN_ACE);
			map.put("result", -1);
			map.put("returnUrl", ManagerUrl.LIST_SRV_ASSIGN);
			map.put("message", "用户没有登陆!");
			return mav;
		}

		try {
			// 查看菜单是否存在
			SysFuncEbo func = funcService.getFuncByMenu(ManagerUrl.LIST_SRV_ASSIGN, Constant.MENU_TYPE_MANAGER);
			if (func != null)
				map.put("pmenu", func.getPid());
			else
				map.put("pmenu", -1);
			// 该管理员菜单列表
			List<FuncDto> funcs = funcService.listUserFunc(userEbo.getUid());
			map.put("funcs", funcs);

			// 该组织居民档案筛选
			List<DocumentInfoEbo> doc = docInfoService.listDocInfo(userEbo.getOrgId(), null, null);
			map.put("doc", doc);

			// 居民服务包列表
			List<ServicePackageInnerEbo> listSrvPackage = srvPackageService.listSrvPackageInner(null, userEbo.getOrgId(), Constant.FLAG_YES, null, null, 0, null, null, 0, -1);
			map.put("listSrvPackage", listSrvPackage);

			// 对应团队列表
			List<DepartmentDto> listDept = deptService.listDepartmentDto(null, Constant.DEPT_TYPE_GRUOP, userEbo.getOrgId(), Constant.FLAG_YES, 0, -1);
			map.put("listDept", listDept);

			// 对应医生列表
			List<StaffDto> listStaff = staffService.listStaffDto(userEbo.getOrgId(), 0, null, null, null, 0, -1);
			map.put("listStaff", listStaff);

		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return mav;
	}

	// 已签约页面数据
	@RequestMapping(ManagerUrl.LIST_SRV_ASSIGN_AUDIT_JSON)
	public @ResponseBody
	Map<String, Object> listServAssAuditJson(HttpServletRequest request, HttpServletResponse response) {

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

			// 签约人筛选
			int prsnId = Util.parseNumVl(request.getParameter("personId"), 0);

			String prsnName = request.getParameter("name");
			String staffName = request.getParameter("name");
			// 签约医生筛选
			int staffId = Util.parseNumVl(request.getParameter("staffId"), 0);
			String staffCode = request.getParameter("staffCode");
			if (Util.isNotNullStr(staffCode)) {
				StaffEbo staff = staffService.getStaffByCode(staffCode);
				if (staff != null)
					staffId = staff.getId();
			}
			// 查询是否默认服务
			String isDefault = request.getParameter("isDefault");
			// 签约状态
			String status = request.getParameter("status");
			List<ServAsgnDto> servAss = srvAssignService.listAsgnDto(prsnName, prsnId, staffName, staffId, isDefault, orgId, status, true, false, page, iDisplayLength);
			List<List<Object>> aaData = new ArrayList<List<Object>>();
			List<Object> aaList = null;
			for (ServAsgnDto ser : servAss) {
				aaList = new ArrayList<Object>();
				aaList.add(ser.getId());
				aaList.add(Util.val(ser.getPersonName()));
				aaList.add(Util.val(ser.getPersonMobile()));
				aaList.add(Util.val(ser.getStafId()));
				aaList.add(Util.val(ser.getStafName()));
				aaList.add(Util.val(ser.getStafType()));
				aaList.add(Util.val(ser.getDeptId()));
				aaList.add(Util.val(ser.getDeptName()));
				aaList.add(Util.val(ser.getSrvId()));
				aaList.add(Util.val(ser.getSrvCode()));
				aaList.add(Util.val(ser.getSrvName()));
				aaList.add(ser.getSrvPrice());
				aaList.add(DateUtil.formatDateTime(ser.getBeginDay()));
				aaList.add(DateUtil.formatDateTime(ser.getEndDay()));
				aaList.add(DateUtil.formatDateTime(ser.getReqTime()));
				aaList.add(Util.val(ser.getStatus()));
				aaList.add(Util.val(ser.getMemo()));
				aaList.add(DateUtil.formatTime(ser.getOperTime()));
				aaData.add(aaList);
			}
			map.put("data", aaData);
			map.put("result", 1);

			int totalRec = srvAssignService.getTotalServAss(prsnName, prsnId, staffName, staffId, isDefault, orgId, status, true, false);
			map.put("iTotalRecords", totalRec);
			map.put("iTotalDisplayRecords", totalRec);
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 用于签约信息数据回显 签约信息修改时，只能一个一个信息修改
	@RequestMapping(ManagerUrl.GET_SRV_ASSIGN_AUDIT_JSON)
	public @ResponseBody
	Map<String, Object> getServAssAuditJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		int id = Util.parseNumVl(request.getParameter("id"), 0);
		String code = request.getParameter("code");
		try {
			ServAsgnEbo ser = srvAssignService.getServAssByIdOrCode(id, code);
			if (ser == null) {
				map.put("result", -1);
				map.put("messge", "没有找到对应的签约记录!");
				return map;
			}
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("prsnId", Util.val(ser.getPrsnId()));
			data.put("srvId", Util.val(ser.getSrvId()));
			data.put("deptId", Util.val(ser.getDeptId()));
			data.put("stafId", Util.val(ser.getStafId()));
			data.put("status", Util.val(ser.getStatus()));
			data.put("beginDay", DateUtil.formatDate(ser.getBeginDay()));
			data.put("endDay", DateUtil.formatDate(ser.getEndDay()));
			data.put("operTime", Util.val(ser.getOperTime()));
			data.put("memo", Util.val(ser.getMemo()));
			map.put("data", data);
			map.put("result", 1);
			map.put("messge", "获取签约数据成功!");
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 添加或修改签约基本信息
	@RequestMapping(ManagerUrl.ADDORMODIFY_SRV_ASSIGN_AUDIT_JSON)
	public @ResponseBody
	Map<String, Object> addServAssAuditlistjson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		try {
			SrvAsgnEto servAsgnEto = new SrvAsgnEto();
			int id = Util.parseNumVl(request.getParameter("servAsgnId"), 0);
			String code = request.getParameter("code");
			int prsnId = Util.parseNumVl(request.getParameter("prsnId"), 0);
			int srvId = Util.parseNumVl(request.getParameter("srvId"), 0);
			int deptId = Util.parseNumVl(request.getParameter("deptId"), 0);
			int staffId = Util.parseNumVl(request.getParameter("stafId"), 0);
			String status = request.getParameter("status");
			String endDay = request.getParameter("endDay");
			String memo = request.getParameter("memo");
			// TODO ServAsgnEto加code
			// 根据服务id获取code
			ServicePackageEbo srvPackage = srvPackageService.getSrvPackageByIdOrCode(srvId, null);
			@SuppressWarnings("unused")
			String srvCode = "";
			if (srvPackage != null)
				srvCode = srvPackage.getCode();
			// 根据部门id获取code
			DepartmentEbo departmentById = deptService.getDepartmentById(deptId);
			String deptCode = "";
			if (departmentById != null)
				deptCode = departmentById.getCode();
			// 根据医生id获取code
			StaffEbo staffById = staffService.getStaffById(staffId);
			String stafCode = "";

			if (Util.isNull(stafCode) && staffById != null)
				stafCode = staffById.getCode();

			servAsgnEto.setPrsnId(prsnId);
			// servAsgnEto.setSrvCode(srvCode);
			servAsgnEto.setDeptCode(deptCode);
			servAsgnEto.setStafCode(stafCode);

			servAsgnEto.setOperTime(DateUtil.getCurDayBeginInSec());
			servAsgnEto.setStatus(status);
			servAsgnEto.setEndDay(DateUtil.parseDateStr(endDay));
			servAsgnEto.setMemo(memo);

			if (id == 0 && Util.isNull(code)) {
				srvAssignService.addServAsgn(servAsgnEto);
				map.put("message", "添加签约成功！");
			} else {
				ServAsgnEbo s = srvAssignService.getServAssByIdOrCode(id, code);
				// s.setPrsnId(prsnId);
				s.setSrvId(srvId);
				// s.setDeptId(deptId);
				// s.setStafId(staffId);
				s.setOperTime(DateUtil.getCurDayBeginInSec());
				s.setStatus(status);
				s.setEndDay(DateUtil.parseDateStr(endDay));
				s.setMemo(memo);
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

	// 审核 签约状态
	@RequestMapping(ManagerUrl.EDIT_SRV_ASSIGN_AUDIT_JSON)
	public @ResponseBody
	Map<String, Object> editstatusauditjson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		try {
			Integer id = Util.parseNumVl(request.getParameter("servAsgnId"), 0);
			String status = request.getParameter("status");
			String memo = request.getParameter("memo");
			srvAssignService.updateServAsgnStatus(id, null, status, memo);
			map.put("message", "审核签约记录成功！");
			map.put("result", 1);
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

}
