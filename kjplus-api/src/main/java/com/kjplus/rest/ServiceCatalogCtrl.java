package com.kjplus.rest;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kjplus.constant.ShareConstant;

@Controller
public class ServiceCatalogCtrl {

	@ApiOperation(value = "signuplist")
	@ApiImplicitParams({  })
	@RequestMapping(value = "/signuplist.html", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> listAppiont(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", ShareConstant.CONNECT_STATUS);
		return map;
	}
}
