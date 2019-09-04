package com.kjplus.service;

import java.util.List;

import com.kjplus.dto.CalendarEntryDto;
import com.kjplus.eto.*;
import com.kjplus.model.*;
import com.kjplus.qto.CalEntryInfoQto;
import com.ybk.exception.DataException;

public interface ICalendarMainService {

	// 通过id code获取日历主表对象
	public CalendarEntryEbo getCalEntryByIdOrCode(int id, String code) throws DataException;

	// 获取默认工作日历
	/*
	 * public CalendarEntryEbo getCalEntryByMainIdOrType(int mainId, String
	 * mainType) throws DataException;
	 */
	public List<CalendarEntryEbo> listCalEntryByMainIdOrType(int mainId, String mainType, String entryType,
			String isDefault, int page, int paging) throws DataException;

	public List<CalendarEntryEbo> listCalEntry(int mainId, String mainType, int createId, int orgId, String flag)
			throws DataException;

	// 通过id code获取日历信息对象
	public CalendarInfoEbo getCalInfoByIdOrCode(int id, String code) throws DataException;

	// 添加日历主表记录    notIsDefault是否修改原有默认  true不修改     false修改
	public CalendarEntryEbo addCalEntry(CalendarEntryEto calEntry,boolean notIsDefault) throws DataException;

	// 添加日历信息表记录
	public CalendarInfoEbo addCalInfo(CalendarInfoEto calInfo) throws DataException;

	// 删除日历信息表记录。逻辑删除
	public void delCalInfo(int infoId) throws DataException;

	// 天机业务管理表
	public CanlendarActivityEbo addCalActivity(CanlendarActivityEto calActivity) throws DataException;

	// 修改加日历信息表中字段
	public void updateCalInfo(int id, String code, String title, String memo, int maxPerson, int joinPerson,
			String flag, int startTime, int endTime) throws DataException;

	// 修改加日历主表表中字段
	public void updateCalEntry(int id, String code, String name, String flag) throws DataException;

	// 修改某种日历类型的默认日历 mainId/mainType/entryType 均不能为空
	public void updateCalEntryIsdefault(int mainId, String mainType, String entryType, String isDefault)
			throws DataException;

	// 获取日历信息
	public List<CalendarEntryDto> listEntryInfo(CalEntryInfoQto calEntryInfoQto, int page, int paging)
			throws DataException;

	// 查询具体某天 notEndTime是否不设置endTime true不设置 false设置
	public List<CalendarEntryDto> listEntryInfoByDay(CalEntryInfoQto calEntryInfoQto, int page, int paging,
			boolean notEndTime) throws DataException;

	// 查询具体某月
	public List<CalendarEntryDto> listEntryInfoByMonth(CalEntryInfoQto calEntryInfoQto, int page, int paging,
			boolean notEndTime) throws DataException;

	/*
	 * // 列表日历行 public List<CalInfoInnerEbo> listCalInfoInnerEbo(int mainId,
	 * String mainType, int calId, String entryFlag, String infoFlag, String
	 * frmDay, String endDay) throws DataException;
	 */
	// 获得多个的eventTypes
	public List<CalendarEventTypeEbo> listCalEventType(List<Integer> defIds) throws DataException;

}
