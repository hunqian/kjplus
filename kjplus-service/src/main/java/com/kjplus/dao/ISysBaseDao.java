package com.kjplus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kjplus.model.*;
import com.kjplus.model.inner.SysRefValueInnerEbo;
import com.ybk.exception.DataException;

/**
 * 
 * @author yuhao t_sys_ref_value dao层映射器
 */
@Repository("baseDao")
public interface ISysBaseDao {

	/**
	 * 
	 * @param type
	 *            传入 SysRefTypeEbo 对象
	 * @throws DataException
	 */
	public void addRefType(SysRefTypeEbo type) throws DataException;

	/**
	 * 
	 * @param typeId
	 *            类型id
	 * @return 返回SysRefTypeEbo对象
	 * @throws DataException
	 */
	public SysRefTypeEbo getRefTypeById(@Param("typeId") int typeId) throws DataException;

	/**
	 * 
	 * @param code
	 *            编号
	 * @return 返回SysRefTypeEbo对象
	 * @throws DataException
	 */
	public SysRefTypeEbo getRefTypeByCode(@Param("refCode") String code) throws DataException;

	/**
	 * 通过valueId获取SysRefValueEbo对象
	 * 
	 * @param vlalueId
	 *            t_ref_value表id
	 * @return 返回SysRefValueEbo对象
	 * @throws DataException
	 */
	public SysRefValueEbo getRefVlById(@Param("valueId") int valueId) throws DataException;

	/**
	 * 通过code 查询对应refvlue
	 * 
	 * @param vlCode
	 *            ref_type_vlue的code
	 * @return 返回SysRefValueEbo对象
	 * @throws DataException
	 */
	public SysRefValueEbo getRefVlByCode(@Param("vlCode") String vlCode) throws DataException;

	/**
	 * 
	 */
	public List<SysRefValueEbo> getRefVlByRefId(@Param("refId") int refId,@Param("prefix") String prefix,@Param("flag") String flag) throws DataException;
	
	//列表refType 根据code和name模糊查询
 	public List<SysRefTypeEbo> listRefType(@Param("code") String code, @Param("name") String name,@Param("flag") String flag
			,@Param("page") int page,@Param("paging") int paging) throws DataException;

	//添加refVl
	public void addRefVl(SysRefValueEbo value) throws DataException;

	//列表refType总数    模糊查询后总数
	public int getTotalRefType(@Param("code") String code, @Param("name") String name,@Param("flag") String flag) throws DataException;

	//罗列参照值
	public List<SysRefValueInnerEbo> listRefVlInnerEbo(@Param("typeIds") String typeIds) throws DataException;
	
}
