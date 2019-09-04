package com.kjplus.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ybk.basic.util.Util;

import com.kjplus.dao.IUserLogDao;
import com.kjplus.eto.UserLogEto;
import com.kjplus.model.UserLogEbo;
import com.kjplus.service.IUserLogService;
import com.mq.util.DateUtil;
import com.ybk.exception.DataException;

@Service("userLogService")
public class UserLogService implements IUserLogService {

	private static Logger logger = Logger.getLogger(UserLogService.class);

	@Autowired
	private IUserLogDao userLogDao;

	public void addUserLog(UserLogEto userLogEto) throws DataException {
		
		if (userLogEto.getUid() <= 0)
			throw new DataException("更新日志失败，uid不能为空");
		
		if (Util.isNull(userLogEto.getUserType()))
			throw new DataException("更新日志失败，userType不能为空");
		
		if (Util.isNull(userLogEto.getOpType()))
			throw new DataException("更新日志失败，opType不能为空");
		try {
			UserLogEbo userLogEbo = new UserLogEbo();
			BeanUtils.copyProperties(userLogEto, userLogEbo);
			userLogEbo.setopTime(DateUtil.getCurTimeInSec());
			userLogDao.addUserLog(userLogEbo);
		} catch (Exception e) {
			logger.error(e);
		}
	};

}
