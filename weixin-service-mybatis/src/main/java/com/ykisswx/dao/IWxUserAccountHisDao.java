package com.ykisswx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ybk.exception.DataException;
import com.ykisswx.model.WxUserAccountHisEbo;

@Repository("wxUserAccountHisDao")
public interface IWxUserAccountHisDao {

	/**
	 * 
	 * @param userId
	 *            用户的userId
	 * @param accId
	 *            accId
	 * @return 返回List<WxUserAccountHisEbo>列表
	 * @throws DataException
	 */
	public List<WxUserAccountHisEbo> listUserAccHisByUserAccId(@Param("userId") int userId, @Param("accId") int accId)
			throws DataException;

	/**
	 * 添加WxUserAccountHis表记录
	 * 
	 * @param userAccountHis
	 *            传入WxUserAccountHisEbo对象
	 * @throws DataException
	 */
	public void addWxUserAccHisEbo(WxUserAccountHisEbo userAccountHis) throws DataException;
}
