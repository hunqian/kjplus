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
import com.kjplus.dto.UserPersonDto;
import com.kjplus.service.IUserService;
import com.ybk.exception.DataException;

/**
 * 
 * @author niuzhiwei
 */
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(value = "classpath:testbeans.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestRedisUserService {

	@Autowired
	private IUserService userService;

	@Test
	public void testFindtypeByCode() {
		try {

			List<UserPersonDto> userPsersons = userService.listUserPerson(0,0,0,Constant.FLAG_YES,"");
			for (UserPersonDto userPserson : userPsersons)
				System.out.println(userPserson);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

}
