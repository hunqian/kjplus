package com.kjplus.rest;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.ybk.basic.util.DateUtil;

import com.kjplus.constant.*;
import com.kjplus.dto.*;
import com.kjplus.model.ServiceListEbo;
import com.kjplus.model.ServiceSumEbo;
import com.kjplus.service.IServiceSumService;
import com.kjplus.service.IUserService;

@Controller
public class MyScoreCtrl {

	private static Logger logger = Logger.getLogger(MyScoreCtrl.class);

	@Autowired
	private IUserService userService;
	@Autowired
	private IServiceSumService serviceSumService;
	
	@RequestMapping(value = ConstantUrlWx.WX_MYSCORE)
	public ModelAndView myScore(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(ConstantPageFtl.WX_MYSCORE);
		Map<String, Object> map = mav.getModelMap();

		UserSess ui = (UserSess) request.getSession().getAttribute(Constant.KJMB_SESS);
		if (ui == null) {
			return new ModelAndView("redirect:" + ConstantUrlWx.WX_MBLOGIN + "?returnUrl=" + ConstantUrlWx.WX_MYFAMILY);
		}
		try{
			ServiceSumEbo sum = serviceSumService.getSrvSumByBody(ui.getUid(), "U");
			//积分减少目录
			List<ServiceListEbo> listDecrList = null;
			//积分增加目录
			List<ServiceListEbo> listIncrList = null;
			if(sum!=null){
				listDecrList = serviceSumService.listSrvList(sum.getId(), 0, null, 0, 0, "D", 0, 10);
				listIncrList = serviceSumService.listSrvList(sum.getId(), 0, null, 0, 0, "I", 0, 10);
			}
			List<MblScoreDto> listDecrData = new ArrayList<MblScoreDto>();
			List<MblScoreDto> listIncrData = new ArrayList<MblScoreDto>();
			for (ServiceListEbo decr : listDecrList) {
				MblScoreDto sc = new MblScoreDto();
				sc.setMemo(decr.getMemo());
				sc.setDay(DateUtil.getDateStr(decr.getCreateTime()));
				sc.setPoint(decr.getServicePoint());
				listDecrData.add(sc);
			}
			for (ServiceListEbo incr : listIncrList) {
				MblScoreDto sc = new MblScoreDto();
				sc.setMemo(incr.getMemo());
				sc.setDay(DateUtil.getDateStr(incr.getCreateTime()));
				sc.setPoint(incr.getServicePoint());
				listIncrData.add(sc);
			}
			map.put("sum", sum.getTotalPoint());
			map.put("listDecrData", listDecrData);
			map.put("listIncrData", listIncrData);
			map.put("result", 1);
			map.put("message", "获取积分详情成功！");
		} catch (Exception e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return mav;
	}

}
