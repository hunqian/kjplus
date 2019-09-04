package com.kjplus.service;

import com.kjplus.dto.*;
import com.kjplus.model.*;
import com.ybk.exception.DataException;


import java.util.List;

//小程序appkey管理
public interface IAppKeyService {

	//根据token获得
	public SysAppKeyEbo getKey(String token) throws DataException;

	//自定义获取token是否有效 可能包含多个 直接取列表对象
	public List<SysAppKeyEbo> getKey(String token,String flag) throws DataException;

	//根据openid获得
	public SysAppKeyEbo getKeyByOpenid(String openid) throws DataException;

	//活的当前用户最近一条有效或者无效的openId和opentoken记录
	public SysAppKeyEbo getKeyByUid(int uid,String flag) throws DataException;

	//自定义获取openid是否有效 可能包含多个 直接取列表对象
	public List<SysAppKeyEbo> getKeyByOpenid(String openid, String flag) throws DataException;

	//获得openid的数量
	public Integer getKeyOpenidNum(String openid) throws DataException;

	//添加一个openid和token的对应关系，本方法只添加，不负责对token的超时管理
	public SysAppKeyEbo addKey(String openid,int uid,int orgId) throws DataException;
	
	//根据openid进行合法性的验证，对于超出beatTime的token，如果autoReg=true重新生成key，否则抛出超时异常
	public boolean checkOpenid(String openid, int beatTimeInSec, boolean autoReg) throws DataException;
	
	//根据token进行合法性的验证，对于超出beatTime的token，如果autoReg=true重新生成key，否则抛出超时异常
	public boolean checkToken(String token, int beatTimeInSec, boolean autoReg) throws DataException;
	
	//更新key的访问时间和访问次数
	public void updateKey(String token, int uid, int orgid) throws DataException;
	
	//获得token对应的org和uid对象
	public TokenUserOrgDto getUserByToken(String token) throws DataException;
	
	//失效token
	public void invalidKeyByToken(String token) throws DataException;
	
	//失效token
	public void invalidKeyByOpenid(String openid) throws DataException;

}
