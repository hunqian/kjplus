package com.kjplus.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.ybk.basic.util.Util;

import com.kjplus.constant.Constant;
import com.kjplus.dao.IFollowupDao;
import com.kjplus.dto.FollowupMainDto;
import com.kjplus.dto.TableDataLineDto;
import com.kjplus.eto.FollowupMainEto;
import com.kjplus.eto.FollowupResEto;
import com.kjplus.eto.PersonOplogEto;
import com.kjplus.model.CodeRepoEbo;
import com.kjplus.model.DocumentInfoEbo;
import com.kjplus.model.FollowupExamEbo;
import com.kjplus.model.FollowupMainEbo;
import com.kjplus.model.FollowupRecEbo;
import com.kjplus.model.OrgEbo;
import com.kjplus.model.StaffEbo;
import com.kjplus.model.inner.FollowupMainInnerEbo;
import com.kjplus.qto.FollowupMainQto;
import com.kjplus.service.IDocInfoService;
import com.kjplus.service.IExamService;
import com.kjplus.service.IFollowupService;
import com.kjplus.service.IOrgService;
import com.kjplus.service.IPersonOplogService;
import com.kjplus.service.IStaffService;
import com.kjplus.service.ISysBaseService;
import com.kjplus.service.ITableService;
import com.kjplus.service.IUserMapService;
import com.kjplus.util.DataValUtil;
import com.ybk.exception.DataException;

@Service("followupService")
public class FollowupService implements IFollowupService {

	private static Logger logger = Logger.getLogger(FollowupService.class);	
	@Autowired
	private IFollowupDao followupDao;
	@Autowired
	private IExamService examService;
	@Autowired
	private IOrgService orgService;
	@Autowired
	private IStaffService staffService;
	@Autowired
	private IDocInfoService docInfoService;
	@Autowired
	private ITableService tableService;
	@Autowired
	private ISysBaseService baseService;
	@Autowired
	private IPersonOplogService personOplogService;
	@Autowired
	private IUserMapService userMapService;
	
	public CodeRepoEbo getCodeRepoByIdOrCode(Integer id, String code) throws DataException {
		boolean isNull = id <= 0 && Util.isNull(code) ? true : false;
		if (isNull)
			return null;
		return followupDao.getCodeRepoByOrCode(id, code);
	}

	public FollowupExamEbo getFollowupExamByIdOrCode(Integer id, String code) throws DataException {
		boolean isNull = id <= 0 && Util.isNull(code) ? true : false;
		if (isNull)
			return null;
		return followupDao.getFollowupExamByIdOrCode(id, code);
	}

	public FollowupRecEbo getFollowupRecByIdOrCode(Integer id, String code) throws DataException {
		boolean isNull = id <= 0 && Util.isNull(code) ? true : false;
		if (isNull)
			return null;
		return followupDao.getFollowupRecByIdOrCode(id, code);
	}

	public FollowupMainEbo getFollowupMainByIdOrCode(Integer id, String code) throws DataException {
		boolean isNull = id <= 0 && Util.isNull(code) ? true : false;
		if (isNull)
			return null;
		return followupDao.getFollowupMainByIdOrCode(id, code);
	}

	public FollowupMainEbo getFollowupMainBySelfCode(String selfCode, int orgId) throws DataException {
		boolean isNull = Util.isNull(selfCode) ? true : false;
		if (isNull)
			return null;
		/*
		 * OrgEbo org = orgService.getOrgById(orgId); if (org == null) throw new
		 * DataException("[err]该组织不存在，orgId="+orgId);
		 */
		return followupDao.getFollowupMainBySelfCode(selfCode, orgId);
	}

	@Transactional(value = "txManager", rollbackFor = { RuntimeException.class, Exception.class, DataException.class }, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, readOnly = true, timeout = 3)
	public FollowupMainEbo addFollowupMain(FollowupMainEto fm) throws DataException {
		// 类型验证判断
		DataValUtil.dataValidation(fm.getClass(), fm);
		if (fm.getPrsnId() == null || fm.getPrsnId().intValue() <= 0)
			throw new DataException("[err]请指定被检测人ID!");
		if (fm.getOrgId() == null || fm.getOrgId().intValue() <= 0) {
			DocumentInfoEbo doc = docInfoService.getDocinfoByIdOrCode(fm.getPrsnId(), null);
			if (doc != null)
				fm.setOrgId(doc.getOrgId());
		}
		// 随机生成code
		String code = Util.genDigiCodeStr(FollowupMainEto.MAX_SELFCODE_LEN);
		FollowupMainEbo fmEbo = getFollowupMainByIdOrCode(0, code);
		while (fmEbo != null) {// 同一组织下的selfCode不相同
			code = Util.genDigiCodeStr(FollowupMainEto.MAX_CODE_LEN);
			fmEbo = getFollowupMainByIdOrCode(0, code);
		}
		fm.setCode(code);
		String selfCode = null;
		if (Util.isNull(fm.getSelfCode())) {// 若不存在，随机生成selfCode
			selfCode = Util.genDigiCodeStr(FollowupMainEto.MAX_SELFCODE_LEN);
			FollowupMainEbo fmEbo2 = getFollowupMainBySelfCode(selfCode, fm.getOrgId());
			while (fmEbo2 != null) {// 同一组织下的selfCode不相同
				selfCode = Util.genDigiCodeStr(FollowupMainEto.MAX_SELFCODE_LEN);
				fmEbo2 = getFollowupMainBySelfCode(selfCode, fm.getOrgId());
			}
		}
		fm.setSelfCode(selfCode);
		FollowupMainEbo fmEboCopy = new FollowupMainEbo();
		BeanUtils.copyProperties(fm, fmEboCopy);
		try {
			followupDao.addFollowupMain(fmEboCopy);
			
			//添加档案操作记录
			PersonOplogEto prsnOplog = new PersonOplogEto();
			prsnOplog.setPrsnId(fmEboCopy.getPrsnId());
			prsnOplog.setOpTypeCode(Constant.RV_DOC_UPDATE);
			prsnOplog.setOrgid(fmEboCopy.getOrgId());
			prsnOplog.setUid(userMapService.getUserIdByStaffId(fmEboCopy.getStafffId()));
			personOplogService.addPrsnOplog(prsnOplog);
			
		} catch (Exception e) {
			logger.error(e);
		}
		
		return fmEboCopy;
	}

	// 添加随访结论记录
	@Transactional(value = "txManager", rollbackFor = { RuntimeException.class, Exception.class, DataException.class }, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, readOnly = true, timeout = 3)
	public FollowupMainEbo addOrModifyFollowupRes(FollowupResEto fuResEto) throws DataException {
		// 类型验证判断
		DataValUtil.dataValidation(fuResEto.getClass(), fuResEto);
		if (Util.isNull(fuResEto.getCode()))
			return null;
		FollowupMainEbo foEbo = getFollowupMainByIdOrCode(0, fuResEto.getCode());
		if (foEbo == null)
			return null;
		FollowupMainEbo foMainEbo = new FollowupMainEbo();
		BeanUtils.copyProperties(fuResEto, foMainEbo);
		followupDao.addOrModifyFollowupRes(foMainEbo);
		FollowupMainEbo foResEbo = getFollowupMainByIdOrCode(0, foMainEbo.getCode());
		if (foResEbo == null)
			return null;
		return foResEbo;
	}

	// 列表随访记录
	// lineData = true 返回随访表具体列数据 ，false 不返回列数据
	public List<FollowupMainDto> listFollowupMainDto(FollowupMainQto fupQto, int page, int paging) throws DataException {
		if (fupQto.getOrgId() > 0) {
			OrgEbo org = orgService.getOrgById(fupQto.getOrgId());
			if (org == null)
				throw new DataException("[err]该组织信息不存在");
		}
		// 随访记录
		List<FollowupMainInnerEbo> listFuEbo = followupDao.listFollowupMainInnerEbo(fupQto, page, paging);
		List<FollowupMainDto> listFuDto = new ArrayList<FollowupMainDto>();
		List<Integer> flpeIds = new ArrayList<Integer>();

		for (FollowupMainInnerEbo in : listFuEbo) {
			FollowupMainDto fu = new FollowupMainDto();
			BeanUtils.copyProperties(in, fu);
			listFuDto.add(fu);
			flpeIds.add(in.getId());
		}
		Map<Integer, List<Object>> examMap = examService.listExamObjs(flpeIds);
		for (FollowupMainDto fm : listFuDto) {
			if (examMap.containsKey(fm.getId()))
				fm.setExamList(examMap.get(fm.getId()));
		}
		// 对随访记录添加表格内具体数据
		if (fupQto.isLineData()) {
			for (FollowupMainDto fum : listFuDto) {// 获取随访具体表格数据
				List<TableDataLineDto> lines = new ArrayList<TableDataLineDto>();
				if (fum.getTabDataId() > 0)
					lines = tableService.listDataLine(fum.getTabDataId());
				if (lines.size() > 0)
					fum.setListDataLine(lines);
			}
		}
		return listFuDto;
	}

	// 随访记录总数
	public int getTotalFollowupMain(FollowupMainQto fupQto) throws DataException {
		if (fupQto.getOrgId() > 0) {
			OrgEbo org = orgService.getOrgById(fupQto.getOrgId());
			if (org == null)
				throw new DataException("[err]该组织信息不存在");
		}
		int count = followupDao.getTotalFollowupMain(fupQto);
		return count;
	}

	// 更新随访记录
	public void updateFollowupMain(FollowupMainEbo fMainEbo) throws DataException {
		if (fMainEbo != null) {
			int followupId = fMainEbo.getId();
			if (followupId != 0) {
				FollowupMainEbo fEbo = getFollowupMainByIdOrCode(followupId, null);
				if (fEbo != null) {

					int prsnId = fMainEbo.getPrsnId();
					if (prsnId != 0) {
						DocumentInfoEbo docEbo = docInfoService.getDocinfoByIdOrCode(prsnId, null);
						if (docEbo == null) {
							throw new DataException("[err]该用户没有建档");
						}
					}

					int staffId = fMainEbo.getStafffId();
					if (staffId != 0) {
						StaffEbo staffById = staffService.getStaffById(staffId);
						if (staffById == null) {
							throw new DataException("[err]该医生不存在");
						}
					}

					followupDao.updateFollowupMain(fMainEbo);
				} else {
					throw new DataException("[err]没有该随访记录");
				}
			}
		} else {
			return;
		}
	}
}
