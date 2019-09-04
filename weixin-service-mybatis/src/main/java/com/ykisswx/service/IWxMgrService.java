package com.ykisswx.service;

import com.ybk.exception.DataException;
import com.ykisswx.dto.WxToken;

//微信数据访问接口
public interface IWxMgrService {

	// 根据accid获得token
	public WxToken getToken(int accid) throws DataException;

	// 保留原来的方法，增加次方法调用
	public WxToken getToken(int accid, String acccode) throws DataException;

}
