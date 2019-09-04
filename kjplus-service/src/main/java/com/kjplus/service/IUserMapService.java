package com.kjplus.service;

import com.kjplus.model.UserMapEbo;
import com.ybk.exception.DataException;

public interface IUserMapService {

	public UserMapEbo addUserMap(UserMapEbo userMapEbo) throws DataException;

	public int getStaffIdByUid(int uid) throws DataException;
	
	public int getStaffIdByAdminUserId(int uid) throws DataException;

	public int getUserIdByStaffId(int staffIde) throws DataException;

	public int getUserIdByAdminUserId(int uid) throws DataException;

}
