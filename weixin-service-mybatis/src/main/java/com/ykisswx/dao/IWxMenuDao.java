package com.ykisswx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ybk.exception.DataException;
import com.ykisswx.model.WxMenuEbo;
import com.ykisswx.model.inner.WxMenuInnerEbo;

/**
 * 
 * @author niuzhiwei 微信菜单
 */
@Repository("wxMenuDao")
public interface IWxMenuDao {

	public List<WxMenuInnerEbo> listMenuByAccid(@Param("accId") int accId,@Param("name") String name,@Param("flag") String flag) throws DataException;
	
	//获取menu
	public WxMenuEbo getWxMenuById(@Param("id") int id) throws DataException;
	
	//添加menu
	public void addMenu(WxMenuEbo wxMenuEbo) throws DataException;
	
	//修改menu
	public void updateMenu(@Param("id") int id,@Param("name") String name,@Param("url") String url,@Param("flag") String flag
			,@Param("key") String key,@Param("note") String note,@Param("orderSeq") int orderSeq) throws DataException;
	
	//获得下级菜单数量
	public int getMenuNum(@Param("accId") int accId,@Param("pid")  int pid,@Param("flag")  String flag) throws DataException;

	
}
