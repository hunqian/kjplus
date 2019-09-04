package com.kjplus.service;

import java.util.List;

import com.kjplus.dto.ChatContactsDto;
import com.kjplus.dto.ChatMsgDto;
import com.kjplus.dto.ContactsDto;
import com.kjplus.eto.ChatContactsEto;
import com.kjplus.eto.ChatMainEto;
import com.kjplus.model.ChatContactsEbo;
import com.kjplus.model.ChatCountenaceEbo;
import com.kjplus.model.ChatFileEbo;
import com.kjplus.model.ChatMainEbo;
import com.kjplus.model.ChatPicEbo;
import com.kjplus.model.ChatTextMsgEbo;
import com.kjplus.model.ChatVedioEbo;
import com.ybk.exception.DataException;

//聊天消息业务层接口
public interface IChatMsgService {

	// 通过消息id获取消息主表对象
	public ChatMainEbo getChatMainByMsgId(String msgId) throws DataException;

	// 通过消息id获取消息文件对象
	public ChatFileEbo getChatFileByMsgId(String msgId) throws DataException;

	// 通过消息id获取消息图片对象
	public ChatPicEbo getChatPicByMsgId(String msgId) throws DataException;

	// 通过消息id获取消息文本对象
	public ChatTextMsgEbo getChatTextByMsgId(String msgId) throws DataException;

	// 通过消息id获取消息表情对象
	public ChatCountenaceEbo getChatCountenaceByMsgId(String msgId) throws DataException;

	// 通过消息id获取消息视频对象
	public ChatVedioEbo getChatVedioByMsgId(String msgId) throws DataException;

	// 修改消息主表对象
	public void updateChatMainStatus(String msgId, String status) throws DataException;

	// 添加聊天文件
	public ChatMainEbo addChatFile(ChatMainEto chatMain, String fileType, String url) throws DataException;

	// 添加聊天图片
	public ChatMainEbo addChatPic(ChatMainEto chatMain, String picType, String url) throws DataException;

	// 添加文字消息
	public ChatMainEbo addChatTextMessage(ChatMainEto chatMain, String msgBody) throws DataException;

	// 添加表情消息
	public ChatMainEbo addChatCountenance(ChatMainEto chatMain, String ctneType, String ctneCode, String url) throws DataException;

	// 添加视频消息
	public ChatMainEbo addChatVedio(ChatMainEto chatMain, Double vedioLenTime, String url) throws DataException;

	/**
	 * 聊天消息添加 总调用方法
	 * 
	 * @param chatMain
	 *            聊天主表对象
	 * @param fileType
	 *            文件类型
	 * @param picType
	 *            图片类型
	 * @param ctneType
	 *            表情类型
	 * @param ctneCode
	 *            表情编号
	 * @param vedioLenTime
	 *            视频时长
	 * @param url
	 *            通用url
	 * @param msgBody
	 *            文字消息体
	 * @return
	 * @throws DataException
	 */
	public ChatMainEbo addChatMain(ChatMainEto chatMain, String fileType, String picType, String ctneType, String ctneCode, Double vedioLenTime,
			String url, String msgBody) throws DataException;

	/**
	 * 添加好友
	 * 
	 * @param chContacts
	 * @return
	 * @throws DataException
	 */
	public ChatContactsEbo addChatContacts(ChatContactsEto chContacts) throws DataException;

	// 查看好友列表
	public List<ChatContactsDto> chatContactsList(String thisSessionCode, String contSessionCode, int page, int paging) throws DataException;

	// 查看最近联系人列表
	public List<ChatContactsDto> recentContacts(String thisSessionCode) throws DataException;

	// 删除指定好友
	public void delChatContacts(String thisSessionCode, String contSessionCode) throws DataException;

	// 修改好友状态
	public void updateContuctsStatus(String thisSessionCode, String contSessionCode, String status, String flag) throws DataException;

	// 消息列表
	public List<ChatMainEbo> listChatMain(String frmSessonCode, String toSessonCode, String chatType, String status) throws DataException;

	// 查询最近10条消息
	public List<ChatMsgDto> listChats(String frSessionCode, String toSessionCode) throws DataException;

	// 获取好友信息
	public ChatContactsEbo getChatContact(String thisSessionCode, String contSessionCode) throws DataException;

	// 最近一条消息
	public ContactsDto latelyMsg(String thisSessionCodee) throws DataException;

	// 最近一条消息
	public ChatMsgDto chatContactsMessage(String thisSessionCode, String contSessionCode) throws DataException;

	// 发送消息后 更新我与好友最近聊天时间
	public void updateLatelyChatTime(String thisSessionCode, String contSessionCode) throws DataException;
}
