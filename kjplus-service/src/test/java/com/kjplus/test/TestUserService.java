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
import com.kjplus.dto.UserDto;
import com.kjplus.dto.UserPersonDto;
import com.kjplus.eto.UserEto;
import com.kjplus.model.UserEbo;
import com.kjplus.service.IUserService;
import com.ybk.exception.DataException;

/**
 * @author niuzhiwei
 */
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(value = "classpath:testbeans.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestUserService {

	@Autowired
	private IUserService userService;

	// private static Logger logger = Logger.getLogger(TestUserService.class);

	// 测试添加
	@Test
	public void testAddUser() {
		try {
			UserEto user = new UserEto();
			user.setNickName("测试账户");
			user.setPassWord("123456");
			user.setOrgId(1);
			user.setMobileFlag(Constant.FLAG_NO);
			userService.addUser(user);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 测试login
	// @Test
	public void testLoginUser() {
		try {
			String userName = "T5720078g937p9y4x";
			String passWord = "1234156";
			UserEbo u = userService.login(userName, passWord);
			if (u != null)
				System.out.println("[loginSuccess]" + u.getUid() + "," + u.getOrgName());
			else
				System.out.println("[loginFail]");
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 测试列表
	@Test
	public void testFindtypeByCode() {
		try {
			List<UserPersonDto> userPsersons = userService.listUserPerson(0, 0, 0, Constant.FLAG_YES, "");
			for (UserPersonDto userPserson : userPsersons)
				System.out.println(userPserson);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 测试获取用户列表
	@Test
	public void testListAdminUser() {
		try {
			int uid = 0;
			String nickName = "";
			String userType = "";
			String status = "";
			String mobileNum = "";
			int orgid = 0;
			int page = 0;
			int paging = 10;

			// 测试获得用户
			UserEbo u1 = userService.getUserById(1);
			System.out.println(u1.getNickName());

			List<UserDto> listUser = userService.listUser(uid, nickName, orgid, status, mobileNum, userType, page, paging);
			for (UserDto userEbo : listUser) {
				System.out.println(userEbo.getUid());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 测试查询list管理员对象
	// @Test
	public void getTotalUser() {
		try {
			int uid = 0;
			String nickName = "";
			String userType = Constant.ADMIN_USER_TYPE_ADMIN;
			String status = "";
			String mobileNum = "";
			int orgid = 0;
			int totalUser = userService.getTotalUser(uid, nickName, orgid, status, mobileNum, userType);
			System.out.println("totalUser:" + totalUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 测试查询list管理员对象
	@Test
	public void testGetSelfPrsnIdByUserId() {
		try {
			int uid = 1;
			int prsnId = userService.getSelfPrsnIdByUserId(uid);
			System.out.println("prsnId:" + prsnId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
