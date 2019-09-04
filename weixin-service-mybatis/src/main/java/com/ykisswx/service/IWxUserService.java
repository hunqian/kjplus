package com.ykisswx.service;

import com.ybk.exception.DataException;

public interface IWxUserService {

	public int getMemberUserId(String openId) throws DataException;

}
