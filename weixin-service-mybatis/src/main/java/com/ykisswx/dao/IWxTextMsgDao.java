package com.ykisswx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ybk.exception.DataException;
import com.ykisswx.model.inner.WxTextMsgInnerEbo;

@Repository("wxTestMsgDao")
public interface IWxTextMsgDao {

	/**
	 * 查询所有TextMsg
	 *
	 * @return 返回List<WxTextMsgInnerEbo>列表
	 * @throws DataException
	 */
	public List<WxTextMsgInnerEbo> listTextMsg(@Param("accid") int accid,@Param("userid") int userid,
			 @Param("msg") String msg, @Param("btime") int btime, @Param("etime") int etime,
			 @Param("page") int page, @Param("paging") int paging) throws DataException;
	
	/**
	 *获取总列
	 *
	 * @param accid
	 * 		微信账号id
	 * @param userid
	 * 		用户信息
	 * @return
	 * @throws DataException
	 */
	public Integer getTotalTextMsg(@Param("accid") int accid, @Param("userid") int userid,
			@Param("msg") String msg,@Param("btime") int btime,@Param("etime") int etime) throws DataException;
	
	/**
	 * 添加addWxTextMsg
	 * 
	 * @param TextMsg
	 *            传入WxTextMsgInnerEbo对象
	 * @throws DataException
	 */
	public void addWxTextMsg(WxTextMsgInnerEbo TextMsg) throws DataException;

}
