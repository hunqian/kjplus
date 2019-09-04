package com.ykisswx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ybk.exception.DataException;
import com.ykisswx.dto.WxAccountDto;
import com.ykisswx.eto.WxAccountEto;
import com.ykisswx.model.WxAccountEbo;

@Repository("wxAccountDao")
public interface IWxAccountDao {

	/**
	 * 查询所有Account
	 *
	 * @return 返回List<WxAccountDto>列表
	 * @throws DataException
	 */
	public List<WxAccountEbo> listAccount(@Param("orgId") int orgId, @Param("account") String account,@Param("type") String type,
			 @Param("page") int page, @Param("paging") int paging) throws DataException;

	/**
	 *获取总列
	 *
	 * @param orgId
	 * 		组织id
	 * @param type
	 * 		类型
	 * @return
	 * @throws DataException
	 */
	public Integer getTotalAccount(@Param("orgId") int orgId, @Param("account") String account, @Param("type") String type) throws DataException;

	/**
	 * TODO
	 * 通过 accId获取获取WxAccountDto对象
	 * 
	 * @param accId
	 *            传入accId
	 * @return 获取WxAccountDto对象
	 * @throws DataException
	 */
	public WxAccountDto getAccountDtoInfo(@Param("accId") int accId) throws DataException;

	/**
	 * 通过accId获取WxAccountEbo对象
	 * 
	 * @param accId
	 *            传入accId
	 * @return 获取WxAccountEbo对象
	 * @throws DataException
	 */
	public WxAccountEbo getAccountEboInfo(@Param("accId") int accId) throws DataException;

	/**
	 * 通过appid获取WxAccountEbo对象
	 * 
	 * @param appId
	 *            传入appid
	 * @return 获取WxAccountEbo对象
	 * @throws DataException
	 */
	public WxAccountEbo getAccountByAppid(@Param("appId") String appId) throws DataException;

	/**
	 * 通过 accCode获取 WxAccountEbo对象
	 * 
	 * @param accCode
	 *            传入accCode
	 * @return 获取WxAccountEbo对象
	 * @throws DataException
	 */
	public WxAccountEbo getAccountByAccCode(@Param("accCode") String accCode) throws DataException;

	/**
	 * 通过 code 和sessCode 进行查询
	 * 
	 * @param code
	 *            传入编号
	 * @param sessCode
	 *            传入sessCode
	 * @return 放回WxAccountEbo对象
	 * @throws DataException
	 */
	public WxAccountEbo getAccountInfoByCode(@Param("token") String token, @Param("sessCode") String sessCode)
			throws DataException;

	/**
	 * 添加addWxAccount
	 * 
	 * @param Account
	 *            传入WxAccountEbo对象
	 * @throws DataException
	 */
	public void addWxAccount(WxAccountEbo Account) throws DataException;

	/**
	 * 通过 accid 或 appid 获取当前微信的组织id
	 * 
	 * @param accid
	 *            accid
	 * @param appid
	 *            appid
	 * @return orgId组织ID
	 * @throws DataException
	 */
	public int getOrgid(@Param("accId") int accId, @Param("appId") String appId) throws DataException;

	/**
	 * 通过 openId 获取对应的组织ID
	 * 
	 * @param openId
	 * @return orgId组织ID
	 * @throws DataException
	 */
	public int getOrgidByOpenid(@Param("openId") String openId) throws DataException;

	/**
	 * 一个组织可以对应多个微信公众号
	 * 根据orgid获得acc对象
	 * 
	 * @param orgid
	 *            组织ID
	 * @return 返回WxAccountEbo对象
	 * 
	 * @throws DataException
	 */
	public List<WxAccountEbo> listAccountIdByOrgid(@Param("orgid") int orgid) throws DataException;

	/**
	 * 编辑WxAccount
	 * 
	 * @param accid
	 *            accid
	 * @param name
	 *            名称
	 * @param intro
	 * 
	 * @param url
	 *            路径
	 * @param token
	 *            交互token
	 * @param encoding
	 *            编码
	 * @param mode
	 *            Secure安全/Text明文/Compatible兼容
	 * @throws DataException
	 */
	public void editAccount(@Param("accid") Integer accid,@Param("wxAccountEto") WxAccountEto wxAccountEto) throws DataException;
}
