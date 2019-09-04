package com.kjplus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kjplus.dto.ChatLatelyMsgDto;
import com.kjplus.model.ChatContactsEbo;
import com.kjplus.model.ChatCountenaceEbo;
import com.kjplus.model.ChatFileEbo;
import com.kjplus.model.ChatMainEbo;
import com.kjplus.model.ChatMsgEbo;
import com.kjplus.model.ChatPicEbo;
import com.kjplus.model.ChatTextMsgEbo;
import com.kjplus.model.ChatVedioEbo;
import com.kjplus.model.ContactsEbo;
import com.ybk.exception.DataException;

//聊天消息
@Repository("chatMsgDao")
public interface IChatMsgDao {

	/**
	 * 
	 * @param msgId
	 *            消息id (uuid)
	 * @throws DataException
	 */
	public ChatMainEbo getChatMainByMsgId(@Param("msgId") String msgId) throws DataException;

	/**
	 * 
	 * @param msgId
	 *            消息id (uuid)
	 * @throws DataException
	 */
	public ChatFileEbo getChatFileByMsgId(@Param("msgId") String msgId) throws DataException;

	/**
	 * 
	 * @param msgId
	 *            消息id (uuid)
	 * @throws DataException
	 */
	public ChatPicEbo getChatPicByMsgId(@Param("msgId") String msgId) throws DataException;

	/**
	 * 
	 * @param msgId
	 *            消息id (uuid)
	 * @throws DataException
	 */
	public ChatTextMsgEbo getChatTextByMsgId(@Param("msgId") String msgId) throws DataException;

	/**
	 * 
	 * @param msgId
	 *            消息id (uuid)
	 * @throws DataException
	 */
	public ChatCountenaceEbo getChatCountenaceByMsgId(@Param("msgId") String msgId) throws DataException;

	/**
	 * 
	 * @param msgId
	 *            消息id (uuid)
	 * @throws DataException
	 */
	public ChatVedioEbo getChatVedioByMsgId(@Param("msgId") String msgId) throws DataException;

	/**
	 * 
	 * @param chatMain
	 *            传入消息主表对象ChatMainEbo
	 * @throws DataException
	 */
	public void addChatMain(ChatMainEbo chatMain) throws DataException;

	/**
	 * 
	 * @param chatFile
	 *            传入消息文件对象ChatFileEbo对象
	 * @throws DataException
	 */
	public void addChatFile(ChatFileEbo chatFile) throws DataException;

	/**
	 * 
	 * @param chatPic
	 *            传入消息图片缩略图对象ChatPicEbo对象
	 * @throws DataException
	 */
	public void addChatPic(ChatPicEbo chatPic) throws DataException;

	/**
	 * 
	 * @param chatTextMsg
	 *            传入消息文字对象ChatTextMsgEbo对象
	 * @throws DataException
	 */
	public void addChatTextmsg(ChatTextMsgEbo chatTextMsg) throws DataException;

	/**
	 * 
	 * @param chatCountenace
	 *            传入消息表情对象ChatCountenaceEbo对象
	 * @throws DataException
	 */
	public void addChatCountenace(ChatCountenaceEbo chatCountenace) throws DataException;

	/**
	 * 
	 * @param chatVedio
	 *            传入消息视频对象ChatVedioEbo对象
	 * @throws DataException
	 */
	public void addChatVedio(ChatVedioEbo chatVedio) throws DataException;

	/**
	 * 
	 * @param msgId
	 *            消息id (uuid)
	 * @param status
	 *            消息主表状态
	 * @throws DataException
	 * 
	 */
	public void updateChatMainStatus(@Param("msgId") String msgId, @Param("status") String status) throws DataException;

	/**
	 * 
	 * @param msgId
	 *            消息id (uuid)
	 * @param status
	 *            消息主表状态
	 * @throws DataException
	 * 
	 */
	public List<ContactsEbo> listContacts(@Param("sessionCode") String sessionCode) throws DataException;

	/**
	 * 添加好友列表信息
	 * 
	 * @param chatContactsEbo
	 *            传入联系人对象ChatContactsEbo对象
	 * @throws DataException
	 */
	public void addChatContacts(ChatContactsEbo chatContactsEbo) throws DataException;

	/**
	 * 查询好友列表信息
	 * 
	 * @param thisUid
	 *            当前登录用户ID
	 * @return
	 * @throws DataException
	 */
	public List<ChatContactsEbo> chatContactsList(@Param("thisSessionCode") String thisSessionCode, @Param("contSessionCode") String contSessionCode,
			@Param("page") int page, @Param("paging") int paging) throws DataException;

	/**
	 * 删除好友
	 * 
	 * @param thisUid
	 *            登录用户ID
	 * @param contUid
	 *            好友ID
	 * @throws DataException
	 */
	public void delChatContacts(@Param("thisSessionCode") String thisSessionCode, @Param("contSessionCode") String contSessionCode) throws DataException;

	/**
	 * 修改好友状态
	 * 
	 * @param thisUid
	 * @param contUid
	 * @param status
	 */
	public void updateContuctsStatus(@Param("thisSessionCode") String thisSessionCode, @Param("contSessionCode") String contSessionCode,
			@Param("status") String status, @Param("flag") String flag) throws DataException;

	/**
	 * 消息列表
	 * 
	 * @param frmId
	 * @param toId
	 * @param chatType
	 * @param status
	 */
	public List<ChatMainEbo> listChatMain(@Param("frmId") Integer frmId, @Param("toId") Integer toId, @Param("chatType") String chatType,
			@Param("status") String status) throws DataException;

	/**
	 * 查询最近10条与指定好友的聊天消息
	 * 
	 * @param thisId
	 *            我的ID
	 * @param contId
	 *            好友ID
	 * @param page
	 * @param paging
	 * @return
	 * @throws DataException
	 */
	public List<ChatMsgEbo> listChats(@Param("frmId") Integer frmId, @Param("toId") Integer toId) throws DataException;

	/**
	 * 
	 * @param thisSessionCode
	 *            我的sessionCode
	 * @param contSessionCode
	 *            联系人的sessionCode
	 * @return
	 * @throws DataException
	 */
	public ChatContactsEbo getChatContact(@Param("thisSessionCode") String thisSessionCode, @Param("contSessionCode") String contSessionCode)
			throws DataException;

	/**
	 * 最近一条消息
	 * 
	 * @param thisSessionCode
	 * @return
	 * @throws DataException
	 */
	public ChatLatelyMsgDto latelyMsg(@Param("thisSessionCode") String thisSessionCode) throws DataException;

	/**
	 * 用户未读消息数
	 * 
	 * @param thisSessionCode
	 * @return
	 * @throws DataException
	 */
	public int notReadTotal(@Param("contSessionCode") String contSessionCode, @Param("thisSessionCode") String thisSessionCode) throws DataException;

	/**
	 * 查询联系人基础数据
	 * 
	 * @param thisSessionCode
	 * @return 登陆用户sessionCode
	 * @throws DataException
	 */
	public List<ChatContactsEbo> recentContacts(@Param("thisSessionCode") String thisSessionCode) throws DataException;

	/**
	 * 查询与指定好友最近的一条聊天记录
	 * 
	 * @param frmId
	 *            发送人Id
	 * @param toId
	 *            接收人ID
	 * @return
	 * @throws DataException
	 */
	public ChatMsgEbo chatContactsLatelyMessage(@Param("frmId") int frmId, @Param("toId") int toId) throws DataException;

	/**
	 * 更新我与好友最近聊天时间，双向，即改我的也改他的
	 * 
	 * @param thisSessionCode
	 * @param contSessionCode
	 * @param latelyTime
	 *            最近聊天时间
	 * @throws DataException
	 */
	public void updateLatelyChatTime(@Param("contSessionCode") String contSessionCode, @Param("thisSessionCode") String thisSessionCode)
			throws DataException;

}
