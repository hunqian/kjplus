package com.kjplus.rest;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kjplus.constant.ConsolePageFtl;
import com.kjplus.constant.Constant;
import com.kjplus.dto.FuncDto;
import com.kjplus.dto.KjAdminUserDto;
import com.kjplus.model.SysFuncEbo;
import com.kjplus.service.IExamService;
import com.kjplus.service.ISysFuncService;
import com.ybk.exception.DataException;

//import org.ybk.basic.image.ImageBasePpUtil;

@Controller
public class JurisdictionListMgrCtrl {

	private static Logger logger = Logger.getLogger(JurisdictionListMgrCtrl.class);
	@Autowired
	private ISysFuncService funcService;
	@Autowired
	private IExamService examService;

	// 签约列表
	@RequestMapping("/jurisdictionlist.html")
	public ModelAndView createDocument(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = mav.getModel();
		mav.setViewName("blank");

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJMGR_SESS);
		if (userEbo == null) {
			mav.setViewName(ConsolePageFtl.LOGIN_ACE);
			map.put("result", -1);
			map.put("returnUrl", "/jurisdictionlist.html");
			map.put("message", "用户没有登陆!");
			return mav;
		}
		try {
			SysFuncEbo func = funcService.getFuncByMenu("jurisdictionlist.html", Constant.MENU_TYPE_MANAGER);
			if (func != null)
				map.put("pmenu", func.getPid());
			else
				map.put("pmenu", "-1");
			List<FuncDto> funcs = funcService.listUserFunc(userEbo.getUid());
			map.put("funcs", funcs);
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return mav;
	}

}
