package com.kjplus.service.impl;

import java.util.List;

import com.kjplus.dao.IDeviceParamDao;
import com.kjplus.eto.DvcParamEto;
import com.kjplus.model.DeviceParamEbo;
import com.kjplus.service.IDeviceParamService;
import com.kjplus.util.DataValUtil;
import com.ybk.exception.DataException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ybk.basic.util.Util;


@Service("dvcParamService")
public class DvcParamService implements IDeviceParamService {

	private static Logger logger = Logger.getLogger(DvcParamService.class);

	@Autowired
	private IDeviceParamDao dvcParamDao;

	public DeviceParamEbo getDeviceParam(int id, String dvcParam) throws DataException {
		boolean isNull = id <= 0 && Util.isNull(dvcParam) ? true : false;
		if (isNull)
			return null;
		DeviceParamEbo  dvcParamEbo =  dvcParamDao.getDeviceParam(id, dvcParam);
		return dvcParamEbo;
	}

	public DeviceParamEbo addDeviceParam(DvcParamEto dvcParamEto) throws DataException {
		//空值验证
		DataValUtil.dataValidation(dvcParamEto.getClass(), dvcParamEto);
		DeviceParamEbo  dvcEbo =  dvcParamDao.getDeviceParam(0, dvcParamEto.getDeviceParam());
		if( dvcEbo != null)
			throw new DataException("该的设备已存在，deviceParam = "+dvcEbo.getDeviceParam());
		String dvcType = dvcParamEto.getDeviceType(); 
		//设备类型。M手机/I IPAD/S血糖仪/P血压仪
		if(dvcType.equals("M"))
			dvcType = "M";
		else if(dvcType.equals("I"))
			dvcType = "I";
		else if(dvcType.equals("S"))
			dvcType = "S";
		else if(dvcType.equals("P"))
			dvcType = "P";
		else
			throw new DataException("输入的设备类型不存在，deviceType = "+dvcType);
		String paramType = dvcParamEto.getParamType();
		//MAC/SN序列号
		if(paramType.equals("MAC"))
			paramType = "MAC";
		else if(paramType.equals("SN"))
			paramType = "SN";
		else
			throw new DataException("输入的参数类型不存在，paramType = "+paramType);
		String flag = dvcParamEto.getFlag();
		if(flag.equals("Y"))
			flag = "Y";
		else if(flag.equals("N"))
			flag = "N";
		else
			throw new DataException("输入的flag类型不对，flag = "+flag);
		try {
			DeviceParamEbo dvcParamEbo = new DeviceParamEbo();
			dvcParamEbo.setDeviceType(dvcParamEto.getDeviceType());
			dvcParamEbo.setParamType(dvcParamEto.getParamType());
			dvcParamEbo.setDeviceParam(dvcParamEto.getDeviceParam());
			dvcParamEbo.setFlag(dvcParamEto.getFlag());
			dvcParamEbo.setCreateTime(dvcParamEto.getCreateTime());
			dvcParamDao.addDeviceParam(dvcParamEbo);
			return dvcParamEbo;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	public List<DeviceParamEbo> listDeviceParamList(String dvcType, String paramType, String dvcParam, String flag,int page,int paging)
			throws DataException {
		//设备类型。M手机/I IPAD/S血糖仪/P血压仪
		if(Util.isNotNull(dvcType)){
			if(dvcType.equals("M"))
				dvcType = "M";
			else if(dvcType.equals("I"))
				dvcType = "I";
			else if(dvcType.equals("S"))
				dvcType = "S";
			else if(dvcType.equals("P"))
				dvcType = "P";
			else
				throw new DataException("输入的设备类型不存在，deviceType = "+dvcType);
		}	
		//MAC/SN序列号
		if(Util.isNotNull(paramType)){
			if(paramType.equals("MAC"))
				paramType = "MAC";
			else if(paramType.equals("SN"))
				paramType = "SN";
			else
				throw new DataException("输入的参数类型不存在，paramType = "+paramType);
		}
		if(Util.isNotNull(flag)){
			if(flag.equals("Y"))
				flag = "Y";
			else if(flag.equals("N"))
				flag = "N";
			else
				throw new DataException("输入的flag类型不对，flag = "+flag);
		}	
		List<DeviceParamEbo> listDvcParamEbo = dvcParamDao.listDeviceParamList(dvcType, paramType, dvcParam, flag,page,paging);
		int total = dvcParamDao.getTotalDeviceParam(dvcType, paramType, dvcParam, flag);
		System.out.println("total"+total);
		return listDvcParamEbo;
	}
	public int getTotalDeviceParam(String dvcType, String paramType, String dvcParam, String flag)
			throws DataException {
		int total = dvcParamDao.getTotalDeviceParam(dvcType, paramType, dvcParam, flag);
		return total;
	}
}
