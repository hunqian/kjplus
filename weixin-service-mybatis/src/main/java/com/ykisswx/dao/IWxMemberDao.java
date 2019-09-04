package com.ykisswx.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ybk.exception.DataException;
import com.ykisswx.model.WxMemberEbo;

@Repository("wxMemberDao")
public interface IWxMemberDao {

	public WxMemberEbo getMemberByMid(@Param("mid") int mid) throws DataException;
}
