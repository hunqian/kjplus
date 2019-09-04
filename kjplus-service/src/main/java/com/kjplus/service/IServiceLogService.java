package com.kjplus.service;

import java.util.List;

import com.kjplus.eto.ServiceLogEto;
import com.kjplus.model.*;
import com.ybk.exception.DataException;

public interface IServiceLogService {

	/**
	 * 
	 * @param id
	 *            <p>
	 *            t_server_log表id
	 * @param code
	 *            <p>
	 *            t_server_log表编号
	 * @return <p>
	 *         返回ServerlogEbo对象
	 * @throws DataException
	 */
	public ServiceLogEbo getServiceLogEboByIdOrCode(int id, String code) throws DataException;

	/**
	 * 添加服务日志
	 * 
	 * @param
	 * 
	 * @return ServiceLogEbo
	 * @throws	DataException
	 */
	public ServiceLogEbo addServiceLog(ServiceLogEto serviceLogEto) throws DataException;

	/**
	 * 列表日志
	 * 
	 * @param srvId
	 *            具体服务的日志记录
	 * @param startTime
	 *            endTIme 时间端刷选日志
	 * 
	 * @return
	 * @throws DataException
	 */
	public List<ServiceLogEbo> listServiceLogEbo(int srvId, int providerId, int prsnId, String flag, int startTime,
			int endTime, int page, int paging) throws DataException;

	/**
	 * 日志总数
	 * 
	 * @param srvId
	 *            具体服务的日志记录
	 * @param startTime
	 *            endTIme 时间端刷选日志
	 * @return int
	 * @throws DataException
	 */
	public int getTotalServiceLog(int srvId, int providerId, int prsnId, String flag, int startTime, int endTime)
			throws DataException;

	/**
	 * 添加服务日志
	 * 
	 * @param
	 * 
	 * @return
	 * @throws
	 */
	public ServiceLogEbo updateServiceLog(int id,  String code,String flag)
			throws DataException;

}
