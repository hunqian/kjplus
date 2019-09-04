package com.ykisswx.service;

import com.ybk.exception.DataException;
import com.ykiss.wx.api.response.OauthGetTokenResponse;

//将于所有与微信API操作的服务集中在此
public interface IWxApiService {

	// 根据oauth2认证返回的code和state参数获得Oauth对象
	public OauthGetTokenResponse fetchTokenResp(int accid, String acccode, String code, String state)
			throws DataException;
}
