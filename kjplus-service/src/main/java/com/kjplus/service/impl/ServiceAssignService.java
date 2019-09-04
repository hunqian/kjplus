package com.kjplus.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.ybk.basic.util.DateUtil;
import org.ybk.basic.util.DateUtils;
import org.ybk.basic.util.Util;

import com.kjplus.constant.Constant;
import com.kjplus.dao.IServiceAsgnDao;
import com.kjplus.dao.IServicePackageDao;
import com.kjplus.dto.IDNameDto;
import com.kjplus.dto.OrgServHeadDto;
import com.kjplus.dto.ServAsgnDto;
import com.kjplus.dto.ServAsgnPackageDto;
import com.kjplus.dto.ServHeadDto;
import com.kjplus.eto.ServHeadEto;
import com.kjplus.eto.SrvAsgnEto;
import com.kjplus.model.DepartmentEbo;
import com.kjplus.model.DocumentInfoEbo;
import com.kjplus.model.OrgEbo;
import com.kjplus.model.ServAsgnEbo;
import com.kjplus.model.ServHeadEbo;
import com.kjplus.model.ServicePackageEbo;
import com.kjplus.model.StaffDeptEbo;
import com.kjplus.model.StaffEbo;
import com.kjplus.model.inner.OrgServHeadInnerEbo;
import com.kjplus.model.inner.ServiceAssignInnerEbo;
import com.kjplus.service.IDeptService;
import com.kjplus.service.IDocInfoService;
import com.kjplus.service.IFileRepoService;
import com.kjplus.service.IOrgService;
import com.kjplus.service.IServiceAssignService;
import com.kjplus.service.IStaffService;
import com.kjplus.service.ISysBaseService;
import com.kjplus.util.DataValUtil;
import com.ybk.exception.DataException;

@Service("srvAssignService")
public class ServiceAssignService implements IServiceAssignService {

	private static Logger logger = Logger.getLogger(ServiceAssignService.class);

	@Autowired
	private IServiceAsgnDao servAsgnDao;
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
	@Autowired
	private IServicePackageDao servicePackageDao;

	@Autowired
	private IFileRepoService fileRepoService;

	private static List<String> status = new ArrayList<String>();
	// 签约状态 初始话
	static {
		status.add(Constant.SRV_ASSIGN_STATUS_BREAK);
		status.add(Constant.SRV_ASSIGN_STATUS_AGREE);
		status.add(Constant.SRV_ASSIGN_STATUS_REFUSE);
		status.add(Constant.SRV_ASSIGN_STATUS_APPLY);
	}

	// 添加签约记录
	@Transactional(value = "txManager", rollbackFor = { RuntimeException.class, Exception.class, DataException.class }, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, readOnly = true, timeout = 3)
	public void addServAsgn(SrvAsgnEto servAsgn) throws DataException {

		// 做控空值验证
		DataValUtil.dataValidation(servAsgn.getClass(), servAsgn);
		DocumentInfoEbo doc = null;
		if (Util.isNotNull(servAsgn.getPrsnCode()) || servAsgn.getPrsnId() != null || servAsgn.getPrsnId().intValue() > 0) {
			// 通过医生编号查询医生是否存在
			doc = docInfoService.getDocinfoByIdOrCode(Util.val(servAsgn.getPrsnId()), Util.val(servAsgn.getPrsnCode()));
			if (doc == null)
				throw new DataException("[err]签约用户不存在！");
		} else
			throw new DataException("[err]签约用户不能为空");
		// 查重 一个用户在有效期内只可以签约一个团队
		List<ServAsgnEbo> srvAss = listServAssByPrsnId(doc.getPrsnId(), true);
		if (srvAss.size() > 0)
			throw new DataException("[err]该用户已经签约团队或是正在申请签约团队，不可重复申请");

		StaffEbo staff = null;
		if (Util.isNotNull(servAsgn.getStafCode())) {
			// 通过医生编号查询医生是否存在
			staff = staffService.getStaffByCode(servAsgn.getStafCode());
			if (staff == null)
				throw new DataException("[err]系统无此医生，staff编号为:" + servAsgn.getStafCode());
		}
		if (servAsgn.getStaffId() != null && servAsgn.getStaffId().intValue() > 0) {
			// 通过医生编号查询医生是否存在
			staff = staffService.getStaffById(servAsgn.getStaffId());
			if (staff == null)
				throw new DataException("[err]系统无此医生，staff编号为:" + servAsgn.getStaffId());
		}
		DepartmentEbo dept = null;
		if (Util.isNotNull(servAsgn.getDeptCode())) {
			dept = deptService.getDepartmentByCode(servAsgn.getDeptCode());
			if (dept == null)
				throw new DataException("[err]系统无此部门，detp编号为:" + servAsgn.getDeptCode());
		} else {
			// 医生与团队 一对一
			List<StaffDeptEbo> staffDept = staffService.listStaffDeptMapEbo(staff.getId(), 0);
			dept = deptService.getDepartmentById(staffDept.get(0).getDeptId());
		}

		List<ServAsgnEbo> servAsgns = new ArrayList<ServAsgnEbo>();
		if (servAsgn.getListPackageCode().size() < 0) {
			throw new DataException("请选定服务包！");
		}
		ServicePackageEbo srvCat = null;
		String code = null;
		ServAsgnEbo srvAsgnEbo = null;
		for (String s : servAsgn.getListPackageCode()) {
			srvAsgnEbo = new ServAsgnEbo();
			BeanUtils.copyProperties(servAsgn, srvAsgnEbo);
			srvAsgnEbo.setPrsnId(doc.getPrsnId());
			if (staff == null)
				srvAsgnEbo.setStafId(0);
			else
				srvAsgnEbo.setStafId(staff.getId());

			if (dept == null)
				srvAsgnEbo.setDeptId(0);
			else
				srvAsgnEbo.setDeptId(dept.getId());

			srvCat = new ServicePackageEbo();
			// 生成 code
			code = Util.genDigiCodeStr(SrvAsgnEto.CODE_LEN);
			ServAsgnEbo srv = getServAssByIdOrCode(0, code);
			while (srv != null) {
				code = Util.genDigiCodeStr(SrvAsgnEto.CODE_LEN);
				srv = getServAssByIdOrCode(0, code);
			}
			srvAsgnEbo.setCode(code);

			// 通过服务编号查询服务是否需存在
			srvCat = servicePackageDao.getSrvPackageByIdOrCode(0, s);
			if (srvCat == null)
				throw new DataException("[err]系统无此服务，服务编号为:" + s);
			srvAsgnEbo.setSrvId(srvCat.getId());
			servAsgns.add(srvAsgnEbo);
		}
		try {
			servAsgnDao.addServAsgn(servAsgns);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	// 修改签约信息　
	public void updateServAsgn(ServAsgnEbo servAsgnEbo) throws DataException {
		// 做控空值验证
		DataValUtil.dataValidation(servAsgnEbo.getClass(), servAsgnEbo);
		boolean isNull = servAsgnEbo.getId() <= 0 && Util.isNull(servAsgnEbo.getCode()) ? true : false;
		if (isNull)
			throw new DataException("修改签约信息时，id和code不能同时为空！");

		// 通过服务编号查询居民服务包是否存在
		if (servAsgnEbo.getSrvId() > 0) {
			ServicePackageEbo srvCat = servicePackageDao.getSrvPackageByIdOrCode(servAsgnEbo.getSrvId(), null);
			if (srvCat == null)
				throw new DataException("[err]系统无此服务，服务ID为:" + servAsgnEbo.getSrvId());
		}
		try {
			servAsgnDao.updateServAsgn(servAsgnEbo);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	// 修改签约服务状态
	public void updateServAsgnStatus(List<IDNameDto> idCodes, String status, String memo) throws DataException {
		if (idCodes.size() <= 0)
			throw new DataException("[err]修改签约状态时，id和code不能同时为空！");
		if (Util.isNull(status))
			throw new DataException("[err]传入状态不可为空");
		else {
			// 非指定状态 抛出异常
			if (!status.contains(status))
				throw new DataException("[err]传入状态不正确");
		}
		for (IDNameDto i : idCodes) {
			// 查询服务是否存在
			ServAsgnEbo srv = servAsgnDao.getServAssByIdOrCode(i.getId(), i.getCode());
			if (srv == null)
				throw new DataException("[err]系统无此签约记录");
		}
		// 设置操作时间
		int operTime = DateUtils.getCurTimeInSec();
		servAsgnDao.updateServAsgnStatus(idCodes, status, operTime, memo);
	}
	
	public void updateServAsgnStatus(int id,String code, String status, String memo) throws DataException{
		List<IDNameDto> idCodes = new ArrayList<IDNameDto>();
		IDNameDto ids = new IDNameDto();
		ids.setId(id);
		ids.setCode(code);
		idCodes.add(ids);
		updateServAsgnStatus(idCodes, status, memo);
	}	
	
	// 通过ID或code获取签约记录信息
	public ServAsgnEbo getServAssByIdOrCode(int id, String code) throws DataException {
		boolean isNull = id <= 0 && Util.isNull(code) ? true : false;
		if (isNull)
			throw new DataException("[err]Id和code不能同时为空");
		return servAsgnDao.getServAssByIdOrCode(id, code);
	}

	public List<ServAsgnEbo> listServAssByPrsnId(int prsnId, boolean isEffect) throws DataException {
		if (prsnId <= 0)
			throw new DataException("[err]prsnId不能同时为空");
		String cunrrentDay = DateUtil.currDay();
		if (isEffect)
			return servAsgnDao.listServAssByPrsnId(prsnId, cunrrentDay);
		else
			return servAsgnDao.listServAssByPrsnId(prsnId, null);
	}

	// 签约列表 可用于查看签约居民是否建档。
	public List<ServAsgnDto> listAsgnDto(String personName, int personId, String staffName, int staffId, String isDefault, int orgId, String status,
			boolean isEffect, boolean isPackages, int page, int paging) throws DataException {
		// 查出数据库全部数据
		List<ServiceAssignInnerEbo> listServAsgn = servAsgnDao.listAsgnInner(personName, personId, staffName, staffId, isDefault, orgId, status, null, page,
				paging);
		List<ServiceAssignInnerEbo> removes = new ArrayList<ServiceAssignInnerEbo>();
		if (isEffect) {// 是否有效 筛选该用户最新签约记录
			for (int i = 0; i < listServAsgn.size(); i++) {
				boolean isFlag = DateUtil.getDateInSecond(listServAsgn.get(i).getEndDay()) < DateUtil.getDateInSecond(DateUtil.newTime());
				if (isFlag)
					removes.add(listServAsgn.get(i));
			}
			listServAsgn.removeAll(removes);
		}
		// 最后存储数据
		List<ServAsgnDto> listAssign = new ArrayList<ServAsgnDto>();
		for (ServiceAssignInnerEbo srv : listServAsgn) {
			if (isPackages) {
				ServAsgnPackageDto pack = new ServAsgnPackageDto();
				BeanUtils.copyProperties(srv, pack);
				// 数据筛选
				int result = filterData(srv, listAssign);
				if (result < 0) {
					ServAsgnDto ass = new ServAsgnDto();
					BeanUtils.copyProperties(srv, ass);
					ass.getListPackage().add(pack);
					listAssign.add(ass);
				} else {
					if (listAssign.size() > result)
						listAssign.get(result).getListPackage().add(pack);
				}
			} else {
				ServAsgnDto ass = new ServAsgnDto();
				BeanUtils.copyProperties(srv, ass);
				listAssign.add(ass);
			}
		}
		return listAssign;
	}

	// 用于数据筛选listAssign是否包含srv中除了签约包(id,code)不一样的其他数据均相等的数据
	public int filterData(ServiceAssignInnerEbo srv, List<ServAsgnDto> listAssign) {
		int result = -1;
		// 判空
		boolean isNull = srv == null || listAssign.size() <= 0 ? true : false;
		if (isNull)
			return result;
		int length = listAssign.size();
		for (int i = 0; i < length; i++) {
			// 判断同一次签约的多个包依据 签约人相同 医生部门相同 签约开始时间相同
			boolean isSame = Util.val(srv.getPersonId()) == listAssign.get(i).getPersonId() && Util.val(srv.getStafId()) == listAssign.get(i).getStafId()
					&& Util.val(srv.getDeptId()) == listAssign.get(i).getDeptId()
					&& DateUtil.getDateInSecond(srv.getBeginDay()) == DateUtil.getDateInSecond(listAssign.get(i).getBeginDay()) ? true : false;
			if (isSame)// 如果均相等则代表有该类数据存储
				return i;
		}
		return result;
	}

	// 获取签约记录总数
	public int getTotalServAss(String personName, int personId, String staffName, int staffId, String isDefault, int orgId, String status, boolean isEffect,
			boolean isPackages) throws DataException {
		int total = servAsgnDao.getTotalServAss(personName, personId, staffName, staffId, isDefault, orgId, status, null);
		// 查出数据库全部数据
		List<ServiceAssignInnerEbo> listServAsgn = servAsgnDao.listAsgnInner(personName, personId, staffName, staffId, isDefault, orgId, status, null, 0, -1);
		List<ServiceAssignInnerEbo> removes = new ArrayList<ServiceAssignInnerEbo>();
		if (isEffect) {// 是否有效
			for (int i = 0; i < listServAsgn.size(); i++) {
				boolean isFlag = DateUtil.getDateInSecond(listServAsgn.get(i).getEndDay()) < DateUtil.getDateInSecond(DateUtil.newTime());
				if (isFlag) {
					removes.add(listServAsgn.get(i));
					total--;
				}
				listServAsgn.removeAll(removes);
			}
		}
		List<ServAsgnDto> listAssign = new ArrayList<ServAsgnDto>();
		if (isPackages) {
			for (ServiceAssignInnerEbo srv : listServAsgn) {
				ServAsgnPackageDto pack = new ServAsgnPackageDto();
				BeanUtils.copyProperties(srv, pack);
				// 数据筛选
				int result = filterData(srv, listAssign);
				if (result < 0) {
					ServAsgnDto ass = new ServAsgnDto();
					BeanUtils.copyProperties(srv, ass);
					ass.getListPackage().add(pack);
					listAssign.add(ass);
				} else {
					if (listAssign.size() > result) {
						listAssign.get(result).getListPackage().add(pack);
						total--;
					}

				}
			}
		}

		return total;
	}

	// 添加协议
	public void addServHead(ServHeadEto servHead) throws DataException {
		DataValUtil.dataValidation(servHead.getClass(), servHead);
		OrgEbo org = orgService.getOrgByCode(servHead.getOrgCode());
		if (org == null)
			throw new DataException("系统无此组织组织编号为" + servHead.getOrgCode());
		ServHeadEbo srvHead = new ServHeadEbo();
		BeanUtils.copyProperties(servHead, srvHead);
		srvHead.setOrgId(org.getId());
		servAsgnDao.addServHead(srvHead);
	}

	// 修改协议状态
	public void updateServHeadByidOrOrgId(int servHeadId, int orgId, String flag) throws DataException {
		boolean isNull = (servHeadId <= 0 && orgId <= 0) || Util.isNull(flag) ? true : false;
		if (isNull)
			return;
		OrgEbo org = orgService.getOrgById(orgId);
		if (org == null)
			throw new DataException("系统无此组织，组织id为：" + orgId);
		ServHeadEbo srvHead = servAsgnDao.getServHeadById(servHeadId);
		if (srvHead == null) {
			throw new DataException("系统无此协议,协议id为：" + servHeadId);
		} else {
			if (flag.equals(srvHead.getFlag()))
				return;
		}
		servAsgnDao.updateServHeadByidOrOrgId(servHeadId, orgId, flag);
	}

	// 查询协议列表
	public List<OrgServHeadDto> listServHead(int orgId, String orgCode, String flag) throws DataException {

		List<OrgServHeadInnerEbo> srvHeads = servAsgnDao.listServHead(orgId, orgCode, flag);
		List<OrgServHeadDto> orgSrvs = new ArrayList<OrgServHeadDto>();
		OrgServHeadDto orgSrvHead = null;
		ServHeadDto srvhead = null;
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		int orgid = 0;
		for (OrgServHeadInnerEbo srvHead : srvHeads) {
			orgid = srvHead.getOrgId();
			if (!map.containsKey(orgid)) {
				orgSrvHead = new OrgServHeadDto();
				BeanUtils.copyProperties(srvHead, orgSrvHead);
				srvhead = new ServHeadDto();
				BeanUtils.copyProperties(srvHead, srvhead);
				orgSrvHead.getSrvHeads().add(srvhead);
				orgSrvs.add(orgSrvHead);
				map.put(orgid, orgSrvHead);
			} else {
				srvhead = new ServHeadDto();
				BeanUtils.copyProperties(srvHead, srvhead);
				orgSrvHead = (OrgServHeadDto) map.get(orgid);
				orgSrvHead.getSrvHeads().add(srvhead);
			}
		}
		return orgSrvs;

	}

}
