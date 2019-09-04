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

import com.kjplus.dto.SysRegionsDto;
import com.kjplus.service.ISysRegionsService;
import com.ybk.exception.DataException;

/**
 * 
 * @author niuzhiwei
 */
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(value = "classpath:testbeans.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestSysService {

	@Autowired
	private ISysRegionsService regionsService;

	// 添加预约活动
	@Test
	public void testListRegions() {
		try {
			List<SysRegionsDto> regions = regionsService.listRegions(4);
			for(SysRegionsDto r: regions){
				System.out.println("[r]" + r.getLocalName());
			}
		} catch (DataException e) {
			e.printStackTrace();
		}
	}
}
