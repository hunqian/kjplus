package com.kjplus.dao;

import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kjplus.model.*;
import com.kjplus.model.inner.*;
import com.ybk.exception.DataException;

@Repository("richContentDao")
public interface IRichContentDao {
	/**
	 * 通过id或者Code获取通用内容
	 * 
	 * @param mainId
	 * @param mainTypeCode
	 * @return
	 * @throws DataException
	 */
	public RichContentEbo getContentByIdOrCode(@Param("id") int id, @Param("code") String code) throws DataException;

	/**
	 * 查询通用内容
	 * 
	 * @param id
	 * @param mainId
	 * @param mainTypeCode
	 * @param title
	 * @return
	 * @throws DataException
	 */
	public List<RichContentInnerEbo> listContent(@Param("mainTypeCode") String mainTypeCode,@Param("orgId") int orgId,
			@Param("title") String title, @Param("page") int page,@Param("paging") int paging) throws DataException;

	/**
	 * 添加通用内容信息
	 * 
	 * @param norContent
	 * @throws DataException
	 */
	public void addContent(RichContentEbo contentEbo) throws DataException;

	/**
	 * 修改通用内容信息
	 * 
	 * @param content
	 * @throws DataException
	 */
	public void updateContent(RichContentEbo content) throws DataException;

	/**
	 * 获取通用内容数据总数
	 * 
	 * @param mainId
	 *            对应组织、部门、医生的编号
	 * @param mainTypeCode
	 *            对应组织（ORG）、部门、医生
	 * @param title
	 * @return
	 * @throws DataException
	 */
	public int getTotalContent(@Param("mainTypeCode") String mainTypeCode,@Param("orgId") int orgId,
			@Param("title") String title) throws DataException;

	/**
	 * 通过mainID,mainTypeCode获取通用内容
	 * 
	 * @param mainId
	 * @param mainTypeCode
	 * @return
	 * @throws DataException
	 */
	public RichContentEbo getContentByMainIdAndMainType(@Param("mainId") int mainId,
			@Param("mainTypeCode") String mainTypeCode) throws DataException;

}
