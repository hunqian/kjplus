package com.kjplus.service.impl;

import java.util.*;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ybk.basic.util.Util;

import com.kjplus.dao.IServiceSumDao;
import com.kjplus.dto.ServiceListDto;
import com.kjplus.dto.ServiceSumDto;
import com.kjplus.eto.*;
import com.kjplus.model.*;
import com.kjplus.model.inner.ServiceSumInnerEbo;
import com.kjplus.service.*;
import com.kjplus.util.DataValUtil;
import com.ybk.exception.DataException;

@Service("serviceSumServie")
public class ServiceSumService implements IServiceSumService {

	@Autowired
	private IServiceSumDao srvSumDao;
	@Autowired
	private IAdminUserService adminUserService;
	@Autowired
	private IUserService userService;
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

	public ServiceSumEbo getSrvSumById(int id) throws DataException {
		boolean isNull = id < 0 ? true : false;
		if (isNull)
			return null;
		return srvSumDao.getSrvSumById(id);
	}

	public ServiceSumEbo getSrvSumByBody(int bodyId, String bodyType) throws DataException {
		boolean isNull = bodyId < 0 && Util.isNull(bodyType) ? true : false;
		if (isNull)
			return null;
		return srvSumDao.getSrvSumByBody(bodyId, bodyType);
	}

	public ServiceListEbo getSrvListById(int id) throws DataException {
		boolean isNull = id < 0 ? true : false;
		if (isNull)
			return null;
		return srvSumDao.getSrvListById(id);
	}

	public ServiceSumEbo addSrvSum(ServiceSumEto srvSum) throws DataException {
		// 空值验证
		DataValUtil.dataValidation(srvSum.getClass(), srvSum);
		ServiceSumEbo s = getSrvSumByBody(srvSum.getBodyId(), srvSum.getBodyType());
		if (s != null)
			return s;
		if (srvSum.getBodyType().equals("A")) {// 管理员类型
			AdminUserEbo admin = adminUserService.getUserByUid(srvSum.getBodyId());
			if (admin == null)
				throw new DataException("添加该积分用户不存在");
		}
		if (srvSum.getBodyType().equals("U")) {// 普通用户类型
			UserEbo user = userService.getUserById(srvSum.getBodyId());
			if (user == null)
				throw new DataException("添加该积分用户不存在");
		}
		ServiceSumEbo sum = new ServiceSumEbo();
		BeanUtils.copyProperties(srvSum, sum);
		srvSumDao.addSrvSum(sum);
		return sum;
	}

	public ServiceListEbo addSrvList(ServiceListEto srvList) throws DataException {
		// 空值验证
		DataValUtil.dataValidation(srvList.getClass(), srvList);
		ServiceSumEbo sum = getSrvSumById(srvList.getSumId());
		if (sum == null)
			throw new DataException("该积分总目录不存在！");
		ServiceListEbo srvListEbo = new ServiceListEbo();
		BeanUtils.copyProperties(srvList, srvListEbo);
		srvSumDao.addSrvList(srvListEbo);
		return srvListEbo;
	}

	public List<ServiceSumDto> listSrvSumDto(int orgId, String bodyType, int page, int paging) throws DataException {
		List<ServiceSumDto> listSum = new ArrayList<ServiceSumDto>();
		if (orgId > 0) {
			OrgEbo org = orgService.getOrgById(orgId);
			if (org == null)
				return listSum;
		}
		if (Util.isNotNull(bodyType) && !("A".equals(bodyType) || "U".equals(bodyType)))// 如果bodyType不为空且不是A或U
			return listSum;
		if (paging <= 0)
			return listSum;
		List<ServiceSumInnerEbo> listSumEbo = srvSumDao.listSrvSumInner(orgId, bodyType, page, paging);
		Map<Integer, ServiceSumDto> sumMap = new HashMap<Integer, ServiceSumDto>();
		for (ServiceSumInnerEbo s : listSumEbo) {
			ServiceListDto lt = new ServiceListDto();
			BeanUtils.copyProperties(s, lt);
			if (sumMap.containsKey(s.getId()))
				sumMap.get(s.getId()).getListLt().add(lt);
			else {
				ServiceSumDto sum = new ServiceSumDto();
				BeanUtils.copyProperties(s, sum);
				sum.getListLt().add(lt);
				sumMap.put(s.getId(), sum);
				listSum.add(sum);
			}
		}
		return listSum;
	}

	public List<ServiceListEbo> listSrvList(int sumId, int mainId, String mainType, int startTime, int endTime,
			String status, int page, int paging) throws DataException {
		List<ServiceListEbo> listList = new ArrayList<ServiceListEbo>();
		if (sumId > 0) {
			ServiceSumEbo sum = getSrvSumById(sumId);
			if (sum == null)
				return listList;
		}else
			return listList; 
		if(sumId < 0)
		if (endTime < startTime)
			return listList;
		if (Util.isNotNull(status) && !("I".equals(status) || "D".equals(status)))// 如果bodyType不为空且不是A或U
			return listList;
		if (paging <= 0)
			return listList;
		listList = srvSumDao.listSrvList(sumId, mainId, mainType, startTime, endTime, status, page, paging);
		return listList;
	}

	public void updateServiceSumPoint(int id, Double totalEarnPoint, Double totalConsumePoint, Double totalPoint)
			throws DataException {
		srvSumDao.updateServiceSumPoint(id, totalEarnPoint, totalConsumePoint, totalPoint);
	}

	public void updateServiceSumPoint(int id, Double point, String status) throws DataException {
		boolean isNull = point == null || point.doubleValue() == 0.0 || Util.isNull(status) ? true : false;
		if (isNull)
			return;
		if (!(status.equals("I") || status.equals("D")))
			throw new DataException("输入的积分处理状态有误！");
		if (point > 0 && status.equals("D"))
			throw new DataException("当减少积分时，分数应该为负数!");
		if (point > 0 && status.equals("I"))
			throw new DataException("当增加积分时，分数应该为正数!");
		ServiceSumEbo sum = getSrvSumById(id);
		if (sum == null)
			throw new DataException("还积分记录不存在");
		// 总赚取积分
		Double totalEarnPoint = sum.getTotalEarnPoint();
		// 总消费积分
		Double totalConsumePoint = sum.getTotalConsumePoint();
		// 剩余积分
		Double totalPoint = sum.getTotalPoint();
		// 当积分是减少时 point为负数
		totalEarnPoint = totalEarnPoint + point;
		totalPoint = totalEarnPoint - totalConsumePoint;
		srvSumDao.updateServiceSumPoint(id, totalEarnPoint, totalConsumePoint, totalPoint);
	}

}
