package com.ykisswx.service;

import com.ybk.exception.DataException;
import com.ykisswx.dto.WxUserInfoNnDto;
import com.ykisswx.model.WxUserInfoEbo;

import java.util.List;

public interface IWxUserInfoService {

	// 获取wid对应的accid
	public int getAccid(int wxUserid) throws DataException;

	//获取组织下的所有微信用户
	public List<WxUserInfoNnDto> listUserByOrg(String nickname, int orgid, int page, int paging) throws DataException;

	//获取总数
	public int getTotal(String nickname, int orgid) throws DataException;
	
	// 通过uid查询用户
	public WxUserInfoEbo getWxUserById(int uid) throws DataException;

	// 上传用户头像
	public void uploadFace(int uid,String face) throws DataException;
}
