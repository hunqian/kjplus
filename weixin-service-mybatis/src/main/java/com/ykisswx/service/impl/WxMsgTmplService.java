package com.ykisswx.service.impl;

import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybk.exception.DataException;
import com.ykisswx.dao.IWxMsgtmplDao;
import com.ykisswx.eto.WxMsgtmplUseEto;
import com.ykisswx.model.*;
import com.ykisswx.model.inner.WxMsgtmplUseInnerEbo;
import com.ykisswx.service.IWxAccountService;
import com.ykisswx.service.IWxMsgTmplService;

@Component("wxMsgTmplService")
public class WxMsgTmplService implements IWxMsgTmplService {

	@Autowired
	private IWxMsgtmplDao wxMsgtmplDao;
	@Autowired
	private IWxAccountService wxAccountService;

	// 根据tmplCode获得模板标题和内容
	public List<WxMsgtmplEbo> listTmpl(String tmplCode, String tmplTitle, int page, int paging) throws DataException {
		List<WxMsgtmplEbo> msgTmplList = wxMsgtmplDao.listTmpl(tmplCode, tmplTitle, page, paging);
		return msgTmplList;
	}

	// 根据tmplCode获得模板总数
	public int getTotalTmpl(String tmplCode, String tmplTitle) throws DataException {
		return wxMsgtmplDao.getTotalTmpl(tmplCode, tmplTitle);
	}

	// 根据tmplCode获得模板标题
	public WxMsgtmplLibEbo getTmpLib(int id, String tmplCode, String tmplTitle)throws DataException {
		WxMsgtmplLibEbo lib = wxMsgtmplDao.getTmplLib(id, tmplCode, tmplTitle);
		return lib;
	}

	// 根据tmplCode获得模板内容
	public WxMsgtmplContentEbo getTmpLContent(int id, String tmplCode) throws DataException {
		
		WxMsgtmplContentEbo content = wxMsgtmplDao.getTmplContent(id, tmplCode);
	/*	if(content == null)
			throw new DataException("content不存在");*/
		return content;
	}

	public void addTemplContent(WxMsgtmplContentEbo wxMsgtmplContentEbo) throws DataException {
		WxMsgtmplContentEbo content = getTmpLContent(0, wxMsgtmplContentEbo.getCode());
			// 已经存在
		if (content != null) {
			// 已经存在更新数据
			wxMsgtmplDao.updateTmplContent(wxMsgtmplContentEbo.getCode(), wxMsgtmplContentEbo.getContent(),
					wxMsgtmplContentEbo.getDemoSample());
		} else {
			wxMsgtmplDao.addTmplContent(wxMsgtmplContentEbo);
		}
		
	}

	// 增加或者修改模板标题
	public void addTemplLib(WxMsgtmplLibEbo wxMsgtmplLibEbo) throws DataException {
		WxMsgtmplLibEbo lib = getTmpLib(0, wxMsgtmplLibEbo.getCode(), null);
		if (lib != null) {
			// 已经存在
			wxMsgtmplDao.updateTmplLib(wxMsgtmplLibEbo.getCode(), wxMsgtmplLibEbo.getTitle(),
					wxMsgtmplLibEbo.getMainIndustry(), wxMsgtmplLibEbo.getSubIndustry());
		} else {
			// 注意此处code是否需要自动生成
			wxMsgtmplDao.addTmplLib(wxMsgtmplLibEbo);
		}
	}

	//获取日志
	public List<WxMsgtmplLogEbo> listTmplLog(int accid,int toUserid,String tmplCode,int page,int paging) throws DataException{
		WxAccountEbo wxAccountEbo = wxAccountService.getAccountInfo(accid, null);
		if(wxAccountEbo == null)
			throw new DataException("该微信公众号不存在，accid="+accid);
	/*	WxMsgtmplLibEbo wxMsgtmplLibEbo = wxMsgtmplDao.getTmplLib(0, tmplCode, null);
		if(wxMsgtmplLibEbo == null)
			throw new DataException("该微信消息模板不存在，code="+tmplCode);*/
		//TODO 用户存在判断
		List<WxMsgtmplLogEbo>  listLog= wxMsgtmplDao.listTmplLog(accid, toUserid, tmplCode, page, paging);
		return listLog;
	}

	public int getTotalTmplUse(int accId ,String tmplCode,String flag) throws DataException{
		int count = wxMsgtmplDao.getTotalTmplUse(accId, tmplCode, flag);
		return count;
	};

	
	//根据消息模板查询使用用户情况
	public List<WxMsgtmplUseInnerEbo> listTmplUse(int accId , String tmplCode, String flag,
			int page, int paging) throws DataException{
		List<WxMsgtmplUseInnerEbo> listTmpl = wxMsgtmplDao.listTmplUse(accId, tmplCode, flag, page, paging);
		return listTmpl;
	}
			
	//用户添加消息模板使用
	public void addTmplUse(WxMsgtmplUseEto wxMsgtmplUseEto) throws DataException{
		int tmplId = wxMsgtmplUseEto.getTmplId();
		WxMsgtmplLibEbo tmplLibEbo = getTmpLib(tmplId, null, null);
		if(tmplLibEbo == null)
			throw new DataException("该消息模板不存在，tmplId="+tmplId);
		WxAccountEbo wxAccountEbo =	wxAccountService.getAccountInfo(wxMsgtmplUseEto.getAccId(), null);
		if(wxAccountEbo == null)
			throw new DataException("该微信公众号不存在，accId="+wxMsgtmplUseEto.getAccId());
		WxMsgtmplUseEbo tmplUseEbo = new WxMsgtmplUseEbo();
		BeanUtils.copyProperties(wxMsgtmplUseEto, tmplUseEbo);
		tmplUseEbo.setTmplCode(tmplLibEbo.getCode());
		//TODOs 字符转换
		tmplUseEbo.setTmplId(tmplLibEbo.getId()+"");
		wxMsgtmplDao.addTmplUse(tmplUseEbo);
	}
	
	//根据tmplCode获得模板内容
	public WxMsgtmplEbo getTmpL(int libId ,int cntId, String tmplCode) throws DataException{
		
		return wxMsgtmplDao.getTmpL(libId, cntId, tmplCode);
	};
			
	
}
