package com.kjplus.test;


import com.kjplus.service.IAppKeyService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.ybk.exception.DataException;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(value = "classpath:testbeans.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestAppKey {

	@Autowired
	private IAppKeyService  appKeyService;

	//增加openid
	@Test
	public void testAddKey() {
		
		String openid = "13611263262";
		//30秒
		int beatTimeInSec = 30;
		try {
			boolean res = appKeyService.checkOpenid(openid, beatTimeInSec, true);
			System.out.println("[checkOpenid]=" + res);
			if(!res)
				appKeyService.addKey(openid,1,1);
		} catch (DataException e) {
			try{
				appKeyService.addKey(openid,1,1);
			}catch(DataException e2){
				e2.printStackTrace();
			}

		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
	
	//失效
	@Test
	public void testInvalidate() {
			
		String token = "UMrREkGFcmeJHVlGJHJ2JfLvKZsp2lPU";
		try {
			appKeyService.invalidKeyByToken(token);
		}catch (DataException e) {
		}
	}
		
	//检验token
	@Test
	public void testCheckToken() {
		
		String token = "reOgnZ1sx0z9nXK3piqpHSH299LPMiZM";
		//30秒
		int beatTimeInSec = 30;
		boolean autoReg = true;
		try {
			boolean res = appKeyService.checkToken(token, beatTimeInSec, autoReg);
			if(!res)
				appKeyService.addKey(appKeyService.getKey(token).getOpenid(),1,1);
			System.out.println("[checkOpenid]=" + res);
		} catch (DataException e) {
			e.printStackTrace();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}	
}
