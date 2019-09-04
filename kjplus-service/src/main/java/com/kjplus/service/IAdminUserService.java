package com.kjplus.service;

import java.util.List;

import com.kjplus.dto.AdminUserDto;
import com.kjplus.eto.AdminUserEto;
import com.kjplus.model.*;
import com.ybk.exception.DataException;

public interface IAdminUserService {

	/**
	 * 登录查询
	 * 
	 * @param user
	 *            登录用户名 password 密码
	 * @return AdminUserEbo
	 * @throws DataException
	 */
	public AdminUserEbo login(String user, String password) throws DataException;

	// 管理员用户名唯一
	public AdminUserEbo getUserByUnameMobile(String userName, String mobile) throws DataException;

	/**
	 * 通过uid获取adminUser对象
	 * 
	 * @param uid
	 *            用户uid
	 * @return
	 * @throws DataException
	 */
	public AdminUserEbo getUserByUid(int uid) throws DataException;

	/**
	 * 注册添加adminUser
	 * 
	 * @param adminUserEto
	 *            adminUserEto
	 * @return
	 * @throws DataException
	 */
	public void register(AdminUserEto adminUserEto) throws DataException;

	/**
	 * 模糊查询adminUser
	 * 
	 * @param userName
	 *            模糊查询 userType adminUser类型 status 状态 page 起始条 paging 显示几条
	 * @return List<AdminUserEbo>
	 * @throws DataException
	 */
	public List<AdminUserDto> listUser(String userName, String nickName, String userType, String status, int orgid,
			int page, int paging) throws DataException;

	// 获得总数
	public int getTotalUser(String userName, String nickName, String userType, String status, int orgid)
			throws DataException;

	// 用户名不可更改，唯一性
	public void updateAdminUser(AdminUserEbo adminUserEbo) throws DataException;

}
