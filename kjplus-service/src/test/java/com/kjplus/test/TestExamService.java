package com.kjplus.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import com.kjplus.dao.IExamDao;
import com.kjplus.dto.ExamBloodDto;
import com.kjplus.dto.ExamGlycemicDto;
import com.kjplus.dto.ExamMainSimpleDto;
import com.kjplus.dto.ExamUrineDto;
import com.kjplus.eto.ExamBloodEto;
import com.kjplus.eto.ExamGlycemicEto;
import com.kjplus.eto.ExamUrineEto;
import com.kjplus.model.ExamMainEbo;
import com.kjplus.model.ExamUrineEbo;
import com.kjplus.model.inner.ExamMainInnerEbo;
import com.kjplus.qto.ExamQto;
import com.kjplus.service.IExamService;
import com.ybk.exception.DataException;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(value = "classpath:testbeans.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestExamService {

	@Autowired
	private IExamDao examDao;
	@Autowired
	private IExamService examService;
	
	private static Logger logger = Logger.getLogger(TestExamService.class);

	//测试添加主表和血压
	@Test
	public void testAddExamBloodEbo() {
		try {
			int examTypeId = 22;
			int flpeId = 1;
			int prsnId = 22;
			int staffId = 2;
			int orgId = 1;
			double shrinkPress = 11d;
			double diastolePress = 22d;
			int heartRate = 100;
			int examTime = DateUtil.getCurTimeInSec();
			ExamBloodEto emBlood = new ExamBloodEto();
			emBlood.setExamTypeId(examTypeId);
			emBlood.setFlpeId(flpeId);
			emBlood.setPrsnId(prsnId);
			emBlood.setStaffId(staffId);
			emBlood.setOrgId(orgId);
			emBlood.setExamTime(examTime);
			emBlood.setShrinkPress(shrinkPress);
			emBlood.setDiastolePress(diastolePress);
			emBlood.setHeartRate(heartRate);
			ExamMainInnerEbo emInner = examService.addExamBloodEbo(emBlood);
			System.out.println("添加测试数据成功 "+emInner);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	//测试添加主表和血糖
	@Test
	public void testAddExamGlycemicEbo() {
		try {
			String examCode = "2294033492564123";//给该主表添加血糖
			int prsnId = 22;
		/*	int examTypeId = 22;
			int flpeId = 1;
			int staffId = 2;
			int orgId = 1;*/
			double glycemicVal = 100;
			String measureStatus = "K";
			int examTime = DateUtil.getCurTimeInSec();
			ExamGlycemicEto emGly = new ExamGlycemicEto();
			emGly.setExamCode(examCode);
			emGly.setPrsnId(prsnId);
/*			emGly.setExamTypeId(examTypeId);
			emGly.setFlpeId(flpeId);
			emGly.setStaffId(staffId);
			emGly.setOrgId(orgId);*/
			emGly.setExamTime(examTime);
			emGly.setGlycemicVal(glycemicVal);
			emGly.setMeasureStatus(measureStatus);
			ExamMainInnerEbo emInner = examService.addExamGlycemicEbo(emGly);
			System.out.println("添加测试数据成功 "+emInner);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	//测试添加血压，血糖，主表
	@Test
	public void testAddExamMainEbo() {
		try {
			int prsnId = 22;
			int examTypeId = 22;
			int flpeId = 1;
			int staffId = 2;
			int orgId = 1;
			double glycemicVal = 100;
			double shrinkPress = 11d;
			double diastolePress = 22d;
			int heartRate = 100;
			String measureStatus = "K";
			int examTime = DateUtil.getCurTimeInSec();
			//添加血糖和主表
			ExamGlycemicEto emGly = new ExamGlycemicEto();
			emGly.setPrsnId(prsnId);
			emGly.setExamTypeId(examTypeId);
			emGly.setFlpeId(flpeId);
			emGly.setStaffId(staffId);
			emGly.setOrgId(orgId);
			emGly.setExamTime(examTime);
			emGly.setGlycemicVal(glycemicVal);
			emGly.setMeasureStatus(measureStatus);
			ExamMainInnerEbo emInner = examService.addExamGlycemicEbo(emGly);
			System.out.println("添加主表和血糖成功 "+emInner);
			//添加该主表对应的血压
			ExamBloodEto emBlood = new ExamBloodEto();
			emBlood.setExamCode(emInner.getExamCode());
			emBlood.setPrsnId(prsnId);
			emBlood.setShrinkPress(shrinkPress);
			emBlood.setDiastolePress(diastolePress);
			emBlood.setHeartRate(heartRate);
			ExamMainInnerEbo emInner2 = examService.addExamBloodEbo(emBlood);
			System.out.println("添加血压成功 "+emInner2);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetExamMainEbo() {
		try {
			int id = 1;
			String code = "11111";
			ExamMainEbo em = examDao.getExamMainEboByIdOrCode(id, code);
			logger.info("获取examMain成功："+em);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}	
	@Test
	public void testListExamMainInnerEbo() {
		try {
			ExamQto examQto = new ExamQto(); 
/*			String examTypeCode = "";
			examQto.setExamTypeCode(examTypeCode);
*/			int orgId = 1;
			examQto.setOrgId(orgId);
			/*	int prsnId = 19;
			examQto.setPrsnId(prsnId);
			String prsnIdStr = "19,21";
			examQto.setPrsnIdStr(prsnIdStr);
			int staffId = 2;
			examQto.setStaffId(staffId);
			*/
			//int firstTime = 1514276660;
			//examQto.setFirstTime(firstTime);
			//int lastTime = 1514276669;
			//examQto.setLastTime(lastTime);
			int page = 0;
			int paging = 10;
			List<ExamMainSimpleDto> listExamInner = examService.listExamMainInnerEbo(examQto,page,paging);
			for(ExamMainSimpleDto ex : listExamInner){
				logger.info("获取examMainInner成功："+ex);
			}
			
		} catch (DataException e) {
			e.printStackTrace();
		}
	}	

	@Test
	public void testlistExamObjs() {
		try {
			List<Integer> flpeIds = new ArrayList<Integer>();
			flpeIds.add(1);
			flpeIds.add(2);
			Map<Integer, List<Object>> examMap = examService.listExamObjs(flpeIds);
			for (Map.Entry<Integer, List<Object>> entry : examMap.entrySet()) {
			    logger.info("获取examMap成功："+entry.getKey());
			    for (Object ex : examMap.get(entry.getKey())) {
					if (ex instanceof ExamGlycemicDto) {
						ExamGlycemicDto exGly = (ExamGlycemicDto) ex;
						System.out.println("血糖值："+ exGly.getGlycemicVal()+ "测试环境："+exGly.getMeasureStatus());
					}
					if (ex instanceof ExamBloodDto) {
						ExamBloodDto exBlood = (ExamBloodDto) ex;
						System.out.println("收缩压："+ exBlood.getShrinkPress()+ "舒张压："+exBlood.getDiastolePress());
					}
					if (ex instanceof List<?>) {
						@SuppressWarnings("unchecked")
						List<ExamUrineDto> listUrine = (List<ExamUrineDto>)ex;
						for(ExamUrineDto urine : listUrine){
							System.out.println("尿检数据");
							System.out.println(urine.getUrineTypeName() + "===" + urine.getUrineVl());
						}
					}
					
				}
			}
			
			
		} catch (DataException e) {
			e.printStackTrace();
		}
	}	
	
	@Test
	public void testGetExamUrine() {
		try {
			int id = 1;
			ExamUrineEbo urine= examService.getExamUrineEboById(id);
			System.out.println(urine);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddExamUrine() {
		try {
			ExamUrineEto u1 = new ExamUrineEto();
			//u1.setExamCode("");
			u1.setUrineTypeId(1121);
			u1.setUrineVl("2Test");
			u1.setPrsnId(23);
			ExamUrineEto u2 = new ExamUrineEto();
			//u2.setExamCode("");
			u2.setUrineTypeId(1123);
			u2.setUrineVl("3Test");
			u2.setPrsnId(24);
			List<ExamUrineEto> listUrine = new ArrayList<ExamUrineEto>();
			listUrine.add(u1);
			listUrine.add(u2);
			examService.addExamUrine(listUrine);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
