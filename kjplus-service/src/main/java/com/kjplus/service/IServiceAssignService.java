package com.kjplus.service;

import java.util.List;

import com.kjplus.dto.*;
import com.kjplus.eto.*;
import com.kjplus.model.*;
import com.ybk.exception.DataException;

public interface IServiceAssignService {

	// 通过ID获取签约记录信息
	public ServAsgnEbo getServAssByIdOrCode(int id, String code) throws DataException;

	// 获取用户所有的签约记录    isEffect 是否有效   true只获取有效时间的记录     false获取全部记录
	public List<ServAsgnEbo> listServAssByPrsnId(int prsnId,boolean isEffect) throws DataException;

	// 签约服务列表   有效时间内的签约记录  isEffect 是否有效的签约记录    isPackages 是否将服务包合并
 	public List<ServAsgnDto> listAsgnDto(String personName, int personId, String staffName, int staffId,
			String isDefault, int orgId, String status,boolean isEffect,boolean isPackages, int page, int paging) throws DataException;

	// 获取签约记录总数
	public int getTotalServAss(String personName, int personId, String staffName, int staffId, String isDefault,
			int orgId, String status,boolean isEffect,boolean isPackages) throws DataException;

	// 添加签约记录 用户在有效期间只能签约一个团体
	public void addServAsgn(SrvAsgnEto servAsgn) throws DataException;

	// 修改签约信息
	public void updateServAsgn(ServAsgnEbo servAsgnEbo) throws DataException;

	// 批量修改  修改签约服务状态
	public void updateServAsgnStatus(List<IDNameDto> idCodes, String status, String memo) throws DataException;
	
	// 单独修改  修改签约服务状态
	public void updateServAsgnStatus(int id,String code, String status, String memo) throws DataException;

	// 添加协议
	public void addServHead(ServHeadEto servHead) throws DataException;

	// 修改协议状态
	public void updateServHeadByidOrOrgId(int servHeadId, int orgId, String flag) throws DataException;

	// 查询协议列表
	public List<OrgServHeadDto> listServHead(int orgId, String orgCode, String flag) throws DataException;
}
