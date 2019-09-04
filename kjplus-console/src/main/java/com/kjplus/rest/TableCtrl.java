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
import org.ybk.basic.util.Util;

import com.kjplus.constant.ConsolePageFtl;
import com.kjplus.constant.Constant;
import com.kjplus.dao.IOrgDao;
import com.kjplus.dto.FuncDto;
import com.kjplus.dto.KjAdminUserDto;
import com.kjplus.dto.SysRegionsDto;
import com.kjplus.dto.TableCfgHeadDto;
import com.kjplus.dto.TableCfgLineDto;
import com.kjplus.model.*;
import com.kjplus.service.*;
import com.mq.util.DateUtil;
import com.ybk.exception.DataException;

@Controller
public class TableCtrl {

	private static Logger logger = Logger.getLogger(TableCtrl.class);
	@Autowired
	private IOrgDao orgDao;
	@Autowired
	private IOrgService orgService;
	@Autowired
	private ISysFuncService funcService;
	@Autowired
	private ISysRegionsService regionsService;
	@Autowired
	private ISysBaseService baseService;
	@Autowired
	private ITableService tableService;
	
	// 组织列表
	@RequestMapping("/tablecfglist.html")
	public ModelAndView orgList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = mav.getModel();
		mav.setViewName("table_cfg_list");
		
		KjAdminUserDto userEbo = (KjAdminUserDto) request.getSession().getAttribute(Constant.KJCONSOLE_SESS);
		if (userEbo == null) {
			mav.setViewName(ConsolePageFtl.LOGIN_ACE);
			map.put("result", -1);
			map.put("returnUrl", "/tablecfglist.html");
			map.put("message", "用户没有登陆!");
			return mav;
		}
		
		try{
			SysFuncEbo func = funcService.getFuncByMenu("tablecfglist.html", Constant.MENU_TYPE_CONSOLE);
			if (func != null)
				map.put("pmenu", func.getPid());
			else
				map.put("pmenu", -1);
			List<FuncDto> funcs = funcService.listUserFunc(userEbo.getUid());
			map.put("funcs", funcs);
			
			List<SysRegionsDto> prvnList = regionsService.listRegions(0);
			map.put("prvnList", prvnList);
			
			List<SysRefValueEbo> orgTypeList = baseService.listRefVlByRefCode("ORG_TYPE", Constant.FLAG_YES);
			map.put("orgTypeList", orgTypeList);
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return mav;
	}

	//表头信息
	@RequestMapping(value = "/tablecfgheadlistjson.html")
	public @ResponseBody
	Map<String, Object> tableCfgHeadListJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		int iDisplayStart = Util.parseNumVl(request.getParameter("iDisplayStart"), 0);
		int iDisplayLength = Util.parseNumVl(request.getParameter("iDisplayLength"), 10);
		int page = iDisplayStart / iDisplayLength;
		if (page <= 0)
			page = 0;
		try {
			String code = request.getParameter("code");
			int id = Util.parseNumVl(request.getParameter("id"), 0);
			//TODO flag判断  name  
			
			List<TableCfgHeadDto>  headList = tableService.listTableCfgHead(id, code);
			List<List<Object>> aaData = new ArrayList<List<Object>>();
			List<Object> aaList = null;
			for (TableCfgHeadDto head : headList ) {
				aaList = new ArrayList<Object>();
				aaList.add(head.getCfgId());
				aaList.add(Util.val(head.getCfgCode()));
				aaList.add(Util.val(head.getCfgName()));
				aaList.add(Util.val(head.getCfgTypeId()));
				aaList.add(Util.val(head.getCfgTypeName()));
				aaList.add(Util.val(head.getCfgFlag()));
				aaList.add(DateUtil.formatDateTime(head.getCfgCreateTime()));
				aaData.add(aaList);
			}
				map.put("aaData", aaData);
				map.put("result", 1);
				map.put("iTotalRecords", aaData.size());
				map.put("iTotalDisplayRecords", aaData.size());
			} catch (DataException e) {
				logger.error(e);
				map.put("result", -1);
				map.put("message", e.getMessage());
			}
			return map;
		}
	
	//表列信息
	@RequestMapping(value = "/tablecfglinelistjson.html")
	public @ResponseBody
	Map<String, Object> tableCfgLineListJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);

		int iDisplayStart = Util.parseNumVl(request.getParameter("iDisplayStart"), 0);
		int iDisplayLength = Util.parseNumVl(request.getParameter("iDisplayLength"), 10);
		int page = iDisplayStart / iDisplayLength;
		if (page <= 0)
			page = 0;
		try {
			int cfgId = Util.parseNumVl(request.getParameter("cfgId"), 100);
			String cfgCode = request.getParameter("cfgCode");
			List<TableCfgLineDto> listTableLine = tableService.listTableLine(cfgId, cfgCode);
			List<List<Object>> aaData = new ArrayList<List<Object>>();
			List<Object> aaList = null;
			for (TableCfgLineDto table : listTableLine) {
				aaList = new ArrayList<Object>();
				aaList.add(table.getLineId());
				aaList.add(Util.val(table.getLineCode()));
				aaList.add(Util.val(table.getLineTitle()));
				aaList.add(Util.val(table.getLineSeq()));
				aaList.add(Util.val(table.getLinePos()));
				aaList.add(Util.val(table.getLinePos2()));
				aaList.add(Util.val(table.getLineRefTypeId()));
				aaList.add(Util.val(table.getLineRefTypeName()));
				aaList.add(Util.val(table.getLineMultiRef()));
				aaList.add(Util.val(table.getLineInputVlType()));
				aaList.add(Util.val(table.getLineInputVlRepl()));
				aaList.add(table.getLineCode());
				aaList.add(table.getLineTitle());
				aaList.add(table.getLineSeq());
				aaList.add(table.getLinePos());
				aaList.add(table.getLinePos2());
				aaList.add(table.getLineRefTypeId());
				aaList.add(table.getLineRefTypeName());
				aaList.add(table.getLineMultiRef());
				aaList.add(table.getLineInputVlType());
				aaList.add(table.getLineInputVlRepl());
				aaList.add(table.getLineTitleWidth());
				aaList.add(table.getLineContentWidth());
				aaList.add(Util.val(table.getLineTypeVlId()));
				aaList.add(Util.val(table.getLineTypeVlName()));
				aaList.add(Util.val(table.getIsReq()));
				aaList.add(table.getLineFlag());
				aaData.add(aaList);
			}
			map.put("aaData", aaData);
			map.put("result", 1);
			map.put("iTotalRecords", aaData.size());
			map.put("iTotalDisplayRecords", aaData.size());
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

}
