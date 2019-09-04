package com.kjplus.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
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
import org.ybk.basic.util.DateUtil;
import org.ybk.basic.util.Util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kjplus.constant.ConsolePageFtl;
import com.kjplus.constant.Constant;
import com.kjplus.constant.ConstantTableCfg;
import com.kjplus.dto.FuncDto;
import com.kjplus.dto.KjAdminUserDto;
import com.kjplus.dto.StaffDto;
import com.kjplus.dto.TableCfgHeadDto;
import com.kjplus.dto.TableCfgLineDto;
import com.kjplus.dto.TableDataLineDto;
import com.kjplus.eto.FollowupMainEto;
import com.kjplus.eto.TableDataHeadEto;
import com.kjplus.eto.TableDataLineEto;
import com.kjplus.model.DocumentInfoEbo;
import com.kjplus.model.FollowupMainEbo;
import com.kjplus.model.SysFuncEbo;
import com.kjplus.model.TableCfgHeadEbo;
import com.kjplus.model.TableDataHeadEbo;
import com.kjplus.service.IDocInfoService;
import com.kjplus.service.IFollowupService;
import com.kjplus.service.IStaffService;
import com.kjplus.service.ISysBaseService;
import com.kjplus.service.ISysFuncService;
import com.kjplus.service.ITableService;
import com.ybk.exception.DataException;

//import org.ybk.basic.image.ImageBasePpUtil;

@Controller
public class FollowUpPageMgrCtrl {

	private static Logger logger = Logger.getLogger(FollowUpPageMgrCtrl.class);
	@Autowired
	private ISysFuncService funcService;
	@Autowired
	private IFollowupService followupService;
	@Autowired
	private ITableService tableService;
	@Autowired
	private IDocInfoService docInfoService;
	@Autowired
	private ISysBaseService baseService;
	@Autowired
	private IStaffService staffService;

	// 随访记录详情
	@RequestMapping("/followuppage.html")
	public ModelAndView createDocument(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = mav.getModel();

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
		if (userEbo == null) {
			mav.setViewName(ConsolePageFtl.LOGIN_ACE);
			map.put("result", -1);
			map.put("returnUrl", "/followuppage.html");
			map.put("message", "用户没有登陆!");
			return mav;
		}

		int followupid = Util.parseNumVl(request.getParameter("followupid"), 0);
		String tableCode = request.getParameter("tableCode");
		String activepage = request.getParameter("activepage");
		String followCode = null;
		if (followupid != 0) {
			try {
				FollowupMainEbo follow = followupService.getFollowupMainByIdOrCode(followupid, null);
				int tableId = follow.getTbcfgId();
				followCode = follow.getCode();
				TableCfgHeadEbo cfgHeadById = tableService.getCfgHeadById(tableId);
				tableCode = cfgHeadById.getCode();
			} catch (DataException e) {
				e.printStackTrace();
			}

		}
		if (followCode != null) {
			map.put("followCode", followCode);
		}

		try {
			SysFuncEbo func = funcService.getFuncByMenu("/followuppage.html", Constant.MENU_TYPE_MANAGER);
			if (func != null)
				map.put("pmenu", func.getPid());
			else
				map.put("pmenu", -1);
			List<FuncDto> funcs = funcService.listUserFunc(userEbo.getUid());
			map.put("funcs", funcs);
			if (tableCode != null) {
				if (tableCode.equals(ConstantTableCfg.TB_RECORDS_OF_HYPERTENSIVE_PATIENTS)) {// 高血压患者随访服务记录表
					mav.setViewName("hypertension_page");
				} else if (tableCode.equals(ConstantTableCfg.TB_SERVICE_WITH_DIABETES)) {// 二型糖尿病患者随访服务记录表
					mav.setViewName("typetwodiabetes_page");
				} else if (tableCode.equals(ConstantTableCfg.TB_NEWBORNVST_BV)) {// 新生儿
					mav.setViewName("newborn_page");
				} else if (tableCode.equals(ConstantTableCfg.TB_HEALTHYRECORD_FLMN)) {// 满月龄儿童健康检查记录表
					mav.setViewName("fullmoonmoa_page");
				} else if (tableCode.equals(ConstantTableCfg.TB_HEALH_EXAMINATION)) {// 12月龄儿童健康检查记录表
					mav.setViewName("twelvemoonmoa_page");
				} else if (tableCode.equals(ConstantTableCfg.TB_First_antenatal_checklist)) {// 第一次产前检查服务记录表
					mav.setViewName("firstantenatal_page");
				} else if (tableCode.equals(ConstantTableCfg.TB_FOLLOWUP_SERVICE_RECORD)) {// 第2次产前随访服务记录表
					mav.setViewName("secondantenatal_page");
				} else if (tableCode.equals(ConstantTableCfg.TB_Postpartum_visit_record)) {// 产后访视记录表
					mav.setViewName("postpartumrecord_page");
				} else if (tableCode.equals(ConstantTableCfg.TB_POSTPARTUM_HEALTH_CHECKLIST)) {// 产后42天健康检查记录表
					mav.setViewName("phealthchecklist_page");
				} else if (tableCode.equals(ConstantTableCfg.TB_ATFTSAOTE)) {// 老年人生活自理能力评估表
					mav.setViewName("aged_page");
				} else if (tableCode.equals(ConstantTableCfg.TB_PISFPWSMD)) {// 严重精神障碍患者个人信息补充表
					mav.setViewName("mentaldisordercomplements_page");
				} else if (tableCode.equals(ConstantTableCfg.TB_ROFSRFPWSMD)) {// 严重精神障碍患者随访服务记录表
					mav.setViewName("mentaldisorder_page");
				} else if (tableCode.equals(ConstantTableCfg.TB_FHFROPWPT)) {// 肺结核患者第一次入户随访记录表
					mav.setViewName("firstpulmonary_page");
				} else if (tableCode.equals(ConstantTableCfg.TB_RTOFSFPTP)) {// 肺结核患者随访服务记录表
					mav.setViewName("pulmonary_page");
				} else if (tableCode.equals(ConstantTableCfg.TB_SIX_MOCHMSROCM)) {// 6月龄儿童中医药健康管理服务记录表
					mav.setViewName("sixmoonhmoc_page");
				} else if (tableCode.equals(ConstantTableCfg.TB_MOCHMSROCM)) {// 24月龄儿童中医药健康管理服务记录表
					mav.setViewName("twelvemoonmoa_page");
				} else if (tableCode.equals(ConstantTableCfg.TB_THREE_MOCHC)) {// 3月龄儿童健康检查记录表
					mav.setViewName("twelvemoonmoa_page");
				} else if (tableCode.equals(ConstantTableCfg.TB_SIX_MOCHC)) {// 6月龄儿童健康检查记录表
					mav.setViewName("twelvemoonmoa_page");
				} else if (tableCode.equals(ConstantTableCfg.TB_EIGHT_MOCHC)) {// 8月龄儿童健康检查记录表
					mav.setViewName("twelvemoonmoa_page");
				} else if (tableCode.equals(ConstantTableCfg.TB_EIGHTEEN_MOCHC)) {// 18月龄儿童健康检查记录表
					mav.setViewName("twelvemoonmoa_page");
				} else if (tableCode.equals(ConstantTableCfg.TB_TWENTYFOUR_MOCHC)) {// 24月龄儿童健康检查记录表
					mav.setViewName("twelvemoonmoa_page");
				} else if (tableCode.equals(ConstantTableCfg.TB_THIRTY_MOCHC)) {// 30月龄儿童健康检查记录表
					mav.setViewName("twelvemoonmoa_page");
				} else if (tableCode.equals(ConstantTableCfg.TB_FOUR_HCFYOC)) {// 4岁儿童健康检查记录表
					mav.setViewName("twelvemoonmoa_page");
				} else if (tableCode.equals(ConstantTableCfg.TB_FIVE_HOFYOC)) {// 5岁儿童健康检查记录表
					mav.setViewName("twelvemoonmoa_page");
				} else if (tableCode.equals(ConstantTableCfg.TB_SIX_HOFYOC)) {// 6岁儿童健康检查记录表
					mav.setViewName("twelvemoonmoa_page");
				} else if (tableCode.equals(ConstantTableCfg.TB_THIRD_ROPFSR)) {// 第3次产前随访服务记录表
					mav.setViewName("mentaldisorder_page");
				} else if (tableCode.equals(ConstantTableCfg.TB_FOURTH_ROPFSR)) {// 第4次产前随访服务记录表
					mav.setViewName("firstpulmonary_page");
				} else if (tableCode.equals(ConstantTableCfg.TB_FIFTH_ROPFSR)) {// 第5次产前随访服务记录表
					mav.setViewName("pulmonary_page");
				} else if (tableCode.equals(ConstantTableCfg.TB_TWELVE_MOCHMSROCM)) {// 12月龄儿童中医药健康管理服务记录表
					mav.setViewName("twelvemoonmoa_page");
				} else if (tableCode.equals(ConstantTableCfg.TB_EIGHTEEN_MOCHMSROCM)) {// 18月龄儿童中医药健康管理服务记录表
					mav.setViewName("twelvemoonmoa_page");
				} else if (tableCode.equals(ConstantTableCfg.TB_THIRTY_MOCHMSROCM)) {// 30月龄儿童中医药健康管理服务记录表
					mav.setViewName("twelvemoonmoa_page");
				} else if (tableCode.equals(ConstantTableCfg.TB_THIRTYSIX_MOCHMSROCM)) {// 36月龄儿童中医药健康管理服务记录表
					mav.setViewName("twelvemoonmoa_page");
				} else if (tableCode.equals(ConstantTableCfg.TB_THREE_HCFYOC)) {// 3岁儿童健康检查记录表
					mav.setViewName("threeagemoa_page");
				} else {
					map.put("message", "没有该随访表");
				}
			} else {
				mav.setViewName("followuppage_list");
			}

			if (followupid > 0) {
				DocumentInfoEbo docInfo = docInfoService.getDocinfoByIdOrCode(followupid, null);
				map.put("docInfo", docInfo);
			}

			List<StaffDto> listStaffDto = staffService.listStaffDto(userEbo.getOrgId(), -1, null,null,null, 0, 10);
			map.put("listStaffDto", listStaffDto);
			map.put("followupid", followupid);
			map.put("tableCode", tableCode);
			map.put("activepage", activepage);

			// 对建档居民进行一系列操作，传递建档居民的编码和名字
			int prsnId = Util.parseNumVl(request.getParameter("prsnId"), 0);
			DocumentInfoEbo docInfoEbo = docInfoService.getDocinfoByIdOrCode(prsnId, null);
			if (docInfoEbo != null) {
				String prsnName = docInfoEbo.getPrsnName();
				map.put("prsnId", prsnId);
				map.put("prsnName", prsnName);
			}

		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return mav;
	}

	// 配置所对应的所有参照
	@RequestMapping(value = "/followuptablecfgrefjson.html")
	public @ResponseBody
	Map<String, Object> tableCfgRefJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
		if (userEbo == null) {
			map.put("result", -1);
			map.put("message", "用户没有登陆!");
			return map;
		}

		try {
			String tableCode = request.getParameter("tableCode");
			if (Util.isNull(tableCode))
				tableCode = ConstantTableCfg.TB_HEALTHYRECORD_FLMN;
			// 获得配置
			List<TableCfgLineDto> cfgs = tableService.listTableLine(tableCode, true);
			Hashtable<String, TableCfgLineDto> cfgHash = new Hashtable<String, TableCfgLineDto>();
			Hashtable<String, TableCfgLineDto> cfgHash2 = new Hashtable<String, TableCfgLineDto>();
			for (TableCfgLineDto c : cfgs) {
				if (c.getRefVls().size() == 0)
					continue;
				cfgHash.put(c.getLineCode(), c);
			}
			for (TableCfgLineDto c : cfgs) {
				cfgHash2.put(c.getLineCode(), c);
			}
			// 全部表格配置
			map.put("alldata", cfgHash2);
			// 有引用的表格配置
			map.put("data", cfgHash);
			map.put("result", 1);
			map.put("message", "保存随访记录成功！");
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 随访详情
	@RequestMapping("/followuppagedata.html")
	public @ResponseBody
	Map<String, Object> docInfoPageData(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
		if (userEbo == null) {
			map.put("result", -1);
			map.put("returnUrl", "/followuppage.html");
			map.put("message", "用户没有登陆!");
			return map;
		}

		int followupid = Util.parseNumVl(request.getParameter("followupid"), 0);
		int prsnId = Util.parseNumVl(request.getParameter("prsnId"), 0);
		try {
			List<TableDataHeadEbo> heads = new ArrayList<TableDataHeadEbo>();
			if (followupid > 0)
				heads = tableService.listDataHeadByMainId(followupid, "FLW");
			Hashtable<String, TableDataLineDto> dataHash = new Hashtable<String, TableDataLineDto>();
			if (heads.size() > 0) {
				List<TableDataLineDto> lines = tableService.listDataLine(heads.get(0).getId());
				for (TableDataLineDto line : lines) {
					dataHash.put(line.getLineCode(), line);
				}
				FollowupMainEbo followInfo = followupService.getFollowupMainByIdOrCode(followupid, null);
				map.put("followInfo", followInfo);
				map.put("dochead", heads.get(0));
			}
			map.put("dataHash", dataHash);
			if (dataHash != null) {
				if (dataHash.isEmpty()) {
					map.put("show", "Y");
				} else {
					map.put("show", "N");
				}
			}

			DocumentInfoEbo docInfoEbo = docInfoService.getDocinfoByIdOrCode(prsnId, null);
			if (docInfoEbo != null) {
				String prsnName = docInfoEbo.getPrsnName();
				map.put("prsnName", prsnName);
			}

		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 保存档案
	@RequestMapping(value = "/saveormodifyfollowupjson.html")
	public @ResponseBody
	Map<String, Object> saveJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
		if (userEbo == null) {
			map.put("result", -1);
			map.put("message", "用户没有登陆!");
			return map;
		}

		try {

			int followupid = Util.parseNumVl(request.getParameter("followupid"), 0);
			int prsnId = Util.parseNumVl(request.getParameter("prsnId"), 0);
			int staffId = Util.parseNumVl(request.getParameter("staffId"), userEbo.getUid());
			String flpeMiscType = request.getParameter("flpeMiscType");

			String tableCode = request.getParameter("tableCode");
			String followupjson = request.getParameter("followupjson");
			System.out.println("页面" + followupjson);
			JSONArray followupArr = JSONArray.parseArray(followupjson);
			System.out.println("转换后" + followupArr);
			if (followupArr == null || followupArr.size() == 0) {
				map.put("result", -1);
				map.put("message", "空对象!");
				return map;
			}

			TableDataHeadEto dataHead = new TableDataHeadEto();
			TableCfgHeadEbo cfgHeadByCode = tableService.getCfgHeadByCode(tableCode);
			if (tableCode != null && cfgHeadByCode != null) {
				dataHead.setCfgId(cfgHeadByCode.getId());
			} else {
				throw new Error("请指定随访的表格");
			}
			dataHead.setOrgId(userEbo.getOrgId());
			dataHead.setStaffId(staffId);
			dataHead.setUid(userEbo.getUid());
			Date date = new Date();
			dataHead.setSheetDay(date);
			dataHead.setBeginTime(DateUtil.getCurTimeInSec());
			dataHead.setEndTime(DateUtil.getCurTimeInSec());

			List<TableDataLineEto> dataLines = new ArrayList<TableDataLineEto>();
			JSONObject di = null;
			Hashtable<String, TableDataLineEto> lineHash = new Hashtable<String, TableDataLineEto>();
			for (int i = 0; i < followupArr.size(); i++) {
				di = followupArr.getJSONObject(i);
				TableDataLineEto dataLine = new TableDataLineEto();
				dataLine.setLineCode(di.getString("code"));
				dataLine.setRefId(Util.parseNumVl(di.getString("refid"), 0));
				dataLine.setInputVl(di.getString("vl"));
				dataLines.add(dataLine);
				lineHash.put(di.getString("code"), dataLine);
			}

			// 预先检查
			tableService.preCheckTableData(dataHead, dataLines);

			FollowupMainEto follEto = new FollowupMainEto();
			DocumentInfoEbo docinfoByIdOrCode = docInfoService.getDocinfoByIdOrCode(prsnId, null);
			if (docinfoByIdOrCode != null) {
				follEto.setPrsnId(docinfoByIdOrCode.getPrsnId());

				if (staffId != 0) {
					follEto.setStafffId(staffId);
				} else {
					follEto.setStafffId(userEbo.getUid());
				}

				if (docinfoByIdOrCode.getOrgId() != 0) {
					follEto.setOrgId(docinfoByIdOrCode.getOrgId());
				}

				if (tableCode != null) {
					if (cfgHeadByCode != null) {
						follEto.setTbcfgId(cfgHeadByCode.getId());
					}
				}
				// 默认随访类型为正规随访
				if (flpeMiscType != null) {
					follEto.setFlpeMiscType(flpeMiscType);
				} else {
					follEto.setFlpeMiscType("F");
				}

				// 对应人群类型
				if (cfgHeadByCode.getPrsnTypeId() != 0) {
					follEto.setFlpeTypeId(cfgHeadByCode.getPrsnTypeId());
				}
				// 随访时间，默认当前时间
				follEto.setFlpeTime(DateUtil.getCurDayBeginInSec());
				// 随访日期，默认当前日期
				follEto.setFlpeDay(new Date());

			} else {
				throw new Error("对不起，您还没有建档，请您去建档");
			}

			if (followupid == 0) {
				FollowupMainEbo addFollowupMain = followupService.addFollowupMain(follEto);
				FollowupMainEbo follow = followupService.getFollowupMainByIdOrCode(0, addFollowupMain.getCode());
				dataHead.setMainId(follow.getId());
				dataHead.setMainType("FLW");
				// 保存
				tableService.addTableData(dataHead, dataLines);
				map.put("result", 1);
				map.put("message", "保存" + follow.getId() + "号随访记录成功！");
				map.put("activepage", "followup");
			} else {
				tableService.modifyTableData(followupid, "FLW", dataLines);
				FollowupMainEbo foEbo = followupService.getFollowupMainByIdOrCode(followupid, null);
				foEbo.setPrsnId(docinfoByIdOrCode.getPrsnId());
				foEbo.setStafffId(staffId);
				followupService.updateFollowupMain(foEbo);
				map.put("result", 1);
				map.put("message", "修改" + followupid + "号随访记录成功！");
				map.put("activepage", "followup");
			}
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	@RequestMapping(value = "/mgr_followuppagelist.html")
	public @ResponseBody
	Map<String, Object> infoListJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		int iDisplayStart = Util.parseNumVl(request.getParameter("iDisplayStart"), 0);
		int iDisplayLength = Util.parseNumVl(request.getParameter("iDisplayLength"), 10);
		int page = iDisplayStart / iDisplayLength;
		if (page <= 0)
			page = 0;

		try {
			List<TableCfgHeadDto> cfgHeadsDtos = tableService.listCfgHeadByTypeId(1068, page, iDisplayLength);
			List<List<Object>> aaData = new ArrayList<List<Object>>();
			List<Object> aaList = null;
			for (TableCfgHeadDto cfg : cfgHeadsDtos) {
				aaList = new ArrayList<Object>();
				aaList.add(cfg.getCfgId());
				aaList.add(cfg.getCfgCode());
				aaList.add(cfg.getCfgName());
				aaData.add(aaList);
			}
			map.put("data", aaData);
			map.put("result", 1);
			int totalRec = tableService.getTotalFollowupPage(1068);
			map.put("iTotalRecords", totalRec);
			map.put("iTotalDisplayRecords", totalRec);
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

}
