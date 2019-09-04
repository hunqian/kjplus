package com.ykisswx.test;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.ybk.basic.util.DateUtil;

import com.ykisswx.dto.WxTextMsgDto;
import com.ykisswx.eto.WxAccountEto;
import com.ykisswx.eto.WxTextMsgEto;
import com.ykisswx.model.WxAccountEbo;
import com.ykisswx.model.inner.WxTextMsgInnerEbo;
import com.ykisswx.service.IWxTextMsgService;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(value = "classpath:beans.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestWxTextMsg {

	@Autowired
	private IWxTextMsgService wxTextMsgService;

	// 测试获得menu
	@Test
	public void testListTextMsg() {
		try {
			int accid = 4;
			int userid = 42;
			//String queryday = DateUtil.currDay();
			String queryday = "2016-02-15";
			List<WxTextMsgDto> listTextMsg = wxTextMsgService.listTextMsg(accid, userid, "http", queryday, 0, 10);
			for (WxTextMsgDto l : listTextMsg)
				System.out.println(l.getTextMsg());
			int total = wxTextMsgService.getTotalTextMsg(accid, userid, "http", queryday);
			System.out.println("total:" + total);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 添加
		//@Test
		public void testAddTextMsg() {
			try {

				WxTextMsgEto msg = new WxTextMsgEto();
				msg.setAccid(1);
				msg.setUserid(1);
				msg.setTextMsg("https");
				msg.setCreateTime(111111111);
				WxTextMsgInnerEbo addAccountInfo = wxTextMsgService.addAccountInfo(msg);
				System.out.println("[WxTextMsgInnerEbo]=" + addAccountInfo);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

}
