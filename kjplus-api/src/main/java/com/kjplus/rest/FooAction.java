package com.kjplus.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kjplus.constant.Constant;
import com.kjplus.constant.ShareConstant;
import com.kjplus.model.AdminUserEbo;

@Api(tags = "测试", description = "关于测试的接口")
@Controller
public class FooAction {

	// private static Logger logger = Logger.getLogger(FooAction.class);

	// 测试数据
	@ApiOperation(value = "测试数据")
	@RequestMapping(value = "/noop.html", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> noOp(HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", ShareConstant.CONNECT_STATUS);

		map.put("result", 1);
		map.put("message", "调用NOOP返回成功！");
		return map;
	}

	// 测试数据
	@ApiOperation(value = "测试数据")
	@RequestMapping(value = Constant.USER_CHECK_URI + "/foo.html", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> foo(HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", ShareConstant.CONNECT_STATUS);
		AdminUserEbo userEbo = (AdminUserEbo) req.getSession().getAttribute(Constant.KJAPI_SESS);
		if (userEbo == null) {
			map.put("result", ShareConstant.RES_ERROR);
			map.put("message", "用户没有登录！");
			return map;
		}

		map.put("result", ShareConstant.RES_OK);
		map.put("message", "调用返回成功！");
		map.put("data", userEbo.getUid());
		return map;
	}
}
