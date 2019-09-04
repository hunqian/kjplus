package com.kjplus.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.ybk.basic.util.Util;

import com.kjplus.dao.IHealthExamDao;
import com.kjplus.dto.HealthExamDto;
import com.kjplus.eto.FollowupMainEto;
import com.kjplus.eto.HealthExamEto;
import com.kjplus.model.DocumentInfoEbo;
import com.kjplus.model.HealthExamEbo;
import com.kjplus.model.OrgEbo;
import com.kjplus.model.StaffEbo;
import com.kjplus.model.TableCfgHeadEbo;
import com.kjplus.model.inner.HealthExamInnerEbo;
import com.kjplus.qto.HealthExamQto;
import com.kjplus.service.IDocInfoService;
import com.kjplus.service.IHealthExamService;
import com.kjplus.service.IOrgService;
import com.kjplus.service.IStaffService;
import com.kjplus.service.ITableService;
import com.kjplus.util.DataValUtil;
import com.ybk.exception.DataException;

@Service("healthExamService")
public class HealthExamService implements IHealthExamService {

	@Autowired
	private IOrgService orgService;
	@Autowired
	private IStaffService staffService;
	@Autowired
	private IDocInfoService docInfoService;
	@Autowired
	private ITableService tableService;
	@Autowired
	private IHealthExamDao healthExamDao;

	// 获取健康检查记录列表
	public List<HealthExamDto> listHealthExam(HealthExamQto hExamQto, int page, int paging) throws DataException {

		if (hExamQto.getPrsnId() > 0) {
			DocumentInfoEbo docInfoEbo = docInfoService.getDocinfoByIdOrCode(hExamQto.getPrsnId(), null);
			if (docInfoEbo == null) {
				throw new DataException("[err]" + hExamQto.getPrsnId() + "号档案不存在");
			}
		}

		if (hExamQto.getOrgId() > 0) {
			OrgEbo orgById = orgService.getOrgById(hExamQto.getOrgId());
			if (orgById == null) {
				throw new DataException("[err]" + hExamQto.getOrgId() + "组织不存在");
			}
		}

		if (hExamQto.getStaffId() > 0) {
			StaffEbo staffById = staffService.getStaffById(hExamQto.getStaffId());
			if (staffById == null) {
				throw new DataException("[err]" + hExamQto.getStaffId() + "医生不存在");
			}
		}

		List<HealthExamInnerEbo> listHealthExam = healthExamDao.listHealthExam(hExamQto, page, paging);
		List<HealthExamDto> listHealthExamDtos = new ArrayList<HealthExamDto>();
		if (listHealthExam.size() > 0) {
			listHealthExamDtos = new ArrayList<HealthExamDto>();
			for (HealthExamInnerEbo healthExamInnerEbo : listHealthExam) {
				HealthExamDto healthExamDto = new HealthExamDto();
				BeanUtils.copyProperties(healthExamInnerEbo, healthExamDto);
				listHealthExamDtos.add(healthExamDto);
			}
		}
		return listHealthExamDtos;
	}

	// 根据ID或Code获取指定健康检查记录
	public HealthExamEbo getHealthExamByIdOrCode(int id, String code) throws DataException {
		boolean isNull = id <= 0 && Util.isNull(code) ? true : false;
		if (isNull)
			return null;
		return healthExamDao.getHealthExamByIdOrCode(id, code);

	}

	// 添加健康检查记录信息
	@Transactional(value = "txManager", rollbackFor = { RuntimeException.class, Exception.class, DataException.class }, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, readOnly = true, timeout = 3)
	public HealthExamEbo addHealthExam(HealthExamEto hExamEto) throws DataException {
		// 做空值验证
		DataValUtil.dataValidation(hExamEto.getClass(), hExamEto);

		if (hExamEto.getPrsnId() > 0) {
			DocumentInfoEbo docInfoEbo = docInfoService.getDocinfoByIdOrCode(hExamEto.getPrsnId(), null);
			if (docInfoEbo == null) {
				throw new DataException("[err]" + hExamEto.getPrsnId() + "号档案不存在");
			}
		}

		if (hExamEto.getStaffId() > 0) {
			StaffEbo staffById = staffService.getStaffById(hExamEto.getStaffId());
			if (staffById == null) {
				throw new DataException("[err]" + hExamEto.getStaffId() + "医生不存在");
			}
		}

		TableCfgHeadEbo cfgHeadById = tableService.getCfgHeadById(hExamEto.getTbcfgId());
		if (cfgHeadById == null) {
			throw new DataException("[err]" + hExamEto.getTbcfgId() + "号表格不存在");
		}

		// 为每一条健康检查记录生成Code
		String code = Util.genDigiCodeStr(FollowupMainEto.MAX_SELFCODE_LEN);
		HealthExamEbo healthExamByIdOrCode = getHealthExamByIdOrCode(0, code);
		while (healthExamByIdOrCode != null) {
			code = Util.genDigiCodeStr(FollowupMainEto.MAX_CODE_LEN);
			healthExamByIdOrCode = getHealthExamByIdOrCode(0, code);
		}

		HealthExamEbo healthExamEbo = new HealthExamEbo();
		BeanUtils.copyProperties(hExamEto, healthExamEbo);
		healthExamEbo.setCode(code);
		DocumentInfoEbo docinfoByIdOrCode = docInfoService.getDocinfoByIdOrCode(hExamEto.getPrsnId(), null);
		int orgId = docinfoByIdOrCode.getOrgId();
		healthExamEbo.setOrgId(orgId);
		healthExamDao.addHealthExam(healthExamEbo);
		return healthExamEbo;
	}

	// 更新健康检查记录
	public void updateHealthExam(HealthExamEbo hExamEbo) throws DataException {
		if (hExamEbo.getPrsnId() > 0) {
			DocumentInfoEbo docInfoEbo = docInfoService.getDocinfoByIdOrCode(hExamEbo.getPrsnId(), null);
			if (docInfoEbo == null) {
				throw new DataException("[err]" + hExamEbo.getPrsnId() + "号档案不存在");
			}
		}

		// 判断是否存在该医生
		if (hExamEbo.getStaffId() > 0) {
			StaffEbo staffById = staffService.getStaffById(hExamEbo.getStaffId());
			if (staffById == null) {
				throw new DataException("[err]" + hExamEbo.getStaffId() + "医生不存在");
			}
		}
		// 判断表格是否存在
		TableCfgHeadEbo cfgHeadById = tableService.getCfgHeadById(hExamEbo.getTbcfgId());
		if (cfgHeadById == null) {
			throw new DataException("[err]" + hExamEbo.getTbcfgId() + "号表格不存在");
		}
		healthExamDao.updateHealthExam(hExamEbo);
	}

	// 获取健康体检记录总数
	public int getTotalHealthExam(HealthExamQto hExamQto) throws DataException {
		if (hExamQto.getPrsnId() > 0) {
			DocumentInfoEbo docInfoEbo = docInfoService.getDocinfoByIdOrCode(hExamQto.getPrsnId(), null);
			if (docInfoEbo == null) {
				throw new DataException("[err]" + hExamQto.getPrsnId() + "号档案不存在");
			}
		}

		if (hExamQto.getOrgId() > 0) {
			OrgEbo orgById = orgService.getOrgById(hExamQto.getOrgId());
			if (orgById == null) {
				throw new DataException("[err]" + hExamQto.getOrgId() + "组织不存在");
			}
		}

		if (hExamQto.getStaffId() > 0) {
			StaffEbo staffById = staffService.getStaffById(hExamQto.getStaffId());
			if (staffById == null) {
				throw new DataException("[err]" + hExamQto.getStaffId() + "医生不存在");
			}
		}
		int total = healthExamDao.getTotalHealthExam(hExamQto);
		return total;
	}

}
