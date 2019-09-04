package com.kjplus.service.impl;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ybk.basic.util.Util;

import com.kjplus.constant.Constant;
import com.kjplus.dao.IServiceEntryDao;
import com.kjplus.dto.ServiceEntryDto;
import com.kjplus.eto.ServiceConfigEto;
import com.kjplus.eto.ServiceEntryEto;
import com.kjplus.model.ServiceConfigEbo;
import com.kjplus.model.ServiceEntryEbo;
import com.kjplus.model.SysRefValueEbo;
import com.kjplus.model.inner.ServiceEntryInnerEbo;
import com.kjplus.service.ICalendarMainService;
import com.kjplus.service.IDeptService;
import com.kjplus.service.IOrgService;
import com.kjplus.service.IServiceEntryService;
import com.kjplus.service.IStaffService;
import com.kjplus.service.ISysBaseService;
import com.kjplus.util.DataValUtil;
import com.ybk.exception.DataException;

@Service("srvEntryServie")
public class ServiceEntryService implements IServiceEntryService {

	private static Logger logger = Logger.getLogger(ServiceEntryService.class);
	@Autowired
	private IServiceEntryDao srvEntryDao;
	@Autowired
	private ISysBaseService baseService;
	@Autowired
	private IOrgService orgService;
	@Autowired
	private ICalendarMainService calMainService;
	@Autowired
	private IStaffService staffService;
	@Autowired
	private IDeptService deptService;

	public ServiceEntryEbo getSrvEntryByIdOrCode(int id, String srvCode) throws DataException {

		boolean isNull = id <= 0 && StringUtils.isBlank(srvCode) ? true : false;
		if (isNull)
			return null;
		return srvEntryDao.getSrvEntryByIdOrCode(id, srvCode);
	}

	public ServiceConfigEbo getSrvConfigById(int id) throws DataException {
		if (id <= 0)
			return null;
		else
			return srvEntryDao.getServiceConfigById(id);
	}

	public List<ServiceConfigEbo> listSrvConfigEbo(int srvId, int orgId) throws DataException {
		return srvEntryDao.listSrvConfigEbo(srvId, orgId);
	}

	// 添加服务总入口
	public ServiceEntryInnerEbo addSrvEntry(ServiceEntryEto srvEntry) throws DataException {

		DataValUtil.dataValidation(srvEntry.getClass(), srvEntry);
		// 查询父服务是否存在
		if (srvEntry.getPid() != null && srvEntry.getPid().intValue() > 0) {
			ServiceEntryEbo entry2 = srvEntryDao.getSrvEntryByIdOrCode(srvEntry.getPid(), null);
			if (entry2 == null)
				throw new DataException("系统无此服务，服务Id：" + srvEntry.getPid());
		}
		SysRefValueEbo refVl = new SysRefValueEbo();
		// S的时候需要对应参照
		if (Constant.SRV_ENTRY_TYPE_SERVICE.equals(srvEntry.getSrvType())) {
			refVl = baseService.getRefVlById(srvEntry.getSrvTypeId());
			if (refVl == null)
				throw new DataException("系统无此参照，参照编号为：" + srvEntry.getSrvTypeId());
		}
		// 生成code
		String code = Util.genDigiCodeStr(ServiceEntryEto.CODE_LEN);
		ServiceEntryEbo entry1 = getSrvEntryByIdOrCode(0, code);
		while (entry1 != null) {
			code = Util.genDigiCodeStr(ServiceEntryEto.CODE_LEN);
			entry1 = getSrvEntryByIdOrCode(0, code);
		}
		ServiceEntryInnerEbo entryInner = new ServiceEntryInnerEbo();
		try {
			ServiceEntryEbo entry3 = new ServiceEntryEbo();
			BeanUtils.copyProperties(srvEntry, entry3);
			entry3.setSrvCode(code);
			entry3.setSrvTypeId(refVl.getId());
			srvEntryDao.addSrvEntry(entry3);
			// 默认添加该服务的配置
			ServiceConfigEto srvConfig = new ServiceConfigEto();
			srvConfig.setSrvId(entry3.getId());
			srvConfig.setOrgId(entry3.getOrgId());
			srvConfig.setProviderPoint(srvEntry.getProviderPoint());
			srvConfig.setAcceptorPoint(srvEntry.getAcceptorPoint());
			ServiceConfigEbo config = addSrvConfig(srvConfig);
			// 复制服务主表
			BeanUtils.copyProperties(entry3, entryInner);
			// 复制服务配置
			BeanUtils.copyProperties(config, entryInner);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return entryInner;
	}

	// 添加并修改服务配置
	public ServiceConfigEbo addSrvConfig(ServiceConfigEto srvConfig) throws DataException {

		ServiceEntryEbo srvEntry = srvEntryDao.getSrvEntryByIdOrCode(srvConfig.getSrvId(), null);
		if (srvEntry == null)
			throw new DataException("该服务不存在");
		ServiceConfigEbo config = null;
		try {
			List<ServiceConfigEbo> cfgs = srvEntryDao.listSrvConfigEbo(srvConfig.getSrvId(), srvConfig.getOrgId());
			if (cfgs.size() > 0) {// 存在该服务的配置 进行更新，且只能修改积分 一一对应
				config = cfgs.get(0);
				srvEntryDao.updateSrvConfig(config.getId(), srvConfig.getAcceptorPoint(), srvConfig.getProviderPoint());
				config = getSrvConfigById(config.getId());
			} else {// 不存在该服务的配置 进行添加
				config = new ServiceConfigEbo();
				BeanUtils.copyProperties(srvConfig, config);
				config.setSrvId(srvEntry.getId());
				srvEntryDao.addSrvConfig(config);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return config;
	}

	// 组织总服务目录列表
	public List<ServiceEntryDto> listServiceEntry(int orgId, List<Integer> typeIds, String type, String opType, String name, String flag, int page, int paging, boolean treeStyle, boolean childNearStyle)
			throws DataException {
		List<ServiceEntryDto> mains = new ArrayList<ServiceEntryDto>();
		
		String typeIdStr = Util.arr2Str(typeIds);
		List<ServiceEntryInnerEbo> inners = srvEntryDao.listServiceEntry(orgId, typeIdStr, type, opType, name, flag, page, paging);

		if (inners.size() <= 0)
			return mains;
		ServiceEntryInnerEbo inner = null;
		ServiceEntryDto srv = null;
		ServiceEntryDto pSrv = null;
		Hashtable<Integer, ServiceEntryDto> servHash = new Hashtable<Integer, ServiceEntryDto>();
		// 去重
		Hashtable<Integer, ServiceEntryInnerEbo> srvInner = new Hashtable<Integer, ServiceEntryInnerEbo>();

		int length = inners.size();
		for (int i = 0; i < length; i++) {
			// 去重操作
			if (!srvInner.containsKey(inners.get(i).getId()))
				srvInner.put(inners.get(i).getId(), inners.get(i));
			else
				continue;
			inner = inners.get(i);
			srv = new ServiceEntryDto();
			BeanUtils.copyProperties(inner, srv);
			if (!treeStyle) {// 不进行树状分布
				mains.add(srv);
			} else {// 树状分布
				if (srv.getPid() != null && servHash.containsKey(srv.getPid())) {// 不是父目录
					pSrv = (ServiceEntryDto) servHash.get(srv.getPid());
					pSrv.getSubs().add(srv);
				} else {
					mains.add(srv);
				}
			}
			servHash.put(srv.getId(), srv);
		}

		if (!treeStyle && childNearStyle) {
			for (int i = 0; i < mains.size(); i++) {
				srv = mains.get(i);
				if (srv.getPid() == null || srv.getPid().intValue() == 0)
					continue;
				if (!servHash.containsKey(srv.getPid()))
					continue;
				for (int j = 0; j < i; j++) {
					if (mains.get(j).getId().intValue() != srv.getPid().intValue())
						continue;
					if (mains.get(j).getPid().intValue() == srv.getPid().intValue())
						continue;
					else {
						mains.remove(i);
						mains.add(j + 1, srv);
						break;
					}
				}
			}
		}
		return mains;
	}

	// 服务入口总数
	public int getTotalEntry(int orgId, List<Integer> typeIds, String type, String opType, String name, String flag) throws DataException {
		int total = 0;
		String typeIdStr = Util.arr2Str(typeIds);
		total = srvEntryDao.getTotalEntry(orgId, typeIdStr, type, opType, name, flag);
		return total;
	}

	// 修改服务主入口
	public void updateSrvEntry(ServiceEntryEbo serviceEntryEbo) throws DataException {

		DataValUtil.dataValidation(serviceEntryEbo.getClass(), serviceEntryEbo);
		if ((serviceEntryEbo.getId() == null || serviceEntryEbo.getId().intValue() == 0) && Util.isNull(serviceEntryEbo.getSrvCode()))
			throw new DataException("请指定要修改的服务入口!");
		else {
			ServiceEntryEbo srv = getSrvEntryByIdOrCode(Util.val(serviceEntryEbo.getId()), Util.val(serviceEntryEbo.getSrvCode()));
			if (srv == null)
				throw new DataException("修改的服务入口不存在!");
		}
		// 查询参照
		SysRefValueEbo refVl = baseService.getRefVlById(serviceEntryEbo.getSrvTypeId());
		if (refVl == null)
			throw new DataException("[err]系统无此参照类型,传入的参照编号为:" + serviceEntryEbo.getSrvType());

		ServiceEntryEbo entry2 = null;
		// 查询父服务是否存在
		if (serviceEntryEbo.getPid() != null && serviceEntryEbo.getPid().intValue() > 0) {
			entry2 = srvEntryDao.getSrvEntryByIdOrCode(serviceEntryEbo.getPid(), null);
			if (entry2 == null)
				throw new DataException("系统无此服务，服务Id：" + serviceEntryEbo.getPid());
		}
		try {
			srvEntryDao.updateSrvEntry(serviceEntryEbo);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

}
