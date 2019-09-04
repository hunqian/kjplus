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
import com.kjplus.model.SysRefValueEbo;
import com.kjplus.qto.ExamQto;
import com.kjplus.service.IDocInfoService;
import com.kjplus.service.IExamService;
import com.kjplus.service.ISysBaseService;
import com.kjplus.service.IUserService;

@Controller
public class ExamCtrl {

	private static Logger logger = Logger.getLogger(ExamCtrl.class);

	@Autowired
	private IUserService userService;
	@Autowired
	private IExamService examService;
	@Autowired
	private ISysBaseService baseService;
	@Autowired
	private IDocInfoService docInfoService;

	// 测量记录
	@RequestMapping(value = ConstantUrlWx.WX_MEASURE_RECORD)
	public ModelAndView measureRecord(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(ConstantPageFtl.WX_MEASURE_RECORD);
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
			ExamQto examQto = new ExamQto();
			examQto.setPrsnIdStr(prsnIdStr.toString());
			List<ExamMainSimpleDto> listExam = examService.listExamMainInnerEbo(examQto, 0, 10);
			List<MblExamDto> listData = new ArrayList<MblExamDto>();
			for (ExamMainSimpleDto exam : listExam) {
				MblExamDto ex = new MblExamDto();
				ex.setExamId(exam.getId());
				ex.setPrsnId(exam.getPrsnId());
				DocumentInfoEbo doc = docInfoService.getDocinfoByIdOrCode(exam.getPrsnId(), null);
				if(doc !=null)
					ex.setUrl(doc.getHeadIconUrl());
				ex.setName(exam.getPrsnName());
				ex.setDay(exam.getExamDay());
				Date data = DateUtil.parseTimeStrInSec(exam.getExamTime());
				String time = DateUtil.formatTime(data); 
				time = time.substring(time.length() - 8, time.length() - 3);
				ex.setTime(time);
				if(exam.getExamTypeId()>0){
					SysRefValueEbo ref = baseService.getRefVlById(exam.getExamTypeId());
					if(ref!=null)
						ex.setMemo(ref.getName());
				}else
					ex.setMemo("未注明");
				listData.add(ex);
			}
			// 列表与登录用户有关系的所有人的测量记录 按时间倒序
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
