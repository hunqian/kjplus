package com.kjplus.test;

import com.kjplus.dao.IServiceLogDao;
import com.kjplus.eto.ServiceLogEto;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.ybk.basic.util.DateUtils;

import com.kjplus.model.ServiceLogEbo;
import com.kjplus.service.IServiceLogService;
import com.ybk.exception.DataException;

import java.util.List;

/**
 * @author songyuebin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(value = "classpath:testbeans.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestServiceLogService {

	@Autowired
	private IServiceLogDao serviceLogDao;
	@Autowired
	private IServiceLogService srvLogService;
	
	// 测试添加服务日志
	@Test
	public void testAddSrvLog() {
		try {
			ServiceLogEto srvLog = new ServiceLogEto();
			srvLog.setSrvId(11);
			srvLog.setPrsnId(19);
			srvLog.setProviderId(17);
			srvLog.setFlag("Y");
			srvLog.setCreateTime(DateUtils.getCurTimeInSec());
			ServiceLogEbo srv =srvLogService.addServiceLog(srvLog);
			System.out.println("添加成功:" + srv);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetSrvLog() {
		try {
			int id = 1;
			String code = "11";
			ServiceLogEbo srvLog = srvLogService.getServiceLogEboByIdOrCode(id, code);
			System.out.println("获取成功:" + srvLog);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testlistSrvLog() {
		try {
			int srvId = 1;
			int providerId = 0;
			int prsnId = 0;
			String flag = "Y";
			int startTime = 0;
			int endTime = 0;
			int page = 0;
			int paging =1;
			List<ServiceLogEbo> listSrvLog = srvLogService.listServiceLogEbo(srvId, providerId, prsnId, flag,
					startTime, endTime,page,paging);
			int total = serviceLogDao.getTotalServiceLog(srvId, providerId, prsnId, flag, startTime, endTime);
			for (ServiceLogEbo srv : listSrvLog) {
				System.out.println("获取成功:" + srv);
			}	
			System.out.println(total);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateSrvLog() {
		try {
			int id = 1;
			String code = "11";
			String flag = "Y";
			serviceLogDao.updateServiceLog(id, code, flag);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}	
}
