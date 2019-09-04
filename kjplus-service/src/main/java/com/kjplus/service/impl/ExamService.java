package com.kjplus.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.ybk.basic.util.DateUtil;
import org.ybk.basic.util.Util;

import com.kjplus.constant.Constant;
import com.kjplus.dao.IExamDao;
import com.kjplus.dto.ExamBloodDto;
import com.kjplus.dto.ExamGlycemicDto;
import com.kjplus.dto.ExamMainSimpleDto;
import com.kjplus.dto.ExamUrineDto;
import com.kjplus.eto.ExamBloodEto;
import com.kjplus.eto.ExamGlycemicEto;
import com.kjplus.eto.ExamMainEto;
import com.kjplus.eto.ExamUrineEto;
import com.kjplus.eto.PersonOplogEto;
import com.kjplus.model.DocumentInfoEbo;
import com.kjplus.model.ExamBloodEbo;
import com.kjplus.model.ExamGlycemicEbo;
import com.kjplus.model.ExamMainEbo;
import com.kjplus.model.ExamUrineEbo;
import com.kjplus.model.OrgEbo;
import com.kjplus.model.StaffEbo;
import com.kjplus.model.SysRefValueEbo;
import com.kjplus.model.inner.ExamMainInnerEbo;
import com.kjplus.qto.ExamQto;
import com.kjplus.service.IDocInfoService;
import com.kjplus.service.IExamService;
import com.kjplus.service.IOrgService;
import com.kjplus.service.IPersonOplogService;
import com.kjplus.service.IStaffService;
import com.kjplus.service.ISysBaseService;
import com.kjplus.service.IUserMapService;
import com.kjplus.util.DataValUtil;
import com.ybk.exception.DataException;

@Service("examService")
public class ExamService implements IExamService {

	@Autowired
	private IExamDao examDao;
	@Autowired
	private IDocInfoService docInfoService;
	@Autowired
	private IStaffService staffService;
	@Autowired
	private IOrgService orgService;
	@Autowired
	private ISysBaseService sysBaseService;
	@Autowired
	private IPersonOplogService personOplogService;
	@Autowired
	private IUserMapService userMapService;

	// 通过id，exmainId获取ExamBloodEbo 对象
	public ExamBloodEbo getExamBloodEboByIdOrMainId(int id, int exmainId) throws DataException {
		boolean isNUll = id < 0 || exmainId < 0 ? true : false;
		if (isNUll)
			return null;
		return examDao.getExamBloodEboByIdOrMainId(id, exmainId);
	}

	@Transactional(value = "txManager", rollbackFor = { RuntimeException.class, Exception.class, DataException.class }, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, readOnly = true, timeout = 3)
	public ExamMainInnerEbo addExamBloodEbo(ExamBloodEto examBlood) throws DataException {
		// 类型验证判断
		DataValUtil.dataValidation(examBlood.getClass(), examBlood);
		DocumentInfoEbo docInfo = docInfoService.getDocinfoByIdOrCode(examBlood.getPrsnId(), null);
		if (docInfo == null)
			throw new DataException("[err]该测试人档案不存在，prsnId = " + examBlood.getPrsnId());
		if (examBlood.getOrgId() <= 0)
			examBlood.setOrgId(docInfo.getOrgId());

		if (examBlood.getStaffId() > 0) {
			StaffEbo staff = staffService.getStaffById(examBlood.getStaffId());
			if (staff == null)
				throw new DataException("[err]该医生信息不存在");
		}
		if (examBlood.getExamTypeId() > 0) {
			SysRefValueEbo refValue = sysBaseService.getRefVlById(examBlood.getExamTypeId());
			if (refValue == null)
				throw new DataException("[err]该测试参照不存在");
		}
		// 时间的空值判断
		if (examBlood.getExamDay() == null) {
			examBlood.setExamDay(DateUtil.newTime());
		}
		ExamMainEbo em = new ExamMainEbo();
		boolean isNull = Util.isNull(examBlood.getExamCode()) ? true : false;
		String code = "";
		if (isNull) {
			code = Util.genDigiCodeStr(ExamMainEto.MAX_CODE_LEG);
			ExamMainEbo em2 = getExamMainEboByIdOrCode(0, code);
			while (em2 != null) {
				code = Util.genDigiCodeStr(16);
				em2 = getExamMainEboByIdOrCode(0, code);
			}
			examBlood.setExamCode(code);
			BeanUtils.copyProperties(examBlood, em);
			addExamMainEbo(em);
		} else {
			ExamMainEbo em3 = getExamMainEboByIdOrCode(0, examBlood.getExamCode());
			if (em3 != null)
				BeanUtils.copyProperties(em3, em);
			else
				throw new DataException("[err]没有该测试主表，code=" + examBlood.getExamCode());
		}
		ExamBloodEbo eb = getExamBloodEboByIdOrMainId(0, em.getId());
		if (eb != null)
			throw new DataException("[err]血压测试不可重复添加");
		// 添加血压
		ExamBloodEbo eb2 = new ExamBloodEbo();
		eb2.setExmainId(em.getId());
		// TODO 血压等数值范围
		eb2.setDiastolePress(examBlood.getDiastolePress());
		eb2.setShrinkPress(examBlood.getShrinkPress());
		eb2.setHeartRate(examBlood.getHeartRate());
		examDao.addExamBloodEbo(eb2);
		// 再次查询
		ExamMainInnerEbo emInner = getExamMainInnerEbo(em.getId(), em.getFlpeId());
		return emInner;
	}

	// 通过id，exmainId获取ExamGlycemicEbo 对象
	public ExamGlycemicEbo getExamGlycemicEboByIdOrMainId(int id, int exmainId) throws DataException {
		boolean isNUll = id < 0 || exmainId < 0 ? true : false;
		if (isNUll)
			return null;
		ExamGlycemicEbo glycemicEbo = examDao.getExamGlycemicEboByIdOrMainId(id, exmainId);
		return glycemicEbo;
	}

	// 添加血糖和主表 加入事务
	@Transactional(value = "txManager", rollbackFor = { RuntimeException.class, Exception.class, DataException.class }, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, readOnly = true, timeout = 3)
	public ExamMainInnerEbo addExamGlycemicEbo(ExamGlycemicEto examGlycemic) throws DataException {
		// 类型验证判断
		DataValUtil.dataValidation(examGlycemic.getClass(), examGlycemic);
		if (examGlycemic.getPrsnId() == null || examGlycemic.getPrsnId().intValue() <= 0)
			throw new DataException("[err]被测试人不能为空");
		if (examGlycemic.getMeasureStatus() == null || examGlycemic.getMeasureStatus() == "")
			examGlycemic.setMeasureStatus("C");// 默认空腹
		if (examGlycemic.getFlpeId() > 0) {
			// TODO 查询随访表 看是否存在
		}
		DocumentInfoEbo docInfo = docInfoService.getDocinfoByIdOrCode(examGlycemic.getPrsnId(), null);
		if (docInfo == null)
			throw new DataException("[err]该测试人档案不存在，prsnId = " + examGlycemic.getPrsnId());
		if (examGlycemic.getOrgId() <= 0)
			examGlycemic.setOrgId(docInfo.getOrgId());
		if (examGlycemic.getStaffId() > 0) {
			StaffEbo staff = staffService.getStaffById(examGlycemic.getStaffId());
			if (staff == null)
				throw new DataException("[err]该医生信息不存在");
		}
		if (examGlycemic.getExamTypeId() > 0) {
			SysRefValueEbo refValue = sysBaseService.getRefVlById(examGlycemic.getExamTypeId());
			if (refValue == null)
				throw new DataException("[err]该测试参照不存在");
		}
		// 时间空值判断
		if (examGlycemic.getExamDay() == null) {
			examGlycemic.setExamDay(DateUtil.newTime());
		}
		ExamMainEbo em = new ExamMainEbo();
		boolean isNull = Util.isNull(examGlycemic.getExamCode()) ? true : false;
		String code = "";
		if (isNull) {// 若code为空
			code = Util.genDigiCodeStr(ExamMainEto.MAX_CODE_LEG);
			ExamMainEbo em2 = getExamMainEboByIdOrCode(0, code);
			while (em2 != null) {
				code = Util.genDigiCodeStr(16);
				em2 = getExamMainEboByIdOrCode(0, code);
			}
			examGlycemic.setExamCode(code);
			BeanUtils.copyProperties(examGlycemic, em);
			addExamMainEbo(em);
		} else {// 主表存在则不进行添加,表示给该主表添加数据
			ExamMainEbo em3 = getExamMainEboByIdOrCode(0, examGlycemic.getExamCode());
			if (em3 != null)
				BeanUtils.copyProperties(em3, em);
			else
				throw new DataException("[err]没有该测试主表，code=" + examGlycemic.getExamCode());
		}
		// 判断血糖测试状态是否正确
		boolean isNull2 = "K".equals(examGlycemic.getMeasureStatus()) || "C".equals(examGlycemic.getMeasureStatus()) ? false : true;
		if (isNull2)
			throw new DataException("[err]测试血糖状态不状态要求，K空腹/C餐后");
		// 不可重复添加
		ExamGlycemicEbo eg = getExamGlycemicEboByIdOrMainId(0, em.getId());
		if (eg != null)
			throw new DataException("[err]血糖测试不可重复添加");
		ExamGlycemicEbo eg2 = new ExamGlycemicEbo();
		eg2.setExmainId(em.getId());
		eg2.setGlycemicVal(examGlycemic.getGlycemicVal());
		eg2.setMeasureStatus(examGlycemic.getMeasureStatus());
		examDao.addExamGlycemicEbo(eg2);

		ExamMainInnerEbo emInner = getExamMainInnerEbo(em.getId(), em.getFlpeId());
		return emInner;
	}

	// 通过id，exmainId获取ExamMainEbo 对象
	public ExamMainEbo getExamMainEboByIdOrCode(int id, String code) throws DataException {
		boolean isNUll = id <= 0 && Util.isNull(code) ? true : false;
		if (isNUll)
			return null;
		return examDao.getExamMainEboByIdOrCode(id, code);
	}

	public ExamMainInnerEbo getExamMainInnerEbo(int exmainId, int flpeId) throws DataException {
		boolean isNull = exmainId < 0 && flpeId < 0 ? true : false;
		if (isNull)
			return null;
		return examDao.getExamMainInnerEbo(exmainId, flpeId);
	}

	// 列表测试记录
	public List<ExamMainSimpleDto> listExamMainInnerEbo(ExamQto examQto, int page, int paging) throws DataException {
		if (examQto.getOrgId() > 0) {
			OrgEbo org = orgService.getOrgById(examQto.getOrgId());
			if (org == null)
				throw new DataException("[err]该组织信息不存在");
		}
		SysRefValueEbo refType = sysBaseService.getRefVlByCode(examQto.getExamTypeCode());
		int examTypeId = 0;
		if (refType != null)
			examTypeId = refType.getId();
		examQto.setExamTypeId(examTypeId);

		List<ExamMainInnerEbo> listEm = examDao.listExamMainInnerEbo(examQto, page, paging);
		// 尿检数据处理
		List<ExamMainInnerEbo> listEmInner = new ArrayList<ExamMainInnerEbo>();
		Map<Integer, List<ExamUrineDto>> urineMap = new HashMap<Integer, List<ExamUrineDto>>();
		List<ExamUrineDto> listUrine = null;
		for (ExamMainInnerEbo e : listEm) {
			if (Constant.TP_EAXM_URINE.equals(e.getExamTypeCode())) {// 尿检数据整合

				ExamUrineDto urine = new ExamUrineDto();
				BeanUtils.copyProperties(e, urine);

				if (urineMap.containsKey(e.getId())) {
					listUrine = urineMap.get(e.getId());
					listUrine.add(urine);
				} else {
					listUrine = new ArrayList<ExamUrineDto>();
					listUrine.add(urine);
					ExamMainInnerEbo ex = new ExamMainInnerEbo();
					BeanUtils.copyProperties(e, ex);
					ex.setListUrine(listUrine);
					listEmInner.add(ex);
					urineMap.put(e.getId(), listUrine);
				}
			} else
				// 血糖、血压数据不做处理
				listEmInner.add(e);
		}

		List<ExamMainSimpleDto> exams = new ArrayList<ExamMainSimpleDto>();
		for (ExamMainInnerEbo e : listEmInner) {
			ExamMainSimpleDto se = new ExamMainSimpleDto();
			BeanUtils.copyProperties(e, se);
			String digest = "";
			SysRefValueEbo refType2 = sysBaseService.getRefVlByCode(e.getExamTypeCode());
			if (refType2 != null)
				e.setRefTypeName(refType2.getName());
			if (Constant.TP_EAXM_BLOOD.equals(e.getExamTypeCode())) {// 血压测试
				se.setRefCode(e.getExamTypeCode());
				digest = "收缩压:" + e.getShrinkPress() + "," + "舒张压：" + e.getDiastolePress() + "心律：" + e.getHeartRate();
				ExamBloodDto blood = new ExamBloodDto();
				blood.setMainId(e.getId());
				blood.setExamCode(e.getExamCode());
				blood.setExamTypeId(e.getExamTypeId());
				blood.setShrinkPress(e.getShrinkPress());
				blood.setDiastolePress(e.getDiastolePress());
				blood.setHeartRate(e.getHeartRate());
				se.setExamObjDto(blood);
			} else if (Constant.TP_EAXM_GLYCEMIC.equals(e.getExamTypeCode())) {// 血糖测试
				se.setRefCode(e.getExamTypeCode());
				String status = "";
				if ("K".equals(e.getMeasureStatus())) {
					status = "空腹";
				} else if ("C".equals(e.getMeasureStatus())) {
					status = "餐后";
				}
				digest = "血糖：" + e.getGlycemicVal() + "测试状况：" + status;
				ExamGlycemicDto gly = new ExamGlycemicDto();
				gly.setMainId(e.getId());
				gly.setExamCode(e.getExamCode());
				gly.setExamTypeId(e.getExamTypeId());
				gly.setGlycemicVal(e.getGlycemicVal());
				gly.setMeasureStatus(e.getMeasureStatus());
				se.setExamObjDto(gly);
			} else if ("RV_EXAM_URINE".equals(e.getExamTypeCode())) {// 尿检测试
				List<ExamUrineDto> listUrine2 = e.getListUrine();
				StringBuffer di = new StringBuffer();
				for (ExamUrineDto u : listUrine2) {
					String d = u.getUrineTypeName() + ":" + u.getUrineVl() + ";";
					di.append(d);
				}
				digest = di.toString();
				se.setExamObjDto(listUrine2);
			}
			se.setExamDay(DateUtil.formatDate(e.getExamDay()));
			se.setDigest(digest);
			exams.add(se);
		}
		return exams;
	}

	public int gettotalExamMain(String refTypeCode, int prsnId, int staffId, int orgId, String firstDay, String lastDay) throws DataException {
		if (orgId > 0) {
			OrgEbo org = orgService.getOrgById(orgId);
			if (org == null)
				throw new DataException("[err]该组织信息不存在");
		}
		int examTypeId = 0;
		SysRefValueEbo refType = sysBaseService.getRefVlByCode(refTypeCode);
		if (refType != null)
			examTypeId = refType.getId();
		int total = examDao.gettotalExamMain(examTypeId, prsnId, staffId, orgId, firstDay, lastDay);
		return total;
	}

	// 根据随访id获得对应的检查记录
	public Map<Integer, List<Object>> listExamObjs(List<Integer> flpeIds) throws DataException {
		if (flpeIds == null || flpeIds.size() <= 0)
			return null;
		Map<Integer, List<Object>> examMap = new HashMap<Integer, List<Object>>();
		// 尿检数据
		Map<Integer, List<ExamUrineDto>> urineMap = new HashMap<Integer, List<ExamUrineDto>>();

		List<ExamMainInnerEbo> examInnerList = examDao.listExamMainInnerEboByFlpeId(Util.arr2Str(flpeIds));
		List<Object> examList = null;
		// 尿检数据
		List<ExamUrineDto> listUrine = null;
		for (ExamMainInnerEbo ei : examInnerList) {
			// 区别随访
			if (examMap.containsKey(ei.getFlpeId()))
				examList = examMap.get(ei.getFlpeId());
			else {
				examList = new ArrayList<Object>();
				examMap.put(ei.getFlpeId(), examList);
			}
			if (ei.getExamTypeCode().equals(Constant.TP_EAXM_BLOOD)) {
				ExamBloodDto bd = new ExamBloodDto();
				BeanUtils.copyProperties(ei, bd);
				examList.add(bd);
			} else if (ei.getExamTypeCode().equals(Constant.TP_EAXM_GLYCEMIC)) {
				ExamGlycemicDto gd = new ExamGlycemicDto();
				BeanUtils.copyProperties(ei, gd);
				examList.add(gd);
			} else if (ei.getExamTypeCode().equals(Constant.TP_EAXM_URINE)) {// 尿检数据
				if (urineMap.containsKey(ei.getFlpeId()))
					listUrine = urineMap.get(ei.getFlpeId());
				else {
					listUrine = new ArrayList<ExamUrineDto>();
					urineMap.put(ei.getFlpeId(), listUrine);
					// 新建时，保存尿检数据
					examList.add(listUrine);
				}
				ExamUrineDto ur = new ExamUrineDto();
				BeanUtils.copyProperties(ei, ur);
				listUrine.add(ur);
			}
		}
		return examMap;
	}

	public ExamUrineEbo getExamUrineEboById(int id) throws DataException {
		boolean isNull = id <= 0 ? true : false;
		if (isNull)
			return null;
		return examDao.getExamUrineEboById(id);
	}

	public void addExamUrine(List<ExamUrineEto> listUrine) throws DataException {
		for (ExamUrineEto urine : listUrine) {
			DataValUtil.dataValidation(urine.getClass(), urine);
		}
		// 用于存储不同人的不同main_id
		Map<Integer, Integer> ids = new HashMap<Integer, Integer>();
		ExamUrineEto ur = null;
		for (int i = 0; i < listUrine.size(); i++) {
			// 检查主表是否存在
			ur = listUrine.get(i);
			// 判断档案是否存在
			DocumentInfoEbo docInfo = docInfoService.getDocinfoByIdOrCode(ur.getPrsnId(), null);
			if (docInfo == null)
				throw new DataException("[err]该测试人档案不存在，prsnId = " + ur.getPrsnId());
			// 判断组织
			if (ur.getOrgId() <= 0)
				ur.setOrgId(docInfo.getOrgId());
			// 判断医生
			if (ur.getStaffId() > 0) {
				StaffEbo staff = staffService.getStaffById(ur.getStaffId());
				if (staff == null)
					throw new DataException("[err]该医生信息不存在");
			}
			// 判断类型
			if (ur.getExamTypeId() > 0) {
				SysRefValueEbo refValue = sysBaseService.getRefVlById(ur.getExamTypeId());
				if (refValue == null)
					throw new DataException("[err]该测试参照不存在");
			}
			boolean isNull = Util.isNull(ur.getExamCode()) ? true : false;
			ExamMainEbo em2 = new ExamMainEbo();
			String code = "";
			if (isNull && (!ids.containsKey(ur.getPrsnId()))) {// 主表不存在就添加一张主表，同时同一个档案用户并未建立主表
				code = Util.genDigiCodeStr(ExamMainEto.MAX_CODE_LEG);
				ExamMainEbo em = getExamMainEboByIdOrCode(0, code);
				while (em != null) {
					code = Util.genDigiCodeStr(16);
					em = getExamMainEboByIdOrCode(0, code);
				}
				ur.setExamCode(code);
				BeanUtils.copyProperties(ur, em2);
				ExamMainEbo main = addExamMainEbo(em2);
				if (main != null)
					ids.put(ur.getPrsnId(), main.getId());
			} else if(!ids.containsKey(ur.getPrsnId())){
				ExamMainEbo main = getExamMainEboByIdOrCode(0, ur.getExamCode());
				if (main == null)
					throw new DataException("[err]测量主表不存在！");
				ids.put(ur.getPrsnId(), main.getId());
			}
		}
		//添加尿检查记录
		List<ExamUrineEbo> listUrineEbo = new ArrayList<ExamUrineEbo>();
		for (ExamUrineEto urine : listUrine) {
			SysRefValueEbo refVl = sysBaseService.getRefVlById(urine.getUrineTypeId());
			if (refVl == null)
				throw new DataException("[err]该尿检数据类型不存在！");
			ExamUrineEbo u = new ExamUrineEbo();
			BeanUtils.copyProperties(urine, u);
			u.setExmainId(ids.get(urine.getPrsnId()));
			listUrineEbo.add(u);
		}
		examDao.addExamUrineEbo(listUrineEbo);
	}

	public ExamMainEbo addExamMainEbo(ExamMainEbo em) throws DataException {
		if (em == null)
			return null;
		examDao.addExamMainEbo(em);
		//添加档案操作记录
		PersonOplogEto prsnOplog = new PersonOplogEto();
		prsnOplog.setPrsnId(em.getPrsnId());
		prsnOplog.setOpTypeCode(Constant.RV_DOC_UPDATE);
		prsnOplog.setOrgid(em.getOrgId());
		prsnOplog.setUid(userMapService.getUserIdByStaffId(em.getStaffId()));
		personOplogService.addPrsnOplog(prsnOplog);
		
		ExamMainEbo main = getExamMainEboByIdOrCode(0, em.getExamCode());
		return main;
	}

}
