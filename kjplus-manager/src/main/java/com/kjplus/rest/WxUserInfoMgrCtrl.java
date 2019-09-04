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
import com.kjplus.dto.KjAdminUserDto;
import com.kjplus.dto.OrgDto;
import com.kjplus.model.SysFuncEbo;
import com.kjplus.service.*;
import com.mq.util.DateUtil;
import com.ybk.exception.DataException;
import com.ykisswx.dto.WxUserInfoNnDto;
import com.ykisswx.model.WxUserInfoEbo;
import com.ykisswx.service.IWxAccountService;
import com.ykisswx.service.IWxUserInfoService;

@Controller
public class WxUserInfoMgrCtrl {

	private static Logger logger = Logger.getLogger(WxUserInfoMgrCtrl.class);
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
	@Autowired
	private IWxUserInfoService wxUserInfoService;

	// 微信列表
	@RequestMapping("/wxuserlist.html")
	public ModelAndView orgList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = mav.getModel();
		mav.setViewName("wxuser_list");

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
		if (userEbo == null) {
			mav.setViewName(ConsolePageFtl.LOGIN_ACE);
			map.put("result", -1);
			map.put("returnUrl", "/wxacclist.html");
			map.put("message", "用户没有登陆!");
			return mav;
		}

		try {
			SysFuncEbo func = funcService.getFuncByMenu("wxuserlist.html", Constant.MENU_TYPE_MANAGER);
			if (func != null)
				map.put("pmenu", func.getPid());
			else
				map.put("pmenu", -1);
			List<FuncDto> funcs = funcService.listUserFunc(userEbo.getUid());
			map.put("funcs", funcs);
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return mav;
	}

	// 微信用户列表
	@RequestMapping(value = "/mgr_wxuserlistjson.html")
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
			KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
			int orgid = userEbo.getOrgId();
			String nickname = request.getParameter("nickname");			
			List<WxUserInfoNnDto> users = wxUserInfoService.listUserByOrg(nickname, orgid,  page,  iDisplayLength);
			List<Integer> orgList = new ArrayList<Integer>();
			for (WxUserInfoNnDto u : users) {
				orgList.add(u.getOrgid());
			}
			List<OrgDto> orgs = orgService.listOrgByIds(orgList);
			
			List<List<Object>> aaData = new ArrayList<List<Object>>();
			List<Object> aaList = null;
			boolean found = false;
			for (WxUserInfoNnDto u : users) {
				found = false;
				aaList = new ArrayList<Object>();
				aaList.add(u.getId());
				aaList.add(Util.val(u.getNickname()));
				aaList.add(Util.val(u.getMobile()));
				aaList.add(Util.val(u.getEmail()));
				aaList.add(Util.val(u.getAge()));
				aaList.add(Util.val(u.getSex()));
				aaList.add(Util.val(u.getAddress()));
				aaList.add(Util.val(u.getArea()));
				aaList.add(Util.val(u.getCity()));
				aaList.add(Util.val(u.getProvince()));
				aaList.add(Util.val(u.getCountry()));
				aaList.add(Util.val(u.getLanguage()));
				aaList.add(Util.val(u.getPhoto()));
				aaList.add(Util.val(u.getRefUserId()));
				aaList.add(Util.val(u.getRefNickname()));
				aaList.add(u.getOrgid());
				for(OrgDto org : orgs){
					if(org.getId().intValue() == u.getOrgid().intValue()){
						aaList.add(org.getName());
						found = true;
						break;
					}
				}
				//如果没有找到，需要填一个空的位置
				if(found == false)
					aaList.add("");
				aaList.add(DateUtil.formatDateTime(u.getCreateTime()));
				aaData.add(aaList);				
			}
			int total = wxUserInfoService.getTotal(nickname, orgid);
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
	
	@RequestMapping(value = "/mgr_getwximgjson.html")
	public @ResponseBody
	Map<String, Object> getfaceJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		int uid = Util.parseNumVl(request.getParameter("uid"), 0);
		try {
			 WxUserInfoEbo wxUser = wxUserInfoService.getWxUserById(uid);
			if (uid == 0){
				map.put("result", -1);
				map.put("messge", "没有找到微信用户ID'" + uid + "'");
				return map;
			}
			Map<String, Object> data = new HashMap<String, Object>();

			data.put("face",wxUser.getPhoto());
			
			map.put("data", data);
			map.put("result", 1);

		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		} finally {
		}
		return map;
	}
	
	// 上传用户头像
	@RequestMapping(value = "/mgr_addwxuserinfofacelistjson.html")
	public @ResponseBody
	Map<String, Object> addwxuserinfofacelistjson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		try {
			String face = request.getParameter("face");
			int uid = Util.parseNumVl(request.getParameter("uid"), 0);
			
			if(uid != 0){
				wxUserInfoService.uploadFace(uid, face);
				map.put("result", 1);
				map.put("message", "上传头像成功");
			}else{
				map.put("result", -1);
				map.put("message", "上传头像失败");
			}
			
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

}
