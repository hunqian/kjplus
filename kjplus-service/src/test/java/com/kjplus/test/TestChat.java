package com.kjplus.test;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kjplus.constant.Constant;
import com.kjplus.dto.ChatContactsDto;
import com.kjplus.dto.ChatMsgDto;
import com.kjplus.dto.ContactsDto;
import com.kjplus.eto.ChatContactsEto;
import com.kjplus.eto.ChatMainEto;
import com.kjplus.service.IChatMsgService;
import com.ybk.exception.DataException;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(value = "classpath:testbeans.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestChat {

	@Autowired
	private IChatMsgService chatMsgService;

	// 测试 文字消息类型添加
	@Test
	public void testAddChatMsgT() {
		ChatMainEto chatMain = new ChatMainEto();
		chatMain.setFrmId(2);
		chatMain.setToId(1);
		chatMain.setChatType(Constant.CHAT_TYPE_TEXT);
		String msgBody = "你掉钱了吗";
		try {
			chatMsgService.addChatTextMessage(chatMain, msgBody);
			System.out.println("添加文字消息成功");
		} catch (DataException e) {
			e.printStackTrace();
		}

	}

	// 测试文件消息类型添加
	// @Test
	public void testAddChatMsgF() {
		ChatMainEto chatMain = new ChatMainEto();
		chatMain.setFrmId(3);
		chatMain.setToId(4);
		chatMain.setChatType(Constant.CHAT_TYPE_DOC);
		String fileType = "doc";
		String url = "http://blog.csdn.net/guyeliuxiahui/article/details/49446295";
		try {
			chatMsgService.addChatFile(chatMain, fileType, url);
		} catch (DataException e) {
			e.printStackTrace();
		}

	}

	// 测试图片消息类型添加
	// @Test
	public void testAddChatMsgP() {
		ChatMainEto chatMain = new ChatMainEto();
		chatMain.setFrmId(2);
		chatMain.setToId(1);
		chatMain.setChatType(Constant.CHAT_TYPE_PICTURE);
		String picType = Constant.PIC_TYPE_THUMBNAIL;
		String url = "https://www.cnblogs.com/xwdreamer/archive/2012/06/13/2547426.html";
		try {
			chatMsgService.addChatPic(chatMain, picType, url);
		} catch (DataException e) {
			e.printStackTrace();
		}

	}

	// 测试表情
	// @Test
	public void testAddChatMsgC() {
		ChatMainEto chatMain = new ChatMainEto();
		chatMain.setFrmId(2);
		chatMain.setToId(1);
		chatMain.setChatType(Constant.CHAT_TYPE_COUNTENANACE);
		String ctneType = Constant.COUNTENACE_TYPE_DYNAMICS;
		String url = "https://www.cnblogs.com/xwdreamer/archive/2012/06/13/2547426.html";
		try {
			chatMsgService.addChatCountenance(chatMain, ctneType, null, url);
		} catch (DataException e) {
			e.printStackTrace();
		}

	}

	// 测试视频
	// @Test
	public void testAddChatMsgV() {
		ChatMainEto chatMain = new ChatMainEto();
		chatMain.setFrmId(2);
		chatMain.setToId(1);
		chatMain.setChatType(Constant.CHAT_TYPE_VIDEO);
		Double vedioLenTime = 11.0;
		String url = "https://www.cnblogs.com/xwdreamer/archive/2012/06/13/2547426.html";
		try {
			chatMsgService.addChatVedio(chatMain, vedioLenTime, url);
		} catch (DataException e) {
			e.printStackTrace();
		}

	}

	// 测试添加好友信息
	// @Test
	public void testAddChatContacts() {
		ChatContactsEto cEto = new ChatContactsEto();
		cEto.setThisSessionCode("a35294a649db4bc7a4ff17f98d1d90d8");
		cEto.setContSessionCode("c91601d2c58643d8895d8b927475d950");
		cEto.setContSign("1111");
		try {
			chatMsgService.addChatContacts(cEto);
			System.out.println("添加成功");
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 测试查询好友列表
	// @Test
	public void testContactsList() {

		String thisSessionCode = "a35294a649db4bc7a4ff17f98d1d90d8";
		String contSessionCode = "1bb38120367b46279f456a242c639138";
		int page = 0;
		int paging = 10;
		try {
			List<ChatContactsDto> chatContactsList = chatMsgService.chatContactsList(thisSessionCode, contSessionCode, page, paging);
			for (ChatContactsDto chat : chatContactsList) {
				System.out.println("好友昵称：" + chat.getContNickName());
				System.out.println("好友类别：" + chat.getContType());
			}
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 测试查询与指定好友最近10条聊天消息
	@Test
	public void testchatContucts() {

		String frmSessionCode = "a35294a649db4bc7a4ff17f98d1d90d8";
		String toSessionCode = "1bb38120367b46279f456a242c639138";
		try {
			List<ChatMsgDto> chatContacts = chatMsgService.listChats(frmSessionCode, toSessionCode);
			for (ChatMsgDto chat : chatContacts) {
				System.out.println("消息：" + chat.getMsgBody());
				System.out.println("发送人ID：" + chat.getFrmSessionCode());
				System.out.println("接收人ID：" + chat.getToSessionCode());
			}
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 测试删除好友列表
	// @Test
	public void testDelChatContacts() {

		String thisSessionCode = "1bb38120367b46279f456a242c639138";
		String contSessionCode = "a35294a649db4bc7a4ff17f98d1d90d8";
		try {
			chatMsgService.delChatContacts(thisSessionCode, contSessionCode);
			System.out.println("删除成功");
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 测试修改好友状态
	// @Test
	public void testChangeContuctsStatus() {

		String thisSessionCode = "1bb38120367b46279f456a242c639138";
		String contSessionCode = "a35294a649db4bc7a4ff17f98d1d90d8";
		String status = "B";
		String flag = "Y";
		try {
			chatMsgService.updateContuctsStatus(thisSessionCode, contSessionCode, status, flag);
			System.out.println("修改好友状态成功");
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 最新消息置顶
	@Test
	public void testLatelyMsg() {
		String thisSessionCode = "a35294a649db4bc7a4ff17f98d1d90d8";
		try {
			ContactsDto cd = chatMsgService.latelyMsg(thisSessionCode);
			System.out.println(cd);

		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 测试查询与该好友最近一条聊天记录
	// @Test
	public void testChatContactsMessage() {

		String thisSessionCode = "a35294a649db4bc7a4ff17f98d1d90d8";
		String contSessionCode = "1bb38120367b46279f456a242c639138";
		try {
			ChatMsgDto chatContactsMessage = chatMsgService.chatContactsMessage(thisSessionCode, contSessionCode);
			System.out.println(chatContactsMessage.getMsgBody());
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 测试查询最近好友列表
	// @Test
	public void testRecentContacts() {

		String thisSessionCode = "a35294a649db4bc7a4ff17f98d1d90d8";
		try {
			List<ChatContactsDto> chatContactsList = chatMsgService.recentContacts(thisSessionCode);
			for (ChatContactsDto chat : chatContactsList) {
				System.out.println("sessionCode:" + chat.getContSessionCode());
				System.out.println("好友昵称：" + chat.getContNickName());
				System.out.println("好友类别：" + chat.getContType());
			}
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 修改我与好友最近聊天时间
	@Test
	public void testUpdateLatelyChatTime() {

		String thisSessionCode = "a35294a649db4bc7a4ff17f98d1d90d8";
		String contSessionCode = "1bb38120367b46279f456a242c639138";
		try {
			chatMsgService.updateLatelyChatTime(thisSessionCode, contSessionCode);
			System.out.println("修改成功");
		} catch (DataException e) {
			e.printStackTrace();
		}
	}
}
