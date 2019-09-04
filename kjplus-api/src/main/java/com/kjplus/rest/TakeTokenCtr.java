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

import com.alibaba.fastjson.JSONObject;
import com.kjplus.constant.Constant;
import com.kjplus.constant.ShareConstant;
import com.kjplus.dto.RongTokenDto;
import com.kjplus.dto.TokenUserOrgDto;
import com.kjplus.eto.AdminUserEto;
import com.kjplus.model.RongTokenEbo;
import com.kjplus.model.UserEbo;
import com.kjplus.service.IAppKeyService;
import com.kjplus.service.IRongTokenService;
import com.kjplus.service.IUserMapService;
import com.kjplus.service.IUserService;
import com.push.call.RongCloudCall;
import com.push.constant.RConstant;
import com.push.model.TokenResult;

@Api(tags = "RToken", description = "关于RToken的接口")
@Controller
public class TakeTokenCtr {

	@Autowired
	private IAppKeyService appKeyService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IUserMapService userMapService;
	@Autowired
	private IRongTokenService rongTokenService;

	// 配置所对应的所有参照
	@ApiOperation(value = "文件上传")
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", value = "本地token", required = true, dataType = "String", paramType = "body"),
			@ApiImplicitParam(name = "reqTime", value = "请求时间", required = true, dataType = "Integer", paramType = "body") })
	@RequestMapping(value = "/getRToken.html", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> tableCfgRefJson(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", ShareConstant.CONNECT_STATUS);

		String token = request.getParameter("token");

		// 请求时间
		int reqTime = Util.parseNumVl(request.getParameter("reqTime"), 0);
		if (Util.isNull(token)) {
			map.put("message", "空token");
			map.put("code", 0);
			return map;
		}
		RongCloudCall rongCloud = RongCloudCall.getInstance(RConstant.RONG_KEY, RConstant.RONG_SECRET);
		// 获取 Token 方法
		TokenResult userGetTokenResult = null;
		JSONObject json = null;
		try {
			// false 抛出超时异常
			appKeyService.checkToken(token, Constant.TOKEN_OUT_TIME, false);
			TokenUserOrgDto tokenUser = appKeyService.getUserByToken(token);
			String nickName = "";
			// TODO:头像 （必传） 后续弄 先用融云默认的
			if (tokenUser != null) {
				UserEbo user = userService.getUserById(userMapService.getUserIdByAdminUserId(tokenUser.getUid()));
				nickName = user.getNickName();
				if (Util.isNull(nickName))
					nickName = Util.genAuthCode(AdminUserEto.MAX_USERNAME_LEN);
			}

			userGetTokenResult = rongCloud.user.getToken(token, nickName, "http://www.rongcloud.cn/images/logo.png");
			json = JSONObject.parseObject(userGetTokenResult.toString());

			// 将用户的融token添加到数据库
			RongTokenDto rongTokenByToken = rongTokenService.getRongTokenByToken(userGetTokenResult.getToken());
			if (rongTokenByToken != null) {
				map.put("message", "融云token已存在");
				return map;
			}

			if (json.getInteger("code") == ShareConstant.CONNECT_STATUS) {
				map.put("message", "获取融云token成功");
				map.put("rtoken", json.getString("token"));
				map.put("userId", json.getString("userId"));
				RongTokenEbo rong = new RongTokenEbo();
				rong.setUid(userService.getUserById(userMapService.getUserIdByAdminUserId(tokenUser.getUid())).getUid());
				rong.setReqTime(reqTime);
				rong.setFlag("Y");
				RongTokenEbo addRongToken = rongTokenService.addRongToken(rong);
				if (addRongToken != null) {
					map.put("message", "获取融云token成功");
				}
			} else {
				map.put("message", json.getString("errorMessage"));
				map.put("rtoken", json.getString("token"));
				map.put("token", token);
			}
			return map;
		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("code", 0);
			return map;
		}
	}
}
