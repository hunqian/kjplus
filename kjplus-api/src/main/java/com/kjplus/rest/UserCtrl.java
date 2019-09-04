package com.kjplus.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ybk.basic.util.Util;

import com.kjplus.constant.Constant;
import com.kjplus.constant.ShareConstant;
import com.kjplus.model.AdminUserEbo;
import com.kjplus.service.IAdminUserService;
import com.kjplus.service.IAppKeyService;
import com.kjplus.service.ICheckLoginService;
import com.ybk.exception.DataException;

@Api(tags = "登录", description = "关于登录的接口")
@Controller
public class UserCtrl {

	@Autowired
	private ICheckLoginService checkLoginService;
	@Autowired
	private IAdminUserService adminUserService;
	@Autowired
	private IAppKeyService appKeyService;

	@ApiOperation(value = "用户登录")
	@ApiImplicitParams({ @ApiImplicitParam(name = "username", value = "用户名或手机号", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/login.html", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> userLog(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", ShareConstant.CONNECT_STATUS);
		// 用户名密码
		String mobile = request.getParameter("username");
		String password = request.getParameter("password");
		if (Util.isNull(mobile)) {
			map.put("result", 0);
			map.put("message", "手机号码不能为空！");
			return map;
		}

		if (Util.isNull(password) || password.length() < 6) {
			map.put("result", 0);
			map.put("message", "密码不能为空长度不能小于6！");
			return map;
		}

		try {
			request.getSession().removeAttribute(Constant.KJAPI_SESS);
			AdminUserEbo userEbo = adminUserService.login(mobile, password);
			if (userEbo == null) {
				map.put("result", ShareConstant.RES_ERROR);
				map.put("message", "用户名或者密码不匹配！");
				return map;
			}

			request.getSession().setAttribute(Constant.KJAPI_SESS, userEbo);
			map.put("opentoken", checkLoginService.genToken(userEbo));
			map.put("result", ShareConstant.RES_OK);
			map.put("message", "登陆成功!");
		} catch (DataException e) {
			map.put("result", ShareConstant.RES_ERROR);
			map.put("message", e.getMessage());
		}
		return map;
	}

	@ApiOperation(value = "用户退出")
	@ApiImplicitParams({ @ApiImplicitParam(name = "opentoken", value = "令牌", required = true, dataType = "String", paramType = "body") })
	@RequestMapping(value = Constant.USER_CHECK_URI + "/logout.html", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> userCheckLogout(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", ShareConstant.CONNECT_STATUS);

		AdminUserEbo userEbo = (AdminUserEbo) request.getSession().getAttribute(Constant.KJAPI_SESS);
		if (userEbo == null) {
			map.put("result", ShareConstant.RES_ERROR);
			map.put("message", "用户未登录，无需退出登录状态!");
			return map;
		}
		// 移除KJAPI_SESS
		request.getSession().removeAttribute(Constant.KJAPI_SESS);

		// 修改opentoken状态
		String opentoken = request.getParameter("opentoken");
		try {
			// 失效token
			appKeyService.invalidKeyByToken(opentoken);
			map.put("result", ShareConstant.RES_OK);
			map.put("message", "退出成功!");
		} catch (DataException e) {
			map.put("result", ShareConstant.RES_ERROR);
			map.put("message", e.getMessage());
		}
		return map;
	}

	@ApiOperation(value = "用户退出")
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> userWebLogout(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", ShareConstant.CONNECT_STATUS);

		AdminUserEbo userEbo = (AdminUserEbo) request.getSession().getAttribute(Constant.KJAPI_SESS);
		if (userEbo == null) {
			map.put("result", ShareConstant.RES_ERROR);
			map.put("message", "用户未登录，无需退出登录状态!");
			return map;
		}

		// 移除KJAPI_SESS
		request.getSession().removeAttribute(Constant.KJAPI_SESS);
		map.put("result", ShareConstant.RES_OK);
		map.put("message", "退出成功!");

		return map;
	}
}