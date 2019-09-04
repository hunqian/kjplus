package com.kjplus.service;

import com.kjplus.model.AdminUserEbo;
import com.ybk.exception.DataException;

import java.util.Map;

public interface ICheckLoginService {

    //登录校验
    public Map<String,Object> checkLogin(String opentoken,String userName,String passWord)throws DataException;
    
	// 生成token
	public String genToken(AdminUserEbo userEbo) throws DataException ;
}
