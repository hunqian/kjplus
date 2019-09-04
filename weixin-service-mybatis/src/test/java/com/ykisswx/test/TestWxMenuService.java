package com.ykisswx.test;

import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.ybk.basic.util.DateUtil;

import com.ykisswx.dao.IWxMenuDao;
import com.ykisswx.dto.WxMenuDto;
import com.ykisswx.model.WxMenuEbo;
import com.ykisswx.service.IWxMenuService;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(value = "classpath:beans.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestWxMenuService {

	@Autowired
	private IWxMenuService wxMenuService;
	@Autowired
	private IWxMenuDao wxMenuDao;
	// 测试获得menu
	@Test
	public void testListMenu() {
		try {
			int accId = 10 ;
			String name = "";
			String flag = "Y";
			List<WxMenuDto> ms= wxMenuService.listMenuByAccid(accId,name,flag, false, true);
			for (WxMenuDto m : ms){
				System.out.println(m.getId() +"," + m.getName() + "_p=" + m.getPid() + "," + m.getPname());
				for (WxMenuDto m2 : m.getSubs()){
					System.out.println("\t[s]" + m2.getName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 测试获得menu
	//@Test
	public void testGetMenu() {
		try {
			int id = 32 ;
			 WxMenuEbo menu= wxMenuDao.getWxMenuById(id);
			 System.out.println("menuName:"+menu.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

	// 测试获得menu
	@Test
	public void testAddMenu() {
		try {
			WxMenuEbo wxMenuEbo =  new WxMenuEbo();
			wxMenuEbo.setPid(74);
			wxMenuEbo.setAccid(10);
			wxMenuEbo.setName("测试添加6");
			wxMenuEbo.setFlag("Y");
			wxMenuEbo.setUrl("");
			wxMenuEbo.setType("click");
			wxMenuEbo.setKey("");
			wxMenuEbo.setNote("备注");
			wxMenuEbo.setOrderSeq(0);
			wxMenuEbo.setCreateTime(DateUtil.newTime());;
			System.out.println("++++"+wxMenuEbo.getName());
			wxMenuService.addMenu(wxMenuEbo);
			 System.out.println("添加成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

	// 测试修改menu
	@Test
	public void testUpdateMenu() {
		try {
			int id = 72;
			String name = "测试修改";
			String url  = "ceshi1xiugia";
			String flag = "N";
			String key = "key";
			String note = "备注信息修改";
			int orderSeq = 11111;
			wxMenuService.updateMenu(id, name, url, flag,key, note,orderSeq);
			System.out.println("修改成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
