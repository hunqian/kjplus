package com.ykisswx.service;

import java.util.List;

import com.ybk.exception.DataException;
import com.ykisswx.dto.WxTextMsgDto;
import com.ykisswx.eto.WxTextMsgEto;
import com.ykisswx.model.inner.WxTextMsgInnerEbo;

public interface IWxTextMsgService {
	
	// 获取微信消息文本列表
	public List<WxTextMsgDto> listTextMsg(int accid, int userid, String msg, String queryDay, int page, int paging) throws DataException;

	// 获取总数
	public Integer getTotalTextMsg(int accid, int userid, String msg, String queryDay) throws DataException;
	
	//添加消息文本
	public WxTextMsgInnerEbo addAccountInfo(WxTextMsgEto msg) throws DataException;
}
