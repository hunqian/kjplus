package com.kjplus.rest;

import java.util.*;

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
import com.kjplus.dto.KjAdminUserDto;
import com.kjplus.model.SysFuncEbo;
import com.kjplus.service.*;
import com.ybk.exception.DataException;
import com.ykisswx.eto.WxAccountEto;
import com.ykisswx.model.WxAccountEbo;
import com.ykisswx.service.IWxAccountService;

@Controller
public class WxAccInfoCtrl {

	private static Logger logger = Logger.getLogger(WxAccInfoCtrl.class);
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

	// 微信账户
	@RequestMapping("/wxaccinfo.html")
	public ModelAndView orgList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = mav.getModel();
		mav.setViewName("wxacc_info");

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
		if (userEbo == null) {
			mav.setViewName(ConsolePageFtl.LOGIN_ACE);
			map.put("result", -1);
			map.put("returnUrl", "/wxaccinfo.html");
			map.put("message", "用户没有登陆!");
			return mav;
		}

		try {
			SysFuncEbo func = funcService.getFuncByMenu("wxaccinfo.html", Constant.MENU_TYPE_MANAGER);
			if (func != null)
				map.put("pmenu", func.getPid());
			else
				map.put("pmenu", -1);
			List<FuncDto> funcs = funcService.listUserFunc(userEbo.getUid());
			map.put("funcs", funcs);

			//TODO 一个组织可以对应多个微信公众号
			WxAccountEbo account = null; //iWxAcountService.getAccByOrgid(userEbo.getOrgId());
			map.put("account", account);
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return mav;
	}

	// 添加修改微信
	@RequestMapping(value = "/mgr_addormodifywxaccinfojson.html")
	public @ResponseBody
	Map<String, Object> addOrModifyWxAccJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);
		
		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
		if (userEbo == null) {
			map.put("result", -1);
			map.put("message", "用户没有登陆!");
			return map;
		}
		
		try {
			int id = Util.parseNumVl(request.getParameter("id"), 0);
			String account = request.getParameter("account");
			String name = request.getParameter("name");
			String intro = request.getParameter("introduction");
			String mode = request.getParameter("mode");
			String appsecret = request.getParameter("appsecret");
			String appid = request.getParameter("appid");
			String token = request.getParameter("token");
			String encoaseskeyding = request.getParameter("encoaseskeyding");
			String type = request.getParameter("type");

			WxAccountEto wxAccountEto = new WxAccountEto();
			wxAccountEto.setAccount(account);
			wxAccountEto.setName(name);
			wxAccountEto.setIntroduction(intro);
			wxAccountEto.setMode(mode);
			wxAccountEto.setAppid(appid);
			wxAccountEto.setEncoaseskeyding(encoaseskeyding);
			wxAccountEto.setToken(token);
			wxAccountEto.setAppsecret(appsecret);
			wxAccountEto.setOrgid(userEbo.getOrgId());
			wxAccountEto.setType(type);
			if(id == 0){
				iWxAcountService.addAccountInfo(wxAccountEto);
				map.put("message", "添加微信账户成功！");
			}else{
				iWxAcountService.editAccAccount(id,wxAccountEto);
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

}
