package com.kjplus.dao;

import org.springframework.stereotype.Repository;

import com.kjplus.model.UserLogEbo;
import com.ybk.exception.DataException;

@Repository("userLogDao")
public interface IUserLogDao {

	// 登录成功时添加日志 通过 uid添加
	public void addUserLog(UserLogEbo userLogEbo) throws DataException;

}
