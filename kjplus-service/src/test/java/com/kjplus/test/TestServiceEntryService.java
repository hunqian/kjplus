package com.kjplus.test;

import com.kjplus.dto.ServiceEntryDto;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kjplus.eto.ServiceConfigEto;
import com.kjplus.eto.ServiceEntryEto;
import com.kjplus.model.ServiceConfigEbo;
import com.kjplus.model.ServiceEntryEbo;
import com.kjplus.model.inner.ServiceEntryInnerEbo;
import com.kjplus.service.IServiceEntryService;
import com.ybk.exception.DataException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author songyuebin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(value = "classpath:testbeans.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestServiceEntryService {

	@Autowired
	private IServiceEntryService srvEntryServie;

	// 测试添加服务主入口
	@Test
	public void testAddSrvEntry() {
		try {
			ServiceEntryEto srvEntry = new ServiceEntryEto();
			srvEntry.setSrvMemo("同时添加服务与配置");
			srvEntry.setSrvName("添加服务与配置");
			srvEntry.setOpType("NEW");
			srvEntry.setSrvType("S");
			srvEntry.setSrvTypeId(368);
			srvEntry.setOrgId(1);
			srvEntry.setAcceptorPoint(12.12);
			srvEntry.setProviderPoint(111.22);
			ServiceEntryInnerEbo entry = srvEntryServie.addSrvEntry(srvEntry);
			System.out.println("添加成功:" + entry);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 测试修改服务主入口
	//@Test
	public void testEditSrvEntry() {
		try {
			ServiceEntryEbo srvEntry = new ServiceEntryEbo();
			srvEntry.setId(12);;
			srvEntry.setSrvCode("87417889");
			srvEntry.setPid(6);
			srvEntry.setSrvMemo("12121这是一个测试");
			srvEntry.setSrvName("2222预约接种");
			srvEntry.setSrvType("S");
			srvEntry.setSrvTypeId(1069);
			srvEntry.setFlag("N");
			
			srvEntryServie.updateSrvEntry(srvEntry);
			System.out.println("修改成功");
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 测试添加配置或修改配置积分
	//@Test
	public void testAddSrvConfig() {
		try {
			ServiceConfigEto srvConfig = new ServiceConfigEto();
			srvConfig.setAcceptorPoint(6.2);
			srvConfig.setProviderPoint(10.2);
			srvConfig.setOrgId(0);
			srvConfig.setSrvId(12);
			ServiceConfigEbo conf = srvEntryServie.addSrvConfig(srvConfig);
			System.out.println(conf);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 测试获取总的服务列表
	@Test
	public void testListServiceEntry() {
		try {
			int orgId = 1;
			String type = null;
			String opType = null;
			String flag = null;
			String name = null;
			boolean treeStyle = false;
			boolean childNearStyle = true;
			List<Integer> typeIds = new ArrayList<Integer>();
			typeIds.add(1141);
			typeIds.add(1142);
			List<ServiceEntryDto> mains = srvEntryServie.listServiceEntry(orgId, typeIds, type, opType, name, flag, 0,
					100, treeStyle, childNearStyle);
			int total = srvEntryServie.getTotalEntry(orgId, typeIds, type, opType, name, flag);
			for (ServiceEntryDto mn : mains) {
				System.out.println(mn.getId() + "," + mn.getSrvName());
				List<ServiceEntryDto> mSubs = mn.getSubs();
				for (ServiceEntryDto n : mSubs) {
					System.out.println("[子]" + n.getId() + "," + n.getSrvName());
				}
			}
			System.out.println(total);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

}
