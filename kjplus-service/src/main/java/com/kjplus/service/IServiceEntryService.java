package com.kjplus.service;

import java.util.List;
 
import com.kjplus.dto.ServiceEntryDto;
import com.kjplus.eto.*;
import com.kjplus.model.*;
import com.kjplus.model.inner.ServiceEntryInnerEbo;
import com.ybk.exception.DataException;

public interface IServiceEntryService {

	// 通过id编号获取 服务主入口
	public ServiceEntryEbo getSrvEntryByIdOrCode(int id, String srvCode) throws DataException;

	// 通过id获取 服务配置
	public ServiceConfigEbo getSrvConfigById(int id) throws DataException;

	// 列表配置
	public List<ServiceConfigEbo> listSrvConfigEbo(int srvId, int orgId)
			throws DataException;

	// 添加服务时默认添加配置
	public ServiceEntryInnerEbo addSrvEntry(ServiceEntryEto srvEntry) throws DataException;

	// 修改服务
	public void updateSrvEntry(ServiceEntryEbo serviceEntryEbo) throws DataException;

	// 添加服务配置
	public ServiceConfigEbo addSrvConfig(ServiceConfigEto srvConfig) throws DataException;

	// 总服务列表
	public List<ServiceEntryDto> listServiceEntry(int orgId, List<Integer> typeIds, String type, String opType, String name,
			String flag, int page, int paging, boolean treeStyle, boolean childNearStyle) throws DataException;

	// 获取服务总数
	public int getTotalEntry(int orgId, List<Integer> typeIds, String type, String opType, String name, String flag)
			throws DataException;

}
