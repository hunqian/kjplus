package com.kjplus.service.impl;

import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ybk.basic.util.Util;

import com.kjplus.constant.Constant;
import com.kjplus.dao.IOrgDao;
import com.kjplus.dto.OrgDto;
import com.kjplus.eto.OrgEto;
import com.kjplus.model.OrgEbo;
import com.kjplus.model.inner.OrgInnerEbo;
import com.kjplus.service.IOrgService;
import com.ybk.exception.DataException;

@Service("orgService")
public class OrgService implements IOrgService {
	@Autowired
	private IOrgDao orgDao;

	private static Logger logger = Logger.getLogger(OrgService.class);

	// 通过啊编号查询组织
	public OrgEbo getOrgByCode(String code) throws DataException {
		if (Util.isNull(code))
			return null;
		return orgDao.getOrgByCode(code);
	}

	// 通过id查询组织
	public OrgEbo getOrgById(int orgId) throws DataException {
		if (orgId <= 0)
			return null;
		return orgDao.getOrgById(orgId);
	}

	public OrgEbo addOrg(OrgEto org) throws DataException {
		if (org == null)
			throw new DataException("[err]添加组织组织对象不能空");
		if (Util.isNull(org.getName()))
			throw new DataException("[err]组织名称不能空");
		if (org.getOrgTypeId() <= 0)
			throw new DataException("[err]请指定org类型!");
		
		if (org.getPrvnId() == null || org.getPrvnId().intValue() <= 0)
			throw new DataException("[err]请指定所属的省!");
		if (org.getCityId() == null || org.getCityId().intValue() <= 0)
			throw new DataException("[err]请指定所属的市!");
		
		OrgEbo org2 = null;
		String code = org.getCode();
		// 产生一个8位长度
		if (Util.isNull(code)) {
			code = Util.genDigiCodeStr(OrgEto.CODE_LEN);
			org2 = getOrgByCode(code);
			while (org2 != null) {
				code = Util.genDigiCodeStr(OrgEto.CODE_LEN);
				org2 = getOrgByCode(code);
			}
		} else {
			org2 = getOrgByCode(code);
			if (org2 != null)
				return org2;
		}

		if (org.getPid() > 0) {
			org2 = getOrgById(org.getPid());
			if (org2 == null)
				throw new DataException("[err]上级pid=" + org.getPid() + "对应的org不存在!");
		}

		OrgEbo orgEbo = new OrgEbo();
		try {
			BeanUtils.copyProperties(org, orgEbo);
			orgEbo.setCode(code);
			orgEbo.setCreateTime(Calendar.getInstance().getTime());
			orgEbo.setFlag(Constant.FLAG_YES);
			if (Util.isNull(org.getAlias()))
				orgEbo.setAlias(org.getName());
			else
				orgEbo.setAlias(org.getAlias());
			orgDao.addOrg(orgEbo);
		} catch (BeansException be) {
			logger.error(be);
		}
		return orgEbo;
	}

	/**
	 * 列表组织
	 * 
	 * @throws DataException
	 */
	public List<OrgDto> listOrg(String orgName, String flag, int cityId, int prvnId, 
			boolean multiLevel, int page, int paging) throws DataException {
		List<OrgInnerEbo> orgList = orgDao.listOrgInnerEbo(orgName, flag, cityId, prvnId, page, paging);
		List<OrgDto> orgs = new ArrayList<OrgDto>();
		Hashtable<Integer, OrgDto> orgHash = new Hashtable<Integer, OrgDto>();
		try {
			for (int i = 0; i < orgList.size(); i++) {
				OrgInnerEbo orgInner = orgList.get(i);
				OrgDto orgDto = new OrgDto();
				BeanUtils.copyProperties(orgInner, orgDto);
				if (multiLevel && orgInner.getPid() != null && orgHash.containsKey(orgInner.getPid())) {
					orgHash.get(orgInner.getPid()).getSubs().add(orgDto);
				} else {
					orgs.add(orgDto);
				}
				orgHash.put(orgInner.getId(), orgDto);
			}
		} catch (BeansException be) {
			logger.error(be);
		}
		return orgs;
	}
	
	//根据id列表获得
	public List<OrgDto> listOrgByIds(List<Integer> orgIds) throws DataException {
		List<OrgDto> orgs = new ArrayList<OrgDto>();
		if(orgIds == null || orgIds.size() == 0)
			return orgs;
		StringBuffer buf = new StringBuffer();
		for(int i=0;i<orgIds.size();i++){
			if(i != 0)
				buf.append(",");
			buf.append(orgIds.get(i));
		}
		List<OrgInnerEbo> orgList = orgDao.listOrgInnerEboByIds(buf.toString());
		try {
			for (int i = 0; i < orgList.size(); i++) {
				OrgInnerEbo orgInner = orgList.get(i);
				OrgDto orgDto = new OrgDto();
				BeanUtils.copyProperties(orgInner, orgDto);
				orgs.add(orgDto);
			}
		} catch (BeansException be) {
			logger.error(be);
		}
		return orgs;
	}

	/**
	 * 获得组织数量
	 * 
	 * @throws DataException
	 */
	public int getTotalOrg(String orgName, String flag, int cityId, int prvnId,
			boolean multiLevel) throws DataException {
		if(multiLevel)
			return orgDao.getTotalOrg(orgName, flag, 0, cityId, prvnId);
		else
			return orgDao.getTotalOrg(orgName, flag, -1, cityId, prvnId);
	}

	// 修改组织
	public void updateOrg(int orgid, OrgEto org) throws DataException {
		if(orgid <=0 )
			return;
		if(org == null)
			return;
		orgDao.updateOrg(orgid, org.getName(), org.getAlias(), org.getAddr(), org.getHeadIconurl(), 
				org.getFlag(), org.getOrgTypeId(), org.getAreaId(), org.getCityId(), org.getPrvnId());	
	}
}
