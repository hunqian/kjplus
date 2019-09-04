package com.kjplus.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.kjplus.service.ITableService;
import com.mq.call.CommandFactory;
import com.mq.call.MessageSendCall;
import com.mq.message.MsgCommand;

@Controller
public class TestCtrl {

	private static Logger logger = Logger.getLogger(TestCtrl.class);
	@Autowired
	private ITableService tableService;
	
	// 预约
	@RequestMapping("appoints.html")
	public ModelAndView createDocument(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("appoint_list");
		try {
			MsgCommand cmd = CommandFactory.getMsgCommand();
			cmd.setCmd("123");
			// 发送对象消息
			MessageSendCall.topicProxyOjbectMessageSend(cmd);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return mav;
	}
	
	/*@RequestMapping(value = "/mgr_appointsjson.html")
	public @ResponseBody
	Map<String, Object> appoinmentjson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		int iDisplayStart = Util.parseNumVl(request.getParameter("iDisplayStart"), 0);
		int iDisplayLength = Util.parseNumVl(request.getParameter("iDisplayLength"), 10);
		int page = iDisplayStart / iDisplayLength;
		if (page <= 0)
			page = 0;

		try {
			int orgId = -1;
			int prvnId = -1;
			int cityId = -1;
			String flag = null;
			List<Integer> tagIds = new ArrayList<Integer>();
			List<PersonDto> persons = personService.listPerson(orgId, prvnId, cityId, flag, tagIds, page, iDisplayLength);
			int totalRec = personService.getTotalPerson(orgId, prvnId, cityId, flag, tagIds);

			List<List<Object>> aaData = new ArrayList<List<Object>>();
			List<Object> aaList = null;
			
			for (PersonDto p : persons) {
				aaList = new ArrayList<Object>();
				aaList.add(p.getPrsnId());
				aaList.add(p.getName());
				aaList.add(p.getGender());
				aaList.add("aa");
				//aaList.add(DateUtil.getDayofCal(DateUtil.currDay()) - DateUtil.getDayofCal(p.getBirthday()));
				StringBuffer tagBuf = new StringBuffer();
				for(int j=0;j<p.getTags().size();j++){
					if(j != 0)
						tagBuf.append(",");
					tagBuf.append(p.getTags().get(j).getRefValName());
				}
				aaList.add(JSON.toJSONString(p.getTags()));
				aaList.add(p.getIdNumber());
				aaList.add(p.getMobileNum());
				aaList.add(p.getFamilyAddr());
				aaList.add("甲乙");
				aaData.add(aaList);
			}
			map.put("aaData", aaData);
			map.put("result", 1);
			map.put("iTotalRecords", totalRec);
			map.put("iTotalDisplayRecords", totalRec);
		} catch (DataException e) {
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}	*/
}
