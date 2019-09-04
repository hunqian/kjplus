package com.kjplus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kjplus.model.RongTokenEbo;
import com.kjplus.model.inner.RongTokenInnerEbo;
import com.ybk.exception.DataException;

@Repository("rongTokenDao")
public interface IRongTokenDao {

	/**
	 * 获取rongToken列表
	 * 
	 * @param uid
	 *            用户ID
	 * @return
	 * @throws DataException
	 */
	public List<RongTokenInnerEbo> listRongToken(@Param("uid") int uid) throws DataException;

	/**
	 * 根据uid查询指定rongToken
	 * 
	 * @param uid
	 * @throws DataException
	 */
	public RongTokenInnerEbo getRongTokenByUid(@Param("uid") int uid) throws DataException;

	/**
	 * 根据token查询指定rongToken
	 * 
	 * @param rongToken
	 * @throws DataException
	 */
	public RongTokenInnerEbo getRongTokenByToken(@Param("rToken") String rToken) throws DataException;

	/**
	 * 为用户添加rongToken
	 * 
	 * @param rongTokenEbo
	 * @throws DataException
	 */
	public void addRongToken(RongTokenEbo rongTokenEbo) throws DataException;

	/**
	 * 修改指定用户的rongToken相关信息
	 * 
	 * @param rongTokenEbo
	 * @throws DataException
	 */
	public void updateRongToken(RongTokenEbo rongTokenEbo) throws DataException;

}
