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
import org.ybk.basic.util.DateUtil;
import org.ybk.basic.util.Util;

import com.kjplus.constant.Constant;
import com.kjplus.constant.ConstantPageFtl;
import com.kjplus.constant.ConstantUrlWx;
import com.kjplus.dto.ActivityEnrolInnerDto;
import com.kjplus.dto.IDNameDto;
import com.kjplus.dto.PersonServiceDto;
import com.kjplus.dto.UserPersonDto;
import com.kjplus.dto.UserSess;
import com.kjplus.model.ActivityEnrolEbo;
import com.kjplus.model.CalendarInfoEbo;
import com.kjplus.model.inner.CalInfoInnerEbo;
import com.kjplus.service.IActivityEnrolService;
import com.kjplus.service.ICalendarMainService;
import com.kjplus.service.IUserService;
import com.ybk.exception.DataException;

@Controller
public class CalendarCtrl {

	private static Logger logger = Logger.getLogger(CalendarCtrl.class);
	@Autowired
	private ICalendarMainService calMainService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IActivityEnrolService activityEnrolService;
	
	@RequestMapping(ConstantUrlWx.WX_SCHEDULE)
	public ModelAndView createDocument(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = mav.getModel();
		mav.setViewName(ConstantPageFtl.WX_SCHEDULE);
		map.put("code", 200);
		UserSess userEbo = (UserSess) request.getSession().getAttribute(Constant.KJMB_SESS);
		if(userEbo == null){
			return new ModelAndView("redirect:" + ConstantUrlWx.WX_MBLOGIN + "?returnUrl=" + ConstantUrlWx.WX_SCHEDULE);
		}
		
		int uid = Util.parseNumVl(request.getParameter("uid"), userEbo.getUid());
		try {
			List<UserPersonDto> listUserPerson = userService.listUserPerson(userEbo.getUid(), 0,userEbo.getOrgId(), Constant.FLAG_YES, null);
			List<IDNameDto> listPerson = new ArrayList<IDNameDto>();
			for (PersonServiceDto person : listUserPerson.get(0).getPrsnServices()) {
				IDNameDto idn = new IDNameDto();
				idn.setId(person.getPersonId());
				idn.setCode(person.getPersonCode());
				idn.setName(person.getPersonName());
				listPerson.add(idn);
			}
			map.put("listperson", listPerson);
			//TODO 是否需要组织筛选
			//String frmDay =  request.getParameter("frmday");
			//String endDay =  request.getParameter("endday");
			//TODO
			List<CalInfoInnerEbo> listCalInfoInnerEbo = new ArrayList<CalInfoInnerEbo>();
			//List<CalInfoInnerEbo> listCalInfoInnerEbo = calMainService.listCalInfoInnerEbo(0, null, 0,"Y", "Y", frmDay, endDay);
			map.put("listcal", listCalInfoInnerEbo);
			//个人参加日历活动
			List<ActivityEnrolInnerDto> listActEnrolInner = activityEnrolService.listActEnrolInner(uid, "DOC", 0, null, null, 0, 100);
			List<CalendarInfoEbo> listCalInfo = listActEnrolInner.get(0).getListCalInfo();
			
			map.put("listcalinfo", listCalInfo);
			map.put("result", 1);
			
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return mav;
	}

	//日历活动详情
	@RequestMapping(value = "/getcalinfojson.html")
	public @ResponseBody
	Map<String, Object> getCalInfoJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		try {
			int id = Util.parseNumVl(request.getParameter("infoid"), 0);
			CalendarInfoEbo calInfo = calMainService.getCalInfoByIdOrCode(id, null);
			map.put("data", calInfo);
			map.put("result", 1);
		} catch (DataException e) {
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}	
   
	//添加或修改
	@RequestMapping(value = "/addormoditfyactivityenrolJson.html")
	public @ResponseBody
	Map<String, Object> addOrModifyActivityEnrolJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		
		int docInfoId = Util.parseNumVl(request.getParameter("docinfoid"), 0);
		int calInfoId = Util.parseNumVl(request.getParameter("calinfoid"), 0);
		int actId = Util.parseNumVl(request.getParameter("actid"), 0);
		try {
			if(actId == 0){
				int joinPerson = 1;
				calMainService.updateCalInfo(calInfoId, null, null, null, 0, joinPerson, "Y", 0, 0);
	
				ActivityEnrolEbo activityEnrolEbo = new ActivityEnrolEbo();
				activityEnrolEbo.setUid(docInfoId);
				activityEnrolEbo.setUserType("DOC");
				activityEnrolEbo.setMainId(calInfoId);
				activityEnrolEbo.setMainType("CA");
				activityEnrolEbo.setStatus("E");
				activityEnrolEbo.setEnrolTime(DateUtil.getCurDayBeginInSec());
				activityEnrolService.addActEnrolEbo(activityEnrolEbo);
				map.put("result", 1);
				map.put("message", "报名成功!");
			}else{
				int joinPerson = -1;
				calMainService.updateCalInfo(calInfoId, null, null, null, 0, joinPerson, "Y", 0, 0);
				activityEnrolService.updateActEnrolEbo(actId, 0, null, 0, null, "C");
				map.put("result", 1);
				map.put("message", "取消报名成功!");
			}
		} catch (DataException e) {
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}
}
