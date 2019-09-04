package com.kjplus.service.impl;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kjplus.dao.ISysRegionsDao;
import com.kjplus.dto.SysRegionsDto;
import com.kjplus.model.SysRegionsEbo;
import com.kjplus.service.ISysRegionsService;
import com.ybk.exception.DataException;

@Service("sysRegionsService")
public class SysRegionsService implements ISysRegionsService {

	@Autowired
	private ISysRegionsDao sysRegionsDao;

	public List<SysRegionsDto> listRegions(int pRegionId) throws DataException {
		List<SysRegionsEbo> regionList = sysRegionsDao.selectSysRegions(pRegionId);
		SysRegionsEbo rgEbo = null;
		List<SysRegionsDto> regions = new ArrayList<SysRegionsDto>();
		SysRegionsDto r = null;
		Hashtable<Integer,SysRegionsDto> rgHash = new Hashtable<Integer,SysRegionsDto>(); 
		for(int i=0;i<regionList.size();i++){
			rgEbo = regionList.get(i);
			r = new SysRegionsDto();
			r.setLocalName(rgEbo.getLocalName());
			r.setRegionId(rgEbo.getRegionId());
			if(rgEbo.getpRegionId() != null && rgEbo.getpRegionId().intValue() > 0 && rgHash.containsKey(rgEbo.getpRegionId())){
				rgHash.get(rgEbo.getpRegionId()).getSubs().add(r);
			}else{
				regions.add(r);
			}
			rgHash.put(rgEbo.getRegionId(), r);
		}
		return regions;
	}
}
