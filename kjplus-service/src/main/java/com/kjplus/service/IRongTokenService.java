package com.kjplus.service;

import java.util.List;

import com.kjplus.dto.RongTokenDto;
import com.kjplus.model.RongTokenEbo;
import com.ybk.exception.DataException;

public interface IRongTokenService {

	/**
	 * 获取rongToken列表
	 * 
	 * @param uid
	 *            用户ID
	 * @return
	 * @throws DataException
	 */
	public List<RongTokenDto> listRongToken(int uid) throws DataException;

	/**
	 * 根据uid查询指定rongToken
	 * 
	 * @param uid
	 * @return
	 * @throws DataException
	 */
	public RongTokenDto getRongTokenByUid(int uid) throws DataException;

	/**
	 * 根据token查询指定rongToken
	 * 
	 * @param rongToken
	 * @return
	 * @throws DataException
	 */
	public RongTokenDto getRongTokenByToken(String rongToken) throws DataException;

	/**
	 * 为用户添加rongToken
	 * 
	 * @param rongTokenEbo
	 * @throws DataException
	 */
	public RongTokenEbo addRongToken(RongTokenEbo rongTokenEbo) throws DataException;

	/**
	 * 修改指定用户的rongToken相关信息
	 * 
	 * @param rongTokenEbo
	 * @throws DataException
	 */
	public void updateRongToken(RongTokenEbo rongTokenEbo) throws DataException;
}
