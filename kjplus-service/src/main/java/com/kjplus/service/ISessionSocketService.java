package com.kjplus.service;

import java.util.List;

import com.kjplus.eto.SessionSocketEto;
import com.kjplus.model.SessionSocketEbo;
import com.ybk.exception.DataException;

public interface ISessionSocketService {

	// 添加session的对照
	public SessionSocketEbo addSessionSocket(SessionSocketEto sessSocket) throws DataException;

	// 通过 sessionCode 获取用户信息
	public SessionSocketEbo getSessionByCode(String sessionCode, String status) throws DataException;

	// 通过 uid 获取用户信息
	public SessionSocketEbo getSessionByUid(Integer uid, String status) throws DataException;

	// 通过 sessionCode修改当前用户websocket的session状态
	public void updateSessionStatus(String sessionCode, String status) throws DataException;

	// 获取当前用户的 sesisonCode列表倒序
	public List<SessionSocketEbo> listSessionByCod(String sessionCode, String status, int uid) throws DataException;

}
