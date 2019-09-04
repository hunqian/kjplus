package com.kjplus.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kjplus.constant.Constant;
import com.kjplus.constant.ShareConstant;
import com.kjplus.dto.DeptStaffDto;
import com.kjplus.model.AdminUserEbo;
import com.kjplus.model.SysRefValueEbo;
import com.kjplus.service.IDeptService;
import com.kjplus.service.IStaffService;
import com.kjplus.service.ISysBaseService;
import com.mq.util.Util;
import com.ybk.exception.DataException;

@Api(tags = "基础参照", description = "关于基础参数的接口")
@Controller
public class BaseCtrl {

	private static Logger logger = Logger.getLogger(BaseCtrl.class);
	@Autowired
	private ISysBaseService baseService;
	@Autowired
	private IDeptService deptService;
	@Autowired
	private IStaffService staffService;

	// 根据code获得参照val
	@ApiOperation(value = "获得参照列表")
	@ApiImplicitParams({ @ApiImplicitParam(name = "code", value = "编号", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/listrefvl.html", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> listRefValue(HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", ShareConstant.CONNECT_STATUS);
		String typeCode = req.getParameter("code");

		try {
			List<SysRefValueEbo> vls = baseService.listRefVlByRefCode(typeCode, Constant.FLAG_YES);
			List<Map<String, Object>> vlist = new ArrayList<Map<String, Object>>();
			for (SysRefValueEbo vl : vls) {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("id", vl.getId());
				m.put("code", vl.getCode());
				m.put("name", vl.getName());
				vlist.add(m);
			}
			map.put("data", vlist);
			map.put("result", ShareConstant.RES_OK);
			map.put("message", "获得参照成功!");
		} catch (DataException e) {
			logger.error(e);
			map.put("result", ShareConstant.RES_ERROR);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 根据deptType获得该组织的团队或部门，以及医生信息
	@ApiOperation(value = "获得部门列表")
	@ApiImplicitParams({ 
		
		@ApiImplicitParam(name = "deptType", value = "部门类型", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = Constant.USER_CHECK_URI + "/listdept.html", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> listdept(HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", ShareConstant.CONNECT_STATUS);

		AdminUserEbo userEbo = (AdminUserEbo) req.getSession().getAttribute(Constant.KJAPI_SESS);
		if (userEbo == null) {
			map.put("result", ShareConstant.RES_UNLOGIN);
			map.put("message", "用户没有登录！不能获取部门信息");
			return map;
		}

		String deptType = req.getParameter("deptType");
		// 暂定 默认获取团队
		if (Util.isNull(deptType))
			deptType = ShareConstant.DEPT_TYPE_GRUOP;
		try {
			List<DeptStaffDto> listDeptStaff = staffService.listDeptStaffDto(userEbo.getOrgid(), deptType, false, true);

			List<Map<String, Object>> listDS = new ArrayList<Map<String, Object>>();
			for (DeptStaffDto d : listDeptStaff) {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("code", d.getDeptCode());
				m.put("name", d.getDeptName());
				m.put("memo", d.getDeptMemo());
				m.put("staffs", d.getStafs());
				listDS.add(m);
			}
			map.put("data", listDS);
			map.put("result", ShareConstant.RES_OK);
			map.put("message", "获得团队或部门成功!");
		} catch (DataException e) {
			logger.error(e);
			map.put("result", ShareConstant.RES_ERROR);
			map.put("message", e.getMessage());
		}
		return map;
	}

}
