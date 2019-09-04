package com.kjplus.test;

import java.util.List;

import com.kjplus.dao.IServerRepoDao;
import com.kjplus.eto.ServerRepoEto;
import com.kjplus.model.ServerRepoEbo;
import com.kjplus.service.IServerRepoService;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(value = "classpath:testbeans.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestServerRepo {

	@Autowired
	private IServerRepoService  serverService;
	@Autowired
	private IServerRepoDao  SrvRepoDao;

	@Test
	public void testAddSrvRepoEbo() {
		ServerRepoEto srvRepoEto = new ServerRepoEto();
		String name = "12111";
		srvRepoEto.setSrvName(name);
		String ipAddr = "0:0:0:0:0";
		srvRepoEto.setIpAddr(ipAddr);
		String flag = "Y";
		srvRepoEto.setFlag(flag);	
		try {
			 ServerRepoEbo srvRepoEbo =  serverService.addSrvRepoEbo(srvRepoEto);
			System.out.println("添加服务器成功：ID= "+srvRepoEbo.getId());
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
	@Test
	public void testGetSrvRepoEbo() {
		int id = 1;
		String code = "";
		try {
			ServerRepoEbo srvRepoEbo = serverService.getSrvRepoByIdOrCode(id, code);
			System.out.println(srvRepoEbo);
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
	
	@Test
	public void testlistSrvRepoEboInner() {
		String name = "11";
		try {
			List<ServerRepoEbo> listSrvRepoEbo = serverService.listSrvRepo(name);
			for (ServerRepoEbo serverRepoEbo : listSrvRepoEbo) {
				System.out.println(serverRepoEbo);
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
	
}
