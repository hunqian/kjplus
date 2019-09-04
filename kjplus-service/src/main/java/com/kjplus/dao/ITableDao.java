package com.kjplus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kjplus.model.TableCfgHeadEbo;
import com.kjplus.model.TableCfgLineEbo;
import com.kjplus.model.TableDataHeadEbo;
import com.kjplus.model.TableDataLineEbo;
import com.kjplus.model.inner.TableCfgInnerEbo;
import com.kjplus.model.inner.TableDataLineInnerEbo;
import com.ybk.exception.DataException;

/**
 * 
 * @author niuzhiwei
 *         <p>
 *         所有报表操作持久层
 */
@Repository("tableDao")
public interface ITableDao {

	/**
	 * 通过编号获取TableCfgHeadEbo对象
	 * 
	 * @param code
	 *            编号
	 * @return
	 * @throws DataException
	 */
	public TableCfgHeadEbo getCfgHeadById(@Param("cfgHeadId") int cfgHeadId, @Param("cfgHeadCode") String cfgHeadcode)
			throws DataException;

	/**
	 * 通过编号获取TableCfgLineEbo对象
	 * 
	 * @param code
	 *            编号
	 * @return
	 * @throws DataException
	 */
	public TableCfgLineEbo getCfgLineById(@Param("cfgLineId") int cfgLineId, @Param("cfgLineCode") String cfgLineCode,
			@Param("cfgId") int cfgId,@Param("lineSeq") int lineSeq, @Param("linePos") int linePos, @Param("linePos2") int linePos2) throws DataException;


	public List<TableCfgHeadEbo> listCfgHeadEbo(@Param("code") String code,@Param("name") String name ,@Param("typeId") int typeId) throws DataException;
	
	
	/**
	 * 通过cfgId 获取配置行列表
	 * 
	 * @param
	 * @return
	 * @throws DataException
	 */
	public List<TableCfgLineEbo> listCfgLine(@Param("cfgId") int cfgId,@Param("code") String code,@Param("title") String title) throws DataException;

	/**
	 * 通过cfgId 获取配置行列表
	 * 
	 * @param cfgId
	 * @return
	 * @throws DataException
	 */
	public List<TableCfgInnerEbo> listTableCfg(@Param("cfgId") int cfgId, @Param("cfgCode") String cfgCode,
			@Param("cfgLineCode") String cfgLineCode) throws DataException;

	/**
	 * 通过编号获取TableDataHeadEbo对象
	 * 
	 * @param code
	 *            编号
	 * @return
	 * @throws DataException
	 */
	public TableDataHeadEbo getDataHeadById(@Param("dataHeadId") int dataHeadId,
			@Param("dataHeadCode") String dataHeadCode) throws DataException;

	
	public List<TableDataHeadEbo> listDataHeadByMainId(@Param("mainId") int mainId,
			@Param("mainType") String mainType) throws DataException;
	
	/**
	 * 通过org和sheetcode编号获取TableDataHeadEbo对象
	 * 
	 * @param code
	 *            编号
	 * @return
	 * @throws DataException
	 */
	public TableDataHeadEbo getDataHeadByOrgSheetCode(@Param("orgId") int orgId, @Param("sheetCode") String sheetCode)
			throws DataException;

	/**
	 * 获取行数据里列表
	 * 
	 * @param id
	 *            id主键
	 * @param cfgLineId
	 *            通过配置行id查询
	 * @param chcekId
	 *            通过checkId对应的检查id查询
	 * @return
	 * @throws DataException
	 */
	public List<TableDataLineInnerEbo> listDataLine(@Param("id") int id, @Param("cfgLineId") int cfgLineId,
			@Param("checkId") int chcekId) throws DataException;

	/**
	 * <p>
	 * e 通过组织id 检查单号sheetCode查询
	 * <p>
	 * 这里返回list 考虑方法重用性可能查询这个组织的所有数据
	 * 
	 * @param orgId
	 *            组织id
	 * @param sheetCode
	 *            检查单号sheetCode
	 * @return
	 * @throws DataException
	 */
	// TODO sheetCode测试
	public List<TableDataHeadEbo> listDataHeadSheetCode(@Param("orgId") int orgId, @Param("sheetCode") String sheetCode)
			throws DataException;

	/**
	 * 添加表格配置头
	 * 
	 * @param cfgHead
	 *            传入TableCfgHeadEbo对象
	 * @throws DataException
	 */
	public void addCfgHead(TableCfgHeadEbo cfgHead) throws DataException;

	/**
	 * 添加表格配置行
	 * 
	 * @param cfgLing
	 *            传入TableCfgLineEbo对象
	 * @throws DataException
	 */
	public void addCfgLine(List<TableCfgLineEbo> cfgLines) throws DataException;

	/**
	 * 添加表格数据存储主表
	 * 
	 * @param dataHead
	 *            传入TableDataHeadEbo对象
	 * @throws DataException
	 */
	public void addDataHead(TableDataHeadEbo dataHead) throws DataException;

	/**
	 * <p>
	 * 对于允许多个选项的，此值从1开始增长;
	 * <p>
	 * 为了节约输入值保存的位置，现在是采用了40个字符，
	 * <p>
	 * 比如内容120字符，则需要存成3
	 * 
	 * @param dataLine
	 *            传入TableDataLineEbo对象
	 * @throws DataException
	 */
	public void addDataLine(List<TableDataLineEbo> dataLines) throws DataException;

	public void updateCfgHead(@Param("code") String code, @Param("name") String name,@Param("flag") String flag) throws DataException;
	
	public void updateCfgLine(@Param("code") String code, @Param("title") String title,@Param("flag") String flag,@Param("typeVlId") int typeVlId) throws DataException;
	
	public void delDataLine(@Param("checkId") int checkId) throws DataException;

	/**
	 * 根据tableTypeID获取详情页列表
	 * 
	 * @param tableTypeId
	 *            表格类型ID，对应参照
	 * @return
	 */
	public List<TableCfgHeadEbo> listCfgHeadByTypeId(@Param("tableTypeId") int tableTypeId, @Param("page") int page, @Param("paging") int paging) throws DataException;

	/**
	 * 获取随访详情页的总数
	 * 
	 * @param tableTypeId
	 */
	public int getTotalFollowupPage(@Param("tableTypeId") int tableTypeId);
	
	//修改表头数据  主要用于修改计算总分
	public void updateDataHead(@Param("id") int id, @Param("total") double total) throws DataException;
	
}
