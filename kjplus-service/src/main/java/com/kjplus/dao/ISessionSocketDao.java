package com.kjplus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kjplus.model.SessionSocketEbo;
import com.ybk.exception.DataException;

@Repository("sessionSocketDao")
public interface ISessionSocketDao {

	/**
	 * 添加session的对照
	 * 
	 * @param sessSocket
	 * @throws DataException
	 */
	public void addSessionSocket(SessionSocketEbo sessSocket) throws DataException;

	/**
	 * 通过 sessionCode 获取用户信息
	 * 
	 * @param sessionCode
	 *            webSocket的session key键存储在数据库中
	 * @param status
	 *            状态
	 * @return
	 * @throws DataException
	 */
	public SessionSocketEbo getSessionByCode(@Param("sessionCode") String sessionCode, @Param("status") String status) throws DataException;

	/**
	 * 通过 uid 获取用户信息
	 * 
	 * @param uid
	 *            用户id orgId 用户组织
	 * @param status
	 *            状态
	 * @return
	 * @throws DataException
	 */
	public SessionSocketEbo getSessionByUid(@Param("uid") Integer uid, @Param("status") String status) throws DataException;

	/**
	 * 通过 sessionCode修改当前用户websocket的session状态
	 * 
	 * @param sessionCode
	 *            webSocket的session key键存储在数据库中
	 * @param status
	 *            状态
	 * @throws DataException
	 */
	public void updateSessionStatus(@Param("sessionCode") String sessionCode, @Param("status") String status) throws DataException;

	/**
	 * 获取当前用户的 sesisonCode列表倒序
	 * 
	 * @param sessionCode
	 * @param status
	 * @param uid
	 * @return
	 * @throws DataException
	 */
	public List<SessionSocketEbo> listSessionByCode(@Param("sessionCode") String sessionCode, @Param("status") String status, @Param("uid") int uid)
			throws DataException;

}
