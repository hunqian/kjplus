package com.ykisswx.service;

import java.util.List;

import com.ybk.exception.DataException;
import com.ykisswx.dto.WxMenuDto;
import com.ykisswx.model.WxMenuEbo;

public interface IWxMenuService {

	// 根据accId获得member
	//treeStyle=true是否返回树状
	public List<WxMenuDto> listMenuByAccid(int accId,String name,String flag, boolean treeStyle, boolean childNearStyle) throws DataException;

	//获取menu
	public WxMenuEbo getWxMenuById(int id) throws DataException;
		
	//添加menu
	public void addMenu(WxMenuEbo wxMenuEbo) throws DataException;

	//修改menu
	public void updateMenu(int id, String name, String url,String flag ,String key,String note,int orderSeq) throws DataException;
	
	//获得下级菜单数量
	public int getMenuNum(int accid, int pid, String flag) throws DataException;
		
}
