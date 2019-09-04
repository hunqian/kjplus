package com.kjplus.service;

import java.util.List;

import com.kjplus.eto.DvcParamEto;
import com.kjplus.model.DeviceParamEbo;
import com.ybk.exception.DataException;

public interface IDeviceParamService {

	public DeviceParamEbo getDeviceParam(int id, String dvcParam) throws DataException;

	public DeviceParamEbo addDeviceParam(DvcParamEto dvcParamEto) throws DataException;

	public List<DeviceParamEbo> listDeviceParamList(String dvcType, String paramType, String dvcParam, String flag,
			int page, int paging) throws DataException;

	public int getTotalDeviceParam(String dvcType, String paramType, String dvcParam, String flag) throws DataException;

}
