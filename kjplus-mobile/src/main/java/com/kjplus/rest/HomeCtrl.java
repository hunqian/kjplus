package com.kjplus.rest;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kjplus.constant.ConstantPageFtl;
import com.kjplus.constant.ConstantUrlWx;
import com.kjplus.service.IInfoService;

@Controller
public class HomeCtrl {

	@Autowired
	private IInfoService infoService;
	private static Logger logger = Logger.getLogger(HomeCtrl.class);	

	@RequestMapping(value = ConstantUrlWx.WX_CN_DOCTOR_SERVICE)
	public ModelAndView cnDoctorService(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(ConstantPageFtl.WX_CN_DOCTOR_SERVICE);
		Map<String, Object> map = mav.getModelMap();
		return mav;
	}
	
	@RequestMapping(value = ConstantUrlWx.WX_CONSULT)
	public ModelAndView consult(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(ConstantPageFtl.WX_CONSULT);
		Map<String, Object> map = mav.getModelMap();
		return mav;
	}
	
/*	@RequestMapping(value = ConstantUrlWx.WX_DIAGNOSE)
	public ModelAndView diagnose(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(ConstantPageFtl.WX_DIAGNOSE);
		Map<String, Object> map = mav.getModelMap();
		return mav;
	}*/
	
//	@RequestMapping(value = ConstantUrlWx.WX_DOCTOR_TEAM)
//	public ModelAndView doctorTeam(HttpServletRequest request, HttpServletResponse response) {
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName(ConstantPageFtl.WX_DOCTOR_TEAM);
//		Map<String, Object> map = mav.getModelMap();
//		return mav;
//	}
	
	@RequestMapping(value = ConstantUrlWx.WX_DRUG_DELIVERY_SITE)
	public ModelAndView drugDeliverySite(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(ConstantPageFtl.WX_DRUG_DELIVERY_SITE);
		Map<String, Object> map = mav.getModelMap();
		return mav;
	}
	
	@RequestMapping(value = ConstantUrlWx.WX_DRUG_DELIVERY)
	public ModelAndView drugDelivery(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(ConstantPageFtl.WX_DRUG_DELIVERY);
		Map<String, Object> map = mav.getModelMap();
		return mav;
	}
	
	@RequestMapping(value = ConstantUrlWx.WX_DRUG_DETAIL)
	public ModelAndView drugDetail(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(ConstantPageFtl.WX_DRUG_DETAIL);
		Map<String, Object> map = mav.getModelMap();
		return mav;
	}
	
	@RequestMapping(value = ConstantUrlWx.WX_DRUG_PRESCRIPTION)
	public ModelAndView drugPrescription(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(ConstantPageFtl.WX_DRUG_PRESCRIPTION);
		Map<String, Object> map = mav.getModelMap();
		return mav;
	}
	
	@RequestMapping(value = ConstantUrlWx.WX_DRUG_SERVICE)
	public ModelAndView drugService(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(ConstantPageFtl.WX_DRUG_SERVICE);
		Map<String, Object> map = mav.getModelMap();
		return mav;
	}
	
/*	@RequestMapping(value = ConstantUrlWx.WX_FAMILY_DOCTOR_APPOINT)
	public ModelAndView familyDoctorAppoint(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(ConstantPageFtl.WX_FAMILY_DOCTOR_APPOINT);
		Map<String, Object> map = mav.getModelMap();
		return mav;
	}*/

	@RequestMapping(value = ConstantUrlWx.WX_HEALTH_CLASSROOM)
	public ModelAndView healthClassroom(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(ConstantPageFtl.WX_HEALTH_CLASSROOM);
		Map<String, Object> map = mav.getModelMap();
		return mav;
	}
	
	@RequestMapping(value = ConstantUrlWx.WX_HEALTH_TOOL)
	public ModelAndView healthTool(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(ConstantPageFtl.WX_HEALTH_TOOL);
		Map<String, Object> map = mav.getModelMap();
		return mav;
	}
	
	@RequestMapping(value = ConstantUrlWx.WX_HELP)
	public ModelAndView help(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(ConstantPageFtl.WX_HELP);
		Map<String, Object> map = mav.getModelMap();
		return mav;
	}
	
	@RequestMapping(value = ConstantUrlWx.WX_INDEX)
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(ConstantPageFtl.WX_INDEX);
		Map<String, Object> map = mav.getModelMap();
		return mav;
	}
	
/*	//随访访视
	@RequestMapping(value = ConstantUrlWx.WX_MEASURE_RECORD_1)
	public ModelAndView measureRecord1(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(ConstantPageFtl.WX_MEASURE_RECORD_1);
		Map<String, Object> map = mav.getModelMap();
		return mav;
	}*/
	
	@RequestMapping(value = ConstantUrlWx.WX_MEASURE_RECORD_2)
	public ModelAndView measureRecord2(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(ConstantPageFtl.WX_MEASURE_RECORD_2);
		Map<String, Object> map = mav.getModelMap();
		return mav;
	}
	
	@RequestMapping(value = ConstantUrlWx.WX_MEASURE_RECORD_3)
	public ModelAndView measureRecord3(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(ConstantPageFtl.WX_MEASURE_RECORD_3);
		Map<String, Object> map = mav.getModelMap();		
		return mav;
	}
	
	//测量记录
/*	@RequestMapping(value = ConstantUrlWx.WX_MEASURE_RECORD)
	public ModelAndView measureRecord(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(ConstantPageFtl.WX_MEASURE_RECORD);
		Map<String, Object> map = mav.getModelMap();
		return mav;
	}
*/	
	@RequestMapping(value = ConstantUrlWx.WX_MYCENTER)
	public ModelAndView myCenter(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(ConstantPageFtl.WX_MYCENTER);
		Map<String, Object> map = mav.getModelMap();
		return mav;
	}
	
	@RequestMapping(value = ConstantUrlWx.WX_MYCOLLECT)
	public ModelAndView myCollect(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(ConstantPageFtl.WX_MYCOLLECT);
		Map<String, Object> map = mav.getModelMap();
		return mav;
	}
	
	/*@RequestMapping(value = ConstantUrlWx.WX_MYFAMILY)
	public ModelAndView myFamily(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(ConstantPageFtl.WX_MYFAMILY);
		Map<String, Object> map = mav.getModelMap();
		return mav;
	}*/
	
/*	@RequestMapping(value = ConstantUrlWx.WX_MYHEALTH)
	public ModelAndView myHealth(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(ConstantPageFtl.WX_MYHEALTH);
		Map<String, Object> map = mav.getModelMap();
		return mav;
	}
	*/
/*	@RequestMapping(value = ConstantUrlWx.WX_MYSCORE)
	public ModelAndView myScore(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(ConstantPageFtl.WX_MYSCORE);
		Map<String, Object> map = mav.getModelMap();
		return mav;
	}*/
	
	@RequestMapping(value = ConstantUrlWx.WX_NEWS)
	public ModelAndView news(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(ConstantPageFtl.WX_NEWS);
		Map<String, Object> map = mav.getModelMap();
		return mav;
	}
	
	@RequestMapping(value = ConstantUrlWx.WX_REFERRAL)
	public ModelAndView referral(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(ConstantPageFtl.WX_REFERRAL);
		Map<String, Object> map = mav.getModelMap();
		return mav;
	}
	
/*	@RequestMapping(value = ConstantUrlWx.WX_SCHEDULE)
	public ModelAndView schedule(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(ConstantPageFtl.WX_SCHEDULE);
		Map<String, Object> map = mav.getModelMap();
		return mav;
	}*/
	
	@RequestMapping(value = ConstantUrlWx.WX_VACCINATION_1)
	public ModelAndView vaccination1(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(ConstantPageFtl.WX_VACCINATION_1);
		Map<String, Object> map = mav.getModelMap();
		return mav;
	}
	
	@RequestMapping(value = ConstantUrlWx.WX_VACCINATION_2)
	public ModelAndView vaccination2(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(ConstantPageFtl.WX_VACCINATION_2);
		Map<String, Object> map = mav.getModelMap();
		return mav;
	}
	
//	@RequestMapping(value = ConstantUrlWx.WX_VACCINATION)
//	public ModelAndView vaccination(HttpServletRequest request, HttpServletResponse response) {
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName(ConstantPageFtl.WX_VACCINATION);
//		Map<String, Object> map = mav.getModelMap();
//		return mav;
//	}
	
	@RequestMapping(value = ConstantUrlWx.WX_VIDEO)
	public ModelAndView video(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(ConstantPageFtl.WX_VIDEO);
		Map<String, Object> map = mav.getModelMap();
		return mav;
	}
	
	@RequestMapping(value = ConstantUrlWx.WX_INOCULATION)
	public ModelAndView inoculation(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(ConstantPageFtl.WX_INOCULATION);
		Map<String, Object> map = mav.getModelMap();
		return mav;
	}
}
