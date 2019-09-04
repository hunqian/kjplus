package com.kjplus.service;

import com.kjplus.eto.UserLogEto;
import com.ybk.exception.DataException;

public interface IUserLogService {

	//登录成功时添加日志 通过 userLogEto添加
	public void addUserLog(UserLogEto userLogEto) throws DataException;
			
}
