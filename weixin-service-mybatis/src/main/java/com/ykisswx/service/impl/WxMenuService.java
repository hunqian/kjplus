package com.ykisswx.service.impl;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ybk.basic.util.DateUtil;

import com.ybk.exception.DataException;
import com.ykisswx.constant.WxConstant;
import com.ykisswx.dao.IWxMenuDao;
import com.ykisswx.dto.WxMenuDto;
import com.ykisswx.model.WxAccountEbo;
import com.ykisswx.model.WxMenuEbo;
import com.ykisswx.model.inner.WxMenuInnerEbo;
import com.ykisswx.service.IWxAccountService;
import com.ykisswx.service.IWxMenuService;

@Component("wxMenuService")
public class WxMenuService implements IWxMenuService {

	@Autowired
	private IWxMenuDao wxMenuDao;

	@Autowired
	private IWxAccountService wxAccountService;

	
	// 根据accId获得member
	//treeStyle=true是否返回树状
	public List<WxMenuDto> listMenuByAccid(int accId,String name,String flag, boolean treeStyle, boolean childNearStyle) throws DataException{
		List<WxMenuInnerEbo> menuList = wxMenuDao.listMenuByAccid(accId,name,flag);
		List<WxMenuDto> menus = new ArrayList<WxMenuDto>();
		Hashtable<Integer, WxMenuDto> menuHash = new Hashtable<Integer, WxMenuDto>();
		WxMenuDto m = null;
		for(WxMenuInnerEbo mi : menuList){
			m = new WxMenuDto();
			BeanUtils.copyProperties(mi, m);
			m.setCreateTime(DateUtil.formatDateTime(mi.getCreateTime()));
			if(!treeStyle){
				menus.add(m);
			}else{
				if(mi.getPid() != null && mi.getPid().intValue()>0 && menuHash.containsKey(mi.getPid())){
					menuHash.get(mi.getPid()).getSubs().add(m);
				}else{
					menus.add(m);
				}
			}
			menuHash.put(mi.getId(), m);
		}
		if(!treeStyle && childNearStyle){
			for(int i=0;i<menus.size();i++){
				m = menus.get(i);
				if(m.getPid() == null || m.getPid().intValue()==0)
					continue;
				if(!menuHash.containsKey(m.getPid()))
					continue;
				boolean reorder = false;
				for(int j=0;j<i;j++){
					if(menus.get(j).getId().intValue() != m.getPid().intValue())
						continue;
					else{
						menus.remove(i);
						menus.add(j+1, m);
						reorder = true;
						
						break;
					}
				}
				/*if(reorder)
					i += 1;
				else
					i += 1;*/
			}
		}
		return menus;
	};

	//获取menu
	public WxMenuEbo getWxMenuById(int id) throws DataException{
		return wxMenuDao.getWxMenuById(id);
	};
		
	
	//添加menu
	public void addMenu(WxMenuEbo wxMenuEbo) throws DataException{
		WxAccountEbo wxAcco =	wxAccountService.getAccountInfo(wxMenuEbo.getAccid(), null);
		if(wxAcco == null)
			throw new DataException("该微信公众号不存在，accID="+wxMenuEbo.getAccid());
		int count = getMenuNum(wxMenuEbo.getAccid(),wxMenuEbo.getPid(), wxMenuEbo.getFlag());
		//判断该组织是否可以继续增加此类菜单
		if(count >= 0 ){
			if(wxMenuEbo.getPid() !=0 ){
				WxMenuEbo menuEbo = wxMenuDao.getWxMenuById(wxMenuEbo.getPid());
				if(menuEbo == null || menuEbo.getPid() !=0)
					throw new DataException("该微信menu不可添加子类, id="+wxMenuEbo.getPid());
			}
			wxMenuDao.addMenu(wxMenuEbo);
		}else{
			throw new DataException("该组织不可在添加此菜单，数目已达上限");
		}
	};
		
	//修改menu
	public void updateMenu( int id, String name, String url, String flag ,String key,String note,int orderSeq) throws DataException{
		WxMenuEbo menu= wxMenuDao.getWxMenuById(id);
		if(menu == null)
			throw new DataException("该menux信息不存在，id="+id);
		if(flag.equals("Y"))
			flag = WxConstant.FLAG_YES;
		else if(flag.equals("N"))
			flag = WxConstant.FLAG_NO;
		else
			throw new DataException("flag格式不对，flag="+flag);
		wxMenuDao.updateMenu(id, name, url, flag,key ,note,orderSeq);
	};
	
	//获得下级菜单数量
	public int getMenuNum(int accid, int pid, String flag) throws DataException{
		WxAccountEbo wxAcco =	wxAccountService.getAccountInfo(accid, null);
		if(wxAcco == null)
			throw new DataException("该微信公众号不存在，accID="+accid);
		int count = wxMenuDao.getMenuNum(accid, pid, flag);
		//控制层判断 3*5原则 添加时判断是
		if(pid == 0){
			//当该微信公众号父菜单数目为3时不可添加菜单
			if(count >= 3){
				return -1;
			}
		}else{
			//当该微信公众号子菜单数目为5时不可添加子菜单
			if(count >= 5){
				return -1;
			}
		}
		return count;
	};
		
	
	
}
