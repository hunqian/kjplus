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
import com.kjplus.constant.Constant;
import com.kjplus.constant.ConstantPageFtl;
import com.kjplus.constant.ConstantTableCfg;
import com.kjplus.constant.ConstantUrlWx;
import com.kjplus.constant.ShareConstant;
import com.kjplus.dto.TableCfgLineDto;
import com.kjplus.dto.TableDataLineDto;
import com.kjplus.dto.UserSess;
import com.kjplus.eto.DocInfoEto;
import com.kjplus.eto.TableDataHeadEto;
import com.kjplus.eto.TableDataLineEto;
import com.kjplus.model.DocumentInfoEbo;
import com.kjplus.model.SysRefValueEbo;
import com.kjplus.model.TableCfgHeadEbo;
import com.kjplus.model.TableDataHeadEbo;
import com.kjplus.service.IDocInfoService;
import com.kjplus.service.ISysBaseService;
import com.kjplus.service.ITableService;
import com.kjplus.service.IUserService;
import com.ybk.exception.DataException;

@Controller
public class CreateDocCtrl {

	private static Logger logger = Logger.getLogger(CreateDocCtrl.class);

	@Autowired
	private ITableService tableService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IDocInfoService docInfoService;
	@Autowired
	private ISysBaseService baseService;

	// 配置建档表格的基本信息和详细信息 获取具体数据
	@RequestMapping(value = ConstantUrlWx.WX_CREATE_DOCUMENT)
	public ModelAndView createDocument(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(ConstantPageFtl.WX_CREATE_DOCUMENT);
		Map<String, Object> map = mav.getModelMap();
		map.put("code", 200);

		UserSess userEbo = (UserSess) request.getSession().getAttribute(Constant.KJMB_SESS);
		if (userEbo == null) {
			return new ModelAndView("redirect:" + ConstantUrlWx.WX_MBLOGIN + "?returnUrl="
					+ ConstantUrlWx.WX_CREATE_DOCUMENT);
		}

		// int docinfoid = Util.parseNumVl(request.getParameter("docinfoid"),
		// 0);
		try {
			// 获取建档列的配置
			List<TableCfgLineDto> cfgs = tableService.listTableLine(-1, ConstantTableCfg.TB_DOCINFO_R1001);
			Hashtable<String, TableCfgLineDto> cfgHash = new Hashtable<String, TableCfgLineDto>();
			for (TableCfgLineDto c : cfgs) {
				cfgHash.put(c.getLineCode(), c);
			}
			// 获取建档表格列配置
			map.put("cfgHash", cfgHash);
			/*
			 * List<TableDataHeadEbo> heads = new ArrayList<TableDataHeadEbo>();
			 * if( docinfoid > 0 ) heads =
			 * tableService.listDataHeadByMainId(docinfoid, "DOC");
			 * Hashtable<String, TableDataLineDto> dataHash = new
			 * Hashtable<String, TableDataLineDto>(); if(heads.size() > 0){
			 * List<TableDataLineDto> lines =
			 * tableService.listDataLine(heads.get(0).getId());
			 * for(TableDataLineDto line: lines){
			 * dataHash.put(line.getLineCode(), line); } DocumentInfoEbo docInfo
			 * = docInfoService.getDocinfoByIdOrCode(docinfoid, null);
			 * map.put("docInfo", docInfo); map.put("dochead", heads.get(0)); }
			 * map.put("dataHash", dataHash);
			 */
			map.put("result", 1);
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

		try {

			String code = request.getParameter("code");
			if (Util.isNull(code))
				code = "R1001";
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
			//TODO 全部表格配置
			map.put("data", cfgHash2);//新建
			map.put("alldata", cfgHash2);//数据回显
			// 有引用的表格配置
			map.put("data2", cfgHash);
			map.put("result", 1);
			map.put("message", "获取参照成功！");
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 添加档案(TODO 暂定修改)
	@RequestMapping(value = "/saveormodifydocinfojson.html")
	public @ResponseBody
	Map<String, Object> saveOrModifyDocInfoJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		UserSess userEbo = (UserSess) request.getSession().getAttribute(Constant.KJMB_SESS);

		try {

			// [{"code":"0","vl":"微信用户"},{"code":"1_1","refid":"161","vl":""},{"code":"12_1","vl":"","refid":"218"}
			// ,{"code":"12_1","vl":"","refid":"219"},{"code":"12_1","vl":"","refid":"220"},{"code":"12_1","vl":"","refid":"221"}]
			// 获取表格列数据信息
			String docinfojson = request.getParameter("docinfojson");
			JSONArray docinfoArr = JSONArray.parseArray(docinfojson);
			if (docinfoArr == null || docinfoArr.size() == 0) {
				map.put("result", -1);
				map.put("message", "空对象!");
				return map;
			}

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

			// 获取表头信息
			TableDataHeadEto dataHead = new TableDataHeadEto();
			TableCfgHeadEbo cfgHead = tableService.getCfgHeadByCode(ConstantTableCfg.TB_DOCINFO_R1001);
			dataHead.setCfgId(cfgHead.getId());
			dataHead.setOrgId(userEbo.getOrgId());
			// 用户自己建立档案 默认 staffId= 0
			dataHead.setStaffId(0);
			// 建档人 微信用户id
			dataHead.setUid(userEbo.getUid());
			Date date = new Date();
			dataHead.setSheetDay(date);
			dataHead.setBeginTime(DateUtil.getCurTimeInSec());
			dataHead.setEndTime(DateUtil.getCurTimeInSec());

			String idNumber = "";
			// 个人身份证号
			if (lineHash.containsKey(ConstantTableCfg.TBLN_DI_IDCARD_2_1)
					&& Util.isNotNull(lineHash.get(ConstantTableCfg.TBLN_DI_IDCARD_2_1).getInputVl()))
				idNumber = lineHash.get(ConstantTableCfg.TBLN_DI_IDCARD_2_1).getInputVl();
			else {
				map.put("result", -1);
				map.put("message", "添加档案时，身份证号不能为空！");
				return map;
			}

			List<DocumentInfoEbo> dis = docInfoService.listDocInfo(userEbo.getOrgId(), null, idNumber);
			if (dis.size() > 0) {
				map.put("result", ShareConstant.RES_ERROR);
				map.put("message", "该身份证号已经建档,IdNumber = " + idNumber);
				return map;
			}

			// 预先检查 表头与表列信息
			tableService.preCheckTableData(dataHead, dataLines);

			// TODO：完善 添加档案
			String vl = null;
			int refid = 0;
			DocInfoEto docEto = new DocInfoEto();
			// PersonEto prsnEto = new PersonEto();
			// 档案用户名
			if (lineHash.containsKey(ConstantTableCfg.TBLN_DI_NAME_0))
				docEto.setPrsnName(lineHash.get(ConstantTableCfg.TBLN_DI_NAME_0).getInputVl());
			// 生日
			if (lineHash.containsKey(ConstantTableCfg.TBLN_DI_BIRTH_1_2)) {
				vl = lineHash.get(ConstantTableCfg.TBLN_DI_BIRTH_1_2).getInputVl();
				vl = Util.replaceAll(vl, "/", "-");
				docEto.setBirthday(vl);
			}

			// 血型
			if (lineHash.containsKey(ConstantTableCfg.TBLN_DI_BLOODTYPE_5)) {
				refid = lineHash.get(ConstantTableCfg.TBLN_DI_BLOODTYPE_5).getRefId();
				SysRefValueEbo rvl = baseService.getRefVlById(refid);
				if (rvl != null)
					docEto.setBloodVl(rvl.getRefVl());
			}
			// 联系人姓名
			if (lineHash.containsKey(ConstantTableCfg.TBLN_DI_CONTACT_3_2))
				docEto.setContactName(lineHash.get(ConstantTableCfg.TBLN_DI_CONTACT_3_2).getInputVl());
			// 联系人联系方式
			if (lineHash.containsKey(ConstantTableCfg.TBLN_DI_PHONE_3_3))
				docEto.setContactNum(lineHash.get(ConstantTableCfg.TBLN_DI_PHONE_3_3).getInputVl());

			if (lineHash.containsKey(ConstantTableCfg.TBLN_DI_FAMLILY_0_1)) {
				// 家庭住址
				docEto.setFamilyAddr(lineHash.get(ConstantTableCfg.TBLN_DI_FAMLILY_0_1).getInputVl());
				// 房子住址
				docEto.setHouseAddr(lineHash.get(ConstantTableCfg.TBLN_DI_FAMLILY_0_1).getInputVl());
			}
			// 个人身份证号
			docEto.setIdNumber(idNumber);
			if (lineHash.containsKey(ConstantTableCfg.TBLN_DI_MOBILR_3_1)) {
				// 移动电话
				docEto.setMobileNum(lineHash.get(ConstantTableCfg.TBLN_DI_MOBILR_3_1).getInputVl());
				// 固定电话
				docEto.setPersonNum(lineHash.get(ConstantTableCfg.TBLN_DI_MOBILR_3_1).getInputVl());
			}
			// 名族
			if (lineHash.containsKey(ConstantTableCfg.TBLN_DI_NATION_4_2)) {
				refid = lineHash.get(ConstantTableCfg.TBLN_DI_NATION_4_2).getRefId();
				SysRefValueEbo rvl = baseService.getRefVlById(refid);
				if (rvl != null)
					docEto.setNationVl(rvl.getRefVl());
			}
			// TODO　创建Id
			docEto.setCreatorId(userEbo.getUid());
			docEto.setOrgId(userEbo.getOrgId());
			// 性别
			if (lineHash.containsKey(ConstantTableCfg.TBLN_DI_SEX_1_1)) {
				refid = lineHash.get(ConstantTableCfg.TBLN_DI_SEX_1_1).getRefId();
				SysRefValueEbo rvl = baseService.getRefVlById(refid);
				if (rvl != null)
					docEto.setPrsnSex(rvl.getRefVl());
			}
			// 工作单位
			if (lineHash.containsKey(ConstantTableCfg.TBLN_DI_WORKUNIT_2_2))
				docEto.setWorkUnit(lineHash.get(ConstantTableCfg.TBLN_DI_WORKUNIT_2_2).getInputVl());
			//公众号端建档默认为未审核状态
			docEto.setStatus(Constant.FLAG_NO);
			// 添加档案
			DocumentInfoEbo doc = docInfoService.addDocInfo(docEto);

			if (doc.getPrsnId() == 0) {
				map.put("result", -1);
				map.put("message", "保存档案失败！");
				return map;
			}

			// 建立关系 用户与档案用户的关系
			userService.addUserPersonRel(userEbo.getUid(), "U", doc.getPrsnId());
			// 获取表头数据
			dataHead.setMainId(doc.getPrsnId());
			dataHead.setMainType("DOC");
			// 保存 表头与表列信息
			boolean isAdd = tableService.addTableData(dataHead, dataLines);
			if(isAdd){
				map.put("result", 1);
				map.put("message", "保存" + docEto.getPrsnName() + "的档案成功！");
			}else{
				map.put("result", 1);
				map.put("message", "保存" + docEto.getPrsnName() + "的档案失败！！！！");
			}
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	//档案详情页面条状
	@RequestMapping("/page_document.html")
	public ModelAndView pageDocument(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("page_document");
		Map<String, Object> map = mav.getModelMap();
		map.put("code", 200);
		
		UserSess userEbo = (UserSess) request.getSession().getAttribute(Constant.KJMB_SESS);
		if (userEbo == null) {
			return new ModelAndView("redirect:" + ConstantUrlWx.WX_MBLOGIN + "?returnUrl="
					+ "/myfamily.html");
		}

		String prsnCode = request.getParameter("prsnCode");
		try {
			map.put("prsnCode", prsnCode);
			map.put("result", 1);
			map.put("message", "获取个人档案详情成功！");
		} catch (Exception e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return mav;
	}	

	//查看档案详情
	@RequestMapping("/get_document_json.html")
	public @ResponseBody
	Map<String,Object> getDocumentJson(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);
		
		String prsnCode = request.getParameter("prsnCode");
		try {
			int prsnId = 0;
			DocumentInfoEbo doc = docInfoService.getDocinfoByIdOrCode(0, prsnCode);
			if(doc!=null)
				prsnId = doc.getPrsnId();
		
			List<TableDataHeadEbo> heads = new ArrayList<TableDataHeadEbo>();
			if (prsnId > 0)
				heads = tableService.listDataHeadByMainId(prsnId, "DOC");
			Hashtable<String, TableDataLineDto> dataHash = new Hashtable<String, TableDataLineDto>();
			if (heads.size() > 0) {
				List<TableDataLineDto> lines = tableService.listDataLine(heads.get(0).getId());
				for (TableDataLineDto line : lines) {
					dataHash.put(line.getLineCode(), line);
				}
				DocumentInfoEbo docInfo = docInfoService.getDocinfoByIdOrCode(prsnId, null);
				map.put("docInfo", docInfo);
				map.put("dochead", heads.get(0));
			}
			map.put("dataHash", dataHash);
			map.put("result", 1);			
			map.put("message", "获取个人档案详情成功！");
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}
}
