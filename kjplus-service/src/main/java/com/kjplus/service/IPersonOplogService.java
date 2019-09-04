package com.kjplus.service;

import java.util.List;

import com.kjplus.eto.PersonOplogEto;
import com.kjplus.model.PersonOplogEbo;
import com.kjplus.model.inner.PersonOplogInnerEbo;
import com.ybk.exception.DataException;

public interface IPersonOplogService {

	// 根据id查询档案操作激励
	public PersonOplogEbo getPrsnOplogById(int id) throws DataException;

	// 列表档案操作记录
	public List<PersonOplogInnerEbo> listPrsnOplog(List<Integer> prsnIds, int opTypeId, int uid, int orgid, String flag, int startTime, int endTime,boolean isPrsn, int page, int paging)
			throws DataException;

	//列表档案操作记录  isPrsn判断当prsnIds为空时，是否返回数据 。false返回0,true返回全部数据
	public int getTotaPrsnOplog(List<Integer> prsnIds, int opTypeId, int uid, int orgid, String flag, int startTime, int endTime,boolean isPrsn) throws DataException;

	// 添加档案操作记录
	public PersonOplogEbo addPrsnOplog(PersonOplogEto prsnOplog) throws DataException;

}
