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
import com.kjplus.dao.ITableDao;
import com.kjplus.dto.FuncDto;
import com.kjplus.dto.KjAdminUserDto;
import com.kjplus.dto.TableCfgLineDto;
import com.kjplus.dto.TableDataLineDto;
import com.kjplus.eto.DocInfoEto;
import com.kjplus.eto.TableDataHeadEto;
import com.kjplus.eto.TableDataLineEto;
import com.kjplus.model.DocumentInfoEbo;
import com.kjplus.model.SysFuncEbo;
import com.kjplus.model.SysRefValueEbo;
import com.kjplus.model.TableDataHeadEbo;
import com.kjplus.service.IAdminUserService;
import com.kjplus.service.IDocInfoService;
import com.kjplus.service.IOrgService;
import com.kjplus.service.ISysBaseService;
import com.kjplus.service.ISysFuncService;
import com.kjplus.service.ITableService;
import com.kjplus.service.IUserService;
import com.ybk.exception.DataException;

@Controller
public class DocInfoPageMgrCtrl {

	private static Logger logger = Logger.getLogger(DocInfoPageMgrCtrl.class);

	@Autowired
	private IDocInfoService docInfoService;
	@Autowired
	private ISysFuncService funcService;
	@Autowired
	private IOrgService orgService;
	@Autowired
	private ISysBaseService baseService;
	@Autowired
	private IAdminUserService adminUserService;
	@Autowired
	private ITableService tableService;
	@Autowired
	private ITableDao tableDao;
	@Autowired
	private IUserService userService;

	// 新建档案
	@RequestMapping("/mgr_docinfonew.html")
	public ModelAndView docInfoNew(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = mav.getModel();
		mav.setViewName("docinfo_new");

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
		if (userEbo == null) {
			mav.setViewName(ConsolePageFtl.LOGIN_ACE);
			map.put("result", -1);
			map.put("returnUrl", "/mgr_docinfonew.html");
			map.put("message", "用户没有登陆!");
			return mav;
		}

		int docinfoid = Util.parseNumVl(request.getParameter("docinfoid"), 0);
		try {
			SysFuncEbo func = funcService.getFuncByMenu("/mgr_docinfonew.html", Constant.MENU_TYPE_MANAGER);
			if (func != null)
				map.put("pmenu", func.getPid());
			else
				map.put("pmenu", -1);
			List<FuncDto> funcs = funcService.listUserFunc(userEbo.getUid());
			map.put("funcs", funcs);

			// 获得配置
			List<TableCfgLineDto> cfgs = tableService.listTableLine(-1, ConstantTableCfg.TB_DOCINFO_R1001);
			Hashtable<String, TableCfgLineDto> cfgHash = new Hashtable<String, TableCfgLineDto>();
			for (TableCfgLineDto c : cfgs) {
				cfgHash.put(c.getLineCode(), c);
			}
			map.put("cfgHash", cfgHash);
			List<TableDataHeadEbo> heads = new ArrayList<TableDataHeadEbo>();
			if (docinfoid > 0)
				heads = tableService.listDataHeadByMainId(docinfoid, "DOC");
			Hashtable<String, TableDataLineDto> dataHash = new Hashtable<String, TableDataLineDto>();
			if (heads.size() > 0) {
				List<TableDataLineDto> lines = tableService.listDataLine(heads.get(0).getId());
				for (TableDataLineDto line : lines) {
					dataHash.put(line.getLineCode(), line);
				}
				DocumentInfoEbo docInfo = docInfoService.getDocinfoByIdOrCode(docinfoid, null);
				map.put("docInfo", docInfo);
				map.put("dochead", heads.get(0));
			}
			map.put("docinfoid", docinfoid);
			map.put("dataHash", dataHash);

			String prsnName = request.getParameter("prsnName");
			map.put("prsnName", prsnName);

			if (docinfoid > 0) {
				DocumentInfoEbo docInfo = docInfoService.getDocinfoByIdOrCode(docinfoid, null);
				map.put("docInfo", docInfo);
			}
			map.put("docinfoid", docinfoid);

		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return mav;
	}

	// 跳转档案表格
	@RequestMapping("/docinfopage.html")
	public ModelAndView docInfoPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = mav.getModel();
		mav.setViewName("docinfo_page");

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
		if (userEbo == null) {
			mav.setViewName(ConsolePageFtl.LOGIN_ACE);
			map.put("result", -1);
			map.put("returnUrl", "/docinfopage.html");
			map.put("message", "用户没有登陆!");
			return mav;
		}

		int docinfoid = Util.parseNumVl(request.getParameter("docinfoid"), 0);
		String activepage = request.getParameter("activepage");
		if (Util.isNull(activepage) || activepage.equals("undefined"))
			activepage = "";
		try {
			SysFuncEbo func = funcService.getFuncByMenu("docinfopage.html", Constant.MENU_TYPE_MANAGER);
			if (func != null)
				map.put("pmenu", func.getPid());
			else
				map.put("pmenu", -1);
			List<FuncDto> funcs = funcService.listUserFunc(userEbo.getUid());
			map.put("funcs", funcs);

			// 获得配置
			List<TableCfgLineDto> cfgs = tableService.listTableLine(-1, ConstantTableCfg.TB_DOCINFO_R1001);
			Hashtable<String, TableCfgLineDto> cfgHash = new Hashtable<String, TableCfgLineDto>();
			for (TableCfgLineDto c : cfgs) {
				cfgHash.put(c.getLineCode(), c);
			}
			map.put("cfgHash", cfgHash);
			List<TableDataHeadEbo> heads = new ArrayList<TableDataHeadEbo>();
			if (docinfoid > 0)
				heads = tableService.listDataHeadByMainId(docinfoid, "DOC");
			Hashtable<String, TableDataLineDto> dataHash = new Hashtable<String, TableDataLineDto>();
			if (heads.size() > 0) {
				List<TableDataLineDto> lines = tableService.listDataLine(heads.get(0).getId());
				for (TableDataLineDto line : lines) {
					dataHash.put(line.getLineCode(), line);
				}
				DocumentInfoEbo docInfo = docInfoService.getDocinfoByIdOrCode(docinfoid, null);
				map.put("docInfo", docInfo);
				map.put("dochead", heads.get(0));
			}
			map.put("docinfoid", docinfoid);
			map.put("dataHash", dataHash);

			String prsnName = request.getParameter("prsnName");
			map.put("prsnName", prsnName);

			if (docinfoid > 0) {
				DocumentInfoEbo docInfo = docInfoService.getDocinfoByIdOrCode(docinfoid, null);
				map.put("docInfo", docInfo);
			}
			map.put("docinfoid", docinfoid);
			map.put("activepage", activepage);

		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return mav;
	}

	// 配置所对应的所有参照
	@RequestMapping(value = "/tablecfgrefjson.html")
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
			String code = request.getParameter("code");
			if (Util.isNull(code))
				code = ConstantTableCfg.TB_DOCINFO_R1001;
			// 获得配置
			List<TableCfgLineDto> cfgs = tableService.listTableLine(code, true);
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
			map.put("message", "保存个人档案成功！");
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 保存档案
	@RequestMapping(value = "/saveormodifydocinfojson.html")
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

			int docinfoid = Util.parseNumVl(request.getParameter("docinfoid"), 0);
			String docinfojson = request.getParameter("docinfojson");
			System.out.println("页面" + docinfojson);
			JSONArray docinfoArr = JSONArray.parseArray(docinfojson);
			System.out.println("转换后" + docinfoArr);
			if (docinfoArr == null || docinfoArr.size() == 0) {
				map.put("result", -1);
				map.put("message", "空对象!");
				return map;
			}

			TableDataHeadEto dataHead = new TableDataHeadEto();
			dataHead.setCfgId(25);
			dataHead.setOrgId(userEbo.getOrgId());
			// TODO:获得和大夫的对应
			dataHead.setStaffId(9);
			dataHead.setUid(userEbo.getUid());
			Date date = new Date();
			dataHead.setSheetDay(date);
			dataHead.setBeginTime(DateUtil.getCurTimeInSec());
			dataHead.setEndTime(DateUtil.getCurTimeInSec());

			List<TableDataLineEto> dataLines = new ArrayList<TableDataLineEto>();
			JSONObject di = null;
			Hashtable<String, TableDataLineEto> lineHash = new Hashtable<String, TableDataLineEto>();
			for (int i = 0; i < docinfoArr.size(); i++) {
				di = docinfoArr.getJSONObject(i);
				TableDataLineEto dataLine = new TableDataLineEto();
				dataLine.setLineCode(di.getString("code"));
				dataLine.setRefId(Util.parseNumVl(di.getString("refid"), 0));
				dataLine.setInputVl(di.getString("vl"));
				dataLines.add(dataLine);
				lineHash.put(di.getString("code"), dataLine);
			}

			// 预先检查
			tableService.preCheckTableData(dataHead, dataLines);

			// TODO：完善
			String vl = null;
			int refid = 0;
			DocInfoEto docEto = new DocInfoEto();
			if (lineHash.containsKey(ConstantTableCfg.TBLN_DI_NAME_0))
				docEto.setPrsnName(lineHash.get(ConstantTableCfg.TBLN_DI_NAME_0).getInputVl());
			if (lineHash.containsKey(ConstantTableCfg.TBLN_DI_BIRTH_1_2)) {
				vl = lineHash.get(ConstantTableCfg.TBLN_DI_BIRTH_1_2).getInputVl();
				vl = Util.replaceAll(vl, "/", "-");
				docEto.setBirthday(vl);
			}
			docEto.setCreateDay(DateUtil.currDay());
			if (lineHash.containsKey(ConstantTableCfg.TBLN_DI_BLOODTYPE_5)) {
				refid = lineHash.get(ConstantTableCfg.TBLN_DI_BLOODTYPE_5).getRefId();
				SysRefValueEbo rvl = baseService.getRefVlById(refid);
				if (rvl != null)
					docEto.setBloodVl(rvl.getRefVl());
			}

			if (lineHash.containsKey(ConstantTableCfg.TBLN_DI_CONTACT_3_2))
				docEto.setContactNum(lineHash.get(ConstantTableCfg.TBLN_DI_CONTACT_3_2).getInputVl());

			if (lineHash.containsKey(ConstantTableCfg.TBLN_DI_CONTACT_3_2))
				docEto.setContactNum(lineHash.get(ConstantTableCfg.TBLN_DI_CONTACT_3_2).getInputVl());

			// TODO:暂时空
			docEto.setFamilyAddr("");
			// TODO:暂时空
			docEto.setHouseAddr("");
			if (lineHash.containsKey(ConstantTableCfg.TBLN_DI_IDCARD_2_1))
				docEto.setIdNumber(lineHash.get(ConstantTableCfg.TBLN_DI_IDCARD_2_1).getInputVl());

			if (lineHash.containsKey(ConstantTableCfg.TBLN_DI_MOBILR_3_1)) {
				docEto.setMobileNum(lineHash.get(ConstantTableCfg.TBLN_DI_MOBILR_3_1).getInputVl());
				// TODO:要区分开
				docEto.setPersonNum(lineHash.get(ConstantTableCfg.TBLN_DI_MOBILR_3_1).getInputVl());
			}
			if (lineHash.containsKey(ConstantTableCfg.TBLN_DI_NATION_4_2)) {
				refid = lineHash.get(ConstantTableCfg.TBLN_DI_NATION_4_2).getRefId();
				SysRefValueEbo rvl = baseService.getRefVlById(refid);
				if (rvl != null)
					docEto.setNationVl(rvl.getRefVl());
			}
			docEto.setCreatorId(userEbo.getUid());
			docEto.setOrgId(userEbo.getOrgId());

			if (lineHash.containsKey(ConstantTableCfg.TBLN_DI_SEX_1_1)) {
				refid = lineHash.get(ConstantTableCfg.TBLN_DI_SEX_1_1).getRefId();
				SysRefValueEbo rvl = baseService.getRefVlById(refid);
				if (rvl != null)
					docEto.setPrsnSex(rvl.getRefVl());
			}

			if (lineHash.containsKey(ConstantTableCfg.TBLN_DI_WORKUNIT_2_2))
				docEto.setWorkUnit(lineHash.get(ConstantTableCfg.TBLN_DI_WORKUNIT_2_2).getInputVl());
			DocumentInfoEbo doc = docInfoService.addDocInfo(docEto);

			if (docinfoid == 0) {
				dataHead.setMainId(doc.getPrsnId());
				dataHead.setMainType("DOC");
				// 保存
				tableService.addTableData(dataHead, dataLines);

				map.put("result", 1);
				map.put("message", "保存" + docEto.getPrsnName() + "的档案成功！");
			} else {
				tableService.modifyTableData(docinfoid, "DOC", dataLines);
				// TODO:修改docinfo service方法完善
				DocumentInfoEbo dInfoEbo = new DocumentInfoEbo();
				BeanUtils.copyProperties(docEto, dInfoEbo);
				dInfoEbo.setPrsnId(doc.getPrsnId());
				dInfoEbo.setCode(doc.getCode());
				docInfoService.updateDocInfo(dInfoEbo);

				map.put("result", 1);
				map.put("message", "修改" + docEto.getPrsnName() + "的档案成功！");
			}
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 档案详情
	@RequestMapping("/docinfopagedata.html")
	public @ResponseBody
	Map<String, Object> docInfoPageData(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
		if (userEbo == null) {
			map.put("result", -1);
			map.put("returnUrl", "/docinfopage.html");
			map.put("message", "用户没有登陆!");
			return map;
		}

		int docinfoid = Util.parseNumVl(request.getParameter("docinfoid"), 0);
		try {

			List<TableDataHeadEbo> heads = new ArrayList<TableDataHeadEbo>();
			if (docinfoid > 0)
				heads = tableService.listDataHeadByMainId(docinfoid, "DOC");
			Hashtable<String, TableDataLineDto> dataHash = new Hashtable<String, TableDataLineDto>();
			if (heads.size() > 0) {
				List<TableDataLineDto> lines = tableService.listDataLine(heads.get(0).getId());
				for (TableDataLineDto line : lines) {
					dataHash.put(line.getLineCode(), line);
				}
				DocumentInfoEbo docInfo = docInfoService.getDocinfoByIdOrCode(docinfoid, null);
				map.put("docInfo", docInfo);
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
