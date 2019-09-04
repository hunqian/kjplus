package com.kjplus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kjplus.model.DeviceParamEbo;
import com.ybk.exception.DataException;


@Repository("dvcParamDao")
public interface IDeviceParamDao {

	public DeviceParamEbo getDeviceParam(@Param("id") int id, @Param("dvcParam") String dvcParam) throws DataException;
	
	public void addDeviceParam(DeviceParamEbo dvcParamEbo) throws DataException;

	public List<DeviceParamEbo> listDeviceParamList(@Param("dvcType") String dvcType,@Param("paramType") String paramType
			,@Param("dvcParam") String dvcParam,@Param("flag") String flag,@Param("page") int page,@Param("paging") int paging) throws DataException;
	
	public int getTotalDeviceParam(@Param("dvcType") String dvcType,@Param("paramType") String paramType
			,@Param("dvcParam") String dvcParam,@Param("flag") String flag) throws DataException;
	
}
