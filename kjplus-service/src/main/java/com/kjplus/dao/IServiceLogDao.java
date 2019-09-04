package com.kjplus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kjplus.model.*;
import com.ybk.exception.DataException;

@Repository("srvLogDao")
public interface IServiceLogDao {

	// 通过id或code查询服务日志
	public ServiceLogEbo getServiceLogEboByIdOrCode(@Param("id") int id, @Param("code") String code)
			throws DataException;

	// 添加服务日志
	public void addServiceLog(ServiceLogEbo serviceLogEbo) throws DataException;

	// 列表日志
	public List<ServiceLogEbo> listServiceLogEbo(@Param("srvId") int srvId, @Param("providerId") int providerId,
			@Param("prsnId") int prsnId, @Param("flag") String flag, @Param("startTime") int startTime,
			@Param("endTime") int endTime, @Param("page") int page, @Param("paging") int paging) throws DataException;

	// 日志总数
	public int getTotalServiceLog(@Param("srvId") int srvId, @Param("providerId") int providerId,
			@Param("prsnId") int prsnId, @Param("flag") String flag, @Param("startTime") int startTime,
			@Param("endTime") int endTime) throws DataException;

	// 添加服务日志
	public void updateServiceLog(@Param("id") int id, @Param("code") String code, @Param("flag") String flag)
			throws DataException;

}
