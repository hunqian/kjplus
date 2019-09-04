package com.kjplus.service;

import java.util.List;

import com.kjplus.dto.TableCfgHeadDto;
import com.kjplus.dto.TableCfgLineDto;
import com.kjplus.dto.TableDataLineDto;
import com.kjplus.eto.TableCfgHeadEto;
import com.kjplus.eto.TableCfgLineEto;
import com.kjplus.eto.TableDataHeadEto;
import com.kjplus.eto.TableDataLineEto;
import com.kjplus.model.TableCfgHeadEbo;
import com.kjplus.model.TableCfgLineEbo;
import com.kjplus.model.TableDataHeadEbo;
import com.ybk.exception.DataException;

public interface ITableService {

	// 通过code获取表格配置头
	public TableCfgHeadEbo getCfgHeadByCode(String cfgHeadCode) throws DataException;

	// 通过code获取表格配置行
	public TableCfgLineEbo getCfgLineByCode(String cfgLineCode) throws DataException;

	// 根据tableTypeId获取表格配置头
	public List<TableCfgHeadDto> listCfgHeadByTypeId(int tableTypeId, int page, int paging) throws DataException;

	// 获取随访详情页得数量
	public int getTotalFollowupPage(int tableTypeId) throws DataException;

	// 通过code获取表格数据存储主表
	public TableDataHeadEbo getDataHeadByCode(String dataHeadCode) throws DataException;

	// 通过mainId和mainType获取表格数据存储主表
	public List<TableDataHeadEbo> listDataHeadByMainId(int mainId, String mainType) throws DataException;

	// 通过id获取表格配置头
	public TableCfgHeadEbo getCfgHeadById(int cfgHeadId) throws DataException;

	// 通过id获取表格配置行
	public TableCfgLineEbo getCfgLineById(int cfgLineId) throws DataException;

	// 通过id获取表格数据存储主表
	public TableDataHeadEbo getDataHeadById(int dataHeadId) throws DataException;

	// 通过组织 组织编号查询
	public List<TableDataHeadEbo> getDataHeadSheetCode(int orgId, String sheetCdoe) throws DataException;

	// 查询表(加入日志记录)列返回一个树状
	// fetchRef=true,是否返回参照
	public List<TableCfgLineDto> listTableLine(String cfgCode, boolean fetchRef) throws DataException;

	// 列表配置行
	public List<TableCfgLineDto> listTableLine(int cfgId, String cfgCode) throws DataException;

	// 列表配置行 cfgLineCode指定行编号进行模糊查询
	public List<TableCfgLineDto> listTableLine(int cfgId, String cfgCode, String cfgLineCode) throws DataException;

	// 列表配置行
	public List<TableCfgHeadDto> listTableCfgHead(int id, String code) throws DataException;

	/**
	 * 添加表配置
	 * 
	 * @param cfgHead
	 *            添加表格配置头
	 * 
	 * @param cfgLines
	 *            添加表格配置行
	 * @throws DataException
	 */
	public boolean addTableCfg(TableCfgHeadEto cfgHead, List<TableCfgLineEto> cfgLines) throws DataException;

	/**
	 * 添加表数据
	 * 
	 * @param dataHead
	 *            添加表格数据存储主表
	 * @param dataLines
	 *            <p>
	 *            对于允许多个选项的，此值从1开始增长;
	 *            <p>
	 *            为了节约输入值保存的位置，现在是采用了40个字符，
	 *            <p>
	 *            比如内容120字符，则需要存成3
	 * @throws DataException
	 */
	public boolean addTableData(TableDataHeadEto dataHead, List<TableDataLineEto> dataLines) throws DataException;

	// 修改
	public boolean modifyTableData(int mainId, String mainType, List<TableDataLineEto> dataLines) throws DataException;

	// 表标题列表
	public List<TableCfgHeadEbo> listCfgHeadEbo(String code, String name, int typeId) throws DataException;

	public void updateCfgHead(String code, String name, String flag) throws DataException;

	// 预先检查数据格式
	public boolean preCheckTableData(TableDataHeadEto dataHead, List<TableDataLineEto> dataLines) throws DataException;

	// 获得数据行
	public List<TableDataLineDto> listDataLine(int checkId) throws DataException;

}
