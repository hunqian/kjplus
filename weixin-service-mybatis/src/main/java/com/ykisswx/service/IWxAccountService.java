package com.ykisswx.service;


import java.util.List;

import com.ybk.exception.DataException;
import com.ykisswx.dto.WxAccountDto;
import com.ykisswx.eto.WxAccountEto;
import com.ykisswx.model.*;


public interface IWxAccountService {
	
	//获取Account的id与名称
	public List<WxAccountDto> listAccount(int orgid, String account, String type, int page, int paging) throws DataException;
	
	//根据accid获得Account信息
	public Integer getTotalAccount(int orgid, String name, String type) throws DataException;

	//根据accid和code获取,以code获取为准
	public WxAccountEbo getAccountInfo(int accId,String accCode) throws DataException;

	//根据acccode获得account
	public WxAccountEbo getAccountByAccCode(String accCode)throws DataException;

	//根据sesscode获得account
	public WxAccountEbo getAccountInfo(String sess) throws DataException;
	
	//增加一个acc
	public WxAccountEbo addAccountInfo(WxAccountEto acct) throws DataException;
	
	//修改一个acc
	public void editAccAccount(Integer accid, WxAccountEto wxAccountEto) throws DataException;
	
	//根据appid获得accid
	public int getAccId(String appid) throws DataException;

	//根据accode获得accid
	public int getAccIdByCode(String accCode) throws DataException;

	//根据orgiid获得acc对象
/*	public WxAccountEbo getAccByOrgid(int orgid) throws DataException;*/
	
	//根据orgiid获得accid
	public List<WxAccountEbo> listAccountIdByOrgid(int orgid) throws DataException;

	//获得当前微信对应的orgid
	public int getOrgid(int accid,String appid) throws DataException;
	
	//获得当前微信对应的orgid
	public int getOrgidByOpenid(String openid) throws DataException;
	
	//获得欢迎内容
	//public List<WxWelcomeEbo> listWelcome(int accid, String tag) throws DataException;
}
