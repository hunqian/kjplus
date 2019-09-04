package com.kjplus.test;

import com.kjplus.constant.Constant;
import com.kjplus.eto.ServicePackageEto;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kjplus.model.ServicePackageEbo;
import com.kjplus.model.inner.ServicePackageInnerEbo;
import com.kjplus.service.IServicePackageService;
import com.ybk.exception.DataException;

import java.util.List;

/**
 * @author songyuebin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(value = "classpath:testbeans.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestServicePackageService {

	@Autowired
	private IServicePackageService srvPackageService;
	
	@Test
	public void testAddSrvPackage() {
		try {
			ServicePackageEto srvPackage = new ServicePackageEto();
			srvPackage.setName("康");
			srvPackage.setMemo("测");
			srvPackage.setOrgId(1);
			srvPackage.setPeriodVal(5);
			srvPackage.setSrvPrice(500.0);
			srvPackage.setIsDefault("Y");
			ServicePackageEbo srv = srvPackageService.addSrvPackage(srvPackage);
			System.out.println("组织服务目录添加成功：" + srv);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	//@Test
	public void testGetSrvPackage() {
		try {
			int id = 9;
			String code = "111111111";
			ServicePackageEbo srvPackage = srvPackageService.getSrvPackageByIdOrCode(id, code);
			System.out.println(srvPackage);
			ServicePackageEbo srvPackage2 = srvPackageService.getSrvPackageByIsDefault(1);
			System.out.println(srvPackage2);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testListSrvPackageInner() {
		try {
		
			String name = "A";
			int orgId = 1;
			String status = "Y";
			String isDefault = "N";
			String periodCode = "PERIOD_TYPE_MONTHS";
			int periodVal = 0;
			String startDay = "2017-12-12";
			String finishDay = "2018-13-12";
			int page = 0;
			int paging = 10;
			List<ServicePackageInnerEbo> listSrvPackage = srvPackageService.listSrvPackageInner(name, orgId, status,
					isDefault, periodCode, periodVal,startDay,finishDay, page, paging);
			for (ServicePackageInnerEbo srv : listSrvPackage) {
				System.out.println(srv);
			}
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateSrvPackageIsDefault() {
		try {
			int id = 8;
			String code = "94317278";
			srvPackageService.updateSrvPackageIsDefault(id, code);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateSrvPackage() {
		try {
			ServicePackageEbo srvPackage = new ServicePackageEbo();
			srvPackage.setId(9);
			srvPackage.setCode("111111111");
			srvPackage.setIsDefault(Constant.FLAG_NO);
			srvPackage.setName("2121");
			srvPackage.setMemo("2121测试的服务");
			srvPackage.setOrgId(2);
			srvPackage.setPeriodTypeId(211);
			srvPackage.setPeriodVal(12);
			srvPackage.setSrvPrice(5220.0);
			srvPackage.setStatus("N");
			srvPackageService.updateSrvPackage(srvPackage);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}
}
