package com.kjplus.service.impl;

import java.util.List;

import com.kjplus.dao.ISysAppKeyDao;
import com.kjplus.dto.TokenUserOrgDto;
import com.kjplus.model.SysAppKeyEbo;
import com.kjplus.service.IAppKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ybk.basic.util.DateUtil;
import org.ybk.basic.util.Util;

import com.kjplus.constant.Constant;
import com.ybk.exception.DataException;

@Component("appKeyService")
public class AppKeyService implements IAppKeyService {

	@Autowired
	private ISysAppKeyDao keyDao;

	private static final int MAX_TOKEN_LEN = 32;

	public SysAppKeyEbo getKey(String token) throws DataException {
		if (Util.isNull(token))
			return null;
		List<SysAppKeyEbo> keys = keyDao.getKey(token, Constant.FLAG_YES);
		if (keys.size() <= 0)
			return null;
		else
			return keys.get(0);
	}

	// 自定义获取token是否有效 可能包含多个 直接取列表对象
	public List<SysAppKeyEbo> getKey(String token, String flag) throws DataException {
		if (Util.isNull(token))
			return null;
		List<SysAppKeyEbo> keys = keyDao.getKey(token, flag);
		if (keys.size() <= 0)
			return null;
		else
			return keys;
	}

	// 根据openid获得
	public SysAppKeyEbo getKeyByOpenid(String openid) throws DataException {
		if (Util.isNull(openid))
			return null;
		List<SysAppKeyEbo> keys = keyDao.getKeyByOpenid(openid, Constant.FLAG_YES);
		if (keys.size() <= 0)
			return null;
		else
			return keys.get(0);
	}

	public SysAppKeyEbo getKeyByUid(int uid, String flag) throws DataException {
		if (uid <= 0)
			return null;
		else
			return keyDao.getKeyByUid(uid, flag);
	}

	// 自定义获取openid是否有效 可能包含多个 直接取列表对象
	public List<SysAppKeyEbo> getKeyByOpenid(String openid, String flag) throws DataException {
		if (Util.isNull(openid))
			return null;
		List<SysAppKeyEbo> keys = keyDao.getKeyByOpenid(openid, flag);
		if (keys.size() <= 0)
			return null;
		else
			return keys;
	}

	// 获得openid的数量
	public Integer getKeyOpenidNum(String openid) throws DataException {

		return keyDao.getKeyOpenidNum(openid);
	}

	public SysAppKeyEbo addKey(String openid, int uid, int orgId) throws DataException {

		if (Util.isNull(openid))
			return null;
		// 获得数量
		Integer t = getKeyOpenidNum(openid);
		String token = Util.genSecID(MAX_TOKEN_LEN);
		SysAppKeyEbo k2 = getKey(token);
		while (k2 != null) {
			token = Util.genSecID(MAX_TOKEN_LEN);
			k2 = getKey(token);
		}
		SysAppKeyEbo k = new SysAppKeyEbo();
		k.setOpenid(openid);
		// 色织token的过期时长为30分钟
		k.setLastVisitTime(DateUtil.getCurTimeInSec());
		k.setOpenSeq(t.intValue() + 1);
		k.setOpentoken(token);
		k.setVisitNum(0);
		if (t.intValue() == 0) {
			k.setVisitOrgid(orgId);
			k.setVisitUid(uid);
		} else {
			List<SysAppKeyEbo> keys = getKeyByOpenid(openid, null);
			if (keys.size() > 0) {// keys是否存在 如果存在则直接取存在的
				SysAppKeyEbo mk2 = keys.get(0);
				k.setVisitOrgid(mk2.getVisitOrgid());
				k.setVisitUid(mk2.getVisitUid());
			} else {// 否则默认选方法中传入的
				k.setVisitOrgid(orgId);
				k.setVisitUid(uid);
			}
		}
		k.setFlag(Constant.FLAG_YES);
		k.setCreateTime(DateUtil.getCurTimeInSec());
		keyDao.addKey(k);
		return k;
	}

	public boolean checkOpenid(String openid, int beatTimeInSec, boolean autoReg) throws DataException {
		SysAppKeyEbo key = getKeyByOpenid(openid);
		if (key == null) {
			return false;
		} else {
			return checkToken(key.getOpentoken(), beatTimeInSec, autoReg);
		}
	}

	public boolean checkToken(String token, int beatTimeInSec, boolean autoReg) throws DataException {
		// 通过token获取key对象
		SysAppKeyEbo key = getKey(token);
		if (key == null)
			return false;
		if (key.getLastVisitTime() + beatTimeInSec > DateUtil.getCurTimeInSec())
			return true;
		if (!autoReg)
			throw new DataException("[err]token超时，不进行自动注册!");
		return false;
	}

	// 如果uid和org大于0，则更新
	public void updateKey(String token, int uid, int orgid) throws DataException {
		// 做控制验证有一个空 则不予更新
		boolean isNull = orgid <= 0 || uid <= 0 || Util.isNull(token) ? true : false;
		if (isNull)
			return;
		keyDao.updateKey(token, uid, orgid, DateUtil.getCurTimeInSec());
	}

	public TokenUserOrgDto getUserByToken(String token) throws DataException {
		SysAppKeyEbo key = getKey(token);
		if (key == null)
			return null;
		TokenUserOrgDto t = new TokenUserOrgDto(key.getVisitOrgid(), key.getVisitUid());
		return t;
	}

	// 失效token
	public void invalidKeyByToken(String token) throws DataException {
		SysAppKeyEbo key = getKey(token);
		if (key != null)
			invalidKeyByOpenid(key.getOpenid());
	}

	// 失效token
	public void invalidKeyByOpenid(String openid) throws DataException {
		if (Util.isNull(openid))
			return;
		keyDao.lapseTokenByOpenId(openid, Constant.FLAG_NO);
	}

}
