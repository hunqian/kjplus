package com.ykisswx.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ybk.exception.DataException;
import com.ykisswx.eto.WxMsgtmplUseEto;
import com.ykisswx.model.WxMsgtmplContentEbo;
import com.ykisswx.model.WxMsgtmplEbo;
import com.ykisswx.model.WxMsgtmplLibEbo;
import com.ykisswx.model.WxMsgtmplLogEbo;
import com.ykisswx.model.WxMsgtmplUseEbo;
import com.ykisswx.model.inner.WxMsgtmplUseInnerEbo;

public interface IWxMsgTmplService {
	
	//根据tmplCode获得模板标题和内容
	public List<WxMsgtmplEbo> listTmpl(String tmlCode,String tmplTitle, int page,int paging) throws DataException;
	
	//根据tmplCode获得模板总数
	public int getTotalTmpl(String tmplCode,String tmplTitle) throws DataException ;
	
	//根据tmplCode获得模板标题
	public WxMsgtmplLibEbo getTmpLib(int id ,String tmplCode,String tmplTitle) throws DataException;
	
	//根据tmplCode获得模板内容
	public WxMsgtmplContentEbo getTmpLContent(int id ,String tmplCode) throws DataException;
	
	//根据tmplCode获得模板内容
	public WxMsgtmplEbo getTmpL(int libId ,int cntId, String tmplCode) throws DataException;
		
	
	
	//增加或者修改模板内容
	public void addTemplContent(WxMsgtmplContentEbo wxMsgtmplContentEbo) throws DataException;
	
	//增加或者修改模板标题
	public void addTemplLib(WxMsgtmplLibEbo wxMsgtmplLibEbo) throws DataException;
		
	//获取日志
	public List<WxMsgtmplLogEbo> listTmplLog(int accid,int toUserid,String tmplCode,int page,int paging) throws DataException;

	//根据消息模板查询使用用户情况
	public List<WxMsgtmplUseInnerEbo> listTmplUse(int accId , String tmplCode, String flag,
			int page, int paging) throws DataException;

	public int getTotalTmplUse(int accId ,String tmplCode,String flag) throws DataException;

	//用户添加消息模板使用
	public void addTmplUse(WxMsgtmplUseEto wxMsgtmplUseEto) throws DataException;
		
	
}
