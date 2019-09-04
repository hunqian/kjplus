package com.kjplus.rest;

import java.io.IOException;
import java.util.*;
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
import com.kjplus.constant.Constant;
import com.kjplus.constant.ConstantUrlWx;
import com.kjplus.dto.UserSess;
import com.kjplus.model.UserEbo;
import com.kjplus.service.IUserService;
import com.ybk.exception.DataException;
import com.ykisswx.rest.WxBaseCtrl;
import com.ykisswx.service.IWxAccountService;
import com.ykisswx.service.IWxMgrService;
import com.ykisswx.service.IWxUserService;

@Controller
public class UserCtrl extends WxBaseCtrl {

	@Autowired
	private IUserService userService;
	@Autowired
	private IWxMgrService weixinMgrService;
	@Autowired
	private IWxAccountService accWxService;
	@Autowired
	private IWxUserService wxUserService;

	private static Logger logger = Logger.getLogger(UserCtrl.class);

	// 用户登录
	@RequestMapping(value = ConstantUrlWx.WX_MBLOGIN)
	public @ResponseBody
	ModelAndView mblogin(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = mav.getModel();
		map.put("code", 200);
		
		mav.setViewName("mblogin");
		String username = request.getParameter("username");
		if (Util.isNull(username))
			username = "";
		map.put("username", username);
		String password = request.getParameter("password");
		String vericode = request.getParameter("vericode");
		String returnUrl = request.getParameter("returnUrl");
		if (Util.isNull(returnUrl))
			returnUrl = "";
		map.put("returnUrl", returnUrl);

		//直接返回
		if(Util.isNull(username)){
			return mav;
		}
		
		// 用户登陆的veritype是mblogin
		String veritype = "mblogin";
		try {
			// 直接返回
			if (Util.isNull(username) && Util.isNull(password)) {
				return mav;
			}

			String vericode2 = (String) request.getSession().getAttribute(Constant.KJ_SESS_VERICODE + veritype);
			if (Util.isNull(vericode) || !vericode.equals(vericode2)) {
				map.put("message", "验证码为空或验证码不匹配!");
				return mav;
			}

			UserEbo userEbo = userService.login(username, password);
			if (userEbo == null) {
				map.put("message", "用户名或不匹配,请重新输入!");
				return mav;
			}
			
			UserSess ui = (UserSess) request.getSession().getAttribute(Constant.KJMB_SESS);
			if (ui == null) {
				ui = new UserSess();
			}
			ui.setNickname(userEbo.getNickName());
			//ui.setId(userEbo.getUid());
			ui.setUid(userEbo.getUid());
			ui.setOrgId(userEbo.getOrgId());
			request.getSession().setAttribute(Constant.KJMB_SESS, ui);

			map.put("result", 1);
			map.put("message", "操作成功!");

			if (Util.isNotNull(returnUrl)) {
				return new ModelAndView("redirect:" + returnUrl);
			} else {
				return new ModelAndView("redirect:" + ConstantUrlWx.WX_INDEX);
			}
		} catch (DataException e) {
			logger.error(e);
			map.put("message", e.getMessage());
			map.put("returnUrl", returnUrl);
		}
		return mav;
	}

	// 用户注册
	@RequestMapping(value = ConstantUrlWx.WX_MBREGIST)
	public @ResponseBody
	ModelAndView mbregist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mbregist");
		return mav;
	}

	// 密码找回
	@RequestMapping(value = ConstantUrlWx.WX_MBFINDPASS)
	public @ResponseBody
	ModelAndView mbfindpass(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mbfindpass");
		return mav;
	}

	// 验证码
	@RequestMapping(value = ConstantUrlWx.WX_MBVERICODE)
	public @ResponseBody
	void veriCode(HttpServletRequest request, HttpServletResponse response) {

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
				code = Util.genDigiCodeStr(4);
			else
				code = Util.genDigiCodeStr(6);
			request.getSession().setAttribute(Constant.KJ_SESS_VERICODE + veritype, code);
			cage.draw(code, response.getOutputStream());
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
