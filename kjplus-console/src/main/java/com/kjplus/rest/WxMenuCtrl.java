package com.kjplus.rest;

import java.util.*;

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

import com.kjplus.constant.*;
import com.kjplus.dto.FuncDto;
import com.kjplus.dto.KjAdminUserDto;
import com.kjplus.model.SysFuncEbo;
import com.kjplus.service.ISysFuncService;
import com.ybk.exception.DataException;
import com.ykisswx.constant.WxConstant;
import com.ykisswx.dto.WxAccountDto;
import com.ykisswx.dto.WxMenuDto;
import com.ykisswx.model.WxAccountEbo;
import com.ykisswx.model.WxMenuEbo;
import com.ykisswx.service.IWxAccountService;
import com.ykisswx.service.IWxMenuService;

@Controller
public class WxMenuCtrl {

	private static Logger logger = Logger.getLogger(WxMenuCtrl.class);
	@Autowired
	private ISysFuncService funcService;
	@Autowired
	private IWxMenuService wxMenuService;
	@Autowired
	private IWxAccountService wxAccountService;

	// menu列表
	@RequestMapping("/wxmenulist.html")
	public ModelAndView menuList(HttpServletRequest request, HttpServletResponse response) {
	
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = mav.getModel();
		mav.setViewName("wxmenu_list");
		
		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJCONSOLE_SESS);
		if (userEbo == null) {
			mav.setViewName(ConsolePageFtl.LOGIN_ACE);
			map.put("result", -1);
			map.put("returnUrl", "/wxmenulist.html");
			map.put("message", "用户没有登陆!");
			return mav;
		}
		
		try{
			SysFuncEbo func = funcService.getFuncByMenu("adminuserlist.html", Constant.MENU_TYPE_CONSOLE);
			if (func != null)
				map.put("pmenu", func.getPid());
			else
				map.put("pmenu", -1);
			List<FuncDto> funcs = funcService.listUserFunc(userEbo.getUid());
			
			List<WxAccountDto> accs = wxAccountService.listAccount(0, null, null, 0, 10);
			map.put("accs", accs);
			
			map.put("funcs", funcs);
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return mav;
	}
	
	// menu父类列表
	@RequestMapping("/wxmenulistjson.html")
	public @ResponseBody
	Map<String, Object> orgList(HttpServletRequest request, HttpServletResponse response) {

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
			//获取公众号id ，默认-1。
			int accId= Util.parseNumVl(request.getParameter("accid"), 0);
			String name = request.getParameter("name");
			String flag = request.getParameter("flag");
			List<WxMenuDto>  menus= wxMenuService.listMenuByAccid(accId, name,flag, false,true);
			List<Object> auList = null;
			List<List<Object>> aaData= new ArrayList<List<Object>>();
			//所有底层menu pid = 0
			for(WxMenuDto mn : menus){
				auList = new ArrayList<Object>();
				auList.add(mn.getId());
				auList.add(mn.getAccid());
				auList.add(Util.val(mn.getOrderSeq()));
				auList.add(Util.val(mn.getName()));
				auList.add(Util.val(mn.getPid()));
				auList.add(Util.val(mn.getPname()));
				auList.add(Util.val(mn.getType()));
				auList.add(Util.val(mn.getFlag()));
				auList.add(Util.val(mn.getUrl()));
				auList.add(Util.val(mn.getKey()));
				auList.add(Util.val(mn.getNote()));
				aaData.add(auList);
			}
			map.put("aaData", aaData);
			map.put("result", 1);
			map.put("iTotalRecords", menus.size());
			map.put("iTotalDisplayRecords", menus.size());
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	//menu菜单(包括父类，子类)
	@RequestMapping(value = "/wxmenujson.html")
	public @ResponseBody
	Map<String, Object> mgrOrgListJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);
		
		//显示相应menu下菜单
		int id= Util.parseNumVl(request.getParameter("id"), 0);
		try {
			WxMenuEbo  wxMenu = wxMenuService.getWxMenuById(id);
			
			//获取menu全部信息 业务层可修改。
			List<WxMenuEbo>  menus= new ArrayList<WxMenuEbo>();
			menus.add(wxMenu);
			List<Object> auList = null;
			List<List<Object>>  aaData= new ArrayList<List<Object>>();
			for(WxMenuEbo mn : menus){
				auList = new ArrayList<Object>();
				auList.add(mn.getId());
				auList.add(Util.val(mn.getOrderSeq()));
				auList.add(Util.val(mn.getName()));
				auList.add(Util.val(mn.getPid()));
				auList.add(Util.val(mn.getType()));
				auList.add(Util.val(mn.getFlag()));
				auList.add(Util.val(mn.getUrl()));
				auList.add(Util.val(mn.getKey()));
				auList.add(Util.val(mn.getNote()));
				//auList.add(mn.getCreateTime());
				aaData.add(auList);
			}
			map.put("aaData", aaData);
			map.put("result", 1);
			map.put("iTotalRecords", menus.size());
			map.put("iTotalDisplayRecords", menus.size());
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	//menu添加
	@RequestMapping(value = "/addmenujson.html")
	public @ResponseBody
	Map<String, Object> addMenuJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);
		
		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJCONSOLE_SESS);
		if (userEbo == null) {
			map.put("result", -1);
			map.put("returnUrl", "/wxmenulist.html");
			map.put("message", "用户没有登陆!");
			return map;
		}
		
		String name = request.getParameter("name");
		String flag = WxConstant.FLAG_YES;
		String url = request.getParameter("url");
		String type = request.getParameter("type");
		String key = request.getParameter("key");
		String note = request.getParameter("note");
		int orderSeq = Util.parseNumVl(request.getParameter("orderSeq"), 0);
		
		try {
			int accId = Util.parseNumVl(request.getParameter("accid"), 0);
			if(accId != 0){
				WxAccountEbo wxAcco =	wxAccountService.getAccountInfo(accId, null);
				if(wxAcco == null){
					map.put("result", -1);
					map.put("message", "该微信公众号不存在!");
					return map;
				}
			}
			int pid = Util.parseNumVl(request.getParameter("pid"), 0);
			//添加二级菜单时，accid从一级菜单中获取
			if( pid !=0 ){
				WxMenuEbo menu = wxMenuService.getWxMenuById(pid);
				accId =  menu.getAccid();
			}
			WxMenuEbo wxMenuEbo =  new WxMenuEbo();
			wxMenuEbo.setPid(pid);
			if(wxMenuEbo.getPid() !=0 ){
				WxMenuEbo menuEbo = wxMenuService.getWxMenuById(wxMenuEbo.getPid());
				if(menuEbo == null || menuEbo.getPid() !=0){
					map.put("result", -1);
					map.put("message", "该微信menu不可添加子类!");
					return map;
				}
			}
			//判断该组织是否可以添加菜单
			int count = wxMenuService.getMenuNum(accId, pid, flag);
			if(count < 0){
				map.put("result", -1);
				map.put("message", "该组织不可再添加此类菜单，已经达到上限");
				return map;
			}
			
			wxMenuEbo.setPid(pid);
			wxMenuEbo.setAccid(accId);
			wxMenuEbo.setName(name);
			wxMenuEbo.setFlag(flag);
			wxMenuEbo.setUrl(url);
			wxMenuEbo.setType(type);
			wxMenuEbo.setKey(key);
			wxMenuEbo.setNote(note);
			wxMenuEbo.setOrderSeq(orderSeq);
			wxMenuEbo.setCreateTime(DateUtil.newTime());
			
			wxMenuService.addMenu(wxMenuEbo);
			map.put("accid", accId);
			map.put("result", 1);
			map.put("message", "添加menu成功");
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	//menu修改
	@RequestMapping(value = "/updatemenujson.html")
	public @ResponseBody
	Map<String, Object> updateMenuJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);
		
		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJCONSOLE_SESS);
		if (userEbo == null) {
			map.put("result", -1);
			map.put("returnUrl", "/wxmenulist.html");
			map.put("message", "用户没有登陆!");
			return map;
		}
		
		String name = request.getParameter("name");
		String flag = WxConstant.FLAG_YES;
		String url = request.getParameter("url");
		String key = request.getParameter("key");
		String note = request.getParameter("note");
		int orderSeq = Util.parseNumVl(request.getParameter("orderSeq"), 0);

		
		try {
			int id = Util.parseNumVl(request.getParameter("id"), 0);
			WxMenuEbo menu= wxMenuService.getWxMenuById(id);
			if(menu == null){
				map.put("result", -1);
				map.put("returnUrl", "/wxmenulist.html");
				map.put("message", "该menux信息不存在");
				return map;
			}
			if(flag.equals("Y"))
				flag = WxConstant.FLAG_YES;
			else if(flag.equals("N"))
				flag = WxConstant.FLAG_NO;
			else{
				map.put("result", -1);
				map.put("returnUrl", "/wxmenulist.html");
				map.put("message", "flag格式不对");
				return map;
			}
			wxMenuService.updateMenu(id, name, url, flag,key, note,orderSeq);
			map.put("result", 1);
			map.put("message", "修改menu成功");
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}
}
