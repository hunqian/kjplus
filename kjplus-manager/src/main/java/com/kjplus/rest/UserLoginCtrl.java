package com.kjplus.rest;

import java.io.IOException;
import java.util.HashMap;
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

import com.github.cage.Cage;
import com.github.cage.GCage;
import com.kjplus.constant.ConsolePageFtl;
import com.kjplus.constant.Constant;
import com.kjplus.dto.KjAdminUserDto;
import com.kjplus.model.AdminUserEbo;
import com.kjplus.service.IAdminUserService;
import com.kjplus.service.ISysFuncService;
import com.ybk.exception.DataException;

@Controller
public class UserLoginCtrl {

	@Autowired
	private IAdminUserService adminUserService;
	@Autowired
	private ISysFuncService funcService;

	private static Logger logger = Logger.getLogger(UserLoginCtrl.class);

	// 登录
	@RequestMapping(value = "/index.html")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView(ConsolePageFtl.LOGIN_ACE);
		Map<String, Object> map = mav.getModel();
		map.put("code", 200);
		return mav;
	}

	// 登录
	@RequestMapping(value = "/login.html")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {

		String returnUrl = request.getParameter("returnUrl");
		KjAdminUserDto user = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
		try {
			if (user != null) {
				if (Util.isNull(returnUrl))
					returnUrl = funcService.getDefaultFunc(user.getUid());
				return new ModelAndView("redirect:" + returnUrl);
			}
		} catch (DataException e) {
			logger.error(e);
		}

		ModelAndView mav = new ModelAndView(ConsolePageFtl.LOGIN_ACE);
		Map<String, Object> map = mav.getModel();
		map.put("code", 200);
		if (Util.isNull(returnUrl))
			returnUrl = "";
		map.put("returnUrl", returnUrl);
		return mav;
	}

	// 退出
	@RequestMapping(value = "/logout.html")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute(Constant.KJMGR_SESS);
		return new ModelAndView("redirect:/login.html");
	}

	// json登录返回
	@RequestMapping(value = "/loginj.html")
	public @ResponseBody Map<String, Object> loginj(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		String user = request.getParameter("username");
		String password = request.getParameter("password");
		String returnUrl = request.getParameter("returnUrl");
		String vericode = request.getParameter("vericode");

		// 直接返回
		if (Util.isNull(user) && Util.isNull(password)) {
			map.put("message", "不能为空");
			map.put("result", -1);
			return map;
		}

		if (Util.isNull(vericode)) {
			map.put("message", "验证码不能为空");
			map.put("result", -1);
			return map;
		}

		try {
			String vericode0 = (String) request.getSession().getAttribute(Constant.KJ_MGR_SESS_VERICODE + "login");
			if (!vericode.equals(vericode0)) {
				map.put("message", "验证码不匹配");
				map.put("result", -1);
				return map;
			}

			AdminUserEbo userEbo = adminUserService.login(user, password);

			KjAdminUserDto adminUser = new KjAdminUserDto();
			adminUser.setUid(userEbo.getUid());
			adminUser.setUserStatus(userEbo.getStatus());
			adminUser.setUserType(userEbo.getUserType());
			adminUser.setOrgId(userEbo.getOrgid());
			adminUser.setNickName(userEbo.getNickName());
			adminUser.setFaceUrl(userEbo.getFace());
			
			// 平台用户的orgid=0
			if (userEbo.getOrgid() == null || userEbo.getOrgid().intValue() <= 0) {
				map.put("message", "无操作权限,登录失败!");
				map.put("result", -2);
				return map;
			}

			if (Util.isNull(returnUrl)) {
				returnUrl = funcService.getDefaultFunc(userEbo.getUid());
			}

			map.put("result", 1);
			if (Util.isNotNull(returnUrl)) {
				map.put("returnUrl", returnUrl);
			} else {
				map.put("returnUrl", "index.html");
			}
			request.getSession().setAttribute(Constant.KJMGR_SESS, adminUser);
		} catch (DataException e) {
			logger.error(e);
			map.put("message", e.getMessage());
			map.put("result", 0);
		}
		return map;
	}

	// 验证码
	@RequestMapping(value = "/vericode.html")
	public @ResponseBody void veriCode(HttpServletRequest request, HttpServletResponse response) {

		try {
			// 调用客户端自己决定业务认证类型
			String veritype = request.getParameter("veritype");
			if (Util.isNull(veritype))
				veritype = "";
			Cage cage = new GCage();
			String code = null;
			if ("mblogin".equals(veritype))
				code = Util.genDigiCodeStr(4);
			else if ("mbbind".equals(veritype))
				code = Util.genDigiCodeStr(5);
			else if ("findpass".equals(veritype))
				code = Util.genDigiCodeStr(6);
			else
				code = Util.genDigiCodeStr(6);
			request.getSession().setAttribute(Constant.KJ_MGR_SESS_VERICODE + veritype, code);
			cage.draw(code, response.getOutputStream());
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
