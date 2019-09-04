package com.kjplus.service;

import java.util.List;

import com.kjplus.dto.SysRegionsDto;
import com.ybk.exception.DataException;

public interface ISysRegionsService {

	// 通过code获取部门
	public List<SysRegionsDto> listRegions(int pRegionId) throws DataException;

}
