package com.kjplus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kjplus.model.*;
import com.kjplus.model.inner.AppointInfoInnerEbo;
import com.ybk.exception.DataException;

@Repository("appointDao")
public interface IAppointDao {

	/**
	 * 通过id，code获取AppointEbo对象
	 * 
	 * @param id
	 *            <p>
	 *            t_appiont表id
	 * @param code
	 *            <p>
	 *            t_appiont表code
	 * @return 返回AppointEbo对象
	 * @throws DataException
	 */
	public AppointEbo getAppointByIdOrCode(@Param("id") int id, @Param("code") String code) throws DataException;

	/**
	 * 
	 * @param appiont
	 *            传入AppointEbo对象
	 * @throws DataException
	 */
	public void addAppoint(AppointEbo appiont) throws DataException;

	/**
	 * 预约列表查询
	 * 
	 * @param
	 * 
	 * @return
	 * @throws
	 */
	public List<AppointInfoInnerEbo> listAppointInfo(@Param("calId") int calId, 
			@Param("prsnIdStr") String prsnIdStr, @Param("orgId") int orgId, @Param("mainId") int mainId,
			@Param("mainType") String mainType,@Param("staffName") String staffName, @Param("startTime") int startTime, @Param("endTime") int endTime,
			@Param("status") String status,@Param("typeId") int typeId,@Param("appTypeId") int appTypeId,@Param("page") int page, @Param("paging") int paging) throws DataException;

	/**
	 * 预约列表总数
	 * 
	 * @param
	 * 
	 * @return
	 * @throws
	 */
	public int getTotalAppoint(@Param("calId") int calId, 
			@Param("prsnIdStr") String prsnIdStr, @Param("orgId") int orgId, @Param("mainId") int mainId,
			@Param("mainType") String mainType,@Param("staffName") String staffName, @Param("startTime") int startTime, @Param("endTime") int endTime,
			@Param("status") String status,@Param("typeId") int typeId,@Param("appTypeId") int appTypeId) throws DataException;

	/**
	 * 通过 id，code修改预约状态
	 * 
	 * @param id
	 *            <p>
	 *            t_appiont表id
	 * @param code
	 *            <p>
	 *            t_appiont表code
	 * @param status
	 *            <p>
	 *            t_appiont表要修改的状态
	 * @throws DataException
	 */
	public void updateAppointStatus(@Param("id") int id, @Param("code") String code, @Param("status") String status, @Param("memo") String memo)
			throws DataException;

	/**
	 * 修改预约基本信息
	 * 
	 * @param
	 * 
	 * @throws DataException
	 */
	public void updateAppointEbo(AppointEbo appointEbo) throws DataException;

}
