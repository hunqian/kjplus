package com.kjplus.test;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kjplus.dao.IUserMapDao;
import com.kjplus.model.UserMapEbo;
import com.kjplus.service.IUserMapService;

/**
 * 
 * @author songyuebin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(value = "classpath:testbeans.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestUserMapService {

	@Autowired
	private IUserMapDao userMapDao;
	@Autowired
	private IUserMapService userMapService;

	// 测试添加userMap对象
	// @Test
	public void testAddUserMap() {
		try {
			UserMapEbo userMapEbo = new UserMapEbo();
			userMapEbo.setMainId(2);
			userMapEbo.setMainType("S");
			userMapEbo.setUid(3);
			userMapEbo.setUserType("A");
			userMapService.addUserMap(userMapEbo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 测试查询staffMap对象
	@Test
	public void testGetStaffIdByUid() {
		try {
			// 该类型必须有 A/U
			int uid = 3;
			int staffId = userMapService.getStaffIdByUid(uid);
			System.out.println("staffId = " + staffId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 测试查询staffMap对象
	@Test
	public void testgetUserIdByStaffId() {
		try {
			// 该类型必须有 A/U
			int uid = 3;
			int staffId = userMapService.getStaffIdByUid(uid);
			System.out.println("staffId = " + staffId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 测试查询staffMap对象
	@Test
	public void testGetUserIdByAdminUserId() {
		try {
			int adminId = 2;
			int uid = userMapService.getUserIdByAdminUserId(adminId);
			System.out.println("uid = " + uid);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * // 测试查询userMap对象
	 * 
	 * @Test public void testListStaffMap() { try { int mainId = 1; String
	 * mainType = null; List<UserMapInnerEbo> listUserMap =
	 * userMapService.listUserMap(mainId,mainType,0,null); for (UserMapInnerEbo
	 * userMapEbo : listUserMap) { System.out.println(userMapEbo.getUid() +
	 * ","+userMapEbo.getMainType()); } } catch (Exception e) {
	 * e.printStackTrace(); } }
	 */
}
