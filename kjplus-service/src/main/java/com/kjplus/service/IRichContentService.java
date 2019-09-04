package com.kjplus.service;

import java.util.*;

import com.kjplus.dto.*;
import com.kjplus.eto.RichContentEto;
import com.kjplus.model.*;
import com.ybk.exception.DataException;

public interface IRichContentService {

	// content内容长度,8K
	public static final int CONTENT_LEN = 80;

	// 根据ID，Code获得通用内容信息
	public RichContentEbo getContentByIdOrCode(int id,String Code) throws DataException;
	
	// 添加通用内容信息
	public void addContent(RichContentEto norContent) throws DataException;
	
	//获取通用内容列表
	public List<RichContentDto> listContent(String mainTypeCode,int orgId,String title,int page,int paging) throws DataException;
	
	// 修改通用内容信息
	public void updateContent(RichContentEbo norContent) throws DataException;	

	// 上传轮播图s
	//public void addNorContentPic(int mainId, String mainTypeCode) throws DataException;
	
	//获取通用内容数据总数
	public int getTotalContent(String mainTypeCode,int orgId,String title) throws DataException;	
	
	// 根据ID，Code获得通用内容信息
	public RichContentEbo getContentByMainIdType(int mainId,String MainTypeCode) throws DataException;
}
