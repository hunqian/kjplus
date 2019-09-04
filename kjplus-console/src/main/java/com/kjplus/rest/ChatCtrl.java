package com.kjplus.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kjplus.constant.ConsolePageFtl;
import com.kjplus.constant.Constant;
import com.kjplus.dto.ChatContactsDto;
import com.kjplus.dto.ChatMsgDto;
import com.kjplus.dto.KjAdminUserDto;
import com.kjplus.dto.MessageDto;
import com.kjplus.model.SessionSocketEbo;
import com.kjplus.model.UserEbo;
import com.kjplus.service.IChatMsgService;
import com.kjplus.service.ISessionSocketService;
import com.kjplus.service.ISysFuncService;
import com.kjplus.service.IUserMapService;
import com.kjplus.service.IUserService;
import com.mq.call.CommandFactory;
import com.mq.call.MessageSendCall;
import com.mq.message.MsgCommand;
import com.ybk.exception.DataException;

@Controller
public class ChatCtrl {

	private static Logger logger = Logger.getLogger(ChatCtrl.class);

	@Autowired
	private ISysFuncService funcService;
	@Autowired
	private ISessionSocketService sessionSocketService;
	@Autowired
	private IUserMapService userMapService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IChatMsgService chatService;

	@Resource(name = "taskExecutor")
	private ThreadPoolTaskExecutor taskExecutor;

	// 好友列表
	@RequestMapping("/contactslist.html")
	public ModelAndView chatContucts(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = mav.getModel();
		mav.setViewName("contacts_list");

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJCONSOLE_SESS);
		if (userEbo == null) {
			mav.setViewName(ConsolePageFtl.LOGIN_ACE);
			map.put("result", -1);
			map.put("returnUrl", "/contactslist.html");
			map.put("message", "用户没有登陆!");
			return mav;
		}
		try {

			String thisSessionCode = null;

			if (userEbo.getSessionCode() == null || userEbo.getSessionCode().equals("")) {
				int uid = userMapService.getUserIdByAdminUserId(userEbo.getUid());
				thisSessionCode = sessionSocketService.getSessionByUid(uid, Constant.FLAG_YES).getSessionCode();
			} else {
				thisSessionCode = userEbo.getSessionCode();
			}

			List<ChatContactsDto> rContacts = chatService.recentContacts(thisSessionCode);
			map.put("rContacts", rContacts);
			if (rContacts.size() > 0) {
				map.put("firstcontact", rContacts.get(0));
			}

			List<ChatContactsDto> contacts = chatService.chatContactsList(thisSessionCode, null, 0, 10);
			map.put("contacts", contacts);

			int thisId = userMapService.getUserIdByAdminUserId(userEbo.getUid());
			UserEbo userById = userService.getUserById(thisId);
			map.put("userById", userById);
			map.put("thisSessionCode", thisSessionCode);
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return mav;
	}

	// 消息发送
	@RequestMapping(value = "/chatmsg.html")
	public @ResponseBody
	Map<String, Object> chatMsg(HttpServletRequest request, HttpServletResponse response) {
		// 加入线程锁防止并发导致数据库负载过高从而影响整体业务，并且防止线程池产生线程安全问题
		synchronized (request) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", 200);
			String sessionCode = request.getParameter("sessionCode");
			// TODO 发送文字消息
			String message = request.getParameter("message");
			try {
				KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJCONSOLE_SESS);
				int frmId = userMapService.getUserIdByAdminUserId(userEbo.getUid());
				UserEbo userById = userService.getUserById(frmId);
				int touid = sessionSocketService.getSessionByCode(sessionCode, Constant.FLAG_YES).getUid();
				List<SessionSocketEbo> session = sessionSocketService.listSessionByCod(sessionCode, Constant.FLAG_YES, touid);
				if (session.size() <= 0) {
					map.put("status", -1);
					map.put("message", "发送的用户无效");
				}
				final MsgCommand cmd = CommandFactory.getMsgCommand();
				MessageDto msg = new MessageDto();
				msg.setFrSCode(userEbo.getSessionCode());
				msg.setFrFace(userById.getFace());
				msg.setFrName(userById.getNickName());
				msg.setContSCode(sessionCode);
				// TODO 默认文本消息
				msg.setChatType("T");
				msg.setMsgType(Constant.CHAT_TYPE_TEXT);
				msg.setMsgInfo(message);
				cmd.setMsgBody(msg);
				// 发送对象消息
				taskExecutor.execute(new Runnable() {
					public void run() {
						try {
							MessageSendCall.topicProxyOjbectMessageSend(cmd);
						} catch (Exception e) {
							logger.error(e.getMessage());
						}
					}
				});
				chatService.updateLatelyChatTime(userEbo.getSessionCode(), sessionCode);
				map.put("msg", msg);
				map.put("result", 1);
				map.put("message", "消息发送成功");
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			return map;
		}
	}

	// 获取最近聊天记录
	@RequestMapping(value = "/getchatslistjson.html")
	public @ResponseBody
	Map<String, Object> getChatListJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJCONSOLE_SESS);
		String toSessionCode = request.getParameter("toSessionCode");
		try {
			List<ChatMsgDto> listChats = chatService.listChats(userEbo.getSessionCode(), toSessionCode);
			if (listChats == null) {
				map.put("data", "没有相关消息");
				map.put("result", -1);
				return map;
			} else {
				map.put("data", listChats);
				map.put("result", 1);
			}
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 最近联系人列表重排
	@RequestMapping(value = "/getlatelycontacts.html")
	public @ResponseBody
	Map<String, Object> getLatelyContacts(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJCONSOLE_SESS);
		try {
			List<ChatContactsDto> recentContacts = chatService.recentContacts(userEbo.getSessionCode());
			if (recentContacts == null) {
				map.put("data", "最近联系人列表为空");
				map.put("result", -1);
				return map;
			} else {
				map.put("data", recentContacts);
				map.put("result", 1);
			}
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}
}
