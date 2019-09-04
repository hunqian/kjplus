package com.ykisswx.test;

import java.util.List;

import com.ykisswx.eto.WxAccountEto;
import com.ykisswx.model.WxAccountEbo;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ykisswx.dto.WxAccountDto;
import com.ykisswx.service.IWxAccountService;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(value = "classpath:beans.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestWxAccount {

	@Autowired
	private IWxAccountService wxAccountService;

	// 测试获得menu
	@Test
	public void testListAccount() {
		try {
			List<WxAccountDto> ls = wxAccountService.listAccount(0,"", null, 0, 10);
			for (WxAccountDto l : ls)
				System.out.println(l.getName());
			int total = wxAccountService.getTotalAccount(0 ,"", null);
			System.out.println("total:" + total);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 添加
	@Test
	public void testAddAccount() {
		try {

			WxAccountEto acct = new WxAccountEto();
			acct.setAppid("wx4d3042226733851333");
			acct.setOrgid(1);
			acct.setAccount("搜索使得金风科技22");
			acct.setName("手机的反馈11");
			acct.setAppsecret("94b2e38f5c8fec33fca20ef96a2821cd");
			//acct.setToken("64af74lt0e088t2h00ye74amfu17wtp5");
			//acct.setEncoaseskeyding("oJAIaPO5v7eP4F26UgdAxRmUTRN4ozYIW6qH8cqABCX");
			acct.setType("S");
			acct.setWebDomain("h5.kangjia.cn");
			WxAccountEbo addAcct = wxAccountService.addAccountInfo(acct);
			System.out.println("[WxAccountEbo]=" + addAcct);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 修改Account
	//@Test
	public void testEditAccount() {
		try {
			int accid = 1;
			String account = "123123";
			String name = "123123";
			String intro = "阿斯科利的积分卡拉斯";
			String appid = "wx9a66";
			String appsecrect = "be16ac";
			String token = "lokeme";
			String encoaseskeyding = "8IqYDB";
			String type = "A";
			String mode = "T";
			
			WxAccountEto wxAccountEto = new WxAccountEto();
			wxAccountEto.setAccount(account);
			wxAccountEto.setName(name);
			wxAccountEto.setIntroduction(intro);
			wxAccountEto.setAppid(appid);
			wxAccountEto.setAppsecret(appsecrect);
			wxAccountEto.setToken(token);
			wxAccountEto.setEncoaseskeyding(encoaseskeyding);
			wxAccountEto.setType(type);
			wxAccountEto.setMode(mode);
			
			wxAccountService.editAccAccount(accid, wxAccountEto);
			System.out.println("修改成功");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
