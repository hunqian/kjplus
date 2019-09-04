package com.ykisswx.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ybk.exception.DataException;

@Repository("wxUserDao")
public interface IWxUserDao {

	public int getUserId(@Param("openId") String openId) throws DataException;

	public int getMemberUserId(@Param("openId") String openId) throws DataException;
}
