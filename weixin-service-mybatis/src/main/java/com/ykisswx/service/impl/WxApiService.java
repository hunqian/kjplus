package com.ykisswx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ybk.basic.util.Util;
import com.ybk.exception.DataException;
import com.ykiss.wx.api.config.ApiConfig;
import com.ykiss.wx.api.response.OauthGetTokenResponse;
import com.ykisswx.dto.WxToken;
import com.ykisswx.service.IWxApiService;
import com.ykisswx.service.IWxMgrService;

@Component("wxApiService")
public class WxApiService implements IWxApiService {

	@Autowired
	private IWxMgrService wxMgrService;

	/*
	 * @Autowired private IAPIStatics wxApiRecService;
	 */
	// 根据oauth2认证返回的code和state参数获得Oauth对象
	public OauthGetTokenResponse fetchTokenResp(int accid, String acccode, String code, String state)
			throws DataException {
		if (Util.isNull(code)) {
			throw new DataException("[err]code参数不能为空!");
		}

		WxToken token = wxMgrService.getToken(accid, acccode);

		// 如果没有找到
		if (token == null) {
			throw new DataException("[err]为找到对应accid=" + accid + "的Token!");
		}

		ApiConfig config = new ApiConfig(token.getAppid(), token.getSecret());
		/* OauthAPI oauthAPI = new OauthAPI(config, wxApiRecService); */
		/* OauthGetTokenResponse tokenResp = oauthAPI.getToken(code); */

		/*
		 * String errMsg = tokenResp.getErrmsg(); if (Util.isNotNull(errMsg) &&
		 * errMsg.contains("不合法")) { throw new DataException("[err]当前操作非法,err="
		 * + errMsg); }
		 */
		return null;

	}
}
