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

import com.kjplus.dao.IElectricMeterDao;
import com.kjplus.dto.ElectricMeterDto;
import com.kjplus.eto.ElectricMeterEto;
import com.kjplus.model.ElectricMeterEbo;
import com.kjplus.service.IElectricMeterService;

/**
 * 
 * @author songyuebin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(value = "classpath:testbeans.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestElectricMeterService {


	@Autowired
	private IElectricMeterDao electricMeterDao;
	@Autowired
	private IElectricMeterService electricMeterService;

	//@Test
	public void testAdd() {
		try {
			ElectricMeterEto el = new ElectricMeterEto();
			el.setProvince("贵州");
			el.setCity("贵阳");
			el.setArea("西区");
			el.setAddr("三道口");
			el.setLongitude("111");
			el.setLatitude("212");
			ElectricMeterEbo ele = electricMeterService.addElectricMeter(el);
			System.out.println("添加：" + ele);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testListEle() {
		try {
			String province = "贵州";
			String city = "";
			String area = "";
			String addr = "";
			List<ElectricMeterDto> list = electricMeterService.listElectricMeterDto(province, city, area, addr, 0, 10);
			for (ElectricMeterDto electricMeterEbo : list) {
				System.out.println(electricMeterEbo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
