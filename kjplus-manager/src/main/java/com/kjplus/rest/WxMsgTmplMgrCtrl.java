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
import org.ybk.basic.util.Util;

import com.kjplus.constant.ConsolePageFtl;
import com.kjplus.constant.Constant;
import com.kjplus.dto.FuncDto;
import com.kjplus.dto.KjAdminUserDto;
import com.kjplus.model.SysFuncEbo;
import com.kjplus.service.ISysFuncService;
import com.ybk.exception.DataException;
import com.ykisswx.constant.WxConstant;
import com.ykisswx.dto.WxAccountDto;
import com.ykisswx.eto.WxMsgtmplUseEto;
import com.ykisswx.model.WxMsgtmplContentEbo;
import com.ykisswx.model.WxMsgtmplEbo;
import com.ykisswx.model.WxMsgtmplLibEbo;
import com.ykisswx.model.inner.WxMsgtmplUseInnerEbo;
import com.ykisswx.service.IWxAccountService;
import com.ykisswx.service.IWxMsgTmplService;

@Controller
public class WxMsgTmplMgrCtrl {

	private static Logger logger = Logger.getLogger(WxMsgTmplMgrCtrl.class);
	@Autowired
	private ISysFuncService funcService;
	@Autowired
	private IWxMsgTmplService wxMsgTmplService;
	@Autowired
	private IWxAccountService wxAccountService;
	
	// Msgtemp列表
	@RequestMapping("/wxmsgtmplist.html")
	public ModelAndView msgtmpList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = mav.getModel();
		mav.setViewName("wxmsgtmp_list");

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
		if (userEbo == null) {
			mav.setViewName(ConsolePageFtl.LOGIN_ACE);
			map.put("result", -1);
			map.put("returnUrl", "/msgtmplist.html");
			map.put("message", "用户没有登陆!");
			return mav;
		}

		try {
			// 显示左面菜单
			SysFuncEbo func = funcService.getFuncByMenu("wxmsgtmplist.html", Constant.MENU_TYPE_MANAGER);
			if (func != null)
				map.put("pmenu", func.getPid());
			else
				map.put("pmenu", -1);
			List<FuncDto> funcs = funcService.listUserFunc(userEbo.getUid());
			
			List<WxAccountDto> accs = wxAccountService.listAccount(0, null, null, 0, 100);
			map.put("accs", accs);
			
			List<WxMsgtmplEbo> tmpls = wxMsgTmplService.listTmpl(null, null, 0, 100);
			map.put("tmpls", tmpls);
			
			map.put("funcs", funcs);
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return mav;
	}

	// Msgtmpl列表信息,可用于查询
	@RequestMapping("/mgr_wxmsgtmplistjson.html")
	public @ResponseBody
	Map<String, Object> msgtmpListJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		int iDisplayStart = Util.parseNumVl(request.getParameter("iDisplayStart"), 0);
		int iDisplayLength = Util.parseNumVl(request.getParameter("iDisplayLength"), 10);
		int page = iDisplayStart / iDisplayLength;
		if (page <= 0)
			page = 0;
		String tmplCode = request.getParameter("code");
		String tmplTitle = request.getParameter("title");

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
		if (userEbo == null) {
			map.put("result", -1);
			map.put("returnUrl", "/login.html");
			map.put("message", "用户没有登陆!");
			return map;
		}
		try {
			List<WxMsgtmplEbo> msgtmplList = wxMsgTmplService.listTmpl(tmplCode, tmplTitle, page, iDisplayLength);
			List<Object> auList = null;
			List<List<Object>> aaData = new ArrayList<List<Object>>();
			for (WxMsgtmplEbo ms : msgtmplList) {
				auList = new ArrayList<Object>();
				auList.add(ms.getId());
				auList.add(Util.val(ms.getCode()));
				auList.add(Util.val(ms.getTitle()));
				auList.add(Util.val(ms.getMainIndustry()));
				auList.add(Util.val(ms.getSubIndustry()));
				auList.add(Util.val(ms.getUseNum()));
				auList.add(Util.val(ms.getContent()));
				auList.add(Util.val(ms.getDemoSample()));
				aaData.add(auList);
			}
			int total = wxMsgTmplService.getTotalTmpl(tmplCode, tmplTitle);
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

	// Msgtmpl列表信息,可用于查询
	@RequestMapping("/mgr_wxmsgtmpltjson.html")
	public @ResponseBody
	Map<String, Object> msgtmplJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
		if (userEbo == null) {
			map.put("result", -1);
			map.put("returnUrl", "/login.html");
			map.put("message", "用户没有登陆!");
			return map;
		}
		try {
			int libId = Util.parseNumVl(request.getParameter("libid"), 0);
			int cntId = Util.parseNumVl(request.getParameter("cntid"), 0);
			String tmplCode = request.getParameter("code");
			WxMsgtmplEbo msgTmpl = wxMsgTmplService.getTmpL(libId, cntId, tmplCode);
			if(msgTmpl != null){
				map.put("msgTmpl", msgTmpl);
				map.put("result", 1);
			}else{
				map.put("result", -1);
				map.put("message", "该消息模板不存在");
			}
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}
	
	// titile名称内容，添加修改
	@RequestMapping("/mgr_addlibjson.html")
	public @ResponseBody
	Map<String, Object> addlibjson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
		if (userEbo == null) {
			map.put("result", -1);
			map.put("returnUrl", "/msgtmp_list.html");
			map.put("message", "用户没有登陆!");
			return map;
		}

		// 标题不可为空
		String mainIndustry = request.getParameter("mainindustry");
		String subIndustry = request.getParameter("subindustry");
		String content = request.getParameter("content");
		String demoSample = request.getParameter("demosample");
		String title = request.getParameter("title");
		
		if (Util.isNull(title) || Util.isNull(content) ) {
			map.put("result", -1);
			map.put("returnUrl", "/msgtmp_list.html");
			map.put("message", "添加Lib时，title和content不可为空");
			return map;
		}
		WxMsgtmplLibEbo lib = new WxMsgtmplLibEbo();
		lib.setTitle(title);
		lib.setMainIndustry(mainIndustry);
		lib.setSubIndustry(subIndustry);

		WxMsgtmplContentEbo cont = new WxMsgtmplContentEbo();
		cont.setContent(content);
		cont.setDemoSample(demoSample);

		// varchar 20 随机生成code ,不可重复
		String code = Util.genAuthCode(10);
		try {
			
			// 获取title，不能重复
			WxMsgtmplLibEbo l = wxMsgTmplService.getTmpLib(0, null, title);
			if (l != null) {
				map.put("result", -1);
				map.put("returnUrl", "/msgtmp_list.html");
				map.put("message", "该title已经存在，请重新设置");
				return map;
			}

			WxMsgtmplLibEbo li = wxMsgTmplService.getTmpLib(0, code, null);
			while (li != null) {
				code = Util.genAuthCode(10);
				li = wxMsgTmplService.getTmpLib(0, code, null);
			}

			lib.setCode(code);
			cont.setCode(code);
			wxMsgTmplService.addTemplLib(lib);
			wxMsgTmplService.addTemplContent(cont);
			map.put("result", 1);
			map.put("message", "添加成功，title：" + lib.getTitle());
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// titile名称，添加修改
	@RequestMapping("/mgr_updateMsgTmpljson.html")
	public @ResponseBody
	Map<String, Object> updatelibjson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		try {
			// 设置code为不可变量
			String code = request.getParameter("code");
			String title = request.getParameter("title");
			String mainIndustry = request.getParameter("mainIndustry");
			String subIndustry = request.getParameter("subIndustry");
			String content = request.getParameter("content");
			String demoSample = request.getParameter("demoSample");
			
			WxMsgtmplLibEbo wxMsgtmplLibEbo = new WxMsgtmplLibEbo();
			wxMsgtmplLibEbo.setCode(code);
			wxMsgtmplLibEbo.setTitle(title);
			wxMsgtmplLibEbo.setMainIndustry(mainIndustry);
			wxMsgtmplLibEbo.setSubIndustry(subIndustry);
			//添加修改在一起
			wxMsgTmplService.addTemplLib(wxMsgtmplLibEbo);
			
			WxMsgtmplContentEbo wxMsgtmplContentEbo = new WxMsgtmplContentEbo();
			wxMsgtmplContentEbo.setCode(code);
			wxMsgtmplContentEbo.setContent(content);
			wxMsgtmplContentEbo.setDemoSample(demoSample);
			wxMsgTmplService.addTemplContent(wxMsgtmplContentEbo);
			map.put("result", 1);
			map.put("message", "修改成功");
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// content内容，添加
	@RequestMapping("/addcontentjson.html")
	public @ResponseBody
	Map<String, Object> addcontentjson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
		if (userEbo == null) {
			map.put("result", -1);
			map.put("returnUrl", "/msgtmp_list.html");
			map.put("message", "用户没有登陆!");
			return map;
		}

		try {
			// code获取title
			String code = request.getParameter("code");
			WxMsgtmplLibEbo tmplLib = wxMsgTmplService.getTmpLib(0, code, null);
			if (tmplLib == null) {
				map.put("result", -1);
				map.put("returnUrl", "/msgtmp_list.html");
				map.put("message", "没有找到相应的title，请重新输入title");
				return map;
			}
			String content = request.getParameter("content");
			String demoSample = request.getParameter("demoSample");
			WxMsgtmplContentEbo cont = new WxMsgtmplContentEbo();
			cont.setContent(content);
			cont.setDemoSample(demoSample);

			wxMsgTmplService.addTemplContent(cont);
			map.put("result", 1);
			map.put("message", "添加或修改内容成功，content：" + cont.getContent());
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 获得内容
	@RequestMapping("/wxgetmsgtmpljson.html")
	public @ResponseBody
	Map<String, Object> getMsgTmlpjson(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
		if (userEbo == null) {
			map.put("result", -1);
			map.put("message", "用户没有登陆!");
			return map;
		}
		try {
			// code获取title
			String code = request.getParameter("code");
			WxMsgtmplContentEbo content = wxMsgTmplService.getTmpLContent(0, code);
			if(content == null){
				map.put("result", 0);
				map.put("message", "没有对应code='"+code+"'的模板内容!");
				return map;
			}
			
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("content", content.getContent());
			data.put("demo", content.getDemoSample());
			map.put("data", data);
			map.put("result", 1);
			map.put("message", "获得模板内容成功!");
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 获得模板使用情况
	@RequestMapping("/listmsgtmplusejson.html")
	public @ResponseBody
	Map<String, Object> listmsgtmplusejson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
		if (userEbo == null) {
			map.put("result", -1);
			map.put("returnUrl", "/wxmenulist.html");
			map.put("message", "用户没有登陆!");
			return map;
		}
		
		int iDisplayStart = Util.parseNumVl(request.getParameter("iDisplayStart"), 0);
		int iDisplayLength = Util.parseNumVl(request.getParameter("iDisplayLength"), 10);
		int page = iDisplayStart / iDisplayLength;
		if (page <= 0)
			page = 0;
		
		try {
			int orgid = userEbo.getOrgId();
			//目前 一个组织对应一个公众号
			List<WxAccountDto>  accList = wxAccountService.listAccount(orgid, null, null, 0, 1);
			//获取公众号id ，默认-1。
			int accId= accList.get(0).getAccid();
			String tmplCode = request.getParameter("tmplcode");
			String flag = request.getParameter("flag");
			List<WxMsgtmplUseInnerEbo> listTmplUse = wxMsgTmplService.listTmplUse(accId, tmplCode, flag, page, iDisplayLength);
			List<Object> auList = null;
			List<List<Object>> aaData = new ArrayList<List<Object>>();
			for (WxMsgtmplUseInnerEbo ms : listTmplUse) {
				auList = new ArrayList<Object>();
				auList.add(ms.getId());
				auList.add(Util.val(ms.getAccId()));
				auList.add(Util.val(ms.getAccount()));
				auList.add(Util.val(ms.getTmplCode()));
				auList.add(Util.val(ms.getTitle()));
				auList.add(Util.val(ms.getContent()));
				auList.add(Util.val(ms.getTmplId()));
				auList.add(Util.val(ms.getFlag()));
				aaData.add(auList);
			}
			int total = wxMsgTmplService.getTotalTmplUse(accId, tmplCode, flag);
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

	
	// 获得模板使用情况
	@RequestMapping("/addmsgtmplusejson.html")
	public @ResponseBody
	Map<String, Object> addmsgtmplusejson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);
		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
		if (userEbo == null) {
			map.put("result", -1);
			map.put("returnUrl", "/wxmenulist.html");
			map.put("message", "用户没有登陆!");
			return map;
		}
		try {
			int orgid = userEbo.getOrgId();
			//目前 一个组织对应一个公众号
			List<WxAccountDto>  accList = wxAccountService.listAccount(orgid, null, null, 0, 1);
			//获取公众号id ，默认-1。
			int accId= accList.get(0).getAccid();
			String code =request.getParameter("code");
			
			WxMsgtmplUseEto wxMsgtmplUseEto = new WxMsgtmplUseEto();
			wxMsgtmplUseEto.setAccId(accId);
			wxMsgtmplUseEto.setFlag(WxConstant.FLAG_YES);
			WxMsgtmplLibEbo WxMsgtmplLibEbo = wxMsgTmplService.getTmpLib(0, code, null);
			if(WxMsgtmplLibEbo ==null){
				map.put("result", -1);
				map.put("message", "该消息模板不存在");
			}
			wxMsgtmplUseEto.setTmplId(WxMsgtmplLibEbo.getId());
			wxMsgTmplService.addTmplUse(wxMsgtmplUseEto);
			map.put("result", 1);
			map.put("message", "添加使用成功");
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}	

	// 获得内容
	@RequestMapping("/wxgetmsgtmplcontentjson.html")
	public @ResponseBody
	Map<String, Object> getMsgTmlpContentjson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
		if (userEbo == null) {
			map.put("result", -1);
			map.put("message", "用户没有登陆!");
			return map;
		}

		try {
			// code获取title
			String code = request.getParameter("code");
			WxMsgtmplContentEbo content = wxMsgTmplService.getTmpLContent(0, code);
			if(content == null){
				map.put("result", 0);
				map.put("message", "没有对应code='"+code+"'的模板内容!");
				return map;
			}
			
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("content", content.getContent());
			data.put("demo", content.getDemoSample());
			map.put("data", data);
			map.put("result", 1);
			map.put("message", "获得模板内容成功!");
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}	

}

