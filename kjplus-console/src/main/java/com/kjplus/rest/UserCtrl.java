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
import org.ybk.basic.util.Util;

import com.kjplus.constant.ConsolePageFtl;
import com.kjplus.constant.Constant;
import com.kjplus.dao.IOrgDao;
import com.kjplus.dto.FuncDto;
import com.kjplus.dto.KjAdminUserDto;
import com.kjplus.dto.UserDto;
import com.kjplus.model.SysFuncEbo;
import com.kjplus.model.UserEbo;
import com.kjplus.service.*;
import com.ybk.exception.DataException;

@Controller
public class UserCtrl {

	private static Logger logger = Logger.getLogger(UserCtrl.class);
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
		
		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJCONSOLE_SESS);
		if (userEbo == null) {
			mav.setViewName(ConsolePageFtl.LOGIN_ACE);
			map.put("result", -1);
			map.put("returnUrl", "/userlist.html");
			map.put("message", "用户没有登陆!");
			return mav;
		}
		
		try{
			SysFuncEbo func = funcService.getFuncByMenu("userlist.html", Constant.MENU_TYPE_CONSOLE);
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
	Map<String, Object> userListJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		int iDisplayStart = Util.parseNumVl(request.getParameter("iDisplayStart"), 0);
		int iDisplayLength = Util.parseNumVl(request.getParameter("iDisplayLength"), 10);
		int page = iDisplayStart / iDisplayLength;
		if (page <= 0)
			page = 0;

		try {
			int uid = Util.parseNumVl(request.getParameter("uid"), 0);
			int orgId = Util.parseNumVl(request.getParameter("orgId"), 0);
			String status = request.getParameter("status");
			String nickName = request.getParameter("nickName");
			String mobileNum = request.getParameter("mobileNum");
			String userType = request.getParameter("userType");
			List<UserDto> userDto = userService.listUser(uid, nickName, orgId, status, mobileNum,userType, page, iDisplayLength);
			List<List<Object>> aaData = new ArrayList<List<Object>>();
			List<Object> aaList = null;
			for (UserDto u : userDto) {
				aaList = new ArrayList<Object>();
				aaList.add(u.getUid());
				aaList.add(u.getNickName());
				aaList.add(u.getUserName());
				aaList.add(u.getEmail());
				aaList.add(u.getMobileNum());
				aaList.add(u.getMobileFlag());
				aaList.add(u.getUserType());
				aaList.add(u.getStatus());
				aaList.add(u.getFace());
				aaList.add(u.getEffectFrom());
				aaList.add(u.getEffectTo());
				aaList.add(u.getSourceFlag());
				aaList.add(u.getCreateTime());
				aaList.add(u.getOrgid());
				aaList.add(u.getOrgName());
				aaData.add(aaList);

			}
			int totalRec = userService.getTotalUser(uid, nickName, orgId,status, mobileNum,userType);
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
	//获取图片
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

			data.put("userid",user.getUid());
			data.put("face",user.getFace());
			
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
			
			if(uid != 0){
				userService.uploadFace(uid, face);
				map.put("result", 1);
				map.put("message", "上传头像成功");
			}else{
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
	
}