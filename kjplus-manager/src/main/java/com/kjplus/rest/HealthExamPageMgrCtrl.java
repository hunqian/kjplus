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
import org.springframework.beans.BeanUtils;
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
import com.kjplus.dto.TableCfgLineDto;
import com.kjplus.dto.TableDataLineDto;
import com.kjplus.eto.HealthExamEto;
import com.kjplus.eto.TableDataHeadEto;
import com.kjplus.eto.TableDataLineEto;
import com.kjplus.model.DocumentInfoEbo;
import com.kjplus.model.HealthExamEbo;
import com.kjplus.model.SysFuncEbo;
import com.kjplus.model.SysRefValueEbo;
import com.kjplus.model.TableDataHeadEbo;
import com.kjplus.service.IDocInfoService;
import com.kjplus.service.IHealthExamService;
import com.kjplus.service.IStaffService;
import com.kjplus.service.ISysBaseService;
import com.kjplus.service.ISysFuncService;
import com.kjplus.service.ITableService;
import com.ybk.exception.DataException;

@Controller
public class HealthExamPageMgrCtrl {

	private static Logger logger = Logger.getLogger(HealthExamPageMgrCtrl.class);

	@Autowired
	private IHealthExamService healthExamService;
	@Autowired
	private ISysFuncService funcService;
	@Autowired
	private ISysBaseService baseService;
	@Autowired
	private ITableService tableService;
	@Autowired
	private IDocInfoService docInfoService;
	@Autowired
	private IStaffService staffService;

	// 健康体检详情
	@RequestMapping("/healthexampage.html")
	public ModelAndView docInfoPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = mav.getModel();
		mav.setViewName("healthexam_page");

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
		if (userEbo == null) {
			mav.setViewName(ConsolePageFtl.LOGIN_ACE);
			map.put("result", -1);
			map.put("returnUrl", "/healthexampage.html");
			map.put("message", "用户没有登陆!");
			return mav;
		}

		try {
			int healthId = Util.parseNumVl(request.getParameter("healthId"), 0);
			SysFuncEbo func = funcService.getFuncByMenu("healthexampage.html", Constant.MENU_TYPE_MANAGER);
			if (func != null)
				map.put("pmenu", func.getPid());
			else
				map.put("pmenu", -1);
			List<FuncDto> funcs = funcService.listUserFunc(userEbo.getUid());
			map.put("funcs", funcs);

			List<StaffDto> listStaffDto = staffService.listStaffDto(userEbo.getOrgId(), -1,null,null,null, 0, 10);
			map.put("listStaffDto", listStaffDto);
			map.put("healthId", healthId);

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
	@RequestMapping(value = "/healthtablecfgrefjson.html")
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

			String tableCode = ConstantTableCfg.TB_HEALTHEXAM_HE;
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
			map.put("message", "获取体检表格配置成功！");
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 保存档案
	@RequestMapping(value = "/saveormodifyhealthexamjson.html")
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

			int healthId = Util.parseNumVl(request.getParameter("healthId"), 0);
			int prsnId = Util.parseNumVl(request.getParameter("prsnId"), 0);
			int staffId = Util.parseNumVl(request.getParameter("staffId"), 0);
			String healthExamJson = request.getParameter("healthExamJson");
			System.out.println("页面" + healthExamJson);
			JSONArray healthExamArr = JSONArray.parseArray(healthExamJson);
			System.out.println("转换后" + healthExamArr);
			if (healthExamArr == null || healthExamArr.size() == 0) {
				map.put("result", -1);
				map.put("message", "空对象!");
				return map;
			}

			TableDataHeadEto dataHead = new TableDataHeadEto();
			dataHead.setCfgId(26);
			dataHead.setOrgId(userEbo.getOrgId());
			dataHead.setStaffId(9);
			dataHead.setUid(prsnId);
			Date date = new Date();
			dataHead.setSheetDay(date);
			dataHead.setBeginTime(DateUtil.getCurTimeInSec());
			dataHead.setEndTime(DateUtil.getCurTimeInSec());

			List<TableDataLineEto> dataLines = new ArrayList<TableDataLineEto>();
			JSONObject di = null;
			Hashtable<String, TableDataLineEto> lineHash = new Hashtable<String, TableDataLineEto>();
			for (int i = 0; i < healthExamArr.size(); i++) {
				di = healthExamArr.getJSONObject(i);
				TableDataLineEto dataLine = new TableDataLineEto();
				dataLine.setLineCode(di.getString("code"));
				dataLine.setRefId(Util.parseNumVl(di.getString("refid"), 0));
				dataLine.setInputVl(di.getString("vl"));
				dataLines.add(dataLine);
				lineHash.put(di.getString("code"), dataLine);
			}

			// 预先检查
			tableService.preCheckTableData(dataHead, dataLines);

			int refid = 0;
			HealthExamEto healthExamEto = new HealthExamEto();
			if (prsnId != 0) {
				healthExamEto.setPrsnId(prsnId);
			}
			if (staffId != 0) {
				healthExamEto.setStaffId(staffId);
			}
			healthExamEto.setTbcfgId(26);
			// 体温
			if (lineHash.containsKey(ConstantTableCfg.TBLN_HE_TEMP_2_1))
				healthExamEto.setTemperature(Double.parseDouble(lineHash.get(ConstantTableCfg.TBLN_HE_TEMP_2_1).getInputVl()));
			// 呼吸频率
			if (lineHash.containsKey(ConstantTableCfg.TBLN_HE_BR_2_2))
				healthExamEto.setRespiratoryFrequency(Integer.parseInt(lineHash.get(ConstantTableCfg.TBLN_HE_BR_2_2).getInputVl()));

			// 脉率
			if (lineHash.containsKey(ConstantTableCfg.TBLN_HE_PR_2_1_1))
				healthExamEto.setPulseRate(Integer.parseInt(lineHash.get(ConstantTableCfg.TBLN_HE_PR_2_1_1).getInputVl()));

			// 左侧高压
			if (lineHash.containsKey(ConstantTableCfg.TBLN_HE_LFH_BP_2_2_2_1_1))
				healthExamEto.setRespiratoryFrequency(Integer.parseInt(lineHash.get(ConstantTableCfg.TBLN_HE_LFH_BP_2_2_2_1_1).getInputVl()));

			// 左侧低压
			if (lineHash.containsKey(ConstantTableCfg.TBLN_HE_LFL_BP_2_2_2_1_2))
				healthExamEto.setRespiratoryFrequency(Integer.parseInt(lineHash.get(ConstantTableCfg.TBLN_HE_LFL_BP_2_2_2_1_2).getInputVl()));

			// 右侧高压
			if (lineHash.containsKey(ConstantTableCfg.TBLN_HE_RIH_BP_2_2_2_2_1))
				healthExamEto.setRespiratoryFrequency(Integer.parseInt(lineHash.get(ConstantTableCfg.TBLN_HE_RIH_BP_2_2_2_2_1).getInputVl()));

			// 右侧低压
			if (lineHash.containsKey(ConstantTableCfg.TBLN_HE_RIL_BP_2_2_2_2_2))
				healthExamEto.setRespiratoryFrequency(Integer.parseInt(lineHash.get(ConstantTableCfg.TBLN_HE_RIL_BP_2_2_2_2_2).getInputVl()));

			// 身高
			if (lineHash.containsKey(ConstantTableCfg.TBLN_HE_STTR_2_3))
				healthExamEto.setHeight(Double.parseDouble(lineHash.get(ConstantTableCfg.TBLN_HE_STTR_2_3).getInputVl()));

			// 体重
			if (lineHash.containsKey(ConstantTableCfg.TBLN_HE_WGHT_2_3_3))
				healthExamEto.setTemperature(Double.parseDouble(lineHash.get(ConstantTableCfg.TBLN_HE_WGHT_2_3_3).getInputVl()));

			// 腰围
			if (lineHash.containsKey(ConstantTableCfg.TBLN_HE_WGHT_2_3_3))
				healthExamEto.setTemperature(Double.parseDouble(lineHash.get(ConstantTableCfg.TBLN_HE_WGHT_2_3_3).getInputVl()));

			// 体质指数
			if (lineHash.containsKey(ConstantTableCfg.TBLN_HE_CI_2_4_4))
				healthExamEto.setTemperature(Double.parseDouble(lineHash.get(ConstantTableCfg.TBLN_HE_CI_2_4_4).getInputVl()));

			healthExamEto.setFlag("Y");

			healthExamEto.setCreateTime(new Date());

			if (lineHash.containsKey(ConstantTableCfg.TBLN_HE_HG_12)) {
				refid = lineHash.get(ConstantTableCfg.TBLN_HE_HG_12).getRefId();
				SysRefValueEbo rvl = baseService.getRefVlById(refid);
				if (rvl != null)
					healthExamEto.setHealthMemo(rvl.getRefVl());
			}

			HealthExamEbo addHealthExam = healthExamService.addHealthExam(healthExamEto);

			if (healthId == 0) {
				dataHead.setMainId(addHealthExam.getId());
				dataHead.setMainType("HE");
				// 保存
				tableService.addTableData(dataHead, dataLines);

				DocumentInfoEbo doc = docInfoService.getDocinfoByIdOrCode(prsnId, null);
				String prsnName = doc.getPrsnName();

				map.put("result", 1);
				map.put("message", "保存" + prsnName + "的体检信息成功！");
			} else {
				tableService.modifyTableData(healthId, "HE", dataLines);
				// TODO:修改docinfo service方法完善
				HealthExamEbo hExamEbo = new HealthExamEbo();
				BeanUtils.copyProperties(healthExamEto, hExamEbo);

				HealthExamEbo health = healthExamService.getHealthExamByIdOrCode(healthId, null);
				hExamEbo.setCode(health.getCode());

				DocumentInfoEbo doc = docInfoService.getDocinfoByIdOrCode(prsnId, null);
				String prsnName = doc.getPrsnName();

				map.put("result", 1);
				map.put("message", "修改" + prsnName + "的档案成功！");
			}
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 档案详情
	@RequestMapping("/healthexampagedata.html")
	public @ResponseBody
	Map<String, Object> docInfoPageData(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
		if (userEbo == null) {
			map.put("result", -1);
			map.put("returnUrl", "/healthexampagedata.html");
			map.put("message", "用户没有登陆!");
			return map;
		}

		int healthId = Util.parseNumVl(request.getParameter("healthId"), 0);
		try {

			List<TableDataHeadEbo> heads = new ArrayList<TableDataHeadEbo>();
			if (healthId > 0)
				heads = tableService.listDataHeadByMainId(healthId, "HE");
			Hashtable<String, TableDataLineDto> dataHash = new Hashtable<String, TableDataLineDto>();
			if (heads.size() > 0) {
				List<TableDataLineDto> lines = tableService.listDataLine(heads.get(0).getId());
				for (TableDataLineDto line : lines) {
					dataHash.put(line.getLineCode(), line);
				}
				HealthExamEbo healthInfo = healthExamService.getHealthExamByIdOrCode(healthId, null);
				map.put("healthInfo", healthInfo);
				map.put("dochead", heads.get(0));
			}
			map.put("dataHash", dataHash);

		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

}
