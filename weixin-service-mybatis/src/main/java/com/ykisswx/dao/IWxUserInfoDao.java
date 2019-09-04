package com.ykisswx.dao;

import com.ykisswx.dto.WxUserInfoNnDto;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ybk.exception.DataException;
import com.ykisswx.model.WxUserInfoEbo;

import java.util.List;

@Repository("wxUserInfoDao")
public interface IWxUserInfoDao {
	/**
	 * 
	 * @param wxUserid
	 *            wx用戶id
	 * @return 获取对应的accId
	 * @throws DataException
	 */
	public Integer getAccid(@Param("wxUserid") int wxUserid) throws DataException;

	/**
	 * 通过openId查询WxUserInfoEbo对象
	 * 
	 * @param openId
	 *            openId
	 * @return 返回WxUserInfoEbo对象
	 * @throws DataException
	 */
	public WxUserInfoEbo getUserInfoByOpenid(@Param("openId") String openId) throws DataException;

	/**
	 * 根据微信用户userid获取WxUserInfoEbo对象
	 * 
	 * @param userId
	 *            用户的id
	 * @return
	 * @throws DataException
	 */
	public WxUserInfoEbo getUserById(@Param("userId") int userId) throws DataException;

	/**
	 *
	 * @param nickname
	 * 		用户昵称
	 * @param orgid
	 * 		组织id
	 * @param page
	 * 		分页起始
	 * @param paging
	 * 		分页结束
	 * @return
	 * @throws DataException
	 */
	public List<WxUserInfoNnDto> listUserByOrg(@Param("nickname") String nickname,@Param("orgid") int orgid,@Param("page") int page,@Param("paging") int paging) throws DataException;

	/**
	 *
	 * @param nickname
	 * 			用户昵称
	 * @param orgid
	 * 			组织id
	 * @return
	 * @throws DataException
	 */
	public int getTotal(@Param("nickname") String nickname,@Param("orgid")int orgid) throws DataException;
	/**
	 * 添加WxUserInfo表记录
	 * 
	 * @param userInfo
	 *            传入WxUserInfoEbo对象
	 * @throws DataException
	 */
	public void addWxUserInfo(WxUserInfoEbo userInfo) throws DataException;
	
	//通过ID获取微信用户信息
	public WxUserInfoEbo getWxUserById(@Param("uid")int uid) throws DataException;
	
	//上传头像
	public void uploadFace(@Param("uid")int uid,@Param("face") String face) throws DataException;

}
