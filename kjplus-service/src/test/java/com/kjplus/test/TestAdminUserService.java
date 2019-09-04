package com.kjplus.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kjplus.constant.Constant;
import com.kjplus.dto.AdminUserDto;
import com.kjplus.eto.AdminUserEto;
import com.kjplus.model.AdminUserEbo;
import com.kjplus.service.IAdminUserService;

/**
 * 
 * @author songyuebin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(value = "classpath:testbeans.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestAdminUserService {

	private static Logger logger = Logger.getLogger(TestAdminUserService.class);

	@Autowired
	private IAdminUserService adminUserService;

	// 测试添加管理员对象
	//@Test
	public void testRegister() {
		try {
			AdminUserEto adminUserEto = new AdminUserEto();
			adminUserEto.setUserName("测试添加admin3");
			adminUserEto.setPassWord("123456");
			adminUserEto.setMobileNum("18210107723");
			adminUserEto.setNickName("测试添加");
			adminUserEto.setOrgId(1);
			adminUserService.register(adminUserEto);
			System.out.println("++++");
			logger.error(adminUserEto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 测试查询list管理员对象
		@Test
		public void testListAdminUser() {
			try {
				String userName = "jianglb";
				String nickName = "jianglb";
				String userType = Constant.ADMIN_USER_TYPE_GNRL;
				String status = "Y";
				int page = 0;
				int paging = 10;
				int orgid = 1;
				List<AdminUserDto> listAdminUser = adminUserService.listUser(
						userName, nickName, userType, status, orgid, page, paging);
				for(AdminUserDto adminUserEbo:listAdminUser){
					System.out.println(adminUserEbo.getUserName());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 测试查询list管理员对象
			@Test
			public void getTotalUser() {
				try {
					String userName = "";
					String userType = Constant.ADMIN_USER_TYPE_GNRL;
					String status = "";
/*					int page = 0;
					int paging = 10;
*/					int totalUser = adminUserService.getTotalUser(userName, null, userType, status, -1);
						System.out.println("totalUser:"+totalUser);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// 测试查询list管理员对象
	@Test
	public void getUpdateAdminUser() {
		try {
			String userName = "syb";
			String passWord = "";
			String email = "";
			String mobileNum = "12458741111";
			String status = "Y";
			String face = "";
			String nickName = "aaaaa";
			AdminUserEbo admin = new AdminUserEbo();
			admin.setUserName(userName);
			admin.setPassWord(passWord);
			admin.setEmail(email);
			admin.setMobileNum(mobileNum);
			admin.setStatus(status);
			admin.setFace(face);
			admin.setNickName(nickName);
			adminUserService.updateAdminUser(admin);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}	
	
	@Test
	public void getAdminUser() {
		try {
			int uid = 16;
			AdminUserEbo admin = adminUserService.getUserByUid(uid);
			System.out.println(admin.getUserName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}	
			
}
