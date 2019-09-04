package com.kjplus.test;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kjplus.dao.IAdminUserMapDao;
import com.kjplus.model.AdminUserMapEbo;

/**
 * 
 * @author songyuebin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(value = "classpath:testbeans.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestAdminUserMapService {

	@Autowired
	private IAdminUserMapDao adminUserMapDao;

	// 测试添加userMap对象
	@Test
	public void testAddUserMap() {
		try {
			AdminUserMapEbo adminUserMapEbo = new AdminUserMapEbo();
			adminUserMapEbo.setStaffId(2);
			adminUserMapEbo.setUid(3);
			adminUserMapEbo.setUserType("A");
			adminUserMapDao.addUserMap(adminUserMapEbo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 测试查询userMap对象
	@Test
	public void testGetUserMapByIdOrUid() {
		try {
			// 该类型必须有 A/U
			String userType = "A";
			int uid = 1;
			AdminUserMapEbo adminUserMapEbo = adminUserMapDao.getUserMap(0, uid, userType);
			System.out.println("++" + adminUserMapEbo.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
