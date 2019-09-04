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
import com.kjplus.model.DocumentInfoEbo;
import com.kjplus.qto.FollowupMainQto;
import com.kjplus.service.IDocInfoService;
import com.kjplus.service.IFollowupService;
import com.kjplus.service.IUserService;

@Controller
public class FollowupCtrl {

	private static Logger logger = Logger.getLogger(FollowupCtrl.class);

	@Autowired
	private IUserService userService;
	@Autowired
	private IFollowupService followupService;
	@Autowired
	private IDocInfoService docInfoService;

	// 随访访视
	@RequestMapping(value = ConstantUrlWx.WX_MEASURE_RECORD_1)
	public ModelAndView measureRecord1(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();

		mav.setViewName(ConstantPageFtl.WX_MEASURE_RECORD_1);
		Map<String, Object> map = mav.getModelMap();
		map.put("code", 200);

		UserSess ui = (UserSess) request.getSession().getAttribute(Constant.KJMB_SESS);
		if (ui == null) {
			return new ModelAndView("redirect:" + ConstantUrlWx.WX_MBLOGIN + "?returnUrl="
					+ ConstantUrlWx.WX_APPOINT_SERVICE);
		}

		try {
			List<UserPersonDto> listUserPrsn = userService.listUserPerson(ui.getUid(), 0, ui.getOrgId(),
					Constant.FLAG_YES, null);
			// 只会获取到一个数据
			StringBuffer prsnIdStr = new StringBuffer();
			List<PersonServiceDto> listPrsn = listUserPrsn.get(0).getPrsnServices();
			int length = listPrsn.size();
			for (int i = 0; i < length; i++) {
				if (i != 0)
					prsnIdStr.append(",");
				prsnIdStr.append(listPrsn.get(i).getPersonId());
			}
			FollowupMainQto fupQto = new FollowupMainQto();
			fupQto.setPrsnIdStr(prsnIdStr.toString());
			List<FollowupMainDto> listFlpe = followupService.listFollowupMainDto(fupQto, 0, 10);
			// 页面数据
			List<MblFollowupDto> listData = new ArrayList<MblFollowupDto>();

			for (FollowupMainDto flpe : listFlpe) {
				MblFollowupDto mbl = new MblFollowupDto();
				// 随访id
				mbl.setFpleId(flpe.getId());
				mbl.setPrsnId(flpe.getPrsnId());
				// 头像
				DocumentInfoEbo doc = docInfoService.getDocinfoByIdOrCode(flpe.getPrsnId(), null);
				if (doc != null)
					mbl.setUrl(doc.getHeadIconUrl());
				mbl.setName(flpe.getPrsnName());
				// 随访年月日
				mbl.setDay(DateUtil.formatDate(flpe.getFlpeDay()));
				// 随访具体时间 时分
				Date data = DateUtil.parseTimeStrInSec(flpe.getFlpeTime());
				String time = DateUtil.formatTime(data);
				time = time.substring(time.length() - 8, time.length() - 3);
				mbl.setTime(time);
				mbl.setMemo(flpe.getTabCfgName());
				listData.add(mbl);
			}
			// 列表与登录用户有关系的所有人的随访记录 按时间倒序
			map.put("listData", listData);
			map.put("result", 1);
			map.put("message", "随访列表加载成功！");
		} catch (Exception e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return mav;
	}
}
