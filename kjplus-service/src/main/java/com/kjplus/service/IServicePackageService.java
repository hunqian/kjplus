package com.kjplus.service;

import java.util.List;

import com.kjplus.eto.*;
import com.kjplus.model.*;
import com.kjplus.model.inner.ServicePackageInnerEbo;
import com.ybk.exception.DataException;

public interface IServicePackageService {

	/**
	 * 通过编号或id查询居民服务包
	 * 
	 * @param code
	 *            服务编号
	 * @param id
	 *            服务id
	 * @return 返回ServPackageEbo对象
	 * @throws DataException
	 */
	public ServicePackageEbo getSrvPackageByIdOrCode(int id, String code) throws DataException;

	/**
	 * 获取组织是否有默认居民服务包
	 * 
	 * @param orgId
	 *            组织id
	 * @param isDefault
	 *            是否有默认服务
	 * @return 返回ServicePackageEbo对象
	 * @throws DataException
	 */
	public ServicePackageEbo getSrvPackageByIsDefault(int orgId) throws DataException;

	/**
	 * 添加居民服务包
	 * 
	 * @param srvPackageEto
	 *            传入ServicePackageEto对象
	 * @throws DataException
	 */
	public ServicePackageEbo addSrvPackage(ServicePackageEto srvPackageEto) throws DataException;

	/**
	 * 组织服务列表
	 * 
	 * @param orgId
	 *            组织id
	 * @param code
	 *            组织编号
	 * @param status
	 *            服务为状态
	 * @param isDefault
	 *            是否是默认服务
	 * @param periodCode
	 *            类型 年 月 季 天
	 * @param periodVal
	 *            周期值
	 * @return
	 * @throws DataException
	 */
	public List<ServicePackageInnerEbo> listSrvPackageInner(String name, int orgId, String status, String isDefault,
			String periodCode, int periodVal,String startDay,String finishDay, int page, int paging) throws DataException;

	/**
	 * 查询组织居民服务包总数
	 * 
	 * @param orgId
	 *            组织id
	 * @param code
	 *            组织编号
	 * @param status
	 *            服务为状态
	 * @param isDefault
	 *            是否是默认服务
	 * @param periodCode
	 *            类型 年 月 季 天
	 * @param periodVal
	 *            周期值
	 * @return
	 * @throws DataException
	 */
	public int getTotalSrvPackage(String name, int orgId, String status, String isDefault, String periodCode,
			int periodVal) throws DataException;

	/**
	 * 修改某项居民服务包为默认服务
	 * 
	 * @param orgId
	 *            组织id
	 * @param isDefault
	 *            默认状态
	 * @return
	 * @throws DataException
	 */
	public void updateSrvPackageIsDefault(int id,String code) throws DataException;

	/**
	 * 修改服务包基本信息
	 * 
	 * @param servAsgn
	 *            传入ServAsgnEbo对象
	 * @throws DataException
	 */
	public void updateSrvPackage(ServicePackageEbo srvPackageEbo) throws DataException;

}
