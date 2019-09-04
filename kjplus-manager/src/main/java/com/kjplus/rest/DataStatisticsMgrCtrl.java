package com.kjplus.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
//import org.ybk.basic.image.ImageBasePpUtil;
import org.ybk.basic.util.DateUtil;

import com.kjplus.constant.ConsolePageFtl;
import com.kjplus.constant.Constant;
import com.kjplus.dto.FuncDto;
import com.kjplus.dto.KjAdminUserDto;
import com.kjplus.dto.TagDto;
import com.kjplus.model.SysFuncEbo;
import com.kjplus.model.SysRefValueEbo;
import com.kjplus.model.inner.DocumentInfoInnerEbo;
import com.kjplus.service.IDocInfoService;
import com.kjplus.service.IExamService;
import com.kjplus.service.IPersonOplogService;
import com.kjplus.service.ISysBaseService;
import com.kjplus.service.ISysFuncService;
import com.kjplus.service.ITagService;
import com.ybk.exception.DataException;

@Controller
public class DataStatisticsMgrCtrl {

	private static Logger logger = Logger.getLogger(DataStatisticsMgrCtrl.class);
	@Autowired
	private ISysFuncService funcService;
	@Autowired
	private IExamService examService;
	@Autowired
	private IDocInfoService docInfoService;
	@Autowired
	private ISysBaseService baseService;
	@Autowired
	private ITagService tagService;
	@Autowired
	private IPersonOplogService personOplogService;
	
	//服务数据汇总页面跳转
	@RequestMapping("/datastatisticlist.html")
	public ModelAndView dataStatisticList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = mav.getModel();
		mav.setViewName("datastatistic_list");

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
		if (userEbo == null) {
			mav.setViewName(ConsolePageFtl.LOGIN_ACE);
			map.put("result", -1);
			map.put("returnUrl", "/datastatisticlist.html");
			map.put("message", "用户没有登陆!");
			return mav;
		}

		try {
			SysFuncEbo func = funcService.getFuncByMenu("/datastatisticlist.html", Constant.MENU_TYPE_MANAGER);
			if (func != null)
				map.put("pmenu", func.getPid());
			else
				map.put("pmenu", -1);
			List<FuncDto> funcs = funcService.listUserFunc(userEbo.getUid());
			map.put("funcs", funcs);
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return mav;
	}

	//数据汇总页面数据
	@RequestMapping("/datastatisticlistjson.html")
	public @ResponseBody
	Map<String, Object> dataStatisticListJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
		int orgId = userEbo.getOrgId();
		//开始结束之间 	格式：2018-1-2
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		
		try {
			int start = DateUtil.parseTimeStrInSec(startTime);
			int end = DateUtil.parseTimeStrInSec(endTime);
			
			//健康档案份数
			int totalDoc = docInfoService.getTotalDocInfoInner(orgId, null, null, Constant.FLAG_YES, null, null,start,end);
			map.put("totalDoc",totalDoc);
			//总的档案更新数
			int uptotalDoc = personOplogService.getTotaPrsnOplog(null, 0, 0, 0, Constant.FLAG_YES, start, end,false);
			map.put("uptotalDoc",uptotalDoc);
			
			/* 
			 * 0-6岁儿童健康档案
			 * */
			//0-6岁儿童健康档案数
			int totalDoc0to6 = 0;
			List<Integer> tagIds1 = null;
			SysRefValueEbo refVl1 = baseService.getRefVlByCode(Constant.TAG_PERSON_ET_0TO6);
			if(refVl1 !=null){
				tagIds1 = new ArrayList<Integer>();
				tagIds1.add(refVl1.getId());
				totalDoc0to6 = docInfoService.getTotalDocInfoInner(orgId, null, null, Constant.FLAG_YES, null, tagIds1,start,end);
			}
			map.put("totalDoc0to6", totalDoc0to6);
			//0-6岁儿童健康档案更新数
			List<DocumentInfoInnerEbo> docs = docInfoService.listDocInfoInner(orgId, null, null, Constant.FLAG_YES, null, tagIds1, true, start, end, 0, -1);
			List<Integer> prsns = new ArrayList<Integer>();
			for(DocumentInfoInnerEbo doc : docs){
				prsns.add(doc.getPrsnId());
			}
			int uptotalDoc0to6 = personOplogService.getTotaPrsnOplog(prsns, 0, 0, 0, Constant.FLAG_YES, start, end,false);
			map.put("uptotalDoc0to6",uptotalDoc0to6);
			
			/* 
			 * 65岁以上健康档案
			 * */
			//65岁以上健康档案数
			int totalDocOver50 = 0;
			List<Integer> tagIds2 = null;
			SysRefValueEbo refVl2 = baseService.getRefVlByCode(Constant.TAG_PERSON_LNR_OVER65);
			if(refVl2 !=null){
				tagIds2 = new ArrayList<Integer>();
				tagIds2.add(refVl2.getId());
				totalDocOver50 = docInfoService.getTotalDocInfoInner(orgId, null, null, Constant.FLAG_YES, null, tagIds2,start,end);
			}
			map.put("totalDocOver50", totalDocOver50);
			//65岁以上健康档案更新数
			List<DocumentInfoInnerEbo> docs2 = docInfoService.listDocInfoInner(orgId, null, null, Constant.FLAG_YES, null, tagIds2, true, start, end, 0, -1);
			List<Integer> prsns2 = new ArrayList<Integer>();
			for(DocumentInfoInnerEbo doc : docs2){
				prsns2.add(doc.getPrsnId());
			}
			int uptotalDocOver50 = personOplogService.getTotaPrsnOplog(prsns2, 0, 0, 0, Constant.FLAG_YES, start, end,false);
			map.put("uptotalDocOver50",uptotalDocOver50);
			
			/* 
			 * 高血压
			 * */
			//高血压
			int totalDocGXY = 0;
			List<Integer> tagIds3 = null;
			SysRefValueEbo refVl3 = baseService.getRefVlByCode(Constant.TAG_PERSON_GXY);
			if(refVl3 !=null){
				tagIds3 = new ArrayList<Integer>();
				tagIds3.add(refVl3.getId());
				totalDocGXY = docInfoService.getTotalDocInfoInner(orgId, null, null, Constant.FLAG_YES, null, tagIds3,start,end);
			}
			map.put("totalDocGXY", totalDocGXY);
			//高血压档案更新数
			List<DocumentInfoInnerEbo> docs3 = docInfoService.listDocInfoInner(orgId, null, null, Constant.FLAG_YES, null, tagIds3, true, start, end, 0, -1);
			List<Integer> prsns3 = new ArrayList<Integer>();
			for(DocumentInfoInnerEbo doc : docs3){
				prsns3.add(doc.getPrsnId());
			}
			int uptotalDocGXY = personOplogService.getTotaPrsnOplog(prsns3, 0, 0, 0, Constant.FLAG_YES, start, end,false);
			map.put("uptotalDocGXY",uptotalDocGXY);
			
			/*
			 * 2型糖尿病
			 * */
			//2型糖尿病
			int totalDocTNB = 0;
			List<Integer> tagIds4 = null;
			SysRefValueEbo refVl4 = baseService.getRefVlByCode(Constant.TAG_PERSON_TNB);
			if(refVl4 !=null){
				tagIds4 = new ArrayList<Integer>();
				tagIds4.add(refVl4.getId());
				totalDocTNB = docInfoService.getTotalDocInfoInner(orgId, null, null, Constant.FLAG_YES, null, tagIds4,start,end);
			}
			map.put("totalDocTNB", totalDocTNB);
			//2型糖尿病档案更新数
			List<DocumentInfoInnerEbo> docs4 = docInfoService.listDocInfoInner(orgId, null, null, Constant.FLAG_YES, null, tagIds4, true, start, end, 0, -1);
			List<Integer> prsns4 = new ArrayList<Integer>();
			for(DocumentInfoInnerEbo doc : docs4){
				prsns4.add(doc.getPrsnId());
			}
			int uptotalDocTNB = personOplogService.getTotaPrsnOplog(prsns4, 0, 0, 0, Constant.FLAG_YES, start, end,false);
			map.put("uptotalDocTNB",uptotalDocTNB);
			
			/*
			 * 孕产妇
			 * */
			//孕产妇
			int totalDocYCF = 0;
			List<Integer> tagIds5 = null;
			SysRefValueEbo refVl5= baseService.getRefVlByCode(Constant.TAG_PERSON_YF);
			SysRefValueEbo refVl6= baseService.getRefVlByCode(Constant.TAG_PERSON_CF);
			if(refVl5 !=null || refVl6 !=null){
				tagIds5 = new ArrayList<Integer>();
				if(refVl5 != null)
					tagIds5.add(refVl5.getId());
				if(refVl6 != null)
					tagIds5.add(refVl6.getId());
				totalDocYCF = docInfoService.getTotalDocInfoInner(orgId, null, null, Constant.FLAG_YES, null, tagIds5,start,end);
			}
			map.put("totalDocYCF", totalDocYCF);
			//孕产妇档案更新数
			List<DocumentInfoInnerEbo> docs5 = docInfoService.listDocInfoInner(orgId, null, null, Constant.FLAG_YES, null, tagIds5, true, start, end, 0, -1);
			List<Integer> prsns5 = new ArrayList<Integer>();
			for(DocumentInfoInnerEbo doc : docs5){
				prsns5.add(doc.getPrsnId());
			}
			int uptotalDocYCF = personOplogService.getTotaPrsnOplog(prsns5, 0, 0, 0, Constant.FLAG_YES, start, end,false);
			map.put("uptotalDocYCF",uptotalDocYCF);
			
			/*
			 * 严重精神障碍
			 * */
			//严重精神障碍
			int totalDocZJSB = 0;
			List<Integer> tagIds7 = null;
			SysRefValueEbo refVl7 = baseService.getRefVlByCode(Constant.TAG_PERSON_ZJSB);
			if(refVl7 !=null){
				tagIds7 = new ArrayList<Integer>();
				tagIds7.add(refVl7.getId());
				totalDocZJSB = docInfoService.getTotalDocInfoInner(orgId, null, null, Constant.FLAG_YES, null, tagIds7,start,end);
			}
			map.put("totalDocZJSB", totalDocZJSB);
			//严重精神障碍档案更新数
			List<DocumentInfoInnerEbo> docs7 = docInfoService.listDocInfoInner(orgId, null, null, Constant.FLAG_YES, null, tagIds7, true, start, end, 0, -1);
			List<Integer> prsns7 = new ArrayList<Integer>();
			for(DocumentInfoInnerEbo doc : docs7){
				prsns7.add(doc.getPrsnId());
			}
			int uptotalDocZJSB = personOplogService.getTotaPrsnOplog(prsns7, 0, 0, 0, Constant.FLAG_YES, start, end,false);
			map.put("uptotalDocZJSB",uptotalDocZJSB);
			
			/*
			 * 肺结核
			 * */
			//肺结核
			int totalDocJHB = 0;
			List<Integer> tagIds8 = null;
			SysRefValueEbo refVl8 = baseService.getRefVlByCode(Constant.TAG_PERSON_JHB);
			if(refVl8 !=null){
				tagIds8 = new ArrayList<Integer>();
				tagIds8.add(refVl8.getId());
				totalDocJHB = docInfoService.getTotalDocInfoInner(orgId, null, null, Constant.FLAG_YES, null, tagIds8,start,end);
			}
			map.put("totalDocJHB", totalDocJHB);
			//肺结核档案更新数
			List<DocumentInfoInnerEbo> docs8 = docInfoService.listDocInfoInner(orgId, null, null, Constant.FLAG_YES, null, tagIds8, true, start, end, 0, -1);
			List<Integer> prsns8 = new ArrayList<Integer>();
			for(DocumentInfoInnerEbo doc : docs8){
				prsns8.add(doc.getPrsnId());
			}
			int uptotalDocJHB = personOplogService.getTotaPrsnOplog(prsns8, 0, 0, 0, Constant.FLAG_YES, start, end,false);
			map.put("uptotalDocJHB",uptotalDocJHB);
			
			/*
			 * 残疾人
			 * */
			//残疾人
			int totalDocCJR = 0;
			List<Integer> tagIds9 = null;
			SysRefValueEbo refVl9 = baseService.getRefVlByCode(Constant.TAG_PERSON_CJR);
			if(refVl9 !=null){
				tagIds9 = new ArrayList<Integer>();
				tagIds9.add(refVl8.getId());
				totalDocCJR = docInfoService.getTotalDocInfoInner(orgId, null, null, Constant.FLAG_YES, null, tagIds9,start,end);
			}
			map.put("totalDocCJR", totalDocCJR);
			//残疾人档案更新数
			List<DocumentInfoInnerEbo> docs9 = docInfoService.listDocInfoInner(orgId, null, null, Constant.FLAG_YES, null, tagIds9, true, start, end, 0, -1);
			List<Integer> prsns9 = new ArrayList<Integer>();
			for(DocumentInfoInnerEbo doc : docs9){
				prsns9.add(doc.getPrsnId());
			}
			int uptotalDocCJR = personOplogService.getTotaPrsnOplog(prsns9, 0, 0, 0, Constant.FLAG_YES, start, end,false);
			map.put("uptotalDocCJR",uptotalDocCJR);
			
			/*
			 * 特困
			 * */
			//特困
			int totalDocTK = 0;
			List<Integer> tagIds10 = null;
			SysRefValueEbo refVl10 = baseService.getRefVlByCode(Constant.TAG_PERSON_TK);
			if(refVl10 !=null){
				tagIds10 = new ArrayList<Integer>();
				tagIds10.add(refVl10.getId());
				totalDocTK = docInfoService.getTotalDocInfoInner(orgId, null, null, Constant.FLAG_YES, null, tagIds10,start,end);
			}
			map.put("totalDocTK", totalDocTK);
			//残疾人档案更新数
			List<DocumentInfoInnerEbo> docs10= docInfoService.listDocInfoInner(orgId, null, null, Constant.FLAG_YES, null, tagIds10, true, start, end, 0, -1);
			List<Integer> prsns10 = new ArrayList<Integer>();
			for(DocumentInfoInnerEbo doc : docs10){
				prsns10.add(doc.getPrsnId());
			}
			int uptotalDocTK = personOplogService.getTotaPrsnOplog(prsns10, 0, 0, 0, Constant.FLAG_YES, start, end,false);
			map.put("uptotalDocTK",uptotalDocTK);
			
			//普通人群
			//档案总人数减去打过标签的人数就是普通人数
			int tagTotals = tagService.getTotalTagPrsns(Constant.TAG_TYPE_DOC);
			int totalCommon = totalDoc - tagTotals;
			map.put("totalCommon",totalCommon);
			//未打标签档案用户的档案更新数
			List<DocumentInfoInnerEbo> docs11 =  docInfoService.listDocInfoInner(orgId, null, null, Constant.FLAG_YES, null, null, true, start, end, 0, -1);
			List<Integer> prsns11 = new ArrayList<Integer>();
			for(DocumentInfoInnerEbo doc : docs11){
				List<TagDto> tags = doc.getTags();
				if(tags.size() == 0 )//添加未打标签的档案id
					prsns11.add(doc.getPrsnId());
			}
			int uptotalCommon = personOplogService.getTotaPrsnOplog(prsns10, 0, 0, 0, Constant.FLAG_YES, start, end,false);
			map.put("uptotalCommon",uptotalCommon);
			
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}


}
