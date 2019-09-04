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
import com.kjplus.dto.AdminUserDto;
import com.kjplus.dto.FuncDto;
import com.kjplus.dto.IDNameDto;
import com.kjplus.dto.KjAdminUserDto;
import com.kjplus.dto.OrgDto;
import com.kjplus.dto.TagDto;
import com.kjplus.eto.DocInfoEto;
import com.kjplus.model.DocumentInfoEbo;
import com.kjplus.model.SysFuncEbo;
import com.kjplus.model.SysRefValueEbo;
import com.kjplus.model.inner.DocumentInfoInnerEbo;
import com.kjplus.service.IAdminUserService;
import com.kjplus.service.IDocInfoService;
import com.kjplus.service.IOrgService;
import com.kjplus.service.ISysBaseService;
import com.kjplus.service.ISysFuncService;
import com.ybk.exception.DataException;
import com.ykisswx.constant.WxConstant;

@Controller
public class DocumentInfoCtrl {

	private static Logger logger = Logger.getLogger(DocumentInfoCtrl.class);

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

	// 档案列表
	@RequestMapping("/docinfolist.html")
	public ModelAndView orgList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = mav.getModel();
		mav.setViewName("docinfo_list");

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJCONSOLE_SESS);
		if (userEbo == null) {
			mav.setViewName(ConsolePageFtl.LOGIN_ACE);
			map.put("result", -1);
			map.put("returnUrl", "/docinfolist.html");
			map.put("message", "用户没有登陆!");
			return mav;
		}

		try {
			SysFuncEbo func = funcService.getFuncByMenu("docinfolist.html", Constant.MENU_TYPE_CONSOLE);
			if (func != null)
				map.put("pmenu", func.getPid());
			else
				map.put("pmenu", -1);
			List<FuncDto> funcs = funcService.listUserFunc(userEbo.getUid());
			map.put("funcs", funcs);
			// 组织列表
			List<OrgDto> orgs = orgService.listOrg(null, WxConstant.FLAG_YES, 0, 0, false, 0, 10);
			List<IDNameDto> idns = new ArrayList<IDNameDto>();
			for (OrgDto org : orgs) {
				IDNameDto idn = new IDNameDto();
				idn.setId(org.getId());
				idn.setCode(org.getCode());
				idn.setName(org.getName());
				idns.add(idn);
			}
			map.put("orglist", idns);

			// 获取组织列表
			List<OrgDto> listOrg = orgService.listOrg(null, null, 0, 0, false, 0, 0);
			map.put("listOrg", listOrg);
			// 获取血型列表
			List<SysRefValueEbo> refVlB = baseService.getRefVlByRefId(21, null);
			map.put("refVlB", refVlB);
			// 获取民族列表
			List<SysRefValueEbo> refVlN = baseService.getRefVlByRefId(20, null);
			map.put("refVlN", refVlN);
			// 获取建档人列表
			List<AdminUserDto> listAUser = adminUserService.listUser(null, null, null, null, 0, 0, 10);
			map.put("listAUser", listAUser);
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
			String name = request.getParameter("name");
			int orgid = Util.parseNumVl(request.getParameter("orgid"), 0);
			String status = request.getParameter("status");
			String flag = request.getParameter("flag");
			String idCard = request.getParameter("idcard");

			List<Integer> tagIds = null;
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
			int total = aaData.size();
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
			int orgid = Util.parseNumVl(request.getParameter("orgid"), 0);
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
}
