package com.kjplus.service.impl;

import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.ybk.basic.util.Util;

import com.kjplus.constant.Constant;
import com.kjplus.dao.IAppointDao;
import com.kjplus.eto.*;
import com.kjplus.model.*;
import com.kjplus.model.inner.AppointInfoInnerEbo;
import com.kjplus.service.*;
import com.kjplus.util.DataValUtil;
import com.mq.util.DateUtil;
import com.ybk.exception.DataException;

@Service("appiontService")
public class AppiontService implements IAppointService {

	private static Logger logger = Logger.getLogger(CalendarMainService.class);
	@Autowired
	private IAppointDao appointDao;
	@Autowired
	private ICalendarMainService calMainService;
	@Autowired
	private IOrgService orgService;
	@Autowired
	private IDeptService deptService;
	@Autowired
	private IStaffService staffService;
	@Autowired
	private IDocInfoService docInfoService;

	public AppointEbo getAppointByIdOrCode(int id, String code) throws DataException {
		Boolean isNull = id <= 0 && Util.isNull(code) ? true : false;
		if (isNull)
			return null;
		return appointDao.getAppointByIdOrCode(id, code);
	}

	@Transactional(value = "txManager", rollbackFor = { RuntimeException.class, Exception.class, DataException.class }, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, readOnly = true, timeout = 3)
	public AppointEbo addAppoint(AppointEto appoint) throws DataException {
		// 空值判断
		DataValUtil.dataValidation(appoint.getClass(), appoint);

		OrgEbo org = orgService.getOrgById(appoint.getOrgId());
		if (org == null)
			throw new DataException("系统无此组织，编号为：" + appoint.getOrgId());
		// 通过mainType 确定预约类型
		if (Constant.APPIONT_TYPE_DEPT.equals(appoint.getMainType())) {// 预约类型为部门
			DepartmentEbo dept = deptService.getDepartmentById(appoint.getMainId());
			if (dept == null)
				throw new DataException("系统无此预约对象,编号为" + appoint.getMainId());
		} else if (Constant.APPIONT_TYPE_ORG.equals(appoint.getMainType())) {// 预约类型为组织
			OrgEbo org1 = orgService.getOrgById(appoint.getMainId());
			if (org1 == null)
				throw new DataException("系统无此预约对象,编号为" + appoint.getMainId());
		} else if (Constant.APPIONT_TYPE_STAFF.equals(appoint.getMainType())) {// 预约类型为医生
			StaffEbo staff = staffService.getStaffById(appoint.getMainId());
			if (staff == null)
				throw new DataException("系统无此预约对象,编号为" + appoint.getMainId());
		} else
			throw new DataException("被预约类型有误,mainType = " + appoint.getMainType());

		// 预约人
		DocumentInfoEbo docInfo = docInfoService.getDocinfoByIdOrCode(appoint.getPrsnId(), null);
		if (docInfo == null)
			throw new DataException("档案无此用户，档案ID为：" + appoint.getPrsnId());

		CalendarInfoEbo calInfo = null;
		if (appoint.getCalInfoId() == null || appoint.getCalInfoId().intValue() == 0) {// 用户主动预约医生
																						// 日历中无该服务
			// CalendarEntryEbo calEntry =
			// calMainService.getCalEntryByMainIdOrType(appoint.getMainId(),
			// appoint.getMainType());
			List<CalendarEntryEbo> listCal = calMainService.listCalEntryByMainIdOrType(appoint.getMainId(), appoint.getMainType(), Constant.ENTRY_TYPE_WORK,
					Constant.FLAG_YES, 0, -1);
			if (listCal.size() == 0)
				throw new DataException("该用户没有开通主动预约项目！");
			CalendarEntryEbo calEntry = listCal.get(0);
			// 不为空代表，可以主动预约该用户
			if (appoint.getAppMemo() == null || appoint.getAppointTime() == null || appoint.getAppointTime().intValue() <= 0)
				throw new DataException("主动预约时,预约描述和预约时间均不能为空！");
			CalendarInfoEto calInfo2 = new CalendarInfoEto();
			calInfo2.setCalEntryId(calEntry.getId());
			// 预约描述
			calInfo2.setStartTime(appoint.getAppointTime());
			// 默认预约结束时间 为半个小时后
			int endTime = appoint.getAppointTime() + 60 * 30;
			calInfo2.setEndTime(endTime);
			if (appoint.getAppointTitle() == null)
				calInfo2.setCalTitle(appoint.getAppMemo());
			else
				calInfo2.setCalTitle(appoint.getAppointTitle());
			calInfo2.setCalMemo(appoint.getAppMemo());
			calInfo2.setSourceType(appoint.getAppointSourceType());
			calInfo = calMainService.addCalInfo(calInfo2);
			appoint.setCalInfoId(calInfo.getId());
		} else {
			calInfo = calMainService.getCalInfoByIdOrCode(appoint.getCalInfoId(), null);
			if (calInfo == null)
				throw new DataException("系统无此活动，编号为：" + appoint.getCalInfoId());
			// 对于 W的默认日历无人数限制 修改 日历中 加入人数
			calMainService.updateCalInfo(0, calInfo.getCode(), null, null, 0, 1, null, 0, 0);
		}

		// 自动生成code
		String code = Util.genDigiCodeStr(AppointEto.CODE_LEN);
		AppointEbo app = getAppointByIdOrCode(0, code);
		while (app != null) {
			code = Util.genDigiCodeStr(AppointEto.CODE_LEN);
			app = getAppointByIdOrCode(0, code);
		}
		AppointEbo appointEbo = null;
		try {
			// 添加预约记录
			appointEbo = new AppointEbo();
			BeanUtils.copyProperties(appoint, appointEbo);
			appointEbo.setStartTime(calInfo.getStartTime());
			appointEbo.setEndTime(calInfo.getEndTime());
			appointEbo.setCode(code);
			appointDao.addAppoint(appointEbo);
		} catch (DataException e) {
			logger.error(e.getMessage());
			throw new DataException(e.getMessage());
		}
		return appointEbo;
	}

	public List<AppointInfoInnerEbo> listAppointInfo(int calId, int prsnId, int orgId, int mainId, String mainType, String staffName, int startTime,
			int endTime, String status, int typeId, int appTypeId, int page, int paging) throws DataException {
		List<Integer> prsnIds = new ArrayList<Integer>();
		if (prsnId > 0)
			prsnIds.add(prsnId);
		return listAppointInfo(calId, prsnIds, orgId, mainId, mainType, staffName, startTime, endTime, status, typeId, appTypeId, page, paging);
	}

	public int getTotalAppoint(int calId, int prsnId, int orgId, int mainId, String mainType, String staffName, int startTime, int endTime, String status,
			int typeId, int appTypeId) throws DataException {
		List<Integer> prsnIds = new ArrayList<Integer>();
		if (prsnId > 0)
			prsnIds.add(prsnId);
		return getTotalAppoint(calId, prsnIds, orgId, mainId, mainType, staffName, startTime, endTime, status, typeId, appTypeId);
	}

	public List<AppointInfoInnerEbo> listAppointInfo(int calId, List<Integer> prsnIds, int orgId, int mainId, String mainType, String staffName, int startTime,
			int endTime, String status, int typeId, int appTypeId, int page, int paging) throws DataException {

		String prsnIdStr = Util.arr2Str(prsnIds);
		List<AppointInfoInnerEbo> listAppoint = appointDao.listAppointInfo(calId, prsnIdStr, orgId, mainId, mainType, staffName, startTime, endTime, status,
				typeId, appTypeId, page, paging);
		return listAppoint;
	}

	public int getTotalAppoint(int calId, List<Integer> prsnIds, int orgId, int mainId, String mainType, String staffName, int startTime, int endTime,
			String status, int typeId, int appTypeId) throws DataException {

		String prsnIdStr = Util.arr2Str(prsnIds);
		int tatal = appointDao.getTotalAppoint(calId, prsnIdStr, orgId, mainId, mainType, staffName, startTime, endTime, status, typeId, appTypeId);
		return tatal;
	}

	public void updateAppointStatus(int id, String code, String status, String memo) throws DataException {
		boolean isNull = (id <= 0 && Util.isNull(code)) || Util.isNull(status) ? true : false;
		if (isNull)
			return;
		AppointEbo appoint = getAppointByIdOrCode(id, code);
		if (status.equals(appoint.getStatus()))
			return;
		// 当预约记录是有效的或是确认的 在 取消预约后，将日历中预约人数进行修改
		String status2 = appoint.getStatus();
		boolean isStatus = status2.equals(Constant.APPOINT_STATUS_CONFIRM) || status2.equals(Constant.APPOINT_STATUS_APPLY) ? true : false;
		if (isStatus) {
			CalendarInfoEbo cal = null;
			if (appoint.getCalInfoId() != null && appoint.getCalInfoId().intValue() != 0)
				cal = calMainService.getCalInfoByIdOrCode(appoint.getCalInfoId(), null);
			if (cal != null && Constant.APPOINT_STATUS_REVOKE.equals(status))
				calMainService.updateCalInfo(cal.getId(), cal.getCode(), null, null, 0, -1, null, 0, 0);
		}
		appointDao.updateAppointStatus(id, code, status, memo);
	}

	public void updateAppointEbo(AppointEbo appointEbo) throws DataException {
		if (appointEbo == null)
			return;
		boolean isNull = (Util.val(appointEbo.getId()) <= 0 && Util.isNull(Util.val(appointEbo.getCode()))) ? true : false;
		if (isNull)
			return;
		if (appointEbo.getCalInfoId() != null && appointEbo.getCalInfoId().intValue() > 0) {
			CalendarInfoEbo calInfo = calMainService.getCalInfoByIdOrCode(appointEbo.getCalInfoId(), null);
			if (calInfo == null)
				return;
			appointEbo.setStartTime(calInfo.getStartTime());
			appointEbo.setEndTime(appointEbo.getEndTime());
		}
		if (appointEbo.getPrsnId() != null && appointEbo.getPrsnId().intValue() > 0) {
			DocumentInfoEbo docInfo = docInfoService.getDocinfoByIdOrCode(appointEbo.getPrsnId(), null);
			if (docInfo == null)
				return;
		}
		boolean isNull2 = Util.isNotNull(appointEbo.getMainType()) && appointEbo.getMainId() != null && appointEbo.getMainId().intValue() > 0 ? true : false;
		if (isNull2) {
			// 通过mainType 确定预约类型
			if (Constant.APPIONT_TYPE_DEPT.equals(appointEbo.getMainType())) {// 预约类型为部门
				DepartmentEbo dept = deptService.getDepartmentById(appointEbo.getMainId());
				if (dept == null)
					return;
			} else if (Constant.APPIONT_TYPE_ORG.equals(appointEbo.getMainType())) {// 预约类型为组织
				OrgEbo org1 = orgService.getOrgById(appointEbo.getMainId());
				if (org1 == null)
					return;
			} else if (Constant.APPIONT_TYPE_STAFF.equals(appointEbo.getMainType())) {// 预约类型为医生
				StaffEbo staff = staffService.getStaffById(appointEbo.getMainId());
				if (staff == null)
					return;
			} else
				return;
		}
		appointEbo.setCreateTime(DateUtil.getCurDayBeginInSec());

		// 当修改的是状态是 日历信息表人数得修改
		// 当预约记录是有效的或是确认的 在 取消预约后，将日历中预约人数进行修改
		AppointEbo appointEbo2 = getAppointByIdOrCode(Util.val(appointEbo.getId()), Util.val(appointEbo.getCode()));
		String status2 = appointEbo2.getStatus();
		boolean isStatus = status2.equals(Constant.APPOINT_STATUS_CONFIRM) || status2.equals(Constant.APPOINT_STATUS_APPLY) ? true : false;
		if (isStatus) {
			CalendarInfoEbo cal = null;
			if (appointEbo.getCalInfoId() != null && appointEbo.getCalInfoId().intValue() != 0)
				cal = calMainService.getCalInfoByIdOrCode(appointEbo.getCalInfoId(), null);
			if (cal != null && Constant.APPOINT_STATUS_REVOKE.equals(appointEbo.getStatus()))
				calMainService.updateCalInfo(cal.getId(), cal.getCode(), null, null, 0, -1, null, 0, 0);
		}
		appointDao.updateAppointEbo(appointEbo);
	}
}
