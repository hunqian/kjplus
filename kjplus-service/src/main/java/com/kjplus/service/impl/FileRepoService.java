package com.kjplus.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.ybk.basic.util.Util;

import com.kjplus.dao.IFileRepoDao;
import com.kjplus.eto.FileRepoEto;
import com.kjplus.model.*;
import com.kjplus.model.inner.FileRepoInnerEbo;
import com.kjplus.service.*;
import com.kjplus.util.DataValUtil;
import com.ybk.exception.DataException;

@Service("fileRepoService")
public class FileRepoService implements IFileRepoService {

	@Autowired
	private IFileRepoDao fileRepoDao;
	@Autowired
	private IServerRepoService serverRepoService;

	public FileRepoEbo getFileRepoByIdOrCode(int id, String code) throws DataException {
		boolean isNull = id <= 0 && Util.isNull(code) ? true : false;
		if (isNull)
			return null;
		return fileRepoDao.getFileRepoByIdOrCode(id, code);
	}

	// 加入事务
	@Transactional(value = "txManager", rollbackFor = { RuntimeException.class, Exception.class, DataException.class }, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, readOnly = true, timeout = 3)
	public FileRepoEbo addFileRepoEbo(FileRepoEto fileRepoEto) throws DataException {
		DataValUtil.dataValidation(fileRepoEto.getClass(), fileRepoEto);
		if (fileRepoEto.getSrvId() > 0) {
			ServerRepoEbo srv = serverRepoService.getSrvRepoByIdOrCode(fileRepoEto.getSrvId(), null);
			if (srv == null)
				throw new DataException("该服务器不存在");
		}
		FileRepoEbo file = new FileRepoEbo();
		BeanUtils.copyProperties(fileRepoEto, file);
		String code = "";
		code = Util.genDigiCodeStr(FileRepoEto.CODE_LEN);
		FileRepoEbo fileRepo = getFileRepoByIdOrCode(0, code);
		while (fileRepo != null) {
			code = Util.genDigiCodeStr(FileRepoEto.CODE_LEN);
			fileRepo = getFileRepoByIdOrCode(0, code);
		}
		file.setCode(code);
		fileRepoDao.addFileRepoEbo(file);
		return file;
	}

	public List<FileRepoInnerEbo> listFileRepoInner(int srvId, int mainId, String mainType, String fileType, int page, int paging)
			throws DataException {
		if (srvId > 0) {
			ServerRepoEbo srv = serverRepoService.getSrvRepoByIdOrCode(srvId, null);
			if (srv == null)
				throw new DataException("该服务器不存在");
		}
		List<FileRepoInnerEbo> listFileRepo = fileRepoDao.listFileRepoInner(srvId, mainId, mainType, fileType, page, paging);
		return listFileRepo;
	}

}
