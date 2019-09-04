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

import com.ykisswx.eto.WxMsgtmplUseEto;
import com.ykisswx.model.WxMsgtmplContentEbo;
import com.ykisswx.model.WxMsgtmplEbo;
import com.ykisswx.model.WxMsgtmplLibEbo;
import com.ykisswx.model.WxMsgtmplUseEbo;
import com.ykisswx.model.inner.WxMsgtmplUseInnerEbo;
import com.ykisswx.service.IWxMsgTmplService;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(value = "classpath:beans.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestWxMsgTmplService {

	@Autowired
	private IWxMsgTmplService wxMsgTmplService;

	//@Test
	public void testGetTemplByCode() {
		try {
			String tmplCode = "";
			String tmplTitle = "通知";
			List<WxMsgtmplEbo> msgtmplList = wxMsgTmplService.listTmpl(tmplCode, tmplTitle, 0, 10);
			for (WxMsgtmplEbo ms : msgtmplList)
				System.out.println(ms.getTitle());
			int total = wxMsgTmplService.getTotalTmpl(tmplCode, tmplTitle);
			System.out.println("total:" + total);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 测试获得temp内容
	//@Test
	public void testGetTempLib() {
		try {
			String tmplCode = "";
			String tmplTitle = "支付";
			WxMsgtmplLibEbo ls = wxMsgTmplService.getTmpLib(0, tmplCode, tmplTitle);
			System.out.println(ls.getTitle());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 测试获得temp内容
	//@Test
	public void testGetTempContent() {
		try {
			int id = -1;
			String tmplCode = "TM00";
			WxMsgtmplContentEbo cs = wxMsgTmplService.getTmpLContent(id, tmplCode);
			System.out.println(cs.getCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 测试添加修改temp内容
	//@Test
	public void testAddTemplateContent() {
		try {
			WxMsgtmplContentEbo MsgtmplContentEbo = new WxMsgtmplContentEbo();
			MsgtmplContentEbo.setCode("2121211");
			MsgtmplContentEbo.setContent("aaaa");
			MsgtmplContentEbo.setDemoSample("aaaaa");
			wxMsgTmplService.addTemplContent(MsgtmplContentEbo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 测试添加标题
	//@Test
	public void testAddTemplateLib() {
		try {
			WxMsgtmplLibEbo msgtmplLibEbo = new WxMsgtmplLibEbo();
			msgtmplLibEbo.setCode("TM00005");
			msgtmplLibEbo.setTitle("测试修改标题2");
			msgtmplLibEbo.setMainIndustry("互联网");
			msgtmplLibEbo.setSubIndustry("电子商务");
			wxMsgTmplService.addTemplLib(msgtmplLibEbo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//@Test
	public void testListTmplLog() {
		try {
			int accid = 4;
			int toUserid = 3;
			String tmplCode = "";
			int page = 0;
			int paging = 10;
			wxMsgTmplService.listTmplLog(accid, toUserid, tmplCode, page, paging);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testListTmplUse() {
		try {
			String tmplCode = "TM00528";
			int page = 0;
			int paging = 10;
			List<WxMsgtmplUseInnerEbo> listTmpl = wxMsgTmplService.listTmplUse(0, tmplCode, "Y", page, paging);
			int count = wxMsgTmplService.getTotalTmplUse(0, tmplCode, "Y");
			for (WxMsgtmplUseInnerEbo m : listTmpl) {
				System.out.println(m.getAccId()+","+m.getAccount()+","+m.getTitle()
						+","+m.getContent());
			}
			System.out.println(count);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//@Test
	public void testAddTmplUse() {
		try {
			int accId = 8;
			int tmplId = 2931;
			WxMsgtmplUseEto wxMsgtmplUseEto = new WxMsgtmplUseEto();
			wxMsgtmplUseEto.setAccId(accId);
			wxMsgtmplUseEto.setTmplId(tmplId);
			wxMsgTmplService.addTmplUse(wxMsgtmplUseEto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testgetTmpl() {
		try {
			int libId = 2932;
			int cntId = 0;
			String tmplCode = "";
			WxMsgtmplEbo tmpl = wxMsgTmplService.getTmpL(libId, cntId, tmplCode);
			System.out.println("tmpl="+tmpl.getTitle()+","+tmpl.getContent());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
