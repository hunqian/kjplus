package com.kjplus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kjplus.model.SysRegionsEbo;
import com.ybk.exception.DataException;

@Repository("sysRegionsDao")
public interface ISysRegionsDao {

	public List<SysRegionsEbo> selectSysRegions(@Param("pRegionId") int pRegionId) throws DataException;

}
