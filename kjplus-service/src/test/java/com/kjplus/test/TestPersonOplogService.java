package com.kjplus.test;

import java.util.ArrayList;
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
import com.kjplus.eto.PersonOplogEto;
import com.kjplus.model.PersonOplogEbo;
import com.kjplus.model.inner.PersonOplogInnerEbo;
import com.kjplus.service.IPersonOplogService;
import com.kjplus.service.impl.PersonOplogService;

/**
 * 
 * @author songyuebin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(value = "classpath:testbeans.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestPersonOplogService {

	@Autowired
	private IPersonOplogService personOplogService;

	// 增加档案操作
	@Test
	public void testAddPrsnOplog() {

		try {
			PersonOplogEto prsnOplog = new PersonOplogEto();
			prsnOplog.setPrsnId(19);
			prsnOplog.setUid(16);
			personOplogService.addPrsnOplog(prsnOplog);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testgetPrsnOplog() {

		try {
			PersonOplogEbo prsn = personOplogService.getPrsnOplogById(1);
			System.out.println(prsn);
			PersonOplogService prsn2 = new PersonOplogService();
			Class<?>[] inters = prsn2.getClass().getInterfaces();
			for (Class<?> an : inters) {
				System.out.println("==================="+an.getName());
				System.out.println("==================="+an.getSimpleName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testListPrsnOplog() {

		try {
			int opTypeId = 0;
			int uid = 0;
			int orgid = 0;
			int startTime = 0;
			int endTime = 0;
			int page = 0;
			int paging = 0;
			List<Integer> prnsIds = new ArrayList<Integer>();
			List<PersonOplogInnerEbo> prsns = personOplogService.listPrsnOplog(prnsIds, opTypeId, uid, orgid, Constant.FLAG_YES, startTime, endTime,false, page,
					paging);
			for (PersonOplogInnerEbo prsn : prsns) {
				System.out.println(prsn);
			}
			int total = personOplogService.getTotaPrsnOplog(prnsIds, opTypeId, uid, orgid, Constant.FLAG_YES, startTime, endTime,false);
			System.out.println(total);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
