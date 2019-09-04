package com.kjplus.test;

import java.util.List;

import com.kjplus.dao.IServiceSumDao;
import com.kjplus.dto.ServiceSumDto;
import com.kjplus.eto.ServiceListEto;
import com.kjplus.eto.ServiceSumEto;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kjplus.model.ServiceListEbo;
import com.kjplus.model.ServiceSumEbo;
import com.kjplus.service.IServiceSumService;
import com.ybk.exception.DataException;


/**
 * @author songyuebin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(value = "classpath:testbeans.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestServiceSumService {

	@Autowired
	private IServiceSumDao serviceSumDao;
	@Autowired
	private IServiceSumService serviceSumService;

	@Test
	public void testAddSrvSum() {
		try {
			ServiceSumEto sum = new ServiceSumEto();
			sum.setBodyId(1);
			sum.setBodyType("U");
			sum.setOrgId(1);
			sum.setTotalConsumePoint(0.0);
			sum.setTotalEarnPoint(0.0);
			sum.setTotalPoint(0.0);
			ServiceSumEbo s=serviceSumService.addSrvSum(sum);
			System.out.println(s);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddSrvlist() {
		try {
			ServiceListEto li = new ServiceListEto();
			li.setOpId(18);
			li.setMainId(1);
			li.setMainType("P");
			li.setServicePoint(0.0);
			li.setServiceStatus("I");
			li.setOperName("1,张三");
			li.setMemo("增加");
			li.setSumId(1);
			ServiceListEbo l = serviceSumService.addSrvList(li);
			System.out.println(l);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}


	@Test
	public void testgetSrvSum() {
		try {
			int id =1;
			ServiceSumEbo sum1 = serviceSumDao.getSrvSumById(id);
			System.out.println(sum1);
			int bodyId = 1;
			String bodyType = "U";
			ServiceSumEbo sum2 = serviceSumService.getSrvSumByBody(bodyId, bodyType);
			System.out.println(sum2);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testListSrvSumDto() {
		try {
			int orgId =1;
			String bodyType = "U";
			int page = 0;
			int paging = 10;
			List<ServiceSumDto> listSum = serviceSumService.listSrvSumDto(orgId, bodyType,page,paging);
			for (ServiceSumDto serviceSumEbo : listSum) {
				System.out.println(serviceSumEbo);
			}
		} catch (DataException e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	public void testgetSrvList() {
		try {
			int id =1;
			ServiceListEbo sum1 = serviceSumService.getSrvListById(id);
			System.out.println(sum1);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testlistSrvList() {
		try {
			int sumId =1;
			int mainId =0;
			String mainType = "";
			int startTime =0;
			int endTime =0;
			String status = "";
			int page =0;
			int paging =10;
			List<ServiceListEbo> listList = serviceSumService.listSrvList(sumId, mainId, mainType, startTime, endTime, status, page, paging);
			for (ServiceListEbo serviceListEbo : listList) {
				System.out.println(serviceListEbo);
			}
		} catch (DataException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateSrvSum() {
		try {
			int id =1;
			double totalEarnPoint =1;
			double totalConsumePoint = 33;
			double totalPoint = 22;
			serviceSumDao.updateServiceSumPoint(id, totalEarnPoint, totalConsumePoint, totalPoint);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}
	
	
}
