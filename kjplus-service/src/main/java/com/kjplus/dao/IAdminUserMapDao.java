package com.kjplus.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kjplus.model.AdminUserMapEbo;
import com.ybk.exception.DataException;

@Repository("adminUserMapDao")
public interface IAdminUserMapDao {

	// 添加uid userType
	public void addUserMap(AdminUserMapEbo adminUserMapEbo) throws DataException;

	// 查询 useType不能为空(业务层判断) 否则可能查到两个数据
	public AdminUserMapEbo getUserMap(@Param("staffid") int staffid, @Param("uid") int uid,
			@Param("userType") String userType) throws DataException;

}
