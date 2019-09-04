package com.kjplus.dao;

import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kjplus.model.*;
import com.kjplus.model.inner.ServiceSumInnerEbo;
import com.ybk.exception.DataException;

@Repository("srvSumDao")
public interface IServiceSumDao {

	/**
	 * 添加积分汇总表
	 * 
	 * @param srvSum
	 *            传入ServiceSumEbo对象
	 * 
	 * @throws DataException
	 */
	public void addSrvSum(ServiceSumEbo srvSum) throws DataException;

	/**
	 * 添加积分记录表
	 * 
	 * @param srvList
	 *            传入ServiceListEbo对象
	 * @throws DataException
	 */
	public void addSrvList(ServiceListEbo srvList) throws DataException;

	/**
	 * 
	 * @param id
	 *            传入t_service_sum表id
	 * @return 返回ServiceSumEbo对象
	 * @throws DataException
	 */
	public ServiceSumEbo getSrvSumById(@Param("id") int id) throws DataException;

	/**
	 * 
	 * @param bodyId
	 *            传入t_service_sum表body_id
	 * @param bodyType
	 *            传入t_service_sum表body_Type
	 * @return 返回ServiceSumEbo对象
	 * @throws DataException
	 */
	public ServiceSumEbo getSrvSumByBody(@Param("bodyId") int bodyId, @Param("bodyType") String bodyType)
			throws DataException;

	/**
	 * 列表积分汇总
	 * 
	 * @param orgId
	 *            传入t_service_sum表org_id
	 * @param bodyType
	 *            传入t_service_sum表body_Type
	 * @return 返回ServiceSumEbo对象
	 * @throws DataException
	 */
	public List<ServiceSumInnerEbo> listSrvSumInner(@Param("orgId") int orgId, @Param("bodyType") String bodyType,
			@Param("page") int page, @Param("paging") int paging) throws DataException;

	/**
	 * 
	 * @param id
	 *            传入t_service_list表id
	 * @return 返回ServiceListEbo对象
	 * @throws DataException
	 */
	public ServiceListEbo getSrvListById(@Param("id") int id) throws DataException;

	/**
	 * 列表积分记录
	 * 
	 * @param startTime和endTime
	 *            某个时间段内的记录
	 * @return 返回ServiceListEbo对象
	 * @throws DataException
	 */
	public List<ServiceListEbo> listSrvList(@Param("sumId") int sumId, @Param("mainId") int mainId,
			@Param("mainType") String mainType, @Param("startTime") int startTime, @Param("endTime") int endTime,
			@Param("status") String status, @Param("page") int page, @Param("paging") int paging) throws DataException;

	/**
	 * 修改积分记录总表
	 * 
	 * @param id
	 *            t_service_sum表id
	 * @param totalEarnPoint
	 *            总赚积分
	 * @param totalConsumePoint
	 *            总消费积分
	 * @param totalPoint
	 *            当前积分
	 * @throws DataException
	 */
	public void updateServiceSumPoint(@Param("id") int id, @Param("totalEarnPoint") Double totalEarnPoint,
			@Param("totalConsumePoint") Double totalConsumePoint, @Param("totalPoint") Double totalPoint)
			throws DataException;

}
