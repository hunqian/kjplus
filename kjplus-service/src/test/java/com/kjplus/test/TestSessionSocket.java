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
import com.kjplus.eto.SessionSocketEto;
import com.kjplus.model.SessionSocketEbo;
import com.kjplus.service.ISessionSocketService;
import com.kjplus.util.MStringUtil;
import com.ybk.exception.DataException;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(value = "classpath:testbeans.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestSessionSocket {

	@Autowired
	private ISessionSocketService sessSocketService;

	// 添加
	@Test
	public void testAddSessionSocket() {
		try {
			SessionSocketEto sess = new SessionSocketEto();
			sess.setSessionCode(MStringUtil.getUUID());
			sess.setUid(10);
			sess.setOrgId(1);
			sessSocketService.addSessionSocket(sess);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 修改
	@Test
	public void testUpdateSessionStatus() {
		try {
			String sessionCode = "84a422514ed24d3a9aa81329d8322be7";
			String status = Constant.FLAG_NO;
			sessSocketService.updateSessionStatus(sessionCode, status);

		} catch (DataException e) {

			e.printStackTrace();
		}
	}

	// 列表
	@Test
	public void testListSessionByCod() {
		try {

			String sessionCode = "84a422514ed24d3a9aa81329d8322be7"; // "84a422514ed24d3a9aa81329d8322be7";
			String status = Constant.FLAG_YES; // null
			Integer uid = 1;
			List<SessionSocketEbo> listSess = sessSocketService.listSessionByCod(sessionCode, status, uid);
			for (SessionSocketEbo sess : listSess)
				System.out.println(sess.getSessionCode());

		} catch (DataException e) {

			e.printStackTrace();
		}
	}

	// 测试获取 有效sessionSocket对象
	@Test
	public void testGetSessionByCode() {
		try {
			String sessionCode = "a35294a649db4bc7a4ff17f98d1d90d8";
			SessionSocketEbo sess = sessSocketService.getSessionByCode(sessionCode, Constant.FLAG_YES);
			System.out.println(sess);
		} catch (DataException e) {

			e.printStackTrace();
		}
	}

};
