package com.kjplus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kjplus.model.PersonOplogEbo;
import com.kjplus.model.inner.PersonOplogInnerEbo;
import com.ybk.exception.DataException;

@Repository
public interface IPersonOplogDao {

	// 根据id查询档案操作激励
	public PersonOplogEbo getPrsnOplogById(@Param("id") int id) throws DataException;

	// 列表档案操作记录
	public List<PersonOplogInnerEbo> listPrsnOplog(@Param("prsnIds") String prsnIds, @Param("opTypeId") int opTypeId, @Param("uid") int uid,
			@Param("orgid") int orgid, @Param("flag") String flag, @Param("startTime") int startTime, @Param("endTime") int endTime, @Param("page") int page,
			@Param("paging") int paging) throws DataException;

	// 列表档案操作记录
	public int getTotalPrsnOplog(@Param("prsnIds") String prsnIds, @Param("opTypeId") int opTypeId, @Param("uid") int uid, @Param("orgid") int orgid,
			@Param("flag") String flag, @Param("startTime") int startTime, @Param("endTime") int endTime) throws DataException;

	// 添加档案操作记录
	public void addPrsnOplog(PersonOplogEbo PrsnOplogEbo) throws DataException;

	// 获取档案用户最大的操作次数记录
	public Integer getBestOpSeq(@Param("prsnId") int prsnId, @Param("flag") String flag) throws DataException;

}
