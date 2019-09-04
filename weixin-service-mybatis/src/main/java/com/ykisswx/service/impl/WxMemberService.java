package com.ykisswx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybk.exception.DataException;
import com.ykisswx.dao.IWxMemberDao;
import com.ykisswx.model.WxMemberEbo;
import com.ykisswx.service.IWxMemberService;

@Component("wxMemberService")
public class WxMemberService implements IWxMemberService {

	@Autowired
	private IWxMemberDao wxMemberDao;

	// 根据mid获得member
	public WxMemberEbo getMemberByMid(int mid) throws DataException {

		WxMemberEbo member = wxMemberDao.getMemberByMid(mid);
		if (member != null)
			return member;
		else
			return null;
	}

}
