package com.kjplus.rest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.ybk.basic.util.Util;

import com.kjplus.constant.ConsolePageFtl;
import com.kjplus.constant.Constant;
import com.kjplus.dao.IInfoDao;
import com.kjplus.dto.FuncDto;
import com.kjplus.dto.InfoDto;
import com.kjplus.dto.KjAdminUserDto;
import com.kjplus.dto.OrgDto;
import com.kjplus.eto.InfoEto;
import com.kjplus.model.InfoEbo;
import com.kjplus.model.SysFuncEbo;
import com.kjplus.service.IAdminUserService;
import com.kjplus.service.IDeptService;
import com.kjplus.service.IInfoService;
import com.kjplus.service.IOrgService;
import com.kjplus.service.ISysFuncService;
import com.kjplus.service.IUserService;
import com.ybk.exception.DataException;

@Controller
public class InfoCtrl {

	private static Logger logger = Logger.getLogger(InfoCtrl.class);
	@Autowired
	private IInfoDao infoDao;
	@Autowired
	private IInfoService infoService;
	@Autowired
	private ISysFuncService funcService;
	@Autowired
	private IDeptService deptService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IOrgService orgService;
	@Autowired
	private IAdminUserService adminUserService;

	// 资讯列表
	@RequestMapping("/infolist.html")
	public ModelAndView createDocument(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = mav.getModel();
		mav.setViewName("info_list");

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJCONSOLE_SESS);
		if (userEbo == null) {
			mav.setViewName(ConsolePageFtl.LOGIN_ACE);
			map.put("result", -1);
			map.put("returnUrl", "/infolist.html");
			map.put("message", "用户没有登陆!");
			return mav;
		}

		try {
			SysFuncEbo func = funcService.getFuncByMenu("infolist.html", Constant.MENU_TYPE_CONSOLE);
			if (func != null)
				map.put("pmenu", func.getPid());
			else
				map.put("pmenu", -1);
			List<FuncDto> funcs = funcService.listUserFunc(userEbo.getUid());
			map.put("funcs", funcs);

			List<OrgDto> listOrg = orgService.listOrg(null, null, 0, 0, false, 0, 0);
			map.put("listOrg", listOrg);

		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return mav;
	}

	@RequestMapping(value = "/mgr_infolistjson.html")
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
			int orgId = Util.parseNumVl(request.getParameter("orgId"), 0);
			String title = request.getParameter("title");
			String flag = request.getParameter("status");
			String infoType = request.getParameter("infoType");
			String queryday = request.getParameter("queryday");
			if (Util.isNotNull(queryday)) {
				Date rtDate = null;
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				try {
					rtDate = dateFormat.parse(queryday);
				} catch (ParseException pe) {
				}
				if (rtDate != null)
					queryday = DateUtil.formatDate(rtDate);
			}
			int infoCatgId = Util.parseNumVl(request.getParameter("cfgId"), 0);
			List<InfoDto> listInfo = infoService.listInfo(title, infoCatgId, flag, "Y", orgId, queryday, false, infoType, page, iDisplayLength);
			List<List<Object>> aaData = new ArrayList<List<Object>>();
			List<Object> aaList = null;
			for (InfoDto info : listInfo) {
				aaList = new ArrayList<Object>();
				aaList.add(info.getId());
				aaList.add(info.getInfoCode());
				aaList.add(info.getInfoTitle());
				aaList.add(Util.val(info.getInfoDesc()));
				aaList.add(info.getInfoType());
				aaList.add(info.getInfoCatgId());
				aaList.add(Util.val(info.getInfoCatgName()));
				aaList.add(info.getTotalViewNum());
				aaList.add(info.getTotalZanNum());
				aaList.add(info.getTotalFocusNum());
				aaList.add(info.getFlag());
				aaList.add(Util.val(info.getPubName()));
				aaList.add(Util.val(info.getDeptName()));
				aaList.add(info.getOrgName());
				aaList.add(Util.val(info.getIconImgUrl()));
				aaList.add(info.getContent());
				aaList.add(DateUtil.formatDateTime(info.getPubTime()));

				aaData.add(aaList);
			}
			map.put("data", aaData);
			map.put("result", 1);

			int totalRec = infoService.getTotalInfo(title, infoCatgId, flag, "Y", orgId, queryday, infoType);
			map.put("iTotalRecords", totalRec);
			map.put("iTotalDisplayRecords", totalRec);
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	@RequestMapping(value = "/mgr_getinfojson.html")
	public @ResponseBody
	Map<String, Object> getInfoJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		String infoCode = request.getParameter("infocode");
		try {
			InfoEbo info = infoService.getInfoByIdOrCode(0, infoCode);
			if (info == null) {
				map.put("result", -1);
				map.put("messge", "没有找到对于的信息内容的编码'" + infoCode + "'");
				return map;
			}

			Map<String, Object> data = new HashMap<String, Object>();
			data.put("code", info.getInfoCode());
			data.put("title", Util.val(info.getInfoTitle()));
			data.put("desc", Util.val(info.getInfoDesc()));
			data.put("catgid", info.getInfoCatgId());
			data.put("orgid", info.getOrgId());
			data.put("deptid", info.getDeptId());
			data.put("headImgUrl", Util.val(info.getHeadImgUrl()));
			data.put("iconImgUrl", Util.val(info.getIconImgUrl()));
			data.put("infoCatgId", info.getInfoCatgId());
			data.put("pubid", info.getPubId());
			data.put("infoType", info.getInfoType());
			map.put("data", data);
			map.put("result", 1);

		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 添加消息内容
	@RequestMapping(value = "/mgr_addinfocontentjson.html")
	public @ResponseBody
	Map<String, Object> addInfoContentJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		int infoid = Util.parseNumVl(request.getParameter("infoid"), 0);
		String infoContent = request.getParameter("content");

		try {
			if (infoid < 0 || Util.isNull(infoContent)) {
				map.put("result", -1);
				map.put("message", "infoid或内容不能为空!");
				return map;
			}
			// 图片保存到目录
			infoContent = null;// ImageBasePpUtil.procContent(infoContent,
								// UploadConstant.IMAGE_FILE_DIR,
								// UploadConstant.IMAGE_SERVER_URL);
			infoService.addInfoContent(infoid, infoContent);
			map.put("result", 1);
			map.put("message", "添加内容成功！");
		} catch (Exception e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 获得内容
	@RequestMapping(value = "/mgr_getinfocontentjson.html")
	public @ResponseBody
	Map<String, Object> getInfoContentJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		int infoid = Util.parseNumVl(request.getParameter("infoid"), 0);
		try {
			if (infoid < 0) {
				map.put("result", -1);
				map.put("message", "infoid不能为空!");
				return map;
			}

			String content = infoService.getInfoContent(infoid);
			map.put("content", content);
			map.put("result", 1);
			map.put("message", "获得内容成功！");
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 添加修改咨询列表
	@RequestMapping(value = "/addormodifyinfojson.html")
	public @ResponseBody
	Map<String, Object> addinfolistjson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		try {
			InfoEto info = new InfoEto();
			String infoTitle = request.getParameter("title");
			String infoCode = request.getParameter("code");
			String infoDesc = request.getParameter("desc");
			int orgId = Util.parseNumVl(request.getParameter("orgid"), 0);
			int infoCatgId = Util.parseNumVl(request.getParameter("catgid"), 0);
			int pubId = Util.parseNumVl(request.getParameter("pubid"), 0);
			int deptId = Util.parseNumVl(request.getParameter("deptid"), 0);
			String infoType = request.getParameter("infoType");
			info.setInfoTitle(infoTitle);
			info.setInfoDesc(infoDesc);
			info.setOrgId(orgId);
			info.setPubId(pubId);
			info.setDeptId(deptId);
			info.setInfoCatgId(infoCatgId);
			info.setPubTime(DateUtil.newTime());
			info.setInfoType(infoType);
			if (Util.isNull(infoCode)) {
				infoService.addInfo(info);
				map.put("message", "添加info成功！");
			} else {
				InfoEbo i = infoService.getInfoByIdOrCode(0, infoCode);
				i.setInfoTitle(infoTitle);
				i.setInfoDesc(infoDesc);
				i.setInfoCode(infoCode);
				i.setOrgId(orgId);
				i.setInfoCatgId(infoCatgId);
				i.setPubId(pubId);
				i.setDeptId(deptId);
				i.setInfoType(infoType);
				if (i != null) {
					infoService.updateInfo(i);
					map.put("message", "修改info功！");
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

	// 下线咨询
	@RequestMapping(value = "/downinfojson.html")
	public @ResponseBody
	Map<String, Object> downinfojson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		try {
			String infoCode = request.getParameter("infocode");

			InfoEbo i = infoService.getInfoByIdOrCode(0, infoCode);
			if (i != null) {
				if (i.getFlag().equals("N")) {
					i.setFlag("Y");
					infoService.updateInfo(i);
					map.put("result", 1);
					map.put("message", "咨询已上线!!!");
				} else {
					i.setFlag("N");
					infoService.updateInfo(i);
					map.put("result", 1);
					map.put("message", "咨询已下线！");
				}
			}

		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}
}
