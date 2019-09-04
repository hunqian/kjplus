package com.ykisswx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ybk.basic.util.Util;

import com.ybk.exception.DataException;
import com.ykisswx.dto.WxToken;
import com.ykisswx.model.WxAccountEbo;
import com.ykisswx.service.IWxAccountService;
import com.ykisswx.service.IWxMgrService;

/**
 * 微信主信息管理入口
 * 
 * @author lzf<br/>
 *         2010-4-30上午10:14:35<br/>
 *         version 1.0
 */

@Component("wxMgrService")
public class WxMgrService implements IWxMgrService {

	@Autowired
	private IWxAccountService wxAccountService;

	public WxToken getToken(int accid) throws DataException {

		WxAccountEbo account = wxAccountService.getAccountInfo(accid, null);

		if (account == null)
			return null;
		else {
			WxToken token = new WxToken();
			token.setAppid(account.getAppid());
			token.setSecret(account.getAppsecret());
			return token;
		}
	}

	// 保留原来的方法，增加次方法调用
	public WxToken getToken(int accid, String acccode) throws DataException {
		if (accid <= 0 && Util.isNull(acccode))
			return null;
		WxAccountEbo account = wxAccountService.getAccountInfo(accid, acccode);

		if (account == null)
			return null;
		else {
			WxToken token = new WxToken();
			token.setAppid(account.getAppid());
			token.setSecret(account.getAppsecret());
			return token;
		}
	}
}
