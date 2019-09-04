package com.kjplus.service.impl;

import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ybk.basic.util.Util;

import com.kjplus.constant.Constant;
import com.kjplus.dao.ICalendarMainDao;
import com.kjplus.dto.*;
import com.kjplus.eto.*;
import com.kjplus.model.*;
import com.kjplus.model.inner.CalEntryInfoInnerEbo;
import com.kjplus.qto.CalEntryInfoQto;
import com.kjplus.service.*;
import com.kjplus.util.DataValUtil;
import com.mq.util.DateUtil;
import com.ybk.exception.DataException;

@Service("calMainService")
public class CalendarMainService implements ICalendarMainService {

	private static Logger logger = Logger.getLogger(CalendarMainService.class);
	@Autowired
	private ICalendarMainDao calMainDao;
	@Autowired
	private ISysBaseService baseService;
	@Autowired
	private IOrgService orgService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IDeptService deptService;
	@Autowired
	private IStaffService staffService;
	@Autowired
	private IAdminUserService adminUserService;

	public CalendarEntryEbo getCalEntryByIdOrCode(int id, String code) throws DataException {
		boolean isNull = id <= 0 && Util.isNull(code) ? true : false;
		if (isNull)
			return null;
		return calMainDao.getCalEntryByIdOrCode(id, code);
	}

	public List<CalendarEntryEbo> listCalEntryByMainIdOrType(int mainId, String mainType, String entryType,
			String isDefault, int page, int paging) throws DataException{
		return calMainDao.listCalEntryByMainIdOrType(mainId, mainType, entryType, isDefault, page, paging);
	}

	

	public CalendarInfoEbo getCalInfoByIdOrCode(int id, String code) throws DataException {
		boolean isNull = id <= 0 && Util.isNull(code) ? true : false;
		if (isNull)
			return null;
		return calMainDao.getCalInfoByIdOrCode(id, code);
	}

	public CalendarEntryEbo addCalEntry(CalendarEntryEto calEntry,boolean notIsDefault) throws DataException {
		// 做空值验证
		DataValUtil.dataValidation(calEntry.getClass(), calEntry);

		OrgEbo org = orgService.getOrgById(calEntry.getOrgId());
		if (org == null) {// 判断组织是否存在
			throw new DataException("系统无此组织,组织编号为：" + calEntry.getOrgId());
		}

		SysRefValueEbo refVl = baseService.getRefVlById(calEntry.getCalTypeId());
		if (refVl == null)
			throw new DataException("系统无此参照,参照Id为：" + calEntry.getCalTypeId());

		if (Constant.CREATE_TYPE_ADMINUSER.equals(calEntry.getMainType())) {
			AdminUserEbo adminUser = adminUserService.getUserByUid(calEntry.getMainId());
			if (adminUser == null)
				throw new DataException("系统无此管理员，ID为:" + calEntry.getMainId());
		} else if (Constant.CREATE_TYPE_USER.equals(calEntry.getMainType())) {
			UserEbo user = userService.getUserById(calEntry.getMainId());
			if (user == null)
				throw new DataException("系统无此用户，id为:" + calEntry.getMainId());
		} else if (Constant.CREATE_TYPE_DEPT.equals(calEntry.getMainType())) {
			DepartmentEbo dept = deptService.getDepartmentById(calEntry.getMainId());
			if (dept == null)
				throw new DataException("系统无此部门，部门Id为：" + calEntry.getMainId());
		} else if (Constant.CREATE_TYPE_ORG.equals(calEntry.getMainType())) {
			OrgEbo org1 = orgService.getOrgById(calEntry.getMainId());
			if (org1 == null)
				throw new DataException("系统不存在组织Id为" + calEntry.getMainId());
		} else if (Constant.CREATE_TYPE_STAFF.equals(calEntry.getMainType())) {
			StaffEbo staff = staffService.getStaffById(calEntry.getMainId());
			if (staff == null)
				throw new DataException("系统无此医生，医生Id为：" + calEntry.getMainId());
		}
		if(Util.isNotNull(calEntry.getIsDefault()) && calEntry.getIsDefault().equals(Constant.FLAG_YES) ){//添加日历是否是默认类型
			List<CalendarEntryEbo> listCal = listCalEntryByMainIdOrType(calEntry.getMainId(), calEntry.getMainType(),calEntry.getEntryType(), calEntry.getIsDefault(), 0, 1);
			if (listCal != null && !notIsDefault)//有记录  同时允许建立该条默认日历
				//修改原有日历的默认类型    再进行添加
				updateCalEntryIsdefault(calEntry.getMainId(), calEntry.getMainType(),calEntry.getEntryType(), Constant.FLAG_NO);
			else
				return listCal.get(0);
		}
		
		String code = Util.genDigiCodeStr(CalendarEntryEto.CODE_LEN);
		CalendarEntryEbo entry = getCalEntryByIdOrCode(0, code);
		while (entry != null) {
			code = Util.genDigiCodeStr(CalendarEntryEto.CODE_LEN);
			entry = getCalEntryByIdOrCode(0, code);
		}

		CalendarEntryEbo cal = new CalendarEntryEbo();
		try {
			BeanUtils.copyProperties(calEntry, cal);
			cal.setCode(code);
			calMainDao.addCalEntry(cal);
			return cal;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return cal;
	}

	public CalendarInfoEbo addCalInfo(CalendarInfoEto calInfo) throws DataException {
		// 做空值验证
		DataValUtil.dataValidation(calInfo.getClass(), calInfo);
		// 防止重复添加

		String claInfoCode = calInfo.getClaInfoCode();
		CalendarInfoEbo info = null;
		if (Util.isNull(claInfoCode)) {
			claInfoCode = Util.genDigiCodeStr(CalendarInfoEto.CODE_LEN);
			info = getCalInfoByIdOrCode(0, claInfoCode);
			while (info != null) {
				claInfoCode = Util.genDigiCodeStr(CalendarInfoEto.CODE_LEN);
				info = getCalInfoByIdOrCode(0, claInfoCode);
			}
		} else {
			info = getCalInfoByIdOrCode(0, claInfoCode);
			if (info != null)
				return info;
		}

		CalendarEntryEbo calEntry = getCalEntryByIdOrCode(calInfo.getCalEntryId(), null);
		if (calEntry == null)
			throw new DataException("系统无此日历主表，编号为：" + calInfo.getCalEntryId());

		if (calInfo.getStartTime() <= 0)// 如果开始时间为空默认为当前时间
			calInfo.setStartTime(DateUtil.getCurTimeInSec());
		// 判断结束时间是否大于开始时间
		boolean dateFlag = calInfo.getEndTime() <= calInfo.getStartTime() ? true : false;
		if (dateFlag)
			throw new DataException("结束时间必须大于开始时间");
		CalendarInfoEbo calInfo1 = null;
		try {
			calInfo1 = new CalendarInfoEbo();
			BeanUtils.copyProperties(calInfo, calInfo1);
			calInfo1.setCalId(calEntry.getId());
			calInfo1.setCode(claInfoCode);
			calMainDao.addCalInfo(calInfo1);
			return calInfo1;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	// 删除日历信息表记录。逻辑删除
	public void delCalInfo(int infoId) throws DataException {
		calMainDao.updateCalInfoFlag(infoId, null, Constant.FLAG_NO);
	}

	public CanlendarActivityEbo addCalActivity(CanlendarActivityEto calActivity) throws DataException {
		// 做空值验证
		DataValUtil.dataValidation(calActivity.getClass(), calActivity);

		CalendarInfoEbo calInfo = getCalInfoByIdOrCode(0, calActivity.getCalInfoCode());
		if (calInfo == null)
			throw new DataException("系统无此日历信息，编号为：" + calActivity.getCalInfoCode());
		CanlendarActivityEbo activity = null;
		try {
			activity = new CanlendarActivityEbo();
			BeanUtils.copyProperties(calActivity, activity);
			activity.setCalInfoId(calInfo.getId());
			calMainDao.addCalActivity(activity);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return activity;
	}

	public void updateCalInfo(int id, String code, String title, String memo, int maxPerson, int joinPerson,
			String flag, int startTime, int endTime) throws DataException {
		boolean isNull = id <= 0 && Util.isNull(code) ? true : false;
		if (isNull)
			return;

		// 修改加入人数时判断 是否已经到结束时间
		CalendarInfoEbo calInfo = getCalInfoByIdOrCode(id, code);
		if (calInfo == null)
			throw new DataException("系统中无此，日历信息记录");

		// 加入人数存在时
		else if (joinPerson > 0) {
			Integer currentTime = DateUtil.getCurTimeInSec();
			// 判断当前时间是否大于结束时间 如果大于则不能加入
			boolean timeFlag = currentTime > calInfo.getEndTime();
			if (timeFlag)
				throw new DataException("活动已经结束");
			// 判断是否已经到最大加入人数
			if (calInfo.getMaxPerson() < calInfo.getJoinPerson() + joinPerson) {
				throw new DataException("活动人数已满");
			}
		}

		// 同时修改开始时间和结束时间
		boolean isNull2 = startTime > 0 && endTime > 0 ? true : false;
		if (isNull2 && startTime >= endTime)
			throw new DataException("同时修改开始时间和结束时间时，开始时间不能大于等于结束时间");

		// 只修改开始时间
		if (!isNull2 && startTime > 0) {
			Integer curTime = DateUtil.getCurTimeInSec();
			Integer end = calInfo.getEndTime();
			if (startTime < curTime || startTime > end)
				throw new DataException("修改开始时间不能小于当前时间，且不能大于结束时间");
		}

		// 只修改结束时间
		if (!isNull2 && endTime > 0) {
			Integer curTime = DateUtil.getCurTimeInSec();
			Integer start = calInfo.getStartTime();
			if (endTime < curTime || start > endTime)
				throw new DataException("修改结束时间不能小于当前时间，且不能小于开始时间");
		}

		if (maxPerson > 0) {
			Integer currentJoinPerson = calInfo.getJoinPerson();
			if (currentJoinPerson > maxPerson)
				throw new DataException("修改最大加入人数时，不能小于当前加入数");
		}

		// 加入人数 是否有效状态同时存在时
		boolean updatejoinPersonFlag = joinPerson > 0 && Constant.FLAG_NO.equals(flag) ? true : false;
		if (updatejoinPersonFlag)
			throw new DataException("加入人数存在时,不能将信息表状态置为无效");

		// TODO 考虑是否会同时修改参加人数和最多参加人数（不需要）

		// 最大人数 加入人数同时修改时
		boolean updateMaxPersonFlag = maxPerson > 0 && joinPerson > 0 ? true : false;
		if (updateMaxPersonFlag) {
			Integer joinPersonNum = calInfo.getJoinPerson();
			if (maxPerson < joinPersonNum + joinPerson)
				throw new DataException("加入人数存在时,修改最大加入数不可以小于，原数加加入人数数");
		}
		// 结束时间 加入人数同时修改时
		boolean updateEndTimeFlag = endTime > 0 && joinPerson > 0 ? true : false;
		if (updateEndTimeFlag) {
			Integer currentTime = DateUtil.getCurTimeInSec();
			if (currentTime > endTime)
				throw new DataException("加入人数，结束时间同时存在时，结束时间不能小于当前时间");
		}

		calMainDao.updateCalInfo(id, code, title, memo, maxPerson, calInfo.getJoinPerson() + joinPerson, flag,
				startTime, endTime);
	}

	public void updateCalEntry(int id, String code, String name, String flag) throws DataException {
		boolean isNull = id <= 0 && Util.isNull(code) ? true : false;
		if (isNull)
			return;
		calMainDao.updateCalEntry(id, code, name, flag);
	}
	
	public void updateCalEntryIsdefault(int mainId, String mainType, String entryType, String isDefault)
			throws DataException{
		boolean isNull = mainId <= 0 || Util.isNull(mainType) || Util.isNull(entryType) ?true:false;
		if(isNull)
			return;
		calMainDao.updateCalEntryIsdefault(mainId, mainType, entryType, isDefault);
	}
	
	
	// 方法主入口
	public List<CalendarEntryDto> listEntryInfo(CalEntryInfoQto calEntryInfoQto, int page, int paging)
			throws DataException {
		//若获取所有数据  将有效时间变为0
		if(!calEntryInfoQto.isValid())
			calEntryInfoQto.setValidTime(0);
		List<CalEntryInfoInnerEbo> calEntryInners = calMainDao.listEntryInfo(calEntryInfoQto, page, paging);

		List<CalendarEntryDto> listEntryInfo = new ArrayList<CalendarEntryDto>();
		Map<Integer, CalendarEntryDto> entryMap = new HashMap<Integer, CalendarEntryDto>();
		for (CalEntryInfoInnerEbo cal : calEntryInners) {
			CalendarInfoDto calInfo = new CalendarInfoDto();
			BeanUtils.copyProperties(cal, calInfo);
			if (entryMap.containsKey(cal.getEntryId())) {
				entryMap.get(cal.getEntryId()).getListCalInfo().add(calInfo);
			} else {
				CalendarEntryDto calEntry = new CalendarEntryDto();
				BeanUtils.copyProperties(cal, calEntry);
				calEntry.getListCalInfo().add(calInfo);
				listEntryInfo.add(calEntry);
				entryMap.put(calEntry.getEntryId(), calEntry);
			}
		}
		return listEntryInfo;
	}

	// 查询月
	public List<CalendarEntryDto> listEntryInfoByMonth(CalEntryInfoQto calEntryInfoQto, int page, int paging,
			boolean notEndTime) throws DataException {
		// 获取一个月中的所有天
		List<String> dates = DateUtil.getDayList(calEntryInfoQto.getYear(), calEntryInfoQto.getMonth());
		// 预先判断
		if (dates.size() == 0)
			return new ArrayList<CalendarEntryDto>();

		String firstDay = dates.get(0) + " 00:00:00";
		String lastDay = dates.get(dates.size() - 1) + " 23:59:59";
		int startTime = DateUtil.parseTimeStrInSec(firstDay);
		calEntryInfoQto.setStartTime(startTime);
		int endTime = DateUtil.parseTimeStrInSec(lastDay);
		if (notEndTime) {
			return listEntryInfo(calEntryInfoQto, page, paging);
		} else {
			calEntryInfoQto.setEndTime(endTime);
			return listEntryInfo(calEntryInfoQto, page, paging);
		}
	}

	// 查询天
	public List<CalendarEntryDto> listEntryInfoByDay(CalEntryInfoQto calEntryInfoQto, int page, int paging,
			boolean notEndTime) throws DataException {
		int startTime = DateUtil.parseTimeStrInSec(calEntryInfoQto.getDayStr() + " 00:00:00");
		int endTime = DateUtil.parseTimeStrInSec(calEntryInfoQto.getDayStr() + " 23:59:59");
		calEntryInfoQto.setStartTime(startTime);
		if (notEndTime) {
			return listEntryInfo(calEntryInfoQto, page, paging);
		} else {
			calEntryInfoQto.setEndTime(endTime);
			return listEntryInfo(calEntryInfoQto, page, paging);
		}
	}

	/*
	 * // 列表日历行 public List<CalInfoInnerEbo> listCalInfoInnerEbo(int mainId,
	 * String mainType, int calId, String entryFlag, String infoFlag, String
	 * frmDay, String endDay) throws DataException {
	 * 
	 * int btime = 0; int etime = 0; // 默认 frmDay和endDay 只传日期。 if
	 * (Util.isNotNull(frmDay)) { btime = DateUtil.parseTimeStrInSec(frmDay +
	 * " 00:00:00"); } if (Util.isNotNull(endDay)) { etime =
	 * DateUtil.parseTimeStrInSec(endDay + " 23:59:59"); } return
	 * calMainDao.listCalInfoInnerEbo(mainId, mainType, calId, entryFlag,
	 * infoFlag, btime, etime); }
	 */

	public List<CalendarEventTypeEbo> listCalEventType(List<Integer> defIds) throws DataException {

		List<CalendarEventTypeEbo> types = new ArrayList<CalendarEventTypeEbo>();
		if (defIds == null || defIds.size() == 0)
			return types;

		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < defIds.size(); i++) {
			if (i != 0)
				buf.append(",");
			buf.append(defIds.get(i));
		}

		types = calMainDao.listCalEventType(buf.toString());
		return types;
	};

	public List<CalendarEntryEbo> listCalEntry(int mainId, String mainType, int createId, int orgId, String flag)
			throws DataException {
		/*
		 * if(orgId >0){ OrgEbo orgEbo = orgService.getOrgById(orgId); if(orgEbo
		 * == null){ throw new DataException("该组织不存在"); } }
		 */
		/*
		 * AdminUserEbo admin = adminUserService.getUserByUid(createId);
		 * if(admin == null){ throw new DataException("该管理员用户不存在"); }
		 */
		List<CalendarEntryEbo> listEntry = calMainDao.listCalEntry(mainId, mainType, createId, orgId, flag);
		return listEntry;
	};

}
