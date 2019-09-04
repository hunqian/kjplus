package com.kjplus.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.ybk.basic.util.Util;

import com.kjplus.dao.IServerRepoDao;
import com.kjplus.eto.ServerRepoEto;
import com.kjplus.model.*;
import com.kjplus.service.*;
import com.kjplus.util.DataValUtil;
import com.ybk.exception.DataException;

@Service("serverRepoService")
public class ServerRepoService implements IServerRepoService {

	@Autowired
	private IServerRepoDao srvRepoDao;

	public ServerRepoEbo getSrvRepoByIdOrCode(int id, String code) throws DataException {
		boolean isNull = id <= 0 && Util.isNull(code) ? true : false;
		if (isNull)
			return null;
		return srvRepoDao.getSrvRepoByIdOrCode(id, code);
	}

	//加入事务
	@Transactional(value = "txManager", rollbackFor = { RuntimeException.class, Exception.class, DataException.class }, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, readOnly = true, timeout = 3)
	public ServerRepoEbo addSrvRepoEbo(ServerRepoEto srvRepoEto) throws DataException {
		DataValUtil.dataValidation(srvRepoEto.getClass(), srvRepoEto);
		ServerRepoEbo srvRepoEbo = new ServerRepoEbo();
		BeanUtils.copyProperties(srvRepoEto, srvRepoEbo);
		String code = "";
		code = Util.genDigiCodeStr(ServerRepoEto.CODE_LEN);
		ServerRepoEbo srvRepo = getSrvRepoByIdOrCode(0, code);
		while (srvRepo != null) {
			code = Util.genDigiCodeStr(ServerRepoEto.CODE_LEN);
			srvRepo = getSrvRepoByIdOrCode(0, code);
		}
		srvRepoEbo.setCode(code);
		srvRepoDao.addSrvRepoEbo(srvRepoEbo);
		return srvRepoEbo;
	}

	public List<ServerRepoEbo> listSrvRepo(String name) throws DataException {
		boolean isNull = Util.isNull(name) ? true : false;
		if (isNull)
			return null;
		List<ServerRepoEbo> listSrvRepo = srvRepoDao.listSrvRepo(name);
		return listSrvRepo;
	}

}
