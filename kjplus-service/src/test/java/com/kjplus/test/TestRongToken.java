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

import com.kjplus.dto.RongTokenDto;
import com.kjplus.model.RongTokenEbo;
import com.kjplus.service.IRongTokenService;
import com.ybk.exception.DataException;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(value = "classpath:testbeans.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestRongToken {

	@Autowired
	private IRongTokenService rongTokenService;

	// 测试为指定用户添加rongToken
	@Test
	public void testAddRongToken() {
		try {
			int uid = 6;
			int reqTime = 1514272993;
			String flag = "Y";
			String isBlock = "N";
			String isBlack = "N";
			RongTokenEbo rong = new RongTokenEbo();
			rong.setUid(uid);
			rong.setReqTime(reqTime);
			rong.setFlag(flag);
			rong.setIsBlock(isBlock);
			rong.setIsBlack(isBlack);
			rongTokenService.addRongToken(rong);
			System.out.println("添加成功");
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 测试根据uid获取指定用户的rongToken
	// @Test
	public void testGetRongTokenByUid() {
		try {
			int uid = 1;
			RongTokenDto rongTokenByUid = rongTokenService.getRongTokenByUid(uid);
			System.out.println("uid:" + rongTokenByUid.getUid());
			System.out.println("token:" + rongTokenByUid.getrToken());
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 测试根据token获取指定用户的rongToken
	// @Test
	public void testGetRongTokenByToken() {
		try {
			String rongToken = "sdfsdfsdfs";
			RongTokenDto rongTokenByToken = rongTokenService.getRongTokenByToken(rongToken);
			System.out.println("uid:" + rongTokenByToken.getUid());
			System.out.println("token:" + rongTokenByToken.getrToken());
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 测试查询rongToken列表
	@Test
	public void testListInfo() {
		try {
			int uid = 0;
			List<RongTokenDto> listRongToken = rongTokenService.listRongToken(uid);
			for (RongTokenDto rongToken : listRongToken) {
				System.out.println("[uid:] " + rongToken.getUid());
				System.out.println("\t[token] " + rongToken.getrToken());
			}
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 修改信息列表
	@Test
	public void testUpdateInfo() {
		try {
			int uid = 3;
			String rToken = "uuuuu";
			int reqTime = 1514274246;
			String flag = "N";
			String isBlock = "Y";
			String isBlack = "Y";
			RongTokenEbo rongTokenEbo = new RongTokenEbo();
			rongTokenEbo.setUid(uid);
			rongTokenEbo.setrToken(rToken);
			rongTokenEbo.setReqTime(reqTime);
			rongTokenEbo.setFlag(flag);
			rongTokenEbo.setIsBlack(isBlack);
			rongTokenEbo.setIsBlock(isBlock);
			rongTokenService.updateRongToken(rongTokenEbo);
			System.out.println("[update] " + "修改成功");
		} catch (DataException e) {
			e.printStackTrace();
		}
	}
}
