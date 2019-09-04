package com.kjplus.rest;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ybk.basic.util.Util;

import com.kjplus.dto.FuncDto;
import com.kjplus.service.ISysFuncService;
import com.ybk.exception.DataException;

@Controller
public class SysFuncCtrl {

	@Autowired
	private ISysFuncService funcService;

	private static Logger logger = Logger.getLogger(SysFuncCtrl.class);

	@RequestMapping(value = "/listfunc.html")
	public @ResponseBody
	Map<String, Object> hellword(HttpServletRequest req, HttpServletResponse res) {
		Map<String, Object> map = new HashMap<String, Object>();
		String type = req.getParameter("type");
		Integer pid = Util.parseNumVl(req.getParameter("pid"), 0);
		List<FuncDto> fucs = null;
		try {
			fucs = funcService.listFunc(pid, type);
		} catch (DataException e) {
			logger.info(e.getMessage());
		}
		map.put("data", fucs);
		return map;
	}
	
}
