package com.kjplus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kjplus.model.*;
import com.kjplus.model.inner.ServicePackageInnerEbo;
import com.ybk.exception.DataException;

@Repository("servicePackageDao")
public interface IServicePackageDao {

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
	public ServicePackageEbo getSrvPackageByIdOrCode(@Param("id") int id, @Param("code") String code)
			throws DataException;

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
	public ServicePackageEbo getSrvPackageByIsDefault(@Param("orgId") int orgId, @Param("isDefault") String isDefault)
			throws DataException;

	/**
	 * 添加居民服务包
	 * 
	 * @param srvPackageEbo
	 *            传入ServicePackageEbo对象
	 * @throws DataException
	 */
	public void addSrvPackage(ServicePackageEbo srvPackageEbo) throws DataException;

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
	public List<ServicePackageInnerEbo> listSrvPackageInner(@Param("name") String name, @Param("orgId") int orgId,
			@Param("status") String status, @Param("isDefault") String isDefault,
			@Param("periodCode") String periodCode, @Param("periodVal") int periodVal,
			@Param("startDay") String startDay, @Param("finishDay") String finishDay, @Param("page") int page,
			@Param("paging") int paging) throws DataException;

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
	public int getTotalSrvPackage(@Param("name") String name, @Param("orgId") int orgId,
			@Param("status") String status, @Param("isDefault") String isDefault,
			@Param("periodCode") String periodCode, @Param("periodVal") int periodVal) throws DataException;

	/**
	 * 仅能修改居民服务包默认服务为非默认服务包
	 * 
	 * @param orgId
	 *            组织id
	 * @param isDefault
	 *            默认状态
	 * @return
	 * @throws DataException
	 */
	public void updateSrvPackageIsDefault(@Param("orgId") int orgId, @Param("isDefault") String isDefault)
			throws DataException;

	/**
	 * 修改服务包基本信息
	 * 
	 * @param servAsgn
	 *            传入ServAsgnEbo对象
	 * @throws DataException
	 */
	public void updateSrvPackage(ServicePackageEbo srvPackageEbo) throws DataException;

}
