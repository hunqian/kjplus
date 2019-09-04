package com.kjplus.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ybk.basic.util.DateUtil;
import org.ybk.basic.util.Util;

import com.kjplus.constant.Constant;
import com.kjplus.dao.IPersonOplogDao;
import com.kjplus.eto.PersonOplogEto;
import com.kjplus.model.AdminUserEbo;
import com.kjplus.model.DocumentInfoEbo;
import com.kjplus.model.PersonOplogEbo;
import com.kjplus.model.SysRefValueEbo;
import com.kjplus.model.inner.PersonOplogInnerEbo;
import com.kjplus.service.IAdminUserService;
import com.kjplus.service.IDocInfoService;
import com.kjplus.service.IPersonOplogService;
import com.kjplus.service.ISysBaseService;
import com.kjplus.util.DataValUtil;
import com.ybk.exception.DataException;

@Service("personOplogService")
public class PersonOplogService implements IPersonOplogService {

	@Autowired
	private IPersonOplogDao personOplogDao;
	@Autowired
	private IDocInfoService docInfoService;
	@Autowired
	private IAdminUserService adminUserService;
	@Autowired
	private ISysBaseService baseService;

	private static Logger logger = Logger.getLogger(PersonOplogService.class);

	// 根据id查询PersonOplogEbo
	public PersonOplogEbo getPrsnOplogById(int id) throws DataException {
		// 空值判断
		if (id <= 0)
			return null;
		return personOplogDao.getPrsnOplogById(id);
	}

	// 列表档案操作记录
	public List<PersonOplogInnerEbo> listPrsnOplog(List<Integer> prsnIds, int opTypeId, int uid, int orgid, String flag, int startTime, int endTime,boolean isPrsn, int page, int paging)
			throws DataException {
		if(prsnIds == null || prsnIds.size() <=0 && (!isPrsn))
			return null;
		String prsnStr = Util.arr2Str(prsnIds);
		return personOplogDao.listPrsnOplog(prsnStr, opTypeId, uid, orgid, flag, startTime, endTime, page, paging);
	}

	public int getTotaPrsnOplog(List<Integer> prsnIds, int opTypeId, int uid, int orgid, String flag, int startTime, int endTime,boolean isPrsn) throws DataException {
		if(prsnIds == null || prsnIds.size() <=0 && (!isPrsn))
			return 0;
		String prsnStr = Util.arr2Str(prsnIds);
		return personOplogDao.getTotalPrsnOplog(prsnStr, opTypeId, uid, orgid, flag, startTime, endTime);
	}

	public PersonOplogEbo addPrsnOplog(PersonOplogEto prsnOplog) throws DataException {
		// 空值判断
		DataValUtil.dataValidation(prsnOplog.getClass(), prsnOplog);
		// 空值验证后 ，无需报错 直接将信息打印入日志中
		// 档案用户判断
		DocumentInfoEbo doc = docInfoService.getDocinfoByIdOrCode(prsnOplog.getPrsnId(), null);
		if (doc == null) {
			logger.info("添加档案操作记录时，档案用户不存在，prsnId =" + prsnOplog.getPrsnId());
			return null;
		}
		if(prsnOplog.getOrgid() == null)
			prsnOplog.setOrgid(doc.getOrgId());
		// 操作人判断
		if (prsnOplog.getUid() != null && prsnOplog.getUid().intValue() > 0) {
			AdminUserEbo admin = adminUserService.getUserByUid(prsnOplog.getUid());
			// 若管理员不存在 则将uid变为0,同时日志记录
			if (admin == null) {
				logger.info("添加档案操作记录时，操作用户不存在，uid =" + prsnOplog.getUid() + ";操作时间：" + DateUtil.getCurTimeInSec());
				prsnOplog.setUid(0);
			}
		}
		// 操作类型判断
		SysRefValueEbo refVl = baseService.getRefVlByCode(prsnOplog.getOpTypeCode());
		if (refVl == null)// 为空，则默认档案更新
			refVl = baseService.getRefVlByCode(Constant.RV_DOC_UPDATE);
		// TODO 档案操作此时统计
		int score = getBestOpSeq(prsnOplog.getPrsnId(), Constant.FLAG_YES);
		if (score > 0)
			prsnOplog.setOpSeq(++score);
		PersonOplogEbo prsn = new PersonOplogEbo();
		BeanUtils.copyProperties(prsnOplog, prsn);
		prsn.setOpTypeId(refVl.getId());
		try {
			personOplogDao.addPrsnOplog(prsn);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return prsn;
	}

	// 获取某条档案的操作记录
	public int getBestOpSeq(int prsnId, String flag) throws DataException {
		if (prsnId <= 0)
			return 0;
		Integer seq = personOplogDao.getBestOpSeq(prsnId, flag);
		if (seq == null || seq.intValue() < 0)
			return 0;
		else
			return seq.intValue();
	}

}
