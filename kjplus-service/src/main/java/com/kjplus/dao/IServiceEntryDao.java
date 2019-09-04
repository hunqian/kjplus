package com.kjplus.dao;

import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kjplus.model.*;
import com.kjplus.model.inner.ServiceEntryInnerEbo;
import com.ybk.exception.DataException;

@Repository("srvEntryDao")
public interface IServiceEntryDao {

	// 服务入口相关操作
	/**
	 * 添加t_service_entry表记录
	 * 
	 * @param srvEntry
	 *            传入ServiceEntryEbo对象
	 * @throws DataException
	 */
	public void addSrvEntry(ServiceEntryEbo srvEntry) throws DataException;

	/**
	 * 
	 * @param id
	 *            t_service_entry的id主键
	 * @param srvCode
	 *            t_service_entry的编号
	 * @return 返回ServiceConfigEbo对象
	 * @throws DataException
	 */
	public ServiceEntryEbo getSrvEntryByIdOrCode(@Param("id") int id, @Param("srvCode") String srvCode)
			throws DataException;

	/**
	 * 获取总服务主入口 按先父后子排序
	 * 
	 * @param orgId
	 *            组织ID
	 * @param flag
	 *            状态
	 * @return
	 * @throws DataException
	 */
	public List<ServiceEntryInnerEbo> listServiceEntry(@Param("orgId") int orgId, @Param("typeIds") String typeIds,
			@Param("type") String type, @Param("opType") String opType, @Param("name") String name,
			@Param("flag") String flag, @Param("page") int page, @Param("paging") int paging) throws DataException;

	/**
	 * 获取服务数据总数
	 * 
	 * @param serviceEntryEbo
	 * @throws DataException
	 */
	public int getTotalEntry(@Param("orgId") int orgId, @Param("typeIds") String typeIds, @Param("type") String type,
			@Param("opType") String opType, @Param("name") String name, @Param("flag") String flag)
			throws DataException;

	/**
	 * 修改服务主入口
	 * 
	 * @param serviceEntryEbo
	 * @throws DataException
	 */
	public void updateSrvEntry(ServiceEntryEbo serviceEntryEbo) throws DataException;

	// 服务配置相关操作

	/**
	 * 添加t_service_confog表记录
	 * 
	 * @param srvConfig
	 *            传入ServiceConfigEbo对象
	 * @throws DataException
	 */
	public void addSrvConfig(ServiceConfigEbo srvConfig) throws DataException;

	/**
	 * 
	 * @param id
	 *            传入t_service_config表id
	 * @return 返回 ServiceConfigEbo对象
	 * @throws DataException
	 */
	public ServiceConfigEbo getServiceConfigById(@Param("id") int id) throws DataException;

	/**
	 * 获取配置的列表
	 * 
	 * @param id
	 *            传入t_service_config表id
	 * @return 返回 ServiceConfigEbo对象
	 * @throws DataException
	 */
	public List<ServiceConfigEbo> listSrvConfigEbo(@Param("srvId") int srvId, @Param("orgId") int orgId)
			throws DataException;

	// 更新
	public void updateSrvConfig(@Param("id") int id, @Param("acceptorPoint") double acceptorPoint,
			@Param("providerPoint") double providerPoint) throws DataException;

}
