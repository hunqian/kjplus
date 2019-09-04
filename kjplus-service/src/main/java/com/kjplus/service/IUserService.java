package com.kjplus.service;

import java.util.List;

import com.kjplus.dto.UserDto;
import com.kjplus.dto.UserPersonDto;
import com.kjplus.eto.UserEto;
import com.kjplus.model.UserEbo;
import com.kjplus.model.UserPersonEbo;
import com.ybk.exception.DataException;

public interface IUserService {

	// 通过uid查询用户
	public UserEbo getUserById(int uid) throws DataException;

	// 通过username查询用户
	public UserEbo getUserByNameOrMobile(String userName,String mobileNum) throws DataException;

	// 用户登陆
	public UserEbo login(String userName, String passWord) throws DataException;

	// 创建用户
	public UserEbo addUser(UserEto user) throws DataException;

	// 获取微信用户自己的prsnId
	public int getSelfPrsnIdByUserId(int uid) throws DataException;

	// 注册用户列表
	public List<UserPersonDto> listUserPerson(int uid, int psrvId, int orgid, String flag, String srvStatus) throws DataException;

	// 列表 微信用户与档案用户的关系
	public List<UserPersonEbo> listUserPersonMap(int uid, String userType, int prsnId) throws DataException;

	// 查看用户列表
	public List<UserDto> listUser(int uid, String nickName, int orgid, String status, String mobileNum, String userType, int startPage, int endPage)
			throws DataException;

	// 根据uid列表查询user
	public List<UserEbo> listUserByUids(List<Integer> uids) throws DataException;

	// 获取用户总数量
	public int getTotalUser(int uid, String nickName, int orgid, String flag, String mobileNum, String userType) throws DataException;

	// 增加用户和person对应
	public void addUserPersonRel(int uid, String userType, int prsnId) throws DataException;

	// 删除用户和person对应
	public void delUserPersonRel(int uid, String userType, int prsnId) throws DataException;

	// 上传用户头像
	public void uploadFace(int uid, String face) throws DataException;

}
