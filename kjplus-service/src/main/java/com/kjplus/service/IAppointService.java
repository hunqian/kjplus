package com.kjplus.service;

import java.util.List;

import com.kjplus.eto.AppointEto;
import com.kjplus.model.AppointEbo;
import com.kjplus.model.inner.AppointInfoInnerEbo;
import com.ybk.exception.DataException;

public interface IAppointService {

	// 通过id ,code 获取 appiontEbo对象
	public AppointEbo getAppointByIdOrCode(int id, String code) throws DataException;

	// 添加预约记录
	public AppointEbo addAppoint(AppointEto appoint) throws DataException;

	// 列表预约信息
	/*
	 * public List<AppiontUserInfoDto> listAppointInfo(int uid, int prsnId, int
	 * startTime, int endTime) throws DataException;
	 */
	public List<AppointInfoInnerEbo> listAppointInfo(int calId, List<Integer> prsnIds, int orgId, int mainId,
			String mainType,String staffName, int startTime, int endTime,String status,int typeId,int appTypeId,int page,int paging) throws DataException;
	
	public int getTotalAppoint(int calId, List<Integer> prsnIds, int orgId, int mainId,
			String mainType,String staffName, int startTime, int endTime,String status,int typeId,int appTypeId) throws DataException;
	
	public List<AppointInfoInnerEbo> listAppointInfo(int calId, int prsnId, int orgId, int mainId,
			String mainType,String staffName, int startTime, int endTime,String status,int typeId,int appTypeId,int page,int paging) throws DataException;

	public int getTotalAppoint(int calId, int prsnId, int orgId, int mainId,
			String mainType,String staffName, int startTime, int endTime,String status,int typeId,int appTypeId) throws DataException;
	
	// 修改 预约状态
	public void updateAppointStatus(int id, String code, String status,String memo) throws DataException;

	// 修改 预约基本信息
	public void updateAppointEbo(AppointEbo appointEbo) throws DataException;

}
