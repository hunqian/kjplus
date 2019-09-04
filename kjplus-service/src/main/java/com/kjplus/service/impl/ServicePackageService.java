package com.kjplus.service.impl;

import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ybk.basic.util.Util;

import com.kjplus.constant.Constant;
import com.kjplus.dao.IServicePackageDao;
import com.kjplus.eto.*;
import com.kjplus.model.*;
import com.kjplus.model.inner.ServicePackageInnerEbo;
import com.kjplus.service.*;
import com.kjplus.util.DataValUtil;
import com.ybk.exception.DataException;

@Service("srvPackageService")
public class ServicePackageService implements IServicePackageService {

	private static Logger logger = Logger.getLogger(ServicePackageService.class);

	@Autowired
	private IServicePackageDao servicePackageDao;
	@Autowired
	private IOrgService orgService;
	@Autowired
	private ISysBaseService baseService;
	@Autowired
	private IStaffService staffService;
	@Autowired
	private IDeptService deptService;
	@Autowired
	private IDocInfoService docInfoService;

	// 通过编号，id 获取服务目录
	public ServicePackageEbo getSrvPackageByIdOrCode(int id, String code) throws DataException {
		boolean isNull = Util.isNull(code) && id <= 0 ? true : false;
		if (isNull)
			return null;
		return servicePackageDao.getSrvPackageByIdOrCode(id, code);

	}

	// 获取组织的默认服务 isDefault必须为Y
	public ServicePackageEbo getSrvPackageByIsDefault(int orgId) throws DataException {
		
		return orgId <= 0 ? null : servicePackageDao.getSrvPackageByIsDefault(orgId, Constant.FLAG_YES);
	}

	// 添加服务目录
	public ServicePackageEbo addSrvPackage(ServicePackageEto srvPackageEto) throws DataException {

		// 做控制验证
		DataValUtil.dataValidation(srvPackageEto.getClass(), srvPackageEto);
		//组织验证
		OrgEbo org = orgService.getOrgById(srvPackageEto.getOrgId());
		if(org==null)
			throw new DataException("[err]添加服务时,组织不能为空，orgId = "+srvPackageEto.getOrgId());
		// 查询参照
		SysRefValueEbo refVl = baseService.getRefVlByCode(srvPackageEto.getPeriodCode());
		if (refVl == null)
			throw new DataException("[err]系统无此参照类型,传入的参照编号为:" + srvPackageEto.getPeriodCode());
		
		// 默认服务查询组织是否有默认服务
		if (Constant.FLAG_YES.equals(srvPackageEto.getIsDefault())) {// 如果是默认服务查询系统中是否存在默认服务
			ServicePackageEbo srv1 = getSrvPackageByIsDefault(org.getId());
			if (srv1 != null) {// 如果组织存在默认服务 修改默认服务为N
				updateSrvPackageIsDefaultByOrgId(org.getId());
			}
		} else if (Constant.FLAG_NO.equals(srvPackageEto.getIsDefault())) {// 如果不是默认服务查询系统是否有默认服务
			ServicePackageEbo srv1 = getSrvPackageByIsDefault(org.getId());
			if (srv1 == null)// 如果没有直接设置为默认服务
				srvPackageEto.setIsDefault(Constant.FLAG_YES);
		}

		// 产生一个8位长
		String code = Util.genDigiCodeStr(ServicePackageEto.CODE_LEN);
		ServicePackageEbo srv = getSrvPackageByIdOrCode(0, code);
		while (srv != null) {
			code = Util.genDigiCodeStr(TableCfgHeadEto.CODE_LEN);
			srv = getSrvPackageByIdOrCode(0, code);
		}
		srvPackageEto.setCode(code);

		ServicePackageEbo serv2 = new ServicePackageEbo();
		BeanUtils.copyProperties(srvPackageEto, serv2);
		serv2.setPeriodTypeId(refVl.getId());
		try {
			servicePackageDao.addSrvPackage(serv2);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return serv2;
	}
	
	// 服务目录列表
	public List<ServicePackageInnerEbo> listSrvPackageInner(String name, int orgId, String status, String isDefault,
			String periodCode, int periodVal,String startDay,String finishDay, int page, int paging) throws DataException{
		List<ServicePackageInnerEbo> listSrvPackage = new ArrayList<ServicePackageInnerEbo>();
		if (orgId > 0) {
			OrgEbo org = orgService.getOrgById(orgId);
			if (org == null)
				return listSrvPackage;
		}
		listSrvPackage = servicePackageDao.listSrvPackageInner(name, orgId, status, isDefault, periodCode, periodVal, startDay, finishDay, page, paging);
		return listSrvPackage;
	}

	// 获取组织服务目录列表总数
	public int getTotalSrvPackage(String name, int orgId, String status, String isDefault, String periodCode,
			int periodVal) throws DataException{
		int total = 0;
		if (orgId > 0) {
			OrgEbo org = orgService.getOrgById(orgId);
			if (org == null)
				return total;
		}
		total = servicePackageDao.getTotalSrvPackage(name, orgId, status, isDefault, periodCode, periodVal);
		return total;
	}

	// 修改服务目录默认状态
	public void updateSrvPackageIsDefault(int id,String code) throws DataException {
		boolean isNull = Util.isNull(code) || id < 0 ? true : false;
		if (isNull)
			throw new DataException("请选择要修改的居民服务包！");
		ServicePackageEbo srvPackage = getSrvPackageByIdOrCode(id, code);
		if(srvPackage==null)
			throw new DataException("该居民服务包不存在！");
		ServicePackageEbo srvPackage2 = getSrvPackageByIsDefault(srvPackage.getOrgId());
		if(srvPackage2!=null){//如果有默认服务 	先修改为非默认服务
			servicePackageDao.updateSrvPackageIsDefault(srvPackage2.getOrgId(), Constant.FLAG_NO);
		}
		//将该项修改为默认服务
		srvPackage.setIsDefault(Constant.FLAG_YES);
		servicePackageDao.updateSrvPackage(srvPackage);
	}
	
	public void updateSrvPackageIsDefaultByOrgId(int orgId) throws DataException {
		boolean isNull = orgId < 0 ? true : false;
		if (isNull)
			return;
		servicePackageDao.updateSrvPackageIsDefault(orgId, Constant.FLAG_NO);
	}

	public void updateSrvPackage(ServicePackageEbo srvPackageEbo) throws DataException {
		boolean isNull = Util.isNull(Util.val(srvPackageEbo.getCode())) && Util.val(srvPackageEbo.getId()) <= 0 ?true:false;
		if(isNull)
			throw new DataException("请选择要修改的服务包！");

		// 查询参照
		SysRefValueEbo refVl = baseService.getRefVlById(srvPackageEbo.getPeriodTypeId());
		if (refVl == null)
			throw new DataException("[err]系统无此参照类型,传入的参照编号为:" + srvPackageEbo.getPeriodTypeId());
		servicePackageDao.updateSrvPackage(srvPackageEbo);
	}

}
