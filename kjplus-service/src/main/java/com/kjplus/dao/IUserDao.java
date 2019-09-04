package com.kjplus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kjplus.model.UserEbo;
import com.kjplus.model.UserPersonEbo;
import com.kjplus.model.inner.UserPersonInnerEbo;
import com.ybk.exception.DataException;

@Repository("userDao")
public interface IUserDao {

	public UserEbo getUser(@Param("uid") int uid, @Param("userName") String userName, @Param("mobile") String mobile) throws DataException;

	public UserEbo login(@Param("user") String user, @Param("mobileNum") String mobileNum, @Param("password") String password, @Param("email") String email,
			@Param("checkType") int checkType) throws DataException;

	public List<UserPersonInnerEbo> listUserPersonInner(@Param("uid") int uid, @Param("psrvId") int psrvId, @Param("flag") String flag,
			@Param("srvStatus") String srvStatus) throws DataException;

	/**
	 * 添加表格数据存储主表
	 * 
	 * @param dataHead
	 *            传入TableDataHeadEbo对象
	 * @throws DataException
	 */
	public void addUser(UserEbo user) throws DataException;

	// 查詢user列表
	public List<UserEbo> listUser(@Param("uid") int uid, @Param("nickName") String nickName, @Param("orgid") int orgid, @Param("status") String status,
			@Param("mobileNum") String mobileNum, @Param("userType") String userType, @Param("page") int page, @Param("paging") int paging)
			throws DataException;

	// 根据uid列表查询user
	public List<UserEbo> listUserByUids(@Param("uids") String uids) throws DataException;

	// 获得总数
	public int getTotalUser(@Param("uid") int uid, @Param("nickName") String nickName, @Param("orgid") int orgid, @Param("status") String status,
			@Param("mobileNum") String mobileNum, @Param("userType") String userType) throws DataException;

	// 列表 微信用户与档案用户的关系
	public List<UserPersonEbo> listUserPerson(@Param("uid") int uid, @Param("userType") String userType, @Param("prsnId") int prsnId) throws DataException;

	// 增加
	public void addUserPersonRel(UserPersonEbo up) throws DataException;

	// 删除
	public void delUserPersonById(@Param("id") int id) throws DataException;

	// 删除
	public void delUserPerson(@Param("uid") int uid, @Param("userType") String userType, @Param("prsnId") int prsnId) throws DataException;

	// 上传头像
	public void uploadFace(@Param("uid") int uid, @Param("face") String face) throws DataException;
	
	//获取本人自己的对应信息
	public UserPersonEbo getSelfPrsnIdByUserId(@Param("uid") int uid, @Param("userType") String userType, @Param("typeId") int typeId) throws DataException;

}
