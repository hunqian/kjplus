package com.kjplus.dao;

import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kjplus.model.*;
import com.kjplus.model.inner.*;
import com.ybk.exception.DataException;

@Repository("infoDao")
public interface IInfoDao {

	/**
	 * 
	 * @param id
	 *            t_info表id
	 * @param infoCode
	 *            t_info表编号
	 * @return <p>
	 *         返回CalendarEntryEbo对象
	 * @throws DataException
	 */
	public InfoEbo getInfoByIdOrCode(@Param("id") int id,
			@Param("infoCode") String infoCode) throws DataException;

	// 根据refcode
	public InfoInnerEbo getInfoByRef(@Param("refId") int refId,
			@Param("refCode") String refCode) throws DataException;

	/**
	 * 
	 * @param id
	 *            t_info_reference表id
	 * @param refCode
	 *            t_info_reference表编号
	 * @return <p>
	 *         返回CalendarEntryEbo对象
	 * @throws DataException
	 */
	public InfoReferenceEbo getInfoReferenceByIdOrCode(@Param("id") int id,
			@Param("refCode") String refCode) throws DataException;

	/**
	 * 
	 * @param infoId
	 *            t_info_reference表id
	 * @param catgId
	 *            t_info_reference表编号
	 * @return <p>
	 *         返回CalendarEntryEbo对象
	 * @throws DataException
	 */
	public InfoReferenceEbo getInfoReference(@Param("infoId") int infoId,
			@Param("catgId") int catgId, @Param("orgId") int orgId)
			throws DataException;

	/**
	 * 
	 * @param id
	 *            t_info_catglog表id
	 * @param code
	 *            t_info_catglog表info编号
	 * @return 返回InfoCatgLogEbo对象
	 * @throws DataException
	 */
	public InfoCatalogEbo getInfoCatalogByIdOrCode(@Param("id") int id,
			@Param("code") String code) throws DataException;

	

	/**
	 * 获得内容对应的内容列表，再在内容搭建（一条咨询对应的内容列表）
	 * 
	 * @param infoId
	 *            t_info表id
	 * @return
	 * @throws DataException
	 */
	public List<InfoContentEbo> listInfoContent(@Param("infoId") int infoId)
			throws DataException;

	/**
	 * （多条咨询对应的内容列表）
	 * 
	 * @param infoIds
	 *            t_info表id的list
	 * @return
	 * @throws DataException
	 */
	public List<InfoContentEbo> listInfoContentArr(
			@Param("infoIds") String infoIds) throws DataException;

	/**
	 * 
	 * @param infoCatgId
	 *            咨询类型
	 * @param flag
	 *            状态
	 * @param orgId
	 *            组织id
	 * @return 咨询列表
	 * @throws DataException
	 */
	public List<InfoInnerEbo> listInfo(@Param("infoTitle") String infoTitle,
			@Param("infoCatgId") int infoCatgId, @Param("flag") String flag,
			@Param("fetchAll") String fetchAll, @Param("orgId") int orgId,
			@Param("btime") String btime, @Param("etime") String etime,
			@Param("infoType") String infoType,
			@Param("page") int page, @Param("paging") int paging)
			throws DataException;

	public int getTotalInfo(@Param("infoTitle") String infoTitle,
			@Param("infoCatgId") int infoCatgId, @Param("flag") String flag,
			@Param("fetchAll") String fetchAll, @Param("orgId") int orgId,
			@Param("btime") String btime, @Param("etime") String etime,
			@Param("infoType") String infoType)	
			throws DataException;

	/**
	 * 
	 * @param infoCatgId
	 *            分类
	 * @param flag
	 *            ref的flag状态
	 * @param infoFlag
	 *            info的flag状态,对于在后台管理状态需要罗列出所有状的info，但在前端显示，只显示infoFlag=Y的
	 * @return 引用咨询列表
	 * @throws DataException
	 */
	public List<InfoReferenceInnerEbo> listInfoReference(
			@Param("infoId") int infoId, @Param("infoCatgId") int infoCatgId, 
			@Param("orgId") int orgId, @Param("flag") String flag, @Param("infoFlag") String infoFlag,
			@Param("page") int page, @Param("paging") int paging)
			throws DataException;

	// 获得简单的引用对象，便于进行参照显示
	public List<InfoReferenceSimpleInnerEbo> listInfoSimpleReference(
			@Param("infoTypeId") int infoTypeId, @Param("orgId") int orgId,
			@Param("flag") String flag, @Param("page") int page,
			@Param("paging") int paging) throws DataException;

	/**
	 * 
	 * @param info
	 *            传入InfoEbo对象
	 * 
	 * @throws DataException
	 */
	public void addInfo(InfoEbo info) throws DataException;

	/**
	 * 
	 * @param infoContent
	 *            传入InfoContentEbo对象
	 * @throws DataException
	 */
	public void addInfoContent(InfoContentEbo infoContent) throws DataException;

	/**
	 * 
	 * @param infoReference
	 *            传入InfoReferenceEbo对象
	 * @throws DataException
	 */
	public void addInfoReference(InfoReferenceEbo infoReference)
			throws DataException;

	/**
	 * 
	 * @param infoCatgLog
	 *            传入InfoCatgLogEbo对象
	 * @throws DataException
	 */
	public void addInfoCatalog(InfoCatalogEbo infoCatgLog) throws DataException;

	/**
	 * 
	 * @param infoReadlog
	 *            传入InfoReadlogEbo对象
	 * @throws DataException
	 */
	public void addReadlog(InfoReadlogEbo infoReadlog) throws DataException;

	/**
	 * 
	 * @param infoId
	 *            传入InfoContentEbo对象
	 * @throws DataException
	 */
	public void delInfoContent(@Param("infoId") int infoId)
			throws DataException;

	/**
	 * 
	 * @param id
	 *            t_info表主键id
	 * @param infoCode
	 *            t_info表编码
	 * 
	 * @param totalViewNum
	 *            关注数
	 * @param totalZanNum
	 *            点赞数
	 * 
	 * @param totalFocusNum
	 *            关注数
	 * @param flag
	 *            状态
	 * @throws DataException
	 */
	public void updateInfoFlag(@Param("infoId") int id,
			@Param("infoCode") String infoCode,
			@Param("totalViewNum") int totalViewNum,
			@Param("totalFocusNum") int totalFocusNum,
			@Param("totalZanNum") int totalZanNum, @Param("flag") String flag)
			throws DataException;

	/**
	 * 
	 * @param id
	 *            t_info_reference表主键id
	 * @param refCode
	 *            t_info_reference表编码
	 * @param viewNum
	 *            关注数
	 * @param zanNum
	 *            点赞数
	 * @param flag
	 *            状态
	 * @throws DataException
	 */
	public void updateReferFlag(@Param("referId") int id,
			@Param("refCode") String refCode, @Param("viewNum") int viewNum,
			@Param("zanNum") int zanNum, @Param("focusNum") int focusNum,
			@Param("flag") String flag) throws DataException;

	// 更新refer
	public void changeReferZanFocusView(@Param("referId") int referId,
			@Param("zanStatus") String zanStatus,
			@Param("focusStatus") String focusStatus,
			@Param("viewStatus") String viewStatus) throws DataException;

	/**
	 * 
	 * @param id
	 *            t_info_readlog表id
	 * @return 返回InfoReadlogEbo对象
	 * @throws DataException
	 */
	public InfoReadlogEbo getReadlogById(@Param("id") int id)
			throws DataException;

	// 更新info
	public void changeInfoZanFocusView(@Param("infoId") int infoId,
			@Param("zanStatus") String zanStatus,
			@Param("focusStatus") String focusStatus,
			@Param("viewStatus") String viewStatus) throws DataException;

	/**
	 * 
	 * @param id
	 *            阅读记录表id
	 * @param flag
	 *            阅读记录状态是否有效
	 * @param readTime
	 *            阅读时间
	 * @throws DataException
	 */
	public void changeReadlog(@Param("id") int id, @Param("flag") String flag,
			@Param("readTime") int readTime) throws DataException;

	// 获得日志列表
	public List<InfoReadlogEbo> listReadlogEbo(@Param("uid") int uid,
			@Param("mainId") int mainId, @Param("mainType") String mainType)
			throws DataException;
	
	//更新信息列表
	public void updateInfo(InfoEbo info) throws DataException;
	
	/**
	 * 
	 * @param orgId
	 *            公司id，orgid=0平台，orgid>0各社区自己的 ，
	 * @param flag
	 *            Y有效，null全部
	 * @return 返回InfoCatgLogEbo对象
	 * @throws DataException
	 */
	public List<InfoCatalogInnerEbo> listInfoCatalogByOrg(@Param("orgId") int orgId,
			@Param("pid") int pid, @Param("name") String name, @Param("flag") String flag)
			throws DataException;
	
	public int getTotalCatalogByOrg(@Param("orgId") int orgId,
			@Param("pid") int pid, @Param("name") String name, @Param("flag") String flag)
			throws DataException;
	
	
	//修改infocatalog信息
	public void updateInfoCatalog(InfoCatalogEbo infoCatalogEbo) throws DataException;	
	
}
