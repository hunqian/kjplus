package com.kjplus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kjplus.model.DocumentInfoEbo;
import com.kjplus.model.inner.DocumentInfoInnerEbo;
import com.ybk.exception.DataException;

@Repository("docInfoDao")
public interface IDocumentInfoDao {

	// 根据id和code获得对象
	public List<DocumentInfoEbo> getDocInfoByIdOrCode(@Param("prsnId") int prsnId, @Param("code") String code) throws DataException;

	// 查询一个orgid中的idNumber对应的
	public List<DocumentInfoEbo> listDocInfo(@Param("orgId") int orgId, @Param("prsnCode") String prsnCode, @Param("idNumber") String idNumber)
			throws DataException;

	// 增加addDocInfo
	public void addDocInfo(DocumentInfoEbo doc) throws DataException;

	// 罗列组合
	public List<DocumentInfoInnerEbo> listDocInfoInner(@Param("orgId") int orgId, @Param("name") String name, @Param("status") String status,
			@Param("flag") String flag, @Param("idCard") String idCard, @Param("startTime") int startTime, @Param("endTime") int endTime,
			@Param("page") int page, @Param("paging") int paging) throws DataException;

	// 罗列标签组合
	public List<DocumentInfoInnerEbo> listDocInfoTagInner(@Param("orgId") int orgId, @Param("name") String name, @Param("status") String status,
			@Param("flag") String flag, @Param("idCard") String idCard, @Param("tagIds") String tagIds, @Param("startTime") int startTime,
			@Param("endTime") int endTime, @Param("page") int page, @Param("paging") int paging) throws DataException;

	// 总数
	public int getTotalDocInfoInner(@Param("orgId") int orgId, @Param("name") String name, @Param("status") String status, @Param("flag") String flag,
			@Param("idCard") String idCard, @Param("startTime") int startTime, @Param("endTime") int endTime) throws DataException;

	// 筛选标签
	public int getTotalDocInfoTagInner(@Param("orgId") int orgId, @Param("name") String name, @Param("status") String status, @Param("flag") String flag,
			@Param("idCard") String idCard, @Param("tagIds") String tagIds, @Param("startTime") int startTime, @Param("endTime") int endTime)
			throws DataException;

	// 修改editDocInfo
	public void editDocInfo(DocumentInfoEbo doc) throws DataException;

	// 通过ID或者Code获取DocInfo
	public DocumentInfoEbo getDocinfoByIdOrCode(@Param("prsnId") int prsnId, @Param("code") String code) throws DataException;

}
