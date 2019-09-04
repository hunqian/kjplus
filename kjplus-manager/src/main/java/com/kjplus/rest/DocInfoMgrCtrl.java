package com.kjplus.rest;

import java.util.ArrayList;
import java.util.HashMap;
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

import com.kjplus.constant.ConsolePageFtl;
import com.kjplus.constant.Constant;
import com.kjplus.dto.DepartmentDto;
import com.kjplus.dto.FuncDto;
import com.kjplus.dto.KjAdminUserDto;
import com.kjplus.dto.StaffDto;
import com.kjplus.dto.TagDto;
import com.kjplus.dto.UserDto;
import com.kjplus.eto.DocInfoEto;
import com.kjplus.model.DocumentInfoEbo;
import com.kjplus.model.StaffEbo;
import com.kjplus.model.SysFuncEbo;
import com.kjplus.model.TagEbo;
import com.kjplus.model.inner.DocumentInfoInnerEbo;
import com.kjplus.model.inner.ServicePackageInnerEbo;
import com.kjplus.service.IAdminUserService;
import com.kjplus.service.IDeptService;
import com.kjplus.service.IDocInfoService;
import com.kjplus.service.IOrgService;
import com.kjplus.service.IServicePackageService;
import com.kjplus.service.IStaffService;
import com.kjplus.service.ISysBaseService;
import com.kjplus.service.ISysFuncService;
import com.kjplus.service.ITagService;
import com.kjplus.service.IUserService;
import com.ybk.exception.DataException;

@Controller
public class DocInfoMgrCtrl {

	private static Logger logger = Logger.getLogger(DocInfoMgrCtrl.class);

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
	private IDeptService deptService;
	@Autowired
	private IStaffService staffService;
	@Autowired
	private ITagService tagService;
	@Autowired
	private IServicePackageService srvPackageService;
	@Autowired
	private IUserService userService;

	// 档案列表
	@RequestMapping("/docinfolist.html")
	public ModelAndView orgList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = mav.getModel();
		mav.setViewName("docinfo_list");

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
		if (userEbo == null) {
			mav.setViewName(ConsolePageFtl.LOGIN_ACE);
			map.put("result", -1);
			map.put("returnUrl", "/docinfolist.html");
			map.put("message", "用户没有登陆!");
			return mav;
		}

		try {
			SysFuncEbo func = funcService.getFuncByMenu("docinfolist.html", Constant.MENU_TYPE_MANAGER);
			if (func != null)
				map.put("pmenu", func.getPid());
			else
				map.put("pmenu", -1);
			List<FuncDto> funcs = funcService.listUserFunc(userEbo.getUid());
			map.put("funcs", funcs);

			List<DepartmentDto> listDepartmentDto = deptService.listDepartmentDto(null, null, userEbo.getOrgId(), null, 0, 10);
			map.put("listDepartmentDto", listDepartmentDto);

			List<ServicePackageInnerEbo> listSrvPackage = srvPackageService.listSrvPackageInner(null, userEbo.getOrgId(), null, null, null, 0, null, null, 0, 100);
			map.put("servCatg", listSrvPackage);

			List<UserDto> listUser = userService.listUser(0, null, userEbo.getOrgId(), "Y", null, null, -1, -1);
			map.put("listUser", listUser);

			List<StaffDto> listStaffDto = staffService.listStaffDto(userEbo.getOrgId(), 0,null,null,null, -1, -1);
			map.put("listStaffDto", listStaffDto);

		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return mav;
	}

	// 档案列表
	@RequestMapping(value = "/docinfolistjson.html")
	public @ResponseBody
	Map<String, Object> adminUserListJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		int iDisplayStart = Util.parseNumVl(request.getParameter("iDisplayStart"), 0);
		int iDisplayLength = Util.parseNumVl(request.getParameter("iDisplayLength"), 10);
		int page = iDisplayStart / iDisplayLength;
		if (page <= 0)
			page = 0;

		try {

			KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
			String name = request.getParameter("name");
			int orgid = userEbo.getOrgId();
			String status = request.getParameter("status");
			String flag = request.getParameter("flag");
			String idCard = request.getParameter("idcard");
			String strTags = request.getParameter("tags");
			List<Integer> tagIds = new ArrayList<Integer>();
			if (Util.isNotNull(strTags)) {
				String[] arrTags = strTags.split(",");
				for (int i = 0; i < arrTags.length; i++) {
					int tagid = Util.parseNumVl(arrTags[i], 0);
					if (tagid != 0)
						tagIds.add(tagid);
				}
			}
			List<DocumentInfoInnerEbo> docs = docInfoService.listDocInfoInner(orgid, name, status,flag, idCard, tagIds,true,0,0, page, iDisplayLength);
			List<List<Object>> aaData = new ArrayList<List<Object>>();
			List<Object> auList = null;
			for (DocumentInfoInnerEbo doc : docs) {
				auList = new ArrayList<Object>();
				auList.add(doc.getPrsnId());
				auList.add(Util.val(doc.getCode()));
				auList.add(Util.val(doc.getFlag()));
				auList.add(Util.val(doc.getHeadIconUrl()));
				auList.add(Util.val(doc.getPrsnCode()));
				auList.add(Util.val(doc.getPrsnName()));
				auList.add(Util.val(doc.getPrsnSex()));
				auList.add(Util.val(DateUtil.formatDate(doc.getBirthday())));
				auList.add(Util.val(doc.getIdNumber()));
				auList.add(Util.val(doc.getMobileNum()));
				auList.add(Util.val(doc.getNationVl()));
				auList.add(Util.val(doc.getFamilyAddr()));
				// 签约信息
				String staffName = "";
				
				if (doc.getSrvAssigns().size() > 0 && doc.getSrvAssigns().get(0).getStafId() != null && doc.getSrvAssigns().get(0).getStafId().intValue() > 0) {
					StaffEbo staff = staffService.getStaffById(doc.getSrvAssigns().get(0).getStafId());
					if (staff != null)
						staffName = staff.getName();
				}
				auList.add(staffName);
				if(doc.getSrvAssigns().size()>0)
					auList.add(Util.val(doc.getSrvAssigns().get(0).getListPackage().get(0).getSrvAlias()));
				else
					auList.add("");
				auList.add(Util.val(DateUtil.formatDate(doc.getCreateDay())));
				if (doc.getTags().size() == 0)
					auList.add("");
				else {
					List<TagDto> tags = doc.getTags();
					StringBuffer buf = new StringBuffer();
					for (int i = 0; i < tags.size(); i++) {
						if (i != 0)
							buf.append(",");
						buf.append(tags.get(i).getRefValName());
					}
					auList.add(buf.toString());
				}
				auList.add(Util.val(doc.getUid()));
				auList.add(Util.val(doc.getUnickName()));
				auList.add(Util.val(doc.getPersonNum()));
				auList.add(Util.val(doc.getWorkUnit()));
				auList.add(Util.val(doc.getBloodVl()));
				auList.add(Util.val(doc.getContactName()));
				auList.add(Util.val(doc.getContactNum()));
				auList.add(Util.val(doc.getHouseAddr()));
				auList.add(Util.val(doc.getOrgId()));
				auList.add(Util.val(doc.getOrgName()));
				auList.add(Util.val(doc.getCreatorName()));
				auList.add(Util.val(doc.getCreatorId()));
				auList.add(Util.val(DateUtil.formatDate(doc.getCreateTime())));

				aaData.add(auList);
			}

			int total = docInfoService.getTotalDocInfoInner(orgid, name,status, flag, idCard, tagIds,0,0);
			map.put("aaData", aaData);
			map.put("result", 1);
			map.put("iTotalRecords", total);
			map.put("iTotalDisplayRecords", total);
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 添加修改咨询列表
	@RequestMapping(value = "/addormodifydocinfojson.html")
	public @ResponseBody
	Map<String, Object> adddocinfolistjson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		try {
			KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
			DocInfoEto docInfoEto = new DocInfoEto();
			String code = request.getParameter("code");
			String prsnname = request.getParameter("prsnname");
			String birthday = request.getParameter("birthday");
			String creatday = request.getParameter("creatday");
			String bloodvl = request.getParameter("bloodvl");
			String contactnum = request.getParameter("contactnum");
			String familyaddr = request.getParameter("familyaddr");
			String houseaddr = request.getParameter("houseaddr");
			String idnumber = request.getParameter("idnumber");
			String mobilenum = request.getParameter("mobilenum");
			String nationvl = request.getParameter("nationvl");
			int creatorid = Util.parseNumVl(request.getParameter("creatorid"), 0);
			int orgid = userEbo.getOrgId();
			String personnum = request.getParameter("personnum");
			String persionsex = request.getParameter("persionsex");
			String workunit = request.getParameter("workunit");
			String headIconUrl = request.getParameter("face");

			docInfoEto.setPrsnName(prsnname);
			docInfoEto.setBirthday(birthday);
			docInfoEto.setCreateDay(creatday);
			docInfoEto.setBloodVl(bloodvl);
			docInfoEto.setContactNum(contactnum);
			docInfoEto.setFamilyAddr(familyaddr);
			docInfoEto.setHouseAddr(houseaddr);
			docInfoEto.setIdNumber(idnumber);
			docInfoEto.setMobileNum(mobilenum);
			docInfoEto.setNationVl(nationvl);
			docInfoEto.setCreatorId(creatorid);
			docInfoEto.setOrgId(orgid);
			docInfoEto.setPersonNum(personnum);
			docInfoEto.setPrsnSex(persionsex);
			docInfoEto.setWorkUnit(workunit);
			docInfoEto.setHeadIconUrl(headIconUrl);

			if (Util.isNull(code)) {
				docInfoService.addDocInfo(docInfoEto);
				map.put("message", "添加建档信息成功！");
			} else {
				DocumentInfoEbo d = docInfoService.getDocinfoByIdOrCode(0, code);
				if (d != null) {
					BeanUtils.copyProperties(docInfoEto, d);

					docInfoService.updateDocInfo(d);
					map.put("message", "修改建档信息成功！");
				}
			}
			map.put("result", 1);
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 获取修改回显信息
	@RequestMapping(value = "/mgr_getdocinfojson.html")
	public @ResponseBody
	Map<String, Object> getDocInfoJson(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		String code = request.getParameter("code");
		try {
			DocumentInfoEbo d = docInfoService.getDocinfoByIdOrCode(0, code);
			if (d == null) {
				map.put("result", -1);
				map.put("messge", "没有找到对于的信息内容的编码'" + code + "'");
				return map;
			}

			Map<String, Object> data = new HashMap<String, Object>();
			data.put("code", d.getCode());
			data.put("prsnid", d.getPrsnId());
			data.put("prsnname", d.getPrsnName());
			data.put("birthday", d.getBirthday());
			data.put("creatday", d.getCreateDay());
			data.put("bloodvl", d.getBloodVl());
			data.put("contactnum", d.getContactNum());
			data.put("familyaddr", d.getFamilyAddr());
			data.put("houseaddr", d.getHouseAddr());
			data.put("idnumber", d.getIdNumber());
			data.put("mobilenum", d.getMobileNum());
			data.put("nationvl", d.getNationVl());
			data.put("creatorid", d.getCreatorId());
			data.put("orgid", d.getOrgId());
			data.put("personnum", d.getPersonNum());
			data.put("persionsex", d.getPrsnSex());
			data.put("workunit", d.getWorkUnit());
			data.put("face", d.getHeadIconUrl());

			map.put("aadata", data);
			map.put("result", 1);

		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 获取标签列表
	@RequestMapping(value = "/mgr_getdoctagjson.html")
	public @ResponseBody
	Map<String, Object> getAdminUserRolesJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		int docid = Util.parseNumVl(request.getParameter("docid"), 0);

		try {
			KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
			if (userEbo == null) {
				map.put("result", -1);
				map.put("message", "用户没有登陆!");
				return map;
			}

			List<TagDto> tags = tagService.listTagChecked(docid, Constant.TAG_TYPE_PERSON, "PERSON_TYPE");

			map.put("data", tags);
			map.put("result", 1);
			map.put("message", "获得用户角色成功!");
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 添加标签
	@RequestMapping(value = "/mgr_defdoctagjson.html")
	public @ResponseBody
	Map<String, Object> defDocTagsJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		int docid = Util.parseNumVl(request.getParameter("docid"), 0);
		String refValIds = request.getParameter("refvalids");

		try {
			KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
			if (userEbo == null) {
				map.put("result", -1);
				map.put("message", "用户没有登陆!");
				return map;
			}
			if (docid <= 0) {
				map.put("result", -1);
				map.put("message", "用户没有指定!");
				return map;
			}

			List<Integer> docTagIds = new ArrayList<Integer>();
			List<String> docTagid = Util.str2Array(refValIds);
			for (String d : docTagid) {
				docTagIds.add(Util.parseNumVl(d, 0));
			}
			tagService.addTags(docid, Constant.TAG_TYPE_PERSON, docTagIds);
			map.put("result", 1);
			map.put("message", "保存居民标签成功!");
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// ajax进行tags列表筛选
	@RequestMapping(value = "/taglistjson.html")
	public @ResponseBody
	Map<String, Object> tagListJson(HttpServletRequest request, HttpServletResponse response) {

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
