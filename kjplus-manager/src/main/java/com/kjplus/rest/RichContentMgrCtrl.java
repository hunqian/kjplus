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
import org.ybk.basic.util.DateUtil;
import org.ybk.basic.util.Util;

import com.kjplus.constant.ConsolePageFtl;
import com.kjplus.constant.Constant;
import com.kjplus.dto.DepartmentDto;
import com.kjplus.dto.FuncDto;
import com.kjplus.dto.KjAdminUserDto;
import com.kjplus.dto.RichContentDto;
import com.kjplus.eto.RichContentEto;
import com.kjplus.model.RichContentEbo;
import com.kjplus.model.SysFuncEbo;
import com.kjplus.model.SysRefValueEbo;
import com.kjplus.service.IDeptService;
import com.kjplus.service.IInfoService;
import com.kjplus.service.IRichContentService;
import com.kjplus.service.ISysBaseService;
import com.kjplus.service.ISysFuncService;
import com.ybk.exception.DataException;

@Controller
public class RichContentMgrCtrl {

	private static Logger logger = Logger.getLogger(RichContentMgrCtrl.class);
	@Autowired
	private IInfoService infoService;
	@Autowired
	private ISysFuncService funcService;
	@Autowired
	private IDeptService deptService;
	@Autowired
	private IRichContentService richContentService;
	@Autowired
	private ISysBaseService sysBaseService;

	// 资讯列表
	@RequestMapping("/richcontentlist.html")
	public ModelAndView createDocument(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = mav.getModel();
		mav.setViewName("richcontent_list");

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
		if (userEbo == null) {
			mav.setViewName(ConsolePageFtl.LOGIN_ACE);
			map.put("result", -1);
			map.put("returnUrl", "/richcontentlist.html");
			map.put("message", "用户没有登陆!");
			return mav;
		}

		try {
			SysFuncEbo func = funcService.getFuncByMenu("richcontentlist.html", Constant.MENU_TYPE_MANAGER);
			if (func != null)
				map.put("pmenu", func.getPid());
			else
				map.put("pmenu", -1);
			List<FuncDto> funcs = funcService.listUserFunc(userEbo.getUid());
			map.put("funcs", funcs);

			List<DepartmentDto> listDepartment = deptService.listDepartmentDto(null,null, userEbo.getOrgId(), null, 0, 10);
			map.put("listDepartment", listDepartment);

			List<SysRefValueEbo> refVlByRefId = sysBaseService.getRefVlByRefId(62, null, null);
			map.put("refVlByRefId", refVlByRefId);
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return mav;
	}

	@RequestMapping(value = "/mgr_richcontentlistjson.html")
	public @ResponseBody
	Map<String, Object> RichContentListJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		int iDisplayStart = Util.parseNumVl(request.getParameter("iDisplayStart"), 0);
		int iDisplayLength = Util.parseNumVl(request.getParameter("iDisplayLength"), 10);
		int page = iDisplayStart / iDisplayLength;
		if (page <= 0)
			page = 0;

		try {
			KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
			int orgId = userEbo.getOrgId();
			String title = request.getParameter("title");
			//String flag = request.getParameter("status");
			String mainTypeCode = request.getParameter("mainTypeCode");
			List<RichContentDto> listContent = richContentService.listContent(mainTypeCode, orgId, title, page, iDisplayLength);
			List<List<Object>> aaData = new ArrayList<List<Object>>();
			List<Object> aaList = null;
			for (RichContentDto content : listContent) {
				aaList = new ArrayList<Object>();
				aaList.add(content.getId());
				aaList.add(content.getCode());
				aaList.add(content.getMainId());
				aaList.add(Util.val(content.getMainTypeCode()));
				aaList.add(Util.val(content.getTitle()));
				aaList.add(Util.val(content.getShowPic1()));
				aaList.add(Util.val(content.getShowPic2()));
				aaList.add(Util.val(content.getShowPic3()));
				aaList.add(Util.val(content.getShowPic4()));
				aaList.add(Util.val(content.getShowPic5()));
				aaList.add(Util.val(content.getContent()));
				aaList.add(DateUtil.formatDateTime(content.getCreateTime()));
				aaList.add(userEbo.getOrgId());
				aaData.add(aaList);
			}
			map.put("data", aaData);
			map.put("result", 1);
			int totalRec = richContentService.getTotalContent(mainTypeCode, orgId, title);
			map.put("iTotalRecords", totalRec);
			map.put("iTotalDisplayRecords", totalRec);
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 获取数据回显
	@RequestMapping(value = "/mgr_getRichContentjson.html")
	public @ResponseBody
	Map<String, Object> getInfoJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		String richContentcode = request.getParameter("richContentcode");
		try {
			RichContentEbo content = richContentService.getContentByIdOrCode(0, richContentcode);
			if (content == null) {
				map.put("result", -1);
				map.put("messge", "没有找到对于的信息内容的编码'" + richContentcode + "'");
				return map;
			}
			Map<String, Object> data = new HashMap<String, Object>();
			
			data.put("code", content.getCode());
			data.put("title", content.getTitle());
			data.put("showPic1", content.getShowPic1());
			data.put("showPic2", content.getShowPic2());
			data.put("showPic3", content.getShowPic3());
			data.put("showPic4", content.getShowPic4());
			data.put("showPic5", content.getShowPic5());
			data.put("content", content.getContent());

			map.put("data", data);
			map.put("result", 1);

		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 添加修改咨询列表
	@RequestMapping(value = "/mgr_addormodifyRichContentjson.html")
	public @ResponseBody
	Map<String, Object> addinfolistjson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		try {
			RichContentEto richContentEto = new RichContentEto();
			KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
			String richCode = request.getParameter("code");
			int mainId =  Util.parseNumVl(request.getParameter("mainId"), 0);
			String mainTypeCode  = request.getParameter("mainTypeCode");
			if (mainId == 0) {
				mainId = userEbo.getOrgId();
				mainTypeCode = "RT_ORG";
			}
			String richTitle = request.getParameter("title");
			String showPic1 = request.getParameter("showPic1");
			String showPic2 = request.getParameter("showPic2");
			String showPic3 = request.getParameter("showPic3");
			String showPic4 = request.getParameter("showPic4");
			String showPic5 = request.getParameter("showPic5");
			String richContent = request.getParameter("richContent");
			int orgId = userEbo.getOrgId();
			
			if (Util.isNull(richCode)) {
				richContentEto.setMainId(mainId);
				richContentEto.setMainTypeCode(mainTypeCode);
				richContentEto.setTitle(richTitle);
				richContentEto.setShowPic1(showPic1);
				richContentEto.setShowPic2(showPic2);
				richContentEto.setShowPic3(showPic3);
				richContentEto.setShowPic4(showPic4);
				richContentEto.setShowPic5(showPic5);
				richContentEto.setContent(richContent);
				richContentEto.setOrgid(orgId);
				richContentService.addContent(richContentEto);
				map.put("message", "添加详情成功！");
			} else {
				RichContentEbo r = richContentService.getContentByIdOrCode(0, richCode);
				r.setTitle(richTitle);
				r.setShowPic1(showPic1);
				r.setShowPic2(showPic2);
				r.setShowPic3(showPic3);
				r.setShowPic4(showPic4);
				r.setShowPic5(showPic5);
				r.setContent(richContent);
				if (r != null) {
					richContentService.updateContent(r);
					map.put("message", "修改详情成功功！");
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
}
