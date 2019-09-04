package com.kjplus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kjplus.model.AdminUserEbo;
import com.kjplus.model.inner.AdminUserInnerEbo;
import com.ybk.exception.DataException;

@Repository("adminUserDao")
public interface IAdminUserDao {

	public AdminUserEbo login(@Param("user") String user, @Param("mobileNum") String mobileNum,
			@Param("password") String password, @Param("email") String email, @Param("checkType") int checkType)
			throws DataException;

	//管理员用户名唯一 
	public AdminUserEbo getUserByUnameMobile(@Param("userName") String userName,
			@Param("mobile") String mobile)throws DataException;

	/**
	 * 通过uid获取管理员对象
	 *
	 * @param uid
	 * 		用户uid
	 * @return
	 * @throws DataException
	 */
	public AdminUserEbo getUserByUid(@Param("uid") int uid )throws DataException;

	/**
	 * 注册添加adminUser
	 *  
	 * @param adminUserEbo
	 *
	 * @return
	 * @throws  DataException
	 */
	public void register(AdminUserEbo adminUserEbo) throws DataException;
	
	/**
	 * 模糊查询adminUser 
	 *  
	 * @param 
	 *			userName 模糊查询
	 *			userType adminUser类型
	 *			status  状态
	 *			page 起始条
	 *			paging 显示几条
	 * @return	List<AdminUserEbo>
	 * @throws  DataException
	 */
	public List<AdminUserInnerEbo> listUser(
			@Param("userName") String userName,@Param("nickName") String nickName,
			@Param("userType") String userType ,@Param("status") String status,
			@Param("orgid") int orgid, 
			@Param("page") int page, @Param("paging") int paging) throws DataException;
	
	//获得总数
	public int getTotalUser(@Param("userName") String userName,@Param("nickName") String nickName,
			@Param("userType") String userType ,@Param("status") String status,
			@Param("orgid") int orgid) throws DataException;

/*
	//用户名不可更改，唯一性
	public void updateAdminUser(@Param("userName") String userName,@Param("passWord") String passWord,
			@Param("email") String email,@Param("mobileNum") String mobileNum,
			@Param("status") String status, @Param("face") String face,@Param("nickName") String nickName)
			throws DataException;
	*/
	//用户名不可更改，唯一性
	public void updateAdminUser(AdminUserEbo adminUserEbo)throws DataException;

}
