package com.kjplus.test;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.ybk.basic.util.DateUtil;

import com.kjplus.dao.IFollowupDao;
import com.kjplus.dto.FollowupMainDto;
import com.kjplus.eto.FollowupMainEto;
import com.kjplus.eto.FollowupResEto;
import com.kjplus.model.CodeRepoEbo;
import com.kjplus.model.FollowupExamEbo;
import com.kjplus.model.FollowupMainEbo;
import com.kjplus.model.FollowupRecEbo;
import com.kjplus.qto.FollowupMainQto;
import com.kjplus.service.IFollowupService;
import com.ybk.exception.DataException;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(value = "classpath:testbeans.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestFollowupService {

	@Autowired
	private IFollowupService followupService;
	@Autowired
	private IFollowupDao followupDao;

	private static Logger logger = Logger.getLogger(TestFollowupService.class);

	// 通过id，code获取 CodeRepoEbo 对象
	// @Test
	public void testGetCodeRepoByIdOrCode() {
		try {
			int id = 2;
			String code = null;
			CodeRepoEbo codeRepoEbo = followupService.getCodeRepoByIdOrCode(id, code);
			logger.info("codeRepoEbo=======================" + codeRepoEbo);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 通过id，code获取 FollowupExamEbo 对象
	// @Test
	public void testGetFollowupExamByIdOrCode() {
		try {
			int id = 1;
			String code = null;
			FollowupExamEbo followupExamEbo = followupService.getFollowupExamByIdOrCode(id, code);
			logger.info("followupExamEbo=======================" + followupExamEbo);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 通过id，code获取 FollowupRecEbo 对象
	@Test
	public void testGetFollowupRecByIdOrCode() {
		try {
			int id = 1;
			String code = null;
			FollowupRecEbo followupRecEbo = followupService.getFollowupRecByIdOrCode(id, code);
			logger.info("followupRecEbo=======================" + followupRecEbo);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 添加 FollowupMainEbo 对象
	@Test
	public void testAddFollowupMain() {
		try {
			FollowupMainEto fue = new FollowupMainEto();
			String selfCode = "";
			fue.setSelfCode(selfCode);
			int prsnId = 27;
			fue.setPrsnId(prsnId);
			int staffId = 22;
			fue.setStafffId(staffId);
			int orgId = 1122;
			fue.setOrgId(orgId);
			int tbcfgId = 1;
			fue.setTbcfgId(tbcfgId);
			String flpeMiscType = "M";// 默认非正规;
			fue.setFlpeMiscType(flpeMiscType);
			int flpeTypeId = 0;
			fue.setFlpeTypeId(flpeTypeId);
			int flpeTime = DateUtil.getCurDayBeginInSec();// 默认当前时间;
			fue.setFlpeTime(flpeTime);
			Date flpeDay = new Date();// 默认当前时间;
			fue.setFlpeDay(flpeDay);
			/*
			 * int resId = 1; fue.setResId(resId); String resMemo = "不错";
			 * fue.setResMemo(resMemo); int resStaffId = 22;
			 * fue.setResStaffId(resStaffId);
			 */
			FollowupMainEbo fm = followupService.addFollowupMain(fue);
			logger.info("添加成功=============" + fm.getId());
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 添加随访结论记录
	// @Test
	public void testAddOrModifyFollowupRes() {
		try {
			FollowupResEto fue = new FollowupResEto();
			String code = "4111895908";
			fue.setCode(code);
			int resId = 1;
			fue.setResId(resId);
			String resMemo = "不错";
			fue.setResMemo(resMemo);
			int resStaffId = 22;
			fue.setResStaffId(resStaffId);
			FollowupMainEbo fm = followupService.addOrModifyFollowupRes(fue);
			logger.info("添加或修改随访结论成功=============" + fm.getId());
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 通过id，code获取 FollowupMainEbo 对象
	@Test
	public void testGetFollowupMain() {
		try {
			int id = 0;
			String code = "11111";
			FollowupMainEbo followupMainEbo = followupDao.getFollowupMainByIdOrCode(id, code);
			logger.info("查询结果=============" + followupMainEbo);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 列表随访记录
	@Test
	public void testlistFollowupMain() {
		try {
			FollowupMainQto fuQto = new FollowupMainQto();
			/*
			 * int prsnId =27; fuQto.setPrsnId(prsnId); int staffId = 1;
			 * fuQto.setStaffId(staffId); int orgId = 1; fuQto.setOrgId(orgId);
			 * int tbcfgId = 1; fuQto.setTbcfgId(tbcfgId); String flpeMiscType =
			 * "M"; fuQto.setFlpeMiscType(flpeMiscType); int flpeTypeId = 1;
			 * fuQto.setFlpeTypeId(flpeTypeId); String startDay = "2017-12-02";
			 * fuQto.setStartDay(startDay); String finishDay = "2017-12-30";
			 * fuQto.setFinishDay(finishDay); boolean lineData = false;
			 * fuQto.setLineData(lineData);
			 */

			int page = 0;
			int paging = 20;
			// fuQto.setPrsnId(21);
			String prsnIdStr = "21,22";
			fuQto.setPrsnIdStr(prsnIdStr);
			int startTime = 1514527020;
			fuQto.setStartTime(startTime);
			int finishTime = 1514527029;
			fuQto.setFinishTime(finishTime);

			List<FollowupMainDto> listMain = followupService.listFollowupMainDto(fuQto, page, paging);
			int count = followupService.getTotalFollowupMain(fuQto);
			for (FollowupMainDto m : listMain) {
				logger.info("查询结果=============" + m);
				System.out.println(m.getOrgId());
			}
			logger.info("查询随访总数=============" + count);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 测试修改随访记录
	@Test
	public void testUpdateFollowUpMain() {
		FollowupMainEbo foEbo = new FollowupMainEbo();
		int followupid = 1;
		int prsnId = 22;
		int staffId = 5;
		foEbo.setId(followupid);
		foEbo.setPrsnId(prsnId);
		foEbo.setStafffId(staffId);
		try {
			followupService.updateFollowupMain(foEbo);
			System.out.println("修改成功");
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
