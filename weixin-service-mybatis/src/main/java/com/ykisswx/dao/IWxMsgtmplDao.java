package com.ykisswx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ybk.exception.DataException;
import com.ykisswx.model.WxMsgtmplContentEbo;
import com.ykisswx.model.WxMsgtmplEbo;
import com.ykisswx.model.WxMsgtmplLibEbo;
import com.ykisswx.model.WxMsgtmplLogEbo;
import com.ykisswx.model.WxMsgtmplUseEbo;
import com.ykisswx.model.inner.WxMsgtmplUseInnerEbo;

@Repository("wxMsgtmplDao")
public interface IWxMsgtmplDao{

	//根据tmplCode获得模板标题和内容
	public List<WxMsgtmplEbo> listTmpl(@Param("tmplCode") String tmplCode ,@Param("tmplTitle") String tmplTitle ,
			@Param("page") int page,@Param("paging") int paging) throws DataException;
	
	//总数
	public int getTotalTmpl(@Param("tmplCode") String tmplCode , @Param("tmplTitle") String tmplTitle) throws DataException;
	
	//根据tmplCode获得模板标题，是获取到具体的名字，而不是类似的，尤其在修改添加时
	public WxMsgtmplLibEbo getTmplLib(@Param("id") int id ,@Param("tmplCode") String tmplCode 
			, @Param("tmplTitle") String tmplTitle) throws DataException;
	
	//根据tmplCode获得模板内容，是获取到具体的名字，而不是类似的，尤其在修改添加时
	public WxMsgtmplContentEbo getTmplContent(@Param("id") int id ,@Param("tmplCode") String tmplCode) throws DataException;
		
	//根据tmplCode获得模板内容
	public WxMsgtmplEbo getTmpL(@Param("libId") int libId ,@Param("cntId") int cntId, @Param("tmplCode") String tmplCode) throws DataException;
			
	
	//更改模板内容
	public void updateTmplContent(@Param("tmplCode") String tmplCode,@Param("content") String content
			,@Param("demo") String demo) throws DataException;
	
	//增加模板内容
	public void addTmplContent(WxMsgtmplContentEbo wxMsgtmplContentEbo) throws DataException;

	//更改标题内容
	public void updateTmplLib(@Param("tmplCode") String tmplCode,@Param("title") String title
			,@Param("mainIndustry") String mainIndustry,@Param("subIndustry") String subIndustry) throws DataException;
	
	//增加标题内容
	public void addTmplLib(WxMsgtmplLibEbo msgtmplLibEbo) throws DataException;

	//获取日志
	public List<WxMsgtmplLogEbo> listTmplLog(@Param("accid") int accid,@Param("toUserid") int toUserid
			,@Param("tmplCode") String tmplCode, @Param("page") int page, @Param("paging") int paging) throws DataException;
	
	//根据微信公众号id获取使用消息模板情况
	public List<WxMsgtmplUseInnerEbo> listTmplUse(@Param("accId") int accId , @Param("tmplCode") String tmplCode,
			@Param("flag") String flag,
			@Param("page") int page,@Param("paging") int paging) throws DataException;

	//根据微信公众号id获取使用消息模板情况
	public int getTotalTmplUse(@Param("accId") int accId , @Param("tmplCode") String tmplCode,
			@Param("flag") String flag) throws DataException;

	
	//用户添加消息模板使用
	public void addTmplUse(WxMsgtmplUseEbo wxMsgtmplUseEbo) throws DataException;
	
}
