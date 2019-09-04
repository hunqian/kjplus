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
import org.ybk.basic.util.DateUtil;

import com.kjplus.dao.IDeviceParamDao;
import com.kjplus.eto.DvcParamEto;
import com.kjplus.model.DeviceParamEbo;
import com.kjplus.service.IDeviceParamService;

/**
 * 
 * @author songyuebin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(value = "classpath:testbeans.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestDvcParamService {

	@Autowired
	private IDeviceParamService dvcParamService;
	
	@Autowired
	private IDeviceParamDao dvcParamDao;

	
	//@Test
	public void testGetDvcParamByIdOrDvcP() {
		try {
			int id = 0;
			String dvcP = "2:2:12";
			DeviceParamEbo  dvcParam = dvcParamService.getDeviceParam(id, dvcP);
			System.out.println(dvcParam);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testListDvcParam() {
		try {
			String dvcParam = "";
			String dvcType = "M";
			String paramType = "";
			String flag = "";
			List<DeviceParamEbo> dvcParamList = dvcParamService.listDeviceParamList(dvcType, paramType, dvcParam, flag,0,10);
			int total = dvcParamService.getTotalDeviceParam(dvcType, paramType, dvcParam, flag);
			System.out.println("total"+total);
			for(DeviceParamEbo dvc : dvcParamList ){
				System.out.println(dvc.getDeviceParam());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddDvcParam() {
		try {
			String dvcParam = "6:2:12";
			String deviceType = "P";
			String paramType = "SN";
			String flag = "Y";
			DvcParamEto dvcParamEto = new DvcParamEto();
			dvcParamEto.setDeviceParam(dvcParam);
			dvcParamEto.setDeviceType(deviceType);
			dvcParamEto.setParamType(paramType);
			dvcParamEto.setFlag(flag);
			dvcParamEto.setCreateTime(DateUtil.getCurTimeInSec());
			DeviceParamEbo dvcParamEbo = dvcParamService.addDeviceParam(dvcParamEto);
			System.out.println("添加成功"+dvcParamEbo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
