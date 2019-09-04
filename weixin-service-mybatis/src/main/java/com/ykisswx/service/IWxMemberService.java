package com.ykisswx.service;

import com.ybk.exception.DataException;
import com.ykisswx.model.WxMemberEbo;

public interface IWxMemberService {

	// 根据mid获得member
	public WxMemberEbo getMemberByMid(int mid) throws DataException;

}
