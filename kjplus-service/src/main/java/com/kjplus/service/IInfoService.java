package com.kjplus.service;

import java.util.*;


import com.kjplus.dto.*;
import com.kjplus.eto.*;
import com.kjplus.model.*;
import com.kjplus.model.inner.InfoInnerEbo;
import com.ybk.exception.DataException;

public interface IInfoService {

	// content内容长度,8K
	public static final int CONTENT_LEN = 80;

	//
	public InfoEbo getInfoByIdOrCode(int id, String infoCode) throws DataException;

	// 获得InfoDTo
	public InfoDto getInfoDto(int id) throws DataException;

	public InfoReferenceEbo getReferenceByIdOrCode(int id, String refCode) throws DataException;

	public InfoCatalogEbo getCatalogByIdOrCode(int id, String code) throws DataException;

	// 添加咨询
	public InfoEbo addInfo(InfoEto info) throws DataException;

	// 添加咨询分类
	public InfoCatalogEbo addCatalog(InfoCatalogEto infoCatalog) throws DataException;

	// 列表一个组织下的分类
	//如果指定了pid=0，则只获得顶级的
	public List<InfoCatalogDto> listCatalog(int orgid, int pid,String name, String flag) throws DataException;

	public int getTotalCatalogByOrg(int orgid, int pid ,String name ,String flag) throws DataException;
	
	// 列表一个组织下的分类,返回一个树状
	public List<InfoCatalogDto> listCatalog(int orgid, int pid,String name, String flag, boolean treeStyle, boolean childNearStyle) throws DataException;
	
	// 添加内容
	public void addInfoContent(int infoId, String content) throws DataException;

	// 通过id获得内容
	public String getInfoContent(int infoId) throws DataException;

	// 一次性获得或多条内容
	public List<InfoContentDto> listInfoContents(List<Integer> infoIds) throws DataException;

	// 添加内容索引
	public InfoReferenceEbo addReference(InfoReferenceEto refer) throws DataException;

	// 添加引用
	// infoId资讯id，catgId分类id
	public InfoReferenceEbo addReference(int infoId, int catgId, int orgId) throws DataException;

	// 访问新闻
	public void addInfoVisit(String refCode) throws DataException;

	// 点赞新闻
	// 在点赞中需要有是否已经点赞的判断
	// TODO:此方法暂时不用
	public void addInfoZan(String refCode, int uid, String status, String type) throws DataException;

	// 列表info
	public List<InfoDto> listInfo(String infoTitle, int infoCatgId, String flag, String fetchAll, int orgid,String queryDay,
			boolean withContent,String infoType, int startPage, int endPage) throws DataException;

	// 获取info数据总数
	public int getTotalInfo(String infoTitle, int infoCatgId, String flag, String fetchAll, int orgid,String queryDay,
			String infoType)
			throws DataException;

	// 列表infoReference
	public List<InfoReferenceDto> listInfoReference(int infoId, int infoCatgId, int orgid, String flag, 
			String infoFlag, int page, int paging) throws DataException;

	// 通过id获取InfoReadlogEbo对象
	public InfoReadlogEbo getReadlogById(int id) throws DataException;

	// 添加阅读记录
	public InfoReadlogEbo addSimpleReadlog(InfoReadlogEto infoReadlog) throws DataException;

	// 添加阅读记录
	public InfoReadlogEbo addReadlog(InfoReadlogEto infoReadlog) throws DataException;

	// 修改阅读时间
	public void changeReadTimeOrFlag(int id, String flag, int readTime) throws DataException;

	// 修改info列表信息
	public void updateInfo(InfoEbo info) throws DataException;	
	
	//修改infocatalog信息
	public void updateInfoCatalog(InfoCatalogEbo infoCatalogEbo) throws DataException;	

	public InfoInnerEbo getInfoByRef(int refId, String refCode) throws DataException;
}
