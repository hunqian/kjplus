package com.kjplus.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ybk.basic.util.DateUtil;
import org.ybk.basic.util.Util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kjplus.constant.Constant;
import com.kjplus.constant.ConstantTableCfg;
import com.kjplus.constant.ShareConstant;
import com.kjplus.dto.TagDto;
import com.kjplus.eto.DocInfoEto;
import com.kjplus.eto.TableDataHeadEto;
import com.kjplus.eto.TableDataLineEto;
import com.kjplus.model.AdminUserEbo;
import com.kjplus.model.DocumentInfoEbo;
import com.kjplus.model.SysRefValueEbo;
import com.kjplus.model.TableCfgHeadEbo;
import com.kjplus.model.TagEbo;
import com.kjplus.model.inner.DocumentInfoInnerEbo;
import com.kjplus.service.IAdminUserService;
import com.kjplus.service.IDocInfoService;
import com.kjplus.service.IOrgService;
import com.kjplus.service.ISysBaseService;
import com.kjplus.service.ITableService;
import com.kjplus.service.ITagService;
import com.kjplus.service.IUserMapService;
import com.ybk.exception.DataException;

@Api(tags = "档案", description = "关于档案的接口")
@Controller
public class DocInfoCtrl {

	private static Logger logger = Logger.getLogger(DocInfoCtrl.class);

	@Autowired
	private IDocInfoService docInfoService;
	@Autowired
	private IOrgService orgService;
	@Autowired
	private ISysBaseService baseService;
	@Autowired
	private IAdminUserService adminUserService;
	@Autowired
	private ITableService tableService;
	@Autowired
	private ITagService tagService;
	@Autowired
	private IUserMapService userMapService;

	// 档案列表
	@ApiOperation(value = "档案列表")
	@ApiImplicitParams({ @ApiImplicitParam(name = "page", value = "分页", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "paging", value = "分页", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "name", value = "名称", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "flag", value = "是否有效", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "idCard", value = "身份证", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "tagids", value = "tagids", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = Constant.USER_CHECK_URI + "/listdocinfo.html", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> adminUserListJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		AdminUserEbo userEbo = (AdminUserEbo) request.getSession().getAttribute(Constant.KJAPI_SESS);
		if (userEbo == null) {
			map.put("result", ShareConstant.RES_ERROR);
			map.put("message", "用户没有登录！");
			return map;
		}

		int page = Util.parseNumVl(request.getParameter("page"), 0);
		int paging = Util.parseNumVl(request.getParameter("paging"), 10);

		try {
			String name = request.getParameter("name");
			// int orgid = RestContant.getOrgId();
			int orgId = userEbo.getOrgid();
			/*
			 * if(orgId == 0){ map.put("result", -1); map.put("message",
			 * "获取档案时组织ID不能为空!"); return map; }
			 */
			String flag = request.getParameter("flag");
			String idCard = request.getParameter("idcard");
			String strTagIds = request.getParameter("tagids");
			List<Integer> tagIds = new ArrayList<Integer>();
			if (Util.isNotNull(strTagIds)) {
				String[] arrTags = strTagIds.split(",");
				for (int i = 0; i < arrTags.length; i++) {
					int tagid = Util.parseNumVl(arrTags[i], 0);
					if (tagid != 0)
						tagIds.add(tagid);
				}
			}
			List<DocumentInfoInnerEbo> docList =new ArrayList<DocumentInfoInnerEbo>(); //docInfoService.listDocInfoInner(orgId, name, null, flag, idCard, tagIds, true, page, paging);
			int totalDoc = 0;//docInfoService.getTotalDocInfoInner(orgId, name, null, flag, idCard, null);
			List<Map<String, Object>> docsData = new ArrayList<Map<String, Object>>();
			for (DocumentInfoInnerEbo doc : docList) {

				Map<String, Object> dl = new HashMap<String, Object>();
				dl.put("prsnId", doc.getPrsnId());
				dl.put("code", doc.getCode());
				dl.put("prsnCode", doc.getPrsnCode());
				dl.put("prsnName", doc.getPrsnName());
				SysRefValueEbo baseSex = baseService.getRefVlByCode(doc.getPrsnSex());
				if (baseSex != null)
					dl.put("prsnSex", baseSex.getName());
				else
					dl.put("prsnSex", "未说明");
				dl.put("idNumber", doc.getIdNumber());
				dl.put("birthday", DateUtil.formatDate(doc.getBirthday()));
				// int age =
				// DateUtil.getAgeFromBirthTime(DateUtil.formatDate(doc.getBirthday()));
				dl.put("age", 0.0);
				dl.put("mobileNum", doc.getMobileNum());
				dl.put("personNum", doc.getPersonNum());
				dl.put("workUnit", Util.val(doc.getWorkUnit()));
				SysRefValueEbo baseBlood = baseService.getRefVlByCode(doc.getBloodVl());
				if (baseBlood != null)
					dl.put("blood", baseBlood.getName());
				else
					dl.put("blood", "未说明");
				SysRefValueEbo baseNation = baseService.getRefVlByCode(doc.getNationVl());
				if (baseNation != null)
					dl.put("nation", baseNation.getName());
				else
					dl.put("nation", "未说明");
				dl.put("contactName", Util.val(doc.getContactName()));
				dl.put("contactNum", Util.val(doc.getContactNum()));
				dl.put("familyAddr", Util.val(doc.getFamilyAddr()));
				dl.put("houseAddr", Util.val(doc.getHouseAddr()));
				dl.put("orgId", doc.getOrgId());
				dl.put("orgName", doc.getOrgName());
				dl.put("creatorName", doc.getCreatorName());
				dl.put("creatorId", doc.getCreatorId());
				dl.put("flag", doc.getFlag());
				dl.put("createTime", DateUtil.formatDateTime(doc.getCreateTime()));
				dl.put("createDay", DateUtil.formatDate(doc.getCreateDay()));
				dl.put("tag", doc.getTags());
				if (doc.getTags().size() == 0)
					dl.put("tagstr", "");
				else {
					List<TagDto> tags = doc.getTags();
					StringBuffer buf = new StringBuffer();
					for (int i = 0; i < tags.size(); i++) {
						if (i != 0)
							buf.append(",");
						buf.append(tags.get(i).getRefValName());
					}
					dl.put("tagstr", buf.toString());
				}
				docsData.add(dl);
			}
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("total", totalDoc);
			data.put("docs", docsData);
			map.put("data", data);
			map.put("result", ShareConstant.RES_OK);
			map.put("message", "成功获得个人档案列表!");
		} catch (DataException e) {
			logger.error(e);
			map.put("result", ShareConstant.RES_ERROR);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 保存档案
	@ApiOperation(value = "保存档案")
	@ApiImplicitParams({ @ApiImplicitParam(name = "docinfojson", value = "档案json", required = true, dataType = "String", paramType = "body") })
	@RequestMapping(value = Constant.USER_CHECK_URI + "/savedocinfo.html", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> saveJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		AdminUserEbo userEbo = (AdminUserEbo) request.getSession().getAttribute(Constant.KJAPI_SESS);
		if (userEbo == null) {
			map.put("result", ShareConstant.RES_ERROR);
			map.put("message", "用户没有登录！");
			return map;
		}

		try {

			String docinfojson = request.getParameter("docinfojson");
			// 数组eg
			// [{"code":"1207","refid":"","vl":"的撒旦"},{"code":"1_1","refid":"159","vl":""}]
			System.out.println("页面" + docinfojson);
			JSONArray docinfoArr = JSONArray.parseArray(docinfojson);
			System.out.println("转换后" + docinfoArr);
			if (docinfoArr == null || docinfoArr.size() == 0) {
				map.put("result", ShareConstant.RES_ERROR);
				map.put("message", "空对象!");
				return map;
			}

			// 对列信息进行解析
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
			String idNumber = "";
			// 预先检查该用户是否已经建档 建档时身份证号不能为空
			if (lineHash.containsKey(ConstantTableCfg.TBLN_DI_IDCARD_2_1) && Util.isNotNull(lineHash.get(ConstantTableCfg.TBLN_DI_IDCARD_2_1).getInputVl()))
				idNumber = lineHash.get(ConstantTableCfg.TBLN_DI_IDCARD_2_1).getInputVl();
			else {
				map.put("result", ShareConstant.RES_ERROR);
				map.put("message", "保存个人档案成功时，身份证号不能为空");
				return map;
			}

			List<DocumentInfoEbo> dis = docInfoService.listDocInfo(userEbo.getOrgid(), null, idNumber);
			if (dis.size() > 0) {
				map.put("result", ShareConstant.RES_ERROR);
				map.put("message", "该身份证号已经建档,IdNumber = " + idNumber);
				return map;
			}

			TableDataHeadEto dataHead = new TableDataHeadEto();
			TableCfgHeadEbo cfgHead = tableService.getCfgHeadByCode(ConstantTableCfg.TB_DOCINFO_R1001);
			dataHead.setCfgId(cfgHead.getId());
			dataHead.setOrgId(userEbo.getOrgid());
			dataHead.setStaffId(userMapService.getStaffIdByAdminUserId(userEbo.getUid()));
			dataHead.setUid(RestContant.getUid());
			Date date = new Date();
			dataHead.setSheetDay(date);
			dataHead.setBeginTime(DateUtil.getCurTimeInSec());
			dataHead.setEndTime(DateUtil.getCurTimeInSec());

			// 预先检查
			tableService.preCheckTableData(dataHead, dataLines);

			// 添加档案
			String vl = null;
			int refid = 0;
			DocInfoEto docEto = new DocInfoEto();
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
			// 创建Id
			docEto.setCreatorId(userEbo.getUid());
			docEto.setOrgId(userEbo.getOrgid());
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
			// 添加档案
			DocumentInfoEbo doc = docInfoService.addDocInfo(docEto);

			if (doc.getPrsnId() == 0) {
				map.put("result", -1);
				map.put("message", "保存档案失败！");
				return map;
			}

			// 建立关系 用户与档案用户的关系 manager端处理
			// 获取表头数据
			dataHead.setMainId(doc.getPrsnId());
			dataHead.setMainType("DOC");
			// 保存 表头与表列信息
			boolean isAdd = tableService.addTableData(dataHead, dataLines);
			if (isAdd) {
				map.put("result", 1);
				map.put("message", "保存" + docEto.getPrsnName() + "的档案成功！");
			} else {
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

	// 获取用户标签列表
	@ApiOperation(value = "获取用户标签列表")
	@ApiImplicitParams({ @ApiImplicitParam(name = "mainid", value = "业务ID", required = true, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "maintype", value = "业务类型", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = Constant.USER_CHECK_URI + "/listtag.html", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> listTag(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		int mainId = Util.parseNumVl(request.getParameter("mainid"), 0);
		String mainType = request.getParameter("maintype");
		if (Util.isNull(mainType))
			mainType = "P";// 代表居民
		try {
			List<TagEbo> tags = tagService.listTag(mainId, mainType);
			map.put("tags", tags);
			map.put("result", 1);
			map.put("message", "筛选标签成功！");
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

}
