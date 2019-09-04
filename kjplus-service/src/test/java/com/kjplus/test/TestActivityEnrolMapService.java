package com.kjplus.test;


import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.ybk.basic.util.DateUtil;

import com.kjplus.dao.IActivityEnrolDao;

import com.kjplus.model.ActivityEnrolEbo;

import com.kjplus.service.IActivityEnrolService;


/**
 * 
 * @author songyuebin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(value = "classpath:testbeans.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestActivityEnrolMapService {

	@Autowired
	private IActivityEnrolDao activityEnrolDao;
	@Autowired
	private IActivityEnrolService activityEnrolService;

	@Test
	public void testGetActEnrolEbo() {
		try {
			int id = 0;
			int uid = 1;
			String  userType = "R";
			int mainId = 1;
			String mainType = "A";
			ActivityEnrolEbo actEnrol = activityEnrolService.getActEnrolEbo(id, uid, userType, mainId, mainType);
			System.out.println(actEnrol);
		} catch (Exception e) { 
			e.printStackTrace();
		}
	}

	@Test
	public void testAddActEnrolEbo() {
		try {
			ActivityEnrolEbo activityEnrolEbo = new ActivityEnrolEbo();
			activityEnrolEbo.setUid(19);
			activityEnrolEbo.setUserType("DOC");
			activityEnrolEbo.setMainId(3);
			activityEnrolEbo.setMainType("CA");
			activityEnrolEbo.setStatus("E");
			activityEnrolEbo.setEnrolTime(DateUtil.getCurDayBeginInSec());
			activityEnrolService.addActEnrolEbo(activityEnrolEbo);
			System.out.println(activityEnrolEbo.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateActEnrolEbo() {
		try {
			int uid = 19;
			String userType = "DOC";
			int mainId = 3;
			String mainType = "CA";
			String status = "C";
			activityEnrolService.updateActEnrolEbo(0, uid, userType, mainId, mainType, status);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
