package com.kjplus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kjplus.model.*;
import com.kjplus.model.inner.CalEntryInfoInnerEbo;
import com.kjplus.qto.CalEntryInfoQto;
import com.ybk.exception.DataException;

@Repository("calMainDao")
public interface ICalendarMainDao {

	/**
	 * 
	 * @param id
	 *            <p>
	 *            t_calendar_entry表主键id
	 * @param code
	 *            <p>
	 *            t_calendar_entry表主键编号
	 * @return <p>
	 *         返回CalendarEntryEbo对象
	 * @throws DataException
	 */
	public CalendarEntryEbo getCalEntryByIdOrCode(@Param("id") int id, @Param("code") String code) throws DataException;

	/**
	 * 获取默认工作日历 有且只有一个
	 * 
	 * @param mainId
	 *            <p>
	 *            t_calendar_entry表main_id
	 * @param mainType
	 *            <p>
	 *            t_calendar_entry表main_type entry_type = "W"(工作日历) is_default =
	 *            "Y"
	 * 
	 * @return <p>
	 *         返回CalendarEntryEbo对象
	 * @throws DataException
	 */

	public List<CalendarEntryEbo> listCalEntryByMainIdOrType(@Param("mainId") int mainId,
			@Param("mainType") String mainType, @Param("entryType") String entryType,
			@Param("isDefault") String isDefault, @Param("page") int page, @Param("paging") int paging)
			throws DataException;

	public List<CalendarEntryEbo> listCalEntry(@Param("mainId") int mainId, @Param("mainType") String mainType,
			@Param("createId") int createId, @Param("orgId") int orgId, @Param("flag") String flag)
			throws DataException;

	/**
	 * 
	 * @param id
	 *            <p>
	 *            t_calendar_info表id
	 * @param code
	 *            <p>
	 *            t_calendar_info表code
	 * @return<p> 返回CalendarInfoEbo对象
	 * @throws DataException
	 */
	public CalendarInfoEbo getCalInfoByIdOrCode(@Param("id") int id, @Param("code") String code) throws DataException;

	/**
	 * 
	 * @param id
	 *            <p>
	 *            t_calendar_entry表主键id
	 * @param code
	 *            <p>
	 *            t_calendar_entry表主键code
	 * @param name
	 *            <p>
	 *            t_calendar_entry表描述
	 * @param flag
	 *            <p>
	 *            t_calendar_entry表主键是否有效
	 * @throws DataException
	 */
	public void updateCalEntry(@Param("id") int id, @Param("code") String code, @Param("name") String name,
			@Param("flag") String flag) throws DataException;

	// 修改某种日历类型的默认日历    mainId/mainType/entryType 均不能为空
	public void updateCalEntryIsdefault(@Param("mainId") int mainId, @Param("mainType") String mainType,
			@Param("entryType") String entryType,@Param("isDefault") String isDefault) throws DataException;

	/**
	 * 修改 t_calendar_info表参数
	 * 
	 * @param id
	 *            <p>
	 *            t_calendar_info表id
	 * @param code
	 *            <p>
	 *            t_calendar_info表code
	 * @param maxPerson
	 *            <p>
	 *            t_calendar_info表最大人数
	 * @param joinPerson
	 *            <p>
	 *            t_calendar_info表加入人数
	 * @param flag
	 *            <p>
	 *            t_calendar_info表状态
	 * @param endTime
	 *            <p>
	 *            t_calendar_info表结束时间
	 * @return
	 * @throws DataException
	 */
	public void updateCalInfo(@Param("id") int id, @Param("code") String code, @Param("title") String title,
			@Param("memo") String memo, @Param("maxPerson") int maxPerson, @Param("joinPerson") int joinPerson,
			@Param("flag") String flag, @Param("startTime") int startTime, @Param("endTime") int endTime)
			throws DataException;

	public void updateCalInfoFlag(@Param("id") int id, @Param("code") String code, @Param("flag") String flag)
			throws DataException;

	/**
	 * 
	 * @param calEntry
	 *            <p>
	 *            传入CalendarEntryEbo对象
	 * 
	 * @throws DataException
	 */
	public void addCalEntry(CalendarEntryEbo calEntry) throws DataException;

	/**
	 * 
	 * @param calInfo
	 *            <p>
	 *            传入CalendarInfoEbo对象
	 * @throws DataException
	 */
	public void addCalInfo(CalendarInfoEbo calInfo) throws DataException;

	/**
	 * 
	 * @param claActivity
	 *            <p>
	 *            传入CanlendarActivityEbo对象
	 * @throws DataException
	 */
	public void addCalActivity(CanlendarActivityEbo claActivity) throws DataException;

	// 列表日历活动
	public List<CalendarActivityEbo> listCalActivitySingleEbo(@Param("calInfoId") int calInfoId,
			@Param("mainId") int mainId, @Param("mainType") String mainType) throws DataException;

	// 删除日历活动
	public void delCalActivity(@Param("id") int id) throws DataException;

	/**
	 * @param calEntryInfoQto
	 *            日历信息查询条件
	 * @param page
	 *            分页开始
	 * @param paging
	 *            分页结束
	 * @return
	 */
	public List<CalEntryInfoInnerEbo> listEntryInfo(@Param("calEntryInfoQto") CalEntryInfoQto calEntryInfoQto,
			@Param("page") int page, @Param("paging") int paging) throws DataException;

	// 获取活动
	public CalendarEventTypeEbo getCalEventType(@Param("id") int id) throws DataException;

	// 获取日历活动
	public List<CalendarEventTypeEbo> listCalEventType(@Param("defIds") String defIds) throws DataException;

	// 添加日历活动
	public void addCalEventType(CalendarEventTypeEbo eventTypeEto) throws DataException;

}
