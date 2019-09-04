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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.ybk.basic.util.DateUtil;
import org.ybk.basic.util.Util;

import com.kjplus.constant.ConsolePageFtl;
import com.kjplus.constant.Constant;
import com.kjplus.dao.IInfoDao;
import com.kjplus.dto.DepartmentDto;
import com.kjplus.dto.FuncDto;
import com.kjplus.dto.KjAdminUserDto;
import com.kjplus.dto.OrgDto;
import com.kjplus.dto.StaffDto;
import com.kjplus.eto.StaffEto;
import com.kjplus.model.StaffEbo;
import com.kjplus.model.SysFuncEbo;
import com.kjplus.model.SysRefValueEbo;
import com.kjplus.service.*;
import com.ybk.exception.DataException;

@Controller
public class StaffCtrl {

	private static Logger logger = Logger.getLogger(StaffCtrl.class);
	@Autowired
	private IInfoDao infoDao;
	@Autowired
	private IInfoService infoService;
	@Autowired
	private ISysFuncService funcService;
	@Autowired
	private IDeptService deptService;
	@Autowired
	private IOrgService orgService;
	@Autowired
	private IStaffService staffService;
	@Autowired
	private ISysBaseService baseService;

	// 资讯列表
	@RequestMapping("/stafflist.html")
	public ModelAndView createDocument(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = mav.getModel();
		mav.setViewName("staff_list");

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJCONSOLE_SESS);
		if (userEbo == null) {
			mav.setViewName(ConsolePageFtl.LOGIN_ACE);
			map.put("result", -1);
			map.put("returnUrl", "/stafflist.html");
			map.put("message", "用户没有登陆!");
			return mav;
		}

		try {
			SysFuncEbo func = funcService.getFuncByMenu("stafflist.html", Constant.MENU_TYPE_CONSOLE);
			if (func != null)
				map.put("pmenu", func.getPid());
			else
				map.put("pmenu", -1);
			List<FuncDto> funcs = funcService.listUserFunc(userEbo.getUid());
			map.put("funcs", funcs);
			
			List<DepartmentDto> listDepartmentDto = deptService.listDepartmentDto(null,null, -1, null, 0, 0);
			map.put("listDepartmentDto", listDepartmentDto);

			List<OrgDto> listOrg = orgService.listOrg(null, null, 0, 0, false, 0, 0);
			map.put("listOrg", listOrg);
			
			List<SysRefValueEbo> refVlByRefId = baseService.getRefVlByRefId(55, null);
			map.put("refVlByRefId", refVlByRefId);

		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return mav;
	}

	// 获取staff列表
	@RequestMapping(value = "/mgr_stafflistjson.html")
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
			int orgId = Util.parseNumVl(request.getParameter("orgId"), -1);
			int deptId = Util.parseNumVl(request.getParameter("deptId"), 0);
			String flag = request.getParameter("flag");
			String status = request.getParameter("status");
			List<StaffDto> listStaffDto = staffService.listStaffDto(orgId, deptId,null,flag,status,page,iDisplayLength);
			List<List<Object>> aaData = new ArrayList<List<Object>>();
			List<Object> aaList = null;
			for (StaffDto staff : listStaffDto) {
				aaList = new ArrayList<Object>();
				aaList.add(staff.getStafId());
				aaList.add(Util.val(staff.getStafCode()));
				aaList.add(Util.val(staff.getStafStafCode()));
				aaList.add(Util.val(staff.getStafName()));
				aaList.add(Util.val(staff.getStafSex()));
				aaList.add(Util.val(staff.getBirthday()));
				aaList.add(Util.val(staff.getMobileNum()));
				aaList.add(Util.val(staff.getTypeId()));
				aaList.add(Util.val(staff.getTypeName()));
				aaList.add(Util.val(staff.getDeptId()));
				aaList.add(Util.val(staff.getDeptName()));
				aaList.add(Util.val(staff.getOrgId()));
				aaList.add(Util.val(staff.getOrgName()));
				aaList.add(Util.val(staff.getIdCard()));
				aaList.add(Util.val(DateUtil.formatDate(staff.getRegDate())));
				aaList.add(Util.val(staff.getFlag()));
				aaList.add(Util.val(staff.getStatus()));
				//aaList.add(Util.val(staff.getStaffDeptName()));
				aaList.add("");
				aaList.add(Util.val(staff.getHeadIconUrl()));
				aaList.add(Util.val(staff.getMemo()));
				aaList.add(Util.val(DateUtil.formatDate(staff.getCreateTime())));
				aaData.add(aaList);
			}
			map.put("aaData", aaData);
			map.put("result", 1);

			int totalRec = staffService.getTotalStaffDto(orgId, deptId,flag,status);
			map.put("iTotalRecords", totalRec);
			map.put("iTotalDisplayRecords", totalRec);
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 获取更新回显数据
	@RequestMapping(value = "/mgr_getstaffjson.html")
	public @ResponseBody
	Map<String, Object> getInfoJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		String staffcode = request.getParameter("staffcode");
		try {
			StaffEbo staffByCode = staffService.getStaffByCode(staffcode);
			if (staffByCode == null) {
				map.put("result", -1);
				map.put("messge", "没有找到对于的信息内容的编码'" + staffcode + "'");
				return map;
			}
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("name", staffByCode.getName());
			data.put("sex", staffByCode.getSex());
			data.put("birthday", staffByCode.getBirthday());
			data.put("mobileNum", staffByCode.getMobileNum());
			data.put("typeid", staffByCode.getTypeId());
			data.put("deptid", staffByCode.getDeptId());
			data.put("orgid", staffByCode.getOrgId());
			data.put("idcard", staffByCode.getIdCard());
			data.put("memo", staffByCode.getMemo());
			data.put("headiconurl", staffByCode.getHeadIconUrl());
			data.put("staffcode", staffcode);
			map.put("data", data);
			map.put("result", 1);

		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 添加修改咨询列表
	@RequestMapping(value = "/mgr_addormodifystaffjson.html")
	public @ResponseBody
	Map<String, Object> addinfolistjson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		try {
			StaffEto staffEto = new StaffEto();
			String code = request.getParameter("code");
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			String birthday = request.getParameter("birthday");
			String idCard = request.getParameter("idcard");
			String memo = request.getParameter("memo");
			String mobileNum = request.getParameter("mobileNum");
			String headiconurl = request.getParameter("headiconurl");
			int typeId = Util.parseNumVl(request.getParameter("typeid"), 0);
			int deptId = Util.parseNumVl(request.getParameter("deptid"), 0);
			int orgId = Util.parseNumVl(request.getParameter("orgid"), 0);

			staffEto.setName(name);
			staffEto.setSex(sex);
			staffEto.setBirthday(birthday);
			staffEto.setMobileNum(mobileNum);
			staffEto.setIdCard(idCard);
			staffEto.setTypeId(typeId);
			staffEto.setDeptId(deptId);
			staffEto.setOrgId(orgId);
			staffEto.setHeadIconUrl(headiconurl);
			if (Util.isNull(code)) {
				staffService.addStaff(staffEto);
				map.put("message", "添加info成功！");
			} else {
				StaffEbo staffByCode = staffService.getStaffByCode(code);
				staffByCode.setName(name);
				staffByCode.setSex(sex);
				staffByCode.setBirthday(birthday);
				staffByCode.setMobileNum(mobileNum);;
				staffByCode.setTypeId(typeId);
				staffByCode.setDeptId(deptId);
				staffByCode.setOrgId(orgId);
				staffByCode.setIdCard(idCard);
				staffByCode.setHeadIconUrl(headiconurl);
				staffByCode.setMemo(memo);
				if (staffByCode != null) {
					staffService.updateStaff(staffByCode);
					map.put("message", "修改info功！");
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
	
	// 通过stafftId获取医生列表
	@RequestMapping(value = "/getstaffbyidjson.html")
	public @ResponseBody
	Map<String, Object> getstaffbyid(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		int staffId = Util.parseNumVl(request.getParameter("staffId"), 0);
		try {
			StaffEbo staffById = staffService.getStaffById(staffId);
			if (staffById != null) {
				String staffCode = staffById.getCode();
				map.put("staffCode", staffCode);
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
