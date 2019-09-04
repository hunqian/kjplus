package com.kjplus.service;

import java.util.*;

import com.kjplus.eto.DocInfoEto;
import com.kjplus.model.*;
import com.kjplus.model.inner.DocumentInfoInnerEbo;
import com.ybk.exception.DataException;

public interface IDocInfoService {

	// 添加一个docInfo
	public DocumentInfoEbo addDocInfo(DocInfoEto docEto) throws DataException;

	// 查询一个orgid中的idNumber对应的
	public List<DocumentInfoEbo> listDocInfo(int orgId, String prsnCode, String idNumber) throws DataException;

	// 罗列组合 isEffect 查询签约时 是否查询有效记录
	public List<DocumentInfoInnerEbo> listDocInfoInner(int orgId, String name, String status, String flag, String idCard, List<Integer> tagIds,
			boolean isEffect, int startTime, int endTime, int page, int paging) throws DataException;

	// 获得总数
	public int getTotalDocInfoInner(int orgId, String name, String status, String flag, String idCard, List<Integer> tagIds, int startTime, int endTime)
			throws DataException;

	// 修改建档信息
	public void updateDocInfo(DocumentInfoEbo dInfoEbo) throws DataException;

	// 通过ID或者Code获取DocInfo
	public DocumentInfoEbo getDocinfoByIdOrCode(int id, String code) throws DataException;

}
