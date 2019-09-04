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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.ybk.basic.util.Util;

import com.kjplus.constant.ConsolePageFtl;
import com.kjplus.constant.Constant;
import com.kjplus.dto.FuncDto;
import com.kjplus.dto.IDNameDto;
import com.kjplus.dto.KjAdminUserDto;
import com.kjplus.dto.OrgDto;
import com.kjplus.model.SysFuncEbo;
import com.kjplus.service.*;
import com.ybk.exception.DataException;
import com.ykisswx.dto.WxAccountDto;
import com.ykisswx.eto.WxAccountEto;
import com.ykisswx.model.WxAccountEbo;
import com.ykisswx.service.IWxAccountService;

@Controller
public class WxAccCtrl {

	private static Logger logger = Logger.getLogger(WxAccCtrl.class);
	@Autowired
	private IWxAccountService iWxAcountService;
	@Autowired
	private ISysFuncService funcService;
	@Autowired
	private ISysRegionsService regionsService;
	@Autowired
	private ISysBaseService baseService;
	@Autowired
	private IOrgService orgService;

	// 微信列表
	@RequestMapping("/wxacclist.html")
	public ModelAndView orgList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = mav.getModel();
		mav.setViewName("wxacc_list");

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJCONSOLE_SESS);
		if (userEbo == null) {
			mav.setViewName(ConsolePageFtl.LOGIN_ACE);
			map.put("result", -1);
			map.put("returnUrl", "/wxacclist.html");
			map.put("message", "用户没有登陆!");
			return mav;
		}

		try {
			SysFuncEbo func = funcService.getFuncByMenu("wxacclist.html", Constant.MENU_TYPE_CONSOLE);
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

	// 微信账户列表
	@RequestMapping(value = "/mgr_wxacclistjson.html")
	public @ResponseBody
	Map<String, Object> orgListJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		int iDisplayStart = Util.parseNumVl(request.getParameter("iDisplayStart"), 0);
		int iDisplayLength = Util.parseNumVl(request.getParameter("iDisplayLength"), 10);
		int page = iDisplayStart / iDisplayLength;
		if (page <= 0)
			page = 0;

		try {
			int orgid =Util.parseNumVl(request.getParameter("orgid"), 0);
			String type = request.getParameter("typeid");
			String account = request.getParameter("account");
			List<WxAccountDto> wxAccount = iWxAcountService.listAccount(orgid, account, type, page, iDisplayLength);
			List<Integer> orgList = new ArrayList<Integer>();
			for (WxAccountDto wxacc : wxAccount) {
				orgList.add(wxacc.getOrgid());
			}
			List<OrgDto> orgs = orgService.listOrgByIds(orgList);

			List<List<Object>> aaData = new ArrayList<List<Object>>();
			List<Object> aaList = null;
			boolean found = false;
			for (WxAccountDto wxacc : wxAccount) {
				found = false;
				aaList = new ArrayList<Object>();
				aaList.add(wxacc.getAccid());
				aaList.add(wxacc.getAccount());
				aaList.add(wxacc.getName());
				aaList.add(wxacc.getUrl());
				aaList.add(wxacc.getAccCode());
				aaList.add(wxacc.getIntroduction());
				aaList.add(wxacc.getAppid());
				aaList.add(wxacc.getAppsecret());
				aaList.add(wxacc.getToken());
				aaList.add(wxacc.getEncoaseskeyding());
				aaList.add(wxacc.getType());
				aaList.add(wxacc.getMode());
				aaList.add(wxacc.getOrgid());
				for (OrgDto org : orgs) {
					if (org.getId() == wxacc.getOrgid()) {
						aaList.add(org.getName());
						found = false;
						break;
					}
				}
				// 如果没有找到，需要填一个空的位置
				if (found == false)
					aaList.add("");
				aaData.add(aaList);
			}
			int totalRec = iWxAcountService.getTotalAccount(orgid, account, type);
			map.put("aaData", aaData);
			map.put("result", 1);
			map.put("iTotalRecords", totalRec);
			map.put("iTotalDisplayRecords", totalRec);
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 获得更新信息
	@RequestMapping(value = "/mgr_getywxaccjson.html")
	public @ResponseBody
	Map<String, Object> getWxAccJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);
		try {
			String wxAccCode = request.getParameter("wxacccode");
			WxAccountEbo accountInfoByCode = iWxAcountService.getAccountByAccCode(wxAccCode);
			if (accountInfoByCode != null) {
				Map<String, Object> data = new HashMap<String, Object>();

				data.put("id", accountInfoByCode.getId());
				data.put("orgid", accountInfoByCode.getOrgid());
				data.put("account", accountInfoByCode.getAccount());
				data.put("appid", accountInfoByCode.getAppid());
				data.put("appsecret", accountInfoByCode.getAppsecret());
				data.put("token", accountInfoByCode.getToken());
				data.put("encoaseskeyding", accountInfoByCode.getEncoaseskeyding());
				data.put("name", accountInfoByCode.getName());
				data.put("introduction", accountInfoByCode.getIntroduction());
				data.put("url", accountInfoByCode.getUrl());
				data.put("grade", accountInfoByCode.getGrade());
				data.put("type", accountInfoByCode.getType());
				data.put("code", accountInfoByCode.getCode());
				data.put("mode", accountInfoByCode.getMode());

				map.put("data", data);
			} else {
				map.put("result", 0);
				map.put("message", "没有找到对应微信账户信息!");
			}
			map.put("result", 1);
			map.put("message", "查找账户信息成功!");
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 添加修改组织
	@RequestMapping(value = "/mgr_addormodifywxaccjson.html")
	public @ResponseBody
	Map<String, Object> addOrModifyWxAccJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);
		try {
			String account = request.getParameter("account");
			String name = request.getParameter("name");
			String intro = request.getParameter("introduction");
			String mode = request.getParameter("modeid");//加密方式
			String appsecret = request.getParameter("appsecret");
			String appid = request.getParameter("appid");
			//String encoaseskeyding = request.getParameter("encoaseskeyding");
			String type = request.getParameter("typeid");//订阅号类型
			String code = request.getParameter("code");
			int orgid = Util.parseNumVl(request.getParameter("orgid"),0);//组织id
			
			WxAccountEto wxAccountEto = new WxAccountEto();
			wxAccountEto.setAccount(account);
			wxAccountEto.setName(name);
			wxAccountEto.setIntroduction(intro);
			wxAccountEto.setMode(mode);
			wxAccountEto.setAppid(appid);
			//wxAccountEto.setEncoaseskeyding(encoaseskeyding);
			wxAccountEto.setAppsecret(appsecret);
			wxAccountEto.setType(type);
			wxAccountEto.setOrgid(orgid);
			
			if (Util.isNull(code)) {
				iWxAcountService.addAccountInfo(wxAccountEto);
				map.put("message", "添加微信账户成功！");
			} else {
				WxAccountEbo wxAccountEbo = iWxAcountService.getAccountByAccCode(code);
				if (wxAccountEbo != null)
					iWxAcountService.editAccAccount(wxAccountEbo.getId(),wxAccountEto);
				map.put("message", "修改微信账户成功！");
			}
			map.put("result", 1);
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 微信acc列表
	@RequestMapping("/wxacclistjson.html")
	public @ResponseBody
	Map<String, Object> accList(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);
		
		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJCONSOLE_SESS);
		if (userEbo == null) {
			map.put("result", -1);
			map.put("returnUrl", "/wxmenulist.html");
			map.put("message", "用户没有登陆!");
			return map;
		}
		try{
			String account = request.getParameter("account");
			List<WxAccountDto>  accList = iWxAcountService.listAccount(-1, account, null, 0, 10);
			List<IDNameDto> idns = new ArrayList<IDNameDto>();
			for(WxAccountDto acc: accList){
				IDNameDto idn = new IDNameDto();
				idn.setId(acc.getAccid());
				idn.setName(acc.getAccount());
				idn.setCode(acc.getAccCode());
				idns.add(idn);
			}
			map.put("data", idns);
			map.put("result", 1);
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}	
}
