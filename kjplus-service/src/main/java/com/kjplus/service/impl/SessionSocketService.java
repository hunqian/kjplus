package com.kjplus.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ybk.basic.util.Util;

import com.kjplus.constant.Constant;
import com.kjplus.dao.ISessionSocketDao;
import com.kjplus.eto.SessionSocketEto;
import com.kjplus.model.OrgEbo;
import com.kjplus.model.SessionSocketEbo;
import com.kjplus.model.UserEbo;
import com.kjplus.service.IOrgService;
import com.kjplus.service.ISessionSocketService;
import com.kjplus.service.IUserService;
import com.kjplus.util.DataValUtil;
import com.ybk.exception.DataException;

@Service("sessSocketService")
public class SessionSocketService implements ISessionSocketService {

	private Logger logger = Logger.getLogger(SessionSocketService.class);
	@Autowired
	private IUserService userService;
	@Autowired
	private IOrgService orgService;
	@Autowired
	private ISessionSocketDao sessionSocketDao;

	public SessionSocketEbo addSessionSocket(SessionSocketEto sessSocket) throws DataException {
		DataValUtil.dataValidation(sessSocket.getClass(), sessSocket);
		UserEbo user = userService.getUserById(sessSocket.getUid());
		if (user == null)
			throw new DataException("系统无此用户");
		int orgId = Util.parseNumVl(String.valueOf(sessSocket.getOrgId()), 0);
		OrgEbo org = null;
		if (orgId > 0) {
			org = orgService.getOrgById(sessSocket.getOrgId());
			if (org == null)
				throw new DataException("系统无此组织");
		}
		// 查询当前用户是否存在有效session 存在 直接返回
		List<SessionSocketEbo> listSessions = listSessionByCod(null, Constant.FLAG_YES, sessSocket.getUid());
		if (listSessions.size() > 0)
			return listSessions.get(0);

		int seq = 0;
		List<SessionSocketEbo> listSessions1 = listSessionByCod(null, null, sessSocket.getUid());
		if (listSessions1.size() > 0)
			seq = listSessions1.get(0).getSeq() + 1;

		SessionSocketEbo session = null;
		try {
			session = new SessionSocketEbo();
			if (org != null)
				session.setOrgId(org.getId());
			session.setUid(user.getUid());
			session.setSessionCode(sessSocket.getSessionCode());
			session.setStatus(sessSocket.getStatus());
			if (seq > 0)
				session.setSeq(seq);
			else
				session.setSeq(Constant.SESSION_SOCKET_SEQ);
			sessionSocketDao.addSessionSocket(session);
		} catch (Exception e) {
			logger.error(e.getMessage());

		}
		return session;
	}

	public SessionSocketEbo getSessionByCode(String sessionCode, String status) throws DataException {
		boolean isNull = Util.isNull(sessionCode) || Util.isNull(status) ? true : false;
		if (isNull)
			return null;
		if (!Constant.FLAG_YES.equals(status))
			return null;
		SessionSocketEbo ss = sessionSocketDao.getSessionByCode(sessionCode, status);
		return ss;
	}

	public SessionSocketEbo getSessionByUid(Integer uid, String status) throws DataException {
		if (uid <= 0)
			return null;
		if (!Constant.FLAG_YES.equals(status))
			return null;
		return sessionSocketDao.getSessionByUid(uid, status);
	}

	public void updateSessionStatus(String sessionCode, String status) throws DataException {
		boolean isNull = Util.isNull(sessionCode) || Util.isNull(status) ? true : false;
		if (isNull)
			return;
		// 判断 当前sessionCode 是否存在
		SessionSocketEbo session = getSessionByCode(sessionCode, Constant.FLAG_YES);
		if (session == null)
			return;
		sessionSocketDao.updateSessionStatus(sessionCode, status);
	}

	public List<SessionSocketEbo> listSessionByCod(String sessionCode, String status, int uid) throws DataException {
		if (uid <= 0)
			throw new DataException("请指定uid");
		return sessionSocketDao.listSessionByCode(sessionCode, status, uid);

	}

}
