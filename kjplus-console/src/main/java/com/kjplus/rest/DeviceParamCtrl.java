package com.kjplus.rest;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.ybk.basic.util.*;

import com.kjplus.constant.ConsolePageFtl;
import com.kjplus.constant.Constant;
import com.kjplus.dto.*;
import com.kjplus.eto.*;
import com.kjplus.model.*;
import com.kjplus.service.*;
import com.ybk.exception.DataException;

@Controller
public class DeviceParamCtrl {

	private static Logger logger = Logger.getLogger(DeviceParamCtrl.class);
	
	@Autowired
	private IDeviceParamService dvcParamService;
	@Autowired
	private ISysFuncService funcService;
	
	// 设备列表
	@RequestMapping("/dvcparamlist.html")
	public ModelAndView orgList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = mav.getModel();
		mav.setViewName("dvcparam_list");

		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJCONSOLE_SESS);
		if (userEbo == null) {
			mav.setViewName(ConsolePageFtl.LOGIN_ACE);
			map.put("result", -1);
			map.put("returnUrl", "/adminuserlist.html");
			map.put("message", "用户没有登陆!");
			return mav;
		}
		
		try {
			SysFuncEbo func = funcService.getFuncByMenu("dvcparamlist.html", Constant.MENU_TYPE_CONSOLE);
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

	// adminUser列表
	@RequestMapping(value = "/dvcparamlistjson.html")
	public @ResponseBody
	Map<String, Object> adminUserListJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		int iDisplayStart = Util.parseNumVl(request.getParameter("iDisplayStart"), 0);
		int iDisplayLength = Util.parseNumVl(request.getParameter("iDisplayLength"), 10);
		int page = iDisplayStart / iDisplayLength;
		if (page <= 0)
			page = 0;
			
		try {
			String dvcParam = request.getParameter("dvcparam");
			String dvcType = request.getParameter("dvctype");
			String paramType = request.getParameter("paramtype");
			String flag = request.getParameter("flag");
			List<DeviceParamEbo> dvcParams = dvcParamService.listDeviceParamList(dvcType, paramType, dvcParam, flag,page,iDisplayLength);
			List<Object> auList = null;
			List<List<Object>> aaData = new ArrayList<List<Object>>();
			for (DeviceParamEbo dvc : dvcParams) {
				auList = new ArrayList<Object>();
				auList.add(dvc.getId());
				auList.add(Util.val(dvc.getDeviceType()));
				auList.add(Util.val(dvc.getParamType()));
				auList.add(Util.val(dvc.getDeviceParam()));
				auList.add(Util.val(dvc.getFlag()));
				auList.add(Util.val(DateUtil.formatDate(dvc.getCreateTime())));
				aaData.add(auList);				
			}
			int total = dvcParamService.getTotalDeviceParam(dvcType, paramType, dvcParam, flag);
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

	
	//设备添加
	@RequestMapping(value = "/adddvcparam.html")
	public @ResponseBody
	Map<String, Object> addadminuser(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		try {
			String dvcParam = request.getParameter("dvcparam");
			String dvcType = request.getParameter("dvctype");
			String paramType = request.getParameter("paramtype");
			if(Util.isNull(dvcParam)){
				map.put("result", -1);
				map.put("message", "输入的设备参数不能为空 ");
			}
			DeviceParamEbo  dvcEbo =  dvcParamService.getDeviceParam(0, dvcParam);
			if( dvcEbo != null){
				map.put("result", -1);
				map.put("message", "该的设备已存在，deviceParam = "+dvcParam);
			}
			//设备类型。M手机/I IPAD/S血糖仪/P血压仪
			if(Util.isNotNull(dvcType)){
				if(dvcType.equals("M"))
					dvcType = "M";
				else if(dvcType.equals("I"))
					dvcType = "I";
				else if(dvcType.equals("S"))
					dvcType = "S";
				else if(dvcType.equals("P"))
					dvcType = "P";
				else{
					map.put("result", -1);
					map.put("message", "输入的设备类型不存在，deviceType = "+dvcType);
				}
			}else{
				map.put("result", -1);
				map.put("message", "输入的设备类型不能为空 ");
			}
			
			//MAC/SN序列号
			if(Util.isNotNull(paramType)){
				if(paramType.equals("MAC"))
					paramType = "MAC";
				else if(paramType.equals("SN"))
					paramType = "SN";
				else{
					map.put("result", -1);
					map.put("message", "输入的参数类型不存在，paramType = "+paramType);
				}
			}else{
				map.put("result", -1);
				map.put("message", "输入的参数类型不能为空 ");
			}
			
			DvcParamEto dvcParamEto = new DvcParamEto();
			dvcParamEto.setDeviceType(dvcType);
			dvcParamEto.setParamType(paramType);
			dvcParamEto.setDeviceParam(dvcParam);
			dvcParamService.addDeviceParam(dvcParamEto);
			map.put("result", 1);
			map.put("message", "添加设备成功");
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}
}

