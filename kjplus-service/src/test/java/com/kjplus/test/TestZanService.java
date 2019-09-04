package com.kjplus.test;


import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kjplus.constant.Constant;
import com.kjplus.eto.ZanHisEto;
import com.kjplus.model.inner.ZanStatusInnerEbo;
import com.kjplus.service.IZanService;
import com.ybk.exception.DataException;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author wangyao
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(value = "classpath:testbeans.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestZanService {

	@Autowired
	private IZanService zanService;

	//@Test
	public void testAddZan() {
		try {
			ZanHisEto zan = new ZanHisEto();
			// zan.setZanId(11);
			zan.setMainId(2);
			zan.setMainType(Constant.ZAN_INFO);
			zan.setUid(1);
			zan.setIpAddr("ipAddr");
			zanService.zan(zan);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	/*@Test
	public void testListZanInfo() {
		try {
			String infoCode = null;
			String infoFlag = null;
			int uid = 0;
			String mainType = Constant.ZAN_INFO_REF;
			List<ZanInfoParentDto> zanInfos = zanService.listZanInfo(infoCode, infoFlag, uid, mainType);
			for (ZanInfoParentDto zanInfo : zanInfos)
				System.out.println(zanInfo);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}*/

	@Test
	public void testListZanInfo() {
		try {
			String mainType = Constant.ZAN_INFO_REF;
			List<Integer> mainIds = new ArrayList<Integer>();
			mainIds.add(5);
			List<ZanStatusInnerEbo> statuses = zanService.listZanHisStatus(mainIds, mainType, 1);
			for (ZanStatusInnerEbo s : statuses)
				System.out.println(s.getZhId() + "," + s.getIpAddr());
		} catch (DataException e) {
			e.printStackTrace();
		}
	}
}
