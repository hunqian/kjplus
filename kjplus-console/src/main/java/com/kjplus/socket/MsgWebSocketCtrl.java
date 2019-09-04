package com.kjplus.socket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.kjplus.constant.Constant;
import com.kjplus.dto.KjAdminUserDto;
import com.kjplus.dto.MessageDto;
import com.kjplus.eto.ChatMainEto;
import com.kjplus.eto.SessionSocketEto;
import com.kjplus.model.SessionSocketEbo;
import com.kjplus.service.IChatMsgService;
import com.kjplus.service.ISessionSocketService;
import com.kjplus.service.IUserMapService;
import com.kjplus.util.MStringUtil;
import com.kjplus.util.SpringUtil;
import com.ybk.exception.DataException;

/**
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 *                 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 *                 
 */
/**
 * 
 * @author niuzhiwei
 *         <p>
 *         ServerEndpoint注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 *         注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端 session会话
 *         有一个很重要的概念 必须可以让用户进行会话重连！！！！！！所以必须要做持久化
 */

@ServerEndpoint(value = "/ws/msgsocket", configurator = HttpSessionConfigurator.class)
public class MsgWebSocketCtrl {
	private static Logger logger = Logger.getLogger(MsgWebSocketCtrl.class);

	// key 作为用户的标识 session作为用户 所对应的会话
	private static Map<String, Object> sessionInfoMap = new HashMap<String, Object>();

	// 用户 信息 全部加入缓存已提高
	private static Map<String, Object> userInfoMap = new HashMap<String, Object>();

	// 聊天业务层
	// spring不支持websocket,所以通过获取上下文的方式获取到相关的业务层代码
	private static IChatMsgService chatService = SpringUtil.getApplicationContext().getBean(IChatMsgService.class);
	private static ISessionSocketService sessSocketService = SpringUtil.getApplicationContext().getBean(ISessionSocketService.class);
	private static IUserMapService userMapService = SpringUtil.getApplicationContext().getBean(IUserMapService.class);

	// 用户打开一个连接的时候 给用户分配一个 唯一 id对应与之 进行数据互换的session
	@OnOpen
	public void onOpen(Session session, EndpointConfig config) throws IOException {
		synchronized (session) {// 另一个线程同样可以访问别的非this的代码块
			if (userInfoMap.size() > 1000) {// 防止内存溢出
				userInfoMap.clear();
			}
			if (sessionInfoMap.size() > 1000) {
				sessionInfoMap.clear();
			}
			// 拿到当前用户中的session信息
			HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
			// 拿到登录用户的相关信息
			KjAdminUserDto user = (KjAdminUserDto) httpSession.getAttribute(Constant.KJCONSOLE_SESS);
			// 判断用户是否登陆
			String jsonMessage = "";
			if (user != null) {
				boolean isNullSCode = user.getSessionCode() == null || user.getSessionCode() == "" ? true : false;
				if (isNullSCode) {// 用户登录没有带sessionCode
					try {
						int uid = userMapService.getUserIdByAdminUserId(user.getUid());
						SessionSocketEbo ss = sessSocketService.getSessionByUid(uid, Constant.FLAG_YES);
						if (ss != null) {// 数据库中存在 查到后赋值
							user.setSessionCode(ss.getSessionCode());
							sessionInfoMap.put(ss.getSessionCode(), session);
							userInfoMap.put(ss.getSessionCode(), user);
							jsonMessage = JSONObject.toJSONString("websoket连接成功！");
							session.getBasicRemote().sendText(jsonMessage);
						} else {// 数据库中不存在
							SessionSocketEto ssk = new SessionSocketEto();
							ssk.setUid(uid);
							int orgId = user.getOrgId() >= 0 ? user.getOrgId() : 0;
							ssk.setOrgId(orgId);
							String uuid = MStringUtil.getUUID();
							ssk.setSessionCode(uuid);
							sessSocketService.addSessionSocket(ssk);
							sessionInfoMap.put(uuid, session);
							userInfoMap.put(uuid, user);
							jsonMessage = JSONObject.toJSONString("websoket连接成功！");
							session.getBasicRemote().sendText(jsonMessage);
						}
					} catch (DataException e) {
						logger.error(e.getMessage());
						Thread.yield();// 产生异常时 防止 死锁杀死当前线程
					}
				} else {
					Session sess1 = (Session) sessionInfoMap.get(user.getSessionCode());
					if (sess1 != null) {
						sessionInfoMap.put(user.getSessionCode(), session);
						jsonMessage = JSONObject.toJSONString("websoket连接成功！");
						session.getBasicRemote().sendText(jsonMessage);
						return;
					} else {
						// 用户登录后携带sessioncode
						sessionInfoMap.put(user.getSessionCode(), session);
						userInfoMap.put(user.getSessionCode(), user);
						jsonMessage = JSONObject.toJSONString("websoket连接成功！");
						session.getBasicRemote().sendText(jsonMessage);
					}
				}
			} else {// 用户没有登录
				jsonMessage = JSONObject.toJSONString("websoket连接成功！");
				session.getBasicRemote().sendText(jsonMessage);
				return;
			}
		}
	}

	// 收到客户端消息后调用的方法 用于进行websocket心跳检测 直接发送编号
	// userInfo 为用户信息 需要 打开连接是 将用户的id传入
	@OnMessage
	public void onMessage(String userInfo, Session session) {
		// poolTaskExecutor.submit();
		String info = "你好啊";
		String jsonMessage = JSONObject.toJSONString(info);
		try {
			session.getBasicRemote().sendText(jsonMessage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 用户关闭会话时 删除 在 缓存map中 的session
	@OnClose
	public void onClose() {

	}

	// 发生错误时调用
	@OnError
	public void onError(Session session, Throwable error) {

	}

	/**
	 * 
	 * 可根据自己的需求添加方法调用
	 * 
	 * @param message
	 * @throws IOException
	 */
	public static void sendMessage(MessageDto message) throws IOException {
		synchronized (message) {
			// 登录管理员与用户关联，uid:登录用户ID
			String contSCode = message.getContSCode();
			Session session = (Session) sessionInfoMap.get(contSCode);
			if (session == null)
				return;
			String jsonMessage = JSONObject.toJSONString(message);
			session.getBasicRemote().sendText(jsonMessage);
			String msgType = message.getChatType();
			// 聊天类型,文字T/P图片/D文档/V音频/C表情
			ChatMainEto chatMainEto = new ChatMainEto();
			try {
				SessionSocketEbo sessionByCode = sessSocketService.getSessionByCode(message.getFrSCode(), Constant.FLAG_YES);
				SessionSocketEbo sessionByCode2 = sessSocketService.getSessionByCode(message.getContSCode(), Constant.FLAG_YES);
				chatMainEto.setFrmId(sessionByCode.getUid());
				chatMainEto.setToId(sessionByCode2.getUid());
				chatMainEto.setChatTime(message.getMsgTime());
				chatMainEto.setChatType(message.getChatType());
				chatMainEto.setStatus(message.getMsgStatus());

				if (msgType.equals(Constant.CHAT_TYPE_TEXT)) {
					chatService.addChatTextMessage(chatMainEto, message.getMsgInfo());
				} else if (msgType.equals(Constant.CHAT_TYPE_PICTURE)) {
					chatService.addChatPic(chatMainEto, message.getMsgType(), message.getMsgInfo());
				} else if (msgType.equals(Constant.CHAT_TYPE_DOC)) {
					chatService.addChatFile(chatMainEto, message.getMsgType(), message.getMsgInfo());
				} else if (msgType.equals(Constant.CHAT_TYPE_VIDEO)) {
					chatService.addChatVedio(chatMainEto, 11.0, message.getMsgInfo());
				} else if (msgType.equals(Constant.CHAT_TYPE_COUNTENANACE)) {
					chatService.addChatCountenance(chatMainEto, message.getMsgType(), null, message.getMsgInfo());
				}
			} catch (DataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}