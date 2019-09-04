package com.kjplus.test;

import java.util.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.ybk.basic.util.DateUtils;

import com.kjplus.constant.Constant;
import com.kjplus.eto.AppointEto;
import com.kjplus.model.AppointEbo;
import com.kjplus.model.inner.AppointInfoInnerEbo;
import com.kjplus.service.IAppointService;
import com.ybk.exception.DataException;

/**
 * 
 * @author songyuebin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(value = "classpath:testbeans.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestAppiontService {

	@Autowired
	private IAppointService appiontService;

	@Test
	public void testGetAppiointByIdOrCode() {
		try {
			int id = 71;
			String code = "32815219080222074424419804070768";
			AppointEbo appointEbo = appiontService.getAppointByIdOrCode(id, code);
			System.out.println(appointEbo);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 添加预约活动
	@Test
	public void testAddAppoit() {
		try {
			AppointEto appoint = new AppointEto();
			// 预约具体 不存在 代表主动预约
			appoint.setCalInfoId(121);
			appoint.setPrsnId(19);
			// 被预约人
			appoint.setMainId(9);
			appoint.setMainType(Constant.APPIONT_TYPE_STAFF);
			appoint.setOrgId(1);
			appoint.setAppTypeId(1141);
			// 主动预约时 有些数据不可空
			appoint.setAppMemo("添加预约测试");
			appoint.setAppointTime(DateUtils.getCurTimeInSec());
			AppointEbo appointEbo = appiontService.addAppoint(appoint);
			System.out.println(appointEbo);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testListAppiontInfo() {
		try {
			int calId = 0;
			int prsnId = 0;
			// List<Integer> prsnIds = new ArrayList<Integer>();
			int orgId = 1;
			int mainId = 0;
			String mainType = "";
			// TODO 用途暂定
			int startTime = 0;
			int endTime = 0;
			int page = 0;
			int paging = 10;
			int typeId = 0;
			int appTypeId = 1141;
			List<AppointInfoInnerEbo> listAppoint = appiontService.listAppointInfo(calId, prsnId, orgId, mainId,
					mainType, null, startTime, endTime, null, typeId,appTypeId, page, paging);
			for (AppointInfoInnerEbo appointInfoInnerEbo : listAppoint)
				System.out.println("预约列表：" + appointInfoInnerEbo);
			int total = appiontService.getTotalAppoint(calId, prsnId, orgId, mainId, mainType, null, startTime,
					endTime, null, typeId,appTypeId);
			System.out.println(total);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateAppiontStatus() {
		try {
			int id = 0;
			String code = "88333092547553665925020709419874";
			String status = "R";
			appiontService.updateAppointStatus(id, code, status, null);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// @Test
	public void testUpdateAppiontEbo() {
		try {
			AppointEbo appointEbo = new AppointEbo();
			int id = 71;
			appointEbo.setId(id);
			String code = "32815219080222074424419804070768";
			appointEbo.setCode(code);
			Integer calId = 143;
			appointEbo.setCalInfoId(calId);
			Integer prsnId = 21;
			appointEbo.setPrsnId(prsnId);
			Integer orgId = 1;
			appointEbo.setOrgId(orgId);
			Integer mainId = 9;
			appointEbo.setMainId(mainId);
			String mainType = "STAFF";
			appointEbo.setMainType(mainType);
			String status = "C";
			appointEbo.setStatus(status);
			String appMemo = "修改基本信息111";
			appointEbo.setAppMemo(appMemo);
			appiontService.updateAppointEbo(appointEbo);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

}
