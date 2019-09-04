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

import com.kjplus.dao.IHealthExamDao;
import com.kjplus.dto.HealthExamDto;
import com.kjplus.eto.HealthExamEto;
import com.kjplus.model.HealthExamEbo;
import com.kjplus.qto.HealthExamQto;
import com.kjplus.service.IHealthExamService;
import com.ybk.exception.DataException;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(value = "classpath:testbeans.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestHealthExamService {

	@Autowired
	private IHealthExamService healthExamService;
	@Autowired
	private IHealthExamDao healthExamDao;

	private static Logger logger = Logger.getLogger(TestHealthExamService.class);

	// 添加 HealthExamEbo 对象
	@Test
	public void testAddHealthExam() {
		try {
			HealthExamEto hExamEto = new HealthExamEto();
			int prsnId = 19;
			int staffId = 2;
			int tbcfgId = 26;
			double temperature = 36.5;
			int respiratoryFrequency = 65;
			int pulseRate = 75;
			int leftHightBlood = 110;
			int leftLowBlood = 112;
			int rightHightBlood = 130;
			int rightLowBlood = 124;
			double height = 175;
			double weight = 65;
			double theWaist = 56;
			double bodyMassIndex = 24.5;
			String flag = "Y";
			Date createTime = new Date();
			String healthMemo = "身体健康状况良好";

			hExamEto.setPrsnId(prsnId);
			hExamEto.setStaffId(staffId);
			hExamEto.setTbcfgId(tbcfgId);
			hExamEto.setTemperature(temperature);
			hExamEto.setRespiratoryFrequency(respiratoryFrequency);
			hExamEto.setPulseRate(pulseRate);
			hExamEto.setLeftHightBlood(leftHightBlood);
			hExamEto.setLeftLowBlood(leftLowBlood);
			hExamEto.setRightHightBlood(rightHightBlood);
			hExamEto.setRightLowBlood(rightLowBlood);
			hExamEto.setHeight(height);
			hExamEto.setWeight(weight);
			hExamEto.setTheWaist(theWaist);
			hExamEto.setBodyMassIndex(bodyMassIndex);
			hExamEto.setFlag(flag);
			hExamEto.setCreateTime(createTime);
			hExamEto.setHealthMemo(healthMemo);
			HealthExamEbo addHealthExam = healthExamService.addHealthExam(hExamEto);
			logger.info("添加成功=============" + addHealthExam.getId());
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	// 查询健康体检记录列表
	@Test
	public void testListHealthExam() {

		try {
			HealthExamQto hExamQto = new HealthExamQto();
			int prsnId = 19;
			int staffId = 5;
			int orgId = 1;
			String startTime = "2018-01-21 00:00:00";
			String endTime = "2018-01-23 17:01:32";
			hExamQto.setPrsnId(prsnId);
			hExamQto.setStaffId(staffId);
			hExamQto.setOrgId(orgId);
			hExamQto.setStartTime(startTime);
			hExamQto.setEndTime(endTime);
			List<HealthExamDto> listHealthExam = healthExamService.listHealthExam(hExamQto, 0, 10);
			for (HealthExamDto healthExamDto : listHealthExam) {
				System.out.println("id=" + healthExamDto.getId() + "code=" + healthExamDto.getCode());
			}
			int total = healthExamService.getTotalHealthExam(hExamQto);
			System.out.println(total);
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 修改健康检查记录信息
	// @Test
	public void testUpdateHealthExam() {

		try {
			String code = "1003935152";
			int leftHightBlood = 250;
			int leftLowBlood = 250;
			int rightHightBlood = 250;
			int rightLowBlood = 250;

			HealthExamEbo healthExamByIdOrCode = healthExamService.getHealthExamByIdOrCode(0, code);
			if (healthExamByIdOrCode == null) {
				throw new DataException("该体检记录信息不存在");
			} else {
				healthExamByIdOrCode.setLeftHightBlood(leftHightBlood);
				healthExamByIdOrCode.setLeftLowBlood(leftLowBlood);
				healthExamByIdOrCode.setRightHightBlood(rightHightBlood);
				healthExamByIdOrCode.setRightLowBlood(rightLowBlood);
				healthExamByIdOrCode.setCode(code);
				healthExamService.updateHealthExam(healthExamByIdOrCode);
			}
			System.out.println("修改档案成功");
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
