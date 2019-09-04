package com.kjplus.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kjplus.model.UserMapEbo;
import com.ybk.exception.DataException;

@Repository("userMapDao")
public interface IUserMapDao {

	// 添加uid userType
	public void addUserMap(UserMapEbo userMapEbo) throws DataException;

	public UserMapEbo getUserMap(@Param("mainId") int mainId, @Param("mainType") String mainType, @Param("uid") int uid,
			@Param("userType") String userType) throws DataException;

	/*
	 * public List<UserMapInnerEbo> listUserMap(@Param("mainId") int
	 * mainId,@Param("mainType") String mainType, @Param("uid") int uid,
	 * 
	 * @Param("userType") String userType) throws DataException;
	 */
}
