package com.kjplus.rest;

import java.io.UnsupportedEncodingException;
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
import org.ybk.basic.util.Util;

import com.kjplus.constant.ConsolePageFtl;
import com.kjplus.constant.Constant;
import com.kjplus.dao.IOrgDao;
import com.kjplus.dto.FuncDto;
import com.kjplus.dto.KjAdminUserDto;
import com.kjplus.dto.UserDto;
import com.kjplus.model.SysFuncEbo;
import com.kjplus.model.UserEbo;
import com.kjplus.service.IOrgService;
import com.kjplus.service.ISysBaseService;
import com.kjplus.service.ISysFuncService;
import com.kjplus.service.ISysRegionsService;
import com.kjplus.service.IUserService;
import com.ybk.exception.DataException;

@Controller
public class UserMgrCtrl {

	private static Logger logger = Logger.getLogger(UserMgrCtrl.class);
	@Autowired
	private IOrgDao orgDao;
	@Autowired
	private IOrgService orgService;
	@Autowired
	private ISysFuncService funcService;
	@Autowired
	private ISysRegionsService regionsService;
	@Autowired
	private ISysBaseService baseService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IUserService adminUserService;

	// 用户列表
	@RequestMapping("/userlist.html")
	public ModelAndView userList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = mav.getModel();
		mav.setViewName("user_list");

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
		if (userEbo == null) {
			mav.setViewName(ConsolePageFtl.LOGIN_ACE);
			map.put("result", -1);
			map.put("returnUrl", "/userlist.html");
			map.put("message", "用户没有登陆!");
			return mav;
		}

		try {
			SysFuncEbo func = funcService.getFuncByMenu("userlist.html", Constant.MENU_TYPE_MANAGER);
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

	@RequestMapping(value = "/mgr_userlistjson.html")
	public @ResponseBody
	Map<String, Object> userListJson(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);
		int iDisplayStart = Util.parseNumVl(request.getParameter("iDisplayStart"), 0);
		int iDisplayLength = Util.parseNumVl(request.getParameter("iDisplayLength"), 10);
		int page = iDisplayStart / iDisplayLength;
		if (page <= 0)
			page = 0;

		try {
			int uid = Util.parseNumVl(request.getParameter("uid"), 0);
			KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
			int orgId = userEbo.getOrgId();
			String status = request.getParameter("status");
			String nickName = request.getParameter("nickName");
			String mobileNum = request.getParameter("mobileNum");
			String userType = request.getParameter("userType");
			List<UserDto> userDto = userService.listUser(uid, nickName, orgId, status, mobileNum, userType, page, iDisplayLength);
			List<List<Object>> aaData = new ArrayList<List<Object>>();
			List<Object> aaList = null;
			for (UserDto u : userDto) {
				aaList = new ArrayList<Object>();
				aaList.add(u.getUid());
				aaList.add(u.getFace());
				aaList.add(u.getNickName());
				aaList.add(u.getUserName());
				aaList.add(u.getEmail());
				aaList.add(u.getMobileNum());
				aaList.add(u.getMobileFlag());
				aaList.add(u.getUserType());
				aaList.add(u.getStatus());
				aaList.add(u.getEffectFrom());
				aaList.add(u.getEffectTo());
				aaList.add(u.getSourceFlag());
				aaList.add(u.getCreateTime());
				aaList.add(u.getOrgid());
				aaList.add(u.getOrgName());
				aaData.add(aaList);

			}
			int totalRec = userService.getTotalUser(uid, nickName, orgId, status, mobileNum, userType);
			map.put("aaData", aaData);
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

	@RequestMapping(value = "/mgr_getimgjson.html")
	public @ResponseBody
	Map<String, Object> getInfoJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		int uid = Util.parseNumVl(request.getParameter("uid"), 0);
		try {
			UserEbo user = userService.getUserById(uid);
			if (user == null) {
				map.put("result", -1);
				map.put("messge", "没有找到对于的用户ID'" + uid + "'");
				return map;
			}
			Map<String, Object> data = new HashMap<String, Object>();

			data.put("userid", user.getUid());
			data.put("face", user.getFace());

			map.put("data", data);
			map.put("result", 1);

		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 上传用户头像
	@RequestMapping(value = "/mgr_addormodifyfacejson.html")
	public @ResponseBody
	Map<String, Object> addfacelistjson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		try {
			String face = request.getParameter("face");
			int uid = Util.parseNumVl(request.getParameter("uid"), 0);

			if (uid != 0) {
				userService.uploadFace(uid, face);
				map.put("result", 1);
				map.put("message", "上传头像成功");
			} else {
				map.put("result", -1);
				map.put("message", "上传头像失败");
			}

		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 根据电话号码查询用户列表
	@RequestMapping(value = "/mgr_userlistbyphonenumjson.html")
	public @ResponseBody
	Map<String, Object> userListByPhoneNumJson(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		try {
			KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
			int orgId = userEbo.getOrgId();
			String mobileNum = request.getParameter("mobileNum");
			List<UserDto> userDto = userService.listUser(0, null, orgId, "Y", mobileNum, "U", -1, -1);
			List<List<Object>> aaData = new ArrayList<List<Object>>();
			List<Object> aaList = null;
			for (UserDto u : userDto) {
				aaList = new ArrayList<Object>();
				aaList.add(u.getUid());
				aaList.add(u.getNickName());
				aaList.add(u.getMobileNum());
				aaData.add(aaList);
			}
			map.put("aaData", aaData);
			map.put("result", 1);
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 为档案绑定用户
	@RequestMapping(value = "/mgr_addUserprsnReljson.html")
	public @ResponseBody
	Map<String, Object> addUserPrsnRel(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		int uid = Util.parseNumVl(request.getParameter("uid"), 0);
		int prsnId = Util.parseNumVl(request.getParameter("prsnId"), 0);
		try {
			UserEbo user = userService.getUserById(uid);
			if (user == null) {
				map.put("result", -1);
				map.put("messge", "没有找到对于的用户ID'" + uid + "'");
				return map;
			}
			userService.addUserPersonRel(uid, "U", prsnId);
			map.put("result", 1);
			map.put("message", "档案已成功绑定用户");

		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}
}