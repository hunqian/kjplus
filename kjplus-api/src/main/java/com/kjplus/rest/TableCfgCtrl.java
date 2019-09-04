package com.kjplus.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
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

import com.kjplus.constant.ShareConstant;
import com.kjplus.dao.ITableDao;
import com.kjplus.dto.TableCfgLineDto;
import com.kjplus.model.SysRefValueEbo;
import com.kjplus.model.TableCfgHeadEbo;
import com.kjplus.service.IAdminUserService;
import com.kjplus.service.IDocInfoService;
import com.kjplus.service.IOrgService;
import com.kjplus.service.ISysBaseService;
import com.kjplus.service.ITableService;
import com.mq.util.DateUtil;
import com.ybk.exception.DataException;

@Api(tags = "表格", description = "关于表格的接口")
@Controller
public class TableCfgCtrl {

	private static Logger logger = Logger.getLogger(DocInfoCtrl.class);

	@Autowired
	private IDocInfoService docInfoService;
	@Autowired
	private IOrgService orgService;
	@Autowired
	private ISysBaseService baseService;
	@Autowired
	private IAdminUserService adminUserService;
	@Autowired
	private ITableService tableService;
	@Autowired
	private ITableDao tableDao;

	// 根据表格类型获取相应表格表头信息
	@ApiOperation(value = "根据表格类型获取相应表格表头信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "typeCode", value = "类型Code", required = true, dataType = "String", paramType = "body") })
	@RequestMapping(value = "/listtableheadcfg.html", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> listTableHeadCfg(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", ShareConstant.CONNECT_STATUS);

		try {
			String typeCode = request.getParameter("typeCode");
			// typecode不为空 且该类型不存在时
			if (Util.isNotNull(typeCode)) {
				SysRefValueEbo base = baseService.getRefVlByCode(typeCode);
				if (base == null) {
					map.put("result", ShareConstant.RES_ERROR);
					map.put("message", "该类型表格信息不存在！");
					return map;
				}
			}
			// typecode为空 或是该类型存在时
			SysRefValueEbo base = baseService.getRefVlByCode(typeCode);
			// 默认获取全部表格表头配置
			int tableTypeId = 0;
			String tableTypeName = "";
			if (base != null) {
				tableTypeId = base.getId();
				tableTypeName = base.getName();
			}

			List<TableCfgHeadEbo> listCfgHead = tableService.listCfgHeadEbo(null, null, tableTypeId);
			List<Map<String, Object>> cfgHeadData = new ArrayList<Map<String, Object>>();
			for (TableCfgHeadEbo th : listCfgHead) {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("id", th.getId());
				m.put("code", th.getCode());
				m.put("name", th.getName());
				m.put("updateTime", DateUtil.getTimeInSec(th.getCreateTime()));
				cfgHeadData.add(m);
			}
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("list", cfgHeadData);
			map.put("data", data);
			map.put("result", ShareConstant.RES_OK);
			if (Util.isNull(tableTypeName))
				map.put("message", "全部表格表头配置获取成功！");
			else
				map.put("message", tableTypeName + "类型，获取成功");
		} catch (DataException e) {
			logger.error(e);
			map.put("result", ShareConstant.RES_ERROR);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 根据表格表头code获取表列配置信息
	@ApiOperation(value = "根据表格表头code获取表列配置信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "code", value = "code", required = true, dataType = "String", paramType = "body") })
	@RequestMapping(value = "/tablecfgref.html", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> tableCfgRefJson(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", ShareConstant.CONNECT_STATUS);

		try {
			String code = request.getParameter("code");
			if (Util.isNull(code)) {
				map.put("result", ShareConstant.RES_ERROR);
				map.put("message", "请输入表格编号！");
				return map;
			}

			TableCfgHeadEbo head = tableService.getCfgHeadByCode(code);
			// 获得配置
			List<TableCfgLineDto> cfgs = tableService.listTableLine(code, true);
			Hashtable<String, TableCfgLineDto> cfgHash = new Hashtable<String, TableCfgLineDto>();
			for (TableCfgLineDto c : cfgs) {
				cfgHash.put(c.getLineCode(), c);
			}
			map.put("data", cfgHash);
			map.put("result", ShareConstant.RES_OK);
			map.put("message", head.getName() + "表列信息和参照获取成功！");
		} catch (DataException e) {
			logger.error(e);
			map.put("result", ShareConstant.RES_ERROR);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 根据表格表头code获取该表格类型对应的表数据
	@ApiOperation(value = "根据表格表头code获取该表格类型对应的表数据")
	@ApiImplicitParams({ @ApiImplicitParam(name = "code", value = "code", required = true, dataType = "String", paramType = "body") })
	@RequestMapping(value = "/tabledata.html", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> tableData(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", ShareConstant.CONNECT_STATUS);

		try {
			String code = request.getParameter("code");
			if (Util.isNull(code)) {
				map.put("result", ShareConstant.RES_ERROR);
				map.put("message", "请输入表格编号！");
				return map;
			}

			TableCfgHeadEbo head = tableService.getCfgHeadByCode(code);
			// 获得配置
			List<TableCfgLineDto> cfgs = tableService.listTableLine(code, true);
			Hashtable<String, TableCfgLineDto> cfgHash = new Hashtable<String, TableCfgLineDto>();
			for (TableCfgLineDto c : cfgs) {
				cfgHash.put(c.getLineCode(), c);
			}
			map.put("data", cfgHash);
			map.put("result", ShareConstant.RES_OK);
			map.put("message", head.getName() + "表列信息和参照获取成功！");
		} catch (DataException e) {
			logger.error(e);
			map.put("result", ShareConstant.RES_ERROR);
			map.put("message", e.getMessage());
		}
		return map;
	}

}
