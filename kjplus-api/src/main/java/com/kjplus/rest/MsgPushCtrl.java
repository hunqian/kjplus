package com.kjplus.rest;

import io.swagger.annotations.Api;
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
import org.ybk.basic.util.Util;

import com.alibaba.fastjson.JSONObject;
import com.kjplus.constant.ShareConstant;
import com.push.call.RongCloudCall;
import com.push.constant.RConstant;
import com.push.message.TxtMessage;
import com.push.model.CodeSuccessResult;

@Api(tags = "消息推送", description = "关于消息推送的接口")
@Controller
public class MsgPushCtrl {

	// 向APP端推送消息
	@ApiOperation(value = "文件上传")
	@ApiImplicitParams({ @ApiImplicitParam(name = "frToken", value = "fromToken", required = true, dataType = "String", paramType = "body"),
			@ApiImplicitParam(name = "toToken", value = "toToken", required = true, dataType = "String", paramType = "body"),
			@ApiImplicitParam(name = "message", value = "消息内容", required = true, dataType = "String", paramType = "body") })
	@RequestMapping(value = "/msgpush.html", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> msgPush(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", ShareConstant.CONNECT_STATUS);
		RongCloudCall rongCloud = RongCloudCall.getInstance(RConstant.RONG_KEY, RConstant.RONG_SECRET);
		String frToken = request.getParameter("frToken");
		String toToken = request.getParameter("toToken");
		String message = request.getParameter("message");
		if (Util.isNull(frToken)) {
			map.put("message", "发送用户id不能空");
			// map.put("code", 0);
			map.put("result", ShareConstant.RES_ERROR);
			return map;
		}
		if (Util.isNull(toToken)) {
			map.put("message", "接收用户的id不能空");
			// map.put("code", 0);
			map.put("result", ShareConstant.RES_ERROR);
			return map;
		}
		try {
			// 接收消息的数组
			String[] messagePublishPrivateToUserId = new String[1];
			// 发送到 用户的id
			messagePublishPrivateToUserId[0] = toToken;
			// 消息对象
			TxtMessage messagePublishPrivateTxtMessage = new TxtMessage(message, frToken);
			// 消息map
			Map<String, String> msgMap = new HashMap<String, String>();
			msgMap.put("pushData", message);
			// 消息转换为json
			String msgJson = JSONObject.toJSONString(msgMap);
			// 调用融云接口发送
			CodeSuccessResult messagePublishPrivateResult = rongCloud.message
					.publishPrivate(frToken, messagePublishPrivateToUserId, messagePublishPrivateTxtMessage, frToken, msgJson, "4", 0, 0, 0, 0);
			// 获取调用结果
			JSONObject resJson = JSONObject.parseObject(messagePublishPrivateResult.toString());
			if (resJson.getInteger("code") == ShareConstant.CONNECT_STATUS) {
				map.put("message", "消息发送成功");
				map.put("result", ShareConstant.RES_OK);
			} else {
				map.put("message", "消息发送失败");
				map.put("result", 0);
			}
			return map;
		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("result", ShareConstant.RES_ERROR);
			return map;
		}
	}

}
