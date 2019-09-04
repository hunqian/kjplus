package com.ykisswx.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ybk.exception.DataException;
import com.ykisswx.model.WxUserAccountEbo;

@Repository("wxUserAccountDao")
public interface IWxUserAccountDao {

	/**
	 * 通过 userId 和accId 获取 WxUserAccountEbo对象
	 * 
	 * @param userId
	 *            用户id
	 * @param accId
	 *            accid
	 * @return 返回 WxUserAccountEbo对象
	 * @throws DataException
	 */
	public WxUserAccountEbo getUserAccountByUserAccId(@Param("userId") int userId, @Param("accId") int accId)
			throws DataException;

	/**
	 * 
	 * @param flag
	 *            状态是 S/U
	 * @param unionid
	 *            可空
	 * @param userId
	 *            用户的id
	 * @throws DataException
	 */
	public void updateUserAccount(@Param("flag") String flag, @Param("unionid") String unionid,
			@Param("userId") int userId) throws DataException;

	/**
	 * 添加WxUserAccount表记录
	 * 
	 * @param userAccount
	 *            传入WxUserAccountEbo对象
	 * @throws DataException
	 */
	public void addWxUserAccount(WxUserAccountEbo userAccount) throws DataException;

}
