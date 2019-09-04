package com.kjplus.rest;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kjplus.service.ISysFuncService;
import com.mq.call.CommandFactory;
import com.mq.call.MessageSendCall;
import com.mq.message.MsgCommand;

/**
 * 
 * @author niuzhiwei 测试消息发送
 */
@Controller
public class testMssage {

	@Autowired
	private ISysFuncService funcService;

	private static Logger logger = Logger.getLogger(testMssage.class);

	@RequestMapping(value = "/testMsg.html")
	public @ResponseBody
	Map<String, Object> hellword(HttpServletRequest request, HttpServletResponse res) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			MsgCommand cmd = CommandFactory.getMsgCommand();
			cmd.setCmd("123");
			// 发送对象消息
			MessageSendCall.topicProxyOjbectMessageSend(cmd);
			map.put("message", "消息发送成功");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return map;
	}

	@RequestMapping("testwz.html")
	public @ResponseBody
	Map<String, Object> testwz(HttpServletRequest req, HttpServletResponse res) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mesg", "HelloWord");
		return map;
	}
}
