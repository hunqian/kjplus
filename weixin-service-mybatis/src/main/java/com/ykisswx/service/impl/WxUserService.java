package com.ykisswx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybk.exception.DataException;
import com.ykisswx.dao.*;
import com.ykisswx.model.*;
import com.ykisswx.service.IWxUserService;

@Component("wxUserService")
public class WxUserService implements IWxUserService {

	@Autowired
	private IWxUserInfoDao wxUserInfoDao;

	// 根据微信用户的OpenId获取对应的memberUserId号
	public int getMemberUserId(String openId) throws DataException {

		WxUserInfoEbo user = wxUserInfoDao.getUserInfoByOpenid(openId);
		if (user == null)
			return -1;
		else {
			return (Integer) user.getMemberId();
		}
	}
}
