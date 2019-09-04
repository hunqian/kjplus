package com.kjplus.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ybk.basic.util.Util;

import com.kjplus.dao.IServiceLogDao;
import com.kjplus.eto.ServiceLogEto;
import com.kjplus.model.*;
import com.kjplus.service.*;
import com.kjplus.util.DataValUtil;
import com.ybk.exception.DataException;

@Service("srvLogService")
public class ServiceLogService implements IServiceLogService {

	@Autowired
	private IServiceLogDao srvLogDao;
	@Autowired
	private IServiceEntryService srvEntryService;
	@Autowired
	private IAdminUserService adminUserService;
	@Autowired
	private IDocInfoService docInfoService;

	public ServiceLogEbo getServiceLogEboByIdOrCode(int id, String code) throws DataException {
		boolean isNull = id <= 0 && Util.isNull(code) ? true : false;
		if (isNull)
			return null;
		return srvLogDao.getServiceLogEboByIdOrCode(id, code);
	}

	public ServiceLogEbo addServiceLog(ServiceLogEto serviceLogEto) throws DataException {
		// 空值验证
		DataValUtil.dataValidation(serviceLogEto.getClass(), serviceLogEto);
		ServiceEntryEbo srvEntry = srvEntryService.getSrvEntryByIdOrCode(serviceLogEto.getSrvId(), null);
		if (srvEntry == null)
			throw new DataException("该服务不存在，id=" + serviceLogEto.getSrvId());
		AdminUserEbo admin = adminUserService.getUserByUid(serviceLogEto.getProviderId());
		if (admin == null)
			throw new DataException("该提供服务用户不存在，id=" + serviceLogEto.getProviderId());
		DocumentInfoEbo doc = docInfoService.getDocinfoByIdOrCode(serviceLogEto.getPrsnId(), null);
		if (doc == null)
			throw new DataException("该接受服务用户档案不存在，prsnID=" + serviceLogEto.getPrsnId());
		String code = Util.genDigiCodeStr(ServiceLogEto.CODE_LEN);
		ServiceLogEbo srv = getServiceLogEboByIdOrCode(0, code);
		while (srv != null) {
			code = Util.genDigiCodeStr(ServiceLogEto.CODE_LEN);
			srv = getServiceLogEboByIdOrCode(0, code);
		}
		ServiceLogEbo srvEbo = new ServiceLogEbo();
		BeanUtils.copyProperties(serviceLogEto, srvEbo);
		srvEbo.setCode(code);
		srvLogDao.addServiceLog(srvEbo);
		return srv;
	}

	public List<ServiceLogEbo> listServiceLogEbo(int srvId, int providerId, int prsnId, String flag, int startTime,
			int endTime, int page, int paging) throws DataException {
		List<ServiceLogEbo> listLog = new ArrayList<ServiceLogEbo>();
		if (srvId > 0) {
			ServiceEntryEbo srvEntry = srvEntryService.getSrvEntryByIdOrCode(srvId, null);
			if (srvEntry == null)
				return listLog;
		}
		if (endTime < startTime)
			return listLog;
		if (paging <= 0)
			return listLog;
		listLog = srvLogDao.listServiceLogEbo(srvId, providerId, prsnId, flag, startTime, endTime, page, paging);
		return listLog;
	}

	public int getTotalServiceLog(int srvId, int providerId, int prsnId, String flag, int startTime, int endTime)
			throws DataException {
		int total = 0;
		if (srvId > 0) {
			ServiceEntryEbo srvEntry = srvEntryService.getSrvEntryByIdOrCode(srvId, null);
			if (srvEntry == null)
				return total;
		}
		if (endTime < startTime)
			return total;
		total = srvLogDao.getTotalServiceLog(srvId, providerId, prsnId, flag, startTime, endTime);
		return total;
	}

	public ServiceLogEbo updateServiceLog(int id, String code, String flag) throws DataException {
		boolean isNull = id <= 0 && Util.isNull(code) ? true : false;
		if (isNull)
			throw new DataException("请选择修改服务日志ID或CODE!");
		ServiceLogEbo srv = getServiceLogEboByIdOrCode(id, code);
		if(srv ==null)
			throw new DataException("该服务日志不存在！");
		srvLogDao.updateServiceLog(id, code, flag);
		return getServiceLogEboByIdOrCode(id, code);
	}

}
