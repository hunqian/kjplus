package com.kjplus.service;

import java.util.List;

import com.kjplus.dto.ServiceSumDto;
import com.kjplus.eto.ServiceListEto;
import com.kjplus.eto.ServiceSumEto;
import com.kjplus.model.*;
import com.ybk.exception.DataException;

public interface IServiceSumService {

	/**
	 * 添加积分汇总表
	 * 
	 * @param srvSum
	 *            传入ServiceSumEbo对象
	 * 
	 * @throws DataException
	 */
	public ServiceSumEbo addSrvSum(ServiceSumEto srvSum) throws DataException;

	/**
	 * 添加积分记录表
	 * 
	 * @param srvList
	 *            传入ServiceListEbo对象
	 * @throws DataException
	 */
	public ServiceListEbo addSrvList(ServiceListEto srvList) throws DataException;

	/**
	 * 
	 * @param id
	 *            传入t_service_sum表id
	 * @return 返回ServiceSumEbo对象
	 * @throws DataException
	 */
	public ServiceSumEbo getSrvSumById(int id) throws DataException;

	/**
	 * 
	 * @param bodyId
	 *            传入t_service_sum表body_id
	 * @param bodyType
	 *            传入t_service_sum表body_Type
	 * @return 返回ServiceSumEbo对象
	 * @throws DataException
	 */
	public ServiceSumEbo getSrvSumByBody(int bodyId, String bodyType) throws DataException;

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
	public List<ServiceSumDto> listSrvSumDto(int orgId, String bodyType, int page, int paging) throws DataException;

	/**
	 * 
	 * @param id
	 *            传入t_service_list表id
	 * @return 返回ServiceListEbo对象
	 * @throws DataException
	 */
	public ServiceListEbo getSrvListById(int id) throws DataException;

	/**
	 * 列表积分记录
	 * 
	 * @param startTime和endTime
	 *            某个时间段内的记录
	 * @return 返回ServiceListEbo对象
	 * @throws DataException
	 */
	public List<ServiceListEbo> listSrvList(int sumId, int mainId, String mainType, int startTime, int endTime,
			String status, int page, int paging) throws DataException;

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
	public void updateServiceSumPoint(int id, Double totalEarnPoint, Double totalConsumePoint, Double totalPoint)
			throws DataException;

	/**
	 * 修改积分记录
	 * 
	 * @param id
	 *            t_service_sum表id
	 * @param point
	 *            积分
	 * @param status
	 *            积分要进行的操作 I增加/D减少
	 * @throws DataException
	 */
	public void updateServiceSumPoint(int id, Double point, String status) throws DataException;
	
}
