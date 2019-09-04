package com.kjplus.rest;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.ybk.basic.util.Util;

import com.kjplus.constant.*;
import com.kjplus.dto.*;
import com.kjplus.service.IUserService;

@Controller
public class MyHealthyCtrl {

	private static Logger logger = Logger.getLogger(MyHealthyCtrl.class);

	@Autowired
	private IUserService userService;

	//我的健康 页面跳转
	@RequestMapping(value = ConstantUrlWx.WX_MYHEALTH)
	public ModelAndView myHealth(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(ConstantPageFtl.WX_MYHEALTH);
		Map<String, Object> map = mav.getModelMap();
		map.put("code", 200);
		
		UserSess ui = (UserSess) request.getSession().getAttribute(Constant.KJMB_SESS);
		if (ui == null) {
			return new ModelAndView("redirect:" + ConstantUrlWx.WX_MBLOGIN + "?returnUrl="
					+ ConstantUrlWx.WX_MYFAMILY);
		}
		
		try {
			List<UserPersonDto> listUserPrsn = userService.listUserPerson(ui.getUid(), 0, ui.getOrgId(),
					Constant.FLAG_YES, null);
			List<PersonServiceDto> prsnServices = new ArrayList<PersonServiceDto>();
			if(listUserPrsn.size() > 0)
				prsnServices = listUserPrsn.get(0).getPrsnServices();
			List<MblMyFamilyDto> listData = new ArrayList<MblMyFamilyDto>();
			MblMyFamilyDto mbl = null;
			for (PersonServiceDto prsn : prsnServices) {
				mbl = new MblMyFamilyDto();
				mbl.setPrsnCode(prsn.getPersonCode());
				mbl.setName(Util.val(prsn.getPersonName()));//用户名
				listData.add(mbl);
			}
			map.put("listData", listData);
			map.put("result", 1);
			map.put("message", "我的家庭成员信息获取成功！");
		} catch (Exception e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		
		return mav;
	}
	
}
