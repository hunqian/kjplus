package com.ykisswx.service.impl;

import com.ykisswx.dto.WxUserInfoNnDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybk.exception.DataException;
import com.ykisswx.dao.IWxUserInfoDao;
import com.ykisswx.model.WxUserInfoEbo;
import com.ykisswx.service.IWxUserInfoService;

import java.util.ArrayList;
import java.util.List;

@Component("wxUserInfoService")
public class WxUserInfoService implements IWxUserInfoService {

	@Autowired
	private IWxUserInfoDao wxUserInfoDao;

	// 获取wid对应的accid
	public int getAccid(int wxUserid) throws DataException {
		if (wxUserid <= 0)
			return -1;
		int wid = wxUserInfoDao.getAccid(wxUserid);
		if (wid > 0)
			return wid;
		else
			return -1;
	}

	// 查询组织下的所有微信用户
	public List<WxUserInfoNnDto> listUserByOrg(String nickname, int orgid, int page, int paging) throws DataException {
		List<WxUserInfoNnDto> infos = wxUserInfoDao.listUserByOrg(nickname, orgid, page, paging);
		return infos;
	}

	public int getTotal(String nickname, int orgid) throws DataException {

		return wxUserInfoDao.getTotal(nickname, orgid);
	}
	
	//通过ID获取微信用户信息
	public WxUserInfoEbo getWxUserById(int uid) throws DataException {
		if (uid <= 0)
			return null;
		return wxUserInfoDao.getWxUserById(uid);
	}
	
	//上传头像
	public void uploadFace(int uid, String face) throws DataException {
		// TODO Auto-generated method stub
		wxUserInfoDao.uploadFace(uid,face);
	}
	
	
}
