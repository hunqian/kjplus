package com.ykisswx.service.impl;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.cache.OptimisticTreeCache.DataVersionAdapter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ybk.basic.util.Util;

import com.ybk.exception.DataException;
import com.ykisswx.dao.*;
import com.ykisswx.dto.WxAccountDto;
import com.ykisswx.eto.WxAccountEto;
import com.ykisswx.model.*;
import com.ykisswx.service.IWxAccountService;
import com.ykisswx.util.DataValUtil;

@Service("wxAccountService")
public class WxAccountService implements IWxAccountService {

	private static Logger logger = Logger.getLogger(WxAccountService.class);

	private static Hashtable<String, Object> caches = new Hashtable<String, Object>();
	@Autowired
	private IWxAccountDao wxAccountDao;
	@Autowired
	private IWxMenuDao IWxMenuDao;

	// 根据accid获得Account信息
	public WxAccountDto getAccountInfo(int accId) throws DataException {
		if (accId <= 0)
			throw new DataException("[err]accId不能空");
		WxAccountEbo account = wxAccountDao.getAccountEboInfo(accId);
		if(account == null)
			return null;
		WxAccountDto wxacc = new WxAccountDto();
		BeanUtils.copyProperties(account, wxacc);
		return wxacc;
	}

	// 根据accid和code获取,以code获取为准
	public WxAccountEbo getAccountInfo(int accId, String accCode) throws DataException {
		WxAccountEbo acc = getAccountByAccCode(accCode);
		if (acc != null)
			return acc;
		if (accId <= 0)
			return null;
		return wxAccountDao.getAccountEboInfo(accId);
	}

	// 根据sesscode获得account
	public WxAccountEbo getAccountInfo(String sess) throws DataException {
		if (Util.isNull(sess))
			return null;

		return wxAccountDao.getAccountInfoByCode(null, sess);
	}

	// 增加一个acc
	public WxAccountEbo addAccountInfo(WxAccountEto acct) throws DataException {
		//空值验证
		DataValUtil.dataValidation(acct.getClass(), acct);
		//判断appid是否存在
		WxAccountEbo appidObject = wxAccountDao.getAccountByAppid(acct.getAppid());
		if (appidObject != null)
			throw new DataException("[err]appid对应的acc对象已经存在!");
		
		// 产生随机accCode
		String accCode = Util.genAuthCode(WxAccountEto.MAX_ACCCODE_LEN);
		WxAccountEbo accCodeObject = getAccountByAccCode(accCode);
		while (accCodeObject != null) {
			accCode = Util.genAuthCode(WxAccountEto.MAX_ACCCODE_LEN);
			accCodeObject = getAccountByAccCode(accCode);
		}

		// 产生sessCode
		String sessCode = Util.genAuthCode(WxAccountEto.MAX_SEECODE_LEN);
		WxAccountEbo sessCodeObject = wxAccountDao.getAccountInfoByCode(null, sessCode);
		while (sessCodeObject != null) {
			sessCode = Util.genAuthCode(WxAccountEto.MAX_SEECODE_LEN);
			sessCodeObject = wxAccountDao.getAccountInfoByCode(null, sessCode);
		}
		
		//产生随机token
		String token = Util.genAuthCode(WxAccountEto.MAX_TOKEN_LEN);
		WxAccountEbo acc2 = wxAccountDao.getAccountInfoByCode(token, null);
		while (acc2 != null) {
			token = Util.genAuthCode(WxAccountEto.MAX_TOKEN_LEN);
			acc2 = wxAccountDao.getAccountInfoByCode(token, null);
		}
		
		WxAccountEbo acc = new WxAccountEbo();
		try {
			BeanUtils.copyProperties(acct, acc);
			acc.setAccCode(accCode);
			acc.setSessCode(sessCode);
			acc.setToken(token);
			wxAccountDao.addWxAccount(acc);
			return acc;
		} catch (BeansException iae) {
			logger.error(iae);
		}
		return null;
	}

	// 获得当前微信对应的orgid
	public int getOrgid(int accid, String appid) throws DataException {
		if (accid <= 0 || Util.isNull(appid))
			throw new DataException("[err]至少指定accid获得appid参数值!");

		return wxAccountDao.getOrgid(accid, appid);
	}

	// 获得当前微信对应的orgid
	public int getOrgidByOpenid(String openid) throws DataException {
		if (Util.isNull(openid))
			throw new DataException("[err]空openid");

		return wxAccountDao.getOrgidByOpenid(openid);
	}

	// 根据appid获得accid
	public int getAccId(String appid) throws DataException {
		String key = "appid." + appid;
		if (caches.containsKey(key)) {
			Integer accid = (Integer) caches.get(key);
			return accid.intValue();
		}
		// 查询
		WxAccountEbo account = wxAccountDao.getAccountByAppid(appid);
		if (account != null) {
			Integer accid = account.getId();
			caches.put(key, accid);
			return accid.intValue();
		}
		return 0;
	}

	// 根据accode获得accid
	public int getAccIdByCode(String accCode) throws DataException {
		String key = "accode." + accCode;
		if (caches.containsKey(key)) {
			Integer accid = (Integer) caches.get(key);
			return accid.intValue();
		}
		// 查询
		WxAccountEbo account = wxAccountDao.getAccountByAccCode(accCode);
		if (account != null) {
			Integer accid = account.getId();
			caches.put(key, accid);
			return accid.intValue();
		}
		return 0;
	}

	public WxAccountEbo getAccountByAccCode(String accCode) throws DataException {

		if (Util.isNull(accCode))
			return null;
		else
			return wxAccountDao.getAccountByAccCode(accCode);

	}

	// 根据orgiid获得accid
	public List<WxAccountEbo> listAccountIdByOrgid(int orgid) throws DataException{
		//空值判断
		if (orgid <= 0)
			return null;
		// 查询
		List<WxAccountEbo> accs= wxAccountDao.listAccountIdByOrgid(orgid);
		return accs;
	}

	// 根据orgiid获得acc对象
	/*public WxAccountEbo getAccByOrgid(int orgid) throws DataException {
		if (orgid <= 0)
			throw new DataException("[err]orgid不能空");
		return wxAccountDao.getAccountIdByOrgid(orgid);
	}*/

	public List<WxAccountDto> listAccount(int orgid, String account, String type, int page, int paging) throws DataException {

		List<WxAccountEbo> accountList = wxAccountDao.listAccount(orgid, account, type, page, paging);
		List<WxAccountDto> accs = new ArrayList<WxAccountDto>();
		try {
			for (WxAccountEbo acc : accountList) {
				WxAccountDto a = new WxAccountDto();
				BeanUtils.copyProperties(acc, a);
				a.setAccid(acc.getId());
				accs.add(a);
			}
		} catch (BeansException be) {
			logger.error(be);

		}
		return accs;
	}

	public Integer getTotalAccount(int orgid, String name, String type) throws DataException {
		return wxAccountDao.getTotalAccount(orgid, name, type);
	}

	// 修改一个acc
	public void editAccAccount(Integer accid, WxAccountEto wxAccountEto) throws DataException {
		if (accid == 0) {
			throw new DataException("accid不能为空！");
		}
		wxAccountDao.editAccount(accid,wxAccountEto);
	}

	// 获得欢迎内容 TODO:无微信欢迎表待实现
}
