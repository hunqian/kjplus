package com.kjplus.service;

import java.util.List;

import com.kjplus.dto.OrgDto;
import com.kjplus.eto.OrgEto;
import com.kjplus.model.OrgEbo;
import com.ybk.exception.DataException;

public interface IOrgService {

	// 通过code获取组织
	public OrgEbo getOrgByCode(String code) throws DataException;

	// 通过orgId获取组织
	public OrgEbo getOrgById(int orgId) throws DataException;

	// 添加组织
	public OrgEbo addOrg(OrgEto org) throws DataException;

	// 修改组织
	public void updateOrg(int orgid, OrgEto org) throws DataException;

	/**
	 * 列表组织
	 * 
	 * multiLevel=true,返回树状
	 * @throws DataException
	 */
	public List<OrgDto> listOrg(String orgName, String flag , int cityId, int prvnId,
			boolean multiLevel, int page, int paging) throws DataException;

	/**
	 * 获得组织数量
	 * 
	 * @throws DataException
	 */
	public int getTotalOrg(String orgName, String flag , int cityId, int prvnId,
			boolean multiLevel) throws DataException;

	//根据id列表获得
	public List<OrgDto> listOrgByIds(List<Integer> orgIds) throws DataException ;
}
