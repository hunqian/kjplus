package com.kjplus.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ybk.basic.util.Util;

import com.alibaba.fastjson.JSON;
import com.kjplus.constant.ShareConstant;
import com.kjplus.dto.SysRefVlDto;
import com.kjplus.dto.TableCfgLineDto;
import com.kjplus.service.ITableService;
import com.ybk.exception.DataException;

@Api(tags = "测试数据", description = "关于测试数据的接口")
@Controller
public class TestAction {

	private static Logger logger = Logger.getLogger(TestAction.class);
	@Autowired
	private ITableService tableService;

	// 测试数据
	@RequestMapping(value = "/testdata.html",method = RequestMethod.GET)
	@ApiOperation(value = "文件上传")
	@ApiImplicitParams({ @ApiImplicitParam(name = "lineCode", value = "配置行编码", required = true, dataType = "String", paramType = "body"),
			@ApiImplicitParam(name = "cfgCode", value = "配置编码", required = true, dataType = "String", paramType = "body") })
	public @ResponseBody
	Map<String, Object> test(HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", ShareConstant.CONNECT_STATUS);
		String cfgLineCode = req.getParameter("lineCode");
		String cfgCode = req.getParameter("cfgCode");
		if (Util.isNull(cfgLineCode))
			cfgLineCode = "";
		if (Util.isNull(cfgCode))
			cfgCode = "";

		try {
			List<TableCfgLineDto> cfgs = tableService.listTableLine(0, cfgCode, cfgLineCode);
			Map<String, Object> dataMap = new HashMap<String, Object>();
			for (TableCfgLineDto cfg : cfgs) {
				if (Util.isNull(cfg.getLineInputVlRepl()))
					cfg.setLineInputVlRepl("");
				if (Util.isNull(cfg.getLineInputVlType()))
					cfg.setLineInputVlType("");
				if (Util.isNull(cfg.getLineFlag()))
					cfg.setLineFlag("");
				tranversCfg(cfg, dataMap);
			}
			map.put("tbdata", dataMap);
			Map<String, Object> tbrefs = new HashMap<String, Object>();
			Iterator<String> keys = dataMap.keySet().iterator();
			String k = null;
			while (keys.hasNext()) {
				k = keys.next();
				if (!(dataMap.get(k) instanceof TableCfgLineDto))
					continue;
				TableCfgLineDto cl = (TableCfgLineDto) dataMap.get(k);
				Map<String, Object> ref = new HashMap<String, Object>();
				ref.put("title", cl.getLineTitle());
				ref.put("pos", cl.getLinePos());
				ref.put("ismulti", cl.getLineMultiRef());
				List<Map<String, Object>> rvms = new ArrayList<Map<String, Object>>();
				for (SysRefVlDto rv : cl.getRefVls()) {
					Map<String, Object> rvm = new HashMap<String, Object>();
					rvm.put("rvid", rv.getRefVlId());
					rvm.put("rvname", rv.getRefVlName());
					rvms.add(rvm);
				}
				ref.put("select", rvms);
				tbrefs.put(cl.getLineCode(), ref);
			}
			map.put("tbref", JSON.toJSONString(tbrefs));
			map.put("result", ShareConstant.RES_OK);
			map.put("message", "获得表格配置成功!");
		} catch (DataException e) {
			logger.error(e);
			map.put("result", ShareConstant.RES_ERROR);
			map.put("message", e.getMessage());
		}
		return map;
	}

	private void tranversCfg(TableCfgLineDto cfg, Map<String, Object> dataMap) {
		if (cfg == null)
			return;
		dataMap.put(cfg.getLineCode(), cfg);
		for (TableCfgLineDto cfg2 : cfg.getSubs()) {
			tranversCfg(cfg2, dataMap);
		}
	}
}
