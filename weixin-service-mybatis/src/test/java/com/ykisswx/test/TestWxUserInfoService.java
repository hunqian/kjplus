package com.ykisswx.test;

import com.ykisswx.dto.WxUserInfoNnDto;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ybk.exception.DataException;
import com.ykisswx.service.IWxUserInfoService;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(value = "classpath:beans.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestWxUserInfoService {

	@Autowired
	private IWxUserInfoService wxUserInfoService;

	//@Test
	public void testGetAccid() {
		int wxUserid = 42;
		try {
			int accid = wxUserInfoService.getAccid(wxUserid);
			System.out.println(accid);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testListUserByOrg() {
		try {
			String nickname=null;
			Integer orgid=0;
			Integer page=1;
			Integer paging=10;
			List<WxUserInfoNnDto> infos= wxUserInfoService.listUserByOrg(nickname, orgid,  page,  paging);
			int total=wxUserInfoService.getTotal(nickname,orgid);
			System.out.println(total);
			for (WxUserInfoNnDto info : infos) {
				System.out.println(info);
			}
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

}
