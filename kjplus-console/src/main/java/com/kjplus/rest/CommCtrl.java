package com.kjplus.rest;

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

import com.kjplus.dao.IInfoDao;
import com.kjplus.dao.IZanDao;
import com.kjplus.dto.SysRegionsDto;
import com.kjplus.service.IInfoService;
import com.kjplus.service.ISysRegionsService;
import com.kjplus.service.IZanService;
import com.mq.util.Util;
import com.ybk.exception.DataException;

//公共服务的操作，比如点赞，关注等
@Controller
public class CommCtrl {

	@Autowired
	private IZanDao zanDao;
	@Autowired
	private IInfoDao infoDao;
	@Autowired
	private IZanService zanService;
	@Autowired
	private IInfoService infoService;
	@Autowired
	private ISysRegionsService regionsService;
	
	private static Logger logger = Logger.getLogger(CommCtrl.class);

	//列表行政区域
	@RequestMapping(value = "/listregions.html")
	public @ResponseBody Map<String, Object> listRegionsJson(HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);
		try{
			
			int pid = Util.parseNumVl(request.getParameter("pid"),0);
			List<SysRegionsDto> regions = regionsService.listRegions(pid);
			map.put("data", regions);
			map.put("result", 1);
			map.put("message", "获取行者区域成功!");
		} catch (DataException e) {
			logger.error(e);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}
}
