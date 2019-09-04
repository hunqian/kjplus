package com.ykisswx.service.impl;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ybk.basic.util.DateUtil;
import org.ybk.basic.util.Util;

import com.ybk.exception.DataException;
import com.ykisswx.dao.*;
import com.ykisswx.dto.WxAccountDto;
import com.ykisswx.dto.WxTextMsgDto;
import com.ykisswx.eto.WxAccountEto;
import com.ykisswx.eto.WxTextMsgEto;
import com.ykisswx.model.*;
import com.ykisswx.model.inner.WxTextMsgInnerEbo;
import com.ykisswx.service.IWxAccountService;
import com.ykisswx.service.IWxTextMsgService;

@Service("wxTextMsgService")
public class WxTextMsgService implements IWxTextMsgService {

	private static Logger logger = Logger.getLogger(WxTextMsgService.class);
	@Autowired
	private IWxTextMsgDao wxTextMsgDao;
	
	//获取消息文本列表数据
	public List<WxTextMsgDto> listTextMsg(int accid, int userid, String msg, String queryDay,int page, int paging) throws DataException {
		
		int btime = 0;
		int etime = 0;
		if(Util.isNotNull(queryDay)){
			btime = DateUtil.parseTimeStrInSec(queryDay + " 00:00:01");
			etime = DateUtil.parseTimeStrInSec(queryDay + " 23:59:59");
		}
		List<WxTextMsgInnerEbo> textMsgList = wxTextMsgDao.listTextMsg(accid, userid, msg, btime, etime, page, paging);
		List<WxTextMsgDto> accs = new ArrayList<WxTextMsgDto>();
		try {
			for (WxTextMsgInnerEbo m : textMsgList) {
				WxTextMsgDto a = new WxTextMsgDto();
				BeanUtils.copyProperties(m, a);
				a.setAccId(m.getAccid());
				a.setUserId(m.getUserid());
				a.setNickName(m.getNickname());
				a.setAccount(m.getAccount());
				accs.add(a);
			}
		} catch (BeansException be) {
			logger.error(be);
		}
		return accs;
	}
	//获取消息文本列表数据总数
	public Integer getTotalTextMsg(int accid, int userid, String msg, String queryDay) throws DataException {
		int btime = 0;
		int etime = 0;
		if(Util.isNotNull(queryDay)){
			btime = DateUtil.parseTimeStrInSec(queryDay + " 00:00:00");
			etime = DateUtil.parseTimeStrInSec(queryDay + " 23:59:59");
		}
		return wxTextMsgDao.getTotalTextMsg(accid, userid, msg, btime, etime);
	}
	
	// 增加一个TextMsg
	public WxTextMsgInnerEbo addAccountInfo(WxTextMsgEto msg) throws DataException {

		if (msg == null){
			throw new DataException("[err]msg对象为空!");
		}
		
		WxTextMsgInnerEbo m = new WxTextMsgInnerEbo();
		try {
			BeanUtils.copyProperties(msg, m);
			wxTextMsgDao.addWxTextMsg(m);
			return m;
		} catch (BeansException iae) {
			logger.error(iae);
		}
		return null;
	}
}
