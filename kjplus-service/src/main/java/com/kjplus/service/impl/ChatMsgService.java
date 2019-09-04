package com.kjplus.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.ybk.basic.util.Util;

import com.kjplus.constant.Constant;
import com.kjplus.dao.IChatMsgDao;
import com.kjplus.dto.ChatContactsDto;
import com.kjplus.dto.ChatLatelyMsgDto;
import com.kjplus.dto.ChatMsgDto;
import com.kjplus.dto.ContactsDto;
import com.kjplus.eto.ChatContactsEto;
import com.kjplus.eto.ChatCountenaceEto;
import com.kjplus.eto.ChatFileEto;
import com.kjplus.eto.ChatMainEto;
import com.kjplus.eto.ChatPicEto;
import com.kjplus.eto.ChatTextMsgEto;
import com.kjplus.eto.ChatVedioEto;
import com.kjplus.model.ChatContactsEbo;
import com.kjplus.model.ChatCountenaceEbo;
import com.kjplus.model.ChatFileEbo;
import com.kjplus.model.ChatMainEbo;
import com.kjplus.model.ChatMsgEbo;
import com.kjplus.model.ChatPicEbo;
import com.kjplus.model.ChatTextMsgEbo;
import com.kjplus.model.ChatVedioEbo;
import com.kjplus.model.SessionSocketEbo;
import com.kjplus.model.UserEbo;
import com.kjplus.service.IChatMsgService;
import com.kjplus.service.ISessionSocketService;
import com.kjplus.service.IUserMapService;
import com.kjplus.service.IUserService;
import com.kjplus.util.DataValUtil;
import com.kjplus.util.URLAvailability;
import com.mq.util.DateUtil;
import com.ybk.exception.DataException;

@Service("chatService")
public class ChatMsgService implements IChatMsgService {

	private static Logger logger = Logger.getLogger(ChatMsgService.class);

	@Autowired
	private IChatMsgDao chatMsgDao;
	@Autowired
	private IUserService userService;
	@Autowired
	private IUserMapService userMapService;
	@Autowired
	private ISessionSocketService sessionSocketService;

	// 通过id获取聊天主表对象
	public ChatMainEbo getChatMainByMsgId(String msgId) throws DataException {
		if (StringUtils.isBlank(msgId.trim()))
			return null;
		return chatMsgDao.getChatMainByMsgId(msgId);
	}

	// 通过id获取聊天文件对象
	public ChatFileEbo getChatFileByMsgId(String msgId) throws DataException {
		if (StringUtils.isBlank(msgId.trim()))
			return null;
		return chatMsgDao.getChatFileByMsgId(msgId);
	}

	// 通过id获取聊天图片对象
	public ChatPicEbo getChatPicByMsgId(String msgId) throws DataException {
		if (StringUtils.isBlank(msgId.trim()))
			return null;
		return chatMsgDao.getChatPicByMsgId(msgId);
	}

	// 通过id获取聊天文本对象
	public ChatTextMsgEbo getChatTextByMsgId(String msgId) throws DataException {
		if (StringUtils.isBlank(msgId.trim()))
			return null;
		return chatMsgDao.getChatTextByMsgId(msgId);
	}

	// 通过消息id获取消息表情对象
	public ChatCountenaceEbo getChatCountenaceByMsgId(String msgId) throws DataException {
		if (StringUtils.isBlank(msgId.trim()))
			return null;
		return chatMsgDao.getChatCountenaceByMsgId(msgId);
	}

	// 通过消息id获取消息视频对象
	public ChatVedioEbo getChatVedioByMsgId(String msgId) throws DataException {
		if (StringUtils.isBlank(msgId.trim()))
			return null;
		return chatMsgDao.getChatVedioByMsgId(msgId);
	}

	// 聊天文件
	public ChatMainEbo addChatFile(ChatMainEto chatMain, String fileType, String url) throws DataException {
		return addChatMain(chatMain, fileType, null, null, null, 0.0, url, null);
	}

	// 聊天图片
	public ChatMainEbo addChatPic(ChatMainEto chatMain, String picType, String url) throws DataException {

		return addChatMain(chatMain, null, picType, null, null, 0.0, url, null);
	}

	// 文字消息
	public ChatMainEbo addChatTextMessage(ChatMainEto chatMain, String msgBody) throws DataException {
		return addChatMain(chatMain, null, null, null, null, 0.0, null, msgBody);
	}

	// 表情消息
	public ChatMainEbo addChatCountenance(ChatMainEto chatMain, String ctneType, String ctneCode, String url) throws DataException {
		return addChatMain(chatMain, null, null, ctneType, ctneCode, 0.0, url, null);
	}

	// 视频消息
	public ChatMainEbo addChatVedio(ChatMainEto chatMain, Double vedioLenTime, String url) throws DataException {
		return addChatMain(chatMain, null, null, null, null, vedioLenTime, url, null);
	}

	// 加入事务
	@Transactional(value = "txManager", rollbackFor = { RuntimeException.class, Exception.class, DataException.class }, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, readOnly = true, timeout = 3)
	public ChatMainEbo addChatMain(ChatMainEto chatMain, String fileType, String picType, String ctneType, String ctneCode, Double vedioLenTime,
			String url, String msgBody) throws DataException {
		// 做控制验证
		DataValUtil.dataValidation(chatMain.getClass(), chatMain);
		UserEbo frUser = userService.getUserById(chatMain.getFrmId());
		if (frUser == null)
			throw new DataException("系统不存在此消息发送人用户");
		UserEbo toUser = userService.getUserById(chatMain.getToId());
		if (toUser == null)
			throw new DataException("系统不存在此消息接收人用户");

		// 查询发送人id
		ChatMainEbo main = new ChatMainEbo();
		BeanUtils.copyProperties(chatMain, main);
		String uuid = getUUID();
		main.setId(uuid);
		if (Constant.CHAT_TYPE_DOC.equals(chatMain.getChatType())) {// 如果是文档类型
			if (StringUtils.isBlank(fileType))
				throw new DataException("请传入文件类型");
			if (StringUtils.isBlank(url))
				throw new DataException("文件路径不能空");
			ChatFileEto chatFile = new ChatFileEto();
			chatFile.setMsgId(uuid);
			chatFile.setFileType(fileType);
			chatFile.setFileUrl(url);
			addChatFile(chatFile);
		} else if (Constant.CHAT_TYPE_PICTURE.equals(chatMain.getChatType())) {// 如果是图片类型
			if (StringUtils.isBlank(picType))
				throw new DataException("请传入图片类型");
			if (StringUtils.isBlank(url))
				throw new DataException("图片路径不能空");
			ChatPicEto chatPic = new ChatPicEto();
			chatPic.setMsgId(uuid);
			chatPic.setPicType(picType);
			chatPic.setPicUrl(url);
			addChatPic(chatPic);
		} else if (Constant.CHAT_TYPE_TEXT.equals(chatMain.getChatType())) {// 如果是文本类型
			ChatTextMsgEto chatTextMsg = new ChatTextMsgEto();
			chatTextMsg.setMsgId(uuid);
			chatTextMsg.setMsgBody(msgBody);
			addChatTextmsg(chatTextMsg);
		} else if (Constant.CHAT_TYPE_COUNTENANACE.equals(chatMain.getChatType())) {// 如果是表情类型
			ChatCountenaceEto chatCountenace = new ChatCountenaceEto();
			chatCountenace.setMsgId(uuid);
			chatCountenace.setCtneType(ctneType);
			chatCountenace.setDynamicPicUrl(url);
			chatCountenace.setCtneCode(ctneCode);
			addChatCountenace(chatCountenace);
		} else if (Constant.CHAT_TYPE_VIDEO.equals(chatMain.getChatType())) {// 如果是视频类型
			ChatVedioEto chatVedio = new ChatVedioEto();
			chatVedio.setMsgId(uuid);
			chatVedio.setVedioLenTime(vedioLenTime);
			chatVedio.setVedioUrl(url);
			addChatVedio(chatVedio);
		}

		try {
			chatMsgDao.addChatMain(main);
		} catch (DataException e) {
			throw new DataException(e.getMessage());
		}
		return null;
	}

	// 聊天文件添加
	private ChatFileEbo addChatFile(ChatFileEto chatFile) throws DataException {
		DataValUtil.dataValidation(chatFile.getClass(), chatFile);
		// 通过文件url模拟打开连接验证 数据文件的url可用性
		boolean isFlag = URLAvailability.isConnect(chatFile.getFileUrl(), 2);
		if (!isFlag)
			throw new DataException("请确认文件网络路径是否正确");
		ChatFileEbo file = new ChatFileEbo();
		BeanUtils.copyProperties(chatFile, file);
		chatMsgDao.addChatFile(file);
		return file;
	}

	// 聊天图片添加
	private ChatPicEbo addChatPic(ChatPicEto chatPic) throws DataException {
		DataValUtil.dataValidation(chatPic.getClass(), chatPic);
		// 通过文件url模拟打开连接验证 数据文件的url可用性
		boolean isFlag = URLAvailability.isConnect(chatPic.getPicUrl(), 2);
		if (!isFlag)
			throw new DataException("请确认文件网络路径是否正确");
		ChatPicEbo pic = new ChatPicEbo();
		BeanUtils.copyProperties(chatPic, pic);
		chatMsgDao.addChatPic(pic);
		return pic;
	}

	// 聊天文字消息添加
	private ChatTextMsgEbo addChatTextmsg(ChatTextMsgEto chatTextMsg) throws DataException {
		DataValUtil.dataValidation(chatTextMsg.getClass(), chatTextMsg);
		if (StringUtils.isBlank(chatTextMsg.getMsgBody().trim()))
			throw new DataException("不能发送空消息");
		if (chatTextMsg.getMsgBody().trim().length() >= 1024)
			throw new DataException("文字长度超过系统限制");
		ChatTextMsgEbo msg = new ChatTextMsgEbo();
		BeanUtils.copyProperties(chatTextMsg, msg);
		msg.setId(getUUID());
		chatMsgDao.addChatTextmsg(msg);
		return msg;
	}

	// 聊天表情添加
	private ChatCountenaceEbo addChatCountenace(ChatCountenaceEto chatCountenace) throws DataException {
		DataValUtil.dataValidation(chatCountenace.getClass(), chatCountenace);
		if (StringUtils.isBlank(chatCountenace.getCtneType()))
			throw new DataException("表情类型不能空");
		if (Constant.COUNTENACE_TYPE_STATIC.equals(chatCountenace.getCtneType())) {
			if (StringUtils.isBlank(chatCountenace.getCtneCode()))
				throw new DataException("非动态表情不能空");
		} else if (Constant.COUNTENACE_TYPE_DYNAMICS.equals(chatCountenace.getCtneType())) {
			if (StringUtils.isBlank(chatCountenace.getDynamicPicUrl()))
				throw new DataException("动态表情url地址不能空");
			// 做url可达性验证
			boolean isFlag = URLAvailability.isConnect(chatCountenace.getDynamicPicUrl(), 2);
			if (!isFlag)
				throw new DataException("请确认文件网络路径是否正确");
		}
		ChatCountenaceEbo countenace = new ChatCountenaceEbo();
		BeanUtils.copyProperties(chatCountenace, countenace);
		chatMsgDao.addChatCountenace(countenace);
		return countenace;
	}

	// 聊天视频添加
	private ChatVedioEbo addChatVedio(ChatVedioEto chatVedio) throws DataException {
		DataValUtil.dataValidation(chatVedio.getClass(), chatVedio);
		String vedioType = "";
		if (chatVedio.getVedioLenTime() > 10.0)// 大于10秒大视频
			vedioType = Constant.VEDIO_TYPE_BIG;
		else
			vedioType = Constant.VEDIO_TYPE_SMALL;
		// 做url可达性验证
		boolean isFlag = URLAvailability.isConnect(chatVedio.getVedioUrl(), 2);
		if (!isFlag)
			throw new DataException("请确认文件网络路径是否正确");
		ChatVedioEbo vedio = new ChatVedioEbo();
		BeanUtils.copyProperties(chatVedio, vedio);
		vedio.setVedioType(vedioType);
		chatMsgDao.addChatVedio(vedio);
		return vedio;
	}

	// 修改聊天消息状态
	public void updateChatMainStatus(String msgId, String status) throws DataException {

		if (StringUtils.isBlank(status.trim()))
			return;
		if (StringUtils.isBlank(msgId.trim()))
			return;
		chatMsgDao.updateChatMainStatus(msgId, status);
	}

	// 添加联系人
	public ChatContactsEbo addChatContacts(ChatContactsEto chContacts) throws DataException {
		// 做控制验证
		DataValUtil.dataValidation(chContacts.getClass(), chContacts);

		if (chContacts.getThisSessionCode().equals(chContacts.getContSessionCode()))
			return null;

		if (chContacts.getContSessionCode() != null) {
			List<ChatContactsDto> chatContactsList = chatContactsList(chContacts.getThisSessionCode(), chContacts.getContSessionCode(), 0,
					Integer.MAX_VALUE);
			if (chatContactsList.size() > 0) {
				return null;
			}
		}
		// 查询系统是否存在该用户
		SessionSocketEbo sessionByCode = sessionSocketService.getSessionByCode(chContacts.getContSessionCode(), "Y");
		UserEbo userById = userService.getUserById(sessionByCode.getUid());
		if (userById == null) {
			throw new DataException("系统无此用户" + chContacts.getContSessionCode());
		}
		ChatContactsEbo chatContactsEbo = new ChatContactsEbo();
		BeanUtils.copyProperties(chContacts, chatContactsEbo);
		chatContactsEbo.setContNickName(userById.getNickName());
		chatContactsEbo.setContFace(userById.getFace());
		chatContactsEbo.setContType(userById.getUserType());
		chatContactsEbo.setStatus("H");
		chatContactsEbo.setFlag("Y");
		chatContactsEbo.setLatelyTime(0);
		;
		try {
			chatMsgDao.addChatContacts(chatContactsEbo);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return chatContactsEbo;

	}

	// 查询联系人列表
	public List<ChatContactsDto> chatContactsList(String thisSessionCode, String contSessionCode, int page, int paging) throws DataException {
		List<ChatContactsEbo> chatContactsList = chatMsgDao.chatContactsList(thisSessionCode, contSessionCode, page, paging);
		List<ChatContactsDto> chatContactList = new ArrayList<ChatContactsDto>();
		ChatContactsDto chatContactsDto = null;
		for (ChatContactsEbo chatContactsEbo : chatContactsList) {
			chatContactsDto = new ChatContactsDto();
			BeanUtils.copyProperties(chatContactsEbo, chatContactsDto);
			chatContactList.add(chatContactsDto);
		}
		return chatContactList;
	}

	// 删除指定好友
	public void delChatContacts(String thisSessionCode, String contSessionCode) throws DataException {
		if (Util.isNull(thisSessionCode)) {
			throw new DataException("系统无此用户" + thisSessionCode);
		}

		if (Util.isNull(contSessionCode))
			return;
		try {
			chatMsgDao.delChatContacts(thisSessionCode, contSessionCode);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	// 修改好友状态
	public void updateContuctsStatus(String thisSessionCode, String contSessionCode, String status, String flag) throws DataException {
		boolean isNull = Util.isNull(thisSessionCode) || Util.isNull(contSessionCode) ? true : false;
		if (isNull)
			return;
		// 查询是否有此好友
		List<ChatContactsDto> chatContactsList = chatContactsList(thisSessionCode, contSessionCode, 0, 10);
		// 好友不存在不予以处理
		if (chatContactsList.size() <= 0)
			return;
		ChatContactsDto cct = chatContactsList.get(0);
		boolean isFalg = status.equals(cct.getStatus()) && flag.equals(cct.getFlag()) ? true : false;
		if (isFalg)
			return;
		if (status.equals("H") && flag.equals("N"))
			throw new DataException("设置为好友时状态记录不能无效");
		chatMsgDao.updateContuctsStatus(thisSessionCode, contSessionCode, status, flag);

	}

	// 查询消息列表
	public List<ChatMainEbo> listChatMain(String frmSessonCode, String toSessonCode, String chatType, String status) throws DataException {
		Boolean isNull = Util.isNull(frmSessonCode) && Util.isNull(toSessonCode) ? true : false;
		if (isNull)
			return null;
		SessionSocketEbo frmSession = sessionSocketService.getSessionByCode(frmSessonCode, Constant.FLAG_YES);
		SessionSocketEbo toSession = sessionSocketService.getSessionByCode(toSessonCode, Constant.FLAG_YES);
		List<ChatMainEbo> listChatMain = chatMsgDao.listChatMain(frmSession.getUid(), toSession.getUid(), null, status);
		return listChatMain;
	}

	// 查找好友
	public ChatContactsEbo getChatContact(String thisSessionCode, String contSessionCode) throws DataException {
		if (Util.isNull(thisSessionCode) || Util.isNull(contSessionCode))
			return null;
		return chatMsgDao.getChatContact(thisSessionCode, contSessionCode);
	};

	// 最新消息置顶
	public ContactsDto latelyMsg(String thisSessionCode) throws DataException {
		if (Util.isNull(thisSessionCode))
			return null;
		// 查询最近一条消息
		ChatLatelyMsgDto clm = chatMsgDao.latelyMsg(thisSessionCode);
		if (clm == null)
			return null;
		ContactsDto cd = new ContactsDto();
		// 聊天类型 文字 图片等
		String chatType = clm.getChatType();
		// 消息事件
		String chatMsgId = clm.getChatId();
		// 消息时间
		cd.setMsgTime(DateUtil.formatShortDateTime(clm.getChatTime()));
		// 聊天类型
		cd.setChatType(chatType);
		// 最近一条消息的状态
		cd.setMsgStatus(clm.getChatStatus());
		ChatPicEbo cp = null;
		ChatFileEbo ce = null;
		ChatVedioEbo cv = null;
		ChatCountenaceEbo cc = null;
		// 等于当前sessionCode 说明那个sessionCode 不是我 而是别人 我当然就要看别人发给我的
		if (!thisSessionCode.equals(clm.getSessionCode1())) {
			cd.setContFace(clm.getUser1Face());
			cd.setContNName(clm.getUser1Name());
			cd.setContSCode(clm.getSessionCode1());
			// 未读消息数
			int notRead = chatMsgDao.notReadTotal(clm.getSessionCode1(), thisSessionCode);
			if (notRead >= 100)
				notRead = 99;
			cd.setUnreadNum(notRead);
		} else {
			cd.setContFace(clm.getUser2Face());
			cd.setContNName(clm.getUser2Name());
			cd.setContSCode(clm.getSessionCode2());
			int notRead = chatMsgDao.notReadTotal(clm.getSessionCode2(), thisSessionCode);
			if (notRead >= 100)
				notRead = 99;
			cd.setUnreadNum(notRead);
		}
		// 聊天消息类型
		if (Constant.CHAT_TYPE_TEXT.equals(chatType))
			cd.setMsgInfo(getChatTextByMsgId(chatMsgId).getMsgBody());
		else if (Constant.CHAT_TYPE_PICTURE.equals(chatType)) {
			cp = getChatPicByMsgId(chatMsgId);
			cd.setMsgInfo(cp.getMsgId());
			cd.setMsgType(cp.getPicType());// 消息类型
		} else if (Constant.CHAT_TYPE_DOC.equals(chatType)) {
			ce = getChatFileByMsgId(chatMsgId);
			cd.setMsgInfo(ce.getFileUrl());
			cd.setMsgType(ce.getFileType());// 消息类型
		} else if (Constant.CHAT_TYPE_VIDEO.equals(chatType)) {
			cv = getChatVedioByMsgId(chatMsgId);
			cd.setMsgInfo(cv.getVedioUrl());
			cd.setMsgType(cv.getVedioType());// 消息类型
		} else if (Constant.CHAT_TYPE_COUNTENANACE.equals(chatType)) {
			cc = getChatCountenaceByMsgId(chatMsgId);
			cd.setMsgType(cc.getCtneType());// 消息类型
			if (Constant.COUNTENACE_TYPE_STATIC.equals(cc.getCtneType())) {
				cd.setMsgInfo(cc.getCtneCode());
			}
		}
		return cd;
	}

	// 查询我与指定好友最近10条聊天记录
	public List<ChatMsgDto> listChats(String frSessionCode, String toSessionCode) throws DataException {
		Boolean isNull = Util.isNull(frSessionCode) || Util.isNull(toSessionCode) ? true : false;
		if (isNull)
			return null;
		SessionSocketEbo frmSession = sessionSocketService.getSessionByCode(frSessionCode, Constant.FLAG_YES);
		SessionSocketEbo toSession = sessionSocketService.getSessionByCode(toSessionCode, Constant.FLAG_YES);
		List<ChatMsgEbo> listChats = chatMsgDao.listChats(frmSession.getUid(), toSession.getUid());
		if (listChats.size() == 0) {
			return null;
		}
		List<ChatMsgDto> chats = new ArrayList<ChatMsgDto>();
		ChatMsgDto chatMsgDto = null;
		SessionSocketEbo sessionByUid = null;
		SessionSocketEbo sessionByUid2 = null;
		for (ChatMsgEbo chatMainEbo : listChats) {
			// 获取聊天信息数据
			chatMsgDto = new ChatMsgDto();
			BeanUtils.copyProperties(chatMainEbo, chatMsgDto);
			sessionByUid = sessionSocketService.getSessionByUid(chatMainEbo.getFrmId(), Constant.FLAG_YES);
			sessionByUid2 = sessionSocketService.getSessionByUid(chatMainEbo.getToId(), Constant.FLAG_YES);
			chatMsgDto.setFrmSessionCode(sessionByUid.getSessionCode());
			chatMsgDto.setToSessionCode(sessionByUid2.getSessionCode());
			chatMsgDto.setMsgId(chatMainEbo.getId());
			if (Constant.CHAT_TYPE_TEXT.equals(chatMainEbo.getChatType())) {
				if (chatMainEbo.getText() != null) {
					chatMsgDto.setMsgBody(chatMainEbo.getText());
				}
			} else if (Constant.CHAT_TYPE_PICTURE.equals(chatMainEbo.getChatType())) {
				if (chatMainEbo.getPicUrl() != null) {
					chatMsgDto.setMsgBody(chatMainEbo.getPicUrl());
				}
			} else if (Constant.CHAT_TYPE_DOC.equals(chatMainEbo.getChatType())) {
				if (chatMainEbo.getFileUrl() != null) {
					chatMsgDto.setMsgBody(chatMainEbo.getFileUrl());
				}
			} else if (Constant.CHAT_TYPE_VIDEO.equals(chatMainEbo.getChatType())) {
				if (chatMainEbo.getVedioUrl() != null) {
					chatMsgDto.setMsgBody(chatMainEbo.getVedioUrl());
				}
			} else if (Constant.CHAT_TYPE_COUNTENANACE.equals(chatMainEbo.getChatType())) {
				if (chatMainEbo.getDynUrl() != null) {
					chatMsgDto.setMsgBody(chatMainEbo.getDynUrl());
				}
			}
			chats.add(chatMsgDto);
		}
		return chats;
	}

	// 查询最近联系人列表
	public List<ChatContactsDto> recentContacts(String thisSessionCode) throws DataException {

		List<ChatContactsDto> chatList = new ArrayList<ChatContactsDto>();
		if (Util.isNull(thisSessionCode)) {
			return chatList;
		}
		SessionSocketEbo sessionByCode = sessionSocketService.getSessionByCode(thisSessionCode, Constant.FLAG_YES);
		if (sessionByCode.getUid() == 0) {
			throw new DataException("该用户不存在SessionCode");
		}
		List<ChatContactsEbo> recentContacts = chatMsgDao.recentContacts(thisSessionCode);
		List<ChatContactsDto> chatDtoOrderByTime = new ArrayList<ChatContactsDto>();
		ChatContactsDto chatContactsDto = null;
		if (recentContacts.size() == 0) {
			return chatList;
		}
		for (ChatContactsEbo chatContactsEbo : recentContacts) {
			if (chatContactsEbo.getLatelyTime() == 0) {
				continue;
			}
			chatContactsDto = new ChatContactsDto();
			chatContactsDto.setContSessionCode(chatContactsEbo.getContSessionCode());
			chatContactsDto.setContNickName(chatContactsEbo.getContNickName());
			chatContactsDto.setContFace(chatContactsEbo.getContFace());
			chatContactsDto.setContType(chatContactsEbo.getContType());
			chatContactsDto.setLatelyTime(chatContactsEbo.getLatelyTime());
			chatContactsDto.setStatus(chatContactsEbo.getStatus());
			chatContactsDto.setFlag(chatContactsEbo.getFlag());
			chatDtoOrderByTime.add(chatContactsDto);
		}
		return chatDtoOrderByTime;
	}

	// 查询最近一条聊天信息
	public ChatMsgDto chatContactsMessage(String thisSessionCode, String contSessionCode) throws DataException {

		Boolean isNull = Util.isNull(thisSessionCode) && Util.isNull(contSessionCode) ? true : false;
		if (isNull)
			return null;
		SessionSocketEbo sessionByCode = sessionSocketService.getSessionByCode(thisSessionCode, Constant.FLAG_YES);
		SessionSocketEbo sessionByCode2 = sessionSocketService.getSessionByCode(contSessionCode, Constant.FLAG_YES);
		if (sessionByCode == null || sessionByCode2 == null) {
			throw new DataException("没有该用户");
		}
		int frmId = sessionByCode.getUid();
		int toId = sessionByCode2.getUid();
		ChatMsgEbo latelyMsgEbo = chatMsgDao.chatContactsLatelyMessage(frmId, toId);
		ChatMsgDto latelyMsgDto = new ChatMsgDto();
		if (latelyMsgEbo == null) {
			return null;
		}
		BeanUtils.copyProperties(latelyMsgEbo, latelyMsgDto);
		SessionSocketEbo sessionByUid = sessionSocketService.getSessionByUid(latelyMsgEbo.getFrmId(), Constant.FLAG_YES);
		SessionSocketEbo sessionByUid2 = sessionSocketService.getSessionByUid(latelyMsgEbo.getToId(), Constant.FLAG_YES);
		latelyMsgDto.setFrmSessionCode(sessionByUid.getSessionCode());
		latelyMsgDto.setToSessionCode(sessionByUid2.getSessionCode());
		latelyMsgDto.setMsgId(latelyMsgEbo.getId());
		if (Constant.CHAT_TYPE_TEXT.equals(latelyMsgEbo.getChatType())) {
			if (latelyMsgEbo.getText() != null) {
				latelyMsgDto.setMsgBody(latelyMsgEbo.getText());
			}
		} else if (Constant.CHAT_TYPE_PICTURE.equals(latelyMsgDto.getChatType())) {
			if (latelyMsgEbo.getPicUrl() != null) {
				latelyMsgDto.setMsgBody(latelyMsgEbo.getPicUrl());
			}
		} else if (Constant.CHAT_TYPE_DOC.equals(latelyMsgEbo.getChatType())) {
			if (latelyMsgEbo.getFileUrl() != null) {
				latelyMsgDto.setMsgBody(latelyMsgEbo.getFileUrl());
			}
		} else if (Constant.CHAT_TYPE_VIDEO.equals(latelyMsgEbo.getChatType())) {
			if (latelyMsgEbo.getVedioUrl() != null) {
				latelyMsgDto.setMsgBody(latelyMsgEbo.getVedioUrl());
			}
		} else if (Constant.CHAT_TYPE_COUNTENANACE.equals(latelyMsgEbo.getChatType())) {
			if (latelyMsgEbo.getDynUrl() != null) {
				latelyMsgDto.setMsgBody(latelyMsgEbo.getDynUrl());
			}
		}
		return latelyMsgDto;
	}

	// 发送消息后 更新我与好友最近聊天时间
	public void updateLatelyChatTime(String thisSessionCode, String contSessionCode) throws DataException {
		Boolean isNull = Util.isNull(thisSessionCode) && Util.isNull(contSessionCode) ? true : false;
		if (isNull)
			return;
		SessionSocketEbo sessionByCode = sessionSocketService.getSessionByCode(thisSessionCode, Constant.FLAG_YES);
		SessionSocketEbo sessionByCode2 = sessionSocketService.getSessionByCode(contSessionCode, Constant.FLAG_YES);
		if (sessionByCode == null || sessionByCode2 == null) {
			throw new DataException("用户不存在");
		}
		try {
			chatMsgDao.updateLatelyChatTime(thisSessionCode, contSessionCode);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	// 产生永不重复的消息ID(uuid)
	private String getUUID() {
		synchronized (this) {
			// 获取uuid
			UUID uuid = UUID.randomUUID();
			// 通过正则表达式将 - 替换掉 随机生成 0 -9 的一位数字做补充 充分保证uuid的永不重复
			String repUUID = uuid.toString().replaceAll("\\-", "" + Math.abs(new Random().nextInt() % 9));
			return repUUID;
		}
	}

}
